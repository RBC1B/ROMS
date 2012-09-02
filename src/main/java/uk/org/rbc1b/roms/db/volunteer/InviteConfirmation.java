/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class InviteConfirmation {

    private Integer inviteConfirmationId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInviteConfirmationId() {
        return inviteConfirmationId;
    }

    public void setInviteConfirmationId(Integer inviteConfirmationId) {
        this.inviteConfirmationId = inviteConfirmationId;
    }

    @Override
    public String toString() {
        return "InviteConfirmation{" + "inviteConfirmationId=" + inviteConfirmationId + '}';
    }
}
