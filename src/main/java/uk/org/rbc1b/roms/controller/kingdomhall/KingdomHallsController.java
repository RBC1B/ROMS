/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.kingdomhall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.KingdomHall;

/**
 * Controller for the kingdom hall related pages.
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/kingdom-halls")
public class KingdomHallsController {

    @Autowired
    private KingdomHallDao kingdomHallDao;

    /**
     * Display the list of kingdom halls.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('KingdomHall', 'READ')")
    @Transactional(readOnly = true)
    public String handleList(ModelMap model) {

        model.addAttribute("kingdomHalls", kingdomHallDao.findKingdomHalls());

        return "kingdom-halls/list";
    }

    /**
     * Display a specified kingdom hall.
     * @param name kingdom hall name (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the kingdom hall
     */
    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('KingdomHall', 'READ')")
    @Transactional(readOnly = true)
    public String handleCircuit(@PathVariable String name, ModelMap model) throws NoSuchRequestHandlingMethodException {

        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(name);

        if (kingdomHall == null) {
            throw new NoSuchRequestHandlingMethodException("No kingdom hall with name [" + name + "]", this.getClass());
        }

        model.addAttribute("kingdomHall", kingdomHall);

        return "kingdom-halls/show";
    }
}
