/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.kingdomhall;

import org.springframework.beans.factory.annotation.Autowired;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.circuit.CircuitDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 *
 * @author ramonsingh
 */
public class KingdomHallModelFactory {

    private static final String BASE_URI = "/kingdomhall/";
    private CircuitDao circuitDao;
    private CongregationDao congregationDao;

    /**
     * generates a URI for kingdom hall.
     *
     * @param kingdomHallId the Kingdom Hall Id to set
     * @return String
     */
    public String generateUri(Integer kingdomHallId) {
        return kingdomHallId != null ? BASE_URI + kingdomHallId : BASE_URI;
    }

    /**
     * Generates a model for a list of Kingdom Halls.
     *
     * @param kingdomHall the Kingdom Hall to set
     * @return KingdomHallListModel
     */
    public KingdomHallListModel generateKingdomHallListModel(KingdomHall kingdomHall) {
        KingdomHallListModel model = new KingdomHallListModel();

        model.setKingdomHallId(kingdomHall.getKingdomHallId());
        model.setName(kingdomHall.getName());
        model.setTown(kingdomHall.getAddress().getTown());
        model.setPostCode(kingdomHall.getAddress().getPostcode());

        return model;
    }

    /**
     * @param circuitDao the circuitDao to set
     */
    @Autowired
    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }

    /**
     * @param congregationDao the congregationDao to set
     */
    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }
}
