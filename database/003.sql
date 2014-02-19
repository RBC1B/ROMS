use ROMS;

-- create the InterviewSession tables
create table InterviewSession (
    InterviewSessionId  bigint(20)  not null    auto_increment,
    KingdomHallId       bigint(20),
    Date                date        not null,
    Time                varchar(4)  not null, /* HHmm format */
    Comments            varchar(250),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (InterviewSessionId),
    foreign key (UpdatedBy) references Person(PersonId),
    foreign key (KingdomHallId) references KingdomHall(KingdomHallId)
)engine=InnoDB;

create table InterviewSession_AUD (
    InterviewSessionId  bigint(20)  not null,
    REV                 int         not null,
    REVTYPE             tinyint,
    KingdomHallId       bigint(20),
    Date                date,
    Time                varchar(4), /* HHmm format */
    Comments            varchar(250),
    UpdateTime          timestamp   not null,
    UpdatedBy           bigint(20)  not null,
    primary key (InterviewSessionId, REV)
)engine=InnoDB;


create table VolunteerInterviewStatus (
    VolunteerInterviewStatusCode    char(2)     not null    unique,
    Name                            varchar(50) not null    unique,
    primary key (VolunteerInterviewStatusCode)
)engine=InnoDB;

create table VolunteerInterviewSession (
    VolunteerInterviewSessionId     bigint(20)  not null    auto_increment,
    InterviewSessionId              bigint(20)  not null,
    PersonId                        bigint(20)  not null,
    VolunteerInterviewStatusCode    char(2)     not null,
    Comments                        varchar(250),
    UpdateTime                      timestamp   not null,
    UpdatedBy                       bigint(20)  not null,
    primary key (VolunteerInterviewSessionId),
    foreign key (InterviewSessionId) references InterviewSession(InterviewSessionId),
    unique (PersonId, InterviewSessionId),
    foreign key (VolunteerInterviewStatusCode) references VolunteerInterviewStatus(VolunteerInterviewStatusCode),
    foreign key (PersonId) references Person(PersonId)
)engine=InnoDB;

create table VolunteerInterviewSession_AUD (
    VolunteerInterviewSessionId     bigint(20)  not null,
    REV                             int         not null,
    REVTYPE                         tinyint,
    InterviewSessionId              bigint(20),
    PersonId                        bigint(20),
    VolunteerInterviewStatusCode    char(2),
    Comments                        varchar(250),
    UpdateTime                      timestamp   not null,
    UpdatedBy                       bigint(20)  not null,
    primary key (VolunteerInterviewSessionId, REV)
)engine=InnoDB;

insert into VolunteerInterviewStatus (VolunteerInterviewStatusCode, Name) values
    ('IT', 'Invited'),
    ('CF', 'Confirmed'),
    ('CC', 'Cancelled'),
    ('NS', 'No-Show'),
    ('CP', 'Completed'),
    ('NR', 'Not required');

-- make the interview status nullable, ready to be dropped in the cleanup
alter table Volunteer modify InterviewStatusCode char(2);
alter table Volunteer_AUD modify InterviewStatusCode char(2);

    
    