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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.project.ProjectAttendance;
import uk.org.rbc1b.roms.db.project.ProjectAttendanceDao;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectAvailabilityDao;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;

/**
 * Factory to generate model for volunteers, and dates they are available. Used
 * to confirm who is coming when.
 */
@Component
public class VolunteersToConfirmModelFactory {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private ProjectAttendanceDao projectAttendanceDao;
    @Autowired
    private ProjectAvailabilityDao projectAvailabilityDao;

    /**
     * Generates the model to display as json data for volunteers and the dates
     * they are available.
     *
     * @param assignments list of departmental assignments
     * @param workSession the work session
     * @param dates the date range for this session
     * @return list of volunteers to confirm their attenance
     */
    public List<VolunteerToConfirmModel> generate(List<Assignment> assignments,
            ProjectDepartmentSession workSession, List<String> dates) {
        List<VolunteerToConfirmModel> modelList = new ArrayList<>();
        FastDateFormat formatter = FastDateFormat.getInstance("dd-MM-yyyy");
        for (Assignment assignment : assignments) {
            ProjectAvailability projectAvailability = projectAvailabilityDao
                    .find(assignment.getPerson().getPersonId(), workSession.getProjectDepartmentSessionId());
            if (projectAvailability != null) {
                VolunteerToConfirmModel model = new VolunteerToConfirmModel();
                List<ProjectAttendanceDate> attendanceDates = new ArrayList<>();

                Person person = personDao.findPerson(assignment.getPerson().getPersonId());
                model.setPersonId(person.getPersonId());
                model.setName(person.getSurname() + ", " + person.getForename());
                for (String date : dates) {
                    try {
                        ProjectAttendanceDate attendanceDate = new ProjectAttendanceDate();
                        attendanceDate.setAvailableDate(date);
                        ProjectAttendance attendance = projectAttendanceDao
                                .getAvailableDate(projectAvailability, new java.sql.Date(formatter.parse(date).getTime()));
                        if (attendance == null) {
                            attendanceDate.setProjectAttendanceId(null);
                        } else {
                            attendanceDate.setProjectAttendanceId(attendance.getProjectAttendanceId());
                            attendanceDate.setRequired(attendance.isRequired());
                        }
                        attendanceDates.add(attendanceDate);
                    } catch (ParseException e) {
                        ProjectAttendanceDate nullAttendanceDate = new ProjectAttendanceDate();
                        nullAttendanceDate.setAvailableDate(date);
                        attendanceDates.add(nullAttendanceDate);
                    }
                }
                model.setProjectAttendanceDates(attendanceDates);
                modelList.add(model);
            }
        }
        return modelList;
    }
}
