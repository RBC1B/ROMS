/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.skill;

import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 * Basic skill information used in the skill list results.
 *
 * @author oliver.elder.esq
 */
public class SkillListModel {

    private Integer skillId;
    private String uri;
    private String name;
    private EntityModel department;
    private String description;
    private EntityModel category;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityModel getDepartment() {
        return department;
    }

    public void setDepartment(EntityModel department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public EntityModel getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(EntityModel category) {
        this.category = category;
    }
}
