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
package uk.org.rbc1b.roms.controller.volunteer;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Form data used when editing a the rbc status data associated with the volunteer.
 *
 * @author oliver.elder.esq
 */
public class VolunteerRbcStatusForm {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime formDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime interviewDate;
    private Integer interviewerAPersonId;
    private String interviewerAUserName;
    private Integer interviewerBPersonId;
    private String interviewerBUserName;
    private String interviewComments;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime joinedDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime badgeIssueDate;
    private boolean availabilityMonday;
    private boolean availabilityTuesday;
    private boolean availabilityWednesday;
    private boolean availabilityThursday;
    private boolean availabilityFriday;
    private boolean availabilitySaturday;
    private boolean availabilitySunday;
    private boolean oversight;
    private String oversightComments;
    private boolean reliefAbroad;
    private String reliefAbroadComments;
    private boolean reliefUK;
    private String reliefUKComments;
    private String hhcFormCode;

    public DateTime getFormDate() {
        return formDate;
    }

    public void setFormDate(DateTime formDate) {
        this.formDate = formDate;
    }

    public DateTime getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(DateTime interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Integer getInterviewerAPersonId() {
        return interviewerAPersonId;
    }

    public void setInterviewerAPersonId(Integer interviewerAPersonId) {
        this.interviewerAPersonId = interviewerAPersonId;
    }

    public String getInterviewerAUserName() {
        return interviewerAUserName;
    }

    public void setInterviewerAUserName(String interviewerAUserName) {
        this.interviewerAUserName = interviewerAUserName;
    }

    public Integer getInterviewerBPersonId() {
        return interviewerBPersonId;
    }

    public void setInterviewerBPersonId(Integer interviewerBPersonId) {
        this.interviewerBPersonId = interviewerBPersonId;
    }

    public String getInterviewerBUserName() {
        return interviewerBUserName;
    }

    public void setInterviewerBUserName(String interviewerBUserName) {
        this.interviewerBUserName = interviewerBUserName;
    }

    public String getInterviewComments() {
        return interviewComments;
    }

    public void setInterviewComments(String interviewComments) {
        this.interviewComments = interviewComments;
    }

    public DateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(DateTime joinedDate) {
        this.joinedDate = joinedDate;
    }

    public DateTime getBadgeIssueDate() {
        return badgeIssueDate;
    }

    public void setBadgeIssueDate(DateTime badgeIssueDate) {
        this.badgeIssueDate = badgeIssueDate;
    }

    public boolean isAvailabilityMonday() {
        return availabilityMonday;
    }

    public void setAvailabilityMonday(boolean availabilityMonday) {
        this.availabilityMonday = availabilityMonday;
    }

    public boolean isAvailabilityTuesday() {
        return availabilityTuesday;
    }

    public void setAvailabilityTuesday(boolean availabilityTuesday) {
        this.availabilityTuesday = availabilityTuesday;
    }

    public boolean isAvailabilityWednesday() {
        return availabilityWednesday;
    }

    public void setAvailabilityWednesday(boolean availabilityWednesday) {
        this.availabilityWednesday = availabilityWednesday;
    }

    public boolean isAvailabilityThursday() {
        return availabilityThursday;
    }

    public void setAvailabilityThursday(boolean availabilityThursday) {
        this.availabilityThursday = availabilityThursday;
    }

    public boolean isAvailabilityFriday() {
        return availabilityFriday;
    }

    public void setAvailabilityFriday(boolean availabilityFriday) {
        this.availabilityFriday = availabilityFriday;
    }

    public boolean isAvailabilitySaturday() {
        return availabilitySaturday;
    }

    public void setAvailabilitySaturday(boolean availabilitySaturday) {
        this.availabilitySaturday = availabilitySaturday;
    }

    public boolean isAvailabilitySunday() {
        return availabilitySunday;
    }

    public void setAvailabilitySunday(boolean availabilitySunday) {
        this.availabilitySunday = availabilitySunday;
    }

    public boolean isOversight() {
        return oversight;
    }

    public void setOversight(boolean oversight) {
        this.oversight = oversight;
    }

    public String getOversightComments() {
        return oversightComments;
    }

    public void setOversightComments(String oversightComments) {
        this.oversightComments = oversightComments;
    }

    public boolean isReliefAbroad() {
        return reliefAbroad;
    }

    public void setReliefAbroad(boolean reliefAbroad) {
        this.reliefAbroad = reliefAbroad;
    }

    public String getReliefAbroadComments() {
        return reliefAbroadComments;
    }

    public void setReliefAbroadComments(String reliefAbroadComments) {
        this.reliefAbroadComments = reliefAbroadComments;
    }

    public boolean isReliefUK() {
        return reliefUK;
    }

    public void setReliefUK(boolean reliefUK) {
        this.reliefUK = reliefUK;
    }

    public String getReliefUKComments() {
        return reliefUKComments;
    }

    public void setReliefUKComments(String reliefUKComments) {
        this.reliefUKComments = reliefUKComments;
    }

    public String getHhcFormCode() {
        return hhcFormCode;
    }

    public void setHhcFormCode(String hhcFormCode) {
        this.hhcFormCode = hhcFormCode;
    }
}
