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
package uk.org.rbc1b.roms.controller.project;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectAvailabilityDao;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;

/**
 *
 * @author ramindursingh
 */
@Component
public class VolunteerForProjectModelFactory {

    @Autowired
    private ProjectAvailabilityDao projectAvailabilityDao;
    @Autowired
    private PersonDao personDao;

    /**
     * Generates the model to display for requests to volunteers to make their
     * availability known. This is a list of all department volunteers used to
     * invite.
     *
     * @param assignments the departmental assignments
     * @param workSession the project worksessions
     * @return a treemap of availability to display
     */
    public List<ProjectAvailabilityModel> generate(List<Assignment> assignments, ProjectDepartmentSession workSession) {
        List<ProjectAvailabilityModel> volunteersForProject = new ArrayList<ProjectAvailabilityModel>();

        for (Assignment assignment : assignments) {
            ProjectAvailabilityModel model;

            if (workSession == null) {
                model = generate(assignment, workSession);
            } else {
                ProjectAvailability availability = projectAvailabilityDao.findVolunteerAvailabilityByWorkSession(
                        assignment.getPerson().getPersonId(), workSession);
                if (availability != null) {
                    model = generate(availability);
                } else {
                    model = generate(assignment, workSession);
                }
            }
            volunteersForProject.add(model);
        }
        return volunteersForProject;
    }

    /**
     * Used to generate a model based on assignment and work session. Used to
     * generate a model for a person who has not been invited - no records in
     * available table.
     *
     * @param assignment the departmental assignment of the volunteer
     * @param session the work session
     * @return model
     */
    public ProjectAvailabilityModel generate(Assignment assignment, ProjectDepartmentSession session) {
        ProjectAvailabilityModel model = new ProjectAvailabilityModel();
        model.setProjectDepartmentSessionId(session.getProjectDepartmentSessionId());
        Person person = personDao.findPerson(assignment.getPerson().getPersonId());

        model.setPersonId(person.getPersonId());
        model.setPersonName(person.getSurname() + ", " + person.getForename());

        String address = person.getAddress().getTown() + ", " + person.getAddress().getCounty() + ", "
                + person.getAddress().getPostcode();
        model.setAddress(address);
        model.setInvited(false);
        model.setEmailSent(false);
        model.setPersonResponded(false);
        model.setOverseerConfirmed(false);
        model.setTransportRequired(false);
        model.setOfferTransport(false);
        model.setAccommodationRequired(false);

        return model;
    }

    /**
     * Generates a model for a volunteer who has already been invited, i.e.
     * there is a record in the availability table.
     *
     * @param availability the available record
     * @return model
     */
    public ProjectAvailabilityModel generate(ProjectAvailability availability) {
        ProjectAvailabilityModel model = new ProjectAvailabilityModel();

        model.setProjectDepartmentSessionId(availability.getProjectDepartmentSession().getProjectDepartmentSessionId());

        Person person = personDao.findPerson(availability.getPerson().getPersonId());

        model.setPersonId(person.getPersonId());
        model.setPersonName(person.getSurname() + ", " + person.getForename());

        if (person.getAddress() != null) {
            String address = person.getAddress().getTown() + ", " + person.getAddress().getCounty() + ", "
                    + person.getAddress().getPostcode();
            model.setAddress(address);
        }

        model.setInvited(true);
        model.setEmailSent(availability.isEmailSent());
        model.setPersonResponded(availability.isPersonResponded());
        model.setOverseerConfirmed(availability.isOverseerConfirmed());
        model.setTransportRequired(availability.isTransportRequired());
        model.setOfferTransport(availability.isOfferTransport());
        model.setAccommodationRequired(availability.isAccommodationRequired());

        return model;
    }
}
