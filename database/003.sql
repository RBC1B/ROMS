/* Move the congregation reference from the volunteer to the person */
use ROMS;

alter table Person
    add column CongregationId bigint(20),
    add constraint foreign key (CongregationId) references Congregation(CongregationId) on delete set null;

update  Person, Volunteer
set     Person.CongregationId = Volunteer.CongregationId
where   Person.personId = Volunteer.PersonId;

alter table Volunteer
    drop foreign key Volunteer_ibfk_3,
    drop column CongregationId;