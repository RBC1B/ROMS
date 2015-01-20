/*
 * The MIT License
 *
 * Copyright 2015 RBC1B.
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
package uk.org.rbc1b.roms.controller.volunteer.contactdetails;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.controller.common.HashGenerator;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.volunteer.AssignmentModel;
import uk.org.rbc1b.roms.controller.volunteer.AssignmentModelFactory;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerModelFactory;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerQualificationModel;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerSkillModel;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.qualification.VolunteerQualification;
import uk.org.rbc1b.roms.db.volunteer.skill.VolunteerSkill;

/**
 * Unit test for {@code VolunteerContactDetailsEmailGenerator} the SUT.
 * We follow the pattern: arrange; act; assert/verify.
 *
 * @author rahulsingh
 */
@RunWith(MockitoJUnitRunner.class)
public class VolunteerContactDetailsEmailGeneratorTest {
    private static final String DIRECTORY_FOR_EMAIL_TEMPLATES = "src/main/webapp/WEB-INF/emails";
    private static final String BIANNUAL_CONTACT_DETAILS_TEMPLATE = "volunteer-biannual-contact-details.ftl";

    private static final Integer MOCK_PERSON_ID = 5;
    private static final String MOCK_PERSON_FORENAME = "Ramindur";
    private static final String MOCK_PERSON_SURNAME = "Singh";
    private static final String MOCK_PERSON_EMAIL = "rhsingh@gmail.com";
    private static final String MOCK_VOLUNTEER_GENDER = "M";
    private static final String MOCK_PERSON_TELEPHONE = "020 8777 0000";
    private static final String MOCK_PERSON_MOBILE = "07777 123 111";
    private static final String MOCK_PERSON_WORKPHONE = "09999 123444";
    private static final String MOCK_ADDRESS_STREET = "10 The Road";
    private static final String MOCK_ADDRESS_TOWN = "Tiny Town";
    private static final String MOCK_ADDRESS_COUNTY = "Middlesex";
    private static final String MOCK_ADDRESS_POSTCODE = "NW5 3RE";

    private static final String MOCK_DEPARTMENT_NAME = "Sound";
    private static final String MOCK_ASSIGNMENT_ROLE = "Overseer";
    private static final String MOCK_ASSIGNMENT_TRADE_NUMBER = "1st Trade";

    private static final String MOCK_QUALIFICATION_NAME = "Diploma in Computing";

    private static final String MOCK_SKILL_NAME = "Juggling";

    private static final String MOCK_CONGREGATION_NAME = "London, Hounslow";

    private static final String SERVER_URL = "edifice.url";
    private static final String MOCK_SERVER_URL = "https://edifice.org.uk";
    private static final String SECURITY_SALT = "security.salt";
    private static final String MOCK_SECURITY_SALT = "0Abser23";
    private static final String MOCK_CONFIRMATION_URI_PARTIAL = "/volunteer-contact-details-confirmation/" + MOCK_PERSON_ID + "/";

    @InjectMocks
    private VolunteerContactDetailsEmailGenerator volunteerContactDetailsEmailGenerator;
    @Mock
    private VolunteerDao mockVolunteerDao;
    @Mock
    private AssignmentModelFactory mockAssignmentModelFactory;
    @Mock
    private VolunteerModelFactory mockVolunteerModelFactory;
    @Mock
    private Volunteer mockVolunteerOne;
    @Mock
    private Person mockPerson;
    @Mock
    private Address mockAddress;
    @Mock
    private Congregation mockCongregation;
    @Mock
    private Assignment mockAssignment;
    @Mock
    private Properties mockProperties;
    @Mock
    private FreeMarkerConfigurer mockFreeMarkerConfigurer;
    @Mock
    private Configuration mockConfiguration;
    private List<Assignment> volunteerAssignments;
    private List<VolunteerQualification> volunteerQualifications;
    private List<VolunteerSkill> volunteerSkills;
    private AssignmentModel assignmentModel;
    private List<VolunteerQualificationModel> volunteerQualificationModelList;
    private List<VolunteerSkillModel> volunteerSkillModelList;
    private Configuration configuration;
    private Template template;

    /**
     * Set up mock method invocations and any configurations.
     *
     * @throws IOException if error occurs with Free marker configuration
     * @throws TemplateException if error occurs with Free marker template
     */
    @Before
    public void setUp() throws IOException, TemplateException {
        // inject mocks and initialise
        MockitoAnnotations.initMocks(this);

        configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(DIRECTORY_FOR_EMAIL_TEMPLATES));
        template = configuration.getTemplate(BIANNUAL_CONTACT_DETAILS_TEMPLATE);
        volunteerAssignments = new ArrayList<>();
        volunteerAssignments.add(mockAssignment);

