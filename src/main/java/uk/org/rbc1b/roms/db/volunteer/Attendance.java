/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.UpdateAuditable;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.volunteer.department.Department;

/**
 * The invitation of a volunteer for a given date on a project.
 * @author oliver.elder.esq
 */
public class Attendance implements UpdateAuditable, Serializable {
    private static final long serialVersionUID = -6357613333933986660L;
    private Integer attendanceId;
    private Project project;
    private Person person;
    private java.sql.Date inviteDate;
    private boolean ableToCome;
    private InvitationConfirmation invitationConfirmation;
    private Department department;
    private boolean attended;
    private Date updateTime;
    private Integer updatedBy;

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

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public InvitationConfirmation getInvitationConfirmation() {
        return invitationConfirmation;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public void setInvitationConfirmation(InvitationConfirmation invitationConfirmation) {
        this.invitationConfirmation = invitationConfirmation;
    }

    public java.sql.Date getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(java.sql.Date inviteDate) {
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
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "Attendance{" + "attendanceId=" + attendanceId + '}';
    }
}
