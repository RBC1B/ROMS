/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import uk.org.rbc1b.roms.db.Person;

/**
 * POJO to contain the volunteer edit form data.
 *
 * @author oliver
 */
public class VolunteerForm {

    private Person person = new Person();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
