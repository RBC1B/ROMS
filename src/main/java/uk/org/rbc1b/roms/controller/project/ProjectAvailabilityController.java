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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.org.rbc1b.roms.controller.ForbiddenRequestException;
import uk.org.rbc1b.roms.controller.ResourceNotFoundException;
import uk.org.rbc1b.roms.controller.common.HashGenerator;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.project.ProjectAttendance;
import uk.org.rbc1b.roms.db.project.ProjectAttendanceDao;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectAvailabilityDao;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSessionDao;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;

/**
 *
 * @author ramindursingh
 */
@Controller
@RequestMapping("/project-availability")
public class ProjectAvailabilityController {
    private static final String SECURITY_SALT = "security.salt";
    private static final String DATETIMEFORMAT = "yyyyMMddHHmm";
    private static final FastDateFormat DATE_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final long MAXTIME = 1209600000; // Two weeks
    private static final long DAY = 24 * 60 * 60 * 1000;
    private static final String ACCOMMODATION = "accommodationRequired";
    private static final String TRANSPORTRQD = "transportRequired";
    private static final String TRANSPORTOFR = "offerTransport";
    @Autowired
    private ProjectAvailabilityDao projectAvailabilityDao;
    @Autowired
    private ProjectDepartmentSessionDao projectDepartmentSessionDao;
    @Autowired
    private ProjectAttendanceDao projectAttendanceDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private KingdomHallDao kingdomHallDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private VolunteerForProjectModelFactory volunteerForProjectModelFactory;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Controller to handle the initial time the volunteer clicks on the link.
     * We are trying to be a bit kinder to the user if something goes wrong, so
     * do not return the standard 404 or 403 errors if something goes wrong - go to a specific error page
     * @param projectAvailabilityId the availability id
     * @param datetime the date time when the original email was sent
     * @param hash the security token
     * @param model the mvc model
     * @return the jsp page to show
     */
    @RequestMapping(value = "/{projectAvailabilityId}/{datetime}/{hash}", method = RequestMethod.GET)
    public String showVolunteerAvailabilityPage(@PathVariable Integer projectAvailabilityId,
            @PathVariable String datetime, @PathVariable String hash, ModelMap model) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability != null && isValidHash(projectAvailability, datetime, hash) && isNotExpired(datetime)) {
            projectAvailability.setPersonResponded(true);
            projectAvailabilityDao.update(projectAvailability);

            AvailabilityForm modelForm = new AvailabilityForm();
            populateModel(modelForm, projectAvailability);
            modelForm.setDatetime(datetime);
            modelForm.setHash(hash);

            model.addAttribute("availabilityModel", modelForm);
            return "project-availability/availability-update";
        }
        return "project-availability/availability-did-not-match";
    }

    /**
     * Handles POST Method.
     * Unlike the get, we can use the standard exceptions here since they will have already seen the page.
     * @param projectAvailabilityId the availability request
     * @param datetime when email sent out
     * @param hash security token
     * @param date the date to add to the database
     */
    @RequestMapping(value = "/{projectAvailabilityId}/{datetime}/{hash}/{date}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void availableDatesPosted(@PathVariable Integer projectAvailabilityId, @PathVariable String datetime,
            @PathVariable String hash, @PathVariable String date) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            throw new ResourceNotFoundException("No ProjectAvailability#" + projectAvailabilityId);
        }

        verifyHashAndStartTime(projectAvailability, datetime, hash);

        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        ProjectAttendance attendance = projectAttendanceDao.getAvailableDate(projectAvailability, sqlDate);
        if (attendance == null) {
            attendance = new ProjectAttendance();
            attendance.setProjectAvailability(projectAvailability);
            attendance.setAvailableDate(sqlDate);
            attendance.setUpdatedBy(projectAvailability.getPerson().getPersonId());
            projectAttendanceDao.save(attendance);
        }
    }

    /**
     * Handles DELETE Method.
     *
     * @param projectAvailabilityId the availability request
     * @param datetime when email sent out
     * @param hash security token
     * @param date the date to delete to the database
     */
    @RequestMapping(value = "/{projectAvailabilityId}/{datetime}/{hash}/{date}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void availableDatesDeleted(@PathVariable Integer projectAvailabilityId, @PathVariable String datetime,
            @PathVariable String hash, @PathVariable String date) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            throw new ResourceNotFoundException("No ProjectAvailability#" + projectAvailabilityId);
        }

        verifyHashAndStartTime(projectAvailability, datetime, hash);

        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        ProjectAttendance attendance = projectAttendanceDao.getAvailableDate(projectAvailability, sqlDate);
        if (attendance != null) {
            projectAttendanceDao.delete(attendance);
        }
    }

    /**
     * Handles PUT Method.
     *
     * @param projectAvailabilityId the availability request
     * @param datetime when email sent out
     * @param hash security token
     * @param requirement the requirement to update
     */
    @RequestMapping(value = "/{projectAvailabilityId}/{datetime}/{hash}/{requirement}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRequirements(@PathVariable Integer projectAvailabilityId, @PathVariable String datetime,
            @PathVariable String hash, @PathVariable String requirement) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            throw new ResourceNotFoundException("No ProjectAvailability#" + projectAvailabilityId);
        }

        verifyHashAndStartTime(projectAvailability, datetime, hash);

        // toggle the requirement value
        switch (requirement) {
        case ACCOMMODATION:
            projectAvailability.setAccommodationRequired(!projectAvailability.isAccommodationRequired());
            break;
        case TRANSPORTRQD:
            projectAvailability.setTransportRequired(!projectAvailability.isTransportRequired());
            break;
        case TRANSPORTOFR:
            projectAvailability.setOfferTransport(!projectAvailability.isOfferTransport());
            break;
        default:
            throw new ResourceNotFoundException("No ProjectAvailability requirement [" + requirement + "] exists");
        }

        projectAvailability.setUpdatedBy(projectAvailability.getPerson().getPersonId());
        projectAvailabilityDao.update(projectAvailability);
    }

    /**
     * Returns the Availability record for a volunteer. Extra parameters are
     * required for extra security.
     *
     * @param projectAvailabilityId the availability id
     * @param datetime the datetime
     * @param hash the security token
     * @return responseEntity set with appropriate JSON data
     */
    @RequestMapping(value = "/{projectAvailabilityId}/{datetime}/{hash}/availability", method = RequestMethod.GET)
    @ResponseBody
    public ProjectAvailabilityModel getVolunteerAvailability(@PathVariable Integer projectAvailabilityId,
            @PathVariable String datetime, @PathVariable String hash) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            throw new ResourceNotFoundException("No ProjectAvailability#" + projectAvailabilityId);
        }

        verifyHashAndStartTime(projectAvailability, datetime, hash);

        return volunteerForProjectModelFactory.generate(projectAvailability);
    }

    /**
     * Returns the dates that the volunteer may have already entered.
     *
     * @param projectAvailabilityId the availability id
     * @param datetime the date time
     * @param hash the security token
     * @return responseEntity with status
     */
    @RequestMapping(value = "/{projectAvailabilityId}/{datetime}/{hash}/attendance", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Boolean> getVolunteerDates(@PathVariable Integer projectAvailabilityId,
            @PathVariable String datetime, @PathVariable String hash) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            throw new ResourceNotFoundException("No ProjectAvailability#" + projectAvailabilityId);
        }

        verifyHashAndStartTime(projectAvailability, datetime, hash);

        List<ProjectAttendance> availableDates = projectAttendanceDao.getDatesForVolunteer(projectAvailability);
        Map<String, Boolean> dates = new HashMap<String, Boolean>();

        for (ProjectAttendance availableDate : availableDates) {
            dates.put(DATE_FORMATTER.format(availableDate.getAvailableDate()), availableDate.isRequired());
        }
        return dates;
    }

    private void populateModel(AvailabilityForm model, ProjectAvailability availability) {
        ProjectDepartmentSession session = projectDepartmentSessionDao.findByProjectDepartmentSessionId(availability
                .getProjectDepartmentSession().getProjectDepartmentSessionId());
        model.setProjectAvailabilityId(availability.getProjectAvailabilityId());
        model.setProjectDepartmentSessionId(session.getProjectDepartmentSessionId());
        model.setWorkingSunday(session.isSunday());

        Project project = projectDao.findProject(session.getProject().getProjectId());
        model.setProjectName(project.getName());

        KingdomHall kingdomhall = kingdomHallDao.findKingdomHall(project.getKingdomHall().getKingdomHallId());
        String address = kingdomhall.getAddress().getStreet() + ", " + kingdomhall.getAddress().getTown() + ", "
                + kingdomhall.getAddress().getCounty() + ", " + kingdomhall.getAddress().getPostcode() + ".";
        model.setAddress(address);

        Department dept = departmentDao.findDepartment(session.getDepartment().getDepartmentId());
        model.setDepartmentName(dept.getName());

        model.setToDate(DATE_FORMATTER.format(session.getToDate()));
        model.setFromDate(DATE_FORMATTER.format(session.getFromDate()));

        // We add extra date to include end date for FullCalendar as the end date
        // is exclusive.
        java.sql.Date endDate = new java.sql.Date(session.getToDate().getTime() + DAY);
        model.setEndDate(DATE_FORMATTER.format(endDate));

        Person person = personDao.findPerson(availability.getPerson().getPersonId());
        model.setVolunteer(person.getSurname() + ", " + person.getForename());
    }

    private void verifyHashAndStartTime(ProjectAvailability projectAvailability, String datetime, String hash) {
        if (!isValidHash(projectAvailability, datetime, hash)) {
            throw new ForbiddenRequestException("Hash is invalid");
        }
        if (!isNotExpired(datetime)) {
            throw new ForbiddenRequestException("Link has expired");
        }
    }

    private boolean isNotExpired(String originaltime) {
        DateTime now = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATETIMEFORMAT);
        DateTime then = formatter.parseDateTime(originaltime);
        long difference = now.getMillis() - then.getMillis();
        return MAXTIME > difference;
    }

    private boolean isValidHash(ProjectAvailability projectAvailability, String datetime, String hash) {
        String value = datetime + ":" + projectAvailability.getPerson().getPersonId() + ":"
                + projectAvailability.getProjectAvailabilityId();
        String generatedHash = HashGenerator.generateHash(value, edificeProperty.getProperty(SECURITY_SALT));

        return generatedHash.equalsIgnoreCase(hash);
    }
}
