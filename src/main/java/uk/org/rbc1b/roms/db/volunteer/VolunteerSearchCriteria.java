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

import uk.org.rbc1b.roms.db.common.AbstractSearchCriteria;

/**
 * Criteria used when searching for a person.
 */
public class VolunteerSearchCriteria extends AbstractSearchCriteria {
    private Integer id;
    private String forename;
    private String surname;
    private Integer congregationId;
    private Integer departmentId;
    private Integer interviewSessionId;
    private Integer kingdomHallId;
    private String location;
    private Integer qualificationId;
    private Integer skillId;

    @Override
    public VolunteerSearchCriteria clone() {
        try {
            // no deep cloning required
            return (VolunteerSearchCriteria) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Failed to clone object");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getInterviewSessionId() {
        return interviewSessionId;
    }

    public void setInterviewSessionId(Integer interviewSessionId) {
        this.interviewSessionId = interviewSessionId;
    }

    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

}
