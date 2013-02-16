/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author oliver.elder.esq
 */
public class CongregationContact {

    private Integer congregationContactId;
    private Congregation congregation;
    private Integer congregationRoleId;
    private Person person;

    public Congregation getCongregation() {
        return congregation;
    }

    public void setCongregation(Congregation congregation) {
        this.congregation = congregation;
    }

    public Integer getCongregationContactId() {
        return congregationContactId;
    }

    public void setCongregationContactId(Integer congregationContactId) {
        this.congregationContactId = congregationContactId;
    }

    public Integer getCongregationRoleId() {
        return congregationRoleId;
    }

    public void setCongregationRoleId(Integer congregationRoleId) {
        this.congregationRoleId = congregationRoleId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "CongregationContact{" + "congregationContactId=" + congregationContactId + '}';
    }
}
