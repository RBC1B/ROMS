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
package uk.org.rbc1b.roms.controller.project;

import java.util.Date;
import java.util.List;
import uk.org.rbc1b.roms.controller.common.model.PersonModel;
import uk.org.rbc1b.roms.db.project.ProjectStageActivityType;

/**
 * Model the project stage activities.
 */
public class ProjectStageActivityModel {
    private Integer id;
    private PersonModel assignedVolunteer;
    private String comments;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;
    private List<ProjectEventModel> events;
    private java.sql.Date projectedStart;
    private java.sql.Date projectedCompletion;
    private String status;
    private List<ProjectStageActivityTaskModel> tasks;
    private ProjectStageActivityType type;

    /**
     * @return total number of tasks connected to the stage
     */
    public int getTotalTaskCount() {
        return tasks.size();
    }

    /**
     * @return  total number of tasks created but not started
     */
    public int getCreatedTaskCount() {
        int count = 0;
        for (ProjectStageActivityTaskModel task : tasks) {
            if (task.getStartedTime() == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return  total number of tasks started but not completed
     */
    public int getStartedTaskCount() {
        int count = 0;
        for (ProjectStageActivityTaskModel task : tasks) {
            if (task.getStartedTime() != null && task.getCompletedTime() == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return  total number of tasks completed
     */
    public int getCompletedTaskCount() {
        int count = 0;
        for (ProjectStageActivityTaskModel task : tasks) {
            if (task.getCompletedTime() != null) {
                count++;
            }
        }
        return count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonModel getAssignedVolunteer() {
        return assignedVolunteer;
    }

    public void setAssignedVolunteer(PersonModel assignedVolunteer) {
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

    public List<ProjectEventModel> getEvents() {
        return events;
    }

    public void setEvents(List<ProjectEventModel> events) {
        this.events = events;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProjectStageActivityTaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<ProjectStageActivityTaskModel> tasks) {
        this.tasks = tasks;
    }

    public ProjectStageActivityType getType() {
        return type;
    }

    public void setType(ProjectStageActivityType type) {
        this.type = type;
    }

}
