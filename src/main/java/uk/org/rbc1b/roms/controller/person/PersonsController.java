/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * Person search. Pass in a candidate, match this against the user
     * first/last name and return the person object in JSON format
     *
     * @param query person match lookup
     * @return model containing the list of qualifications
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, headers = "Accept=application/json")
    //@PreAuthorize - not clear who will not be allowed to access
    @Transactional(readOnly = true)
    public List<Person> handleList(@PathVariable String query) {
        return personDao.findPersons(query);
    }
}
