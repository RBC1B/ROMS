/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.project;

/**
 *
 * @author oliver.elder.esq
 */
public class ProjectEvent {

    private Integer projectEventId;
    private Project project;
    private Commentator commentator;
    private String comments;
    private boolean visible;

    public Commentator getCommentator() {
        return commentator;
    }

    public void setCommentator(Commentator commentator) {
        this.commentator = commentator;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getProjectEventId() {
        return projectEventId;
    }

    public void setProjectEventId(Integer projectEventId) {
        this.projectEventId = projectEventId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "ProjectEvent{" + "projectEventId=" + projectEventId + '}';
    }
}
