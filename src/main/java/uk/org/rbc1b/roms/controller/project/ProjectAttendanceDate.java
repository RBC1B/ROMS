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
package uk.org.rbc1b.roms.controller.project;

/**
 * Holds dates for a project sessions that volunteer can/cannot attend.
 */
public class ProjectAttendanceDate {

    private Integer projectAttendanceId;
    private String availableDate;
    private boolean required;

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
     * @return the availableDate
     */
    public String getAvailableDate() {
        return availableDate;
    }

    /**
     * @param availableDate the availableDate to set
     */
    public void setAvailableDate(String availableDate) {
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
}
