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
import uk.org.rbc1b.roms.db.UpdateAuditable;

/**
 * Project stage defined for a given project. Each project defines one or more stages to be completed to finish the
 * project.
 */
@Audited
public class ProjectStage implements UpdateAuditable, Serializable {
    private static final long serialVersionUID = 1L;
    private Integer projectStageId;
    private Project project;
    private Integer projectStageTypeId;
    private Integer projectStageStatusId;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;
    private Set<ProjectStageTask> tasks;
    private Date updateTime;
    private Integer updatedBy;

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

    public Integer getProjectStageStatusId() {
        return projectStageStatusId;
    }

    public void setProjectStageStatusId(Integer projectStageStatusId) {
        this.projectStageStatusId = projectStageStatusId;
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

    public Set<ProjectStageTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<ProjectStageTask> tasks) {
        this.tasks = tasks;
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
