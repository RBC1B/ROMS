/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

import uk.org.rbc1b.roms.db.DefaultAuditable;

/**
 * An aspect of a kingdom hall, e.g. car park, that may need fixing one day.
 * @author oliver.elder.esq
 */
public class KingdomHallFeature extends DefaultAuditable {

    private Integer kingdomHallFeatureId;
    private KingdomHall kingdomHall;
    private HallFeature hallFeature;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public HallFeature getHallFeature() {
        return hallFeature;
    }

    public void setHallFeature(HallFeature hallFeature) {
        this.hallFeature = hallFeature;
    }

    public KingdomHall getKingdomHall() {
        return kingdomHall;
    }

    public void setKingdomHall(KingdomHall kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public Integer getKingdomHallFeatureId() {
        return kingdomHallFeatureId;
    }

    public void setKingdomHallFeatureId(Integer kingdomHallFeatureId) {
        this.kingdomHallFeatureId = kingdomHallFeatureId;
    }

    @Override
    public String toString() {
        return "KingdomHallFeature{" + "kingdomHallFeatureId=" + kingdomHallFeatureId + '}';
    }
}
