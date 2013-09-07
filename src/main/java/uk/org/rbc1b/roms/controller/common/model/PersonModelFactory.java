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
package uk.org.rbc1b.roms.controller.common.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.congregation.CongregationModelFactory;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.application.User;

/**
 * Create a person model.
 * @author oliver
 */
@Component
public class PersonModelFactory {

    private static final String BASE_URI = "/persons/";
    private CongregationDao congregationDao;

    /**
     * Generate the uri used to access the person pages.
     * @param personId optional volunteer id
     * @return uri
     */
    public static String generateUri(Integer personId) {
        return personId != null ? BASE_URI + personId : BASE_URI;
    }

    /**
     * Create a person model.
     * @param person person
     * @return model
     */
    public PersonModel generatePersonModel(Person person) {

        if (person == null) {
            return null;
        }

        PersonModel model = new PersonModel();
        model.setId(person.getPersonId());
        model.setUri(generateUri(person.getPersonId()));

        model.setAddress(person.getAddress());
        model.setBirthDate(person.getBirthDate());
        model.setComments(person.getComments());
        if (person.getCongregation() != null) {
            model.setCongregation(generateCongregationModel(person.getCongregation().getCongregationId()));
        }
        model.setEmail(person.getEmail());
        model.setForename(person.getForename());
        model.setMiddleName(person.getMiddleName());
        model.setSurname(person.getSurname());
        model.setMobile(person.getMobile());
        model.setTelephone(person.getTelephone());
        model.setWorkPhone(person.getWorkPhone());

        model.setUri(generateUri(person.getPersonId()));
        model.setEditUri(generateUri(person.getPersonId()) + "/edit");

        return model;
    }

    /**
     * Generate the user model. We point the user uri to the person page.
     * @param user user
     * @return model
     */
    public EntityModel generateUserModel(User user) {
        if (user == null) {
            return null;
        }
        EntityModel model = new EntityModel();
        model.setName(user.getUserName());
        model.setId(user.getPersonId());
        model.setUri(generateUri(user.getPersonId()));
        return model;
    }

    private EntityModel generateCongregationModel(Integer congregationId) {
        if (congregationId == null) {
            return null;
        }

        Congregation congregation = congregationDao.findCongregation(congregationId);

        EntityModel congregationModel = new EntityModel();
        congregationModel.setId(congregation.getCongregationId());
        congregationModel.setName(congregation.getName());
        congregationModel.setUri(CongregationModelFactory.generateUri(congregation.getCongregationId()));

        return congregationModel;
    }

    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }
}
