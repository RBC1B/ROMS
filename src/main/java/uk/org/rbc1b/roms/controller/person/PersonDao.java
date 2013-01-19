/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.List;
import uk.org.rbc1b.roms.db.Person;

/**
 * Add/edit/delete/update the core person data.
 *
 * @author oliver
 */
public interface PersonDao {

    /**
     * Look up the list of people matching the criteria.
     * <p>We look for exact match names
     * @param forename person's first name
     * @param surname  person's last name
     * @return list or people
     */
    List<Person> findPersons(String forename, String surname);
}
