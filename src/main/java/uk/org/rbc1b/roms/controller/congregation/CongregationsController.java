/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Congregation;

/**
 * Handle congregation related requests.
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/congregations")
public class CongregationsController {

    private static final String BASE_URI = "/congregations/";
    private CongregationDao congregationDao;

    /**
     * Generate the uri used to access the congregation pages.
     *
     * @param congregationId optional congregation id
     * @return uri
     */
    public static String generateUri(Integer congregationId) {
        return congregationId != null ? BASE_URI + congregationId : BASE_URI;
    }

    /**
     * Search for a congregation by name.
     *
     * @param name partial name match
     * @return list of matching congregations
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public CongregationSearchResponse handleSearch(@RequestParam(value = "name", required = true) String name) {
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

    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }
}
