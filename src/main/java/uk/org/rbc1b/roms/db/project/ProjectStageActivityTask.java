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
import uk.org.rbc1b.roms.db.application.User;

/**
 * Task involved in completing a project stage activity.
 */
@Audited
public class ProjectStageActivityTask implements UpdateAuditable, Serializable, ProjectStageSortable {

    private static final long serialVersionUID = -2121305669657847928L;
    private static final String STARTED_STATUS_CODE = "WP";
    private Integer projectStageActivityTaskId;
    private ProjectStageActivity projectStageActivity;
    private String name;
    private User assignedUser;
    private String statusCode;
    private String comments;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;
    private Set<ProjectStageActivityTaskEvent> events;
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
        return projectStageActivityTaskId;
    }

    public Integer getProjectStageActivityTaskId() {
        return projectStageActivityTaskId;
    }

    public void setProjectStageActivityTaskId(Integer projectStageActivityTaskId) {
        this.projectStageActivityTaskId = projectStageActivityTaskId;
    }

    public ProjectStageActivity getProjectStageActivity() {
        return projectStageActivity;
    }

    public void setProjectStageActivity(ProjectStageActivity projectStageActivity) {
        this.projectStageActivity = projectStageActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
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

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public Set<ProjectStageActivityTaskEvent> getEvents() {
        return events;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public void setEvents(Set<ProjectStageActivityTaskEvent> events) {
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
        return "ProjectStageActivityTask{" + "projectStageActivityTaskId=" + projectStageActivityTaskId + ", name="
                + name + '}';
    }
}
