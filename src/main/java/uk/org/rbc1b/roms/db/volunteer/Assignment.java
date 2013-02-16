/*
 * Copyright (c) Black Crow System Limited, 2010-2012. All rights reserved.
 * This software is distributed under the License of Black Crow Systems Limited.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.db.Person;

/**
 * @author oliver.elder.esq
 */
public class Assignment {

    private Integer assignmentId;
    private Person person;
    private Department department;
    private Integer roleId;
    private Date assignedDate;
    private TradeNumber tradeNumber;
    private Team team;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TradeNumber getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(TradeNumber tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignmentId=" + assignmentId + '}';
    }
}
