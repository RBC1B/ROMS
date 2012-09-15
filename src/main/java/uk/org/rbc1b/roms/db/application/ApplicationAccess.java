/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.application;

import uk.org.rbc1b.roms.db.Person;

/**
 *
 * @author oliver.elder.esq
 */
public class ApplicationAccess {

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
