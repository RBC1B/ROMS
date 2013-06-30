/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.db.Congregation;

/**
 *
 * @author ramindursingh
 */
@Component
public class CongregationModelFactory {

    private static final String BASE_URI = "/congregations/";

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

        EntityModel kingdomHallModel = new EntityModel();
        if (congregation.getKingdomHall() != null) {
            kingdomHallModel.setId(congregation.getKingdomHall().getKingdomHallId());
            kingdomHallModel.setName(congregation.getKingdomHall().getName());
        }
        model.setKingdomHall(kingdomHallModel);

        EntityModel circuitModel = new EntityModel();
        if (congregation.getCircuit() != null) {
            circuitModel.setId(congregation.getCircuit().getCircuitId());
            circuitModel.setName(congregation.getCircuit().getName());
        }
        model.setCircuit(circuitModel);

        EntityModel rbcRegionModel = new EntityModel();
        if (congregation.getRbcRegion() != null) {
            rbcRegionModel.setId(congregation.getRbcRegion().getRbcRegionId());
            rbcRegionModel.setName(congregation.getRbcRegion().getName());
        }
        model.setRbcRegion(rbcRegionModel);

        EntityModel rbcSubRegionModel = new EntityModel();
        if (congregation.getRbcSubRegion() != null) {
            rbcSubRegionModel.setId(congregation.getRbcSubRegion().getRbcSubRegionId());
            rbcSubRegionModel.setName(congregation.getRbcSubRegion().getName());
        }
        model.setRbcSubRegion(rbcSubRegionModel);

        return model;
    }
}
