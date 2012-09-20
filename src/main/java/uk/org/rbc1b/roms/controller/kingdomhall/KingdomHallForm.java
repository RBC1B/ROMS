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

    public String getDrawings() {
        return drawings;
    }

    public void setDrawings(String drawings) {
        this.drawings = drawings;
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

    public Integer getOwnershipTypeId() {
        return ownershipTypeId;
    }

    public void setOwnershipTypeId(Integer ownershipTypeId) {
        this.ownershipTypeId = ownershipTypeId;
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

    public Integer getTitleHolderCongregationId() {
        return titleHolderCongregationId;
    }

    public void setTitleHolderCongregationId(Integer titleHolderCongregationId) {
        this.titleHolderCongregationId = titleHolderCongregationId;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
