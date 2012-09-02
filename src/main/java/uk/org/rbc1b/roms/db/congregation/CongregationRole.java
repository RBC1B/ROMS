/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.congregation;

/**
 *
 * @author oliver.elder.esq
 */
public class CongregationRole {

    private Integer congregationRoleId;
    private String description;

    public Integer getCongregationRoleId() {
        return congregationRoleId;
    }

    public void setCongregationRoleId(Integer congregationRoleId) {
        this.congregationRoleId = congregationRoleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CongregationRole{" + "congregationRoleId=" + congregationRoleId + '}';
    }
}
