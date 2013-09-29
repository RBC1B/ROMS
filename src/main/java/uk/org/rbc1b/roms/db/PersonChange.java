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

/**
 *
 * @author ramindursingh
 */
public class PersonChange implements Serializable {

    private static final long serialVersionUID = 0x1L;
    private Integer personChangeId;
    private Integer personId;
    private String oldForename;
    private String oldMiddleName;
    private String oldSurname;
    private Address oldAddress;
    private String oldTelephone;
    private String oldMobile;
    private String oldWorkPhone;
    private String oldEmail;
    private String newForename;
    private String newMiddleName;
    private String newSurname;
    private Address newAddress;
    private String newTelephone;
    private String newMobile;
    private String newWorkPhone;
    private String newEmail;
    private String comment;
    private java.sql.Date changeDate;
    private boolean formUpdated;

    /**
     * @return the personChangeId
     */
    public Integer getPersonChangeId() {
        return personChangeId;
    }

    /**
     * @param personChangeId the personChangeId to set
     */
    public void setPersonChangeId(Integer personChangeId) {
        this.personChangeId = personChangeId;
    }

    /**
     * @return the personId
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * @return the oldForename
     */
    public String getOldForename() {
        return oldForename;
    }

    /**
     * @param oldForename the oldForename to set
     */
    public void setOldForename(String oldForename) {
        this.oldForename = oldForename;
    }

    /**
     * @return the oldMiddleName
     */
    public String getOldMiddleName() {
        return oldMiddleName;
    }

    /**
     * @param oldMiddleName the oldMiddleName to set
     */
    public void setOldMiddleName(String oldMiddleName) {
        this.oldMiddleName = oldMiddleName;
    }

    /**
     * @return the oldSurname
     */
    public String getOldSurname() {
        return oldSurname;
    }

    /**
     * @param oldSurname the oldSurname to set
     */
    public void setOldSurname(String oldSurname) {
        this.oldSurname = oldSurname;
    }

    /**
     * @return the oldAddress
     */
    public Address getOldAddress() {
        return oldAddress;
    }

    /**
     * @param oldAddress the oldAddress to set
     */
    public void setOldAddress(Address oldAddress) {
        this.oldAddress = oldAddress;
    }

    /**
     * @return the oldTelephone
     */
    public String getOldTelephone() {
        return oldTelephone;
    }

    /**
     * @param oldTelephone the oldTelephone to set
     */
    public void setOldTelephone(String oldTelephone) {
        this.oldTelephone = oldTelephone;
    }

    /**
     * @return the oldMobile
     */
    public String getOldMobile() {
        return oldMobile;
    }

    /**
     * @param oldMobile the oldMobile to set
     */
    public void setOldMobile(String oldMobile) {
        this.oldMobile = oldMobile;
    }

    /**
     * @return the oldWorkPhone
     */
    public String getOldWorkPhone() {
        return oldWorkPhone;
    }

    /**
     * @param oldWorkPhone the oldWorkPhone to set
     */
    public void setOldWorkPhone(String oldWorkPhone) {
        this.oldWorkPhone = oldWorkPhone;
    }

    /**
     * @return the oldEmail
     */
    public String getOldEmail() {
        return oldEmail;
    }

    /**
     * @param oldEmail the oldEmail to set
     */
    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    /**
     * @return the newForename
     */
    public String getNewForename() {
        return newForename;
    }

    /**
     * @param newForename the newForename to set
     */
    public void setNewForename(String newForename) {
        this.newForename = newForename;
    }

    /**
     * @return the newMiddleName
     */
    public String getNewMiddleName() {
        return newMiddleName;
    }

    /**
     * @param newMiddleName the newMiddleName to set
     */
    public void setNewMiddleName(String newMiddleName) {
        this.newMiddleName = newMiddleName;
    }

    /**
     * @return the newSurname
     */
    public String getNewSurname() {
        return newSurname;
    }

    /**
     * @param newSurname the newSurname to set
     */
    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }

    /**
     * @return the newAddress
     */
    public Address getNewAddress() {
        return newAddress;
    }

    /**
     * @param newAddress the newAddress to set
     */
    public void setNewAddress(Address newAddress) {
        this.newAddress = newAddress;
    }

    /**
     * @return the newTelephone
     */
    public String getNewTelephone() {
        return newTelephone;
    }

    /**
     * @param newTelephone the newTelephone to set
     */
    public void setNewTelephone(String newTelephone) {
        this.newTelephone = newTelephone;
    }

    /**
     * @return the newMobile
     */
    public String getNewMobile() {
        return newMobile;
    }

    /**
     * @param newMobile the newMobile to set
     */
    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    /**
     * @return the newWorkPhone
     */
    public String getNewWorkPhone() {
        return newWorkPhone;
    }

    /**
     * @param newWorkPhone the newWorkPhone to set
     */
    public void setNewWorkPhone(String newWorkPhone) {
        this.newWorkPhone = newWorkPhone;
    }

    /**
     * @return the newEmail
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * @param newEmail the newEmail to set
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the changeDate
     */
    public java.sql.Date getChangeDate() {
        return changeDate;
    }

    /**
     * @param changeDate the changeDate to set
     */
    public void setChangeDate(java.sql.Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * @return the formUpdated
     */
    public boolean isFormUpdated() {
        return formUpdated;
    }

    /**
     * @param formUpdated the formUpdated to set
     */
    public void setFormUpdated(boolean formUpdated) {
        this.formUpdated = formUpdated;
    }

    @Override
    public String toString() {
        return "PersonChange{" + "personChangeId=" + personChangeId + '}';
    }
}
