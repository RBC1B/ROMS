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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.congregation.CongregationModelFactory;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.application.User;

/**
 * Create a person model.
 * @author oliver
 */
@Component
public class PersonModelFactory {

    private static final String BASE_URI = "/persons/";
    @Autowired
    private CongregationDao congregationDao;

    @Autowired
    private PersonDao personDao;

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
        model.setInitials(calculatePersonInitials(person));
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
    public UserModel generateUserModel(User user) {
        if (user == null) {
            return null;
        }
        UserModel model = new UserModel();
        model.setName(user.getUserName());
        model.setId(user.getPersonId());
        model.setUri(generateUri(user.getPersonId()));

        Person person = personDao.findPerson(user.getPersonId());
        model.setInitials(calculatePersonInitials(person));

        return model;
    }

    /**
     * Generate the simplified entity model to represent the volunteer congregation.
     * @param congregationId congregation id
     * @return entity model
     */
    public EntityModel generateCongregationModel(Integer congregationId) {
        if (congregationId == null) {
            return null;
        }

        Congregation congregation = congregationDao.findCongregation(congregationId);
        return generateCongregationModel(congregation);
    }

    /**
     * Generate the simplified entity model to represent the volunteer congregation.
     * @param congregation congregation
     * @return entity model
     */
    public EntityModel generateCongregationModel(Congregation congregation) {
        EntityModel congregationModel = new EntityModel();
        congregationModel.setId(congregation.getCongregationId());
        congregationModel.setName(congregation.getName());
        congregationModel.setUri(CongregationModelFactory.generateUri(congregation.getCongregationId()));

        return congregationModel;
    }

    /**
     * Calculate the persons initials from their entered names.
     * @param person person
     * @return initials
     */
    private String calculatePersonInitials(Person person) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(person.getForename())) {
            builder.append(person.getForename().charAt(0));
        }
        if (StringUtils.isNotBlank(person.getMiddleName())) {
            builder.append(person.getMiddleName().charAt(0));
        }
        if (StringUtils.isNotBlank(person.getSurname())) {
            builder.append(person.getSurname().charAt(0));
        }

        // catch-all.. if person has managed to save an empty name, return a placeholder
        if (builder.length() == 0) {
            builder.append("XX");
        }

        return builder.toString();
    }
}
