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
package uk.org.rbc1b.roms.scheduled;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.joda.time.DateTime;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
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
 * Checks PersonAvailability table to see if there is anyone who needs to be
 * send an email.
 */
@Component
public class ProjectAvailabilityEmailScheduledService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectAvailabilityEmailScheduledService.class);
    private static final String BASE_URI = "/project-availability";
    private static final String AVAILABILITY_SUBJECT = "Project Availability Request";
    private static final String CONFIRMATION_SUBJECT = "Project Confirmation Dates";
    private static final String SECURITY_SALT = "security.salt";
    private static final String SERVER_URL = "edifice.url";
    private static final String DATETIMEFORMAT = "yyyyMMddHHmm";
    private static final String AVAILABILITY_TEMPLATE = "project-availability.ftl";
    private static final String CONFIRMATION_TEMPLATE = "project-attendance-confirmation.ftl";
    @Autowired
    private PersonDao personDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private KingdomHallDao kingdomHallDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ProjectDepartmentSessionDao projectDepartmentSessionDao;
    @Autowired
    private ProjectAvailabilityDao projectAvailabilityDao;
    @Autowired
    private ProjectAttendanceDao projectAttendanceDao;
    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;
    @Autowired
    private UserDetailsService userDetailsService;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Checks volunteers who have not been sent an email.
     */
    // For testing
    @Scheduled(cron = "0 0/5 * * * ?")
    //@Scheduled(cron = "0 15,45 * * * ?")
    public void checkAvailability() {
        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<ProjectAvailability> projectAvailabilityList = projectAvailabilityDao.findUnnotifiedVolunteers();
        if (projectAvailabilityList.isEmpty()) {
            return;
        }
        for (ProjectAvailability projectAvailability : projectAvailabilityList) {
            try {
                createEmailForVolunteers(projectAvailability);
                projectAvailability.setEmailSent(true);
                projectAvailabilityDao.update(projectAvailability);
            } catch (IOException e) {
                LOGGER.error("Failed to send Availability email:", e);
            } catch (TemplateException e) {
                LOGGER.error("Failed to generate Availability email", e);
            }
        }
    }

    /**
     * Checks volunteers who have been need to sent the confirmed dates.
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    //@Scheduled(cron = "0 10,40 * * * ?")
    public void checkConfirmationDates() {
        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<ProjectAvailability> projectAvailabilityList = projectAvailabilityDao.findUnconfirmedVolunteers();
        if (projectAvailabilityList.isEmpty()) {
            return;
        }
        for (ProjectAvailability projectAvailability : projectAvailabilityList) {
            try {
                createConfirmationEmailForVolunteer(projectAvailability);
                projectAvailability.setConfirmationEmail(true);
                projectAvailabilityDao.update(projectAvailability);
            } catch (IOException e) {
                LOGGER.error("Failed to send Confirmation email:", e);
            } catch (TemplateException e) {
                LOGGER.error("Failed to generate Confirmation email", e);
            }
        }
    }

    private void createConfirmationEmailForVolunteer(ProjectAvailability projectAvailability)
            throws IOException, TemplateException {
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map model = new HashMap();

        List<ProjectAttendance> projectAttendances = projectAttendanceDao.getConfirmedDates(projectAvailability);
        if (projectAttendances.isEmpty()) {
            return;
        }

        List<String> dates = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String date;
        for (ProjectAttendance projectAttendance : projectAttendances) {
            date = dateFormat.format(projectAttendance.getAvailableDate());
            dates.add(date);
        }
        model.put("dates", dates);

        Person person = personDao.findPerson(projectAvailability.getPerson().getPersonId());
        populatePersonToModel(model, person);
        model.put("httpsurl", getSecureConfirmedDatesUrl(person, projectAvailability));

        ProjectDepartmentSession projectSession = projectDepartmentSessionDao.
                findByProjectDepartmentSessionId(projectAvailability.
                getProjectDepartmentSession().getProjectDepartmentSessionId());
        Department department = departmentDao.findDepartment(projectSession.getDeparment().getDepartmentId());
        model.put("department", department.getName());
        model.put("projectsession", projectSession.getProjectDepartmentSessionId());

        Project project = projectDao.findProject(projectSession.getProject().getProjectId());
        populateProjectToModel(model, project);

        String text = FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(CONFIRMATION_TEMPLATE), model);
        Email email = new Email();
        email.setRecipient(person.getEmail());
        email.setSubject(CONFIRMATION_SUBJECT);
        email.setText(text);
        emailDao.save(email);
    }

    private void createEmailForVolunteers(ProjectAvailability projectAvailability)
            throws IOException, TemplateException {
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, String> model = new HashMap<String, String>();

        Person person = personDao.findPerson(projectAvailability.getPerson().getPersonId());
        populatePersonToModel(model, person);
        model.put("httpsurl", getSecureAvailabilityUrl(person, projectAvailability));

        ProjectDepartmentSession projectSession = projectDepartmentSessionDao.
                findByProjectDepartmentSessionId(projectAvailability.
                getProjectDepartmentSession().getProjectDepartmentSessionId());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        model.put("fromDate", dateFormat.format(projectSession.getFromDate()));
        model.put("toDate", dateFormat.format(projectSession.getToDate()));
        Department department = departmentDao.findDepartment(projectSession.getDeparment().getDepartmentId());
        model.put("department", department.getName());

        Project project = projectDao.findProject(projectSession.getProject().getProjectId());
        populateProjectToModel(model, project);

        String text = FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(AVAILABILITY_TEMPLATE), model);
        Email email = new Email();
        email.setRecipient(person.getEmail());
        email.setSubject(AVAILABILITY_SUBJECT);
        email.setText(text);
        emailDao.save(email);
    }

    private void populatePersonToModel(Map<String, String> model, Person person) {
        model.put("forename", person.getForename());
        model.put("email", person.getEmail());
    }

    private void populateProjectToModel(Map<String, String> model, Project project) {
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
     * https://edifice/project-availability/{personId}/{projectAvailabilityId}/{datetime}/{token}/update
     *
     * @param person
     * @param projectAvailability
     * @return url
     */
    private String getSecureAvailabilityUrl(Person person, ProjectAvailability projectAvailability) {
        String url = edificeProperty.getProperty(SERVER_URL);

        url = url + BASE_URI + "/" + person.getPersonId() + "/" + projectAvailability.getProjectAvailabilityId() + "/";
        String datetime = getCurrentDateTime();
        url = url + datetime + "/";
        url = url + getSecureToken(person, projectAvailability, datetime) + "/update";

        return url;
    }

    private String getSecureConfirmedDatesUrl(Person person, ProjectAvailability projectAvailability) {
        String url = edificeProperty.getProperty(SERVER_URL);
        url = url + BASE_URI + "/" + person.getPersonId() + "/" + projectAvailability.getProjectAvailabilityId() + "/";
        String datetime = getCurrentDateTime();
        url = url + datetime + "/";
        url = url + getSecureToken(person, projectAvailability, datetime) + "/confirmed";
        return url;
    }

    private String getCurrentDateTime() {
        DateTime datetime = new DateTime();
        return datetime.toString(DATETIMEFORMAT);
    }

    private String getSecureToken(Person person, ProjectAvailability projectAvailability, String datetime) {
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
