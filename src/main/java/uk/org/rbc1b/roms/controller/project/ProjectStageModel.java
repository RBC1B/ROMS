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
    private List<ProjectStageActivityModel> activities;
    private Integer id;
    private ProjectStageType type;
    private String status;
    private Date createdTime;
    private Date startedTime;
    private Date completedTime;

    /**
     * @return total number of activities connected to the stage
     */
    public int getTotalActivityCount() {
        return activities.size();
    }

    /**
     * @return  total number of activities created but not started
     */
    public int getCreatedActivityCount() {
        int count = 0;
        for (ProjectStageActivityModel activity : activities) {
            if (activity.getStartedTime() == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return  total number of activities started but not completed
     */
    public int getStartedActivityCount() {
        int count = 0;
        for (ProjectStageActivityModel activity : activities) {
            if (activity.getStartedTime() != null && activity.getCompletedTime() == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return  total number of activities completed
     */
    public int getCompletedActivityCount() {
        int count = 0;
        for (ProjectStageActivityModel activity : activities) {
            if (activity.getCompletedTime() != null) {
                count++;
            }
        }
        return count;
    }

    public List<ProjectStageActivityModel> getActivities() {
        return activities;
    }

    public void setActivities(List<ProjectStageActivityModel> activities) {
        this.activities = activities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
