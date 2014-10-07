/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.controller.report;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.report.FixedReport;
import uk.org.rbc1b.roms.db.report.ReportDao;
import au.com.bytecode.opencsv.CSVWriter;
import com.google.common.net.MediaType;

/**
 * Handle data reports.
 */
@Controller
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportDao reportDao;

    @Autowired
    private DataSource dataSource;

    /**
     * Return the list of active reports.
     * @param model model
     * @return view name
     * id is found
     */
    @RequestMapping(value = "fixed", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('REPORT', 'READ')")
    public String showFixedReports(ModelMap model) {
        List<FixedReport> reports = reportDao.findFixedReports();

        List<FixedReportModel> reportModels = new ArrayList<FixedReportModel>(reports.size());
        for (FixedReport report : reports) {
            reportModels.add(createFixedReportModel(report));
        }

        model.addAttribute("reports", reportModels);
        return "reports/fixed/list";
    }

    /**
     * Run a fixed report, returning the data in html format.
     * @param reportId report id
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find the report
     */
    @RequestMapping(value = "fixed/{reportId}/html", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('REPORT', 'READ')")
    public String runHtmlReport(@PathVariable Integer reportId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        FixedReport fixedReport = reportDao.findFixedReport(reportId);
        if (fixedReport == null) {
            throw new NoSuchRequestHandlingMethodException("No fixed report #" + reportId, this.getClass());
        }
        model.addAttribute("report", createFixedReportModel(fixedReport));
        try {
            ReportResults reportResults = extractResults(fixedReport.getQuery());
            model.addAttribute("columnNames", reportResults.columnNames);
            model.addAttribute("resultRows", reportResults.resultRows);
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to extract report data. Message: [" + e.getMessage() + "]", e);
        }

        return "reports/fixed/show-html";
    }

    /**
     * Run a fixed report, returning the data in a downloadable csv format.
     * @param reportId report id
     * @param response servlet response to output the csv data to directly
     * @throws NoSuchRequestHandlingMethodException on failure to find the report
     * @throws IOException on failure to write to output stream
     */
    @RequestMapping(value = "fixed/{reportId}/csv", method = RequestMethod.GET, consumes = "text/csv", produces = "text/csv")
    @PreAuthorize("hasPermission('REPORT', 'READ')")
    public void downloadCsvReport(@PathVariable Integer reportId, HttpServletResponse response)
            throws NoSuchRequestHandlingMethodException, IOException {

        FixedReport fixedReport = reportDao.findFixedReport(reportId);
        if (fixedReport == null) {
            throw new NoSuchRequestHandlingMethodException("No fixed report #" + reportId, this.getClass());
        }

        ReportResults reportResults;
        try {
            reportResults = extractResults(fixedReport.getQuery());
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to extract report data. Message: [" + e.getMessage() + "]", e);
        }

        String[] headers = reportResults.columnNames.toArray(new String[reportResults.columnNames.size()]);

        List<String[]> records = new ArrayList<String[]>();
        for (List<String> reportRow : reportResults.resultRows) {
            records.add(reportRow.toArray(new String[reportRow.size()]));
        }

        String fileName = "edifice-report-" + fixedReport.getName().replace(" ", "-").toLowerCase() + ".csv";

        response.setContentType(MediaType.CSV_UTF_8.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        OutputStream output = response.getOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(output), '\u0009');

        writer.writeNext(headers);
        writer.writeAll(records);

        writer.close();

    }

    private FixedReportModel createFixedReportModel(FixedReport report) {
        FixedReportModel reportModel = new FixedReportModel();
        reportModel.setName(report.getName());
        reportModel.setDescription(report.getDescription());
        reportModel.setCsvUri("/reports/fixed/" + report.getFixedReportId() + "/csv");
        reportModel.setHtmlUri("/reports/fixed/" + report.getFixedReportId() + "/html");
        return reportModel;
    }

    private ReportResults extractResults(String sql) throws SQLException {
        Connection con = DataSourceUtils.getConnection(dataSource);
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery(sql);

        ReportResults reportResults = new ReportResults();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        List<Integer> columnTypeIds = new ArrayList<Integer>();
        for (int i = 0; i < columnCount; i++) {
            columnTypeIds.add(rsmd.getColumnType(i + 1));
        }

        reportResults.columnNames = new ArrayList<String>();
        for (int i = 0; i < columnCount; i++) {
            reportResults.columnNames.add(rsmd.getColumnLabel(i + 1));
        }

        reportResults.resultRows = new ArrayList<List<String>>();
        while (rs.next()) {
            List<String> resultRow = new ArrayList<String>();

            for (int i = 0; i < columnCount; i++) {
                Integer columnTypeId = columnTypeIds.get(i);
                if (columnTypeId.intValue() == Types.BOOLEAN || columnTypeId.intValue() == Types.BIT) {
                    resultRow.add(Boolean.valueOf(rs.getBoolean(i + 1)).toString());
                } else {
                    resultRow.add(rs.getString(i + 1));
                }
            }

            reportResults.resultRows.add(resultRow);
        }

        return reportResults;
    }

    private static final class ReportResults {
        private List<String> columnNames;
        private List<List<String>> resultRows;
    }

}
