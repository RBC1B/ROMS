/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.sql.Timestamp;

/**
 * Mapping of data that records data changes.
 *
 * @author oliver.elder.esq
 */
public class Updates {

    private Integer updatesId;
    private String updatedTable;
    private Person person;  // updater
    private String updateInformation;
    private Timestamp updateTime;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUpdateInformation() {
        return updateInformation;
    }

    public void setUpdateInformation(String updateInformation) {
        this.updateInformation = updateInformation;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatedTable() {
        return updatedTable;
    }

    public void setUpdatedTable(String updatedTable) {
        this.updatedTable = updatedTable;
    }

    public Integer getUpdatesId() {
        return updatesId;
    }

    public void setUpdatesId(Integer updatesId) {
        this.updatesId = updatesId;
    }

    @Override
    public String toString() {
        return "Updates{" + "updatesId=" + updatesId + '}';
    }
}
