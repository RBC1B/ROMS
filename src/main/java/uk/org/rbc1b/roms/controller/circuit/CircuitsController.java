/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.circuit;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.Circuit;

/**
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/circuits")
public class CircuitsController {

    @Autowired
    private CircuitDao circuitDao;

    /**
     * @return model containing the list of circuits
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('Circuit', 'READ')")
    @Transactional(readOnly = true)
    public String handleList(ModelMap model) {

        model.addAttribute("circuits", circuitDao.findCircuits());

        return "circuits";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('Circuit', 'READ')")
    @Transactional(readOnly = true)
    public String handleCircuit(@PathVariable String name, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Circuit circuit = circuitDao.findCircuit(name);

        if (circuit == null) {
            throw new NoSuchRequestHandlingMethodException("No circuit with name [" + name + "]", this.getClass());
        }

        model.addAttribute("circuit", circuit);

        return "circuit";
    }

    /**
     * Display the form to create a new circuit
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('Circuit', 'ADD')")
    public String handleNewForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("circuit", new CircuitForm());

        return "circuitEdit";
    }

    /**
     * Create a new circuit
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('Circuit', 'ADD')")
    @Transactional
    public String handleNewSubmit(@Valid CircuitForm circuitForm) {

        Circuit circuit = new Circuit();
        circuit.setCircuitName(circuitForm.getName());
        circuit.setCircuitOverseer(circuitForm.getOverseerName());
        circuit.setCocounty(circuitForm.getOverseerCounty());
        circuit.setComments(circuitForm.getComments());
        circuit.setCopostcode(circuitForm.getOverseerPostcode());
        circuit.setCostreet(circuitForm.getOverseerStreet());
        circuit.setCotown(circuitForm.getOverseerTelephone());
        circuit.setEmail(circuitForm.getOverseerEmail());
        circuit.setMobile(circuitForm.getOverseerMobile());
        circuit.setTelephone(circuitForm.getOverseerTelephone());

        //circuit.setCongregations(); - no congregations initially created

        circuitDao.createCircuit(circuit);

        return "redirect:circuits/" + circuitForm.getName();
    }

    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }
}
