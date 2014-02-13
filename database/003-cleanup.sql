use ROMS;

/*
 * Make non-backwards compatible changes after data migration is complete for the 
 * interview sessions
 */
alter table Volunteer drop foreign key Volunteer_ibfk_10;
alter table Volunteer drop column InterviewStatusCode;
alter table Volunteer drop column InterviewDate;

-- drop the old reference table
drop table InterviewStatus;
