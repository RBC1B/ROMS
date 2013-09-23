/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
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
package uk.org.rbc1b.roms.controller.volunteer;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * View that builds the Volunteer Badge as a PDF.
 *
 * @author rahulsingh
 */
public class VolunteerBadgePdfView extends AbstractPdfView {

    /**
     * Builds the PDF document of the badge.
     *
     * @param model model
     * @param document document
     * @param writer writer
     * @param request http request
     * @param response http response
     * @throws Exception if not formed
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Volunteer volunteer = (Volunteer) model.get("volunteer");
        String colourBand = (String) model.get("colourBand");
        Set<String> skillsSet = (Set<String>) model.get("skillsSet");
        String assignment = (String) model.get("assignment");
        document.open();

        document.setPageSize(PageSize.ID_1);

        PdfContentByte cb = writer.getDirectContent();

        VolunteerBadgePdfUtils.addBand(cb, colourBand);
        VolunteerBadgePdfUtils.addSkillsTable(cb);

        VolunteerBadgePdfUtils.addSkils(cb, skillsSet);

        ServletContext context = request.getServletContext();
        String contextPath = context.getRealPath("/");
        Image img = Image.getInstance(contextPath + "/images/oli-lion.jpg");

        VolunteerBadgePdfUtils.addImage(cb, img);
        VolunteerBadgePdfUtils.addBarcode(cb, volunteer.getPersonId());
        VolunteerBadgePdfUtils.addVolunteerName(cb, volunteer.getForename() + " " + volunteer.getSurname());
        VolunteerBadgePdfUtils.addRBCRegionTitle(cb, "RBC London and Home Counties");

        VolunteerBadgePdfUtils.addDepartment(cb, assignment);
        VolunteerBadgePdfUtils.addBigRectangle(cb);
        VolunteerBadgePdfUtils.addBadgeTitle(cb, "Kingdom Hall Construction");
    }
}
