/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.skill;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramindursingh
 */
public class SkillForm {

    @NotNull
    @Size(max = 50)
    private String name;
    @Size(max = 250)
    private String description;
    @Size(max = 3)
    private boolean appearOnBadge;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the appearOnBadge
     */
    public boolean isAppearOnBadge() {
        return appearOnBadge;
    }

    /**
     * @param appearOnBadge the appearOnBadge to set
     */
    public void setAppearOnBadge(boolean appearOnBadge) {
        this.appearOnBadge = appearOnBadge;
    }
}
