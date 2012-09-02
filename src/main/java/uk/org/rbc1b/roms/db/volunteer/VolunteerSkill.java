/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.db.Person;

/**
 *
 * @author oliver.elder.esq
 */
public class VolunteerSkill {

    private Integer volunteerSkillId;
    private Person person;
    private Skill skill;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
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
