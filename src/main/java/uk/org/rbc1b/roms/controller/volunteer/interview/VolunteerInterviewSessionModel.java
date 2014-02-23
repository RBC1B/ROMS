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
 * Model for a person invited to an interview.
 */
public class VolunteerInterviewSessionModel {
    private Integer id;
    private String forename;
    private String surname;
    private EntityModel congregation;
    private String rbcSubRegion;
    private String comments;
    private String interviewStatus;
    private String interviewStatusCode;
    private String uri;
    private String volunteerUri;

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

    public EntityModel getCongregation() {
        return congregation;
    }

    public void setCongregation(EntityModel congregation) {
        this.congregation = congregation;
    }

    public String getRbcSubRegion() {
        return rbcSubRegion;
    }

    public void setRbcSubRegion(String rbcSubRegion) {
        this.rbcSubRegion = rbcSubRegion;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getInterviewStatusCode() {
        return interviewStatusCode;
    }

    public void setInterviewStatusCode(String interviewStatusCode) {
        this.interviewStatusCode = interviewStatusCode;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVolunteerUri() {
        return volunteerUri;
    }

    public void setVolunteerUri(String volunteerUri) {
        this.volunteerUri = volunteerUri;
    }

}
