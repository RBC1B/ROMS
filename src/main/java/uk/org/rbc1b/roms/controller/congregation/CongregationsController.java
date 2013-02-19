/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.controller.common.congregation.CongregationDao;
import uk.org.rbc1b.roms.db.Congregation;

/**
 * Handle congregation related requests.
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/congregations")
public class CongregationsController {

    @Autowired
    private CongregationDao congregationDao;

    /**
     * Search for a congregation by name.
     *
     * @param name partial name match
     * @return list of matching congregations
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    //@PreAuthorize - not clear who will not be allowed to access
    @Transactional(readOnly = true)
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
}
