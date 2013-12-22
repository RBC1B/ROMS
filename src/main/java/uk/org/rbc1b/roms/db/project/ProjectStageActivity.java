/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.db.project;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import uk.org.rbc1b.roms.db.UpdateAuditable;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Activity involved in completing a project stage.
 */
@Audited
public class ProjectStageActivity implements UpdateAuditable, Serializable, ProjectStageSortable {
    private static final long serialVersionUID = -2121305669657847928L;
    private static final String STARTED_STATUS_CODE = "WP";
    private Integer projectStageActivityId;
    private ProjectStageActivityType projectStageActivityType;
    private ProjectStage projectStage;
    private Volunteer assignedVolunteer;
    private String statusCode;
    private String comments;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;
    private java.sql.Date projectedStart;
    private java.sql.Date projectedCompletion;
    private Set<ProjectStageActivityTask> tasks;
    private Set<ProjectStageActivityEvent> events;
    private Date updateTime;
    private Integer updatedBy;

    /**
     * @return true if the status is started
     */
    public boolean isStarted() {
        return statusCode.equals(STARTED_STATUS_CODE);
    }
    
    @Override
    public Integer getProjectStageSortableId() {
        return projectStageActivityId;
    }

    public Integer getProjectStageActivityId() {
        return projectStageActivityId;
    }

    public void setProjectStageActivityId(Integer projectStageActivityId) {
        this.projectStageActivityId = projectStageActivityId;
    }

    public ProjectStageActivityType getProjectStageActivityType() {
        return projectStageActivityType;
    }

    public void setProjectStageActivityType(ProjectStageActivityType projectStageActivityType) {
        this.projectStageActivityType = projectStageActivityType;
    }

    public ProjectStage getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(ProjectStage projectStage) {
        this.projectStage = projectStage;
    }

    public Volunteer getAssignedVolunteer() {
        return assignedVolunteer;
    }

    public void setAssignedVolunteer(Volunteer assignedVolunteer) {
        this.assignedVolunteer = assignedVolunteer;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Date startedTime) {
        this.startedTime = startedTime;
    }

    public Date getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public java.sql.Date getProjectedStart() {
        return projectedStart;
    }

    public void setProjectedStart(java.sql.Date projectedStart) {
        this.projectedStart = projectedStart;
    }

    public java.sql.Date getProjectedCompletion() {
        return projectedCompletion;
    }

    public void setProjectedCompletion(java.sql.Date projectedCompletion) {
        this.projectedCompletion = projectedCompletion;
    }

    public Set<ProjectStageActivityTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<ProjectStageActivityTask> tasks) {
        this.tasks = tasks;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public Set<ProjectStageActivityEvent> getEvents() {
        return events;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public void setEvents(Set<ProjectStageActivityEvent> events) {
        this.events = events;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "ProjectStageActivity{" + "projectStageActivityId=" + projectStageActivityId + '}';
    }
}
