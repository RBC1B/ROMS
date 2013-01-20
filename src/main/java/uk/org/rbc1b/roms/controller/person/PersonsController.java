/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.Person;

/**
 * Control access to the underlying person data.
 *
 * @author oliver
 */
@Controller
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    private PersonDao personDao;

    /**
     * Person search. Pass in a candidate, match this against the user first/last name and return the person object in JSON format
     *
     * @param forename person match lookup first name
     * @param surname person match lookup last name
     * @return model containing the list of people
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    //@PreAuthorize - not clear who will not be allowed to access
    @Transactional(readOnly = true)
    @ResponseBody
    public PersonsSearchResponse handleSearch(@RequestParam(value = "forename", required = true) String forename,
            @RequestParam(value = "surname", required = true) String surname) {
        List<Person> persons = personDao.findPersons(forename, surname);

        // delete the lazy loaded sub collections to prevent the JSON marshaller blowing up
        PersonsSearchResponse response = new PersonsSearchResponse();
        if (!persons.isEmpty()) {
            for (Person person : persons) {
                Congregation congregation = person.getCongregation();
                if (congregation == null) {
                    continue;
                }

                congregation.setCircuit(null);
                congregation.setContacts(null);
                congregation.setKingdomHall(null);
                congregation.setRbcRegion(null);
                congregation.setRbcSubRegion(null);
            }
            response.setPersons(persons);
        }
        return response;
    }
}
