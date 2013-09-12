package uk.org.rbc1b.roms.controller.volunteer.experience;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableRequestData;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableResult;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTrade;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTradeSearchCriteria;

/**
 * Control the display of the volunteer trades (experience) list.
 */
@Controller
@RequestMapping("/volunteer-experience")
public class VolunteerExperienceController {
    private VolunteerDao volunteerDao;
    private VolunteerTradeModelFactory volunteerTradeModelFactory;

    /**
     * Display a list of volunteer experience. Since the actual list is retrieved by ajax, there is no need to load
     * anything into the model.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showVolunteerExperienceList(ModelMap model) {
        return "volunteers/experience-list";
    }

    /**
     * Display the list of persons.
     * @param requestData data tables request data
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AjaxDataTableResult<VolunteerTradeModel> showDatatableAjaxVolunteerExperienceList(
            AjaxDataTableRequestData requestData) {

        VolunteerTradeSearchCriteria searchCriteria = new VolunteerTradeSearchCriteria();
        requestData.populateSearchCriteria(searchCriteria);

        AjaxDataTableResult<VolunteerTradeModel> result = new AjaxDataTableResult<VolunteerTradeModel>();
        result.setEcho(requestData.getEcho());

        int totalFilteredResults = volunteerDao.findVolunteerTradesCount(searchCriteria);
        if (searchCriteria.isFiltered()) {
            VolunteerTradeSearchCriteria noFilterCriteria = searchCriteria.clone();
            noFilterCriteria.clearFilter();
            result.setTotalRecords(volunteerDao.findVolunteerTradesCount(searchCriteria));
        } else {
            result.setTotalRecords(totalFilteredResults);
        }

        if (totalFilteredResults > 0) {
            List<VolunteerTrade> trades = volunteerDao.findVolunteerTrades(searchCriteria);
            List<VolunteerTradeModel> modelList = new ArrayList<VolunteerTradeModel>(trades.size());
            for (VolunteerTrade trade : trades) {
                modelList.add(volunteerTradeModelFactory.generateVolunteerTradeModel(trade));
            }
            result.setRecords(modelList);
            result.setTotalDisplayRecords(totalFilteredResults);
        } else {
            result.setRecords(Collections.<VolunteerTradeModel>emptyList());
        }

        return result;
    }

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @Autowired
    public void setVolunteerTradeModelFactory(VolunteerTradeModelFactory volunteerTradeModelFactory) {
        this.volunteerTradeModelFactory = volunteerTradeModelFactory;
    }

}
