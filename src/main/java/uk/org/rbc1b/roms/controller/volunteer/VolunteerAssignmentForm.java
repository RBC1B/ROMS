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
package uk.org.rbc1b.roms.controller.volunteer;

import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Data associated with updating a volunteer assignment.
 */
public class VolunteerAssignmentForm {
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime assignedDate;
    @NotNull
    private Integer departmentId;
    @NotNull
    private Integer teamId;
    @NotNull
    private String assignmentRoleCode;
    @NotNull
    private Integer tradeNumberId;

    public DateTime getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(DateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getAssignmentRoleCode() {
        return assignmentRoleCode;
    }

    public void setAssignmentRoleCode(String assignmentRoleCode) {
        this.assignmentRoleCode = assignmentRoleCode;
    }

    public Integer getTradeNumberId() {
        return tradeNumberId;
    }

    public void setTradeNumberId(Integer tradeNumberId) {
        this.tradeNumberId = tradeNumberId;
    }

}
