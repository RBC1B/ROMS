/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class RbcStatus {

    private Integer rbcStatusId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRbcStatusId() {
        return rbcStatusId;
    }

    public void setRbcStatusId(Integer rbcStatusId) {
        this.rbcStatusId = rbcStatusId;
    }

    @Override
    public String toString() {
        return "RbcStatus{" + "rbcStatusId=" + rbcStatusId + '}';
    }
}
