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
package uk.org.rbc1b.roms.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.PersonChange;
import uk.org.rbc1b.roms.db.PersonChangeDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import java.sql.Date;

/**
 *
 */
@Aspect
public class PersonAspect {

    private PersonDao personDao;
    private PersonChangeDao personChangeDao;

    /**
     * Pointcut definition for Person table.
     *
     * @param person the person to capture
     */
    @Pointcut("execution(* uk.org.rbc1b.roms.db.PersonDao.updatePerson(..)) && args(person,..)")
    public void personChange(Person person) {
    }

    /**
     * Pointcut definition for Volunteer update as it extends Person.
     *
     * @param volunteer the volunteer to capture
     */
    @Pointcut("execution(* uk.org.rbc1b.roms.db.volunteer.VolunteerDao.updateVolunteer(..)) && args(volunteer,..)")
    public void volunteerChange(Volunteer volunteer) {
    }

    /**
     * Captures updates to Person table.
     *
     * @param person the person to save
     *
     */
    @Before("personChange(person)")
    public void capturePersonChange(Person person) {
        this.saveChangesToPerson(person);
    }

    /**
     * Captures updates to Person table through volunteer update.
     *
     * @param volunteer the volunteer to save
     */
    @Before("volunteerChange(volunteer)")
    public void captureVolunteerChange(Volunteer volunteer) {
        Person person = new Person();
        person.setPersonId(volunteer.getPersonId());
        person.setForename(volunteer.getForename());
        person.setMiddleName(volunteer.getMiddleName());
        person.setSurname(volunteer.getSurname());
        person.setAddress(volunteer.getAddress());
        person.setEmail(volunteer.getEmail());
        person.setMobile(volunteer.getMobile());
        person.setTelephone(volunteer.getTelephone());
        person.setWorkPhone(volunteer.getWorkPhone());
        this.saveChangesToPerson(person);
    }

    /**
     * Saves a row in the PersonChange table.
     *
     * @param person the person to save to personChange table
     */
    public void saveChangesToPerson(Person person) {
        Person oldPerson = this.personChangeDao.getOldPerson(person.getPersonId(), person);
        PersonChange personChange = new PersonChange();
        personChange.setPersonId(person.getPersonId());
        personChange.setNewForename(person.getForename());
        personChange.setNewSurname(person.getSurname());
        personChange.setNewAddress(person.getAddress());
        personChange.setNewEmail(person.getEmail());
        personChange.setNewTelephone(person.getTelephone());
        personChange.setNewMobile(person.getMobile());
        personChange.setNewWorkPhone(person.getWorkPhone());
        personChange.setOldForename(oldPerson.getForename());
        personChange.setOldSurname(oldPerson.getSurname());
        personChange.setOldAddress(oldPerson.getAddress());
        personChange.setOldEmail(oldPerson.getEmail());
        personChange.setOldTelephone(oldPerson.getTelephone());
        personChange.setOldMobile(oldPerson.getMobile());
        personChange.setOldWorkPhone(oldPerson.getWorkPhone());
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        personChange.setChangeDate(new Date(calendar.getTimeInMillis()));
        personChange.setFormUpdated(false);
        this.personChangeDao.savePersonChange(personChange);
    }

    @Autowired
    public void setPersonChangeDao(PersonChangeDao personChangeDao) {
        this.personChangeDao = personChangeDao;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
