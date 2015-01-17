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
package uk.org.rbc1b.roms.controller.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Factory class to create the volunteerEmergencyContact model.
 */
@Component
public class VolunteerEmergencyContactModelFactory {
    @Autowired
    private ReferenceDao referenceDao;
    @Autowired
    private PersonModelFactory personModelFactory;

    /**
     * Create the emergency contact model.
     *
     * @param volunteer the volunteer to get the emergency contact from
     * (temporary arrangement until emergency contacts are stored in a separate
     * table).
     * @return model
     */
    public VolunteerEmergencyContactModel generateVolunteerEmergencyContactModel(Volunteer volunteer) {

        VolunteerEmergencyContactModel model = new VolunteerEmergencyContactModel();
        model.setId(1); // haven't got an ID at the moment, so spoofing one
        model.setPerson(personModelFactory.generatePersonModel(volunteer.getEmergencyContact()));
        model.setUri(VolunteerModelFactory.generateUri(volunteer.getPersonId()) + "/emergencycontacts/1");

        if (volunteer.getEmergencyContactRelationshipCode() != null) {
            model.setRelationship(referenceDao.findRelationshipValues().get(volunteer.getEmergencyContactRelationshipCode()));
        }
        return model;
    }
}
