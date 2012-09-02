/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

/**
 *
 * @author oliver.elder.esq
 */
public class HallFeature {

    private Integer hallFeatureId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHallFeatureId() {
        return hallFeatureId;
    }

    public void setHallFeatureId(Integer hallFeatureId) {
        this.hallFeatureId = hallFeatureId;
    }

    @Override
    public String toString() {
        return "HallFeature{" + "hallFeatureId=" + hallFeatureId + '}';
    }
}
