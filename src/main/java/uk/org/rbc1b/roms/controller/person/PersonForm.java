/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Congregation;

/**
 * From bean used when editing the person.
 *
 * @author oliver
 */
public class PersonForm {

    private Integer personId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime birthDate;
    private Integer congregationId;
    private String congregationName;
    @NotNull
    @Size(min = 2)
    private String forename;
    private String middleName;
    @NotNull
    @Size(min = 2)
    private String surname;
    private String street;
    private String town;
    private String county;
    private String postcode;
    private String telephone;
    private String mobile;
    private String workPhone;
    private String email;
    private String comments;

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
