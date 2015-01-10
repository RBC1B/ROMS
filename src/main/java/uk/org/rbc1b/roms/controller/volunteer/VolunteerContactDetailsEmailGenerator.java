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

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 *
 * @author rahulsingh
 */
@Component
public class VolunteerContactDetailsEmailGenerator {

    private static final String BIANNUAL_CONTACT_DETAILS_TEMPLATE = "volunteer-biannual-contact-details.ftl";
    private static final String SUBJECT = "RBC (London & Home Counties) Volunteer Information Held on Edifice";

    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;

    /**
     * Generate the email template for the volunteer passed into this
     * generator class.
     *
     * @param volunteer the volunteer (recipient) to be used in the template
     * @return Email full email with injected template
     * @throws IOException if we can't find the template
     * @throws TemplateException if free marker has trouble
     */
    public Email generateEmailForVolunteers(Volunteer volunteer) throws IOException, TemplateException {

        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, Object> model = new HashMap<>();

        model.put("volunteer", volunteer);

        populatePersonModel(model, volunteer.getPerson());

        Email email = new Email();
        email.setRecipient(volunteer.getPerson().getEmail());
        email.setSubject(SUBJECT);
        email.setText(FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(BIANNUAL_CONTACT_DETAILS_TEMPLATE), model));

        return email;
    }

    private void populatePersonModel(Map<String, Object> model, Person person) {
        model.put("person", person);
    }

}
