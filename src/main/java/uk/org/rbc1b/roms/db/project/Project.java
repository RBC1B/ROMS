package uk.org.rbc1b.roms.db.project;

import java.util.Date;
import java.util.Set;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * @author oliver.elder.esq
 */
public class Project {

    private Address address;
    private String constraints;
    private Date completedDate;
    private Person contactPerson;
    private Person coordinator;
    private String estimateCost;
    private KingdomHall kingdomHall;
    private String name;
    private Integer projectId;
    private Integer projectTypeId;
    private String priority;
    private Date requestDate;
    private ProjectStage stage;
    private ProjectStatus status;
    private String supportingCongregation;
    private String telephone;
    private Date visitDate;
    private Set<ProjectWorkBrief> workBriefs;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public Person getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Person contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Person getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Person coordinator) {
        this.coordinator = coordinator;
    }

    public String getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(String estimateCost) {
        this.estimateCost = estimateCost;
    }

    public KingdomHall getKingdomHall() {
        return kingdomHall;
    }

    public void setKingdomHall(KingdomHall kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
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

    public Set<ProjectWorkBrief> getWorkBriefs() {
        return workBriefs;
    }

    public void setWorkBriefs(Set<ProjectWorkBrief> workBriefs) {
        this.workBriefs = workBriefs;
    }

    @Override
    public String toString() {
        return "Project{" + "projectId=" + projectId + '}';
    }
}
