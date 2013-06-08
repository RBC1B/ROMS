/*
 * Copyright (c) Black Crow System Limited, 2010-2012. All rights reserved.
 * This software is distributed under the License of Black Crow Systems Limited.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.db.DefaultAuditable;

/**
 * The volunteers role within a team/department.
 * @author oliver.elder.esq
 */
public class Assignment extends DefaultAuditable {

    private Integer assignmentId;
    private Integer personId;
    private Integer departmentId;
    private Integer roleId;
    private Date assignedDate;
    private Integer tradeNumberId;
    private Integer teamId;

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTradeNumberId() {
        return tradeNumberId;
    }

    public void setTradeNumberId(Integer tradeNumberId) {
        this.tradeNumberId = tradeNumberId;
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignmentId=" + assignmentId + '}';
    }
}