        VolunteerQualification volunteerQualification = new VolunteerQualification();
        volunteerQualifications = new ArrayList<>();
        volunteerQualifications.add(volunteerQualification);

        VolunteerSkill volunteerSkill = new VolunteerSkill();
        volunteerSkills = new ArrayList<>();
        volunteerSkills.add(volunteerSkill);

        when(mockVolunteerOne.getGender()).thenReturn(MOCK_VOLUNTEER_GENDER);
        when(mockVolunteerOne.getPersonId()).thenReturn(MOCK_PERSON_ID);
        when(mockVolunteerOne.getPerson()).thenReturn(mockPerson);
        when(mockVolunteerOne.getEmergencyContact()).thenReturn(mockPerson);

        when(mockPerson.getForename()).thenReturn(MOCK_PERSON_FORENAME);
        when(mockPerson.getSurname()).thenReturn(MOCK_PERSON_SURNAME);
        when(mockPerson.getPersonId()).thenReturn(MOCK_PERSON_ID);
        when(mockPerson.getEmail()).thenReturn(MOCK_PERSON_EMAIL);
        when(mockPerson.getTelephone()).thenReturn(MOCK_PERSON_TELEPHONE);
        when(mockPerson.getMobile()).thenReturn(MOCK_PERSON_MOBILE);
        when(mockPerson.getAddress()).thenReturn(mockAddress);
        when(mockPerson.getCongregation()).thenReturn(mockCongregation);

        when(mockAddress.getStreet()).thenReturn(MOCK_ADDRESS_STREET);
        when(mockAddress.getTown()).thenReturn(MOCK_ADDRESS_TOWN);
        when(mockAddress.getCounty()).thenReturn(MOCK_ADDRESS_COUNTY);
        when(mockAddress.getPostcode()).thenReturn(MOCK_ADDRESS_POSTCODE);

        when(mockCongregation.getName()).thenReturn(MOCK_CONGREGATION_NAME);

        when(mockVolunteerDao.findAssignments(MOCK_PERSON_ID)).thenReturn(volunteerAssignments);

        assignmentModel = new AssignmentModel();
        initialiseAssignmentModel();
        when(mockAssignmentModelFactory.generateAssignmentModel((Assignment) anyObject())).thenReturn(assignmentModel);

        volunteerQualificationModelList = new ArrayList<>();
        initialiseQualificationModelList();
        when(mockVolunteerModelFactory.generateVolunteerQualificationsModel(volunteerQualifications)).thenReturn(volunteerQualificationModelList);

        volunteerSkillModelList = new ArrayList<>();
        initialiseSkillModelList();
        when(mockVolunteerModelFactory.generateVolunteerSkillsModel(volunteerSkills)).thenReturn(volunteerSkillModelList);

        when(mockProperties.getProperty(SERVER_URL)).thenReturn(MOCK_SERVER_URL);
        when(mockProperties.getProperty(SECURITY_SALT)).thenReturn(MOCK_SECURITY_SALT);

