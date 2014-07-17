use ROMS;

create table FixedReport (
    FixedReportId   bigint(20)      not null    auto_increment,
    Name            varchar(100)    not null    unique, 
    Description     varchar(250),
    Query           text            not null,
    Active          boolean         not null default true,
    UpdateTime      timestamp       not null,
    UpdatedBy       bigint(20)      not null,
    primary key (FixedReportId),
    foreign key (UpdatedBy) references Person(PersonId)
)engine=InnoDB;
-- Create Report application
insert into Application (Name, Code, Comments, UpdateTime, UpdatedBy) values
('Reports', 'REPORT', 'Running database queries/reports', NOW(), 0),
('Persons', 'PERSON', 'Access to non-volunteers and volunteers', NOW(), 0);
-- Grant access to System account for Report and Person
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values
(0, 10, 'D', 'D', NOW(), 0),
(0, 11, 'D', 'D', NOW(), 0);

-- Increase size of Email table text to 4000
alter table Email modify Text varchar(4000);