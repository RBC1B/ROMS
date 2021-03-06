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

import java.util.Map;
import java.util.Set;
import uk.org.rbc1b.roms.controller.common.model.PersonModel;
import uk.org.rbc1b.roms.controller.common.model.UserModel;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTrade;

/**
 * Model of volunteer details.
 *
 * @author oliver
 */
public class VolunteerModel extends PersonModel {

    private String appointment;
    private java.sql.Date baptismDate;
    private PersonModel emergencyContact;
    private String emergencyContactRelationship;
    private String fulltime;
    private String gender;
    private String maritalStatus;
    private String status;
    private PersonModel spouse;
    private Set<VolunteerTrade> trades;
    private java.sql.Date formDate;
    private java.sql.Date joinedDate;
    private java.sql.Date badgeIssueDate;
    private java.sql.Date interviewDate;
    private UserModel interviewerA;
    private UserModel interviewerB;
    private String interviewComments;
    private Map<Long, Boolean> availability;
    private boolean photoProvided;
    private boolean oversight;
    private String oversightComments;
    private boolean reliefAbroad;
    private String reliefAbroadComments;
    private boolean reliefUK;
    private String reliefUKComments;
    private String hhcFormCode;
    private String editNameUri;
    private String editAssignmentUri;
    private String editEmergencyContactUri;
    private String editCommentsUri;
    private String editSkillUri;
    private String editSpiritualUri;
    private String editPersonalUri;
    private String editRbcStatusUri;
    private String editImageUri;
    private String editRbcStatusCodeUri;
    private String editExperienceUri;
    private String editQualificationUri;
    private java.sql.Date contactDetailsLastConfirmed;

    /**
     * Determine if the volunteer is available on a given day of the week.
     *
     * @param dayOfWeek day of week, based on the Calendar DAY_OF_WEEK values
     * @return true if marked as available
     */
    public boolean isDayOfWeekAvailable(int dayOfWeek) {
        if (availability == null) {
            return false;
        }
        return availability.get(dayOfWeek);
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public java.sql.Date getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(java.sql.Date baptismDate) {
        this.baptismDate = baptismDate;
    }

    public PersonModel getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(PersonModel emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    public String getFulltime() {
        return fulltime;
    }

    public void setFulltime(String fulltime) {
        this.fulltime = fulltime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PersonModel getSpouse() {
        return spouse;
    }

    public void setSpouse(PersonModel spouse) {
        this.spouse = spouse;
    }

    public Set<VolunteerTrade> getTrades() {
        return trades;
    }

    public void setTrades(Set<VolunteerTrade> trades) {
        this.trades = trades;
    }

    public java.sql.Date getFormDate() {
        return formDate;
    }

    public void setFormDate(java.sql.Date formDate) {
        this.formDate = formDate;
    }

    public java.sql.Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(java.sql.Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public java.sql.Date getBadgeIssueDate() {
        return badgeIssueDate;
    }

    public void setBadgeIssueDate(java.sql.Date badgeIssueDate) {
        this.badgeIssueDate = badgeIssueDate;
    }

    public java.sql.Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(java.sql.Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public UserModel getInterviewerA() {
        return interviewerA;
    }

    public void setInterviewerA(UserModel interviewerA) {
        this.interviewerA = interviewerA;
    }

    public UserModel getInterviewerB() {
        return interviewerB;
    }

    public void setInterviewerB(UserModel interviewerB) {
        this.interviewerB = interviewerB;
    }

    public String getInterviewComments() {
        return interviewComments;
    }

    public void setInterviewComments(String interviewComments) {
        this.interviewComments = interviewComments;
    }

    public Map<Long, Boolean> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<Long, Boolean> availability) {
        this.availability = availability;
    }

    public boolean isPhotoProvided() {
        return photoProvided;
    }

    public void setPhotoProvided(boolean photoProvided) {
        this.photoProvided = photoProvided;
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

    public String getEditNameUri() {
        return editNameUri;
    }

    public void setEditNameUri(String editNameUri) {
        this.editNameUri = editNameUri;
    }

    public String getEditAssignmentUri() {
        return editAssignmentUri;
    }

    public void setEditAssignmentUri(String editAssignmentUri) {
        this.editAssignmentUri = editAssignmentUri;
    }

    public String getEditEmergencyContactUri() {
        return editEmergencyContactUri;
    }

    public void setEditEmergencyContactUri(String editEmergencyContactUri) {
        this.editEmergencyContactUri = editEmergencyContactUri;
    }

    public String getEditCommentsUri() {
        return editCommentsUri;
    }

    public void setEditCommentsUri(String editCommentsUri) {
        this.editCommentsUri = editCommentsUri;
    }

    public String getEditSkillUri() {
        return editSkillUri;
    }

    public void setEditSkillUri(String editSkillUri) {
        this.editSkillUri = editSkillUri;
    }

    public String getEditSpiritualUri() {
        return editSpiritualUri;
    }

    public void setEditSpiritualUri(String editSpiritualUri) {
        this.editSpiritualUri = editSpiritualUri;
    }

    public String getEditPersonalUri() {
        return editPersonalUri;
    }

    public void setEditPersonalUri(String editPersonalUri) {
        this.editPersonalUri = editPersonalUri;
    }

    public String getEditRbcStatusUri() {
        return editRbcStatusUri;
    }

    public void setEditRbcStatusUri(String editRbcStatusUri) {
        this.editRbcStatusUri = editRbcStatusUri;
    }

    public String getEditImageUri() {
        return editImageUri;
    }

    public void setEditImageUri(String editImageUri) {
        this.editImageUri = editImageUri;
    }

    public String getEditRbcStatusCodeUri() {
        return editRbcStatusCodeUri;
    }

    public void setEditRbcStatusCodeUri(String editRbcStatusCodeUri) {
        this.editRbcStatusCodeUri = editRbcStatusCodeUri;
    }

    /**
     * @return the editExperienceUri
     */
    public String getEditExperienceUri() {
        return editExperienceUri;
    }

    /**
     * @param editExperienceUri the editExperienceUri to set
     */
    public void setEditExperienceUri(String editExperienceUri) {
        this.editExperienceUri = editExperienceUri;
    }

    /**
     * @return the editQualificationUri
     */
    public String getEditQualificationUri() {
        return editQualificationUri;
    }

    /**
     * @param editQualificationUri the editQualificationUri to set
     */
    public void setEditQualificationUri(String editQualificationUri) {
        this.editQualificationUri = editQualificationUri;
    }

    public java.sql.Date getContactDetailsLastConfirmed() {
        return contactDetailsLastConfirmed;
    }

    public void setContactDetailsLastConfirmed(java.sql.Date contactDetailsLastConfirmed) {
        this.contactDetailsLastConfirmed = contactDetailsLastConfirmed;
    }
}
