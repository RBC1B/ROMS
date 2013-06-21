/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.kingdomhall;

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
    private Integer ownershipTypeId;
    private String drawings;
    private Integer titleHolderCongregationId;

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
     * @return the drawings
     */
    public String getDrawings() {
        return drawings;
    }

    /**
     * @param drawings the drawings to set
     */
    public void setDrawings(String drawings) {
        this.drawings = drawings;
    }

    /**
     * @return the titleHolderCongregationId
     */
    public Integer getTitleHolderCongregationId() {
        return titleHolderCongregationId;
    }

    /**
     * @param titleHolderCongregationId the titleHolderCongregationId to set
     */
    public void setTitleHolderCongregationId(Integer titleHolderCongregationId) {
        this.titleHolderCongregationId = titleHolderCongregationId;
    }
}
