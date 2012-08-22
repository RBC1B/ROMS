/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author ramindursingh
 */
public class Invitation {

    private InvitationID invitationid;
    private String department;
    private String abletocome;
    private String reason;
    private String comments;

    /**
     * @return the invitationid
     */
    public InvitationID getInvitationid() {
        return invitationid;
    }

    /**
     * @param invitationid the invitationid to set
     */
    public void setInvitationid(InvitationID invitationid) {
        this.invitationid = invitationid;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the abletocome
     */
    public String getAbletocome() {
        return abletocome;
    }

    /**
     * @param abletocome the abletocome to set
     */
    public void setAbletocome(String abletocome) {
        this.abletocome = abletocome;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
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
}
