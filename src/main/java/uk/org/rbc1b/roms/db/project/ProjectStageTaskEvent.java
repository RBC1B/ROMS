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
import uk.org.rbc1b.roms.db.CreateAuditable;

/**
 * Events associated with the project stage tasks.
 */
public class ProjectStageTaskEvent implements CreateAuditable, Serializable {

    private static final long serialVersionUID = -8934557962915966912L;
    private Integer projectStageTaskEventId;
    private ProjectStageTask projectStageTask;
    private Integer projectStageTaskEventTypeId;
    private String comments;
    private Date createTime;
    private Integer createdBy;

    public Integer getProjectStageTaskEventId() {
        return projectStageTaskEventId;
    }

    public void setProjectStageTaskEventId(Integer projectStageTaskEventId) {
        this.projectStageTaskEventId = projectStageTaskEventId;
    }

    public ProjectStageTask getProjectStageTask() {
        return projectStageTask;
    }

    public void setProjectStageTask(ProjectStageTask projectStageTask) {
        this.projectStageTask = projectStageTask;
    }

    public Integer getProjectStageTaskEventTypeId() {
        return projectStageTaskEventTypeId;
    }

    public void setProjectStageTaskEventTypeId(Integer projectStageTaskEventTypeId) {
        this.projectStageTaskEventTypeId = projectStageTaskEventTypeId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "ProjectStageTaskEvent{" + "projectStageTaskEventId=" + projectStageTaskEventId + '}';
    }
}
