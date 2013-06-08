/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.db.DefaultAuditable;

/**
 * The skill owned by a volunteer. The Level indicates the competence.
 *
 * @author oliver.elder.esq
 */
public class VolunteerSkill extends DefaultAuditable {

    private Integer volunteerSkillId;
    private Integer personId;
    private Integer skillId;
    private Integer level;
    private String comments;
    private Date trainingDate;
    private String trainingResults;

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getTrainingResults() {
        return trainingResults;
    }

    public void setTrainingResults(String trainingResults) {
        this.trainingResults = trainingResults;
    }

    public Integer getVolunteerSkillId() {
        return volunteerSkillId;
    }

    public void setVolunteerSkillId(Integer volunteerSkillId) {
        this.volunteerSkillId = volunteerSkillId;
    }

    @Override
    public String toString() {
        return "VolunteerSkill{" + "volunteerSkillId=" + volunteerSkillId + '}';
    }
}
