/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Form data for the volunteer name update.
 *
 * @author oliver.elder.esq
 */
public class VolunteerNameForm {

    @NotNull
    @Size(min = 2)
    private String forename;
    private String middleName;
    @NotNull
    @Size(min = 2)
    private String surname;

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
}
