/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.circuit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request form bean when creating/editing the circuit.
 *
 * @author oliver.elder.esq
 */
public class CircuitForm {

    @NotNull
    @Size(max = 50)
    private String name;
    @Size(max = 50)
    private String overseerName;
    @Size(max = 15)
    private String overseerTelephone;
    @Size(max = 15)
    private String overseerMobile;
    @Size(max = 50)
    private String overseerEmail;
    @Size(max = 50)
    private String overseerStreet;
    @Size(max = 50)
    private String overseerTown;
    @Size(max = 50)
    private String overseerCounty;
    @Size(max = 10)
    private String overseerPostcode;
    private String comments;

    /**
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return overseer county
     */
    public String getOverseerCounty() {
        return overseerCounty;
    }

    /**
     * @param overseerCounty overseer county
     */
    public void setOverseerCounty(String overseerCounty) {
        this.overseerCounty = overseerCounty;
    }

    /**
     * @return overseer email
     */
    public String getOverseerEmail() {
        return overseerEmail;
    }

    /**
     * @param overseerEmail overseer email
     */
    public void setOverseerEmail(String overseerEmail) {
        this.overseerEmail = overseerEmail;
    }

    /**
     * @return overseer mobile number
     */
    public String getOverseerMobile() {
        return overseerMobile;
    }

    /**
     * @param overseerMobile overseer mobile number
     */
    public void setOverseerMobile(String overseerMobile) {
        this.overseerMobile = overseerMobile;
    }

    /**
     * @return overseer name
     */
    public String getOverseerName() {
        return overseerName;
    }

    /**
     * @param overseerName overseer name
     */
    public void setOverseerName(String overseerName) {
        this.overseerName = overseerName;
    }

    /**
     * @return  overseer postcode
     */
    public String getOverseerPostcode() {
        return overseerPostcode;
    }

    /**
     * @param overseerPostcode overseer postcode
     */
    public void setOverseerPostcode(String overseerPostcode) {
        this.overseerPostcode = overseerPostcode;
    }

    /**
     * @return overseer street
     */
    public String getOverseerStreet() {
        return overseerStreet;
    }

    /**
     * @param overseerStreet overseer street
     */
    public void setOverseerStreet(String overseerStreet) {
        this.overseerStreet = overseerStreet;
    }

    /**
     * @return overseer telephone
     */
    public String getOverseerTelephone() {
        return overseerTelephone;
    }

    /**
     * @param overseerTelephone overseer telephone
     */
    public void setOverseerTelephone(String overseerTelephone) {
        this.overseerTelephone = overseerTelephone;
    }

    /**
     * @return overseer town
     */
    public String getOverseerTown() {
        return overseerTown;
    }

    /**
     * @param overseerTown overseer town
     */
    public void setOverseerTown(String overseerTown) {
        this.overseerTown = overseerTown;
    }
}
