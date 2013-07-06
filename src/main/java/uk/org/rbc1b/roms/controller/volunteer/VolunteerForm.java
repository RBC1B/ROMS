/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
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
package uk.org.rbc1b.roms.controller.volunteer;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import uk.org.rbc1b.roms.db.volunteer.VolunteerTrade;

/**
 * POJO to contain the volunteer edit form data.
 *
 * @author oliver
 */
public class VolunteerForm {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime baptismDate;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime birthDate;
    @NotNull
    private Integer congregationId;
    private String congregationName;
    private String county;
    private boolean elder;
    private String email;
    @NotNull
    @Size(min = 2)
    private String emergencyContactForename;
    @NotNull
    @Size(min = 2)
    private String emergencyContactSurname;
    private Integer emergencyContactPersonId;
    private String emergencyContactTelephone;
    private String emergencyContactMobile;
    private String emergencyContactCounty;
    @NotNull
    @Size(min = 2)
    private String emergencyContactStreet;
    @NotNull
    @Size(min = 2)
    private String emergencyContactTown;
    private String emergencyContactPostcode;
    @NotNull
    private Integer emergencyRelationshipId;
    @NotNull
    @Size(min = 2)
    private String forename;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime formDate;
    @NotNull
    @Size(min = 1, max = 1)
    private String gender;    // M or F
    private String middleName;
    private boolean ministerialServant;
    private String mobile;
    private boolean regularPioneer;
    private Integer personId;
    @NotNull
    @Size(min = 2)
    private String postcode;
    private String spouseForename;
    private String spouseSurname;
    private Integer spousePersonId;
    @NotNull
    @Size(min = 2)
    private String surname;
    @NotNull
    @Size(min = 2)
    private String street;
    private String telephone;
    @NotNull
    @Size(min = 2)
    private String town;
    private List<VolunteerTrade> trades = new ArrayList<VolunteerTrade>();
    private String workPhone;

    public DateTime getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(DateTime baptismDate) {
        this.baptismDate = baptismDate;
    }

    public boolean isElder() {
        return elder;
    }

    public void setElder(boolean elder) {
        this.elder = elder;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContactForename() {
        return emergencyContactForename;
    }

    public void setEmergencyContactForename(String emergencyContactForename) {
        this.emergencyContactForename = emergencyContactForename;
    }

    public String getEmergencyContactSurname() {
        return emergencyContactSurname;
    }

    public void setEmergencyContactSurname(String emergencyContactSurname) {
        this.emergencyContactSurname = emergencyContactSurname;
    }

    public Integer getEmergencyContactPersonId() {
        return emergencyContactPersonId;
    }

    public void setEmergencyContactPersonId(Integer emergencyContactPersonId) {
        this.emergencyContactPersonId = emergencyContactPersonId;
    }

    public String getEmergencyContactTelephone() {
        return emergencyContactTelephone;
    }

    public void setEmergencyContactTelephone(String emergencyContactTelephone) {
        this.emergencyContactTelephone = emergencyContactTelephone;
    }

    public String getEmergencyContactMobile() {
        return emergencyContactMobile;
    }

    public void setEmergencyContactMobile(String emergencyContactMobile) {
        this.emergencyContactMobile = emergencyContactMobile;
    }

    public String getEmergencyContactCounty() {
        return emergencyContactCounty;
    }

    public void setEmergencyContactCounty(String emergencyContactCounty) {
        this.emergencyContactCounty = emergencyContactCounty;
    }

    public String getEmergencyContactStreet() {
        return emergencyContactStreet;
    }

    public void setEmergencyContactStreet(String emergencyContactStreet) {
        this.emergencyContactStreet = emergencyContactStreet;
    }

    public String getEmergencyContactTown() {
        return emergencyContactTown;
    }

    public void setEmergencyContactTown(String emergencyContactTown) {
        this.emergencyContactTown = emergencyContactTown;
    }

    public String getEmergencyContactPostcode() {
        return emergencyContactPostcode;
    }

    public void setEmergencyContactPostcode(String emergencyContactPostcode) {
        this.emergencyContactPostcode = emergencyContactPostcode;
    }

    public Integer getEmergencyRelationshipId() {
        return emergencyRelationshipId;
    }

    public void setEmergencyRelationshipId(Integer emergencyRelationshipId) {
        this.emergencyRelationshipId = emergencyRelationshipId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isMinisterialServant() {
        return ministerialServant;
    }

    public void setMinisterialServant(boolean ministerialServant) {
        this.ministerialServant = ministerialServant;
    }

    public boolean isRegularPioneer() {
        return regularPioneer;
    }

    public void setRegularPioneer(boolean regularPioneer) {
        this.regularPioneer = regularPioneer;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
    }

    public String getCongregationName() {
        return congregationName;
    }

    public void setCongregationName(String congregationName) {
        this.congregationName = congregationName;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public DateTime getFormDate() {
        return formDate;
    }

    public void setFormDate(DateTime formDate) {
        this.formDate = formDate;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSpouseForename() {
        return spouseForename;
    }

    public void setSpouseForename(String spouseForename) {
        this.spouseForename = spouseForename;
    }

    public String getSpouseSurname() {
        return spouseSurname;
    }

    public void setSpouseSurname(String spouseSurname) {
        this.spouseSurname = spouseSurname;
    }

    public Integer getSpousePersonId() {
        return spousePersonId;
    }

    public void setSpousePersonId(Integer spousePersonId) {
        this.spousePersonId = spousePersonId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public List<VolunteerTrade> getTrades() {
        return trades;
    }

    public void setTrades(List<VolunteerTrade> trades) {
        this.trades = trades;
    }
}
