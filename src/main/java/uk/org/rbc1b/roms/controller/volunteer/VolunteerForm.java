/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * POJO to contain the volunteer edit form data.
 *
 * @author oliver
 */
public class VolunteerForm {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime baptismDate;
    private boolean elder;
    private Integer emergencyRelationshipId;
    @NotNull
    @Size(min = 1, max = 1)
    private String gender;    // M or F
    private boolean ministerialServant;
    private boolean regularPioneer;
    private Integer personId;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime birthDate;
    private Integer congregationId;
    @NotNull
    @Size(min = 2)
    private String forename;
    private String middleName;
    @NotNull
    @Size(min = 2)
    private String surname;
    @NotNull
    @Size(min = 2)
    private String street;
    @NotNull
    @Size(min = 2)
    private String town;
    private String county;
    @NotNull
    @Size(min = 2)
    private String postcode;
    private String telephone;
    private String mobile;
    private String workPhone;
    private String email;

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

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
