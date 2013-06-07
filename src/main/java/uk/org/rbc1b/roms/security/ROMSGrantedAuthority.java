/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Granted authority, with specific levels for department and non-department access.
 *
 * @author oliver.elder.esq
 */
public class ROMSGrantedAuthority implements GrantedAuthority {

    private String application;
    private Integer departmentLevelAccess;
    private Integer nonDepartmentLevelAccess;

    @Override
    public String getAuthority() {
        return departmentLevelAccess.toString();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Integer getDepartmentLevelAccess() {
        return departmentLevelAccess;
    }

    public void setDepartmentLevelAccess(Integer departmentLevelAccess) {
        this.departmentLevelAccess = departmentLevelAccess;
    }

    public Integer getNonDepartmentLevelAccess() {
        return nonDepartmentLevelAccess;
    }

    public void setNonDepartmentLevelAccess(Integer nonDepartmentLevelAccess) {
        this.nonDepartmentLevelAccess = nonDepartmentLevelAccess;
    }
}
