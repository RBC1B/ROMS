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
package uk.org.rbc1b.roms.controller.kingdomhall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.LoggingHandlerExceptionResolver;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableResult;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;

/**
 * Controller for the kingdom hall related pages.
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/kingdom-halls")
public class KingdomHallsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHandlerExceptionResolver.class);
    private static final String BASE_URI = "/kingdom-halls/";
    private KingdomHallDao kingdomHallDao;

    /**
     * Generate the uri used to access the kingdom hall pages.
     * @param kingdomHallId optional kingdom hall id
     * @return uri
     */
    public static String generateUri(Integer kingdomHallId) {
        return kingdomHallId != null ? BASE_URI + kingdomHallId : BASE_URI;
    }

    /**
     * Display the list of kingdom halls.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    public String showKingdomHallList(ModelMap model) {
        model.addAttribute("kingdomHalls", createKingdomHallListModels(kingdomHallDao.findKingdomHalls()));

        return "kingdom-halls/list";
    }

    /**
     * Search for a kingdom hall by name.
     * @param name partial name match
     * @return list of matching kingdom halls
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public KingdomHallSearchResponse findCongregations(@RequestParam(value = "name", required = true) String name) {
        List<KingdomHall> kingdomHalls = kingdomHallDao.findKingdomHalls(name);

        KingdomHallSearchResponse response = new KingdomHallSearchResponse();
        if (!kingdomHalls.isEmpty()) {
            List<KingdomHallSearchResult> results = new ArrayList<KingdomHallSearchResult>();
            for (KingdomHall kingdomHall : kingdomHalls) {
                KingdomHallSearchResult result = new KingdomHallSearchResult();
                result.setId(kingdomHall.getKingdomHallId());
                result.setName(kingdomHall.getName());
                results.add(result);
            }
            response.setResults(results);
        }
        return response;
    }

    /**
     * Create a new kingdom hall.
     * @param kingdomHallForm form bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createKingdomHall(@Valid KingdomHallForm kingdomHallForm) {
        KingdomHall kingdomHall = new KingdomHall();
        if (kingdomHallForm.getKingdomHallId() != null) {
            kingdomHall.setKingdomHallId(kingdomHallForm.getKingdomHallId());
        }
        Address address = new Address();
        address.setCounty(kingdomHallForm.getCounty());
        address.setPostcode(kingdomHallForm.getPostcode());
        address.setStreet(kingdomHallForm.getStreet());
        address.setTown(kingdomHallForm.getTown());
        kingdomHall.setAddress(address);
        kingdomHall.setName(kingdomHallForm.getName());

        return "redirect:/kingdom-hall";
    }

    /**
     * Display the page list of kingdom halls, returning JSON.
     * @param echoId request identifier returned back to the caller
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AjaxDataTableResult<KingdomHallListModel> showDatatableAjaxKingdomHallList(
            @RequestParam(value = "sEcho") Integer echoId) {
        AjaxDataTableResult<KingdomHallListModel> result = new AjaxDataTableResult<KingdomHallListModel>();
        result.setRecords(createKingdomHallListModels(kingdomHallDao.findKingdomHalls()));
        result.setTotalDisplayRecords(result.getAaData().size());
        result.setTotalRecords(result.getAaData().size());
        result.setEcho(echoId);
        return result;
    }

    /**
     * Display a specified kingdom hall.
     * @param kingdomHallId kingdom hall id (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the kingdom hall
     */
    @RequestMapping(value = "{kingdomHallId}", method = RequestMethod.GET)
    public String showKingdomHall(@PathVariable Integer kingdomHallId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(kingdomHallId);

        if (kingdomHall == null) {
            throw new NoSuchRequestHandlingMethodException("No kingdom hall #" + kingdomHallId, this.getClass());
        }

        model.addAttribute("kingdomHall", kingdomHall);
        model.addAttribute("congregations", kingdomHallDao.findCongregations(kingdomHallId));

        return "kingdom-halls/show";
    }

    /**
     * Display the form to create a new circuit.
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateKingdomHallForm(ModelMap model) {
        // initialise the form bean
        model.addAttribute("kingdomHall", new KingdomHallForm());

        return "kingdom-halls/edit";
    }

    private List<KingdomHallListModel> createKingdomHallListModels(List<KingdomHall> halls) {
        if (CollectionUtils.isEmpty(halls)) {
            return Collections.emptyList();
        }
        List<KingdomHallListModel> modelList = new ArrayList<KingdomHallListModel>(halls.size());
        for (KingdomHall hall : halls) {
            KingdomHallListModel model = new KingdomHallListModel();
            model.setKingdomHallId(hall.getKingdomHallId());
            model.setName(hall.getName());
            model.setPostCode(hall.getAddress().getPostcode());
            model.setTown(hall.getAddress().getTown());
            model.setUri(generateUri(hall.getKingdomHallId()));
            model.setEditUri(generateUri(hall.getKingdomHallId()) + "/edit");

            modelList.add(model);
        }
        return modelList;
    }

    /**
     * Deletes a Kingdom Hall.
     * @param request http servlet request
     * @param model spring mvc model
     * @return mvc redirect
     * @throws NoSuchRequestHandlingMethodException on failure to find the Kingdom Hall
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteKingdomHall(HttpServletRequest request, ModelMap model) throws NoSuchRequestHandlingMethodException {
        Integer kingdomHallId;
        kingdomHallId = Integer.parseInt(request.getParameter("kingdomHallId"));
        KingdomHall kingdomHall = this.kingdomHallDao.findKingdomHall(kingdomHallId);
        if (kingdomHall == null) {
            throw new NoSuchRequestHandlingMethodException("No Kingdom Hall #" + kingdomHallId, this.getClass());
        } else {
            this.kingdomHallDao.deleteKingdomHall(kingdomHall);

            LOGGER.error("Deleted Kingdom Hall:" + kingdomHallId);
            return "redirect:/kingdom-halls";
        }
    }

    @Autowired
    public void setKingdomHallDao(KingdomHallDao kingdomHallDao) {
        this.kingdomHallDao = kingdomHallDao;
    }
}
