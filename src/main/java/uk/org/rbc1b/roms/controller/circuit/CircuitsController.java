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
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.congregation.Circuit;

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
     * Display the list of circuits.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('CIRCUIT', 'READ')")
    @Transactional(readOnly = true)
    public String handleList(ModelMap model) {

        model.addAttribute("circuits", circuitDao.findCircuits());

        return "circuits/list";
    }

    /**
     * Display a specified circuit.
     *
     * @param circuitId circuit id (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the
     * circuit
     */
    @RequestMapping(value = "{circuitId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('CIRCUIT', 'READ')")
    @Transactional(readOnly = true)
    public String handleCircuit(@PathVariable Integer circuitId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Circuit circuit = circuitDao.findCircuit(circuitId);

        if (circuit == null) {
            throw new NoSuchRequestHandlingMethodException("No circuit #" + circuitId, this.getClass());
        }

        model.addAttribute("circuit", circuit);

        return "circuits/show";
    }

    /**
     * Display the form to create a new circuit.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('CIRCUIT', 'ADD')")
    public String handleNewForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("circuit", new CircuitForm());

        return "circuits/edit";
    }

    /**
     * Create a new circuit.
     *
     * @param circuitForm form bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('CIRCUIT', 'ADD')")
    @Transactional
    public String handleNewSubmit(@Valid CircuitForm circuitForm) {

        Circuit circuit = new Circuit();
        Person circuitOverseer = new Person();
        circuitOverseer.setForename(circuitForm.getCoForename());
        circuitOverseer.setSurname(circuitForm.getCoSurname());
        circuit.setCircuitOverseer(circuitOverseer);
        circuit.setName(circuitForm.getName());

        circuitDao.createCircuit(circuit);

        return "redirect:circuits/" + circuit.getCircuitId();
    }

    /**
     * @param circuitDao circuit dao
     */
    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }
}
