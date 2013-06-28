/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.kingdomhall.*;

/**
 *
 * @author ramindursingh
 */
@Component
public class CongregationModelFactory {

    private static final String BASE_URI = "/congregations/";
    private KingdomHallDao kingdomHallDao;

    /**
     * Generate the uri used to access congregation pages.
     *
     * @param congregationId optional congregation id
     * @return uri
     */
    public static String generateUri(Integer congregationId) {
        return congregationId != null ? BASE_URI + congregationId : BASE_URI;
    }

    /**
     * Generate the model used in the congregation list view.
     *
     * @param congregation congregation
     * @return model
     */
    public CongregationListModel generateCongregationListModel(Congregation congregation) {
        CongregationListModel model = new CongregationListModel();
        model.setCongregationId(congregation.getCongregationId());
        model.setName(congregation.getName());

        if (congregation.getKingdomHall() != null) {
            EntityModel kingdomHallModel = new EntityModel();
            kingdomHallModel.setId(congregation.getKingdomHall().getKingdomHallId());
            kingdomHallModel.setName(congregation.getKingdomHall().getName());
            model.setKingdomHall(kingdomHallModel);
        }

        return model;
    }

    /**
     * @param kingdomHallDao the kingdomHallDao to set
     */
    @Autowired
    public void setKingdomHallDao(KingdomHallDao kingdomHallDao) {
        this.kingdomHallDao = kingdomHallDao;
    }
}
