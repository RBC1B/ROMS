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

import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 *
 * @author ramindursingh
 */
public class ProjectModel {

    private Integer projectId;
    private String name;
    private EntityModel kingdomHall;
    private EntityModel coordinator;
    private boolean minorWork;
    private java.sql.Date requestDate;
    private java.sql.Date completedDate;
    private String uri;
    private String editUri;
    private String modifyDepartmentSessionUri;

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
    public EntityModel getKingdomHall() {
        return kingdomHall;
    }

    /**
     * @param kingdomHall the kingdomHall to set
     */
    public void setKingdomHall(EntityModel kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    /**
     * @return the coordinator
     */
    public EntityModel getCoordinator() {
        return coordinator;
    }

    /**
     * @param coordinator the coordinator to set
     */
    public void setCoordinator(EntityModel coordinator) {
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

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the editUri
     */
    public String getEditUri() {
        return editUri;
    }

    /**
     * @param editUri the editUri to set
     */
    public void setEditUri(String editUri) {
        this.editUri = editUri;
    }

    /**
     * @return the modifyDepartmentSessionUri
     */
    public String getModifyDepartmentSessionUri() {
        return modifyDepartmentSessionUri;
    }

    /**
     * @param modifyDepartmentSessionUri the modifyDepartmentSessionUri to set
     */
    public void setModifyDepartmentSessionUri(String modifyDepartmentSessionUri) {
        this.modifyDepartmentSessionUri = modifyDepartmentSessionUri;
    }
}
