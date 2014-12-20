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

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.project.ProjectAttendance;
import uk.org.rbc1b.roms.db.project.ProjectAttendanceDao;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSessionDao;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * Generate the email to be send to the volunteer requesting they confirm when they are available.
 */
@Component
public class ProjectAvailabilityEmailGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectAvailabilityEmailGenerator.class);
    private static final String BASE_URI = "/project-availability";
    private static final String DATETIME_FORMAT = "yyyyMMddHHmm";
    private static final String SECURITY_SALT = "security.salt";
    private static final String SERVER_URL = "edifice.url";
    private static final String AVAILABILITY_SUBJECT = "Availability for Project Request";
    private static final String AVAILABILITY_TEMPLATE = "project-availability.ftl";
    private static final String CONFIRMATION_SUBJECT = "Project Attendance Confirmation Dates";
    private static final String CONFIRMATION_TEMPLATE = "project-attendance-confirmation.ftl";

    @Autowired
    private KingdomHallDao kingdomHallDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ProjectDepartmentSessionDao projectDepartmentSessionDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectAttendanceDao projectAttendanceDao;
    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Generate the email to be sent to the volunteer when asking for their availability.
     * @param projectAvailability availability
     * @return email body text
     * @throws IOException on failure to process the freemarker template
     * @throws TemplateException on failure to process the freemarker template
     */
    public Email generateVolunteerAvailabilityRequestEmail(ProjectAvailability projectAvailability) throws IOException,
            TemplateException {

        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, Object> model = new HashMap<>();

        Person person = personDao.findPerson(projectAvailability.getPerson().getPersonId());
        populatePersonToModel(model, person);
        model.put("httpsurl", generateSecureAvailabilityUrl(person, projectAvailability));

        ProjectDepartmentSession projectSession = projectDepartmentSessionDao
                .findByProjectDepartmentSessionId(projectAvailability.getProjectDepartmentSession()
                        .getProjectDepartmentSessionId());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        model.put("fromDate", dateFormat.format(projectSession.getFromDate()));
        model.put("toDate", dateFormat.format(projectSession.getToDate()));
        Department department = departmentDao.findDepartment(projectSession.getDepartment().getDepartmentId());
        model.put("department", department.getName());

        Project project = projectDao.findProject(projectSession.getProject().getProjectId());
        populateProjectToModel(model, project);

        Email email = new Email();
        email.setRecipient(person.getEmail());
        email.setSubject(AVAILABILITY_SUBJECT);
        email.setText(FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(AVAILABILITY_TEMPLATE), model));

        return email;
    }

    /**
     * Generate the email used to tell the volunteer which of their dates they are invited to.
     * @param projectAvailability project availbility
     * @return email
     * @throws IOException on failure to process the freemarker template
     * @throws TemplateException on failure to process the freemarker template
     */
    public Email generateVolunteerAvailabilityConfirmationEmail(ProjectAvailability projectAvailability)
            throws IOException, TemplateException {
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, Object> model = new HashMap<>();

        List<ProjectAttendance> projectAttendances = projectAttendanceDao.getConfirmedDates(projectAvailability);
        if (projectAttendances.isEmpty()) {
            return null;
        }

        List<String> dates = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        for (ProjectAttendance projectAttendance : projectAttendances) {
            dates.add(dateFormat.format(projectAttendance.getAvailableDate()));
        }
        model.put("dates", dates);

        Person person = personDao.findPerson(projectAvailability.getPerson().getPersonId());
        populatePersonToModel(model, person);
        model.put("httpsurl", getSecureConfirmedDatesUrl(person, projectAvailability));

        ProjectDepartmentSession projectSession = projectDepartmentSessionDao
                .findByProjectDepartmentSessionId(projectAvailability.getProjectDepartmentSession()
                        .getProjectDepartmentSessionId());
        Department department = departmentDao.findDepartment(projectSession.getDepartment().getDepartmentId());
        model.put("department", department.getName());
        model.put("projectsession", projectSession.getProjectDepartmentSessionId());

        Project project = projectDao.findProject(projectSession.getProject().getProjectId());
        populateProjectToModel(model, project);

        Email email = new Email();
        email.setRecipient(person.getEmail());
        email.setSubject(CONFIRMATION_SUBJECT);
        email.setText(FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(CONFIRMATION_TEMPLATE), model));

        return email;
    }

    private void populatePersonToModel(Map<String, Object> model, Person person) {
        model.put("forename", person.getForename());
        model.put("email", person.getEmail());
    }

    private void populateProjectToModel(Map<String, Object> model, Project project) {
        project.setKingdomHall(kingdomHallDao.findKingdomHall(project.getKingdomHall().getKingdomHallId()));
        model.put("project", project.getName());
        model.put("kingdomhall", project.getKingdomHall().getName());
        model.put("street", project.getKingdomHall().getAddress().getStreet());
        model.put("town", project.getKingdomHall().getAddress().getTown());
        model.put("county", project.getKingdomHall().getAddress().getCounty());
        model.put("postcode", project.getKingdomHall().getAddress().getPostcode());
    }

    /**
     * Generates the url with the following format:
     * https://edifice/project-availability/{projectAvailabilityId}/{datetime}/{token}/update
     *
     * @param person
     * @param projectAvailability
     * @return url
     */
    private String generateSecureAvailabilityUrl(Person person, ProjectAvailability projectAvailability) {
        List<String> url = new ArrayList<String>();
        url.add(edificeProperty.getProperty(SERVER_URL));
        url.add(BASE_URI);
        url.add(Integer.toString(projectAvailability.getProjectAvailabilityId()));
        String datetime = getCurrentDateTime();
        url.add(datetime);
        url.add(generateSecureToken(person, projectAvailability, datetime));

        return StringUtils.join(url, "/");
    }

    private String getSecureConfirmedDatesUrl(Person person, ProjectAvailability projectAvailability) {
        StringBuilder builder = new StringBuilder();
        builder.append(edificeProperty.getProperty(SERVER_URL));
        builder.append(BASE_URI).append("/").append(projectAvailability.getProjectAvailabilityId()).append("/");
        String datetime = getCurrentDateTime();
        builder.append(datetime).append("/");
        builder.append(generateSecureToken(person, projectAvailability, datetime)).append("/confirmed");
        return builder.toString();
    }

    private String getCurrentDateTime() {
        DateTime datetime = new DateTime();
        return datetime.toString(DATETIME_FORMAT);
    }

    private String generateSecureToken(Person person, ProjectAvailability projectAvailability, String datetime) {
        String salt = edificeProperty.getProperty(SECURITY_SALT);
        if (salt == null || salt.isEmpty()) {
            salt = "er9bhmbsaa5ppdnoQP";
            LOGGER.error("JNDI property for security salt is not set - will use default.");
        }
        String text = datetime + ":" + person.getPersonId() + ":" + projectAvailability.getProjectAvailabilityId();
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        return encoder.encodePassword(salt, text);
    }
}
