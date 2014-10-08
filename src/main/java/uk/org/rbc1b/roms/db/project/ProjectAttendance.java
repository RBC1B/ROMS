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

import java.sql.Date;

/**
 *
 * @author ramindursingh
 */
public class ProjectAttendance {

    private Integer projectAttendanceId;
    private ProjectAvailability projectAvailability;
    private Date availableDate;
    private boolean required;
    private boolean attended;
    private Date updateTime;
    private Integer updatedBy;

    /**
     * @return the projectAttendanceId
     */
    public Integer getProjectAttendanceId() {
        return projectAttendanceId;
    }

    /**
     * @param projectAttendanceId the projectAttendanceId to set
     */
    public void setProjectAttendanceId(Integer projectAttendanceId) {
        this.projectAttendanceId = projectAttendanceId;
    }

    /**
     * @return the projectAvailability
     */
    public ProjectAvailability getProjectAvailability() {
        return projectAvailability;
    }

    /**
     * @param projectAvailability the projectAvailability to set
     */
    public void setProjectAvailability(ProjectAvailability projectAvailability) {
        this.projectAvailability = projectAvailability;
    }

    /**
     * @return the availableDate
     */
    public Date getAvailableDate() {
        return availableDate;
    }

    /**
     * @param availableDate the availableDate to set
     */
    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the attended
     */
    public boolean isAttended() {
        return attended;
    }

    /**
     * @param attended the attended to set
     */
    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
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
