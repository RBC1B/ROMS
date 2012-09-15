/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class InvitationConfirmation {

    private Integer invitationConfirmationId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInvitationConfirmationId() {
        return invitationConfirmationId;
    }

    public void setInvitationConfirmationId(Integer invitationConfirmationId) {
        this.invitationConfirmationId = invitationConfirmationId;
    }

    @Override
    public String toString() {
        return "InvitationConfirmation{" + "invitationConfirmationId=" + invitationConfirmationId + '}';
    }
}
