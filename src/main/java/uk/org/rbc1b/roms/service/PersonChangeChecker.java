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

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.PersonChange;
import uk.org.rbc1b.roms.db.PersonChangeDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;
import uk.org.rbc1b.roms.db.Email;
import uk.org.rbc1b.roms.db.EmailDao;

/**
 * Checks PersonChange table to see if there are any outstanding changes.
 */
@Component
public class PersonChangeChecker {

    @Autowired
    private PersonChangeDao personChangeDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private VelocityEngine velocityEngine;

    /**
     * Checks if there are changes in the PersonChanges table.
     *
     */
    public void checkIfOutstandingChanges() {
        List<PersonChange> personChangeList = this.personChangeDao.findPersonChangeNotUpdated();
        if (!personChangeList.isEmpty()) {
            Volunteer volunteerOverseer = new Volunteer();
            volunteerOverseer.setForename("Ramindur");
            volunteerOverseer.setEmail("ramindur.singh@blackcrowsys.com");
            Map<String, Volunteer> model = new HashMap<String, Volunteer>();
            model.put("volunteerOverseer", volunteerOverseer);
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "uk/org/rbc1b/roms/service/EmailForPersonChanges.vm", model);
            Email email = new Email();
            email.setReceipient(volunteerOverseer.getEmail());
            email.setSubject("Volunteer Information Changes");
            email.setText(text);
            emailDao.save(email);
        }
    }
}
