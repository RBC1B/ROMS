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
package uk.org.rbc1b.roms.db.application;

import org.hibernate.envers.Audited;
import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;
import uk.org.rbc1b.roms.db.Person;

/**
 * @author oliver.elder.esq
 */
@Audited
public class ApplicationAccess extends DefaultUpdateAuditable {
    private static final long serialVersionUID = -6714506443989985762L;
    private Integer applicationAccessId;
    private Person person;
    private Application application;
    private Integer departmentAccess;
    private Integer nonDepartmentAccess;
    private String name;

    public Integer getApplicationAccessId() {
        return applicationAccessId;
    }

    public void setApplicationAccessId(Integer applicationAccessId) {
        this.applicationAccessId = applicationAccessId;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Integer getDepartmentAccess() {
        return departmentAccess;
    }

    public void setDepartmentAccess(Integer departmentAccess) {
        this.departmentAccess = departmentAccess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNonDepartmentAccess() {
        return nonDepartmentAccess;
    }

    public void setNonDepartmentAccess(Integer nonDepartmentAccess) {
        this.nonDepartmentAccess = nonDepartmentAccess;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "ApplicationAccess{" + "applicationAccessId=" + applicationAccessId + '}';
    }
}
