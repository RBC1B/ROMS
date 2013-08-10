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

import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;

/**
 * A qualification owned by a volunteer.
 * @author oliver.elder.esq
 */
public class VolunteerQualification extends DefaultUpdateAuditable {
    private static final long serialVersionUID = -7960214728374952774L;
    private Integer volunteerQualificationId;
    private Integer personId;
    private Integer qualificationId;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Integer getVolunteerQualificationId() {
        return volunteerQualificationId;
    }

    public void setVolunteerQualificationId(Integer volunteerQualificationId) {
        this.volunteerQualificationId = volunteerQualificationId;
    }

    @Override
    public String toString() {
        return "VolunteerQualification{" + "volunteerQualificationId=" + volunteerQualificationId + '}';
    }
}
