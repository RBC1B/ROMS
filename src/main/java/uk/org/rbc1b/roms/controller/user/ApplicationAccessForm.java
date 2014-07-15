/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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
package uk.org.rbc1b.roms.controller.user;

/**
 *
 * @author ramindursingh
 */
public class ApplicationAccessForm {

    private Integer applicationAccessId;
    private String name;
    private String code;
    private char deptPermission;
    private char nonDeptPermission;

    /**
     * @return the applicationAccessId
     */
    public Integer getApplicationAccessId() {
        return applicationAccessId;
    }

    /**
     * @param applicationAccessId the applicationAccessId to set
     */
    public void setApplicationAccessId(Integer applicationAccessId) {
        this.applicationAccessId = applicationAccessId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the deptPermission
     */
    public char getDeptPermission() {
        return deptPermission;
    }

    /**
     * @param deptPermission the deptPermission to set
     */
    public void setDeptPermission(char deptPermission) {
        this.deptPermission = deptPermission;
    }

    /**
     * @return the nonDeptPermission
     */
    public char getNonDeptPermission() {
        return nonDeptPermission;
    }

    /**
     * @param nonDeptPermission the nonDeptPermission to set
     */
    public void setNonDeptPermission(char nonDeptPermission) {
        this.nonDeptPermission = nonDeptPermission;
    }
}