        when(mockFreeMarkerConfigurer.getConfiguration()).thenReturn(mockConfiguration);
        when(mockConfiguration.getTemplate(BIANNUAL_CONTACT_DETAILS_TEMPLATE)).thenReturn(template);

    }

    /**
     * Should render an email object with the volunteer's contact details.
     *
     * @throws IOException if can't find the template
     * @throws TemplateException if Free marker has had enough
     */
    @Test
    public void shouldRenderAnEmailWithVolunteerContactDetails() throws IOException, TemplateException {
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertNotNull(email);
        assertThat(email.getText(), containsString(MOCK_PERSON_FORENAME));
        assertThat(email.getText(), containsString(MOCK_PERSON_SURNAME));
        assertThat(email.getText(), containsString(MOCK_ADDRESS_STREET));
        assertThat(email.getText(), containsString(MOCK_ADDRESS_TOWN));
        assertThat(email.getText(), containsString(MOCK_ADDRESS_COUNTY));
        assertThat(email.getText(), containsString(MOCK_ADDRESS_POSTCODE));

    }

    /**
     * The email should only render the work phone if it's provided in the database.
     *
     * @throws IOException if we can't find the template
     * @throws TemplateException if Freemarker has had enough
     */
    @Test
    public void shouldShowVolunteerWorkPhoneIfPresent() throws IOException, TemplateException {
        when(mockPerson.getWorkPhone()).thenReturn(MOCK_PERSON_WORKPHONE);

        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertThat(email.getText(), containsString(MOCK_PERSON_WORKPHONE));
    }

    /**
     * The email should not render the volunteer's work phone if has not been provided.
     * It should render the message: "A work phone has not been provided".
     *
     * @throws IOException if we can't find the template
     * @throws TemplateException if Freemarker has had enough
     */
    @Test
    public void shouldNotShowVolunteerWorkPhoneIfNotPresent() throws IOException, TemplateException {
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertThat(email.getText(), not(containsString(MOCK_PERSON_WORKPHONE)));
        assertThat(email.getText(), containsString("A work phone has not been provided"));
    }

    /**
     * Email should show volunteer assignment. We are only testing for one here.
     *
     * @throws IOException if template not found
     * @throws TemplateException if Freemarker's given up
     */
    @Test
    public void shouldShowVolunteerAssignment() throws IOException, TemplateException {
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertThat(email.getText(), containsString(MOCK_DEPARTMENT_NAME));
        assertThat(email.getText(), containsString(MOCK_ASSIGNMENT_ROLE));
    }

    /**
     * Email should show volunteer qualification if given.
     *
     * @throws IOException if template not found
     * @throws TemplateException Freemarker's given up
     */
    @Test
    public void shouldShowVolunteerQualification() throws IOException, TemplateException {
        when(mockVolunteerDao.findQualifications(MOCK_PERSON_ID)).thenReturn(volunteerQualifications);
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertThat(email.getText(), containsString(MOCK_QUALIFICATION_NAME));
    }

    /**
     * Given a volunteer has no qualifications,
     * when we produce the email, then the
     * "We currently do not have any information
     * about your qualifications" message should appear in
     * the email.
     *
     * @throws IOException if can't find the template
     * @throws TemplateException if Freemarker's given up
     */
    @Test
    public void shouldShowNoQualificationsMessage() throws IOException, TemplateException {
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertThat(email.getText(), containsString("We currently do not have any information about your qualifications"));
    }

    /**
     * Email should show the volunteer's skills. We are only testing for one skill.
     *
     * @throws IOException if can't find the template
     * @throws TemplateException if Freemarker has had enough
     */
    @Test
    public void shouldShowVolunteerSkill() throws IOException, TemplateException {
        when(mockVolunteerDao.findSkills(MOCK_PERSON_ID)).thenReturn(volunteerSkills);
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertThat(email.getText(), containsString(MOCK_SKILL_NAME));
    }

    /**
     * Given a volunteer with no recorded skills,
     * when we produce the email, then the Message:
     * "We currently do not have any information about your skills" is
     * displayed on the email.
     *
     * @throws IOException if can't find the template
     * @throws TemplateException if Freemarker has had enough
     */
    @Test
    public void shouldShowNoVolunteerSkillsMessage() throws IOException, TemplateException {
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        assertThat(email.getText(), containsString("We currently do not have any information about your skills"));
    }

    /**
     * Should show the confirmation link with the hash digest based on the person
     * id and the date time.
     *
     * @throws IOException if can't find template
     * @throws TemplateException if Freemarker's given up
     */
    @Test
    public void shouldShowTheConfirmationLink() throws IOException, TemplateException {
        Email email = volunteerContactDetailsEmailGenerator.generateEmailForVolunteers(mockVolunteerOne);

        final DateTime dateTime = new DateTime();
        String dateTimeString = dateTime.toString("yyyyMMddHHmm");
        String hash = HashGenerator.generateHash(dateTimeString + ":" + MOCK_PERSON_ID, MOCK_SECURITY_SALT);

        String completeUri = MOCK_SERVER_URL + MOCK_CONFIRMATION_URI_PARTIAL + dateTimeString + "/" + hash;
        assertThat(email.getText(), containsString(completeUri));
    }

    private void initialiseAssignmentModel() {
        EntityModel departmentModel = new EntityModel();
        departmentModel.setName(MOCK_DEPARTMENT_NAME);
        assignmentModel.setDepartment(departmentModel);
        assignmentModel.setRole(MOCK_ASSIGNMENT_ROLE);
        assignmentModel.setTradeNumber(MOCK_ASSIGNMENT_TRADE_NUMBER);
    }

    private void initialiseQualificationModelList() {
        EntityModel qualificationModel = new EntityModel();
        qualificationModel.setName(MOCK_QUALIFICATION_NAME);

        VolunteerQualificationModel volunteerQualificationModel = new VolunteerQualificationModel();
        volunteerQualificationModel.setQualification(qualificationModel);

        volunteerQualificationModelList.add(volunteerQualificationModel);
    }

    private void initialiseSkillModelList() {
        EntityModel department = new EntityModel();
        department.setName(MOCK_DEPARTMENT_NAME);

        EntityModel skill = new EntityModel();
        skill.setName(MOCK_SKILL_NAME);

        VolunteerSkillModel volunteerSkillModel = new VolunteerSkillModel();
        volunteerSkillModel.setDepartment(department);
        volunteerSkillModel.setSkill(skill);
        volunteerSkillModelList.add(volunteerSkillModel);
    }
}
