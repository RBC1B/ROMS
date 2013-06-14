/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

import uk.org.rbc1b.roms.db.DefaultAuditable;

/**
 *
 * @author ramonsingh
 */
public class OwnershipType extends DefaultAuditable {

    private Integer ownershipTypeId;
    private String name;

    /**
     * @return the ownershipTypeId
     */
    public Integer getOwnershipTypeId() {
        return ownershipTypeId;
    }

    /**
     * @param ownershipTypeId the ownershipTypeId to set
     */
    public void setOwnershipTypeId(Integer ownershipTypeId) {
        this.ownershipTypeId = ownershipTypeId;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
