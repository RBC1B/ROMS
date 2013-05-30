/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 * Model of a volunteer assignment.
 *
 * @author oliver.elder.esq
 */
public class AssignmentModel {

    private Integer id;
    private EntityModel department;
    private String role;
    private Date assignedDate;
    private String tradeNumber;
    private EntityModel team;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntityModel getDepartment() {
        return department;
    }

    public void setDepartment(EntityModel department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public EntityModel getTeam() {
        return team;
    }

    public void setTeam(EntityModel team) {
        this.team = team;
    }
}
