/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.project;

/**
 *
 * @author oliver.elder.esq
 */
public class WorkFeature {

    private Integer workFeatureId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWorkFeatureId() {
        return workFeatureId;
    }

    public void setWorkFeatureId(Integer workFeatureId) {
        this.workFeatureId = workFeatureId;
    }

    @Override
    public String toString() {
        return "WorkFeature{" + "workFeatureId=" + workFeatureId + '}';
    }
}
