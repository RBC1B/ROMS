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
package uk.org.rbc1b.roms.controller.volunteer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

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

    @InjectMocks
    private VolunteerContactDetailsEmailGenerator volunteerContactDetailsEmailGenerator;

    @Mock
    private Volunteer mockVolunteerOne;
    @Mock
    private Volunteer mockVolunteerTwo;
    @Mock
    private Person mockPerson;
    @Mock
    private FreeMarkerConfigurer mockFreeMarkerConfigurer;
    private Configuration configuration;
    @Mock
    private Configuration mockConfiguration;
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

        when(mockVolunteerOne.getGender()).thenReturn(MOCK_VOLUNTEER_GENDER);
        when(mockVolunteerOne.getPerson()).thenReturn(mockPerson);

        when(mockPerson.getForename()).thenReturn(MOCK_PERSON_FORENAME);
        when(mockPerson.getSurname()).thenReturn(MOCK_PERSON_SURNAME);
        when(mockPerson.getPersonId()).thenReturn(MOCK_PERSON_ID);
        when(mockPerson.getEmail()).thenReturn(MOCK_PERSON_EMAIL);
        when(mockPerson.getTelephone()).thenReturn(MOCK_PERSON_TELEPHONE);
        when(mockPerson.getMobile()).thenReturn(MOCK_PERSON_MOBILE);

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

    }
}
