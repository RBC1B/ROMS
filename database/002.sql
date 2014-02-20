use ROMS;

-- corrections to the audit table
-- since it is not currently used, we can drop and recreate it
drop table ProjectStageActivityTask_AUD;

create table ProjectStageActivityTask_AUD (
    ProjectStageActivityTaskId  bigint(20),
    REV                         int         not null,
    REVTYPE                     tinyint,
    ProjectStageActivityId      bigint(20)  not null,
    Name                        varchar(250),
    AssignedUserId              bigint(20)  not null,
    StatusCode                  char(2)     not null,
    Comments                    varchar(1000),
    CreatedTime                 timestamp   not null,
    StartedTime                 timestamp   null,
    CompletedTime               timestamp   null,
    UpdateTime                  timestamp   not null,
    UpdatedBy                   bigint(20)  not null,
    primary key (ProjectStageActivityTaskId, REV)
)engine=InnoDB;

-- add missing project event types
insert into ProjectStageEventType (ProjectStageEventTypeCode, Name) values ('CR', 'Created');

insert into ProjectStageActivityEventType (ProjectStageActivityEventTypeCode, Name) values ('CR', 'Created');

insert into ProjectStageActivityTaskEventType (ProjectStageActivityTaskEventTypeCode, Name) values ('CR', 'Created');

-- Clean up Email related tables
create table EmailType(
    EmailCode               varchar(2)      not null,
    Description             varchar(150),
    primary key (EmailCode)
)engine=InnoDB;

insert into EmailType (EmailCode, Description) values
    ('VU','Volunteer Information Update'),
    ('TU','Training Information update');

create table EmailRecipient(
    EmailRecipientId        bigint(20)      not null     auto_increment,
    EmailCode               varchar(2)      not null,
    PersonId                bigint(20)      not null,
    primary key (EmailRecipientId),
    constraint unique (EmailCode, PersonId),
    foreign key (EmailCode) references EmailType(EmailCode) on delete cascade,
    foreign key (PersonId) references Person(PersonId) on delete cascade
)engine=InnoDB;

drop table MailRecipient;

drop table MailType;

drop table EmailAttachment;