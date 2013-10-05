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
package uk.org.rbc1b.roms.controller.congregation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationContact;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.circuit.CircuitDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;

/**
 * Handle congregation related requests.
 * @author oliver.elder.esq
 * @author Ramindur
 */
@Controller
@RequestMapping("/congregations")
public class CongregationsController {

    private CongregationDao congregationDao;
    private CongregationModelFactory congregationModelFactory;
    private KingdomHallDao kingdomHallDao;
    private CircuitDao circuitDao;
    private ReferenceDao referenceDao;
    private PersonDao personDao;

    /**
     * Displays the list of congregations.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showCongregationList(ModelMap model) {

        List<CongregationListModel> modelList = congregationModelFactory.generateCongregationListModels(congregationDao
                .findAllCongregations());

        model.addAttribute("congregations", modelList);
        model.addAttribute("newUri", CongregationModelFactory.generateUri(null) + "/new");
        return "congregations/list";
    }

    /**
     * Search for a congregation by name.
     * @param name partial name match
     * @return list of matching congregations
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<CongregationSearchResult> findCongregations(@RequestParam(value = "name", required = true) String name) {
        List<Congregation> congregations = congregationDao.findCongregations(name);
        List<CongregationSearchResult> results = new ArrayList<CongregationSearchResult>();
        if (!congregations.isEmpty()) {
            for (Congregation congregation : congregations) {
                CongregationSearchResult result = new CongregationSearchResult();
                result.setId(congregation.getCongregationId());
                result.setName(congregation.getName());
                results.add(result);
            }
        }
        return results;
    }

    /**
     * Displays a congregation.
     * @param congregationId congregation Id (primary key)
     * @param model mvc
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the congregation
     */
    @RequestMapping(value = "{congregationId}", method = RequestMethod.GET)
    public String showCongregation(@PathVariable Integer congregationId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Congregation congregation = congregationDao.findCongregation(congregationId);
        if (congregation == null) {
            throw new NoSuchRequestHandlingMethodException("No Congregation #" + congregationId, this.getClass());
        }
        model.addAttribute("congregation", congregationModelFactory.generateCongregationModel(congregation));
        return "congregations/show";
    }

    /**
     * Displays a congregation for editing.
     * @param congregationId congregation ID to edit
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find congregation
     */
    @RequestMapping(value = "{congregationId}/edit", method = RequestMethod.GET)
    public String showEditCongregationForm(@PathVariable Integer congregationId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Congregation congregation = congregationDao.findCongregation(congregationId);
        if (congregation == null) {
            throw new NoSuchRequestHandlingMethodException("No congregation #" + congregationId, this.getClass());
        }

        CongregationForm form = new CongregationForm();
        form.setName(congregation.getName());
        form.setNumber(congregation.getNumber());

        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(congregation.getKingdomHall().getKingdomHallId());

        form.setKingdomHallId(kingdomHall.getKingdomHallId());
        form.setKingdomHallName(kingdomHall.getName());
        form.setCircuitId(congregation.getCircuit().getCircuitId());
        form.setRbcRegionId(congregation.getRbcRegionId());
        form.setPublishers(congregation.getPublishers());
        form.setAttendance(congregation.getAttendance());
        form.setFunds(congregation.getFunds());
        form.setLoans(congregation.getLoans());
        form.setMonthlyIncome(congregation.getMonthlyIncome());
        form.setStrategy(congregation.getStrategy());

        if (congregation.getContacts() != null) {
            for (CongregationContact contact : congregation.getContacts()) {
                Person person = personDao.findPerson(contact.getPerson().getPersonId());

                if (contact.getCongregationRoleId() == CongregationContact.COORDINATOR_ROLE) {
                    form.setCoordinatorForename(person.getForename());
                    form.setCoordinatorSurname(person.getSurname());
                    form.setCoordinatorPersonId(person.getPersonId());
                } else if (contact.getCongregationRoleId() == CongregationContact.SECRETARY_ROLE) {
                    form.setSecretaryForename(person.getForename());
                    form.setSecretarySurname(person.getSurname());
                    form.setSecretaryPersonId(person.getPersonId());
                } else {
                    throw new IllegalStateException("Unknown congregation role: " + contact.getCongregationRoleId());
                }
            }
        }

        model.addAttribute("congregationForm", form);
        model.addAttribute("circuits", circuitDao.findCircuits());
        model.addAttribute("rbcRegions", referenceDao.findRbcRegionValues());
        model.addAttribute("rbcSubRegions", referenceDao.findRbcSubRegionValues());
        model.addAttribute("submitUri", CongregationModelFactory.generateUri(congregationId));
        model.addAttribute("submitMethod", "PUT");

        return "congregations/edit";
    }

