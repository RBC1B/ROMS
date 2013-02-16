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

    private Integer appointmentId;
    private String availability;   // 7 char string, representing T or F, Monday to Sunday
    private Person emergencyContact;
    private Integer emergencyContactRelationshipId;
    private Integer fulltimeId;
    private String gender;    // M or F
    private Integer maritalStatusId;
    private java.sql.Date baptismDate;
    private java.sql.Date interviewDate;
    private Person interviewerA;
    private Person interviewerB;
    private String interviewComments;
    private java.sql.Date joinedDate;
    private java.sql.Date formDate;
    private Integer interviewStatusId;
    private boolean oversight;
    private String oversightComments;
    private Integer rbcStatusId;
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
     *
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

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
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

    public Integer getEmergencyContactRelationshipId() {
        return emergencyContactRelationshipId;
    }

    public void setEmergencyContactRelationshipId(Integer emergencyContactRelationshipId) {
        this.emergencyContactRelationshipId = emergencyContactRelationshipId;
    }

    public java.sql.Date getFormDate() {
        return formDate;
    }

    public void setFormDate(java.sql.Date formDate) {
        this.formDate = formDate;
    }

    public Integer getFulltimeId() {
        return fulltimeId;
    }

    public void setFulltimeId(Integer fulltimeId) {
        this.fulltimeId = fulltimeId;
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

    public Integer getInterviewStatusId() {
        return interviewStatusId;
    }

    public void setInterviewStatusId(Integer interviewStatusId) {
        this.interviewStatusId = interviewStatusId;
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

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
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

    public Integer getRbcStatusId() {
        return rbcStatusId;
    }

    public void setRbcStatusId(Integer rbcStatusId) {
        this.rbcStatusId = rbcStatusId;
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
