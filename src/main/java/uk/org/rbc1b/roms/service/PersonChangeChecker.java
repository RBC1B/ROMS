/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
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
package uk.org.rbc1b.roms.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.db.PersonChange;
import uk.org.rbc1b.roms.db.PersonChangeDao;
import uk.org.rbc1b.roms.db.Email;
import uk.org.rbc1b.roms.db.EmailDao;
import uk.org.rbc1b.roms.db.MailRecipient;
import uk.org.rbc1b.roms.db.MailRecipientDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;

/**
 * Checks PersonChange table to see if there are any outstanding changes.
 */
@Component
public class PersonChangeChecker {

    // private static final String RECIPIENT_SEARCH = "Volunteer Update";
    private static final Integer RECIPIENT_ID = 1;
    private static final String EMAIL_TEMPLATE = "EmailForPersonChanges.ftl";
    private static final String SUBJECT = "Volunteer Information Changes";
    private PersonChangeDao personChangeDao;
    private PersonDao personDao;
    private EmailDao emailDao;
    private MailRecipientDao mailRecipientDao;
    private FreeMarkerConfigurer freemarkerConfig;

    /**
     * Checks if there are changes in the PersonChanges table.
     *
     * @throws IOException the IO exception reading template
     * @throws TemplateException the template mapping
     */
    public void checkIfOutstandingChanges()
            throws IOException, TemplateException {
        List<PersonChange> personChangeList = personChangeDao.findPersonChangeNotUpdated();
        if (!personChangeList.isEmpty()) {
            List<MailRecipient> mailRecipients = mailRecipientDao.getRecipientByMailTypeId(RECIPIENT_ID);
            for (MailRecipient mailRecipient : mailRecipients) {
                Map<String, Person> model = new HashMap<String, Person>();
                Person person = personDao.findPerson(mailRecipient.getPerson().getPersonId());
                model.put("person", person);
                Configuration conf = freemarkerConfig.getConfiguration();
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
    }

    /**
     * @param personChangeDao the personChangeDao to set
     */
    @Autowired
    public void setPersonChangeDao(PersonChangeDao personChangeDao) {
        this.personChangeDao = personChangeDao;
    }

    /**
     * @param personDao the personDao to set
     */
    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    /**
     * @param emailDao the emailDao to set
     */
    @Autowired
    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    /**
     * @param mailRecipientDao the mailRecipientDao to set
     */
    @Autowired
    public void setMailRecipientDao(MailRecipientDao mailRecipientDao) {
        this.mailRecipientDao = mailRecipientDao;
    }

    /**
     * @param freemarkerConfig the freemarkerConfig to set
     */
    @Autowired
    public void setFreemarkerConfig(FreeMarkerConfigurer freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }
}
