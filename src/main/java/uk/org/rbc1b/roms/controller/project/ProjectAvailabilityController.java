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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectAvailabilityController.class);
    private static final String SECURITY_SALT = "security.salt";
    private static final String DATETIMEFORMAT = "yyyyMMddHHmm";
    private static final String DATEFORMAT = "yyyy-MM-dd";
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
     *
     * @param personId the person Id
     * @param projectAvailabilityId the availability id
     * @param datetime the date time when the original email was sent
     * @param hash the security token
     * @param model the mvc model
     * @return the jsp page to show
     */
    @RequestMapping(value = "/{personId}/{projectAvailabilityId}/{datetime}/{hash}", method = RequestMethod.GET)
    public String showVolunteerAvailabilityPage(@PathVariable Integer personId,
            @PathVariable Integer projectAvailabilityId, @PathVariable String datetime, @PathVariable String hash,
            ModelMap model) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            return "project-availability/availability-did-not-match";
        }
        if (checkIfValidProjectAvailability(personId, projectAvailability)
                && checkHash(projectAvailability, datetime, hash) && checkIfWithinTime(datetime)) {
            projectAvailability.setPersonResponded(true);
            projectAvailabilityDao.update(projectAvailability);

            AvailabilityForm modelForm = new AvailabilityForm();
            populateModel(modelForm, projectAvailability);
            modelForm.setDatetime(datetime);
            modelForm.setHash(hash);

            model.addAttribute("availabilityModel", modelForm);
            return "project-availability/availability-update";
        } else {
            return "project-availability/availability-did-not-match";
        }
    }

    /**
     * Handles POST Method.
     *
     * @param response the http response
     * @param personId the person id
     * @param projectAvailabilityId the availability request
     * @param datetime when email sent out
     * @param hash security token
     * @param date the date to add to the database
     */
    @RequestMapping(value = "/{personId}/{projectAvailabilityId}/{datetime}/{hash}/{date}", method = RequestMethod.POST)
    public void availableDatesPosted(HttpServletResponse response, @PathVariable Integer personId,
            @PathVariable Integer projectAvailabilityId, @PathVariable String datetime, @PathVariable String hash,
            @PathVariable String date) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability != null && checkIfValidProjectAvailability(personId, projectAvailability)
                && checkHash(projectAvailability, datetime, hash) && checkIfWithinTime(datetime)) {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

            ProjectAttendance attendance = projectAttendanceDao.getAvailableDate(projectAvailability, sqlDate);
            if (attendance == null) {
                attendance = new ProjectAttendance();
                attendance.setProjectAvailability(projectAvailability);
                attendance.setAvailableDate(sqlDate);
                attendance.setUpdatedBy(personId);
                attendance.setUpdateTime(now);
                projectAttendanceDao.save(attendance);
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            LOGGER.error("Error - something did not check out for " + date);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }

    /**
     * Handles DELETE Method.
     *
     * @param response the http response
     * @param personId the person id
     * @param projectAvailabilityId the availability request
     * @param datetime when email sent out
     * @param hash security token
     * @param date the date to delete to the database
     */
    @RequestMapping(value = "/{personId}/{projectAvailabilityId}/{datetime}/{hash}/{date}", method = RequestMethod.DELETE)
    public void availableDatesDeleted(HttpServletResponse response, @PathVariable Integer personId,
            @PathVariable Integer projectAvailabilityId, @PathVariable String datetime, @PathVariable String hash,
            @PathVariable String date) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability != null && checkIfValidProjectAvailability(personId, projectAvailability)
                && checkHash(projectAvailability, datetime, hash) && checkIfWithinTime(datetime)) {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);

            ProjectAttendance attendance = projectAttendanceDao.getAvailableDate(projectAvailability, sqlDate);
            if (attendance != null) {
                projectAttendanceDao.delete(attendance);
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            LOGGER.error("Error - something did not check out for " + date);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }

    /**
     * Handles PUT Method.
     *
     * @param response the http response
     * @param personId the person id
     * @param projectAvailabilityId the availability request
     * @param datetime when email sent out
     * @param hash security token
     * @param requirement the requirement to update
     */
    @RequestMapping(value = "/{personId}/{projectAvailabilityId}/{datetime}/{hash}/{requirement}", method = RequestMethod.PUT)
    public void updateRequirements(HttpServletResponse response, @PathVariable Integer personId,
            @PathVariable Integer projectAvailabilityId, @PathVariable String datetime, @PathVariable String hash,
            @PathVariable String requirement) {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability != null && checkIfValidProjectAvailability(personId, projectAvailability)
                && checkHash(projectAvailability, datetime, hash) && checkIfWithinTime(datetime)) {

            boolean validRequest = false;
            if (requirement.equalsIgnoreCase(ACCOMMODATION)) {
                validRequest = true;
                if (projectAvailability.isAccommodationRequired()) {
                    projectAvailability.setAccommodationRequired(false);
                } else {
                    projectAvailability.setAccommodationRequired(true);
                }
            } else if (requirement.equalsIgnoreCase(TRANSPORTRQD)) {
                validRequest = true;
                if (projectAvailability.isTransportRequired()) {
                    projectAvailability.setTransportRequired(false);
                } else {
                    projectAvailability.setTransportRequired(true);
                }
            } else if (requirement.equalsIgnoreCase(TRANSPORTOFR)) {
                validRequest = true;
                if (projectAvailability.isOfferTransport()) {
                    projectAvailability.setOfferTransport(false);
                } else {
                    projectAvailability.setOfferTransport(true);
                }
            } else {
                LOGGER.error("Unrecognised requirement update request: " + requirement);
            }
            if (validRequest) {
                projectAvailability.setUpdatedBy(personId);
                projectAvailability.setUpdateTime(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                projectAvailabilityDao.update(projectAvailability);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            LOGGER.error("Error - something did not check out for " + requirement);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }

    /**
     * Returns the Availability record for a volunteer. Extra parameters are
     * required for extra security.
     *
     * @param personId the volunteer id
     * @param projectAvailabilityId the availability id
     * @param datetime the datetime
     * @param hash the security token
     * @return responseEntity set with appropriate JSON data
     */
    @RequestMapping(value = "/{personId}/{projectAvailabilityId}/{datetime}/{hash}/availability", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getVolunteerAvailability(@PathVariable Integer personId,
            @PathVariable Integer projectAvailabilityId, @PathVariable String datetime, @PathVariable String hash) {
        ProjectAvailability availability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (availability == null && checkIfValidProjectAvailability(personId, availability)
                && checkHash(availability, datetime, hash) && checkIfWithinTime(datetime)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Object>(volunteerForProjectModelFactory.generate(availability), HttpStatus.OK);
        }
    }

    /**
     * Returns the dates that the volunteer may have already entered.
     *
     * @param personId the person id
     * @param projectAvailabilityId the availability id
     * @param datetime the date time
     * @param hash the security token
     * @return responseEntity with status
     */
    @RequestMapping(value = "/{personId}/{projectAvailabilityId}/{datetime}/{hash}/attendance", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getVolunteerDates(@PathVariable Integer personId,
            @PathVariable Integer projectAvailabilityId, @PathVariable String datetime, @PathVariable String hash) {
        ProjectAvailability availability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (availability == null && checkIfValidProjectAvailability(personId, availability)
                && checkHash(availability, datetime, hash) && checkIfWithinTime(datetime)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            List<ProjectAttendance> availableDates = projectAttendanceDao.getDatesForVolunteer(availability);
            if (availableDates == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                Map<String, Boolean> dates = new HashMap<String, Boolean>();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                for (ProjectAttendance availableDate : availableDates) {
                    dates.put(formatter.format(availableDate.getAvailableDate()), availableDate.isRequired());
                    LOGGER.error("Date: " + formatter.format(availableDate.getAvailableDate()) + ", required="
                            + availableDate.isRequired());
                }
                return new ResponseEntity<Object>(dates, HttpStatus.OK);
            }
        }
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

        DateFormat dateFormat = new SimpleDateFormat(DATEFORMAT, Locale.UK);
        model.setToDate(dateFormat.format(session.getToDate()));
        model.setFromDate(dateFormat.format(session.getFromDate()));

        // We add extra date to include end date for FullCalendar as the end date
        // is exclusive.
        java.sql.Date endDate = new java.sql.Date(session.getToDate().getTime() + DAY);
        model.setEndDate(dateFormat.format(endDate));

        Person person = personDao.findPerson(availability.getPerson().getPersonId());
        model.setVolunteer(person.getSurname() + ", " + person.getForename());
    }

    private boolean checkIfValidProjectAvailability(Integer personId, ProjectAvailability projectAvailability) {
        if (projectAvailability.getPerson().getPersonId() != personId) {
            return false;
        }
        return true;
    }

    private boolean checkIfWithinTime(String originaltime) {
        DateTime now = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATETIMEFORMAT);
        DateTime then = formatter.parseDateTime(originaltime);
        long difference = now.getMillis() - then.getMillis();
        return MAXTIME > difference;
    }

    private boolean checkHash(ProjectAvailability projectAvailability, String datetime, String hash) {
        String salt = edificeProperty.getProperty(SECURITY_SALT);
        if (salt == null || salt.isEmpty()) {
            salt = "er9bhmbsaa5ppdnoQP";
            LOGGER.error("JNDI property for security salt is not set - will use default.");
        }
        String text = datetime + ":" + projectAvailability.getPerson().getPersonId() + ":"
                + projectAvailability.getProjectAvailabilityId();
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String workedHash = encoder.encodePassword(salt, text);
        return workedHash.equalsIgnoreCase(hash);
    }
}
