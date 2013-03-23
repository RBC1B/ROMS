/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 * Criteria used when searching for a person.
 *
 * @author oliver.elder.esq
 */
public class PersonSearchCriteria {

    private String forename;
    private String surname;
    private Integer congregationId;
    private String email;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
