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
