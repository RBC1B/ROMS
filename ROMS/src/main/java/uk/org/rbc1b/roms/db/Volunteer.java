/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author ramindursingh
 */
public class Volunteer  implements java.io.Serializable {
    private Long rbcid;
    private String forename;
    private String surname;
    private String middleName;
    private String pictureName;
    private EmergencyContact econtact;

    public Volunteer(){
        // default constructor required by hibernate
    }

    public Volunteer(Long rbcid, String forename, String surname){
        this.rbcid = rbcid;
        this.forename = forename;
        this.surname = surname;
    }

    public Volunteer(Long rbcid, String forename, String surname, String middleName, String pictureName){
        this.rbcid = rbcid;
        this.forename = forename;
        this.surname = surname;
        this.middleName = middleName;
        this.pictureName = pictureName;
    }

    /**
     * @return the rbcid
     */
    public Long getRbcid() {
        return rbcid;
    }

    /**
     * @param rbcid the rbcid to set
     */
    public void setRbcid(Long rbcid) {
        this.rbcid = rbcid;
    }

    /**
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * @param forename the forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the pictureName
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * @param pictureName the pictureName to set
     */
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    /**
     * @return the econtact
     */
    public EmergencyContact getEcontact() {
        return econtact;
    }

    /**
     * @param econtact the econtact to set
     */
    public void setEcontact(EmergencyContact econtact) {
        this.econtact = econtact;
    }

    public String getHTMLTable(){
        String html="<table>"
                + "<tr><td>test</td></tr>"
                + "</table>";
        return html;
    }
}
