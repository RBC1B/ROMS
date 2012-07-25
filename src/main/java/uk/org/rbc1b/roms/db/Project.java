package uk.org.rbc1b.roms.db;
// Generated Apr 12, 2012 2:36:09 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;

/**
 * Project generated by hbm2java
 */
public class Project implements java.io.Serializable {

    private String projectName;
    private KingdomHall kingdomHall;
    private ProjectType projectType;
    private String priority;
    private String projectStreet;
    private String projectTown;
    private String projectCounty;
    private String projectPostcode;
    private String projectTelephone;
    private String contactName;
    private String contactStreet;
    private String contactTown;
    private String contactCounty;
    private String contactPostcode;
    private String contactTelephone;
    private String contactMobile;
    private String contactEmail;
    private Date requestDate;
    private Date initialVisitDate;
    private String designBrief;
    private String mainHallWork;
    private String schoolWork;
    private String toiletWork;
    private String foyerWork;
    private String exteriorWork;
    private String carParkWork;
    private String landscapeWork;
    private String otherWork;
    private String fundsOnHand;
    private String avgMonthContribution;
    private String currentLoans;
    private String projectEstimateCost;
    private String status;
    private String supportingCong;
    private String comments;
    private String updateInfo;
    private Long coordinator;
    private Long chairman;
    private Long member;
    private String workRbc;
    private String workDesign;
    private String workProj;
    private String projectInfo;
    private String stage;
    private Date completedDate;

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public Project(String projectName, KingdomHall kingdomHall, ProjectType projectType, String priority, String projectStreet, String projectTown, String projectCounty, String projectPostcode, String projectTelephone, String contactName, String contactStreet, String contactTown, String contactCounty, String contactPostcode, String contactTelephone, String contactMobile, String contactEmail, Date requestDate, Date initialVisitDate, String designBrief, String mainHallWork, String schoolWork, String toiletWork, String foyerWork, String exteriorWork, String carParkWork, String landscapeWork, String otherWork, String fundsOnHand, String avgMonthContribution, String currentLoans, String projectEstimateCost, String status, String supportingCong, String comments, String updateInfo, Long coordinator, Long chairman, Long member, String workRbc, String workDesign, String workProj, String projectInfo, String stage, Date completedDate) {
        this.projectName = projectName;
        this.kingdomHall = kingdomHall;
        this.projectType = projectType;
        this.priority = priority;
        this.projectStreet = projectStreet;
        this.projectTown = projectTown;
        this.projectCounty = projectCounty;
        this.projectPostcode = projectPostcode;
        this.projectTelephone = projectTelephone;
        this.contactName = contactName;
        this.contactStreet = contactStreet;
        this.contactTown = contactTown;
        this.contactCounty = contactCounty;
        this.contactPostcode = contactPostcode;
        this.contactTelephone = contactTelephone;
        this.contactMobile = contactMobile;
        this.contactEmail = contactEmail;
        this.requestDate = requestDate;
        this.initialVisitDate = initialVisitDate;
        this.designBrief = designBrief;
        this.mainHallWork = mainHallWork;
        this.schoolWork = schoolWork;
        this.toiletWork = toiletWork;
        this.foyerWork = foyerWork;
        this.exteriorWork = exteriorWork;
        this.carParkWork = carParkWork;
        this.landscapeWork = landscapeWork;
        this.otherWork = otherWork;
        this.fundsOnHand = fundsOnHand;
        this.avgMonthContribution = avgMonthContribution;
        this.currentLoans = currentLoans;
        this.projectEstimateCost = projectEstimateCost;
        this.status = status;
        this.supportingCong = supportingCong;
        this.comments = comments;
        this.updateInfo = updateInfo;
        this.coordinator = coordinator;
        this.chairman = chairman;
        this.member = member;
        this.workRbc = workRbc;
        this.workDesign = workDesign;
        this.workProj = workProj;
        this.projectInfo = projectInfo;
        this.stage = stage;
        this.completedDate = completedDate;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public KingdomHall getKingdomHall() {
        return this.kingdomHall;
    }

    public void setKingdomHall(KingdomHall kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public ProjectType getProjectType() {
        return this.projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProjectStreet() {
        return this.projectStreet;
    }

    public void setProjectStreet(String projectStreet) {
        this.projectStreet = projectStreet;
    }

    public String getProjectTown() {
        return this.projectTown;
    }

    public void setProjectTown(String projectTown) {
        this.projectTown = projectTown;
    }

    public String getProjectCounty() {
        return this.projectCounty;
    }

    public void setProjectCounty(String projectCounty) {
        this.projectCounty = projectCounty;
    }

    public String getProjectPostcode() {
        return this.projectPostcode;
    }

    public void setProjectPostcode(String projectPostcode) {
        this.projectPostcode = projectPostcode;
    }

    public String getProjectTelephone() {
        return this.projectTelephone;
    }

    public void setProjectTelephone(String projectTelephone) {
        this.projectTelephone = projectTelephone;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactStreet() {
        return this.contactStreet;
    }

    public void setContactStreet(String contactStreet) {
        this.contactStreet = contactStreet;
    }

    public String getContactTown() {
        return this.contactTown;
    }

    public void setContactTown(String contactTown) {
        this.contactTown = contactTown;
    }

    public String getContactCounty() {
        return this.contactCounty;
    }

    public void setContactCounty(String contactCounty) {
        this.contactCounty = contactCounty;
    }

    public String getContactPostcode() {
        return this.contactPostcode;
    }

    public void setContactPostcode(String contactPostcode) {
        this.contactPostcode = contactPostcode;
    }

    public String getContactTelephone() {
        return this.contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getContactMobile() {
        return this.contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Date getRequestDate() {
        return this.requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getInitialVisitDate() {
        return this.initialVisitDate;
    }

    public void setInitialVisitDate(Date initialVisitDate) {
        this.initialVisitDate = initialVisitDate;
    }

    public String getDesignBrief() {
        return this.designBrief;
    }

    public void setDesignBrief(String designBrief) {
        this.designBrief = designBrief;
    }

    public String getMainHallWork() {
        return this.mainHallWork;
    }

    public void setMainHallWork(String mainHallWork) {
        this.mainHallWork = mainHallWork;
    }

    public String getSchoolWork() {
        return this.schoolWork;
    }

    public void setSchoolWork(String schoolWork) {
        this.schoolWork = schoolWork;
    }

    public String getToiletWork() {
        return this.toiletWork;
    }

    public void setToiletWork(String toiletWork) {
        this.toiletWork = toiletWork;
    }

    public String getFoyerWork() {
        return this.foyerWork;
    }

    public void setFoyerWork(String foyerWork) {
        this.foyerWork = foyerWork;
    }

    public String getExteriorWork() {
        return this.exteriorWork;
    }

    public void setExteriorWork(String exteriorWork) {
        this.exteriorWork = exteriorWork;
    }

    public String getCarParkWork() {
        return this.carParkWork;
    }

    public void setCarParkWork(String carParkWork) {
        this.carParkWork = carParkWork;
    }

    public String getLandscapeWork() {
        return this.landscapeWork;
    }

    public void setLandscapeWork(String landscapeWork) {
        this.landscapeWork = landscapeWork;
    }

    public String getOtherWork() {
        return this.otherWork;
    }

    public void setOtherWork(String otherWork) {
        this.otherWork = otherWork;
    }

    public String getFundsOnHand() {
        return this.fundsOnHand;
    }

    public void setFundsOnHand(String fundsOnHand) {
        this.fundsOnHand = fundsOnHand;
    }

    public String getAvgMonthContribution() {
        return this.avgMonthContribution;
    }

    public void setAvgMonthContribution(String avgMonthContribution) {
        this.avgMonthContribution = avgMonthContribution;
    }

    public String getCurrentLoans() {
        return this.currentLoans;
    }

    public void setCurrentLoans(String currentLoans) {
        this.currentLoans = currentLoans;
    }

    public String getProjectEstimateCost() {
        return this.projectEstimateCost;
    }

    public void setProjectEstimateCost(String projectEstimateCost) {
        this.projectEstimateCost = projectEstimateCost;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupportingCong() {
        return this.supportingCong;
    }

    public void setSupportingCong(String supportingCong) {
        this.supportingCong = supportingCong;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUpdateInfo() {
        return this.updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public Long getCoordinator() {
        return this.coordinator;
    }

    public void setCoordinator(Long coordinator) {
        this.coordinator = coordinator;
    }

    public Long getChairman() {
        return this.chairman;
    }

    public void setChairman(Long chairman) {
        this.chairman = chairman;
    }

    public Long getMember() {
        return this.member;
    }

    public void setMember(Long member) {
        this.member = member;
    }

    public String getWorkRbc() {
        return this.workRbc;
    }

    public void setWorkRbc(String workRbc) {
        this.workRbc = workRbc;
    }

    public String getWorkDesign() {
        return this.workDesign;
    }

    public void setWorkDesign(String workDesign) {
        this.workDesign = workDesign;
    }

    public String getWorkProj() {
        return this.workProj;
    }

    public void setWorkProj(String workProj) {
        this.workProj = workProj;
    }

    public String getProjectInfo() {
        return this.projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }

    public String getStage() {
        return this.stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Date getCompletedDate() {
        return this.completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }
}