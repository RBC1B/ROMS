/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.org.rbc1b.roms.dao.circuit.CircuitDao;
import uk.org.rbc1b.roms.db.Circuit;

/**
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/circuits/")
public class CircuitsController {

    @Autowired
    private CircuitDao circuitDao;

    /**
     * @return model containing the list of circuits
     */
    @RequestMapping(method = RequestMethod.GET)
    @Transactional(readOnly=true)
    public ModelAndView handleList() {
        ModelAndView model = new ModelAndView("circuits");
        model.addObject("circuits", circuitDao.findCircuits());

        return model;
    }

    @RequestMapping(value = "{name}/", method = RequestMethod.GET)
    @Transactional(readOnly=true)
    public ModelAndView handleCircuit(@PathVariable String name) {
        ModelAndView model = new ModelAndView("circuit");

        Circuit circuit = circuitDao.findCircuit(name);

        model.addObject("circuit", circuit);

        return model;
    }

    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }
}
