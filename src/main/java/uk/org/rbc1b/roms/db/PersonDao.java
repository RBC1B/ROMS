/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

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
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Person findPerson(Integer personId);

    /**
     * Look up the list of people matching the criteria. <p>We look for exact match names
     *
     * @param forename person's first name
     * @param surname person's last name
     * @return list of people
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<Person> findPersons(String forename, String surname);

    /**
     * Look up the list of people matching the criteria. All criteria is optional.
     *
     * @param searchCriteria search criteria
     * @return list of people
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<Person> findPersons(PersonSearchCriteria searchCriteria);

    /**
     * Look up the number of people matching the criteria.
     *
     * @param searchCriteria search criteria
     * @return list of people
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    int findPersonsCount(PersonSearchCriteria searchCriteria);

    /**
     * Save/update the person.
     *
     * @param person person to persist
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    @Transactional
    void savePerson(Person person);
}
