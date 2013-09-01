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
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.circuit.CircuitDao;
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

    /**
     * Displays the list of congregations.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showCongregationList(ModelMap model) {
        List<Congregation> congregations = congregationDao.findAllCongregations();
        List<CongregationListModel> modelList = new ArrayList<CongregationListModel>(congregations.size());
        for (Congregation congregation : congregations) {
            modelList.add(congregationModelFactory.generateCongregationListModel(congregation));
        }
        model.addAttribute("congregations", modelList);
        model.addAttribute("newUri", CongregationModelFactory.generateUri(null) + "new");
        return "congregations/list";
    }

    /**
     * Search for a congregation by name.
     * @param name partial name match
     * @return list of matching congregations
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public CongregationSearchResponse findCongregations(@RequestParam(value = "name", required = true) String name) {
        List<Congregation> congregations = congregationDao.findCongregations(name);

        CongregationSearchResponse response = new CongregationSearchResponse();
        if (!congregations.isEmpty()) {
            List<CongregationSearchResult> results = new ArrayList<CongregationSearchResult>();
            for (Congregation congregation : congregations) {
                CongregationSearchResult result = new CongregationSearchResult();
                result.setId(congregation.getCongregationId());
                result.setName(congregation.getName());
                results.add(result);
            }
            response.setResults(results);
        }
        return response;
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

        CongregationForm form = new CongregationForm(congregation);
        model.addAttribute("congregation", form);
        model.addAttribute("kingdomHalls", kingdomHallDao.findKingdomHalls());
        model.addAttribute("circuits", circuitDao.findCircuits());
        model.addAttribute("rbcRegions", referenceDao.findRbcRegionValues());
        model.addAttribute("rbcSubRegions", referenceDao.findRbcSubRegionValues());

        return "congregations/edit";
    }

    /**
     * Displays the form to create a new skill.
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateCongregationForm(ModelMap model) {
        model.addAttribute("congregation", new CongregationForm());
        model.addAttribute("kingdomHalls", kingdomHallDao.findKingdomHalls());
        model.addAttribute("circuits", circuitDao.findCircuits());
        model.addAttribute("rbcRegions", referenceDao.findRbcRegionValues());
        model.addAttribute("rbcSubRegions", referenceDao.findRbcSubRegionValues());

        return "congregations/edit";
    }

    /**
     * Creates a new congregation.
     * @param congregationForm congregationForm bean
     * @return view name
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createCongregation(@Valid CongregationForm congregationForm) {
        Congregation congregation = new Congregation();
        if (congregationForm.getCongregationId() != null) {
            congregation.setCongregationId(congregationForm.getCongregationId());
        }
        congregation.setName(congregationForm.getName());

        // MORE Work to DO HERE to set the congregation bean

        congregationDao.saveCongregation(congregation);
        return "redirect:congregations";
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
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

}
