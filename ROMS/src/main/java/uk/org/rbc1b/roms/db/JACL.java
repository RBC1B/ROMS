/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author ramindursingh
 */
public class JACL {
    private Long id;
    private String userName;
    private String appName;
    private String acllevel;
    private int acl;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return the acllevel
     */
    public String getAcllevel() {
        return acllevel;
    }

    /**
     * @param acllevel the acllevel to set
     */
    public void setAcllevel(String acllevel) {
        this.acllevel = acllevel;
    }

    /**
     * @return the acl
     */
    public int getAcl() {
        return acl;
    }

    /**
     * @param acl the acl to set
     */
    public void setAcl(int acl) {
        this.acl = acl;
    }
}
