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
package uk.org.rbc1b.roms.controller.volunteer.interview;

import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 * Model representing an volunteer interview session - a time and place
 * to gather volunteers for interviewing.
 */
public class InterviewSessionModel {

    private EntityModel kingdomHall;
    private java.sql.Date date;
    private String time;
    private String comments;
    private String uri;
    private String editUri;
    private String invitationsUri;
    private int invitedVolunteerCount;
    private int confirmedVolunteerCount;
    private int declinedVolunteerCount;

    /**
     * Simplistic time formatter, returning the time in HH:mm format.
     * @return formatted time
     */
    public String formatTime() {
        if (time == null) {
            return null;
        }
        return time.substring(0, 2) + ":" + time.substring(2);
    }

    /**
     * Return the number of volunteers who have been invited and have not declined.
     * @return count
     */
    public int getAttendingVolunteerCount() {
        return invitedVolunteerCount - declinedVolunteerCount;
    }

    public EntityModel getKingdomHall() {
        return kingdomHall;
    }

    public void setKingdomHall(EntityModel kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEditUri() {
        return editUri;
    }

    public void setEditUri(String editUri) {
        this.editUri = editUri;
    }

    public String getInvitationsUri() {
        return invitationsUri;
    }

    public void setInvitationsUri(String invitationsUri) {
        this.invitationsUri = invitationsUri;
    }

    /**
     * Count of volunteers who have been invited to the session (including those who have declined).
     * @return count
     */
    public int getInvitedVolunteerCount() {
        return invitedVolunteerCount;
    }

    /**
     * Count of volunteers who have been invited to the session (including those who have declined).
     * @param invitedVolunteerCount count
     */
    public void setInvitedVolunteerCount(int invitedVolunteerCount) {
        this.invitedVolunteerCount = invitedVolunteerCount;
    }

    /**
     * Count of volunteers are confirmed to attend (or completed - have attended) the session.
     * @return count
     */
    public int getConfirmedVolunteerCount() {
        return confirmedVolunteerCount;
    }

    /**
     * Count of volunteers are confirmed to attend (or completed - have attended) the session.
     * @param confirmedVolunteerCount count
     */
    public void setConfirmedVolunteerCount(int confirmedVolunteerCount) {
        this.confirmedVolunteerCount = confirmedVolunteerCount;
    }

    /**
     * Count of volunteers who were invited, but who have declined (or are no-shows,
     * or not required to attend after all).
     * @return count
     */
    public int getDeclinedVolunteerCount() {
        return declinedVolunteerCount;
    }

    /**
     * Count of volunteers who were invited, but who have declined (or are no-shows,
     * or not required to attend after all).
     * @param declinedVolunteerCount count
     */
    public void setDeclinedVolunteerCount(int declinedVolunteerCount) {
        this.declinedVolunteerCount = declinedVolunteerCount;
    }

}
