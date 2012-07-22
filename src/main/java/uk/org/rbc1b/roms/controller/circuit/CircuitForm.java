/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.circuit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request form bean when creating/editing the circuit
 *
 * @author oliver.elder.esq
 */
public class CircuitForm {

    @NotNull
    @Size(max=50)
    private String name;
    @Size(max=50)
    private String overseerName;
    @Size(max=15)
    private String overseerTelephone;
    @Size(max=15)
    private String overseerMobile;
    @Size(max=50)
    private String overseerEmail;
    @Size(max=50)
    private String overseerStreet;
    @Size(max=50)
    private String overseerTown;
    @Size(max=50)
    private String overseerCounty;
    @Size(max=10)
    private String overseerPostcode;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverseerCounty() {
        return overseerCounty;
    }

    public void setOverseerCounty(String overseerCounty) {
        this.overseerCounty = overseerCounty;
    }

    public String getOverseerEmail() {
        return overseerEmail;
    }

    public void setOverseerEmail(String overseerEmail) {
        this.overseerEmail = overseerEmail;
    }

    public String getOverseerMobile() {
        return overseerMobile;
    }

    public void setOverseerMobile(String overseerMobile) {
        this.overseerMobile = overseerMobile;
    }

    public String getOverseerName() {
        return overseerName;
    }

    public void setOverseerName(String overseerName) {
        this.overseerName = overseerName;
    }

    public String getOverseerPostcode() {
        return overseerPostcode;
    }

    public void setOverseerPostcode(String overseerPostcode) {
        this.overseerPostcode = overseerPostcode;
    }

    public String getOverseerStreet() {
        return overseerStreet;
    }

    public void setOverseerStreet(String overseerStreet) {
        this.overseerStreet = overseerStreet;
    }

    public String getOverseerTelephone() {
        return overseerTelephone;
    }

    public void setOverseerTelephone(String overseerTelephone) {
        this.overseerTelephone = overseerTelephone;
    }

    public String getOverseerTown() {
        return overseerTown;
    }

    public void setOverseerTown(String overseerTown) {
        this.overseerTown = overseerTown;
    }
}
