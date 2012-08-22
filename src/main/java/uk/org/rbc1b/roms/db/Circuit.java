package uk.org.rbc1b.roms.db;
// Generated Apr 12, 2012 2:36:09 PM by Hibernate Tools 3.2.1.GA

import java.util.Set;

/**
 * Circuit generated by hbm2java.
 */
public class Circuit implements java.io.Serializable {

    private String circuitName;
    private String circuitOverseer;
    private String telephone;
    private String mobile;
    private String email;
    private String costreet;
    private String cotown;
    private String cocounty;
    private String copostcode;
    private String comments;
    private Set<Congregation> congregations;

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getCircuitOverseer() {
        return this.circuitOverseer;
    }

    public void setCircuitOverseer(String circuitOverseer) {
        this.circuitOverseer = circuitOverseer;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCostreet() {
        return this.costreet;
    }

    public void setCostreet(String costreet) {
        this.costreet = costreet;
    }

    public String getCotown() {
        return this.cotown;
    }

    public void setCotown(String cotown) {
        this.cotown = cotown;
    }

    public String getCocounty() {
        return this.cocounty;
    }

    public void setCocounty(String cocounty) {
        this.cocounty = cocounty;
    }

    public String getCopostcode() {
        return this.copostcode;
    }

    public void setCopostcode(String copostcode) {
        this.copostcode = copostcode;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Congregation> getCongregations() {
        return this.congregations;
    }

    public void setCongregations(Set<Congregation> congregations) {
        this.congregations = congregations;
    }

    @Override
    public String toString() {
        return "Circuit{" + "circuitName=" + circuitName + '}';
    }
}
