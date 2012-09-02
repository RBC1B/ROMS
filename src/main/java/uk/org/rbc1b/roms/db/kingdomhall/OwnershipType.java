/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

/**
 *
 * @author oliver.elder.esq
 */
public class OwnershipType {

    private Integer ownershipTypeId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwnershipTypeId() {
        return ownershipTypeId;
    }

    public void setOwnershipTypeId(Integer ownershipTypeId) {
        this.ownershipTypeId = ownershipTypeId;
    }

    @Override
    public String toString() {
        return "OwnershipType{" + "ownershipTypeId=" + ownershipTypeId + '}';
    }
}
