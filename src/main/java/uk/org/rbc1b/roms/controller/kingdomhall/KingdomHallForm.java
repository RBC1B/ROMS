/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.kingdomhall;

import uk.org.rbc1b.roms.db.Congregation;

/**
 * Form bean for Kingdom hall create and edit actions.
 *
 * @author oliver.elder.esq
 */
public class KingdomHallForm {

    private Integer kingdomHallId;
    private String name;
    private String street;
    private String town;
    private String county;
    private String postcode;
    private Congregation owningCongregation;
    private String ownershipTypeName;

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the ownershipTypeName
     */
    public String getOwnershipTypeName() {
        return ownershipTypeName;
    }

    /**
     * @param ownershipTypeName the ownershipTypeName to set
     */
    public void setOwnershipTypeName(String ownershipTypeName) {
        this.ownershipTypeName = ownershipTypeName;
    }

    /**
     * @return the owningCongregation
     */
    public Congregation getOwningCongregation() {
        return owningCongregation;
    }

    /**
     * @param owningCongregation the owningCongregation to set
     */
    public void setOwningCongregation(Congregation owningCongregation) {
        this.owningCongregation = owningCongregation;
    }
}
