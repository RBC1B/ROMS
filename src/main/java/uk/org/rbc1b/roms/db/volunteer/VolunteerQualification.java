/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import uk.org.rbc1b.roms.db.Person;

/**
 *
 * @author oliver.elder.esq
 */
public class VolunteerQualification {

    private Integer volunteerQualificationId;
    private Person person;
    private Qualification qualification;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Integer getVolunteerQualificationId() {
        return volunteerQualificationId;
    }

    public void setVolunteerQualificationId(Integer volunteerQualificationId) {
        this.volunteerQualificationId = volunteerQualificationId;
    }

    @Override
    public String toString() {
        return "VolunteerQualification{" + "volunteerQualificationId=" + volunteerQualificationId + '}';
    }
}
