DROP DATABASE IF EXISTS ROMS;

CREATE DATABASE ROMS;

GRANT ALL ON ROMS.* TO rbcadmin;

use ROMS;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

create table Application(
    ApplicationId   bigint(20)  auto_increment,
    Name            varchar(30) not null    unique, -- display name
    Code            varchar(12) not null    unique, -- used in backend as an identifier
    Comments        varchar(250),
    primary key (ApplicationId)
)engine=InnoDB;

create table Person(
    PersonId    bigint(20)  not null    auto_increment,
    Forename    varchar(50) not null,
    MiddleName  varchar(50),
    Surname     varchar(50) not null,
    Street      varchar(50),
    Town        varchar(50),
    County      varchar(50),
    Postcode    varchar(10),
    Telephone   varchar(15),
    Mobile      varchar(15),
    WorkPhone   varchar(50),
    Email       varchar(50),
    Comments    varchar(250),
    primary key (PersonId)
)engine=InnoDB;

create table Circuit(
    CircuitId   bigint(20)      auto_increment,
    Name        varchar(50)     not null    unique,
    primary key (CircuitId)
)engine=InnoDB;

create table OwnershipType(
    OwnershipTypeId     bigint(20)  not null    auto_increment,
    Name                varchar(50) not null    unique,
    primary key (OwnershipTypeId)
)engine=InnoDB;

create table HallFeature(
    HallFeatureId   bigint(20)  auto_increment,
    Name            varchar(50) not null unique,
    primary key (HallFeatureId)
)engine=InnoDB;

create table KingdomHall(
    KingdomHallId   bigint(20)  auto_increment,
    Name            varchar(50) not null unique,
    Street          varchar(50) not null,
    Town            varchar(50) not null,
    County          varchar(50),
    Postcode        varchar(10) not null,
    OwnershipTypeId bigint(20),
    Drawings        varchar(50),
    primary key (KingdomHallId),
    constraint foreign key (OwnershipTypeId) references OwnershipType(OwnershipTypeId)
)engine=InnoDB;

create table KingdomHallFeature(
    KingdomHallFeatureId    bigint(20)  auto_increment,
    KingdomHallId           bigint(20)  not null,
    HallFeatureId           bigint(20)  not null,
    Comments                varchar(250),
    primary key (KingdomHallFeatureId),
    unique(KingdomHallId, HallFeatureId),
    constraint foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete cascade,
    constraint foreign key (HallFeatureId) references HallFeature(HallFeatureId) on delete cascade
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
    primary key (CongregationId),
    constraint foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete set null,
    constraint foreign key (CircuitId) references Circuit(CircuitId),
    constraint foreign key (RbcRegionId) references RbcRegion(RbcRegionId) on delete set null,
    constraint foreign key (RbcSubRegionId) references RbcSubRegion(RbcSubRegionId) on delete set null
)engine=InnoDB;

create table CongregationRole(
    CongregationRoleId  bigint(20)  auto_increment,
    Description         varchar(50) not null    unique,
    primary key (CongregationRoleId)
)engine=InnoDB;

