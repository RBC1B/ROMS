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
package uk.org.rbc1b.roms.db.project;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.envers.Audited;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.UpdateAuditable;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * A unit of work to be performed at a given site (kingdom hall or address).
 *
 * @author oliver.elder.esq
 */
@Audited
public class Project implements UpdateAuditable, Serializable {

    private static final long serialVersionUID = 9175072301683424474L;
    private Integer projectId;
    private String name;
    private KingdomHall kingdomHall;
    private Person coordinator;
    private boolean minorWork;
    private java.sql.Date requestDate;
    private java.sql.Date completedDate;
    private Date updateTime;
    private Integer updatedBy;

    /**
     * @return the projectId
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
     * @return the kingdomHall
     */
    public KingdomHall getKingdomHall() {
        return kingdomHall;
    }

    /**
     * @param kingdomHall the kingdomHall to set
     */
    public void setKingdomHall(KingdomHall kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    /**
     * @return the coordinator
     */
    public Person getCoordinator() {
        return coordinator;
    }

    /**
     * @param coordinator the coordinator to set
     */
    public void setCoordinator(Person coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * @return the minorWork
     */
    public boolean isMinorWork() {
        return minorWork;
    }

    /**
     * @param minorWork the minorWork to set
     */
    public void setMinorWork(boolean minorWork) {
        this.minorWork = minorWork;
    }

    /**
     * @return the requestDate
     */
    public java.sql.Date getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(java.sql.Date requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the completedDate
     */
    public java.sql.Date getCompletedDate() {
        return completedDate;
    }

    /**
     * @param completedDate the completedDate to set
     */
    public void setCompletedDate(java.sql.Date completedDate) {
        this.completedDate = completedDate;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "Project{" + "projectId=" + getProjectId() + '}';
    }
}
