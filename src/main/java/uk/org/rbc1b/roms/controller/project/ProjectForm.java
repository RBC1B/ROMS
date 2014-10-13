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
package uk.org.rbc1b.roms.controller.project;

import org.joda.time.DateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.db.project.Project;

/**
 *
 * @author ramindursingh
 */
public class ProjectForm {

    private Integer projectId;
    @NotNull
    @Size(min = 5, max = 250)
    private String name;
    @NotNull
    private Integer kingdomHallId;
    private String kingdomHallName;
    private Integer coordinatorId;
    private String surname;
    private String forename;
    private boolean minorWork;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime requestDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime completedDate;

    /**
     * Default no argument constructor.
     */
    public ProjectForm() {
    }

    /**
     * Constructor with project argument. Note: Make sure that all the
     * properties are loaded, not lazy.
     *
     * @param project the project
     */
    public ProjectForm(Project project) {
        this.projectId = project.getProjectId();
        this.name = project.getName();
        this.kingdomHallId = project.getKingdomHall().getKingdomHallId();
        this.kingdomHallName = project.getKingdomHall().getName();
        if (project.getCoordinator() != null) {
            this.coordinatorId = project.getCoordinator().getPersonId();
            this.forename = project.getCoordinator().getForename();
            this.surname = project.getCoordinator().getSurname();
        }
        this.minorWork = project.isMinorWork();
        this.requestDate = DataConverterUtil.toDateTime(project.getRequestDate());
        if (project.getCompletedDate() != null) {
            this.completedDate = DataConverterUtil.toDateTime(project.getCompletedDate());
        }
    }

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
     * @return the kingdomHallId
     */
    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    /**
     * @param kingdomHallId the kingdomHallId to set
     */
    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    /**
     * @return the kingdomHallName
     */
    public String getKingdomHallName() {
        return kingdomHallName;
    }

    /**
     * @param kingdomHallName the kingdomHallName to set
     */
    public void setKingdomHallName(String kingdomHallName) {
        this.kingdomHallName = kingdomHallName;
    }

    /**
     * @return the coordinatorId
     */
    public Integer getCoordinatorId() {
        return coordinatorId;
    }

    /**
     * @param coordinatorId the coordinatorId to set
     */
    public void setCoordinatorId(Integer coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * @param forename the forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
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
    public DateTime getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(DateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the completedDate
     */
    public DateTime getCompletedDate() {
        return completedDate;
    }

    /**
     * @param completedDate the completedDate to set
     */
    public void setCompletedDate(DateTime completedDate) {
        this.completedDate = completedDate;
    }
}
