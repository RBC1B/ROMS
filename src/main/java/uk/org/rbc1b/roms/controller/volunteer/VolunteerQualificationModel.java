/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 *
 * @author rhioli
 */
public class VolunteerQualificationModel {

    private Integer id;
    private String description;
    private EntityModel qualification;
    private String comments;
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

    public EntityModel getQualification() {
        return qualification;
    }

    public void setQualification(EntityModel qualification) {
        this.qualification = qualification;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isAppearOnBadge() {
        return appearOnBadge;
    }

    public void setAppearOnBadge(boolean appearOnBadge) {
        this.appearOnBadge = appearOnBadge;
    }
}
