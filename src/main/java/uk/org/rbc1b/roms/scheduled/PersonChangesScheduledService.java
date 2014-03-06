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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonChange;
import uk.org.rbc1b.roms.db.PersonChangeDao;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
import uk.org.rbc1b.roms.db.email.EmailRecipient;
import uk.org.rbc1b.roms.db.email.EmailRecipientDao;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Checks PersonChange table to see if there are any outstanding changes.
 * Send out an email notification if required.
 */
@Component
public class PersonChangesScheduledService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonChangesScheduledService.class);
    private static final String VOLUNTEER_UPDATE = "VU";
    private static final String EMAIL_TEMPLATE = "person-changes.ftl";
    private static final String SUBJECT = "Volunteer Information Changes";
    @Autowired
    private PersonChangeDao personChangeDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private EmailRecipientDao emailRecipientDao;
    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Queue up emails if there are outstanding person form changes.
     * Scheduled to run at 1am every day
     */
    // @Scheduled(cron = "*/5 * * * * ?") Test setting
    @Scheduled(cron = "0 0 01 * * ?")
    public void checkIfOutstandingChanges() {
        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(system, system.getUsername(),
                system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<PersonChange> personChangeList = personChangeDao.findPersonChangeNotUpdated();
        if (personChangeList.isEmpty()) {
            return;
        }
        List<EmailRecipient> mailRecipients = emailRecipientDao.getRecipientByEmailCode(VOLUNTEER_UPDATE);
        for (EmailRecipient mailRecipient : mailRecipients) {
            try {
                createEmailForRecipient(mailRecipient);
            } catch (IOException e) {
                LOGGER.error("Failed to send parseon change notification email", e);
            } catch (TemplateException e) {
                LOGGER.error("Failed to send parseon change notification email", e);
            }
        }
    }

    private void createEmailForRecipient(EmailRecipient mailRecipient) throws IOException, TemplateException {
        Map<String, Person> model = new HashMap<String, Person>();
        Person person = personDao.findPerson(mailRecipient.getPerson().getPersonId());
        model.put("person", person);
        Configuration conf = emailFreemarkerConfigurer.getConfiguration();
        Template template = conf.getTemplate(EMAIL_TEMPLATE);
        Writer out = new StringWriter();
        template.process(model, out);
        Email email = new Email();
        email.setRecipient(person.getEmail());
        email.setSubject(SUBJECT);
        email.setText(out.toString());
        emailDao.save(email);
    }

}
