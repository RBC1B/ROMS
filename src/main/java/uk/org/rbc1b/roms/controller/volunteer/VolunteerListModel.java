/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 * Model of summary information for a volunteer, used in the list results.
 *
 * @author oliver.elder.esq
 */
public class VolunteerListModel {

    private EntityModel congregation;
    private String editUri;
    private String email;
    private String forename;
    private Integer id;
    private String middleName;
    private String surname;
    private String uri;

    public EntityModel getCongregation() {
        return congregation;
    }

    public void setCongregation(EntityModel congregation) {
        this.congregation = congregation;
    }

    public String getEditUri() {
        return editUri;
    }

    public void setEditUri(String editUri) {
        this.editUri = editUri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
