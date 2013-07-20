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

import java.sql.Date;
import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.project.Project;

/**
 * The invitation of a volunteer for a given date on a project.
 * @author oliver.elder.esq
 */
public class Attendance extends DefaultUpdateAuditable {

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
