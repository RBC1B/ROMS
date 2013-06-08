/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import uk.org.rbc1b.roms.db.Category;
import uk.org.rbc1b.roms.db.DefaultAuditable;

/**
 * A capability that may be owned by a volunteer, and required on a project.
 * @author oliver.elder.esq
 */
public class Skill extends DefaultAuditable {

    private Integer skillId;
    private String name;
    private Department department;
    private String description;
    private Category category;

    /**
     * @return the skillId
     */
    public Integer getSkillId() {
        return skillId;
    }

    /**
     * @param skillId the skillId to set
     */
    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

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
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
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
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Skill{" + "skillId=" + getSkillId() + '}';
    }
}
