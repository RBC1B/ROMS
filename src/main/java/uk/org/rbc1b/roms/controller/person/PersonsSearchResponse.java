/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.List;
import uk.org.rbc1b.roms.db.Person;

/**
 * Root element for the person search response.
 *
 * @author oliver.elder.esq
 */
public class PersonsSearchResponse {

    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
