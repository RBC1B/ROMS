/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.db.Person;

/**
 * POJO to contain the volunteer edit form data.
 *
 * @author oliver
 */
public class VolunteerForm {

    private Date baptismDate;
    private Integer congregationId;
    private boolean elder;
    private Integer emergencyRelationshipId;
    private String gender;    // M or F
    private boolean ministerialServant;
    private Person person = new Person();
    private boolean regularPioneer;

    public Date getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(Date baptismDate) {
        this.baptismDate = baptismDate;
    }

    public Integer getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isRegularPioneer() {
        return regularPioneer;
    }

    public void setRegularPioneer(boolean regularPioneer) {
        this.regularPioneer = regularPioneer;
    }
}
