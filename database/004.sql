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
