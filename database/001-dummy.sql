-- Create dummy data to access the ROMS database
use ROMS;

-- clean up all existing data
delete from Attendance;
delete from ProjectEvent;
delete from ProjectWorkBrief;
delete from Project;
delete from TitleHolder;
delete from Congregation;
delete from CongregationContact;
delete from Circuit;
delete from KingdomHallFeature;
delete from KingdomHall;
delete from ApplicationAccess;
delete from Assignment;
delete from VolunteerSkill;
delete from VolunteerQualification;
delete from Volunteer;
delete from Skill;
delete from Qualification;
delete from User;
delete from Person;

-- reset the auto-increments
alter table Attendance AUTO_INCREMENT=1;
alter table ProjectEvent AUTO_INCREMENT=1;
alter table ProjectWorkBrief AUTO_INCREMENT=1;
alter table Project AUTO_INCREMENT=1;
alter table TitleHolder AUTO_INCREMENT=1;
alter table Congregation AUTO_INCREMENT=1;
alter table CongregationContact AUTO_INCREMENT=1;
alter table Circuit AUTO_INCREMENT=1;
alter table KingdomHallFeature AUTO_INCREMENT=1;
alter table KingdomHall AUTO_INCREMENT=1;
alter table ApplicationAccess AUTO_INCREMENT=1;
alter table Assignment AUTO_INCREMENT=1;
alter table Skill AUTO_INCREMENT=1;
alter table Qualification AUTO_INCREMENT=1;
alter table VolunteerSkill AUTO_INCREMENT=1;
alter table VolunteerQualification AUTO_INCREMENT=1;
alter table Person AUTO_INCREMENT=1;

insert into Person(Forename, Surname, Comments)
values ('Ramindur', 'Singh', 'Test user account');

insert into Person(Forename, MiddleName, Surname,
                    Street, Town, County, Postcode,
                    Telephone, Mobile, WorkPhone, Email,
                    Comments, BirthDate)
values ('Billy', 'Alfred', 'Bonzo',
        '29 Acacia Road', 'Ericsville', 'Yorkshire', 'Y01 7DU',
        '01904 551550', '07855 841311', '01904 551551', 'billy.bonzo@gmail.com',
        'Circuit overseer and all round good guy', '1961-04-10');

insert into Person(Forename, Surname, Comments)
values ('Ian', 'Smith', 'Slightly grumpy CBE');

insert into Person(Forename, Surname, Comments)
values ('Douglas', 'Fitzpatrick', 'Requested user with minimal permissions');

insert into Person(Forename, MiddleName, Surname, Comments, BirthDate)
values ('Jack', 'Of', 'Alltrades', 'Hightly skilled volunteer', '1961-08-12');

insert into Person(Forename, Surname, Comments)
values ('Arthur', 'Anyjobs', 'Willing volunteer');

insert into Person(Forename, Surname, Comments, BirthDate)
values ('Ken', 'Whereheis', 'Unlikely to show up volunteer', '1978-11-14');


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

-- Kingdom Hall Features
insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 1, "Block paved, spare blocks piled up behind hall");

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 2, "Smells new. Some people don't like the colour of the curtains");

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 3, "Compact and bijou");

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 4, "Sound system not yet wired in");

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 5, "Unisex toilets.");

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 6, "Brickwork a bit dodgy. Very nice otherwise");

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 7, "Red tiles (local building requirement). Lots of silicon.");

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments)
values (1, 8, "100 seats in brown. No seats with arm rests.");


-- Circuits
insert into Circuit(Name, CircuitOverseerId) value ('London No. 12', 1);
insert into Circuit(Name, CircuitOverseerId) value ('Fife No. 2', 2);
insert into Circuit(Name, CircuitOverseerId) value ('North of Watford No. 17', 4);

-- Congregations
insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy)
values('East Sunnytown', '12556', 1, 3, 1, 1, '95', '120+', '2500', '6000', '450', 'Focus on starting an Armenien group');

insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy)
values('West Sunnytown', '12568', 1, 3, 1, 1, '63', '55', '1500', null, '250', 'Beat East Sunnytown at football');

insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy)
values('Grimsby', '13550', null, 2, null, null, null, null, null, null, null, null);

-- Title Holders
-- There is possibly a question over the constraint that prevents a cong. being the title
-- holder for multiple halls. This may happen during a sale/purchase, or if we keep old halls on record
insert into TitleHolder(KingdomHallId, CongregationId) values (2, 1);

insert into TitleHolder(KingdomHallId, CongregationId) values (3, 2);

insert into CongregationContact(CongregationId, CongregationRoleId, PersonId) values (2, 1, 3);

insert into Project(Name,ProjectTypeId,KingdomHallId,Priority,Street,Town,County,Postcode,Telephone,ContactPersonId,RequestDate,VisitDate,EstimateCost,
				ProjectStatusId,SupportingCongregation,ProjectConstraints,CoordinatorId,ProjectStageId)
values ('Slightly scruffy hall spruce', 1, 2, 2, null, null, null, null, null, 2, '2012-09-12', '2012-11-24', '£300 + VAT',
		2, 'Murmurings of approval all round', 'The cong is reknowned as terrible cooks', 4, 3);

insert into ProjectWorkBrief(ProjectId, WorkFeatureId, Brief)
values (1, 6, 'Fill and pain walls. Replace light fixtures. Move radiator to opposite wall. Replace floor tiles.');

insert into ProjectWorkBrief(ProjectId, WorkFeatureId, Brief)
values (1, 3, 'Add shelfs along main wall. Fix the door lock.');

insert into ProjectWorkBrief(ProjectId, WorkFeatureId, Brief)
values (1, 8, 'Replace slabs at front door');

insert into ProjectEvent(ProjectId, CommentatorId, Comments, Visible, Created)
values(1, 7, 'Arranged first meeting with BoE and RBC representatives', true, '2012-07-02');

insert into ProjectEvent(ProjectId, CommentatorId, Comments, Visible, Created)
values(1, 7, 'Agreed project timescales', false, '2012-07-12');

insert into Project(Name,ProjectTypeId,KingdomHallId,Priority,Street,Town,County,Postcode,Telephone,ContactPersonId,RequestDate,VisitDate,EstimateCost,
				ProjectStatusId,SupportingCongregation,ProjectConstraints,CoordinatorId,ProjectStageId)
values ('Brand new hall near Hull', 2, null, null, '73 Industrial Street', 'Hull', null, 'HL12 5FD', '1234 53367', null, null, null, null,
		1, null, null, null, 7 );

-- Skills
insert into Skill(Name, DepartmentId, Description, AppearOnBadge)
values('Compo mixing' , 10,  'Smart enough not to stick his hands in the mix', false);

insert into Skill(Name, DepartmentId, Description, AppearOnBadge)
values('Wheel barrow handling' , 10,  null, true);

insert into Skill(Name, DepartmentId, Description, AppearOnBadge)
values('Block work', 10,  null, true);

insert into Skill(Name, DepartmentId, Description, AppearOnBadge)
values('Decorative brick laying' , 10,  'Does not smear all over the joints', true);

insert into Skill(Name, DepartmentId, Description, AppearOnBadge)
values('Structural brick laying' , 10,  'Good for walls hidden round the back', true);

-- Qualifications
insert into Qualification(Name, Description, AppearOnBadge)
values ('Structural Engineer', 'BEng equivalent', true);

insert into Qualification(Name, Description, AppearOnBadge)
values ('Food preparation', 'HNC', false);

insert into Qualification(Name, Description, AppearOnBadge)
values ('Email technicial', 'Fully trained in the difference between reply and reply to all ', false);

