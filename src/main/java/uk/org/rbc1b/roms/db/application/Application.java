/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.application;

/**
 * Mapping of the sections of the webapp, e.g. Volunteers.
 * <p>Each section is called an application.
 * @author oliver.elder.esq
 */
public class Application {

    private Integer applicationId;
    private String name;
    private String comments;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Application{" + "applicationId=" + applicationId + '}';
    }
}
