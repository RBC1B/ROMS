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
package uk.org.rbc1b.roms.controller.volunteer.ldc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 *
 * This is a unit test for the class {@code SubmitLDCFormVolunteerEmailGenerator}.
 *
 * @author rahulsingh
 */
@RunWith(MockitoJUnitRunner.class)
public class SubmitLDCFormVolunteerEmailGeneratorTest {

    private static final String DIRECTORY_FOR_EMAIL_TEMPLATES = "src/main/webapp/WEB-INF/emails";
    private static final String VOLUNTEER_SUBMIT_LDC_FORM_TEMPLATE = "volunteer-submit-ldc-form.ftl";

    private static final String MOCK_EMAIL_ADDRESS = "rahul.singh@gmailings.com";
    private static final String MOCK_VOLUNTEER_GENDER = "M";
    private static final String MOCK_VOLUNTEER_FORENAME = "Rahul";
    private static final String MOCK_VOLUNTEER_SURNAME = "Singh";

    @InjectMocks
    private SubmitLDCFormVolunteerEmailGenerator submitLDCFormVolunteerEmailGenerator;
    @Mock
    private Volunteer mockVolunteer;
    @Mock
    private Person mockPerson;
    @Mock
    private FreeMarkerConfigurer mockFreeMarkerConfigurer;
    @Mock
    private Configuration mockConfiguration;
    private Configuration configuration;
    private Template template;
    private Date twoAndHalfYearsOldDate;
    private Date olderThanTwoAndHalfYrsDate;
    private Date threeYearsOldDate;
    private Date fourYearsOldDate;

    /**
     * Set-up mocks and test case scenarios.
     *
     * @throws IOException if can't access the template properly
     */
    @Before
    public void setUp() throws IOException {
        // inject mocks and initialise
        MockitoAnnotations.initMocks(this);

        // set up Freemarker configuration
        configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(DIRECTORY_FOR_EMAIL_TEMPLATES));
        template = configuration.getTemplate(VOLUNTEER_SUBMIT_LDC_FORM_TEMPLATE);

        when(mockFreeMarkerConfigurer.getConfiguration()).thenReturn(mockConfiguration);
        when(mockConfiguration.getTemplate(VOLUNTEER_SUBMIT_LDC_FORM_TEMPLATE)).thenReturn(template);

        // volunteer and person mocks
        when(mockVolunteer.getPerson()).thenReturn(mockPerson);
        when(mockPerson.getEmail()).thenReturn(MOCK_EMAIL_ADDRESS);
        when(mockVolunteer.getGender()).thenReturn(MOCK_VOLUNTEER_GENDER);
        when(mockPerson.getForename()).thenReturn(MOCK_VOLUNTEER_FORENAME);
        when(mockPerson.getSurname()).thenReturn(MOCK_VOLUNTEER_SURNAME);

        // set two and half yrs old date
        DateTime twoAndHalfYrsInPast = new DateTime();
        twoAndHalfYrsInPast = twoAndHalfYrsInPast.minusYears(2);
        twoAndHalfYrsInPast = twoAndHalfYrsInPast.minusMonths(6);
        twoAndHalfYearsOldDate = DataConverterUtil.toSqlDate(twoAndHalfYrsInPast);

        // set older than two and half yrs old date
        DateTime olderThanTwoAndHalfYrsInPast = twoAndHalfYrsInPast.minusMonths(2);
        olderThanTwoAndHalfYrsDate = DataConverterUtil.toSqlDate(olderThanTwoAndHalfYrsInPast);

        // set three yrs old date
        threeYearsOldDate = DataConverterUtil.toSqlDate(new DateTime().minusYears(3));

        // set four yrs old date
        fourYearsOldDate = DataConverterUtil.toSqlDate(new DateTime().minusYears(4));
    }

    /**
     * Email should show the volunteer's details. Including forename, surname
     * using the Freemarker template.
     *
     * @throws IOException if I/O error
     * @throws TemplateException if Freemarker can't inject the values into the template
     */
    @Test
    public void shouldUseFreemarkerTemplateToProduceEmailMessage() throws IOException, TemplateException {
        when(mockVolunteer.getFormDate()).thenReturn(twoAndHalfYearsOldDate);
        Email email = submitLDCFormVolunteerEmailGenerator.generateEmailForVolunteers(mockVolunteer);

        assertThat(email.getText(), containsString("Brother"));
        assertThat(email.getText(), containsString(MOCK_VOLUNTEER_FORENAME));
        assertThat(email.getText(), containsString(MOCK_VOLUNTEER_SURNAME));
    }

    /**
     * Given a volunteer, when his/her form date is null (unknown),
     * then display the form date unknown message in the email.
     *
     * @throws IOException if I/O error
     * @throws TemplateException if Freemarker's had enough
     */
    @Test
    public void shouldShowFormDateNotKnownMessage() throws IOException, TemplateException {
        generateEmail(null, SubmitLDCFormEmailMessageConstants.FORM_DATE_UNKNOWN);
    }

    /**
     * Given a volunteer, when his/her form date is exactly two and half
     * years, then show the 'at least two and half years old' message.
     *
     * @throws IOException I/O Error
     * @throws TemplateException Freemarker template error
     */
    @Test
    public void shouldShowFormTwoAndHalfYrsOldMessage() throws IOException, TemplateException {
        generateEmail(twoAndHalfYearsOldDate, SubmitLDCFormEmailMessageConstants.FORM_DATE_TWO_HALF_YRS);
    }

    /**
     * Given a volunteer, when his/her form date is older than two and half years,
     * but not older than 3 years, then show the 'at least two and half years old'
     * message.
     *
     * @throws IOException I/O Error
     * @throws TemplateException Freemarker template error
     */
    @Test
    public void shouldShowFormOlderThanTwoAndHalfYrsMessage() throws IOException, TemplateException {
        generateEmail(olderThanTwoAndHalfYrsDate, SubmitLDCFormEmailMessageConstants.FORM_DATE_TWO_HALF_YRS);
    }

    /**
     * Given a volunteer, when his/her form date is three years old,
     * then show the 'at least 3 years old' message.
     *
     * @throws IOException I/O Error
     * @throws TemplateException Freemarker template error
     */
    @Test
    public void shouldShowFormThreeYearsOrOlderMessage() throws IOException, TemplateException {
        generateEmail(threeYearsOldDate, SubmitLDCFormEmailMessageConstants.FORM_DATE_THREE_YRS);
    }

    /**
     * Given a volunteer, when his/her form date is older than 3 years,
     * then show the older than 3 years message.
     *
     * @throws IOException I/O error
     * @throws TemplateException template freemarker exception
     */
    @Test
    public void shouldShowThreeYrsOlderMessageForVeryOldFormDate() throws IOException, TemplateException {
        generateEmail(fourYearsOldDate, SubmitLDCFormEmailMessageConstants.FORM_DATE_THREE_YRS);
    }

    /**
     * Generate email and carry our assertions on the email.
     *
     * @param date the sample form date
     * @param expectedEmaillMessageConstant expected email message
     * @throws IOException I/O error
     * @throws TemplateException template exception
     */
    private void generateEmail(Date date, SubmitLDCFormEmailMessageConstants expectedEmaillMessageConstant) throws IOException, TemplateException {
        when(mockVolunteer.getFormDate()).thenReturn(date);
        Email email = submitLDCFormVolunteerEmailGenerator.generateEmailForVolunteers(mockVolunteer);

        assertThat(email.getText(), containsString(
                expectedEmaillMessageConstant.getMessage()));

    }
}
