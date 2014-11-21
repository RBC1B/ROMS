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
public class AvailabilityForm {

    private Integer projectAvailabilityId;
    private Integer projectDepartmentSessionId;
    private String projectName;
    private String address;
    private String departmentName;
    private String volunteer;
    private String fromDate;
    private String toDate;
    private String endDate;
    private boolean transportRequired;
    private boolean offerTransport;
    private boolean accommodationRequired;
    private String hash;
    private String datetime;

    /**
     * Default constructor with certain values set.
     *
     */
    public AvailabilityForm() {
        transportRequired = false;
        offerTransport = false;
        accommodationRequired = false;
    }

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
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the volunteer
     */
    public String getVolunteer() {
        return volunteer;
    }

    /**
     * @param volunteer the volunteer to set
     */
    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the transportRequired
     */
    public boolean isTransportREquired() {
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
    public boolean isAccomodationRequired() {
        return accommodationRequired;
    }

    /**
     * @param accommodationRequired the accommodationRequired to set
     */
    public void setAccommodationRequired(boolean accommodationRequired) {
        this.accommodationRequired = accommodationRequired;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
