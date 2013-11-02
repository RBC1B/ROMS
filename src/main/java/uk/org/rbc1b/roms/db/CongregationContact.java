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
package uk.org.rbc1b.roms.db;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.envers.Audited;

/**
 * Linkage of person to congregation.
 * @author oliver.elder.esq
 */
@Audited
public class CongregationContact implements UpdateAuditable, Serializable {

    public static final String COORDINATOR_ROLE = "CB";
    public static final String SECRETARY_ROLE = "SC";

    private static final long serialVersionUID = 4754412345307010085L;
    private Integer congregationContactId;
    private Congregation congregation;
    private String congregationRoleCode;
    private Person person;
    private Date updateTime;
    private Integer updatedBy;

    public Congregation getCongregation() {
        return congregation;
    }

    public void setCongregation(Congregation congregation) {
        this.congregation = congregation;
    }

    public Integer getCongregationContactId() {
        return congregationContactId;
    }

    public void setCongregationContactId(Integer congregationContactId) {
        this.congregationContactId = congregationContactId;
    }

    public String getCongregationRoleCode() {
        return congregationRoleCode;
    }

    public void setCongregationRoleCode(String congregationRoleCode) {
        this.congregationRoleCode = congregationRoleCode;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "CongregationContact{" + "congregationContactId=" + congregationContactId + '}';
    }
}
