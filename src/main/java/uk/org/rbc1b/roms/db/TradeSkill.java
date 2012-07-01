/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author ramindursingh
 */
public class TradeSkill {
    private String skill;
    private String skillType;
    private Department department;
    private String description;

    /**
     * @return the skill
     */
    public String getSkill() {
        return skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * @return the skillType
     */
    public String getSkillType() {
        return skillType;
    }

    /**
     * @param skillType the skillType to set
     */
    public void setSkillType(String skillType) {
        this.skillType = skillType;
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
}
