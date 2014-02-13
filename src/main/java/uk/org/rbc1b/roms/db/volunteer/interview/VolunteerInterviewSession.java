/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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
package uk.org.rbc1b.roms.db.volunteer.interview;

import java.io.Serializable;
import java.util.Date;
import uk.org.rbc1b.roms.db.UpdateAuditable;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Link between a volunteer and the interview session they were invited to.
 */
public class VolunteerInterviewSession implements UpdateAuditable, Serializable {
    private static final long serialVersionUID = -5602798115958399326L;
    private Integer volunteerInterviewSessionId;
    private InterviewSession interviewSession;
    private Volunteer volunteer;
    private String volunteerInterviewStatusCode;
    private String comments;
    private Date updateTime;
    private Integer updatedBy;

    public Integer getVolunteerInterviewSessionId() {
        return volunteerInterviewSessionId;
    }

    public void setVolunteerInterviewSessionId(Integer volunteerInterviewSessionId) {
        this.volunteerInterviewSessionId = volunteerInterviewSessionId;
    }

    public InterviewSession getInterviewSession() {
        return interviewSession;
    }

    public void setInterviewSession(InterviewSession interviewSession) {
        this.interviewSession = interviewSession;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getVolunteerInterviewStatusCode() {
        return volunteerInterviewStatusCode;
    }

    public void setVolunteerInterviewStatusCode(String volunteerInterviewStatusCode) {
        this.volunteerInterviewStatusCode = volunteerInterviewStatusCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        return "VolunteerInterviewSession #" + volunteerInterviewSessionId;
    }

}
