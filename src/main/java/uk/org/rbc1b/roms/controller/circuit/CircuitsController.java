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
package uk.org.rbc1b.roms.controller.circuit;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.circuit.Circuit;
import uk.org.rbc1b.roms.db.circuit.CircuitDao;

/**
 * Access the circuit information.
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/circuits")
public class CircuitsController {
    private static final String BASE_CIRCUIT_URI = "/circuits";
    private CircuitDao circuitDao;
    private PersonDao personDao;

    /**
     * Generate the uri used to access the circuit pages.
     *
     * @param circuitId optional circuit id
     * @return uri
     */
    public String generateCircuitUri(Integer circuitId) {
        return circuitId != null ? BASE_CIRCUIT_URI + "/" + circuitId : BASE_CIRCUIT_URI;
    }

    /**
     * Display the list of circuits.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showCircuitList(ModelMap model) {

        model.addAttribute("circuits", circuitDao.findCircuits());

        return "circuits/list";
    }

    /**
     * Display a specified circuit.
     *
     * @param circuitId circuit id (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the circuit
     */
    @RequestMapping(value = "{circuitId}", method = RequestMethod.GET)
    public String showCircuit(@PathVariable Integer circuitId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Circuit circuit = circuitDao.findCircuit(circuitId);

        if (circuit == null) {
            throw new NoSuchRequestHandlingMethodException("No circuit #" + circuitId, this.getClass());
        }

        model.addAttribute("circuit", circuit);

        return "circuits/show";
    }

    /**
     * Display a specified circuit for editing.
     *
     * @param circuitId circuit id (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the circuit
     */
    @RequestMapping(value = "{circuitId}/edit", method = RequestMethod.GET)
    public String showEditCircuitForm(@PathVariable Integer circuitId, ModelMap model) throws NoSuchRequestHandlingMethodException {
        Circuit circuit = circuitDao.findCircuit(circuitId);

        if (circuit == null) {
            throw new NoSuchRequestHandlingMethodException("No circuit #" + circuitId, this.getClass());
        }
        CircuitForm form = new CircuitForm();
        form.setCircuitId(circuitId);
        form.setName(circuit.getName());
        form.setPersonId(circuit.getCircuitOverseer().getPersonId());
        form.setForename(circuit.getCircuitOverseer().getForename());
        form.setMiddleName(circuit.getCircuitOverseer().getMiddleName());
        form.setSurname(circuit.getCircuitOverseer().getSurname());
        form.setEmail(circuit.getCircuitOverseer().getEmail());
        if (circuit.getCircuitOverseer().getAddress() != null) {
            form.setStreet(circuit.getCircuitOverseer().getAddress().getStreet());
            form.setTown(circuit.getCircuitOverseer().getAddress().getTown());
            form.setCounty(circuit.getCircuitOverseer().getAddress().getCounty());
            form.setPostcode(circuit.getCircuitOverseer().getAddress().getPostcode());
        }
        form.setTelephone(circuit.getCircuitOverseer().getTelephone());
        form.setMobile(circuit.getCircuitOverseer().getMobile());

        model.addAttribute("circuitForm", form);
        model.addAttribute("submitUri", generateCircuitUri(circuitId));
        model.addAttribute("submitMethod", "PUT");

        return "circuits/edit";
    }

    /**
     * Display the form to create a new circuit.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateCircuitForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("circuitForm", new CircuitForm());
        model.addAttribute("submitUri", generateCircuitUri(null));
        model.addAttribute("submitMethod", "POST");

        return "circuits/edit";
    }

    /**
     * Create a new circuit.
     *
     * @param circuitForm form bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createCircuit(@Valid CircuitForm circuitForm) {

        Circuit circuit = new Circuit();
        if (circuitForm.getCircuitId() != null) {
            circuit.setCircuitId(circuitForm.getCircuitId());
        }
        circuit.setName(circuitForm.getName());

        Person circuitOverseer = createCircuitOverseer(circuitForm);
        circuit.setCircuitOverseer(circuitOverseer);

        circuitDao.createCircuit(circuit);

        return "redirect:/circuits" + circuit.getCircuitId();
    }

    /**
     * Update a circuit.
     *
     * @param circuitId existing circuit.
     * @param circuitForm form bean
     * @return view name
     */
    @RequestMapping(value = "{circuitId}", method = RequestMethod.PUT)
    public String updateCircuit(@PathVariable Integer circuitId, @Valid CircuitForm circuitForm) {

        Circuit circuit = new Circuit();
        circuit.setCircuitId(circuitId);
        circuit.setName(circuitForm.getName());

        Person circuitOverseer = createCircuitOverseer(circuitForm);
        circuit.setCircuitOverseer(circuitOverseer);

        circuitDao.updateCircuit(circuit);

        return "redirect:/circuits" + circuit.getCircuitId();
    }

    private Person createCircuitOverseer(CircuitForm circuitForm) {
        Person circuitOverseer;
        if (circuitForm.getPersonId() != null) {
            circuitOverseer = personDao.findPerson(circuitForm.getPersonId());
        } else {
            circuitOverseer = new Person();
        }

        circuitOverseer.setForename(circuitForm.getForename());
        circuitOverseer.setMiddleName(circuitForm.getMiddleName());
        circuitOverseer.setSurname(circuitForm.getSurname());
        circuitOverseer.setEmail(circuitForm.getEmail());

        Address address = new Address();
        address.setStreet(circuitForm.getStreet());
        address.setTown(circuitForm.getTown());
        address.setCounty(circuitForm.getCounty());
        address.setPostcode(circuitForm.getPostcode());
        circuitOverseer.setAddress(address);

        circuitOverseer.setTelephone(circuitForm.getTelephone());
        circuitOverseer.setMobile(circuitForm.getMobile());

        return circuitOverseer;
    }

    /**
     * @param circuitDao circuit dao
     */
    @Autowired
    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }

    /**
     * @param personDao person dao
     */
    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
