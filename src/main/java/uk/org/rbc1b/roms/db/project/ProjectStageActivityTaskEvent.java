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
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import uk.org.rbc1b.roms.db.CreateAuditable;

/**
 * Events associated with the project stage activity tasks.
 */
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ProjectStageActivityTaskEvent implements CreateAuditable, Serializable {

    private static final long serialVersionUID = -8934557962915966912L;
    private Integer projectStageActivityTaskEventId;
    private ProjectStageActivityTask projectStageActivityTask;
    private String projectStageActivityTaskEventTypeCode;
    private String comments;
    private Date createTime;
    private Integer createdBy;

    public Integer getProjectStageActivityTaskEventId() {
        return projectStageActivityTaskEventId;
    }

    public void setProjectStageActivityTaskEventId(Integer projectStageActivityTaskEventId) {
        this.projectStageActivityTaskEventId = projectStageActivityTaskEventId;
    }

    public ProjectStageActivityTask getProjectStageActivityTask() {
        return projectStageActivityTask;
    }

    public void setProjectStageActivityTask(ProjectStageActivityTask projectStageActivityTask) {
        this.projectStageActivityTask = projectStageActivityTask;
    }

    public String getProjectStageActivityTaskEventTypeCode() {
        return projectStageActivityTaskEventTypeCode;
    }

    public void setProjectStageActivityTaskEventTypeCode(String projectStageActivityTaskEventTypeCode) {
        this.projectStageActivityTaskEventTypeCode = projectStageActivityTaskEventTypeCode;
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
        return "ProjectStageTaskEvent{" + "projectStageActivityTaskEventId=" + projectStageActivityTaskEventId + '}';
    }
}