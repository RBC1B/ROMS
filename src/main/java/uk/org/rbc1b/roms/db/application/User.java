/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.application;

import java.util.Set;

/**
 *
 * @author oliver.elder.esq
 */
public class User {

    private Set<ApplicationAccess> applicationAccess;
    private Integer personId;
    private String userName;
    private String password;

    public Set<ApplicationAccess> getApplicationAccess() {
        return applicationAccess;
    }

    public void setApplicationAccess(Set<ApplicationAccess> applicationAccess) {
        this.applicationAccess = applicationAccess;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" + "personId=" + personId + '}';
    }
}
