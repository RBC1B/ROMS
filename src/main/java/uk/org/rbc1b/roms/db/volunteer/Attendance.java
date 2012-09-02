/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.sql.Date;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.project.Project;

/**
 *
 * @author oliver.elder.esq
 */
public class Attendance {

    private Integer attendanceId;
    private Project project;
    private Person person;
    private Date inviteDate;
    private boolean ableToCome;
    private InvitationConfirmation invitationConfirmation;
    private Department department;
    private boolean attended;

    public boolean isAbleToCome() {
        return ableToCome;
    }

    public void setAbleToCome(boolean ableToCome) {
        this.ableToCome = ableToCome;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public InvitationConfirmation getInvitationConfirmation() {
        return invitationConfirmation;
    }

    public void setInvitationConfirmation(InvitationConfirmation invitationConfirmation) {
        this.invitationConfirmation = invitationConfirmation;
    }

    public Date getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(Date inviteDate) {
        this.inviteDate = inviteDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Attendance{" + "attendanceId=" + attendanceId + '}';
    }
}
