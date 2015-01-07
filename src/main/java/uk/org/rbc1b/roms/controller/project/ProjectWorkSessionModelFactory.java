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
package uk.org.rbc1b.roms.controller.project;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;

/**
 *
 * @author ramindursingh
 */
@Component
public class ProjectWorkSessionModelFactory {

    private static final String DATEFORMAT = "dd-MM-YYYY";

    /**
     * Generates the model for displaying in JSP.
     *
     * @param sessions the projectDepartmentSession list
     * @return the model
     */
    public List<ProjectWorkSessionModel> generate(List<ProjectDepartmentSession> sessions) {
        List<ProjectWorkSessionModel> workSessions = new ArrayList<ProjectWorkSessionModel>();

        for (ProjectDepartmentSession session : sessions) {
            ProjectWorkSessionModel model = new ProjectWorkSessionModel();

            FastDateFormat dateFormat = FastDateFormat.getInstance(DATEFORMAT);
            model.setToDate(dateFormat.format(session.getToDate()));
            model.setFromDate(dateFormat.format(session.getFromDate()));

            model.setProjectDepartmentSession(session.getDepartment().getName()
                    + " - " + session.getProjectDepartmentSessionId()
                    + " - " + model.getFromDate());

            model.setDepartmentId(session.getDepartment().getDepartmentId());
            model.setProjectId(session.getProject().getProjectId());

            model.setSunday(session.isSunday());

            workSessions.add(model);
        }

        return workSessions;
    }
}
