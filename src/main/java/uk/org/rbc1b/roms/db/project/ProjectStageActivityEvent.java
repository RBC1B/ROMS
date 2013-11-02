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
 * Events associated with the project stage activities.
 */
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ProjectStageActivityEvent implements CreateAuditable, Serializable {
    private static final long serialVersionUID = 4947689666475610021L;
    private Integer projectStageActivityEventId;
    private ProjectStageActivity projectStageActivity;
    private String projectStageActivityEventTypeCode;
    private String comments;
    private Date createTime;
    private Integer createdBy;

    public Integer getProjectStageActivityEventId() {
        return projectStageActivityEventId;
    }

    public void setProjectStageActivityEventId(Integer projectStageActivityEventId) {
        this.projectStageActivityEventId = projectStageActivityEventId;
    }

    public ProjectStageActivity getProjectStageActivity() {
        return projectStageActivity;
    }

    public void setProjectStageActivity(ProjectStageActivity projectStageActivity) {
        this.projectStageActivity = projectStageActivity;
    }

    public String getProjectStageActivityEventTypeCode() {
        return projectStageActivityEventTypeCode;
    }

    public void setProjectStageActivityEventTypeCode(String projectStageActivityEventTypeCode) {
        this.projectStageActivityEventTypeCode = projectStageActivityEventTypeCode;
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
        return "ProjectStageActivityEvent{" + "projectStageActivityEventId=" + projectStageActivityEventId + '}';
    }
}
