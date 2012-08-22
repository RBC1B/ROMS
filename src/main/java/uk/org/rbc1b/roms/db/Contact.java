/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.sql.Date;

/**
 *
 * @author ramindursingh
 */
public class Contact {

    private Long id;
    private String street;
    private String town;
    private String county;
    private String postcode;
    private String telephone;
    private String mobile;
    private String email;
    private String comments;
    private String status;
    private String congregation;
    private String jobdescrip;
    private String sblt;
    private Date induction;
    private String appointment;
    private String fulltime;
    private String availability;
    private String workphone;
    private String assignmentpending;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the congregation
     */
    public String getCongregation() {
        return congregation;
    }

    /**
     * @param congregation the congregation to set
     */
    public void setCongregation(String congregation) {
        this.congregation = congregation;
    }

    /**
     * @return the jobdescrip
     */
    public String getJobdescrip() {
        return jobdescrip;
    }

    /**
     * @param jobdescrip the jobdescrip to set
     */
    public void setJobdescrip(String jobdescrip) {
        this.jobdescrip = jobdescrip;
    }

    /**
     * @return the sblt
     */
    public String getSblt() {
        return sblt;
    }

    /**
     * @param sblt the sblt to set
     */
    public void setSblt(String sblt) {
        this.sblt = sblt;
    }

    /**
     * @return the induction
     */
    public Date getInduction() {
        return induction;
    }

    /**
     * @param induction the induction to set
     */
    public void setInduction(Date induction) {
        this.induction = induction;
    }

    /**
     * @return the appointment
     */
    public String getAppointment() {
        return appointment;
    }

    /**
     * @param appointment the appointment to set
     */
    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    /**
     * @return the fulltime
     */
    public String getFulltime() {
        return fulltime;
    }

    /**
     * @param fulltime the fulltime to set
     */
    public void setFulltime(String fulltime) {
        this.fulltime = fulltime;
    }

    /**
     * @return the availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * @return the workphone
     */
    public String getWorkphone() {
        return workphone;
    }

    /**
     * @param workphone the workphone to set
     */
    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    /**
     * @return the assignmentpending
     */
    public String getAssignmentpending() {
        return assignmentpending;
    }

    /**
     * @param assignmentpending the assignmentpending to set
     */
    public void setAssignmentpending(String assignmentpending) {
        this.assignmentpending = assignmentpending;
    }
}
