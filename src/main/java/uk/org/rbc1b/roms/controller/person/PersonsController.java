/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableRequestData;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableResult;
import uk.org.rbc1b.roms.controller.common.model.PersonModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.volunteer.VolunteersController;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.PersonSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;

/**
 * Control access to the underlying person data.
 *
 * @author oliver
 */
@Controller
@RequestMapping("/persons")
public class PersonsController {

    private PersonDao personDao;
    private VolunteerDao volunteerDao;
    private CongregationDao congregationDao;
    private PersonModelFactory personModelFactory;

    /**
     * Display the list of persons.
     *
     * @param model mvc model
     * @param searchCriteria search criteria passed in the form
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    public String handleList(ModelMap model, PersonSearchCriteria searchCriteria) {

        List<Person> persons = personDao.findPersons(searchCriteria);
        List<PersonModel> modelList = new ArrayList<PersonModel>(persons.size());
        for (Person person : persons) {
            modelList.add(personModelFactory.generatePersonModel(person));
        }

        model.addAttribute("persons", modelList);

        return "persons/list";
    }

    /**
     * Display the list of persons.
     *
     * @param requestData data tables request data
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AjaxDataTableResult handleDatatableAjaxList(AjaxDataTableRequestData requestData) {

        PersonSearchCriteria searchCriteria = new PersonSearchCriteria();
        searchCriteria.setSearch(requestData.getSearch());
        searchCriteria.setSortValue(requestData.getSortValue());
        searchCriteria.setSortDirection(requestData.getSortDirection());
        searchCriteria.setStartIndex(requestData.getDisplayStart());
        searchCriteria.setMaxResults(requestData.getDisplayLength());

        AjaxDataTableResult<PersonModel> result = new AjaxDataTableResult<PersonModel>();
        result.setEcho(requestData.getEcho());

        int totalResults = personDao.findPersonsCount(searchCriteria);
        result.setTotalRecords(totalResults);
        if (totalResults > 0) {
            List<Person> persons = personDao.findPersons(searchCriteria);
            List<PersonModel> modelList = new ArrayList<PersonModel>(persons.size());
            for (Person person : persons) {
                modelList.add(personModelFactory.generatePersonModel(person));
            }
            result.setRecords(modelList);
            result.setTotalDisplayRecords(modelList.size());
        }

        return result;
    }

    /**
     * @param personId person primary key
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the id is found
     */
    @RequestMapping(value = "{personId}", method = RequestMethod.GET)
    public String handlePerson(@PathVariable Integer personId, ModelMap model) throws NoSuchRequestHandlingMethodException {
        Person person = fetchPerson(personId);

        if (volunteerDao.findVolunteer(person.getPersonId(), null) != null) {
            return "redirect:" + VolunteersController.generateUri(personId);
        }
        model.addAttribute("person", personModelFactory.generatePersonModel(person));

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
    public String handleEditForm(@PathVariable Integer personId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Person person = fetchPerson(personId);

        if (volunteerDao.findVolunteer(person.getPersonId(), null) != null) {
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
        model.addAttribute("submitUri", personModelFactory.generateUri(personId));

        return "persons/edit";

    }

    /**
     * Handle the person edit form submit.
     *
     * @param personId person primary key
     * @param form populate person form
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the id is found
     */
    @RequestMapping(value = "{personId}", method = RequestMethod.POST)
    private String handleEditSubmit(@PathVariable Integer personId, @Valid PersonForm form) throws NoSuchRequestHandlingMethodException {
        Person person = fetchPerson(personId);

        if (form.getStreet() != null || form.getTown() != null || form.getCounty() != null || form.getPostcode() != null) {
            Address address = new Address();
            address.setCounty(form.getCounty());
            address.setPostcode(form.getPostcode());
            address.setStreet(form.getStreet());
            address.setTown(form.getTown());
            person.setAddress(address);
        } else {
            person.setAddress(null);
        }

        person.setBirthDate(form.getBirthDate() != null ? new java.sql.Date(form.getBirthDate().toDateMidnight().getMillis()) : null);
        person.setComments(form.getComments());

        if (form.getCongregationId() == null) {
            person.setCongregation(null);
        } else if (person.getCongregation() == null || !person.getCongregation().getCongregationId().equals(form.getCongregationId())) {
            person.setCongregation(congregationDao.findCongregation(form.getCongregationId()));
        }

        person.setEmail(form.getEmail());
        person.setForename(form.getForename());
        person.setMiddleName(form.getMiddleName());
        person.setMobile(form.getMobile());
        person.setSurname(form.getSurname());
        person.setTelephone(form.getTelephone());
        person.setWorkPhone(form.getWorkPhone());

        personDao.savePerson(person);

        return "redirect:" + personModelFactory.generateUri(personId);
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
            result.setVolunteer(volunteerDao.findVolunteer(person.getPersonId(), null) != null);
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

    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }
}
