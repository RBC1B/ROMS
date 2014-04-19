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
package uk.org.rbc1b.roms.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.application.User;

/**
 * Generates the users models.
 *
 */
@Component
public class UserModelFactory {

    private static final String BASE_URI = "/users";
    private static final String VOLUNTEER_BASE_URI = "/volunteers";
    @Autowired
    private PersonDao personDao;

    /**
     * Generates the uri used to access the user pages.
     *
     * @param personId optional person id
     * @return uri
     */
    public static String generateUri(Integer personId) {
        return personId != null ? BASE_URI + "/" + personId : BASE_URI;
    }

    /**
     * Create the model.
     *
     * @param user user
     * @return model
     */
    public UserModel generateUserModel(User user) {
        UserModel model = new UserModel();
        Person person = personDao.findPerson(user.getPersonId());
        model.setPersonId(user.getPersonId());
        model.setUserName(user.getUserName());
        model.setPassword(user.getPassword());
        model.setActive(user.isActive());
        model.setUri(generateUri(user.getPersonId()));
        model.setEditUri(generateUri(user.getPersonId()) + "/edit");
        model.setName(person.getSurname() + ", " + person.getForename());
        model.setVolunteerUri(VOLUNTEER_BASE_URI + "/" + user.getPersonId());
        return model;
    }
}
