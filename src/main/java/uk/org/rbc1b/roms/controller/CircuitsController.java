/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.dao.circuit.CircuitDao;
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
    @Transactional(readOnly=true)
    public String handleList(ModelMap model) {

        model.addAttribute("circuits", circuitDao.findCircuits());

        return "circuits";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('Circuit', 'READ')")
    @Transactional(readOnly=true)
    public String handleCircuit(@PathVariable String name, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Circuit circuit = circuitDao.findCircuit(name);

        if (circuit == null) {
            throw new NoSuchRequestHandlingMethodException("No circuit with name [" + name + "]", this.getClass());
        }

        model.addAttribute("circuit", circuit);

        return "circuit";
    }

    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }
}
