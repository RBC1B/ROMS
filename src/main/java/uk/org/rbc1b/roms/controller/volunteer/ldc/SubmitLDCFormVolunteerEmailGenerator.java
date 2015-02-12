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

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Email Generator informing a volunteer that he/she needs to submit
 * a new LDC form.
 *
 * @author rahulsingh
 */
@Component
public class SubmitLDCFormVolunteerEmailGenerator {

    private static final String SUBJECT = "LDC Form Update";
    private static final String SUBMIT_LDC_FORM_TEMPLATE = "volunteer-submit-ldc-form.ftl";

    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;

    /**
     * Generate email for a volunteer with the correct message.
     *
     * @param volunteer the volunteer
     * @return Email object
     * @throws IOException if we can't find the email template
     * @throws TemplateException if Freemarker's had enough
     */
    public Email generateEmailForVolunteers(Volunteer volunteer) throws IOException, TemplateException {
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Map<String, Object> model = new HashMap<>();
        model.put("volunteer", volunteer);

        Date date = volunteer.getFormDate();
        if (date == null) {
            model.put("message", SubmitLDCFormEmailMessageConstants.FORM_DATE_UNKNOWN.getMessage());
        } else {
            DateTime formDate = DataConverterUtil.toDateTime(date);
            DateTime todayDate = new DateTime();
            Period period = new Period(formDate, todayDate);

            if ((period.getYears() == 2) && (period.getMonths() >= 6)) {
                model.put("message", SubmitLDCFormEmailMessageConstants.FORM_DATE_TWO_HALF_YRS.getMessage());
            } else if (period.getYears() >= 3) {
                model.put("message", SubmitLDCFormEmailMessageConstants.FORM_DATE_THREE_YRS.getMessage());
            }
        }

        Email email = new Email();
        email.setRecipient(volunteer.getPerson().getEmail());
        email.setSubject(SUBJECT);
        email.setText(FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(SUBMIT_LDC_FORM_TEMPLATE), model));

        return email;
    }

}
