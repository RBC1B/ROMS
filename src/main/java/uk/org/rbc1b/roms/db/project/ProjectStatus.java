/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.project;

/**
 *
 * @author oliver.elder.esq
 */
public class ProjectStatus {

    private Integer projectStatusId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Integer projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Override
    public String toString() {
        return "ProjectStatus{" + "projectStatusId=" + projectStatusId + '}';
    }
}
