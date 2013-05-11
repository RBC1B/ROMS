/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.project;

import java.util.Date;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModel;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.project.ProjectStage;

/**
 * Model representing the project.
 *
 * @author oliver
 */
public class ProjectModel {

    private Address address;
    private String constraints;
    private Date completedDate;
    private PersonModel contactPerson;
    private PersonModel coordinator;
    private String estimateCost;
    private EntityModel kingdomHall;
    private String name;
    private Integer projectId;
    private String type;
    private String priority;
    private Date requestDate;
    private ProjectStage stage;
    private String status;
    private String supportingCongregation;
    private String telephone;
    private Date visitDate;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public PersonModel getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(PersonModel contactPerson) {
        this.contactPerson = contactPerson;
    }

    public PersonModel getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(PersonModel coordinator) {
        this.coordinator = coordinator;
    }

    public String getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(String estimateCost) {
        this.estimateCost = estimateCost;
    }

    public EntityModel getKingdomHall() {
        return kingdomHall;
    }

    public void setKingdomHall(EntityModel kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public ProjectStage getStage() {
        return stage;
    }

    public void setStage(ProjectStage stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupportingCongregation() {
        return supportingCongregation;
    }

    public void setSupportingCongregation(String supportingCongregation) {
        this.supportingCongregation = supportingCongregation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
