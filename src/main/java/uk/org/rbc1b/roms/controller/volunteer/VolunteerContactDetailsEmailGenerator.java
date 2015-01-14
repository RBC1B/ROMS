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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.qualification.VolunteerQualification;
import uk.org.rbc1b.roms.db.volunteer.skill.VolunteerSkill;

/**
 * Generates the email that should be sent to each volunteer over a period of six months.
 * Generates the volunteer information required in the email.
 *
 * @author rahulsingh
 */
@Component
public class VolunteerContactDetailsEmailGenerator {

    private static final String BIANNUAL_CONTACT_DETAILS_TEMPLATE = "volunteer-biannual-contact-details.ftl";
    private static final String SUBJECT = "RBC (London & Home Counties) Volunteer Information Held on Edifice";

    @Autowired
    private VolunteerDao volunteerDao;

    @Autowired
    private AssignmentModelFactory assignmentModelFactory;

    @Autowired
    private VolunteerModelFactory volunteerModelFactory;

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
        model.put("emergencyContact", volunteer.getEmergencyContact());

        List<Assignment> assignments = volunteerDao.findAssignments(volunteer.getPersonId());
        populateAssignments(model, assignments);

        List<VolunteerQualification> qualifications = volunteerDao.findQualifications(volunteer.getPersonId());
        model.put("qualifications", volunteerModelFactory.generateVolunteerQualificationsModel(qualifications));

        List<VolunteerSkill> skills = volunteerDao.findSkills(volunteer.getPersonId());
        model.put("skills", volunteerModelFactory.generateVolunteerSkillsModel(skills));

        Email email = new Email();
        email.setRecipient(volunteer.getPerson().getEmail());
        email.setSubject(SUBJECT);
        email.setText(FreeMarkerTemplateUtils.processTemplateIntoString(conf.getTemplate(BIANNUAL_CONTACT_DETAILS_TEMPLATE), model));

        return email;
    }

    /**
     * Populate the person data into the model which will be injected into the template
     * using the model map.
     *
     * @param model model map
     * @param person person data
     */
    private void populatePersonModel(Map<String, Object> model, Person person) {
        model.put("person", person);

        Address address = person.getAddress();
        model.put("street", address.getStreet());
        model.put("town", address.getTown());
        model.put("county", address.getCounty());
        model.put("postcode", address.getPostcode());
    }

    private void populateAssignments(Map<String, Object> model, List<Assignment> assignments) {
        List<AssignmentModel> assignmentModelList = new ArrayList<>();
        for (Assignment assignment : assignments) {
            assignmentModelList.add(assignmentModelFactory.generateAssignmentModel(assignment));
        }
        model.put("assignments", assignmentModelList);
    }
}
