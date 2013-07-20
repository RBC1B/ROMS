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
import uk.org.rbc1b.roms.db.project.ProjectStageType;

/**
 * Model for an individual project stage.
 */
public class ProjectStageModel {

    private Integer projectStageId;
    private ProjectStageType type;
    private String status;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;
    private List<ProjectStageTaskModel> tasks;

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
        for (ProjectStageTaskModel task: tasks) {
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
        for (ProjectStageTaskModel task: tasks) {
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
        for (ProjectStageTaskModel task: tasks) {
            if (task.getCompletedTime() != null) {
                count++;
            }
        }
        return count;
    }

    public Integer getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(Integer projectStageId) {
        this.projectStageId = projectStageId;
    }

    public ProjectStageType getType() {
        return type;
    }

    public void setType(ProjectStageType type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<ProjectStageTaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<ProjectStageTaskModel> tasks) {
        this.tasks = tasks;
    }
}
