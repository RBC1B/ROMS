/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.controller.common.PhoneNumberFormatter;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableRequestData;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableResult;
import uk.org.rbc1b.roms.controller.common.model.PersonModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.user.UserModelFactory;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerModelFactory;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.PersonSearchCriteria;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;

/**
 * Control access to the underlying person data.
 * @author oliver
 */
@Controller
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private CongregationDao congregationDao;
    @Autowired
    private PersonModelFactory personModelFactory;
    @Autowired
    private UserDao userDao;

    /**
     * Display the list of persons.
     * @param model mvc model
     * @param searchCriteria search criteria passed in the form
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    @PreAuthorize("hasPermission('PERSON','READ')")
    public String showPersonList(ModelMap model, PersonSearchCriteria searchCriteria) {

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
     * @param requestData data tables request data
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @PreAuthorize("hasPermission('PERSON','READ')")
    @ResponseBody
    public AjaxDataTableResult<PersonModel> showDatatableAjaxPersonList(AjaxDataTableRequestData requestData) {

        PersonSearchCriteria searchCriteria = new PersonSearchCriteria();
        requestData.populateSearchCriteria(searchCriteria);

        AjaxDataTableResult<PersonModel> result = new AjaxDataTableResult<PersonModel>();
        result.setEcho(requestData.getEcho());

        int totalFilteredResults = personDao.findPersonsCount(searchCriteria);
        if (searchCriteria.isFiltered()) {
            PersonSearchCriteria noFilterCriteria = searchCriteria.clone();
            noFilterCriteria.clearFilter();
            result.setTotalRecords(personDao.findPersonsCount(searchCriteria));
        } else {
            result.setTotalRecords(totalFilteredResults);
        }

        if (totalFilteredResults > 0) {
            List<Person> persons = personDao.findPersons(searchCriteria);
            List<PersonModel> modelList = new ArrayList<PersonModel>(persons.size());
            for (Person person : persons) {
                modelList.add(personModelFactory.generatePersonModel(person));
            }
            result.setRecords(modelList);
            result.setTotalDisplayRecords(totalFilteredResults);
        } else {
            result.setRecords(Collections.<PersonModel>emptyList());
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
    @PreAuthorize("hasPermission('PERSON','READ')")
    public String showPerson(@PathVariable Integer personId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Person person = fetchPerson(personId);

        if (volunteerDao.findVolunteer(person.getPersonId(), null) != null) {
            return "redirect:" + VolunteerModelFactory.generateUri(personId);
        }
        model.addAttribute("person", personModelFactory.generatePersonModel(person));

        User user = userDao.findUser(personId);
        if (user != null) {
            model.addAttribute("userUri", UserModelFactory.generateUri(personId));
        } else {
            model.addAttribute("createUserUri", UserModelFactory.generateUri(null) + "/new?userId=" + personId);
        }

        return "persons/show";
    }

    /**
     * Display the form to create a new person.
     * @param personId person primary key
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the id is found
     */
    @RequestMapping(value = "{personId}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PERSON','EDIT') or hasPermission('VOLUNTEER','EDIT')")
    public String showEditPersonForm(@PathVariable Integer personId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        Person person = fetchPerson(personId);

        if (volunteerDao.findVolunteer(person.getPersonId(), null) != null) {
            return "redirect:" + VolunteerModelFactory.generateUri(personId);
        }

        PersonForm form = new PersonForm();
        form.setPersonId(person.getPersonId());

        if (person.getBirthDate() != null) {
            form.setBirthDate(new DateTime(person.getBirthDate().getTime()));
        }
        form.setComments(person.getComments());

        if (person.getCongregation() != null) {

            Congregation congregation = congregationDao.findCongregation(person.getCongregation().getCongregationId());

            form.setCongregationId(congregation.getCongregationId());
            form.setCongregationName(congregation.getName());
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
        model.addAttribute("submitUri", PersonModelFactory.generateUri(personId));

        return "persons/edit";

    }

    /**
     * Handle the person edit form submit.
     * @param personId person primary key
     * @param form populate person form
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the id is found
     */
    @RequestMapping(value = "{personId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('PERSON','EDIT') or hasPermission('VOLUNTEER','EDIT')")
    public String updatePerson(@PathVariable Integer personId, @Valid PersonForm form)
            throws NoSuchRequestHandlingMethodException {
        Person person = fetchPerson(personId);

        if (form.getStreet() != null || form.getTown() != null || form.getCounty() != null
                || form.getPostcode() != null) {
            Address address = new Address();
            address.setCounty(form.getCounty());
            address.setPostcode(form.getPostcode());
            address.setStreet(form.getStreet());
            address.setTown(form.getTown());
            person.setAddress(address);
        } else {
            person.setAddress(null);
        }

        person.setBirthDate(DataConverterUtil.toSqlDate(form.getBirthDate()));
        person.setComments(form.getComments());
        if (form.getCongregationId() != null) {
            person.setCongregation(congregationDao.findCongregation(form.getCongregationId()));
        } else {
            person.setCongregation(null);
        }
        person.setEmail(form.getEmail());
        person.setForename(form.getForename());
        person.setMiddleName(form.getMiddleName());
        person.setMobile(PhoneNumberFormatter.format(form.getMobile()));
        person.setSurname(form.getSurname());
        person.setTelephone(PhoneNumberFormatter.format(form.getTelephone()));
        person.setWorkPhone(PhoneNumberFormatter.format(form.getWorkPhone()));

        personDao.updatePerson(person);

        return "redirect:" + PersonModelFactory.generateUri(personId);
    }

    /**
     * Note: There seems to be a bug in Spring 3.1 that causes the same uri with a different produces attribute throw an
     * "Ambiguous handler methods mapped" exception.
     * @param personId person primary key
     * @return person object
     * @throws NoSuchRequestHandlingMethodException 404 response
     */
    @RequestMapping(value = "{personId}/reference", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasPermission('PERSON','READ') or hasPermission('VOLUNTEER','READ')")
    @ResponseBody
    public PersonModel showAjaxPerson(@PathVariable Integer personId) throws NoSuchRequestHandlingMethodException {
        Person person = fetchPerson(personId);

        return personModelFactory.generatePersonModel(person);
    }

    /**
     * Person search. Pass in a candidate, match this against the user first/last name and return the person object in
     * JSON format
     * @param forename person match lookup first name
     * @param surname person match lookup last name
     * @param checkVolunteer if true, confirm whether the person is a volunteer
     * @return model containing the list of people
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasPermission('PERSON','READ') or hasPermission('VOLUNTEER','READ')")
    @ResponseBody
    public List<PersonSearchResult> findPersons(@RequestParam(value = "forename", required = true) String forename,
            @RequestParam(value = "surname", required = true) String surname,
            @RequestParam(value = "checkVolunteer") boolean checkVolunteer) {
        List<Person> persons = personDao.findPersons(forename, surname);
        List<PersonSearchResult> results = new ArrayList<PersonSearchResult>();
        if (!persons.isEmpty()) {
            for (Person person : persons) {
                results.add(generatePersonSearchResult(person, checkVolunteer));
            }
        }
        return results;
    }

    private PersonSearchResult generatePersonSearchResult(Person person, boolean checkVolunteer) {

        PersonSearchResult result = new PersonSearchResult();
        result.setForename(person.getForename());
        result.setSurname(person.getSurname());
        result.setPersonId(person.getPersonId());
        result.setBirthDate(person.getBirthDate());

        if (person.getCongregation() != null) {
            Congregation congregation = congregationDao.findCongregation(person.getCongregation().getCongregationId());

            result.setCongregationName(congregation.getName());
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

}
