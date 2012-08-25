/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.Date;

/**
 *
 * @author ramindursingh
 */
public class User {

    private String id;
    private Integer rbcid;
    private Integer pin;
    private String comments;
    private String password;
    private Date lastchange;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the rbcid
     */
    public Integer getRbcid() {
        return rbcid;
    }

    /**
     * @param rbcid the rbcid to set
     */
    public void setRbcid(Integer rbcid) {
        this.rbcid = rbcid;
    }

    /**
     * @return the pin
     */
    public Integer getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(Integer pin) {
        this.pin = pin;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastchange
     */
    public Date getLastchange() {
        return lastchange;
    }

    /**
     * @param lastchange the lastchanged to set
     */
    public void setLastchange(Date lastchange) {
        this.lastchange = lastchange;
    }
}
