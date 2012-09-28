-- Create dummy data to access the ROMS database
use ROMS;

-- clean up all existing data
delete from Project;
delete from Congregation;
delete from CongregationContact;
delete from Circuit;
delete from KingdomHall;
delete from ApplicationAccess;
delete from Person;

-- reset the auto-increments
alter table Project AUTO_INCREMENT=1;
alter table Congregation AUTO_INCREMENT=1;
alter table CongregationContact AUTO_INCREMENT=1;
alter table Circuit AUTO_INCREMENT=1;
alter table KingdomHall AUTO_INCREMENT=1;
alter table ApplicationAccess AUTO_INCREMENT=1;
alter table Person AUTO_INCREMENT=1;

insert into Person(Forename, Surname, Comments)
values ('Ramindur', 'Singh', 'Test user account');

insert into Person(Forename, Surname, Comments)
values ('Billy', 'Bonzo', 'Circuit overseer and all round good guy');

insert into Person(Forename, Surname, Comments)
values ('Ian', 'Smith', 'Slightly grumpy CBE');

insert into Person(Forename, Surname, Comments)
values ('Douglas', 'Fitzpatrick', 'Requested user with minimal permissions');



insert into User(PersonId, UserName, Password)
values (1, 'RaminderSingh', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');

insert into User(PersonId, UserName, Password)
values (4, 'DouglasF', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');

-- RaminderSingh full access
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

-- DouglasF read only access
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 1, 1, 0);
-- Circuit
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 2, 1, 0);
-- Congregation
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 3, 1, 0);
-- Database
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 4, 1, 0);
-- Kingdom Halls
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 5, 1, 0);
-- Projects
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 6, 1, 0);
-- Skills
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 7, 1, 0);
-- User
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 8, 1, 0);
-- Volunteers
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess)
values (4, 9, 1, 0);

-- Kingdom Halls
insert into KingdomHall(Name, Street, Town, County, Postcode, OwnershipTypeId, Drawings)
values ('Spangly new hall', 'Nice Street', 'Sunnytown', 'Angus', 'DD2 8FE', 1, 'None');
insert into KingdomHall(Name, Street, Town, County, Postcode, OwnershipTypeId, Drawings)
values ('Slightly scruffy hall', 'Pleasant Street', 'Grimsby', 'Southshire', 'AB1 2EF', 2, 'Detailed, old');
insert into KingdomHall(Name, Street, Town, County, Postcode, OwnershipTypeId, Drawings)
values ('Ripe For Demolition', 'Rundown Street', 'Hull', 'Nowhere', 'ZZ10 4RG', 3, 'Destroyed mysteriously');

insert into Circuit(Name) value ('London No. 12');
insert into Circuit(Name) value ('Fife No. 2');
insert into Circuit(Name) value ('North of Watford No. 17');

insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy)
values('East Sunnytown', '12556', 1, 3, 1, 1, '95', '120+', '2500', '6000', '450', 'Focus on starting an Armenien group');

insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy)
values('West Sunnytown', '12568', 1, 3, 1, 1, '63', '55', '1500', null, '250', 'Beat East Sunnytown at football');

insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy)
values('Grimsby', '13550', null, 2, null, null, null, null, null, null, null, null);

insert into CongregationContact(CongregationId, CongregationRoleId, PersonId)
values (2, 1, 3);

insert into Project(Name,ProjectTypeId,KingdomHallId,Priority,Street,Town,County,Postcode,Telephone,ContactPersonId,RequestDate,VisitDate,EstimateCost,
				ProjectStatusId,SupportingCongregation,ProjectConstraints,CoordinatorId,ProjectStageId)
values ('Slightly scruffy hall spruce', 1, 2, 2, null, null, null, null, null, 2, '2012-09-12', '2012-11-24', '£300 + VAT',
		2, 'Murmurings of approval all round', 'The cong is reknowned as terrible cooks', 4, 3);

insert into Project(Name,ProjectTypeId,KingdomHallId,Priority,Street,Town,County,Postcode,Telephone,ContactPersonId,RequestDate,VisitDate,EstimateCost,
				ProjectStatusId,SupportingCongregation,ProjectConstraints,CoordinatorId,ProjectStageId)
values ('Brand new hall near Hull', 2, null, null, '73 Industrial Street', 'Hull', null, 'HL12 5FD', '1234 53367', null, null, null, null,
		1, null, null, null, 7 );
