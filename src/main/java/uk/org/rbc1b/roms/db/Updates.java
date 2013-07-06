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
