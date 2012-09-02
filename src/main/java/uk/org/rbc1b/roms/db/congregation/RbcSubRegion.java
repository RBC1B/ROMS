/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.congregation;

/**
 *
 * @author oliver.elder.esq
 */
public class RbcSubRegion {

    private Integer rbcSubRegionId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRbcSubRegionId() {
        return rbcSubRegionId;
    }

    public void setRbcSubRegionId(Integer rbcSubRegionId) {
        this.rbcSubRegionId = rbcSubRegionId;
    }

    @Override
    public String toString() {
        return "RbcSubRegion{" + "rbcSubRegionId=" + rbcSubRegionId + '}';
    }
}
