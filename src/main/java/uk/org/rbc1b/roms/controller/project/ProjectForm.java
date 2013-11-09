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
package uk.org.rbc1b.roms.controller.project;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Form bean used when creating a new project.
 */
public class ProjectForm {
    private String constraints;
    private Integer coordinatorUserId;
    private String coordinatorUserName;
    @Size(max = 50)
    private String estimateCost;
    private String kingdomHallName;
    private Integer kingdomHallId;
    @NotNull
    @Size(max = 50)
    private String name;
    @NotNull
    private Integer projectTypeId;
    @Size(max = 50)
    private String priority;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime requestDate;
    @Size(max = 250)
    private String supportingCongregation;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime visitDate;

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public Integer getCoordinatorUserId() {
        return coordinatorUserId;
    }

    public void setCoordinatorUserId(Integer coordinatorUserId) {
        this.coordinatorUserId = coordinatorUserId;
    }

    public String getCoordinatorUserName() {
        return coordinatorUserName;
    }

    public void setCoordinatorUserName(String coordinatorUserName) {
        this.coordinatorUserName = coordinatorUserName;
    }

    public String getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(String estimateCost) {
        this.estimateCost = estimateCost;
    }

    public String getKingdomHallName() {
        return kingdomHallName;
    }

    public void setKingdomHallName(String kingdomHallName) {
        this.kingdomHallName = kingdomHallName;
    }

    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public DateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(DateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getSupportingCongregation() {
        return supportingCongregation;
    }

    public void setSupportingCongregation(String supportingCongregation) {
        this.supportingCongregation = supportingCongregation;
    }

    public DateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(DateTime visitDate) {
        this.visitDate = visitDate;
    }

}
