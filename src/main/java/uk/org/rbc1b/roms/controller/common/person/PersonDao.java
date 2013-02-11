/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.person;

import java.util.List;
import uk.org.rbc1b.roms.db.Person;

/**
 * Add/edit/delete/update the core person data.
 *
 * @author oliver
 */
public interface PersonDao {

    /**
     * Look up the person by the primary key.
     *
     * @param personId person id
     * @return Person, or null if no matching instance
     */
    Person findPerson(Integer personId);

    /**
     * Look up the list of people matching the criteria. <p>We look for exact match names
     *
     * @param forename person's first name
     * @param surname person's last name
     * @return list or people
     */
    List<Person> findPersons(String forename, String surname);

    /**
     * Save/update the person.
     *
     * @param person person to persist
     */
    void savePerson(Person person);
}
