/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;

/**
 * Handle congregation related requests.
 *
 * @author oliver.elder.esq
 * @author Ramindur
 */
@Controller
@RequestMapping("/congregations")
public class CongregationsController {

    private CongregationDao congregationDao;
    private CongregationModelFactory congregationModelFactory;
    private KingdomHallDao kingdomHallDao;

    /**
     * Displays the list of congregations.
     *
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
        return "congregations/list";
    }

    /**
     * Search for a congregation by name.
     *
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
     * @param congregationDao the congregationDao to set
     */
    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }

    /**
     * @param congregationModelFactory the congregationModelFactory to set
     */
    @Autowired
    public void setCongregationModelFactory(CongregationModelFactory congregationModelFactory) {
        this.congregationModelFactory = congregationModelFactory;
    }

    /**
     * @param kingdomHallDao the kingdomHallDao to set
     */
    @Autowired
    public void setKingdomHallDao(KingdomHallDao kingdomHallDao) {
        this.kingdomHallDao = kingdomHallDao;
    }
}
