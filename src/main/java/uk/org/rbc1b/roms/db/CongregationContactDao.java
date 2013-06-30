/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Looks up congregation contacts.
 *
 * @author ramindursingh
 */
public interface CongregationContactDao {

    /**
     * Gets congregations contacts.
     *
     * @param congregation contact details
     * @return list of contacts
     */
    @Transactional(readOnly = true)
    List<Person> findCongregationContacts(Congregation congregation);
}