-- Volunteer
insert into Volunteer(PersonId, RbcStatusId, AppointmentId, FulltimeId, Availability,
EmergencyContactId, EmergencyContactRelationshipId, Gender, MaritalStatusId, BaptismDate, InterviewDate,
InterviewerA, InterviewerB, InterviewComments, JoinedDate, FormDate, InterviewStatusId, Oversight, OversightComments,
ReliefUK, ReliefUKComments, ReliefAbroad, ReliefAbroadComments, HHCFormCode, BadgeIssueDate)
values(5, 1, 1, null, 'TTTTTTT',
3, 5, 'M', 2, '1982-06-19', '2008-02-09',
1, 2, 'Jolly nice bloke', '2008-02-11', '2008-01-03', 5, true, 'Very capable...still a jolly nice bloke?',
true, '3 weeks notice', true, '6 weeks notice', 'HHCForm-1', '2010-04-17');

insert into VolunteerTrade(PersonId, Name, ExperienceDescription, ExperienceYears)
values (5, "Balloon Modelling", "Worked for kids parties and street entertainer. Specialised in marine life", 3);
insert into VolunteerTrade(PersonId, Name, ExperienceDescription, ExperienceYears)
values (5, "Juggling", "Balls and skittles. Only once tried to do chainsaws", 1);

-- update the person cong now. Can before because of the relationships between person, circuit and congregation
update Person set CongregationId = 1 where PersonId = 5;

insert into Assignment(PersonId, DepartmentId, RoleId, AssignedDate, TradeNumberId, TeamId)
values(5, 10, 5, '2010-04-11', 1, 1);

insert into Assignment(PersonId, DepartmentId, RoleId, AssignedDate, TradeNumberId, TeamId)
values(5, 51, 1, '2011-03-16', 2, 1);


insert into VolunteerSkill(PersonId, SkillId, Level, Comments, TrainingDate, TrainingResults)
values (5, 1, 1, null, null, null);

insert into VolunteerSkill(PersonId, SkillId, Level, Comments, TrainingDate, TrainingResults)
values (5, 2, 2, null, '2010-05-09', 'No problems');

insert into VolunteerSkill(PersonId, SkillId, Level, Comments, TrainingDate, TrainingResults)
values (5, 4, 1, '... and fast too', null, null);

insert into VolunteerQualification(PersonId, QualificationId, Comments)
values(5, 1, 'Maths is only ok');

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended)
values(1, 5, '2012-07-23', true, null, 10, true);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended)
values(1, 5, '2012-07-24', true, null, 10, true);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended)
values(1, 5, '2012-11-24', null, null, null, null);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended)
values(1, 5, '2012-11-25', null, null, null, null);



insert into Volunteer(PersonId, RbcStatusId, AppointmentId, FulltimeId, Availability,
EmergencyContactId, EmergencyContactRelationshipId, Gender, MaritalStatusId, BaptismDate, InterviewDate,
InterviewerA, InterviewerB, InterviewComments, JoinedDate, FormDate, InterviewStatusId, Oversight, OversightComments,
ReliefUK, ReliefUKComments, ReliefAbroad, ReliefAbroadComments, HHCFormCode, BadgeIssueDate)
values(6, 4, null, null, 'TTFTTTT',
null, null, 'M', 5, null, null,
null, null, null, null, null, 3, false, null,
false, null, false, null, null, null);

update Person set congregationId = 2 where PersonId = 6;

insert into VolunteerQualification(PersonId, QualificationId, Comments)
values(6, 2, 'Hand washing is a bit suspect');

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended)
values(1, 6, '2012-07-23', false, 6, 10, false);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended)
values(1, 6, '2012-07-24', false, 3, 10, false);


insert into Volunteer(PersonId, RbcStatusId, AppointmentId, FulltimeId, Availability,
EmergencyContactId, EmergencyContactRelationshipId, Gender, MaritalStatusId, BaptismDate, InterviewDate,
InterviewerA, InterviewerB, InterviewComments, JoinedDate, FormDate, InterviewStatusId, Oversight, OversightComments,
ReliefUK, ReliefUKComments, ReliefAbroad, ReliefAbroadComments, HHCFormCode, BadgeIssueDate)
values(7, 4, null, 2, 'FFFFFFT',
3, 5, 'M', 4, '1999-07-10', '2012-05-29',
1, null, null, null, '2012-03-13', 3, false, null,
false, null, false, null, null, null);

update Person set congregationId = 3 where PersonId = 7;
