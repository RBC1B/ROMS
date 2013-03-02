/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.person.PersonDao;
import uk.org.rbc1b.roms.controller.congregation.CongregationsController;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.controller.volunteer.VolunteersController;
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
     * @param personId person primary key
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the id is found
     */
    @RequestMapping(value = "{personId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    public String handlePerson(@PathVariable Integer personId, ModelMap model) throws NoSuchRequestHandlingMethodException {
        Person person = fetchPerson(personId);

        if (volunteerDao.findVolunteer(person.getPersonId()) != null) {
            return "redirect:" + VolunteersController.generateUri(personId);
        }

        model.addAttribute("person", generatePersonModel(person));

        return "persons/show";
    }

    /**
     * Display the form to create a new person.
     *
     * @param personId person primary key
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the id is found
     */
    @RequestMapping(value = "{personId}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    @Transactional(readOnly = true)
    public String handleEditForm(@PathVariable Integer personId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Person person = fetchPerson(personId);

        if (volunteerDao.findVolunteer(person.getPersonId()) != null) {
            return "redirect:" + VolunteersController.generateUri(personId);
        }

        PersonForm form = new PersonForm();
        form.setPersonId(person.getPersonId());

        if (person.getBirthDate() != null) {
            form.setBirthDate(new DateTime(person.getBirthDate().getTime()));
        }
        form.setComments(person.getComments());

        if (person.getCongregation() != null) {
            form.setCongregationId(person.getCongregation().getCongregationId());
            form.setCongregationName(person.getCongregation().getName());
        }


        form.setSurname(person.getSurname());
        form.setForename(person.getForename());
        form.setMiddleName(person.getMiddleName());

        if (person.getAddress() != null) {
            form.setCounty(person.getAddress().getCounty());
            form.setPostcode(person.getAddress().getPostcode());
            form.setStreet(person.getAddress().getStreet());
            form.setTown(person.getAddress().getTown());
        }
        form.setEmail(person.getEmail());
        form.setTelephone(person.getTelephone());
        form.setMobile(person.getMobile());
        form.setWorkPhone(person.getWorkPhone());

        model.addAttribute("person", form);

        return "persons/edit";

    }

    private PersonModel generatePersonModel(Person person) {

        PersonModel model = new PersonModel();
        model.setAddress(person.getAddress());
        model.setBirthDate(person.getBirthDate());
        model.setComments(person.getComments());

        if (person.getCongregation() != null) {
            EntityModel congregation = new EntityModel();
            congregation.setId(person.getCongregation().getCongregationId());
            congregation.setName(person.getCongregation().getName());
            congregation.setUri(CongregationsController.generateUri(person.getCongregation().getCongregationId()));

            model.setCongregation(congregation);
        }

        model.setEmail(person.getEmail());
        model.setForename(person.getForename());
        model.setMiddleName(person.getMiddleName());
        model.setSurname(person.getSurname());
        model.setMobile(person.getMobile());
        model.setTelephone(person.getTelephone());
        model.setWorkPhone(person.getWorkPhone());

        return model;
    }

    /**
     * Note: There seems to be a bug in Spring 3.1 that causes the same uri with a different produces attribute throw an " Ambiguous handler methods mapped"
     * exception.
     *
     * @param personId person primary key
     * @return person object
     * @throws NoSuchRequestHandlingMethodException 404 response
     */
    @RequestMapping(value = "{personId}/reference", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    @ResponseBody
    public Person handleAjaxPerson(@PathVariable Integer personId) throws NoSuchRequestHandlingMethodException {
        Person person = fetchPerson(personId);

        return person;
    }

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

    private Person fetchPerson(Integer personId) throws NoSuchRequestHandlingMethodException {
        Person person = personDao.findPerson(personId);
        if (person == null) {
            throw new NoSuchRequestHandlingMethodException("No person with id [" + personId + "]", this.getClass());
        }
        return person;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }
}
