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

import java.util.Set;
import org.hibernate.envers.Audited;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTrade;

/**
 * @author oliver.elder.esq
 */
@Audited
public class Volunteer extends Person {
    private static final long serialVersionUID = -8454242375027482447L;
    private String appointmentCode;
    private String availability; // 7 char string, representing T or F, Monday to Sunday
    private Person emergencyContact;
    private Integer emergencyContactRelationshipId;
    private String fulltimeCode;
    private String gender; // M or F
    private Integer maritalStatusId;
    private java.sql.Date baptismDate;
    private java.sql.Date interviewDate;
    private User interviewerA;
    private User interviewerB;
    private String interviewComments;
    private java.sql.Date joinedDate;
    private java.sql.Date formDate;
    private String interviewStatusCode;
    private boolean oversight;
    private String oversightComments;
    private String rbcStatusCode;
    private boolean reliefUK;
    private String reliefUKComments;
    private boolean reliefAbroad;
    private String reliefAbroadComments;
    private Person spouse;
    private String hhcFormCode;
    private java.sql.Date badgeIssueDate;
    private Set<VolunteerTrade> trades;

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

    public String getAppointmentCode() {
        return appointmentCode;
    }

    public void setAppointmentCode(String appointmentCode) {
        this.appointmentCode = appointmentCode;
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

    public String getFulltimeCode() {
        return fulltimeCode;
    }

    public void setFulltimeCode(String fulltimeCode) {
        this.fulltimeCode = fulltimeCode;
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

    public String getInterviewStatusCode() {
        return interviewStatusCode;
    }

    public void setInterviewStatusCode(String interviewStatusCode) {
        this.interviewStatusCode = interviewStatusCode;
    }

    public User getInterviewerA() {
        return interviewerA;
    }

    public void setInterviewerA(User interviewerA) {
        this.interviewerA = interviewerA;
    }

    public User getInterviewerB() {
        return interviewerB;
    }

    public void setInterviewerB(User interviewerB) {
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

    public String getRbcStatusCode() {
        return rbcStatusCode;
    }

    public void setRbcStatusCode(String rbcStatusCode) {
        this.rbcStatusCode = rbcStatusCode;
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

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Set<VolunteerTrade> getTrades() {
        return trades;
    }

    public void setTrades(Set<VolunteerTrade> trades) {
        this.trades = trades;
    }

    @Override
    public String toString() {
        return "Volunteer{" + "personId=" + super.getPersonId() + '}';
    }
}
