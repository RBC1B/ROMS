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

/**
 * Project stage defined for a given project. Each project defines one or more stages to be completed to finish the
 * project.
 */
@Audited
public class ProjectStage implements UpdateAuditable, Serializable {
    private static final long serialVersionUID = 1L;
    private static final int STARTED_STATUS_ID = 1;
    private Integer projectStageId;
    private Project project;
    private Integer projectStageTypeId;
    private Integer statusId;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;
    private java.sql.Date projectedStart;
    private java.sql.Date projectedCompletion;
    private Set<ProjectStageActivity> activities;
    private Set<ProjectStageEvent> events;
    private Date updateTime;
    private Integer updatedBy;

    /**
     * @return true if the status is started
     */
    public boolean isStarted() {
        return statusId.equals(STARTED_STATUS_ID);
    }

    public Integer getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(Integer projectStageId) {
        this.projectStageId = projectStageId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getProjectStageTypeId() {
        return projectStageTypeId;
    }

    public void setProjectStageTypeId(Integer projectStageTypeId) {
        this.projectStageTypeId = projectStageTypeId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public Set<ProjectStageActivity> getActivities() {
        return activities;
    }

    public void setActivities(Set<ProjectStageActivity> activities) {
        this.activities = activities;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public Set<ProjectStageEvent> getEvents() {
        return events;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public void setEvents(Set<ProjectStageEvent> events) {
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
        return "ProjectStage#" + projectStageId;
    }
}
