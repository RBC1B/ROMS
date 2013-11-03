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
package uk.org.rbc1b.roms.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Granted authority, with specific levels for department and non-department access.
 * @author oliver.elder.esq
 */
public class ROMSGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 7170344476765512185L;
    private Application application;
    private AccessLevel departmentLevelAccess;
    private AccessLevel nonDepartmentLevelAccess;

    @Override
    public String getAuthority() {
        return departmentLevelAccess.toString();
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public AccessLevel getDepartmentLevelAccess() {
        return departmentLevelAccess;
    }

    public void setDepartmentLevelAccess(AccessLevel departmentLevelAccess) {
        this.departmentLevelAccess = departmentLevelAccess;
    }

    public AccessLevel getNonDepartmentLevelAccess() {
        return nonDepartmentLevelAccess;
    }

    public void setNonDepartmentLevelAccess(AccessLevel nonDepartmentLevelAccess) {
        this.nonDepartmentLevelAccess = nonDepartmentLevelAccess;
    }

    @Override
    public String toString() {
        return "ROMSGrantedAuthority [application=" + application + ", departmentLevelAccess=" + departmentLevelAccess
                + ", nonDepartmentLevelAccess=" + nonDepartmentLevelAccess + "]";
    }

}
