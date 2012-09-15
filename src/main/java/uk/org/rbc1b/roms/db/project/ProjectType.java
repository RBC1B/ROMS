package uk.org.rbc1b.roms.db.project;

/**
 * @author oliver.elder.esq
 */
public class ProjectType {

    private Integer projectTypeId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    @Override
    public String toString() {
        return "ProjectType{" + "projectTypeId=" + projectTypeId + '}';
    }
}
