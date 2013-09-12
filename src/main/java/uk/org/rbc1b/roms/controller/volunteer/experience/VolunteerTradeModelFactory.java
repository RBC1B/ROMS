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
package uk.org.rbc1b.roms.controller.volunteer.experience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerTrade;

/**
 * Generate the volunteer trade model.
 */
@Component
public class VolunteerTradeModelFactory {
    private PersonDao personDao;
    private PersonModelFactory personModelFactory;

    /**
     * Generate the volunteer trade model.
     * @param volunteerTrade trade
     * @return mode
     */
    public VolunteerTradeModel generateVolunteerTradeModel(VolunteerTrade volunteerTrade) {

        VolunteerTradeModel model = new VolunteerTradeModel();
        model.setExperienceDescription(volunteerTrade.getExperienceDescription());
        model.setExperienceYears(volunteerTrade.getExperienceYears());
        model.setName(volunteerTrade.getName());
        model.setPerson(personModelFactory.generatePersonModel(personDao.findPerson(volunteerTrade.getPerson()
                .getPersonId())));
        return model;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

}
