/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 *
 * @author ramindursingh
 */
public class CongregationListModel {

    private Integer congregationId;
    private String uri;
    private String name;
    private EntityModel congregation;
    private EntityModel kingdomHall;

    /**
     * @return the congregationId
     */
    public Integer getCongregationId() {
        return congregationId;
    }

    /**
     * @param congregationId the congregationId to set
     */
    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the congregation
     */
    public EntityModel getCongregation() {
        return congregation;
    }

    /**
     * @param congregation the congregation to set
     */
    public void setCongregation(EntityModel congregation) {
        this.congregation = congregation;
    }

    /**
     * @return the kingdomHall
     */
    public EntityModel getKingdomHall() {
        return kingdomHall;
    }

    /**
     * @param kingdomHall the kingdomHall to set
     */
    public void setKingdomHall(EntityModel kingdomHall) {
        this.kingdomHall = kingdomHall;
    }
}
