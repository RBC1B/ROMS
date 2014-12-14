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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.org.rbc1b.roms.db.project.ProjectAttendance;
import uk.org.rbc1b.roms.db.project.ProjectAttendanceDao;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectAvailabilityDao;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSessionDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.AssignmentSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;

/**
 * Handles all REST Project availability/attendance AJAX calls producing JSON.
 */
@RestController
@RequestMapping(value = "/service/projects")
public class ProjectRestController {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ProjectDepartmentSessionDao projectDepartmentSessionDao;
    @Autowired
    private VolunteersToConfirmModelFactory volunteersToConfirmModelFactory;
    @Autowired
    private ProjectAvailabilityDao projectAvailabilityDao;
    @Autowired
    private ProjectAttendanceDao projectAttendanceDao;

    /**
     * Gets a list of dates in a range for a department session.
     *
     * @param sessionId the projectDepartmentSessionId
     * @return respnseEntity with list and status code
     */
    @RequestMapping(value = "/session/{sessionId}/dates", method = RequestMethod.GET)
    public ResponseEntity<Object> getSessionDates(@PathVariable Integer sessionId) {
        ProjectDepartmentSession session = projectDepartmentSessionDao.findByProjectDepartmentSessionId(sessionId);
        if (session == null) {
            return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
        } else {
            List<String> dateRange = generateDateRange(new DateTime(session.getFromDate()), new DateTime(session.getToDate()));
            return new ResponseEntity<Object>(dateRange, HttpStatus.OK);
        }
    }

    /**
     * Gets a list of volunteers and the dates that they are available.
     *
     * @param sessionId the project department session id
     * @return responseEntity with the list and status code
     */
    @RequestMapping(value = "/session/{sessionId}/volunteers", method = RequestMethod.GET)
    public ResponseEntity<Object> getVolunteersToConfirm(@PathVariable Integer sessionId) {
        ProjectDepartmentSession session = projectDepartmentSessionDao.findByProjectDepartmentSessionId(sessionId);
        if (session == null) {
            return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
        } else {
            AssignmentSearchCriteria assignmentSearchCriteria = new AssignmentSearchCriteria();
            assignmentSearchCriteria.setDepartmentId(session.getDepartment().getDepartmentId());
            List<Assignment> departmentVolunteers = departmentDao.findAssignments(assignmentSearchCriteria);
            List<VolunteerToConfirmModel> volunteersToConfirm =
                    volunteersToConfirmModelFactory
                    .generate(departmentVolunteers, session, generateDateRange(new DateTime(session.getFromDate()), new DateTime(session.getToDate())));
            return new ResponseEntity<Object>(volunteersToConfirm, HttpStatus.OK);
        }
    }

    /**
     * Saves the attendance required update.
     *
     * @param attendanceId the attendance id
     * @param requiredDate whether the volunteer is to be invited or not
     * @return responseEntity
     */
    @RequestMapping(value = "/attendance/{attendanceId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> inviteVolunteer(@PathVariable Integer attendanceId, @RequestBody ProjectAttendanceModel requiredDate) {
        ProjectAttendance projectAttendance = projectAttendanceDao.find(attendanceId);
        if (projectAttendance == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            projectAttendance.setRequired(requiredDate.isRequired());
            projectAttendanceDao.update(projectAttendance);
            ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAttendance.getProjectAvailability().getProjectAvailabilityId());
            if (projectAvailability != null) {
                projectAvailability.setOverseerConfirmed(true);
                projectAvailabilityDao.update(projectAvailability);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    private List<String> generateDateRange(DateTime startDate, DateTime endDate) {
        List<String> range = new ArrayList<>();
        DateTime tmpdate;
        tmpdate = startDate;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        while (tmpdate.isBefore(endDate) || tmpdate.equals(endDate)) {
            range.add(formatter.format(tmpdate.toDate()));
            tmpdate = tmpdate.plusDays(1);
        }
        return range;
    }
}
