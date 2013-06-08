/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import uk.org.rbc1b.roms.db.DefaultAuditable;

/**
 * A qualification owned by a volunteer.
 * @author oliver.elder.esq
 */
public class VolunteerQualification extends DefaultAuditable {

    private Integer volunteerQualificationId;
    private Integer personId;
    private Integer qualificationId;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
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
