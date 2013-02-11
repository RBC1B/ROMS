/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import uk.org.rbc1b.roms.db.Person;

/**
 *
 * @author oliver.elder.esq
 */
public class Volunteer extends Person {

    private RbcStatus rbcStatus;
    private Appointment appointment;
    private Fulltime fulltime;
    private String availability;   // 7 char string, representing T or F, Monday to Sunday
    private Person emergencyContact;
    private Relationship emergencyContactRelationship;
    private String gender;    // M or F
    private MaritalStatus maritalStatus;
    private java.sql.Date baptismDate;
    private java.sql.Date interviewDate;
    private Person interviewerA;
    private Person interviewerB;
    private String interviewComments;
    private java.sql.Date joinedDate;
    private java.sql.Date formDate;
    private InterviewStatus interviewStatus;
    private boolean oversight;
    private String oversightComments;
    private boolean reliefUK;
    private String reliefUKComments;
    private boolean reliefAbroad;
    private String reliefAbroadComments;
    private String hhcFormCode;
    private java.sql.Date badgeIssueDate;

    /**
     * Default constructor.
     */
    public Volunteer() {
        // do nothing
    }

    /**
     * Instantiate a volunteer from the person.
     * @param person underlying person to be extended
     */
    public Volunteer(Person person) {
        this.setPersonId(person.getPersonId());
        this.setBirthDate(person.getBirthDate());
        this.setCongregation(person.getCongregation());
        this.setForename(person.getForename());
        this.setMiddleName(person.getMiddleName());
        this.setSurname(person.getSurname());
        this.setAddress(person.getAddress());
        this.setTelephone(person.getTelephone());
        this.setMobile(person.getMobile());
        this.setWorkPhone(person.getWorkPhone());
        this.setEmail(person.getEmail());
        this.setComments(person.getComments());
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public java.sql.Date getBadgeIssueDate() {
        return badgeIssueDate;
    }

    public void setBadgeIssueDate(java.sql.Date badgeIssueDate) {
        this.badgeIssueDate = badgeIssueDate;
    }

    public java.sql.Date getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(java.sql.Date baptismDate) {
        this.baptismDate = baptismDate;
    }

    public Person getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(Person emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Relationship getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(Relationship emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    public java.sql.Date getFormDate() {
        return formDate;
    }

    public void setFormDate(java.sql.Date formDate) {
        this.formDate = formDate;
    }

    public Fulltime getFulltime() {
        return fulltime;
    }

    public void setFulltime(Fulltime fulltime) {
        this.fulltime = fulltime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHhcFormCode() {
        return hhcFormCode;
    }

    public void setHhcFormCode(String hhcFormCode) {
        this.hhcFormCode = hhcFormCode;
    }

    public String getInterviewComments() {
        return interviewComments;
    }

    public void setInterviewComments(String interviewComments) {
        this.interviewComments = interviewComments;
    }

    public java.sql.Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(java.sql.Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public Person getInterviewerA() {
        return interviewerA;
    }

    public void setInterviewerA(Person interviewerA) {
        this.interviewerA = interviewerA;
    }

    public Person getInterviewerB() {
        return interviewerB;
    }

    public void setInterviewerB(Person interviewerB) {
        this.interviewerB = interviewerB;
    }

    public java.sql.Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(java.sql.Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public RbcStatus getRbcStatus() {
        return rbcStatus;
    }

    public void setRbcStatus(RbcStatus rbcStatus) {
        this.rbcStatus = rbcStatus;
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

    @Override
    public String toString() {
        return "Volunteer{" + "personId=" + super.getPersonId() + '}';
    }
}
