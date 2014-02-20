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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.email.Email;
import uk.org.rbc1b.roms.email.EmailDao;
import uk.org.rbc1b.roms.email.EmailRecipient;
import uk.org.rbc1b.roms.email.EmailRecipientDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonChange;
import uk.org.rbc1b.roms.db.PersonChangeDao;
import uk.org.rbc1b.roms.db.PersonDao;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Checks PersonChange table to see if there are any outstanding changes.
 */
@Component
public class PersonChangeChecker {

    private static final String VOLUNTEER_UPDATE = "VU";
    private static final String EMAIL_TEMPLATE = "EmailForPersonChanges.ftl";
    private static final String SUBJECT = "Volunteer Information Changes";
    @Autowired
    private PersonChangeDao personChangeDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private EmailRecipientDao mailRecipientDao;
    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    /**
     * Checks if there are changes in the PersonChanges table.
     *
     * @throws IOException the IO exception reading template
     * @throws TemplateException the template mapping
     */
    public void checkIfOutstandingChanges() throws IOException, TemplateException {
        List<PersonChange> personChangeList = personChangeDao.findPersonChangeNotUpdated();
        if (!personChangeList.isEmpty()) {
            List<EmailRecipient> mailRecipients = mailRecipientDao.getRecipientByEmailCode(VOLUNTEER_UPDATE);
            for (EmailRecipient mailRecipient : mailRecipients) {
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

}
