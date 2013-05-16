/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import uk.org.rbc1b.roms.controller.common.model.PersonModel;

/**
 * Model of volunteer details.
 *
 * @author oliver
 */
public class VolunteerModel extends PersonModel {

    private PersonModel emergencyContact;
    private String emergencyContactRelationship;
    private String gender;
    private String maritalStatus;
    private String status;
    private PersonModel spouse;

    public PersonModel getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(PersonModel emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PersonModel getSpouse() {
        return spouse;
    }

    public void setSpouse(PersonModel spouse) {
        this.spouse = spouse;
    }
}
