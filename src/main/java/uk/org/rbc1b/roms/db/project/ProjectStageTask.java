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

import java.util.Date;
import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Task involved in completing a project stage.
 */
public class ProjectStageTask extends DefaultUpdateAuditable {
    private static final long serialVersionUID = -2121305669657847928L;
    private Integer projectStageTaskId;
    private ProjectStage projectStage;
    private String name;
    private Volunteer assignedVolunteer;
    private String comments;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;

    public Integer getProjectStageTaskId() {
        return projectStageTaskId;
    }

    public void setProjectStageTaskId(Integer projectStageTaskId) {
        this.projectStageTaskId = projectStageTaskId;
    }

    public ProjectStage getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(ProjectStage projectStage) {
        this.projectStage = projectStage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Volunteer getAssignedVolunteer() {
        return assignedVolunteer;
    }

    public void setAssignedVolunteer(Volunteer assignedVolunteer) {
        this.assignedVolunteer = assignedVolunteer;
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

    @Override
    public String toString() {
        return "ProjectStageTask{" + "projectStageTaskId=" + projectStageTaskId + ", name=" + name + '}';
    }
}
