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

