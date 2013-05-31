/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import uk.org.rbc1b.roms.controller.common.model.PersonModel;
import uk.org.rbc1b.roms.db.volunteer.VolunteerTrade;

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
    private PersonModel interviewerA;
    private PersonModel interviewerB;
    private String interviewComments;
    private Map<Integer, Boolean> availability;
    private boolean oversight;
    private String oversightComments;
    private boolean reliefAbroad;
    private String reliefAbroadComments;
    private boolean reliefUK;
    private String reliefUKComments;
    private String hhcFormCode;
    private List<AssignmentModel> assignments;
    private List<VolunteerSkillModel> skills;

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

    public Date getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(Date baptismDate) {
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

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Date getBadgeIssueDate() {
        return badgeIssueDate;
    }

    public void setBadgeIssueDate(Date badgeIssueDate) {
        this.badgeIssueDate = badgeIssueDate;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public PersonModel getInterviewerA() {
        return interviewerA;
    }

    public void setInterviewerA(PersonModel interviewerA) {
        this.interviewerA = interviewerA;
    }

    public PersonModel getInterviewerB() {
        return interviewerB;
    }

    public void setInterviewerB(PersonModel interviewerB) {
        this.interviewerB = interviewerB;
    }

    public String getInterviewComments() {
        return interviewComments;
    }

    public void setInterviewComments(String interviewComments) {
        this.interviewComments = interviewComments;
    }

    public Map<Integer, Boolean> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<Integer, Boolean> availability) {
        this.availability = availability;
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

    public List<AssignmentModel> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentModel> assignments) {
        this.assignments = assignments;
    }

    public List<VolunteerSkillModel> getSkills() {
        return skills;
    }

    public void setSkills(List<VolunteerSkillModel> skills) {
        this.skills = skills;
    }
}
