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
package uk.org.rbc1b.roms.controller.personchange;

/**
 *
 * @author ramindursingh
 */
public class PersonChangeModel {

    private Integer personChangeId;
    private Integer personId;
    private String oldSurname;
    private String oldForename;
    private String oldStreet;
    private String oldTown;
    private String oldCounty;
    private String oldPostcode;
    private String oldTelephone;
    private String oldWorkPhone;
    private String oldMobile;
    private String oldEmail;
    private String newSurname;
    private String newForename;
    private String newStreet;
    private String newTown;
    private String newCounty;
    private String newPostcode;
    private String newTelephone;
    private String newWorkPhone;
    private String newMobile;
    private String newEmail;
    private String updateUri;

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
     * @return the oldStreet
     */
    public String getOldStreet() {
        return oldStreet;
    }

    /**
     * @param oldStreet the oldStreet to set
     */
    public void setOldStreet(String oldStreet) {
        this.oldStreet = oldStreet;
    }

    /**
     * @return the oldTown
     */
    public String getOldTown() {
        return oldTown;
    }

    /**
     * @param oldTown the oldTown to set
     */
    public void setOldTown(String oldTown) {
        this.oldTown = oldTown;
    }

    /**
     * @return the oldCounty
     */
    public String getOldCounty() {
        return oldCounty;
    }

    /**
     * @param oldCounty the oldCounty to set
     */
    public void setOldCounty(String oldCounty) {
        this.oldCounty = oldCounty;
    }

    /**
     * @return the oldPostcode
     */
    public String getOldPostcode() {
        return oldPostcode;
    }

    /**
     * @param oldPostcode the oldPostcode to set
     */
    public void setOldPostcode(String oldPostcode) {
        this.oldPostcode = oldPostcode;
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
     * @return the newStreet
     */
    public String getNewStreet() {
        return newStreet;
    }

    /**
     * @param newStreet the newStreet to set
     */
    public void setNewStreet(String newStreet) {
        this.newStreet = newStreet;
    }

    /**
     * @return the newTown
     */
    public String getNewTown() {
        return newTown;
    }

    /**
     * @param newTown the newTown to set
     */
    public void setNewTown(String newTown) {
        this.newTown = newTown;
    }

    /**
     * @return the newCounty
     */
    public String getNewCounty() {
        return newCounty;
    }

    /**
     * @param newCounty the newCounty to set
     */
    public void setNewCounty(String newCounty) {
        this.newCounty = newCounty;
    }

    /**
     * @return the newPostcode
     */
    public String getNewPostcode() {
        return newPostcode;
    }

    /**
     * @param newPostcode the newPostcode to set
     */
    public void setNewPostcode(String newPostcode) {
        this.newPostcode = newPostcode;
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
     * @return the updateUri
     */
    public String getUpdateUri() {
        return updateUri;
    }

    /**
     * @param updateUri the updateUri to set
     */
    public void setUpdateUri(String updateUri) {
        this.updateUri = updateUri;
    }
}
