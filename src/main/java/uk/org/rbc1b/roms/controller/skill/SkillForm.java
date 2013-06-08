/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.skill;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import uk.org.rbc1b.roms.db.volunteer.Skill;

/**
 *
 * @author ramindursingh
 */
public class SkillForm {

    private Integer skillId;
    @NotNull
    @Size(max = 50)
    private String name;
    private Integer departmentId;
    @Size(max = 50)
    private String department;
    @Size(max = 250)
    private String description;
    private Integer categoryId;
    @Size(max = 20)
    private String category;

    /**
     * Default no argument constructor.
     */
    public SkillForm() {
    }

    /**
     * Constructor.
     *
     * @param skill the skill to use
     */
    public SkillForm(Skill skill) {
        skillId = skill.getSkillId();
        name = skill.getName();
        departmentId = skill.getDepartment().getDepartmentId();
        department = skill.getDepartment().getName();
        description = skill.getDescription();
        categoryId = skill.getCategory().getCategoryId();
        category = skill.getCategory().getName();
    }

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
     * @return the departmentId
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
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
     * @return the categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
