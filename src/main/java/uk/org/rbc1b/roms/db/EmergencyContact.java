/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author ramindursingh
 */
public class EmergencyContact {

    private Long rbcid;
    private String ename;
    private String erelation;
    private String etelephone;
    private String emobile;
    private String estreet;
    private String etown;
    private String ecounty;
    private String epostcode;
    private Volunteer volunteer;

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
     * @return the ename
     */
    public String getEname() {
        return ename;
    }

    /**
     * @param ename the ename to set
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * @return the erelation
     */
    public String getErelation() {
        return erelation;
    }

    /**
     * @param erelation the erelation to set
     */
    public void setErelation(String erelation) {
        this.erelation = erelation;
    }

    /**
     * @return the etelephone
     */
    public String getEtelephone() {
        return etelephone;
    }

    /**
     * @param etelephone the etelephone to set
     */
    public void setEtelephone(String etelephone) {
        this.etelephone = etelephone;
    }

    /**
     * @return the emobile
     */
    public String getEmobile() {
        return emobile;
    }

    /**
     * @param emobile the emobile to set
     */
    public void setEmobile(String emobile) {
        this.emobile = emobile;
    }

    /**
     * @return the estreet
     */
    public String getEstreet() {
        return estreet;
    }

    /**
     * @param estreet the estreet to set
     */
    public void setEstreet(String estreet) {
        this.estreet = estreet;
    }

    /**
     * @return the etown
     */
    public String getEtown() {
        return etown;
    }

    /**
     * @param etown the etown to set
     */
    public void setEtown(String etown) {
        this.etown = etown;
    }

    /**
     * @return the ecounty
     */
    public String getEcounty() {
        return ecounty;
    }

    /**
     * @param ecounty the ecounty to set
     */
    public void setEcounty(String ecounty) {
        this.ecounty = ecounty;
    }

    /**
     * @return the epostcode
     */
    public String getEpostcode() {
        return epostcode;
    }

    /**
     * @param epostcode the epostcode to set
     */
    public void setEpostcode(String epostcode) {
        this.epostcode = epostcode;
    }

    /**
     * @return the volunteer
     */
    public Volunteer getVolunteer() {
        return volunteer;
    }

    /**
     * @param volunteer the volunteer to set
     */
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}
