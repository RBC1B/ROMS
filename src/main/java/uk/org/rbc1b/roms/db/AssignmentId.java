/*
 * Copyright (c) Black Crow System Limited, 2010-2012. All rights reserved. This software is distributed
 * under the License of Black Crow Systems Limited. @author rahulsingh
 */
package uk.org.rbc1b.roms.db;

import java.io.Serializable;

/**
 * AssignmentId Class to correspond to table AssignmentId.
 */
public class AssignmentId implements Serializable {

    private Long rbcid;
    private Department department;

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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssignmentId other = (AssignmentId) obj;
        if (this.rbcid != other.rbcid && (this.rbcid == null || !this.rbcid.equals(other.rbcid))) {
            return false;
        }
        if (this.department != other.department && (this.department == null || !this.department.equals(other.department))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.rbcid != null ? this.rbcid.hashCode() : 0);
        hash = 79 * hash + (this.department != null ? this.department.hashCode() : 0);
        return hash;
    }
}
