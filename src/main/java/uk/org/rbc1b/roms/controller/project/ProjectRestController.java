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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uk.org.rbc1b.roms.controller.BadRequestException;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.project.ProjectAttendance;
import uk.org.rbc1b.roms.db.project.ProjectAttendanceDao;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectAvailabilityDao;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSessionDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.AssignmentSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.security.ROMSUserDetails;

/**
 * Handles all REST Project availability/attendance AJAX calls producing JSON.
 */
@RestController
@RequestMapping(value = "/service/projects")
public class ProjectRestController {

    @Autowired
    private PersonDao personDao;
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
    @Autowired
    private VolunteerForProjectModelFactory volunteerForProjectModelFactory;
    @Autowired
    private ProjectGateListModelFactory projectGateListModelFactory;

    /**
     * Handles requests to insert into availability, when a volunteer is
     * invited.
     *
     * @param sessionId the projectDepartmentSession id
     * @param personId the volunteer to invite
     * @throws BadRequestException if person or session does not exist
     */
    @RequestMapping(value = "/sessions/{sessionId}/person/{personId}/availability", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('PROJECT', 'EDIT')")
    public void insertAvailabilityForVolunteer(@PathVariable Integer sessionId,
            @PathVariable Integer personId)
            throws BadRequestException {
        ProjectAvailability availability = new ProjectAvailability();
        Person person = personDao.findPerson(personId);
        ProjectDepartmentSession workSession = projectDepartmentSessionDao.findByProjectDepartmentSessionId(sessionId);
        if (person == null || workSession == null) {
            throw new BadRequestException("Cannot add Availability for session " + sessionId + " or person " + personId);
        }
        availability.setPerson(person);
        availability.setProjectDepartmentSession(workSession);

        availability.setUpdatedBy(findUserId());
        availability.setUpdateTime(new java.sql.Date(new java.util.Date().getTime()));

        projectAvailabilityDao.save(availability);
    }

    /**
     * Handles requests to delete from availability, when a volunteer is not
     * invited.
     *
     * @param sessionId the project department session id
     * @param personId the person id
     * @throws BadRequestException if session or person does not exist
     */
    @RequestMapping(value = "/sessions/{sessionId}/person/{personId}/availability", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('PROJECT', 'EDIT')")
    public void deleteAvailabilityForVolunteer(@PathVariable Integer sessionId,
            @PathVariable Integer personId) throws BadRequestException {
        ProjectAvailability availability = projectAvailabilityDao.find(personId, sessionId);
        if (availability == null) {
            throw new BadRequestException("Cannot delete Availability for session " + sessionId + " or person " + personId);
        }
        projectAvailabilityDao.delete(availability);
    }

    /**
     * Gets a list of dates in a range for a department session.
     *
     * @param sessionId the projectDepartmentSessionId
     * @return responseEntity with list and status code
     * @throws BadRequestException if session id is not found
     */
    @RequestMapping(value = "/session/{sessionId}/dates", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public ResponseEntity<Object> getSessionDates(@PathVariable Integer sessionId)
            throws BadRequestException {
        ProjectDepartmentSession session = projectDepartmentSessionDao.findByProjectDepartmentSessionId(sessionId);
        if (session == null) {
            throw new BadRequestException("No session found with session id" + sessionId);
        }
        List<String> dateRange = generateDateRange(new DateTime(session.getFromDate()), new DateTime(session.getToDate()));
        return new ResponseEntity<Object>(dateRange, HttpStatus.OK);
    }

    /**
     * Returns a list of volunteers who are in the department matching
     * projectDepartmentSession.
     *
     * @param sessionId the project department session id
     * @return ResponseEntity containing list of volunteers and status
     * @throws BadRequestException if session id does not exist
     */
    @RequestMapping(value = "/department/volunteers/session/{sessionId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public ResponseEntity<Object> findDepartmentVolunteers(@PathVariable Integer sessionId)
            throws BadRequestException {
        ProjectDepartmentSession workSession = projectDepartmentSessionDao.findByProjectDepartmentSessionId(sessionId);
        if (workSession == null) {
            throw new BadRequestException("No session found with session id" + sessionId);
        }
        AssignmentSearchCriteria assignmentSearchCriteria = new AssignmentSearchCriteria();
        assignmentSearchCriteria.setMaxResults(null);
        assignmentSearchCriteria.setDepartmentId(workSession.getDepartment().getDepartmentId());
        List<Assignment> departmentVolunteers = departmentDao.findAssignments(assignmentSearchCriteria);
        return new ResponseEntity<Object>(volunteerForProjectModelFactory.generate(departmentVolunteers, workSession), HttpStatus.OK);
    }

    /**
     * Gets a list of volunteers and the dates that they are available.
     *
     * @param sessionId the project department session id
     * @return responseEntity with the list and status code
     * @throws BadRequestException if session does not exist
     */
    @RequestMapping(value = "/session/{sessionId}/volunteers", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public ResponseEntity<Object> getVolunteersToConfirm(@PathVariable Integer sessionId)
            throws BadRequestException {
        ProjectDepartmentSession session = projectDepartmentSessionDao.findByProjectDepartmentSessionId(sessionId);
        if (session == null) {
            throw new BadRequestException("No session found with session id" + sessionId);
        }
        AssignmentSearchCriteria assignmentSearchCriteria = new AssignmentSearchCriteria();
        assignmentSearchCriteria.setDepartmentId(session.getDepartment().getDepartmentId());
        assignmentSearchCriteria.setMaxResults(null);
        List<Assignment> departmentVolunteers = departmentDao.findAssignments(assignmentSearchCriteria);
        List<VolunteerToConfirmModel> volunteersToConfirm =
                volunteersToConfirmModelFactory
                .generate(departmentVolunteers, session, generateDateRange(new DateTime(session.getFromDate()), new DateTime(session.getToDate())));
        return new ResponseEntity<Object>(volunteersToConfirm, HttpStatus.OK);
    }

    /**
     * Saves the attendance required update.
     *
     * @param attendanceId the attendance id
     * @param requiredDate whether the volunteer is to be invited or not
     * @throws BadRequestException if attendance id does not exist
     */
    @RequestMapping(value = "/attendance/{attendanceId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('PROJECT', 'EDIT')")
    public void inviteVolunteer(@PathVariable Integer attendanceId, @RequestBody ProjectAttendanceModel requiredDate)
            throws BadRequestException {
        ProjectAttendance projectAttendance = projectAttendanceDao.find(attendanceId);
        if (projectAttendance == null) {
            throw new BadRequestException("No attendance with id " + attendanceId);
        }
        projectAttendance.setRequired(requiredDate.isRequired());
        projectAttendanceDao.update(projectAttendance);
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAttendance.getProjectAvailability().getProjectAvailabilityId());
        if (projectAvailability != null) {
            projectAvailability.setOverseerConfirmed(true);
            if (projectAvailability.isConfirmationEmail()) {
                projectAvailability.setConfirmationEmail(false);
                projectAvailability.setDatesChanged(true);
            }
            projectAvailabilityDao.update(projectAvailability);
        }
    }

    /**
     * Gets a list of volunteers attending a project on a given date for gate
     * list.
     *
     * @param projectId the project Id
     * @param projectDate the date - a string, if ALL, then gets all dates
     * @return response entity JSON
     * @throws ParseException if cannot convert to a valid date
     */
    @RequestMapping(value = "/project-gatelist/{projectId}/date/{projectDate}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public ResponseEntity<Object> getGateListByDate(@PathVariable Integer projectId, @PathVariable String projectDate)
            throws ParseException {
        List<ProjectAttendance> attendances;
        if (projectDate.equalsIgnoreCase("ALL")) {
            attendances = projectAttendanceDao.findConfirmedVolunteersForProject(projectId);
        } else {
            FastDateFormat format = FastDateFormat.getInstance("dd-MM-yyyy");
            java.util.Date dateParser = format.parse(projectDate);
            java.sql.Date sqlDate = new java.sql.Date(dateParser.getTime());

            attendances = projectAttendanceDao.findConfirmedVolunteersForProjectByDate(projectId, sqlDate);
        }
        List<ProjectGateListModel> models = projectGateListModelFactory.generateModels(attendances);
        return new ResponseEntity<Object>(models, HttpStatus.OK);
    }

    /**
     * Gets a summary for gate list for a project for a particular day.
     *
     * @param projectId the project id
     * @param projectDate the date
     * @return response entity with data
     * @throws ParseException if cannot convert to a valid date
     */
    @RequestMapping(value = "/project-gatelist/{projectId}/date/{projectDate}/summary", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public ResponseEntity<Object> getGateListSummaryByDate(@PathVariable Integer projectId, @PathVariable String projectDate)
            throws ParseException {
        Map<String, String> summary = new HashMap<>();
        FastDateFormat format = FastDateFormat.getInstance("dd-MM-yyyy");
        java.util.Date dateParser = format.parse(projectDate);
        java.sql.Date sqlDate = new java.sql.Date(dateParser.getTime());

        List<ProjectAttendance> attendances = projectAttendanceDao.findConfirmedVolunteersForProjectByDate(projectId, sqlDate);
        List<ProjectAttendance> availables = projectAttendanceDao.findAllAvailableVolunteersForProjectByDate(projectId, sqlDate);
        summary.put("date", projectDate);
        summary.put("invited", Integer.toString(attendances.size()));
        summary.put("available", Integer.toString(availables.size()));
        return new ResponseEntity<Object>(summary, HttpStatus.OK);
    }

    private List<String> generateDateRange(DateTime startDate, DateTime endDate) {
        List<String> range = new ArrayList<>();
        DateTime tmpdate;
        tmpdate = startDate;
        FastDateFormat formatter = FastDateFormat.getInstance("dd-MM-yyyy");
        while (!tmpdate.isAfter(endDate)) {
            range.add(formatter.format(tmpdate.toDate()));
            tmpdate = tmpdate.plusDays(1);
        }
        return range;
    }

    private Integer findUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ROMSUserDetails user = (ROMSUserDetails) authentication.getPrincipal();
        return user.getUserId();
    }

    /**
     * Handles exceptions that result in bad request.
     *
     * @param e the exception to handle
     */
    @ExceptionHandler({BadRequestException.class, ParseException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleExceptions(Exception e) {
    }
}
