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
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.Email;
import uk.org.rbc1b.roms.db.EmailDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;

/**
 * Checks PersonChange table to see if there are any outstanding changes.
 */
@Component
public class PersonChangeChecker {

    @Autowired
    private PersonChangeDao personChangeDao;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    /**
     * Checks if there are changes in the PersonChanges table.
     *
     * @throws IOException the IO exception reading template
     * @throws TemplateException the template mapping
     */
    public void checkIfOutstandingChanges()
            throws IOException, TemplateException {
        List<PersonChange> personChangeList = this.personChangeDao.findPersonChangeNotUpdated();
        if (!personChangeList.isEmpty()) {
            // This should be the volunteer overseer
            Volunteer volunteerOverseer = new Volunteer();
            volunteerOverseer.setForename("Ramindur");
            volunteerOverseer.setEmail("ramindur.singh@blackcrowsys.com");


            Map<String, Volunteer> model = new HashMap<String, Volunteer>();
            model.put("volunteerOverseer", volunteerOverseer);
            Configuration conf = freemarkerConfig.getConfiguration();
            Template template = conf.getTemplate("EmailForPersonChanges.ftl");
            Writer out = new StringWriter();
            template.process(model, out);
            Email email = new Email();
            email.setReceipient(volunteerOverseer.getEmail());
            email.setSubject("Volunteer Information Changes");
            email.setText(out.toString());
            emailDao.save(email);
        }
    }
}
