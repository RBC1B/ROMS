/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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

import uk.org.rbc1b.roms.db.volunteer.department.Department;

/**
 *
 * @author ramindursingh
 */
public class ProjectDepartmentRequired {

    private Integer projectDepartmentRequiredId;
    private Project project;
    private Department deparment;
    private String name;
    private java.sql.Date workDate;
    private int workPeriod;
    private java.sql.Date updateTime;
    private Integer updatedBy;

    /**
     * @return the projectDepartmentRequiredId
     */
    public Integer getProjectDepartmentRequiredId() {
        return projectDepartmentRequiredId;
    }

    /**
     * @param projectDepartmentRequiredId the projectDepartmentRequiredId to set
     */
    public void setProjectDepartmentRequiredId(Integer projectDepartmentRequiredId) {
        this.projectDepartmentRequiredId = projectDepartmentRequiredId;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the deparment
     */
    public Department getDeparment() {
        return deparment;
    }

    /**
     * @param deparment the deparment to set
     */
    public void setDeparment(Department deparment) {
        this.deparment = deparment;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the workDate
     */
    public java.sql.Date getWorkDate() {
        return workDate;
    }

    /**
     * @param workDate the workDate to set
     */
    public void setWorkDate(java.sql.Date workDate) {
        this.workDate = workDate;
    }

    /**
     * @return the workPeriod
     */
    public int getWorkPeriod() {
        return workPeriod;
    }

    /**
     * @param workPeriod the workPeriod to set
     */
    public void setWorkPeriod(int workPeriod) {
        this.workPeriod = workPeriod;
    }

    /**
     * @return the updateTime
     */
    public java.sql.Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(java.sql.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the updatedBy
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
