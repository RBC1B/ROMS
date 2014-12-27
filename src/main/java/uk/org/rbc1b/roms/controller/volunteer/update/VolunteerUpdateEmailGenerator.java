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
package uk.org.rbc1b.roms.controller.volunteer.update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.controller.common.HashGenerator;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * Generate the emails associated with requesting and confirming volunteer detail updates.
 */
@Component
public class VolunteerUpdateEmailGenerator {
    private static final String UPDATE_REQUEST_TEMPLATE = "volunteer-contact-update-request.ftl";
    private static final String UPDATE_CONFIRMATION_TEMPLATE = "volunteer-contact-update-confirmation.ftl";
    private static final String SUBJECT = "RBC (London & Home Counties) Volunteer Information Update";
    private static final String SERVER_URL = "edifice.url";
    private static final String DATETIME_FORMAT = "yyyyMMddHHmm";
    private static final String SECURITY_SALT = "security.salt";

    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;
    @Resource(name = "edificeProperty")
    private Properties edificeProperty;

    /**
     * Prepares an email to send to the volunteer with the link to the form where they can update
     * their information.
     *
     * @param volunteer the volunteer to send
     * @return email
     * @throws TemplateException on failure process the template
     * @throws IOException on failure to look up the template
     */
    public Email generateVolunteerUpdateRequestEmail(Volunteer volunteer) throws IOException, TemplateException {
        String uri = generateSecureUri(volunteer);

        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, String> model = new HashMap<String, String>();
        model.put("forename", volunteer.getPerson().getForename());
        model.put("httpsurl", uri);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(UPDATE_REQUEST_TEMPLATE),
                model);
        Email email = new Email();
        email.setRecipient(volunteer.getPerson().getEmail());
        email.setSubject(SUBJECT);
        email.setText(text);

        return email;
    }

    /**
     * Generate the email send to the volunteer to confirm they have updated their details.
     * @param volunteer volunteer
     * @return email
     * @throws TemplateException on failure process the template
     * @throws IOException on failure to look up the template
     */
    public Email generateVolunteerUpdateConfirmationEmail(Volunteer volunteer) throws IOException, TemplateException {
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, String> model = new HashMap<String, String>();
        model.put("forename", volunteer.getPerson().getForename());
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(UPDATE_CONFIRMATION_TEMPLATE),
                model);
        Email email = new Email();
        email.setRecipient(volunteer.getPerson().getEmail());
        email.setSubject(SUBJECT + " - Confirmation");
        email.setText(text);

        return email;
    }

    /**
     * Gets the secure URI for a given volunteer
     *
     * @param volunteer the volunteer
     * @return url string
     */
    private String generateSecureUri(Volunteer volunteer) {
        List<String> path = new ArrayList<String>();
        path.add(edificeProperty.getProperty(SERVER_URL));
        path.add("volunteer-contact");
        path.add(Integer.toString(volunteer.getPersonId()));
        String dateTime = getCurrentDateTime();
        path.add(dateTime);

        String value = dateTime + ":" + volunteer.getPersonId() + volunteer.getPerson().getBirthDate().toString();
        path.add(HashGenerator.generateHash(value, edificeProperty.getProperty(SECURITY_SALT)));

        return StringUtils.join(path, "/");
    }

    private String getCurrentDateTime() {
        DateTime datetime = new DateTime();
        return datetime.toString(DATETIME_FORMAT);
    }

}
