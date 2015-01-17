-- October 2014 Changes to database
-- most of the changes are for Projects.
-- Note: As there is no data on production, it is easier to simply restart with
-- Project table.
use ROMS;

-- drop all unnecessary tables
drop table Attendance;
drop table Attendance_AUD;

drop table ProjectWorkBrief;
drop table ProjectWorkBrief_AUD;

drop table ProjectStageActivityTaskEvent;
drop table ProjectStageActivityEvent;
drop table ProjectStageEvent;

drop table ProjectStageActivityTask;
drop table ProjectStageActivityTask_AUD;

drop table ProjectStageActivity;
drop table ProjectStageActivity_AUD;

drop table ProjectStageOrder;
drop table ProjectStageOrder_AUD;

drop table ProjectStage;
drop table ProjectStage_AUD;

drop table ProjectStageActivityTaskEventType;
drop table ProjectStageActivityEventType;
drop table ProjectStageEventType;

drop table ProjectStageTypeActivityType;
drop table ProjectStageTypeActivityType_AUD;

drop table ProjectStageActivityType;
drop table ProjectStageActivityType_AUD;

drop table ProjectTypeStageType;
drop table ProjectTypeStageType_AUD;

drop table ProjectStageType;
drop table ProjectStageType_AUD;

drop table Project;
drop table Project_AUD;

drop table ProjectStatus;
drop table ProjectType;

create table AvailabilityStatus (
    AvailabilityStatusCode          char(2)     not null    unique,
    Name                            varchar(50) not null    unique,
    primary key (AvailabilityStatusCode)
)engine=InnoDB;

create table Project(
    ProjectId       bigint(20)  auto_increment,
    Name            varchar(250) not null    unique,
    KingdomHallId   bigint(20),
    CoordinatorId   bigint(20) default null,
    MinorWork       boolean default true,
    RequestDate     date,
    CompletedDate   date,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key (ProjectId),
    foreign key (KingdomHallId) references KingdomHall(KingdomHallId) on delete set null,
    foreign key (CoordinatorId) references Person(PersonId) on delete set null,
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table Project_AUD (
    ProjectId       bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    Name            varchar(50) not null,
    KingdomHallId   bigint(20),
    CoordinatorId   bigint(20),
    MinorWork       boolean,
    RequestDate     date,
    CompletedDate   date,
    UpdateTime      timestamp,
    UpdatedBy       bigint(20),
    primary key (ProjectId, REV)
)engine=InnoDB;

create table ProjectDepartmentSession (
    ProjectDepartmentSessionId bigint(20) auto_increment,
    ProjectId       bigint(20),
    DepartmentId    bigint(20),
    FromDate        date,
    ToDate          date,
    Sunday          boolean default false,
    UpdateTime      timestamp not null,
    UpdatedBy       bigint(20) not null,
    primary key(ProjectDepartmentSessionId),
    foreign key(ProjectId) references Project(ProjectId) on delete set null,
    foreign key(DepartmentId) references Department(DepartmentId) on delete set null,
    foreign key (UpdatedBy) references Person(PersonId),
    unique (ProjectId, DepartmentId, FromDate)
)engine=InnoDB;

create table ProjectDepartmentSession_AUD (
    ProjectDepartmentSessionId bigint(20),
    REV             int         not null,
    REVTYPE         tinyint,
    ProjectId       bigint(20),
    DepartmentId    bigint(20),
    Name            varchar(250),
    FromDate        date,
    ToDate          date,
    UpdateTime      timestamp not null,
    UpdatedBy       bigint(20) not null,
    primary key(ProjectDepartmentSessionId, REV)
)engine=InnoDB;

create table ProjectAvailability(
    ProjectAvailabilityId       bigint(20) auto_increment,
    ProjectDepartmentSessionId bigint(20),
    PersonId                    bigint(20),
    EmailSent                   boolean default false,
    PersonResponded             boolean default false,
    OverseerConfirmed           boolean default false,
    ConfirmationEmail           boolean default false,
    AvailabilityStatusCode      char(2),
    TransportRequired           boolean default false,
    OfferTransport              boolean default false,
    AccommodationRequired        boolean default false,
    DatesChanged                boolean default false,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key(ProjectAvailabilityId),
    unique (ProjectDepartmentSessionId, PersonId),
    foreign key(ProjectDepartmentSessionId) references ProjectDepartmentSession(ProjectDepartmentSessionId) on delete cascade,
    foreign key(PersonId) references Person(PersonId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ProjectAvailability_AUD(
    ProjectAvailabilityId       bigint(20),
    REV                         int         not null,
    REVTYPE                     tinyint,
    ProjectDepartmentSessionId bigint(20),
    PersonId                    bigint(20),
    EmailSent                   boolean,
    PersonResponded             boolean,
    OverseerConfirmed           boolean,
    ConfirmationEmail           boolean,
    AvailabilityStatusCode      char(2),
    TransportRequired           boolean,
    OfferTransport              boolean,
    AccomodationRequired        boolean,
    DatesChanged                boolean,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,        
    primary key(ProjectAvailabilityId, REV)
)engine=InnoDB;

create table ProjectAttendance(
    ProjectAttendanceId         bigint(20) auto_increment,
    ProjectAvailabilityId       bigint(20)  not null,
    AvailableDate               date not null,
    Required                    boolean default false,
    Attended                    boolean default false,
    UpdateTime      timestamp   not null,
    UpdatedBy       bigint(20)  not null,
    primary key(ProjectAttendanceId),
    unique (ProjectAvailabilityId, AvailableDate),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;

create table ProjectAttendance_AUD(
    ProjectAttendanceId         bigint(20),
    REV                         int         not null,
    REVTYPE                     tinyint,
    ProjectAvailabilityId       bigint(20),
    AvailableDate               date,
    Required                    boolean,
    Attended                    boolean,
    UpdateTime                  timestamp,
    UpdatedBy                   bigint(20),
    primary key(ProjectAttendanceId, REV),
    unique (ProjectAvailabilityId, AvailableDate)
)engine=InnoDB;

-- Required data
insert into AvailabilityStatus(AvailabilityStatusCode, Name) values
('AV', 'Available'),
('CO', 'Not available - CO Visit'),
('CA', 'Not available - Assembly'),
('HD', 'Not available - Holiday'),
('WK', 'Not available - Work'),
('NA', 'Not available - Other');

-- Increase email test size
alter table Email modify Text varchar(65000);
alter table Email add Html boolean default false;