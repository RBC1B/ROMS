/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.io.Serializable;
import java.sql.Date;

/*
 * Assignment Class to correspond to table Assignment
 * Copyright (c) Black Crow System Limited, 2010-2012. All rights reserved.
 * This software is distributed under the License of Black Crow Systems Limited.
 * @author rahulsingh
 */
public class Assignment implements Serializable {
    private AssignmentId id;
    private Volunteer volunteer;
    private Role role;
    private Date date;
    private String comments;
    private String tradeNumber;
    private String team;

    public Assignment(){}

    public Assignment(AssignmentId id, Volunteer vol, Role role, Date date, String comments, String tradeNo, String team){
        this.id = id;
        this.volunteer = vol;
        this.role = role;
        this.date = date;
        this.comments = comments;
        this.tradeNumber = tradeNo;
        this.team = team;
    }

    /**
     * @return the id
     */
    public AssignmentId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(AssignmentId id) {
        this.id = id;
    }

    /**
     * @return the volunteer
     */
    public Volunteer getVolunteer() {
        return volunteer;
    }

    /**
     * @param volunteer the volunteer to set
     */
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the tradeNumber
     */
    public String getTradeNumber() {
        return tradeNumber;
    }

    /**
     * @param tradeNumber the tradeNumber to set
     */
    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    /**
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object e){
        Assignment that = (Assignment) e;
        if (this.id.equals(that.id)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
