/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 * Model to represent a skill a volunteer has.
 *
 * @author oliver.elder.esq
 */
public class VolunteerSkillModel {

    private Integer id;
    private String description;
    private EntityModel department;
    private EntityModel skill;
    private Integer level;
    private String comments;
    private java.sql.Date trainingDate;
    private String trainingResults;
    private boolean appearOnBadge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntityModel getDepartment() {
        return department;
    }

    public void setDepartment(EntityModel department) {
        this.department = department;
    }

    public EntityModel getSkill() {
        return skill;
    }

    public void setSkill(EntityModel skill) {
        this.skill = skill;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTrainingResults() {
        return trainingResults;
    }

    public void setTrainingResults(String trainingResults) {
        this.trainingResults = trainingResults;
    }

    public boolean isAppearOnBadge() {
        return appearOnBadge;
    }

    public void setAppearOnBadge(boolean appearOnBadge) {
        this.appearOnBadge = appearOnBadge;
    }
}
