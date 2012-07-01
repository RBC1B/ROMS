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
public class InvitationID implements Serializable {

    private long rbcid;
    private String projectname;
    private Date invitedate;

    public InvitationID() {
        // default constructor required by hibernate
    }

    public InvitationID(long rbcid, String projectname, Date invitedate) {
        this.rbcid = rbcid;
        this.projectname = projectname;
        this.invitedate = invitedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof InvitationID)) {
            return false;
        }
        final InvitationID iid = (InvitationID) o;
        if (this.rbcid != iid.rbcid) {
            return false;
        }
        if (!this.projectname.equalsIgnoreCase(iid.getProjectname())) {
            return false;
        }
        if (this.invitedate != iid.getInvitedate()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.rbcid ^ (this.rbcid >>> 32));
        hash = 29 * hash + (this.projectname != null ? this.projectname.hashCode() : 0);
        hash = 29 * hash + (this.invitedate != null ? this.invitedate.hashCode() : 0);
        return hash;
    }

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
     * @return the invitedate
     */
    public Date getInvitedate() {
        return invitedate;
    }

    /**
     * @param invitedate the invitedate to set
     */
    public void setInvitedate(Date invitedate) {
        this.invitedate = invitedate;
    }
}
