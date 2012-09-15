/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class Department {

    private Integer departmentId;
    private String name;
    private Department superDepartment;
    private String description;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getSuperDepartment() {
        return superDepartment;
    }

    public void setSuperDepartment(Department superDepartment) {
        this.superDepartment = superDepartment;
    }

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + '}';
    }
}
