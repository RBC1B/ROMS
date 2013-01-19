/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author oliver.elder.esq
 */
public class RbcRegion {

    private Integer rbcRegionId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRbcRegionId() {
        return rbcRegionId;
    }

    public void setRbcRegionId(Integer rbcRegionId) {
        this.rbcRegionId = rbcRegionId;
    }

    @Override
    public String toString() {
        return "RbcRegion{" + "rbcRegionId=" + rbcRegionId + '}';
    }
}
