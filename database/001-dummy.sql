-- Create dummy data to access the ROMS database
use ROMS;

insert into Person(Forename, Surname, Comments)
values ('Ramindur', 'Singh', 'Test user account');

insert into User(PersonId, UserName, Password)
values (1, 'RaminderSingh', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');

-- Attendance & Invitations
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 1, 4, 4);

-- Circuit
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 2, 4, 4);

-- Congregation
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 3, 4, 4);

-- Database
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 4, 4, 4);

-- Kingdom Halls
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 5, 4, 4);

-- Projects
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 6, 4, 4);

-- Skills
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 7, 4, 4);

-- User
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 8, 4, 4);

-- Volunteers
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (1, 9, 4, 4);