    /**
     * Displays the form to create a new skill.
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateCongregationForm(ModelMap model) {
        model.addAttribute("congregationForm", new CongregationForm());
        model.addAttribute("kingdomHalls", kingdomHallDao.findKingdomHalls());
        model.addAttribute("circuits", circuitDao.findCircuits());
        model.addAttribute("rbcRegions", referenceDao.findRbcRegionValues());
        model.addAttribute("rbcSubRegions", referenceDao.findRbcSubRegionValues());
        model.addAttribute("submitUri", CongregationModelFactory.generateUri(null));
        model.addAttribute("submitMethod", "POST");
        return "congregations/edit";
    }

    /**
     * Updates a congregation, creating new {@link Person} instances for newly created congregation contacts if
     * required.
     * @param congregationId existing congregation id
     * @param congregationForm congregationForm bean
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find congregation
     */
    @RequestMapping(value = "{congregationId}", method = RequestMethod.PUT)
    public String updateCongregation(@PathVariable Integer congregationId, @Valid CongregationForm congregationForm)
            throws NoSuchRequestHandlingMethodException {
        Congregation congregation = congregationDao.findCongregation(congregationId);
        if (congregation == null) {
            throw new NoSuchRequestHandlingMethodException("No congregation #" + congregationId, this.getClass());
        }

        populateCongregation(congregationForm, congregation);

        congregationDao.updateCongregation(congregation);
        return "redirect:" + CongregationModelFactory.generateUri(congregationId);
    }

    /**
     * Creates a congregation, creating new {@link Person} instances for newly created congregation contacts if
     * required.
     * @param congregationForm congregationForm bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createCongregation(@Valid CongregationForm congregationForm) {
        Congregation congregation = new Congregation();
        congregation.setContacts(new HashSet<CongregationContact>());

        populateCongregation(congregationForm, congregation);

        congregationDao.createCongregation(congregation);

        // make sure all the contact persons are linked to the new congregation
        for (CongregationContact contact : congregation.getContacts()) {
            if (contact.getPerson().getCongregation() == null) {
                Person person = contact.getPerson();
                person.setCongregation(congregation);
                personDao.updatePerson(person);
            }
        }

        return "redirect:" + CongregationModelFactory.generateUri(congregation.getCongregationId());
    }

    private void populateCongregation(CongregationForm congregationForm, Congregation congregation) {
        congregation.setAttendance(congregationForm.getAttendance());
        congregation.setFunds(congregationForm.getFunds());
        congregation.setLoans(congregationForm.getLoans());
        congregation.setMonthlyIncome(congregationForm.getMonthlyIncome());
        congregation.setName(congregationForm.getName());
        congregation.setNumber(congregationForm.getNumber());
        congregation.setPublishers(congregationForm.getPublishers());
        congregation.setRbcRegionId(congregationForm.getRbcRegionId());
        congregation.setRbcSubRegionId(congregationForm.getRbcSubRegionId());
        congregation.setStrategy(congregationForm.getStrategy());

        mergeContacts(congregation, congregationForm);

        if (congregationForm.getCircuitId() == null) {
            congregation.setCircuit(null);
        } else {
            congregation.setCircuit(circuitDao.findCircuit(congregationForm.getCircuitId()));
        }

        if (congregationForm.getKingdomHallId() == null) {
            congregation.setKingdomHall(null);
        } else {
            congregation.setKingdomHall(kingdomHallDao.findKingdomHall(congregationForm.getKingdomHallId()));
        }
    }

    private void mergeContacts(Congregation congregation, CongregationForm congregationForm) {
        mergeContact(congregation, CongregationContact.COORDINATOR_ROLE, congregationForm.getCoordinatorPersonId(),
                congregationForm.getCoordinatorForename(), congregationForm.getCoordinatorSurname());
        mergeContact(congregation, CongregationContact.SECRETARY_ROLE, congregationForm.getSecretaryPersonId(),
                congregationForm.getSecretaryForename(), congregationForm.getSecretarySurname());
    }

    /**
     * Merge an individual congregation contact.
     */
    private void mergeContact(Congregation congregation, Integer roleId, Integer personId, String forename,
            String surname) {
        if (personId == null && surname == null) {
            congregation.removeContact(roleId);
            return;
        }

        CongregationContact contact = congregation.findContact(roleId);
        if (contact == null) {
            Person person = fetchPerson(congregation, personId, forename, surname);
            if (person != null) {
                contact = new CongregationContact();
                contact.setCongregation(congregation);
                contact.setCongregationRoleId(roleId);
                contact.setPerson(person);
                congregation.getContacts().add(contact);
            }
        } else if (!contact.getPerson().getPersonId().equals(personId)) {
            Person person = fetchPerson(congregation, personId, forename, surname);
            if (person != null) {
                contact.setPerson(person);
            }
        }

    }

    private Person fetchPerson(Congregation congregation, Integer personId, String forename, String surname) {
        if (personId != null) {
            return personDao.findPerson(personId);
        }

        if (forename != null && surname != null) {
            Person person = new Person();
            person.setForename(forename);
            person.setSurname(surname);
            if (congregation.getCongregationId() != null) {
                person.setCongregation(congregation);
            }
            personDao.createPerson(person);
            return person;
        }
        return null;
    }

    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }

    @Autowired
    public void setCongregationModelFactory(CongregationModelFactory congregationModelFactory) {
        this.congregationModelFactory = congregationModelFactory;
    }

    @Autowired
    public void setKingdomHallDao(KingdomHallDao kingdomHallDao) {
        this.kingdomHallDao = kingdomHallDao;
    }

    @Autowired
    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

}
