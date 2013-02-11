/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import uk.org.rbc1b.roms.controller.common.person.PersonDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerDao;
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
    @Autowired
    private VolunteerDao volunteerDao;

    /**
     * Person search. Pass in a candidate, match this against the user first/last name and return the person object in JSON format
     *
     * @param forename person match lookup first name
     * @param surname person match lookup last name
     * @param checkVolunteer if true, confirm whether the person is a volunteer
     * @return model containing the list of people
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    //@PreAuthorize - not clear who will not be allowed to access
    @Transactional(readOnly = true)
    @ResponseBody
    public PersonsSearchResponse handleSearch(@RequestParam(value = "forename", required = true) String forename,
            @RequestParam(value = "surname", required = true) String surname, @RequestParam(value = "checkVolunteer") boolean checkVolunteer) {
        List<Person> persons = personDao.findPersons(forename, surname);

        // delete the lazy loaded sub collections to prevent the JSON marshaller blowing up
        PersonsSearchResponse response = new PersonsSearchResponse();
        if (!persons.isEmpty()) {
            List<PersonSearchResult> results = new ArrayList<PersonSearchResult>(persons.size());
            for (Person person : persons) {
                results.add(generatePersonSearchResult(person, checkVolunteer));
            }
            response.setResults(results);
        }
        return response;
    }

    private PersonSearchResult generatePersonSearchResult(Person person, boolean checkVolunteer) {

        PersonSearchResult result = new PersonSearchResult();
        result.setForename(person.getForename());
        result.setSurname(person.getSurname());
        result.setPersonId(person.getPersonId());
        result.setBirthDate(person.getBirthDate());

        if (person.getCongregation() != null) {
            result.setCongregationName(person.getCongregation().getName());
        }

        if (checkVolunteer) {
            result.setVolunteer(volunteerDao.findVolunteer(person.getPersonId()) != null);
        }
        return result;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }
}
