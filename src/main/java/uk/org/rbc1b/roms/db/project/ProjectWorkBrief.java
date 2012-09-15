/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.project;

/**
 *
 * @author oliver.elder.esq
 */
public class ProjectWorkBrief {

    private Integer projectWorkBriefId;
    private Project project;
    private WorkFeature workFeature;
    private String brief;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getProjectWorkBriefId() {
        return projectWorkBriefId;
    }

    public void setProjectWorkBriefId(Integer projectWorkBriefId) {
        this.projectWorkBriefId = projectWorkBriefId;
    }

    public WorkFeature getWorkFeature() {
        return workFeature;
    }

    public void setWorkFeature(WorkFeature workFeature) {
        this.workFeature = workFeature;
    }

    @Override
    public String toString() {
        return "ProjectWorkBrief{" + "projectWorkBriefId=" + projectWorkBriefId + '}';
    }
}
