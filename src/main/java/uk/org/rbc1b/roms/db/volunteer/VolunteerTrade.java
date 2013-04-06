/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import uk.org.rbc1b.roms.db.Person;

/**
 * Captures the data entered by the volunteer in their form.
 *
 * @author oliver.elder.esq
 */
public class VolunteerTrade {

    private Integer volunteerTradeId;
    private Person person;
    private String name;
    private String experienceDescription;
    private Integer experienceYears;

    public Integer getVolunteerTradeId() {
        return volunteerTradeId;
    }

    public void setVolunteerTradeId(Integer volunteerTradeId) {
        this.volunteerTradeId = volunteerTradeId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperienceDescription() {
        return experienceDescription;
    }

    public void setExperienceDescription(String experienceDescription) {
        this.experienceDescription = experienceDescription;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }
}
