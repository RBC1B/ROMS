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
import uk.org.rbc1b.roms.controller.congregation.CongregationModelFactory;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.CongregationSearchCriteria;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;

/**
 * Controller for the kingdom hall related pages.
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/kingdom-halls")
public class KingdomHallsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHandlerExceptionResolver.class);
    private KingdomHallDao kingdomHallDao;
    private KingdomHallModelFactory kingdomHallModelFactory;
    private ReferenceDao referenceDao;
    private CongregationDao congregationDao;
    private CongregationModelFactory congregationModelFactory;

    /**
     * Display the list of kingdom halls.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    public String showKingdomHallList(ModelMap model) {
        model.addAttribute("kingdomHalls",
                kingdomHallModelFactory.generateKingdomHallListModels(kingdomHallDao.findKingdomHalls()));
        model.addAttribute("newUri", KingdomHallModelFactory.generateUri(null) + "/new");

        return "kingdom-halls/list";
    }

    /**
     * Display a specified kingdom hall.
     *
     * @param kingdomHallId kingdom hall id (primary key)
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the
     * kingdom hall
     */
    @RequestMapping(value = "{kingdomHallId}", method = RequestMethod.GET)
    public String showKingdomHall(@PathVariable Integer kingdomHallId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(kingdomHallId);

        if (kingdomHall == null) {
            throw new NoSuchRequestHandlingMethodException("No kingdom hall #" + kingdomHallId, this.getClass());
        }

        model.addAttribute("kingdomHall", kingdomHallModelFactory.generateKingdomHallModel(kingdomHall));
        model.addAttribute("ownershipType",
                referenceDao.findKingdomHallOwnershipTypeValues().get(kingdomHall.getOwnershipTypeCode()));

        return "kingdom-halls/show";
    }

    /**
     * Show the Kingdom Hall Edit form.
     *
     * @param kingdomHallId kingdomHallId
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException if kingdom hall is not found
     */
    @RequestMapping(value = "{kingdomHallId}/edit", method = RequestMethod.GET)
    public String showEditKingdomHallForm(@PathVariable Integer kingdomHallId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(kingdomHallId);

        if (kingdomHall == null) {
            throw new NoSuchRequestHandlingMethodException("No kingdom hall #" + kingdomHallId, this.getClass());
        }

        // initialise the Kingdom Hall Form
        KingdomHallForm kingdomHallForm = new KingdomHallForm();

        kingdomHallForm.setName(kingdomHall.getName());

        if (kingdomHall.getAddress() != null) {
            kingdomHallForm.setStreet(kingdomHall.getAddress().getStreet());
            kingdomHallForm.setTown(kingdomHall.getAddress().getTown());
            kingdomHallForm.setCounty(kingdomHall.getAddress().getCounty());
            kingdomHallForm.setPostcode(kingdomHall.getAddress().getPostcode());
        }

        if (kingdomHall.getTitleHolder() != null) {
            Congregation titleHoldingCongregation = congregationDao.findCongregation(kingdomHall.getTitleHolder()
                    .getCongregationId());
            kingdomHallForm.setTitleHoldingCongregationId(titleHoldingCongregation.getCongregationId());
            kingdomHallForm.setTitleHoldingCongregationName(titleHoldingCongregation.getName());
        }

        kingdomHallForm.setOwnershipTypeCode(kingdomHall.getOwnershipTypeCode());

        if (findCongregations(kingdomHall.getKingdomHallId()) != null) {
            kingdomHallForm.setCongregations(findCongregations(kingdomHall.getKingdomHallId()));
        }

        model.addAttribute("kingdomHallForm", kingdomHallForm);
        model.addAttribute("submitUri", KingdomHallModelFactory.generateUri(kingdomHallId));
        model.addAttribute("ownershipValues", referenceDao.findKingdomHallOwnershipTypeValues());
        model.addAttribute("submitMethod", "PUT");

        return "kingdom-halls/edit";

    }

    /**
     * Display the form to create a new circuit.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateKingdomHallForm(ModelMap model) {
        // initialise the form bean
        model.addAttribute("kingdomHallForm", new KingdomHallForm());

        return "kingdom-halls/edit";
    }

    /**
     * Helper method to find a list of congregations that meet at a hall.
     *
     * @param kingdomHallId kingdom hall id
     * @return list of congregations
     */
    private List<Congregation> findCongregations(Integer kingdomHallId) {
        CongregationSearchCriteria searchCriteria = new CongregationSearchCriteria();
        searchCriteria.setKingdomHallId(kingdomHallId);

        List<Congregation> congregations = congregationDao.findCongregations(searchCriteria);

        return congregations;
    }

    /**
     * Search for a kingdom hall by name.
     *
     * @param name partial name match
     * @return list of matching kingdom halls
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<KingdomHallSearchResult> findCongregations(@RequestParam(value = "name", required = true) String name) {
        List<KingdomHall> kingdomHalls = kingdomHallDao.findKingdomHalls(name);

        List<KingdomHallSearchResult> results = new ArrayList<KingdomHallSearchResult>();
        if (!kingdomHalls.isEmpty()) {

            for (KingdomHall kingdomHall : kingdomHalls) {
                KingdomHallSearchResult result = new KingdomHallSearchResult();
                result.setId(kingdomHall.getKingdomHallId());
                result.setName(kingdomHall.getName());
                results.add(result);
            }
        }
        return results;
    }

    /**
     * Create a new kingdom hall.
     *
     * @param kingdomHallForm form bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createKingdomHall(@Valid KingdomHallModel kingdomHallForm) {
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

        return "redirect:" + KingdomHallModelFactory.generateUri(kingdomHall.getKingdomHallId());
    }

    /**
     * Display the page list of kingdom halls, returning JSON.
     *
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

    private List<KingdomHallListModel> createKingdomHallListModels(List<KingdomHall> halls) {
        if (CollectionUtils.isEmpty(halls)) {
            return Collections.emptyList();
        }
        List<KingdomHallListModel> modelList = new ArrayList<KingdomHallListModel>(halls.size());
        for (KingdomHall hall : halls) {
            KingdomHallListModel model = new KingdomHallListModel();
            model.setKingdomHallId(hall.getKingdomHallId());
            model.setName(hall.getName());
            model.setPostcode(hall.getAddress().getPostcode());
            model.setTown(hall.getAddress().getTown());
            model.setUri(KingdomHallModelFactory.generateUri(hall.getKingdomHallId()));
            model.setEditUri(KingdomHallModelFactory.generateUri(hall.getKingdomHallId()) + "/edit");

            modelList.add(model);
        }
        return modelList;
    }

    /**
     * Deletes a Kingdom Hall.
     *
     * @param request http servlet request
     * @param model spring mvc model
     * @return mvc redirect
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * Kingdom Hall
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteKingdomHall(HttpServletRequest request, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
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
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    @Autowired
    public void setKingdomHallModelFactory(KingdomHallModelFactory kingdomHallModelFactory) {
        this.kingdomHallModelFactory = kingdomHallModelFactory;
    }
}
