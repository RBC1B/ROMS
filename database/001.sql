DROP DATABASE IF EXISTS ROMS;

CREATE DATABASE ROMS;

GRANT ALL ON ROMS.* TO rbcadmin;

use ROMS;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

create table REVINFO (
    REV         bigint  auto_increment,
    REVTSTMP    bigint,
    primary key (REV)
)engine=InnoDB;

-- create the person. We don't create the foreign key to the congregation until the table is created
create table Person(
    PersonId        bigint(20)  not null    auto_increment,
    Forename        varchar(50) not null,
    MiddleName      varchar(50),
    Surname         varchar(50) not null,
    Street          varchar(50),
    Town            varchar(50),
    County          varchar(50),
    Postcode        varchar(10),
    Telephone       varchar(15),
    Mobile          varchar(15),
    WorkPhone       varchar(15),
    Email           varchar(50),
    Comments        varchar(250),
    CongregationId  bigint(20),
    BirthDate       date,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (PersonId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Person_AUD(
    PersonId        bigint(20)  not null,
    REV             int         not null,
    REVTYPE         tinyint,
    Forename        varchar(50) not null,
    MiddleName      varchar(50),
    Surname         varchar(50) not null,
    Street          varchar(50),
    Town            varchar(50),
    County          varchar(50),
    Postcode        varchar(10),
    Telephone       varchar(15),
    Mobile          varchar(15),
    WorkPhone       varchar(15),
    Email           varchar(50),
    Comments        varchar(250),
    CongregationId  bigint(20),
    BirthDate       date,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (PersonId, REV)
)engine=InnoDB;

-- track person details change requests
create table PersonChange(
    PersonChangeId  bigint(20) not null auto_increment,
    PersonId        bigint(20) not null,
    OldForeName     varchar(50),
    OldMiddleName   varchar(50),
    OldSurname      varchar(50),
    OldStreet       varchar(50),
    OldTown         varchar(50),
    OldCounty       varchar(50),
    OldPostcode     varchar(10),
    OldTelephone    varchar(15),
    OldMobile       varchar(15),
    OldWorkPhone    varchar(15),
    OldEmail        varchar(50),
    NewForeName     varchar(50),
    NewMiddleName   varchar(50),
    NewSurname      varchar(50),
    NewStreet       varchar(50),
    NewTown         varchar(50),
    NewCounty       varchar(50),
    NewPostcode     varchar(10),
    NewTelephone    varchar(15),
    NewMobile       varchar(15),
    NewWorkPhone    varchar(15),
    NewEmail        varchar(50),
    Comment         varchar(150),
    ChangeDate      timestamp not null,
    FormUpdated     boolean default false,
    primary key (PersonChangeId),
    foreign key (PersonId) references Person(PersonId)
)engine=InnoDB;

-- application is a sub-section of the web-application
create table Application(
    ApplicationId   bigint(20)  auto_increment,
    Name            varchar(30) not null    unique, -- display name
    Code            varchar(12) not null    unique, -- used in backend as an identifier
    Comments        varchar(250),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (ApplicationId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Application_AUD (
    ApplicationId   bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    Name            varchar(30) not null,
    Code            varchar(12) not null,
    Comments        varchar(250),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (ApplicationId, REV)    
)engine=InnoDB;

-- registered webapp user
create table User(
    PersonId    bigint(20),
    UserName    varchar(50) not null    unique,
    Password    varchar(50) not null,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (PersonId),
    foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table User_AUD(
    PersonId    bigint(20),
    REV         int         not null,
    REVTYPE     tinyint,
    UserName    varchar(50) not null,
    Password    varchar(50) not null,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (PersonId, REV)
)engine=InnoDB;

-- collection of congregations
create table Circuit(
    CircuitId           bigint(20)      auto_increment,
    Name                varchar(50)     not null    unique,
    CircuitOverseerId   bigint(20),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (CircuitId),
    foreign key (CircuitOverseerId) references Person(PersonId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Circuit_AUD(
    CircuitId           bigint(20),
    REV                 int         not null,
    REVTYPE             tinyint,
    Name                varchar(50)     not null,
    CircuitOverseerId   bigint(20),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (CircuitId, REV)
)engine=InnoDB;


create table KingdomHallOwnershipType(
    KingdomHallOwnershipTypeId  bigint(20)  not null    auto_increment,
    Name                        varchar(50) not null    unique,
    primary key (KingdomHallOwnershipTypeId)
)engine=InnoDB;

create table HallFeature(
    HallFeatureId   bigint(20)  auto_increment,
    Name            varchar(50) not null unique,
    primary key (HallFeatureId)
)engine=InnoDB;

create table KingdomHall(
    KingdomHallId               bigint(20)  auto_increment,
    Name                        varchar(50) not null unique,
    Street                      varchar(50) not null,
    Town                        varchar(50) not null,
    County                      varchar(50),
    Postcode                    varchar(10) not null,
    KingdomHallOwnershipTypeId  bigint(20),
    TitleHolderId               bigint(20),
    Drawings                    varchar(50),
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (KingdomHallId),
    foreign key (KingdomHallOwnershipTypeId) references KingdomHallOwnershipType(KingdomHallOwnershipTypeId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table KingdomHall_AUD (
    KingdomHallId               bigint(20),
    REV                         int not null,
    REVTYPE                     tinyint,
    Name                        varchar(50) not null,
    Street                      varchar(50) not null,
    Town                        varchar(50) not null,
    County                      varchar(50),
    Postcode                    varchar(10) not null,
    KingdomHallOwnershipTypeId  bigint(20),
    TitleHolderId               bigint(20),
    Drawings                    varchar(50),
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (KingdomHallId, REV)
)engine=InnoDB;

-- a part of the kingdom hall, e.g. Car Park
create table KingdomHallFeature(
    KingdomHallFeatureId    bigint(20)  auto_increment,
    KingdomHallId           bigint(20)  not null,
    HallFeatureId           bigint(20)  not null,
    Comments                varchar(250),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (KingdomHallFeatureId),
    unique(KingdomHallId, HallFeatureId),
    foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete cascade,
    foreign key (HallFeatureId) references HallFeature(HallFeatureId) on delete cascade,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table KingdomHallFeature_AUD(
    KingdomHallFeatureId    bigint(20),
    REV                     int         not null,
    REVTYPE                 tinyint,
    KingdomHallId           bigint(20)  not null,
    HallFeatureId           bigint(20)  not null,
    Comments                varchar(250),
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (KingdomHallFeatureId, REV)
)engine=InnoDB;

create table RbcRegion(
    RbcRegionId     bigint(20)  auto_increment,
    Name            varchar(50) not null unique,
    primary key (RbcRegionId)
)engine=InnoDB;

create table RbcSubRegion(
    RbcSubRegionId  bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    primary key (RbcSubRegionId)
)engine=InnoDB;

create table Congregation(
    CongregationId  bigint(20)  not null    auto_increment,
    Name            varchar(50) not null    unique,
    Number          varchar(10) not null    unique,
    KingdomHallId   bigint(20),
    CircuitId       bigint(20)  not null,
    RbcRegionId     bigint(20),
    RbcSubRegionId  bigint(20),
    Publishers      varchar(10),
    Attendance      varchar(10),
    Funds           varchar(50),
    Loans           varchar(10),
    MonthlyIncome   varchar(10),
    Strategy        varchar (1000),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (CongregationId),
    foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete set null,
    foreign key (CircuitId) references Circuit(CircuitId),
    foreign key (RbcRegionId) references RbcRegion(RbcRegionId) on delete set null,
    foreign key (RbcSubRegionId) references RbcSubRegion(RbcSubRegionId) on delete set null,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Congregation_AUD(
    CongregationId  bigint(20)  not null,
    REV             int         not null,
    REVTYPE         tinyint,
    Name            varchar(50) not null,
    Number          varchar(10) not null,
    KingdomHallId   bigint(20),
    CircuitId       bigint(20)  not null,
    RbcRegionId     bigint(20),
    RbcSubRegionId  bigint(20),
    Publishers      varchar(10),
    Attendance      varchar(10),
    Funds           varchar(50),
    Loans           varchar(10),
    MonthlyIncome   varchar(10),
    Strategy        varchar (1000),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (CongregationId, REV)
)engine=InnoDB;

-- we can now add the foeign key to the person congregation id
alter table Person add
    constraint foreign key (CongregationId) references Congregation(CongregationId) on delete set null;

-- we can now add the foeign key to the kingdom hall title holder congregation id
alter table KingdomHall add
    constraint foreign key (TitleHolderId) references Congregation(CongregationId) on delete set null;

create table CongregationRole(
    CongregationRoleId  bigint(20)  auto_increment,
    Name                varchar(50) not null    unique,
    primary key (CongregationRoleId)
)engine=InnoDB;

create table CongregationContact(
    CongregationContactId   bigint(20)  auto_increment,
    CongregationId          bigint(20)  not null,
    CongregationRoleId      bigint(20)  not null,
    PersonId                bigint(20)  not null,
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (CongregationContactId),
    unique(CongregationId, CongregationRoleId),
    foreign key (CongregationId) references Congregation(CongregationId) on delete cascade,
    foreign key (CongregationRoleId) references CongregationRole(CongregationRoleId),
    foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table CongregationContact_AUD(
    CongregationContactId   bigint(20),
    REV                     int         not null,
    REVTYPE                 tinyint,
    CongregationId          bigint(20)  not null,
    CongregationRoleId      bigint(20)  not null,
    PersonId                bigint(20)  not null,
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (CongregationContactId, REV)
)engine=InnoDB;

create table InterviewStatus(
    InterviewStatusId   bigint(20)  auto_increment,
    Name                varchar(50) not null    unique,
    primary key (InterviewStatusId)
)engine=InnoDB;

-- RBC department
create table Department(
    DepartmentId        bigint(20)  auto_increment,
    Name                varchar(50) not null    unique,
    SuperDepartmentId   bigint(20),
    Description         varchar(50),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (DepartmentId),
    foreign key (SuperDepartmentId) references Department(DepartmentId) on delete set null,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Department_AUD(
    DepartmentId        bigint(20),
    REV                 int         not null,
    REVTYPE             tinyint,
    Name                varchar(50) not null,
    SuperDepartmentId   bigint(20),
    Description         varchar(50),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (DepartmentId, REV)
)engine=InnoDB;

create table RbcStatus(
    RbcStatusId     bigint(20)      auto_increment,
    Name            varchar (15)    not null    unique,
    primary key (RbcStatusId)
)engine=InnoDB;

-- Person appointment, e.g. ministerial servant
create table Appointment(
    AppointmentId   bigint(20)  auto_increment,
    Name            varchar(25) not null    unique,
    primary key (AppointmentId)
)engine=InnoDB;

create table Fulltime(
    FulltimeId  bigint(20)  auto_increment,
    Name        varchar(25) not null    unique,
    primary key (FulltimeId)
)engine=InnoDB;

-- emergency contact relationship
create table Relationship(
    RelationshipId  bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    primary key (RelationshipId)
)engine=InnoDB;

create table MaritalStatus(
    MaritalStatusId bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    primary key (MaritalStatusId)
)engine=InnoDB;

create table Volunteer(
    PersonId            bigint(20)  not null    unique,
    RbcStatusId         bigint(20)  not null,
    AppointmentId       bigint(20),
    FulltimeId          bigint(20),
    Availability        varchar(7),
    EmergencyContactId  bigint(20),
    EmergencyContactRelationshipId bigint(20),
    Gender              varchar(1)  not null,
    MaritalStatusId     bigint(20),
    SpousePersonId      bigint(20),
    BaptismDate         date,
    InterviewDate       date,
    InterviewerA        bigint(20),
    InterviewerB        bigint(20),
    InterviewComments   varchar(150),
    JoinedDate          date,
    FormDate            date,
    InterviewStatusId   bigint(20)  not null,
    Oversight           boolean     not null,
    OversightComments   varchar(50),
    ReliefUK            boolean     not null,
    ReliefUKComments    varchar(50),
    ReliefAbroad        boolean     not null,
    ReliefAbroadComments varchar(50),
    HHCFormCode         varchar(15),
    BadgeIssueDate      date,
    primary key(PersonId),
    foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (RbcStatusId) references RbcStatus(RbcStatusId),
    foreign key (AppointmentId) references Appointment(AppointmentId),
    foreign key (FulltimeId) references Fulltime(FulltimeId),
    foreign key (EmergencyContactId) references Person(PersonId) on delete set null,
    foreign key (EmergencyContactRelationshipId) references Relationship(RelationshipId) on delete set null,
    foreign key (MaritalStatusId) references MaritalStatus(MaritalStatusId),
    foreign key (InterviewerA) references User(PersonId) on delete set null,
    foreign key (InterviewerB) references User(PersonId) on delete set null,
    foreign key (InterviewStatusId) references InterviewStatus(InterviewStatusId)
)engine=InnoDB;

create table Volunteer_AUD (
    PersonId            bigint(20)  not null,
    REV                 int         not null,
    REVTYPE             tinyint,
    RbcStatusId         bigint(20)  not null,
    AppointmentId       bigint(20),
    FulltimeId          bigint(20),
    Availability        varchar(7),
    EmergencyContactId  bigint(20),
    EmergencyContactRelationshipId bigint(20),
    Gender              varchar(1)  not null,
    MaritalStatusId     bigint(20),
    SpousePersonId      bigint(20),
    BaptismDate         date,
    InterviewDate       date,
    InterviewerA        bigint(20),
    InterviewerB        bigint(20),
    InterviewComments   varchar(150),
    JoinedDate          date,
    FormDate            date,
    InterviewStatusId   bigint(20)  not null,
    Oversight           boolean     not null,
    OversightComments   varchar(50),
    ReliefUK            boolean     not null,
    ReliefUKComments    varchar(50),
    ReliefAbroad        boolean     not null,
    ReliefAbroadComments varchar(50),
    HHCFormCode         varchar(15),
    BadgeIssueDate      date,
    primary key(PersonId, REV)
)engine=InnoDB;

-- volunteer experience, entered on application form
create table VolunteerTrade (
    VolunteerTradeId        bigint(20)      auto_increment,
    PersonId                bigint(20),
    Name                    varchar(250)    not null,
    ExperienceDescription   text,
    ExperienceYears         integer,
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (VolunteerTradeId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table VolunteerTrade_AUD (
    VolunteerTradeId        bigint(20),
    REV                     int         not null,
    REVTYPE                 tinyint,
    PersonId                bigint(20),
    Name                    varchar(250)    not null,
    ExperienceDescription   text,
    ExperienceYears         integer,
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (VolunteerTradeId, REV)
)engine=InnoDB;

-- permission levels for the application, based on their department
create table ApplicationAccess(
    ApplicationAccessId bigint(20)  auto_increment,
    PersonId            bigint(20)  not null,
    ApplicationId       bigint(20),
    DepartmentAccess    integer     not null,
    NonDepartmentAccess integer     not null,
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (ApplicationAccessId),
    unique (PersonId, ApplicationId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    constraint foreign key (ApplicationId) references Application(ApplicationId) on delete cascade,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ApplicationAccess_AUD(
    ApplicationAccessId bigint(20),
    REV                 int         not null,
    REVTYPE             tinyint,
    PersonId            bigint(20)  not null,
    ApplicationId       bigint(20),
    DepartmentAccess    integer     not null,
    NonDepartmentAccess integer     not null,
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (ApplicationAccessId, REV)
)engine=InnoDB;

create table AssignmentRole(
    AssignmentRoleId    bigint(20)  auto_increment,
    Name                varchar(50) not null    unique,
    primary key (AssignmentRoleId)
)engine=InnoDB;

-- maps assignment priority of the volunteer to the department
create table TradeNumber(
    TradeNumberId   bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    primary key (TradeNumberId)
)engine=InnoDB;

create table Team(
    TeamId      bigint(20)  auto_increment,
    Name        varchar(50) not null    unique,
    primary key (TeamId)
)engine=InnoDB;

create table Assignment(
    AssignmentId        bigint(20)  auto_increment,
    PersonId            bigint(20)  not null,
    DepartmentId        bigint(20)  not null,
    AssignmentRoleId    bigint(20)  not null,
    AssignedDate        date        not null,
    TradeNumberId       bigint(20)  not null,
    TeamId              bigint(20)  not null,
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (AssignmentId),
    foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (DepartmentId) references Department(DepartmentId) on delete cascade,
    foreign key (AssignmentRoleId) references AssignmentRole(AssignmentRoleId),
    foreign key (TradeNumberId) references TradeNumber(TradeNumberId),
    foreign key (TeamId) references Team(TeamId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Assignment_AUD(
    AssignmentId        bigint(20),
    REV                 int         not null,
    REVTYPE             tinyint,
    PersonId            bigint(20)  not null,
    DepartmentId        bigint(20)  not null,
    AssignmentRoleId    bigint(20)  not null,
    AssignedDate        date        not null,
    TradeNumberId       bigint(20)  not null,
    TeamId              bigint(20)  not null,
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (AssignmentId, REV)
)engine=InnoDB;

create table ProjectType(
    ProjectTypeId   bigint(20)  auto_increment,
    Name     varchar(25) not null    unique,
    primary key (ProjectTypeId)
)engine=InnoDB;

create table InvitationConfirmation(
    InvitationConfirmationId    bigint(20)  auto_increment,
    Description                 varchar(25) not null    unique,
    primary key (InvitationConfirmationId)
)engine=InnoDB;

create table WorkFeature(
    WorkFeatureId   bigint(20)  auto_increment,
    Name     varchar(50) not null    unique,
    primary key (WorkFeatureId)
)engine=InnoDB;

create table ProjectStatus(
    ProjectStatusId bigint(20)  auto_increment,
    Name     varchar(50) not null    unique,
    primary key (ProjectStatusId)
)engine=InnoDB;

create table Project(
    ProjectId       bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    ProjectTypeId   bigint(20)  not null,
    KingdomHallId   bigint(20),
    Priority        varchar(50),
    Street          varchar (80),
    Town            varchar(50),
    County          varchar(50),
    Postcode        varchar(10),
    Telephone       varchar(20),
    ContactPersonId bigint(20),
    RequestDate     date,
    VisitDate       date,
    EstimateCost    varchar(50),
    ProjectStatusId bigint(20)  not null,
    SupportingCongregation varchar(250),
    ProjectConstraints text,
    CoordinatorId   bigint(20),
    CompletedDate   date,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (ProjectId),
    foreign key (ProjectTypeId) references ProjectType(ProjectTypeId),
    foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete set null,
    foreign key (ContactPersonId) references Person(PersonId) on delete set null,
    foreign key (ProjectStatusId) references ProjectStatus(ProjectStatusId),
    foreign key (CoordinatorId) references Person(PersonId) on delete set null,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Project_AUD (
    ProjectId       bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    Name            varchar(50) not null,
    ProjectTypeId   bigint(20)  not null,
    KingdomHallId   bigint(20),
    Priority        varchar(50),
    Street          varchar (80),
    Town            varchar(50),
    County          varchar(50),
    Postcode        varchar(10),
    Telephone       varchar(20),
    ContactPersonId bigint(20),
    RequestDate     date,
    VisitDate       date,
    EstimateCost    varchar(50),
    ProjectStatusId bigint(20)  not null,
    SupportingCongregation varchar(250),
    ProjectConstraints text,
    CoordinatorId   bigint(20),
    CompletedDate   date,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (ProjectId, REV)
)engine=InnoDB;

create table ProjectStageType (
    ProjectStageTypeId  bigint(20)  auto_increment,
    Name                varchar(5)  not null    unique,
    Description         varchar(50),
    AssignedTo          varchar(500),
    WorkNotes           varchar(1000),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (ProjectStageTypeId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ProjectStageType_AUD (
    ProjectStageTypeId  bigint(20),
    REV                 int         not null,
    REVTYPE             tinyint,
    Name                varchar(5)  not null,
    Description         varchar(50),
    AssignedTo          varchar(500),
    WorkNotes           varchar(1000),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (ProjectStageTypeId, REV)
)engine=InnoDB;

-- mapping of stage types linked to a project type
-- when a new project is created, stages and stage activities are created
create table ProjectTypeStageType (
    ProjectTypeStageTypeId      bigint(20)  auto_increment,
    ProjectTypeId               bigint(20) not null,
    ProjectStageTypeId          bigint(20) not null,
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectTypeStageTypeId),
    unique (ProjectTypeId, ProjectStageTypeId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ProjectTypeStageType_AUD (
    ProjectTypeStageTypeId      bigint(20),
    REV                         int         not null,
    REVTYPE                     tinyint,
    ProjectTypeId               bigint(20) not null,
    ProjectStageTypeId          bigint(20) not null,
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectTypeStageTypeId, REV)
)engine=InnoDB;

create table ProjectStageActivityType (
    ProjectStageActivityTypeId   bigint(20)  auto_increment,
    Name                        varchar(5)  not null    unique,
    Description                 varchar(50),
    AssignedTo                  varchar(500),
    WorkNotes                   varchar(1000),
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectStageActivityTypeId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ProjectStageActivityType_AUD (
    ProjectStageActivityTypeId  bigint(20),
    REV                         int         not null,
    REVTYPE                     tinyint,
    Name                        varchar(5)  not null,
    Description                 varchar(50),
    AssignedTo                  varchar(500),
    WorkNotes                   varchar(1000),
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectStageActivityTypeId, REV)
)engine=InnoDB;

-- mapping of activity types linked to a stage type
-- when a new project is created, stages and stage activities are created
create table ProjectStageTypeActivityType (
    ProjectStageTypeActivityTypeId  bigint(20)  auto_increment,
    ProjectStageTypeId              bigint(20)  not null,
    ProjectStageActivityTypeId      bigint(20)  not null,
    UpdateTime                      timestamp   not null,
    UpdatedBy                       bigint(20)  not null,
    primary key (ProjectStageTypeActivityTypeId),
    foreign key (ProjectStageTypeId) references ProjectStageType(ProjectStageTypeId),
    foreign key (ProjectStageActivityTypeId) references ProjectStageActivityType(ProjectStageActivityTypeId),
    unique (ProjectStageTypeId, ProjectStageActivityTypeId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ProjectStageTypeActivityType_AUD (
    ProjectStageTypeActivityTypeId  bigint(20),
    REV                             int         not null,
    REVTYPE                         tinyint,
    ProjectStageTypeId              bigint(20),
    ProjectStageActivityTypeId      bigint(20),
    UpdateTime                      timestamp   not null,
    UpdatedBy                       bigint(20)  not null,
    primary key (ProjectStageTypeActivityTypeId, REV)
)engine=InnoDB;

create table ProjectStageEventType (
    ProjectStageEventTypeId bigint(20)  auto_increment,
    Name                    varchar(50),
    primary key (ProjectStageEventTypeId)
)engine=InnoDB;

create table ProjectStageActivityEventType (
    ProjectStageActivityEventTypeId bigint(20)  auto_increment,
    Name                            varchar(50),
    primary key (ProjectStageActivityEventTypeId)
)engine=InnoDB;

create table ProjectStageActivityTaskEventType (
    ProjectStageActivityTaskEventTypeId  bigint(20)  auto_increment,
    Name                            varchar(50),
    primary key (ProjectStageActivityTaskEventTypeId)
)engine=InnoDB;

create table ProjectStage (
    ProjectStageId          bigint(20)  auto_increment,
    ProjectId               bigint(20)  not null,
    ProjectStageTypeId      bigint(20)  not null,
    ProjectStageStatusId    bigint(20)  not null,
    CreatedTime             timestamp   not null,
    StartedTime             timestamp   null,
    CompletedTime           timestamp   null,
    ProjectedStart          date        null,
    ProjectedCompletion     date        null,
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (ProjectStageId),
    foreign key (ProjectId) references Project(ProjectId),
    foreign key (ProjectStageTypeId) references ProjectStageType(ProjectStageTypeId),
    foreign key (ProjectStageStatusId) references ProjectStatus(ProjectStatusId),
    foreign key (UpdatedBy) references User(PersonId)
)engine=InnoDB;

create table ProjectStage_AUD (
    ProjectStageId          bigint(20),
    REV                     int         not null,
    REVTYPE                 tinyint,
    ProjectId               bigint(20)  not null,
    ProjectStageTypeId      bigint(20)  not null,
    ProjectStageStatusId    bigint(20)  not null,
    CreatedTime             timestamp   not null,
    StartedTime             timestamp   null,
    CompletedTime           timestamp   null,
    ProjectedStart          date        null,
    ProjectedCompletion     date        null,
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (ProjectStageId, REV)
)engine=InnoDB;

create table ProjectStageOrder (
    ProjectStageOrderId     bigint(20)  auto_increment,
    ProjectId               bigint(20)  not null,
    ProjectStageId          bigint(20)  not null,
    PreviousProjectStageId  bigint(20),
    NextProjectStageId      bigint(20),
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (ProjectStageOrderId),
    foreign key (ProjectId) references Project(ProjectId),
    foreign key (ProjectStageId) references ProjectStage(ProjectStageId),
    foreign key (PreviousProjectStageId) references ProjectStage(ProjectStageId),
    foreign key (NextProjectStageId) references ProjectStage(ProjectStageId),
    foreign key (UpdatedBy) references User(PersonId)
)engine=InnoDB;

create table ProjectStageOrder_AUD (
    ProjectStageOrderId     bigint(20),
    REV                     int         not null,
    REVTYPE                 tinyint,
    ProjectId               bigint(20)  not null,
    ProjectStageId          bigint(20)  not null,
    PreviousProjectStageId  bigint(20),
    NextProjectStageId      bigint(20),
    UpdateTime              timestamp   not null,
    UpdatedBy               bigint(20)  not null,
    primary key (ProjectStageOrderId, REV)
)engine=InnoDB;

create table ProjectStageActivity (
    ProjectStageActivityId      bigint(20)  auto_increment,
    ProjectStageActivityTypeId  bigint(20)  not null,
    ProjectStageId              bigint(20)  not null,
    AssignedVolunteerId         bigint(20)  not null,
    Comments                    varchar(1000),
    CreatedTime                 timestamp   not null,
    StartedTime                 timestamp   null,
    CompletedTime               timestamp   null,
    ProjectedStart              date        null,
    ProjectedCompletion         date        null,
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectStageActivityId),
    foreign key (ProjectStageActivityTypeId) references ProjectStageActivityType(ProjectStageActivityTypeId),
    foreign key (ProjectStageId) references ProjectStage(ProjectStageId),
    foreign key (AssignedVolunteerId) references Volunteer(PersonId),
    foreign key (UpdatedBy) references User(PersonId)
)engine=InnoDB;

create table ProjectStageActivity_AUD (
    ProjectStageActivityId      bigint(20)  auto_increment,
    REV                         int         not null,
    REVTYPE                     tinyint,
    ProjectStageActivityTypeId  bigint(20)  not null,
    ProjectStageId              bigint(20)  not null,
    AssignedVolunteerId         bigint(20)  not null,
    Comments                    varchar(1000),
    CreatedTime                 timestamp   not null,
    StartedTime                 timestamp   null,
    CompletedTime               timestamp   null,
    ProjectedStart              date        null,
    ProjectedCompletion         date        null,
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectStageActivityId, REV)
)engine=InnoDB;

create table ProjectStageActivityTask (
    ProjectStageActivityTaskId  bigint(20)  auto_increment,
    ProjectStageActivityId      bigint(20)  not null,
    Name                        varchar(250),
    AssignedVolunteerId         bigint(20)  not null,
    Comments                    varchar(1000),
    CreatedTime                 timestamp   not null,
    StartedTime                 timestamp   null,
    CompletedTime               timestamp   null,
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectStageActivityTaskId),
    foreign key (ProjectStageActivityId) references ProjectStageActivity(ProjectStageActivityId),
    foreign key (AssignedVolunteerId) references Volunteer(PersonId),
    foreign key (UpdatedBy) references User(PersonId)
)engine=InnoDB;

create table ProjectStageActivityTask_AUD (
    ProjectStageActivityTaskId  bigint(20),
    REV                         int         not null,
    REVTYPE                     tinyint,
    ProjectStageId              bigint(20)  not null,
    Name                        varchar(250),
    AssignedVolunteerId         bigint(20)  not null,
    Comments                    varchar(1000),
    CreatedTime                 timestamp   not null,
    StartedTime                 timestamp   null,
    CompletedTime               timestamp   null,
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectStageActivityTaskId, REV)
)engine=InnoDB;

create table ProjectStageEvent (
    ProjectStageEventId     bigint(20)  auto_increment,
    ProjectStageId          bigint(20)  not null,
    ProjectStageEventTypeId bigint(20)  not null,
    Comments                text,
    CreateTime              timestamp   not null,
    CreatedBy               bigint(20)  not null,
    primary key (ProjectStageEventId),
    foreign key (ProjectStageId) references ProjectStage(ProjectStageId) on delete cascade,
    foreign key (ProjectStageEventTypeId) references ProjectStageEventType(ProjectStageEventTypeId),
    foreign key (CreatedBy) references User(PersonId)
)engine=InnoDB;

create table ProjectStageActivityEvent (
    ProjectStageActivityEventId         bigint(20)  auto_increment,
    ProjectStageActivityId              bigint(20)  not null,
    ProjectStageActivityEventTypeId     bigint(20)  not null,
    Comments                            text,
    CreateTime                          timestamp   not null,
    CreatedBy                           bigint(20)  not null,
    primary key (ProjectStageActivityEventId),
    foreign key (ProjectStageActivityId) references ProjectStageActivity(ProjectStageActivityId) on delete cascade,
    foreign key (ProjectStageActivityEventTypeId) references ProjectStageActivityEventType(ProjectStageActivityEventTypeId),
    foreign key (CreatedBy) references User(PersonId)
)engine=InnoDB;

create table ProjectStageActivityTaskEvent (
    ProjectStageActivityTaskEventId     bigint(20)  auto_increment,
    ProjectStageActivityTaskId          bigint(20)  not null,
    ProjectStageActivityTaskEventTypeId bigint(20)  not null,
    Comments                            text,
    CreateTime                          timestamp   not null,
    CreatedBy                           bigint(20)  not null,
    primary key (ProjectStageActivityTaskEventId),
    foreign key (ProjectStageActivityTaskId) references ProjectStageActivityTask(ProjectStageActivityTaskId) on delete cascade,
    foreign key (ProjectStageActivityTaskEventTypeId) references ProjectStageActivityTaskEventType(ProjectStageActivityTaskEventTypeId),
    foreign key (CreatedBy) references User(PersonId)
)engine=InnoDB;


create table ProjectWorkBrief(
    ProjectWorkBriefId  bigint(20)  auto_increment,
    ProjectId           bigint(20)  not null,
    WorkFeatureId       bigint(20)  not null,
    Brief               text,
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (ProjectWorkBriefId),
    unique (ProjectId, WorkFeatureId),
    foreign key (ProjectId) references Project(ProjectId) on delete cascade,
    foreign key (WorkFeatureId) references WorkFeature(WorkFeatureId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ProjectWorkBrief_AUD(
    ProjectWorkBriefId  bigint(20),
    REV                 int         not null,
    REVTYPE             tinyint,
    ProjectId           bigint(20)  not null,
    WorkFeatureId       bigint(20)  not null,
    Brief               text,
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (ProjectWorkBriefId, REV)
)engine=InnoDB;

create table Attendance(
    AttendanceId    bigint(20)  auto_increment,
    ProjectId       bigint(20)  not null,
    PersonId        bigint(20)  not null,
    InviteDate      date        not null,
    AbleToCome      boolean,
    InvitationConfirmationId    bigint(20),
    DepartmentId    bigint(20),
    Attended        boolean,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (AttendanceId),
    unique (ProjectId, PersonId, InviteDate),
    foreign key (ProjectId) references Project(ProjectId) on delete cascade,
    foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (InvitationConfirmationId) references InvitationConfirmation(InvitationConfirmationId) on delete set null,
    foreign key (DepartmentId) references Department(DepartmentId) on delete set null,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Attendance_AUD (
    AttendanceId    bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    ProjectId       bigint(20)  not null,
    PersonId        bigint(20)  not null,
    InviteDate      date        not null,
    AbleToCome      boolean,
    InvitationConfirmationId    bigint(20),
    DepartmentId    bigint(20),
    Attended        boolean,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (AttendanceId, REV)
)engine=InnoDB;

create table Qualification(
    QualificationId bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    Description     varchar(150),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (QualificationId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Qualification_AUD (
    QualificationId bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    Name            varchar(50) not null,
    Description     varchar(150),
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (QualificationId, REV)
)engine=InnoDB;

create table VolunteerQualification(
    VolunteerQualificationId    bigint(20)  auto_increment,
    PersonId                    bigint(20)  not null,
    QualificationId             bigint(20)  not null,
    Comments                    varchar(100),
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (VolunteerQualificationId),
    unique (PersonId, QualificationId),
    foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (QualificationId) references Qualification(QualificationId) on delete cascade,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table VolunteerQualification_AUD(
    VolunteerQualificationId    bigint(20),
    REV                         int         not null,
    REVTYPE                     tinyint,
    PersonId                    bigint(20)  not null,
    QualificationId             bigint(20)  not null,
    Comments                    varchar(100),
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (VolunteerQualificationId, REV)
)engine=InnoDB;

create table SkillCategory(
    SkillCategoryId bigint(20) auto_increment,
    Name            varchar(50) not null unique,
    Colour          varchar(20),
    AppearOnBadge   boolean default false,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (SkillCategoryId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table SkillCategory_AUD(
    SkillCategoryId bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    Name            varchar(50) not null,
    Colour          varchar(20),
    AppearOnBadge   boolean,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (SkillCategoryId, REV)
)engine=InnoDB;

create table Skill(
    SkillId         bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    DepartmentId    bigint(20),
    Description     varchar(250),
    SkillCategoryId bigint(20) default null,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (SkillId),
    foreign key (DepartmentId) references Department(DepartmentId) on delete set null,
    foreign key (SkillCategoryId) references SkillCategory(SkillCategoryId) on delete set null,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Skill_AUD(
    SkillId         bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    Name            varchar(50) not null    unique,
    DepartmentId    bigint(20),
    Description     varchar(250),
    SkillCategoryId bigint(20) default null,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (SkillId, REV)
)engine=InnoDB;

create table VolunteerSkill(
    VolunteerSkillId    bigint(20)  auto_increment,
    PersonId            bigint(20)  not null,
    SkillId             bigint(20)  not null,
    Level               integer     not null,
    Comments            varchar(250),
    TrainingDate        date,
    TrainingResults     varchar(15),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (VolunteerSkillId),
    unique (PersonId, SkillId),
    foreign key (PersonId) references Person(PersonId) on delete cascade,
    foreign key (SkillId) references Skill(SkillId) on delete cascade,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table VolunteerSkill_AUD (
    VolunteerSkillId    bigint(20),
    REV                 int         not null,
    REVTYPE             tinyint,
    PersonId            bigint(20)  not null,
    SkillId             bigint(20)  not null,
    Level               integer     not null,
    Comments            varchar(250),
    TrainingDate        date,
    TrainingResults     varchar(15),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (VolunteerSkillId, REV)
)engine=InnoDB;

-- create the base system user. The password is unhashed, so they can't log in
-- this is only used to set up the initial data
insert into Person(Forename, Surname, UpdateTime, UpdatedBy)
values ('System', '-', NOW(), 1);

insert into User(PersonId, UserName, Password, UpdateTime, UpdatedBy)
values (1, 'System', 'INVALID', NOW(), 1);

insert into Application (Name, Code, Comments, UpdateTime, UpdatedBy) values
    ('Attendance & Invitations', 'ATTENDANCE', 'Mangaging project invites and gates list', NOW(), 1),
    ('Circuit', 'CIRCUIT', 'Managing Circuits in RBC region', NOW(), 1),
    ('Congregation', 'CONG', 'Managing Congregations in RBC region', NOW(), 1),
    ('Database', 'DATABASE', 'Managing database and definitions', NOW(), 1),
    ('Kingdom Halls', 'KINGDOMHALL', 'Managing Kingdom Halls in RBC region', NOW(), 1),
    ('Projects', 'PROJECT', 'Managing Projects', NOW(), 1),
    ('Skills', 'SKILL', 'Managing volunteer\'s skills and qualifications', NOW(), 1),
    ('User', 'USER', 'Managing User access', NOW(), 1),
    ('Volunteers', 'VOLUNTEER', 'Managing Volunteers', NOW(), 1);

insert into KingdomHallOwnershipType (Name) values
    ('Freehold'),
    ('Leasehold'),
    ('Rented');

insert into HallFeature(Name) values
    ('Car Park'),
    ('Main Hall'),
    ('Library'),
    ('Auxilliary Room'),
    ('Facilities'),
    ('Building'),
    ('Roof'),
    ('Seating');

insert into RbcRegion (Name) values
    ('London & Home Counties'),
    ('Outside LHC');

insert into RbcSubRegion(Name) values
    ('North'),
    ('South');

insert into CongregationRole(Name) values
    ('CoBE'),
    ('Secretary');

insert into InterviewStatus (Name) values
    ('Invite Due'),
    ('Invited'),
    ('No-Show'),
    ('Re-Invite'),
    ('Completed');

insert into Department (DepartmentId, Name, Description, UpdateTime, UpdatedBy) values
    ('1','RBC','RBC Committee and assistants', NOW(), 1),
    ('2','Chairman','RBC Chairman\'s office', NOW(), 1),
    ('3','Construction','RBC Construction', NOW(), 1),
    ('4','Construction Support','RBC Construction Support', NOW(), 1),
    ('5','Personal','RBC Personal', NOW(), 1),
    ('6','Project Development','RBC Project Development', NOW(), 1);
insert into Department (Name, SuperDepartmentId, Description, UpdateTime, UpdatedBy) values
    ('Project Coordination','2','Project Coordinators', NOW(), 1),
    ('Alarms','3','', NOW(), 1),
    ('Block Paving','3','', NOW(), 1),
    ('Bricklaying','3','', NOW(), 1),
    ('Carpentry','3','', NOW(), 1),
    ('Carpeting','3','', NOW(), 1),
    ('Ceiling','3','', NOW(), 1),
    ('Decorating','3','', NOW(), 1),
    ('Electrical','3','', NOW(), 1),
    ('Electrical Testing','3','', NOW(), 1),
    ('Ground Works','3','', NOW(), 1),
    ('Insulation','3','', NOW(), 1),
    ('Internal Finishes','3','', NOW(), 1),
    ('Landscaping','3','', NOW(), 1),
    ('Maintenance Inspection','3','', NOW(), 1),
    ('Plasterboard','3','', NOW(), 1),
    ('Plastering','3','', NOW(), 1),
    ('Plumbing','3','', NOW(), 1),
    ('Roof Trusses','3','', NOW(), 1),
    ('Roofing','3','', NOW(), 1),
    ('Sound','3','', NOW(), 1),
    ('Tiling','3','', NOW(), 1),
    ('Ventilation','3','', NOW(), 1),
    ('Wall Panelling','3','', NOW(), 1),
    ('Accounts','4','', NOW(), 1),
    ('Audit','4','', NOW(), 1),
    ('Equipment','4','', NOW(), 1),
    ('Food Service','4','', NOW(), 1),
    ('IT Support','4','', NOW(), 1),
    ('Installation','4','', NOW(), 1),
    ('Materials','4','', NOW(), 1),
    ('On Site Services','4','', NOW(), 1),
    ('Purchasing','4','', NOW(), 1),
    ('Quality Control','4','', NOW(), 1),
    ('Scaffolding','4','', NOW(), 1),
    ('Admin','5','', NOW(), 1),
    ('First Aid','5','', NOW(), 1),
    ('Safety','5','', NOW(), 1),
    ('Training','5','', NOW(), 1),
    ('Volunteer Service','5','', NOW(), 1),
    ('Design','6','', NOW(), 1),
    ('Legel','6','', NOW(), 1),
    ('Real Estate','6','', NOW(), 1);
insert into Department (Name, SuperDepartmentId, Description, UpdateTime, UpdatedBy) values
    ('Land Acquisition and Sale','49','', NOW(), 1),
    ('Land Search','49','', NOW(), 1),
    ('Planning Policy','49','', NOW(), 1);

insert into RbcStatus (Name) values
    ('Active'),
    ('Do Not Use'),
    ('Inactive'),
    ('Pending'),
    ('Reassign');

insert into Appointment (Name) values
    ('Elder'),
    ('Ministerial Servant');

insert into Fulltime (Name) values
    ('Bethel'),
    ('Regular Pioneer');

insert into Relationship(Name) values
    ('Elder'),
    ('Family'),
    ('Father'),
    ('Husband'),
    ('Ministerial Servant/Congregation Member'),
    ('Mother'),
    ('Wife'),
    ('Other');

insert into MaritalStatus(Name) values
    ('Divorced'),
    ('Married'),
    ('Other'),
    ('Separated'),
    ('Single'),
    ('Widowed');

insert into AssignmentRole(Name) values
    ('Assistant'),
    ('Administration'),
    ('Department Safety Advisor'),
    ('Inspector'),
    ('Overseer'),
    ('Trainee'),
    ('Trainer'),
    ('Volunteer');

insert into TradeNumber(Name) values
    ('1st Trade'),
    ('2nd Trade'),
    ('3rd Trade');

insert into Team(Name) values
    ('A'),
    ('B');

insert into ProjectType (Name) values
    ('Minor Work'),
    ('New Build'),
    ('Rebuild'),
    ('Refurbishment');

insert into ProjectStageType(Name, Description, AssignedTo, WorkNotes, UpdateTime, UpdatedBy) values
    ('0','Land Search','Project Development','Land Search.', NOW(), 1),
    ('1','Assess Project Viability','Chairman','Assess project viability.', NOW(), 1),
    ('002a','Viability Design','Project Development','Following necessary site visits provide initial concept designs, site layouts. More than one design option may be required.', NOW(), 1),
    ('002b','Viability Costings','Construction Support','Provide estimated costs for project including all options to be presented to congregations.', NOW(), 1),
    ('3','Visit Congregation','Chairman','Meet with all congregation elders, present design options & costs. Agree scope of works (SOW).', NOW(), 1),
    ('4','Signed Scope Of Works (SOW)','Chairman','Ensure a scope of works is signed by all congregation elders.', NOW(), 1),
    ('5','S-83','Construction Support','S83 to be signed by congregations and retained in RBC files. Only needed if a loan required.', NOW(), 1),
    ('6','Detailed Design Work','Project Development','Prepare detailed drawings based upon agreed SOW. Prepare planning application drawings if required.', NOW(), 1),
    ('7','Design And Access Statement','Project Development - Design','Prepare design & Access statement.', NOW(), 1),
    ('8','RBC Review of Draft Plan','Chairman','Review drawings and plans with RBC and approve or request alterations.', NOW(), 1),
    ('9','Congregation Approval of Drawings','Chairman','Project Co-Ordinator to contact Congregation and inform Chairman/PD of decision.', NOW(), 1),
    ('10','Planning Application and Approval','Project Development - Design','Submit plans to local authority for approval. Fees to be paid by congregation direct.', NOW(), 1),
    ('11','S84 - Loan Application and Resolution','Construction Support','Congregation need to complete and sign S84 together with congregation resolutions. S84 then to be forwarded to branch for approval.', NOW(), 1),
    ('12','Bank Account and VAT Application','Construction Support','Open a bank account for projects over £10,000. VAT registration required for all new or rebuilds.', NOW(), 1),
    ('13','Foundation Design and Selection','Project Development - Design','Complete foundation design and specification.', NOW(), 1),
    ('14','Building Regulation Approval','Project Development','Prepare and submit drawings for building regulation approval.', NOW(), 1),
    ('15','Material List','Construction Support','Prepare detailed materials list.', NOW(), 1),
    ('16','S-124 - List of Major Cost Elements','Construction Support','Complete S-124.', NOW(), 1),
    ('17','Construction Planning','Chairman','Agree construction date and inform congregation.', NOW(), 1),
    ('18','Construction Kick-Off Meeting','Chairman','Project Coordinator to organise kick off meeting 4-6 weeks prior to build. Meetings for construction support, trade overseers, basic site safety meetings and food safety training.', NOW(), 1),
    ('19','Construction','Construction','Carry out work in line with SOW.', NOW(), 1),
    ('20','Project Documentation','Construction Support','Ensure all relevant documentation retained and help in congregation H&S file. All local authority approvals, instruction manuals.', NOW(), 1),
    ('21','Complete S-85','Chairman','Complete S-85 and send to Branch.', NOW(), 1),
    ('999','Close Project','Chairman','Close project after all project work has been completed.', NOW(), 1);

insert into ProjectTypeStageType (ProjectTypeId, ProjectStageTypeId, UpdateTime, UpdatedBy) values
    (1, 1, NOW(), 1);
    
insert into ProjectStageActivityType (Name, Description, AssignedTo, WorkNotes, UpdateTime, UpdatedBy) values
    ('0-a', 'Council website search', 'Project Development', 'yadda yadda', NOW(), 1);
insert into ProjectStageActivityType (Name, Description, AssignedTo, WorkNotes, UpdateTime, UpdatedBy) values
    ('0-b', 'Speak to some farmers', 'Project Development', 'Get aff my land!', NOW(), 1);    

insert into ProjectStageTypeActivityType (ProjectStageTypeId, ProjectStageActivityTypeId, UpdateTime, UpdatedBy) values
    (1, 1, NOW(), 1);    
    
insert into InvitationConfirmation (Description) values
    ('Assembly/Convention'),
    ('Circuit Visit'),
    ('Holiday'),
    ('Other'),
    ('Uncontactable'),
    ('Work');

insert into WorkFeature (Name) values
    ('Design'),
    ('Main Hall'),
    ('Library'),
    ('Auxilliary Room'),
    ('Facilities'),
    ('Foyer'),
    ('Hall Exterior'),
    ('Car Park'),
    ('Landscape');

insert into ProjectStatus (Name) values
    ('On Hold'),
    ('Work In Progress'),
    ('Cancelled'),
    ('Completed');

insert into ProjectStageEventType (Name) values
    ('Started'),
    ('Completed'),
    ('Reopened'),
    ('Cancelled'),
    ('Notes');

insert into ProjectStageActivityEventType (Name) values
    ('Started'),
    ('Completed'),
    ('Reopened'),
    ('Cancelled'),
    ('Notes');
    
insert into ProjectStageActivityTaskEventType (Name) values
    ('Started'),
    ('Completed'),
    ('Reopened'),
    ('Cancelled'),
    ('Notes');

insert into SkillCategory (Name, Colour, AppearOnBadge, UpdateTime, UpdatedBy) values
    ('General', 'RED', true, NOW(), 1);

insert into SkillCategory (Name, Colour, AppearOnBadge, UpdateTime, UpdatedBy) values
    ('Other', 'GREEN', false, NOW(), 1);