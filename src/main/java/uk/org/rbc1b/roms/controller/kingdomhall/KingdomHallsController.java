/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.kingdomhall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.AjaxDataTableResult;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * Controller for the kingdom hall related pages.
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/kingdom-halls")
public class KingdomHallsController {

    @Autowired
    private KingdomHallDao kingdomHallDao;

    /**
     * Display the list of kingdom halls.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    @PreAuthorize("hasPermission('KingdomHall', 'READ')")
    @Transactional(readOnly = true)
    public String handleList(ModelMap model) {

        model.addAttribute("kingdomHalls", createKingdomHallListModels(kingdomHallDao.findKingdomHalls()));

        return "kingdom-halls/list";
    }

    /**
     * Display the page list of kingdom halls, returning JSON.
     * @param echoId request identifier returned back to the caller
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @PreAuthorize("hasPermission('KingdomHall', 'READ')")
    @Transactional(readOnly = true)
    @ResponseBody
    public AjaxDataTableResult<KingdomHallListModel> handlePageList(@RequestParam(value = "sEcho") String echoId) {
        AjaxDataTableResult<KingdomHallListModel> result = new AjaxDataTableResult<KingdomHallListModel>();
        result.setAaData(createKingdomHallListModels(kingdomHallDao.findKingdomHalls()));
        result.setiTotalDisplayRecords(result.getAaData().size());
        result.setiTotalRecords(result.getAaData().size());
        result.setsEcho(echoId);
        return result;
    }

    /**
     * Display a specified kingdom hall.
     *
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

    private List<KingdomHallListModel> createKingdomHallListModels(List<KingdomHall> halls) {
        if (CollectionUtils.isEmpty(halls)) {
            return Collections.emptyList();
        }
        List<KingdomHallListModel> modelList = new ArrayList<KingdomHallListModel>(halls.size());
        for (KingdomHall hall : halls) {
            KingdomHallListModel model = new KingdomHallListModel();
            model.setName(hall.getName());
            model.setPostCode(hall.getAddress().getPostcode());
            model.setTown(hall.getAddress().getTown());
            modelList.add(model);
        }
        return modelList;
    }
}
