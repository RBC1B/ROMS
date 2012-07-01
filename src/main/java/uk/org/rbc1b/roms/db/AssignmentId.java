/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.io.Serializable;

/*
 * AssignmentId Class to correspond to table AssignmentId Copyright (c) Black Crow System Limited, 2010-2012. All rights reserved. This software is distributed
 * under the License of Black Crow Systems Limited. @author rahulsingh
 */
public class AssignmentId implements Serializable {

    private Long rbcid;
    private Department department;

    public AssignmentId() {
    }

    public AssignmentId(Long rbcid, Department dep) {
        this.rbcid = rbcid;
        this.department = dep;
    }

    /**
     * @return the rbcid
     */
    public Long getRbcid() {
        return rbcid;
    }

    /**
     * @param rbcid the rbcid to set
     */
    public void setRbcid(Long rbcid) {
        this.rbcid = rbcid;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.rbcid != null ? this.rbcid.hashCode() : 0);
        hash = 17 * hash + (this.department != null ? this.department.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object e) {
        AssignmentId that = (AssignmentId) e;
        if ((this.rbcid == that.rbcid) && (this.department.equals(that.department))) {
            return true;
        } else {
            return false;
        }
    }
}
