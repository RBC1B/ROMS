/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ramindursingh
 */
public class AttendanceID implements Serializable {

    private long rbcid;
    private String projectname;
    private Date attenddate;

    /**
     * @return the rbcid
     */
    public long getRbcid() {
        return rbcid;
    }

    /**
     * @param rbcid the rbcid to set
     */
    public void setRbcid(long rbcid) {
        this.rbcid = rbcid;
    }

    /**
     * @return the projectname
     */
    public String getProjectname() {
        return projectname;
    }

    /**
     * @param projectname the projectname to set
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    /**
     * @return the attenddate
     */
    public Date getAttenddate() {
        return attenddate;
    }

    /**
     * @param attenddate the attenddate to set
     */
    public void setAttenddate(Date attenddate) {
        this.attenddate = attenddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof AttendanceID)) {
            return false;
        }
        final AttendanceID aid = (AttendanceID) o;
        if (this.rbcid != aid.rbcid) {
            return false;
        }
        if (!this.projectname.equalsIgnoreCase(aid.getProjectname())) {
            return false;
        }
        if (this.attenddate != aid.getAttenddate()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.rbcid ^ (this.rbcid >>> 32));
        hash = 37 * hash + (this.projectname != null ? this.projectname.hashCode() : 0);
        hash = 37 * hash + (this.attenddate != null ? this.attenddate.hashCode() : 0);
        return hash;
    }
}