create table CongregationContact(
    CongregationContactId   bigint(20)  auto_increment,
    CongregationId          bigint(20)  not null,
    CongregationRoleId      bigint(20)  not null,
    PersonId                bigint(20)  not null,
    primary key (CongregationContactId),
    unique(CongregationId, CongregationRoleId),
    constraint foreign key (CongregationId) references Congregation(CongregationId) on delete cascade,
    constraint foreign key (CongregationRoleId) references CongregationRole(CongregationRoleId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade
)engine=InnoDB;

create table TitleHolder(
    TitleHolderId   bigint(20)  auto_increment,
    KingdomHallId   bigint(20)  not null    unique,
    CongregationId  bigint(20)  not null    unique,
    primary key (TitleHolderId),
    constraint foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete cascade,
    constraint foreign key (CongregationId) references Congregation(CongregationId) on delete cascade
)engine=InnoDB;

create table InterviewStatus(
    InterviewStatusId   bigint(20)  auto_increment,
    Description         varchar(50) not null    unique,
    primary key (InterviewStatusId)
)engine=InnoDB;

create table Department(
    DepartmentId        bigint(20)  auto_increment,
    Name                varchar(50) not null    unique,
    SuperDepartmentId   bigint(20),
    Description         varchar(50),
    primary key (DepartmentId),
    constraint foreign key (SuperDepartmentId) references Department(DepartmentId) on delete set null
)engine=InnoDB;

create table RbcStatus(
    RbcStatusId     bigint(20)      auto_increment,
    Description     varchar (15)    not null    unique,
    primary key (RbcStatusId)
)engine=InnoDB;

create table Appointment(
    AppointmentId   bigint(20)  auto_increment,
    Description     varchar(25) not null    unique,
    primary key (AppointmentId)
)engine=InnoDB;

create table Fulltime(
    FulltimeId  bigint(20)  auto_increment,
    Description varchar(25) not null    unique,
    primary key (FulltimeId)
)engine=InnoDB;

create table Relationship(
    RelationshipId  bigint(20)  auto_increment,
    Description     varchar(50) not null    unique,
    primary key (RelationshipId)
)engine=InnoDB;

create table MaritalStatus(
    MaritalStatusId bigint(20)  auto_increment,
    Description     varchar(50) not null    unique,
    primary key (MaritalStatusId)
)engine=InnoDB;

create table Volunteer(
    PersonId            bigint(20)  not null    unique,
    RbcStatusId         bigint(20)  not null,
    CongregationId      bigint(20),
    AppointmentId       bigint(20),
    FulltimeId          bigint(20),
    Availability        varchar(7),
    EmergencyContactId  bigint(20),
    EmergencyContactRelationshipId bigint(20),
    DOB                 date,
    Gender              varchar(1)  not null,
    MaritalStatusId     bigint(20)  not null,
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
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    constraint foreign key (RbcStatusId) references RbcStatus(RbcStatusId),
    constraint foreign key (CongregationId) references Congregation(CongregationId) on delete set null,
    constraint foreign key (AppointmentId) references Appointment(AppointmentId),
    constraint foreign key (FulltimeId) references Fulltime(FulltimeId),
    constraint foreign key (EmergencyContactId) references Person(PersonId) on delete set null,
    constraint foreign key (EmergencyContactRelationshipId) references Relationship(RelationshipId) on delete set null,
    constraint foreign key (MaritalStatusId) references MaritalStatus(MaritalStatusId),
    constraint foreign key (InterviewerA) references Person(PersonId) on delete set null,
    constraint foreign key (InterviewerB) references Person(PersonId) on delete set null,
    constraint foreign key (InterviewStatusId) references InterviewStatus(InterviewStatusId)
)engine=InnoDB;

create table User(
    PersonId    bigint(20),
    UserName    varchar(50) not null    unique,
    Password    varchar(50) not null,
    primary key (PersonId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade
)engine=InnoDB;

create table ApplicationAccess(
    ApplicationAccessId bigint(20)  auto_increment,
    PersonId            bigint(20)  not null,
    ApplicationId       bigint(20),
    DepartmentAccess    integer     not null,
    NonDepartmentAccess integer     not null,
    primary key (ApplicationAccessId),
    unique (PersonId, ApplicationId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    constraint foreign key (ApplicationId) references Application(ApplicationId) on delete cascade
)engine=InnoDB;

create table Role(
    RoleId      bigint(20)  auto_increment,
    Description varchar(50) not null    unique,
    primary key (RoleId)
)engine=InnoDB;

create table TradeNumber(
    TradeNumberId   bigint(20)  auto_increment,
    Description     varchar(50) not null    unique,
    primary key (TradeNumberId)
)engine=InnoDB;

create table Team(
    TeamId      bigint(20)  auto_increment,
    Description varchar(50) not null    unique,
    primary key (TeamId)
)engine=InnoDB;

create table Assignment(
    AssignmentId    bigint(20)  auto_increment,
    PersonId        bigint(20)  not null,
    DepartmentId    bigint(20)  not null,
    RoleId          bigint(20)  not null,
    AssignedDate    date        not null,
    TradeNumberId   bigint(20)  not null,
    TeamId          bigint(20)  not null,
    primary key (AssignmentId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    constraint foreign key (DepartmentId) references Department(DepartmentId) on delete cascade,
    constraint foreign key (RoleId) references Role(RoleId),
    constraint foreign key (TradeNumberId) references TradeNumber(TradeNumberId),
    constraint foreign key (TeamId) references Team(TeamId)
)engine=InnoDB;

create table ProjectStage(
    ProjectStageId  bigint(20)  auto_increment,
    Name            varchar(5)  not null    unique,
    Description     varchar(50),
    AssignedTo      varchar(500),
    WorkNotes       varchar(1000),
    primary key (ProjectStageId)
)engine=InnoDB;

create table ProjectType(
    ProjectTypeId   bigint(20)  auto_increment,
    Description     varchar(25) not null    unique,
    primary key (ProjectTypeId)
)engine=InnoDB;

create table InvitationConfirmation(
    InvitationConfirmationId    bigint(20)  auto_increment,
    Description                 varchar(25) not null    unique,
    primary key (InvitationConfirmationId)
)engine=InnoDB;

create table Commentator(
    CommentatorId   bigint(20)  auto_increment,
    DepartmentId    bigint(20)  not null    unique,
    primary key (CommentatorId),
    constraint foreign key (DepartmentId) references Department(DepartmentId)
)engine=InnoDB;

create table WorkFeature(
    WorkFeatureId   bigint(20)  auto_increment,
    Description     varchar(50) not null    unique,
    primary key (WorkFeatureId)
)engine=InnoDB;

create table ProjectStatus(
    ProjectStatusId bigint(20)  auto_increment,
    Description     varchar(50) not null    unique,
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
    ProjectStageId  bigint(20)  not null,
    CompletedDate   date,
    primary key (ProjectId),
    constraint foreign key (ProjectTypeId) references ProjectType(ProjectTypeId),
    constraint foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete set null,
    constraint foreign key (ContactPersonId) references Person(PersonId) on delete set null,
    constraint foreign key (ProjectStatusId) references ProjectStatus(ProjectStatusId),
    constraint foreign key (CoordinatorId) references Person(PersonId) on delete set null,
    constraint foreign key (ProjectStageId) references ProjectStage(ProjectStageId)
)engine=InnoDB;

create table ProjectWorkBrief(
    ProjectWorkBriefId  bigint(20)  auto_increment,
    ProjectId           bigint(20)  not null,
    WorkFeatureId       bigint(20)  not null,
    Brief               text,
    primary key (ProjectWorkBriefId),
    unique (ProjectId, WorkFeatureId),
    constraint foreign key (ProjectId) references Project(ProjectId) on delete cascade,
    constraint foreign key (WorkFeatureId) references WorkFeature(WorkFeatureId)
)engine=InnoDB;

create table ProjectEvent(
    ProjectEventId  bigint(20)  auto_increment,
    ProjectId       bigint(20)  not null,
    CommentatorId   bigint(20),
    Comments        text,
    Visible         boolean     default true,
    Created         timestamp   not null,
    primary key (ProjectEventId),
    constraint foreign key (ProjectId) references Project(ProjectId) on delete cascade,
    constraint foreign key (CommentatorId) references Commentator(CommentatorId) on delete set null
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
    primary key (AttendanceId),
    unique (ProjectId, PersonId, InviteDate),
    constraint foreign key (ProjectId) references Project(ProjectId) on delete cascade,
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    constraint foreign key (InvitationConfirmationId) references InvitationConfirmation(InvitationConfirmationId) on delete set null,
    constraint foreign key (DepartmentId) references Department(DepartmentId) on delete set null
)engine=InnoDB;

create table Qualification(
    QualificationId bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    Description     varchar(150),
    AppearOnBadge   boolean default false,
    primary key (QualificationId)
)engine=InnoDB;

create table VolunteerQualification(
    VolunteerQualificationId    bigint(20)  auto_increment,
    PersonId                    bigint(20)  not null,
    QualificationId             bigint(20)  not null,
    Comments                    varchar(100),
    primary key (VolunteerQualificationId),
    unique (PersonId, QualificationId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    constraint foreign key (QualificationId) references Qualification(QualificationId) on delete cascade
)engine=InnoDB;

create table Skill(
    SkillId         bigint(20)  auto_increment,
    Name            varchar(50) not null    unique,
    DepartmentId    bigint(20),
    Description     varchar(250),
    AppearOnBadge   boolean default false,
    primary key (SkillId),
    constraint foreign key (DepartmentId) references Department(DepartmentId) on delete set null
)engine=InnoDB;

create table VolunteerSkill(
    VolunteerSkillId    bigint(20)  auto_increment,
    PersonId            bigint(20)  not null,
    SkillId             bigint(20)  not null,
    Level               integer     not null,
    Comments            varchar(250),
    TrainingDate        date,
    TrainingResults     varchar(15),
    primary key (VolunteerSkillId),
    unique (PersonId, SkillId),
    constraint foreign key (PersonId) references Person(PersonId) on delete cascade,
    constraint foreign key (SkillId) references Skill(SkillId) on delete cascade
)engine=InnoDB;

create table Updates(
    UpdatesId           bigint(20)  auto_increment,
    UpdatedTable        varchar(50) not null,
    PersonId            bigint(20),
    UpdateInformation   text,
    UpdateTime          timestamp   not null,
    primary key (UpdatesId),
    constraint foreign key (PersonId) references Person(PersonId) on delete set null
)engine=InnoDB;

insert into Application (Name, Code, Comments) values
    ('Attendance & Invitations', 'ATTENDANCE', 'Mangaging project invites and gates list'),
    ('Circuit', 'CIRCUIT', 'Managing Circuits in RBC region'),
    ('Congregation', 'CONG', 'Managing Congregations in RBC region'),
    ('Database', 'DATABASE', 'Managing database and definitions'),
    ('Kingdom Halls', 'KINGDOMHALL', 'Managing Kingdom Halls in RBC region'),
    ('Projects', 'PROJECT', 'Managing Projects'),
    ('Skills', 'SKILL', 'Managing volunteer\'s skills and qualifications'),
    ('User', 'USER', 'Managing User access'),
    ('Volunteers', 'VOLUNTEER', 'Managing Volunteers');

insert into OwnershipType (Name) values
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

insert into CongregationRole(Description) values
    ('CoBE'),
    ('Secretary');

insert into InterviewStatus (Description) values
    ('Invite Due'),
    ('Invited'),
    ('No-Show'),
    ('Re-Invite'),
    ('Completed');

insert into Department (DepartmentId, Name, Description) values
    ('1','RBC','RBC Committee and assistants'),
    ('2','Chairman','RBC Chairman\'s office'),
    ('3','Construction','RBC Construction'),
    ('4','Construction Support','RBC Construction Support'),
    ('5','Personal','RBC Personal'),
    ('6','Project Development','RBC Project Development');
insert into Department (Name, SuperDepartmentId, Description) values
    ('Project Coordination','2','Project Coordinators'),
    ('Alarms','3',''),
    ('Block Paving','3',''),
    ('Bricklaying','3',''),
    ('Crpentry','3',''),
    ('Carpeting','3',''),
    ('Ceiling','3',''),
    ('Decorating','3',''),
    ('Electrical','3',''),
    ('Electrical Testing','3',''),
    ('Ground Works','3',''),
    ('Insulation','3',''),
    ('Internal Finishes','3',''),
    ('Landscaping','3',''),
    ('Maintenance Inspection','3',''),
    ('Plasterboard','3',''),
    ('Plastering','3',''),
    ('Plumbing','3',''),
    ('Roof Trusses','3',''),
    ('Roofing','3',''),
    ('Sound','3',''),
    ('Tiling','3',''),
    ('Ventilation','3',''),
    ('Wall Panelling','3',''),
    ('Accounts','4',''),
    ('Audit','4',''),
    ('Equipment','4',''),
    ('Food Service','4',''),
    ('IT Support','4',''),
    ('Installation','4',''),
    ('Materials','4',''),
    ('On Site Services','4',''),
    ('Purchasing','4',''),
    ('Quality Control','4',''),
    ('Scaffolding','4',''),
    ('Admin','5',''),
    ('First Aid','5',''),
    ('Safety','5',''),
    ('Training','5',''),
    ('Volunteer Service','5',''),
    ('Design','6',''),
    ('Legel','6',''),
    ('Real Estate','6','');
insert into Department (Name, SuperDepartmentId, Description) values
    ('Land Acquisition and Sale','49',''),
    ('Land Search','49',''),
    ('Planning Policy','49','');

insert into RbcStatus (Description) values
    ('Active'),
    ('Do Not Use'),
    ('Inactive'),
    ('Pending'),
    ('Reassign');

insert into Appointment (Description) values
    ('Elder'),
    ('Ministerial Servant'),
    ('Publisher');

insert into Fulltime (Description) values
    ('Bethel'),
    ('Publisher'),
    ('Regular Pioneer');

insert into Relationship(Description) values
    ('Elder'),
    ('Family'),
    ('Father'),
    ('Husband'),
    ('Ministerial Servant/Congregation Member'),
    ('Mother'),
    ('Wife'),
    ('Other');

insert into MaritalStatus(Description) values
    ('Divorced'),
    ('Married'),
    ('Other'),
    ('Separated'),
    ('Single'),
    ('Widowed');

insert into Role(Description) values
    ('Assistant'),
    ('Administration'),
    ('Department Safety Advisor'),
    ('Inspector'),
    ('Overseer'),
    ('Trainee'),
    ('Trainer'),
    ('Volunteer');

insert into TradeNumber(Description) values
    ('1st Trade'),
    ('2nd Trade'),
    ('3rd Trade');

insert into Team(Description) values
    ('A'),
    ('B');


insert into ProjectStage(Name, Description, AssignedTo, WorkNotes) values
    ('0','Land Search','Project Development','Land Search.'),
    ('1','Assess Project Viability','Chairman','Assess project viability.'),
    ('002a','Viability Design','Project Development','Following necessary site visits provide initial concept designs, site layouts. More than one design option may be required.'),
    ('002b','Viability Costings','Construction Support','Provide estimated costs for project including all options to be presented to congregations.'),
    ('3','Visit Congregation','Chairman','Meet with all congregation elders, present design options & costs. Agree scope of works (SOW).'),
    ('4','Signed Scope Of Works (SOW)','Chairman','Ensure a scope of works is signed by all congregation elders.'),
    ('5','S-83','Construction Support','S83 to be signed by congregations and retained in RBC files. Only needed if a loan required.'),
    ('6','Detailed Design Work','Project Development','Prepare detailed drawings based upon agreed SOW. Prepare planning application drawings if required.'),
    ('7','Design And Access Statement','Project Development - Design','Prepare design & Access statement.'),
    ('8','RBC Review of Draft Plan','Chairman','Review drawings and plans with RBC and approve or request alterations.'),
    ('9','Congregation Approval of Drawings','Chairman','Project Co-Ordinator to contact Congregation and inform Chairman/PD of decision.'),
    ('10','Planning Application and Approval','Project Development - Design','Submit plans to local authority for approval. Fees to be paid by congregation direct.'),
    ('11','S84 - Loan Application and Resolution','Construction Support','Congregation need to complete and sign S84 together with congregation resolutions. S84 then to be forwarded to branch for approval.'),
    ('12','Bank Account and VAT Application','Construction Support','Open a bank account for projects over £10,000. VAT registration required for all new or rebuilds.'),
    ('13','Foundation Design and Selection','Project Development - Design','Complete foundation design and specification.'),
    ('14','Building Regulation Approval','Project Development','Prepare and submit drawings for building regulation approval.'),
    ('15','Material List','Construction Support','Prepare detailed materials list.'),
    ('16','S-124 - List of Major Cost Elements','Construction Support','Complete S-124.'),
    ('17','Construction Planning','Chairman','Agree construction date and inform congregation.'),
    ('18','Construction Kick-Off Meeting','Chairman','Project Coordinator to organise kick off meeting 4-6 weeks prior to build. Meetings for construction support, trade overseers, basic site safety meetings and food safety training.'),
    ('19','Construction','Construction','Carry out work in line with SOW.'),
    ('20','Project Documentation','Construction Support','Ensure all relevant documentation retained and help in congregation H&S file. All local authority approvals, instruction manuals.'),
    ('21','Complete S-85','Chairman','Complete S-85 and send to Branch.'),
    ('999','Close Project','Chairman','Close project after all project work has been completed.');

insert into ProjectType (Description) values
    ('Minor Work'),
    ('New Build'),
    ('Rebuild'),
    ('Refurbishment');

insert into InvitationConfirmation (Description) values
    ('Assembly/Convention'),
    ('Circuit Visit'),
    ('Holiday'),
    ('Other'),
    ('Uncontactable'),
    ('Work');

insert into Commentator (DepartmentId) values
    ('1'),
    ('2'),
    ('3'),
    ('4'),
    ('5'),
    ('6'),
    ('7'),
    ('47'),
    ('48'),
    ('49'),
    ('50'),
    ('51'),
    ('52');

insert into WorkFeature (Description) values
    ('Design'),
    ('Main Hall'),
    ('Library'),
    ('Auxilliary Room'),
    ('Facilities'),
    ('Foyer'),
    ('Hall Exterior'),
    ('Car Park'),
    ('Landscape');

insert into ProjectStatus (Description) values
    ('On Hold'),
    ('Work In Progress'),
    ('Cancelled'),
    ('Completed');
