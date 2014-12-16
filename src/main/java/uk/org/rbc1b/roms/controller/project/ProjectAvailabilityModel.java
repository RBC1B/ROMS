/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.controller.project;

/**
 *
 * @author ramindursingh
 */
public class ProjectAvailabilityModel {

    private Integer projectDepartmentSessionId;
    private Integer personId;
    private String personName;
    private String address;
    private boolean invited;
    private boolean emailSent;
    private boolean personResponded;
    private boolean overseerConfirmed;
    private boolean transportRequired;
    private boolean offerTransport;
    private boolean accommodationRequired;

    /**
     * @return the projectDepartmentSessionId
     */
    public Integer getProjectDepartmentSessionId() {
        return projectDepartmentSessionId;
    }

    /**
     * @param projectDepartmentSessionId the projectDepartmentSessionId to set
     */
    public void setProjectDepartmentSessionId(Integer projectDepartmentSessionId) {
        this.projectDepartmentSessionId = projectDepartmentSessionId;
    }

    /**
     * @return the personId
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * @return the personName
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * @param personName the personName to set
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the invited
     */
    public boolean isInvited() {
        return invited;
    }

    /**
     * @param invited the invited to set
     */
    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    /**
     * @return the emailSent
     */
    public boolean isEmailSent() {
        return emailSent;
    }

    /**
     * @param emailSent the emailSent to set
     */
    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    /**
     * @return the personResponded
     */
    public boolean isPersonResponded() {
        return personResponded;
    }

    /**
     * @param personResponded the personResponded to set
     */
    public void setPersonResponded(boolean personResponded) {
        this.personResponded = personResponded;
    }

    /**
     * @return the overseerConfirmed
     */
    public boolean isOverseerConfirmed() {
        return overseerConfirmed;
    }

    /**
     * @param overseerConfirmed the overseerConfirmed to set
     */
    public void setOverseerConfirmed(boolean overseerConfirmed) {
        this.overseerConfirmed = overseerConfirmed;
    }

    /**
     * @return the transportRequired
     */
    public boolean isTransportRequired() {
        return transportRequired;
    }

    /**
     * @param transportRequired the transportRequired to set
     */
    public void setTransportRequired(boolean transportRequired) {
        this.transportRequired = transportRequired;
    }

    /**
     * @return the offerTransport
     */
    public boolean isOfferTransport() {
        return offerTransport;
    }

    /**
     * @param offerTransport the offerTransport to set
     */
    public void setOfferTransport(boolean offerTransport) {
        this.offerTransport = offerTransport;
    }

    /**
     * @return the accommodationRequired
     */
    public boolean isAccommodationRequired() {
        return accommodationRequired;
    }

    /**
     * @param accommodationRequired the accommodationRequired to set
     */
    public void setAccommodationRequired(boolean accommodationRequired) {
        this.accommodationRequired = accommodationRequired;
    }
}
