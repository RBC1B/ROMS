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
package uk.org.rbc1b.roms.db.project;

import uk.org.rbc1b.roms.db.Person;

/**
 *
 * @author ramindursingh
 */
public class ProjectAvailability {

    private Integer projectAvailabilityId;
    private ProjectDepartmentSession projectDepartmentSession;
    private Person person;
    private boolean emailSent;
    private boolean personResponded;
    private boolean overseerConfirmed;
    private boolean confirmationEmail;
    private AvailabilityStatus availabilityStatus;
    private boolean transportRequired;
    private boolean offerTransport;
    private boolean accomodationRequired;
    private java.sql.Date updateTime;
    private Integer updatedBy;

    /**
     * @return the projectAvailabilityId
     */
    public Integer getProjectAvailabilityId() {
        return projectAvailabilityId;
    }

    /**
     * @param projectAvailabilityId the projectAvailabilityId to set
     */
    public void setProjectAvailabilityId(Integer projectAvailabilityId) {
        this.projectAvailabilityId = projectAvailabilityId;
    }

    /**
     * @return the projectDepartmentSession
     */
    public ProjectDepartmentSession getProjectDepartmentSession() {
        return projectDepartmentSession;
    }

    /**
     * @param projectDepartmentSession the projectDepartmentSession to set
     */
    public void setProjectDepartmentSession(ProjectDepartmentSession projectDepartmentSession) {
        this.projectDepartmentSession = projectDepartmentSession;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
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
     * @return the confirmationEmail
     */
    public boolean isConfirmationEmail() {
        return confirmationEmail;
    }

    /**
     * @param confirmationEmail the confirmationEmail to set
     */
    public void setConfirmationEmail(boolean confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
    }

    /**
     * @return the availabilityStatus
     */
    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     * @param availabilityStatus the availabilityStatus to set
     */
    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
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
     * @return the accomodationRequired
     */
    public boolean isAccomodationRequired() {
        return accomodationRequired;
    }

    /**
     * @param accomodationRequired the accomodationRequired to set
     */
    public void setAccomodationRequired(boolean accomodationRequired) {
        this.accomodationRequired = accomodationRequired;
    }

    /**
     * @return the updateTime
     */
    public java.sql.Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(java.sql.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the updatedBy
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
