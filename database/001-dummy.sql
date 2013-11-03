-- Create dummy data to access the ROMS database
use ROMS;

-- clean up all existing data
delete from Attendance;
delete from ProjectStageOrder;
delete from ProjectStageEvent;
delete from ProjectStageActivity;
delete from ProjectStage;
delete from ProjectWorkBrief;
delete from Project;
delete from Congregation;
delete from CongregationContact;
delete from Circuit;
delete from KingdomHallFeature;
delete from KingdomHall;
delete from ApplicationAccess where PersonId != 0;
delete from Assignment;
delete from VolunteerSkill;
delete from VolunteerQualification;
delete from Volunteer;
delete from Skill;
delete from Qualification;
delete from User where PersonId != 0;
delete from PersonChange;
-- delete all persons apart from the pre-defined system user
delete from Person where PersonId != 0;

-- reset the auto-increments
alter table Attendance AUTO_INCREMENT=1;
alter table ProjectStageOrder AUTO_INCREMENT=1;
alter table ProjectStageEvent AUTO_INCREMENT=1;
alter table ProjectStageActivity AUTO_INCREMENT=1;
alter table ProjectStage AUTO_INCREMENT=1;
alter table ProjectWorkBrief AUTO_INCREMENT=1;
alter table Project AUTO_INCREMENT=1;
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
alter table PersonChange AUTO_INCREMENT=1;
alter table Person AUTO_INCREMENT=1;

insert into Person(Forename, Surname, Street, Comments, UpdateTime, UpdatedBy)
values ('Ramindur', 'Singh', '9 The Alders', 'Test user account', NOW(), 0);

insert into Person(Forename, MiddleName, Surname,
                    Street, Town, County, Postcode,
                    Telephone, Mobile, WorkPhone, Email,
                    Comments, BirthDate, UpdateTime, UpdatedBy)
values ('Billy', 'Alfred', 'Bonzo',
        '29 Acacia Road', 'Ericsville', 'Yorkshire', 'Y01 7DU',
        '01904 551550', '07855 841311', '01904 551551', 'billy.bonzo@gmail.com',
        'Circuit overseer and all round good guy', '1961-04-10', NOW(), 0);

insert into Person(Forename, Surname, Comments, UpdateTime, UpdatedBy)
values ('Ian', 'Smith', 'Slightly grumpy CBE', NOW(), 0);

insert into Person(Forename, Surname, Street, Comments, UpdateTime, UpdatedBy)
values ('Douglas', 'Fitzpatrick', '15 The Grange', 'Requested user with minimal permissions', NOW(), 0);

insert into Person(Forename, MiddleName, Surname, Comments, BirthDate, UpdateTime, UpdatedBy)
values ('Jack', 'Of', 'Alltrades', 'Hightly skilled volunteer', '1961-08-12', NOW(), 0);

insert into Person(Forename, Surname, Comments, UpdateTime, UpdatedBy)
values ('Arthur', 'Anyjobs', 'Willing volunteer', NOW(), 0);

insert into Person(Forename, Surname, Comments, BirthDate, UpdateTime, UpdatedBy)
values ('Ken', 'Whereheis', 'Unlikely to show up volunteer', '1978-11-14', NOW(), 0);


insert into User(PersonId, UserName, Password, UpdateTime, UpdatedBy)
values (1, 'RaminderSingh', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NOW(), 0);

insert into User(PersonId, UserName, Password, UpdateTime, UpdatedBy)
values (2, 'AddOnly', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NOW(), 0);

insert into User(PersonId, UserName, Password, UpdateTime, UpdatedBy)
values (3, 'EditOnly', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NOW(), 0);

insert into User(PersonId, UserName, Password, UpdateTime, UpdatedBy)
values (4, 'ReadOnly', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NOW(), 0);

insert into User(PersonId, UserName, Password, UpdateTime, UpdatedBy)
values (5, 'NoPermissions', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NOW(), 0);

-- RaminderSingh full access (DELETE)
-- Attendance & Invitations
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 1, 'D', 'D', NOW(), 0);
-- Circuit
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 2, 'D', 'D', NOW(), 0);
-- Congregation
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 3, 'D', 'D', NOW(), 0);
-- Database
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 4, 'D', 'D', NOW(), 0);
-- Kingdom Halls
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 5, 'D', 'D', NOW(), 0);
-- Projects
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 6, 'D', 'D', NOW(), 0);
-- Skills
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 7, 'D', 'D', NOW(), 0);
-- User
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 8, 'D', 'D', NOW(), 0);
-- Volunteers
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (1, 9, 'D', 'D', NOW(), 0);

-- AddOnly add-access to all applications
-- Attendance & Invitations
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 1, 'A', 'A', NOW(), 0);
-- Circuit
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 2, 'A', 'A', NOW(), 0);
-- Congregation
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 3, 'A', 'A', NOW(), 0);
-- Database
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 4, 'A', 'A', NOW(), 0);
-- Kingdom Halls
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 5, 'A', 'A', NOW(), 0);
-- Projects
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 6, 'A', 'A', NOW(), 0);
-- Skills
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 7, 'A', 'A', NOW(), 0);
-- User
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 8, 'A', 'A', NOW(), 0);
-- Volunteers
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (2, 9, 'A', 'A', NOW(), 0);


-- EditOnly edit only for all applications
-- Attendance & Invitations
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 1, 'E', 'E', NOW(), 0);
-- Circuit
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 2, 'E', 'E', NOW(), 0);
-- Congregation
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 3, 'E', 'E', NOW(), 0);
-- Database
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 4, 'E', 'E', NOW(), 0);
-- Kingdom Halls
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 5, 'E', 'E', NOW(), 0);
-- Projects
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 6, 'E', 'E', NOW(), 0);
-- Skills
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 7, 'E', 'E', NOW(), 0);
-- User
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 8, 'E', 'E', NOW(), 0);
-- Volunteers
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (3, 9, 'E', 'E', NOW(), 0);


-- ReadOnly read only access
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 1, 'R', 'R', NOW(), 0);
-- Circuit
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 2, 'R', 'R', NOW(), 0);
-- Congregation
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 3, 'R', 'R', NOW(), 0);
-- Database
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 4, 'R', 'R', NOW(), 0);
-- Kingdom Halls
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 5, 'R', 'R', NOW(), 0);
-- Projects
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 6, 'R', 'R', NOW(), 0);
-- Skills
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 7, 'R', 'R', NOW(), 0);
-- User
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 8, 'R', 'R', NOW(), 0);
-- Volunteers
insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values (4, 9, 'R', 'R', NOW(), 0);

-- Kingdom Halls
insert into KingdomHall(Name, Street, Town, County, Postcode, KingdomHallOwnershipTypeCode, Drawings, UpdateTime, UpdatedBy)
values ('Spangly new hall', 'Nice Street', 'Sunnytown', 'Angus', 'DD2 8FE', 'FH', 'None', NOW(), 0);
insert into KingdomHall(Name, Street, Town, County, Postcode, KingdomHallOwnershipTypeCode, Drawings, UpdateTime, UpdatedBy)
values ('Slightly scruffy hall', 'Pleasant Street', 'Grimsby', 'Southshire', 'AB1 2EF', 'LH', 'Detailed, old', NOW(), 0);
insert into KingdomHall(Name, Street, Town, County, Postcode, KingdomHallOwnershipTypeCode, Drawings, UpdateTime, UpdatedBy)
values ('Ripe For Demolition', 'Rundown Street', 'Hull', 'Nowhere', 'ZZ10 4RG', 'RT', 'Destroyed mysteriously', NOW(), 0);

-- Kingdom Hall Features
insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 1, 'Block paved, spare blocks piled up behind hall', NOW(), 0);

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 2, 'Smells new. Some people don\'t like the colour of the curtains', NOW(), 0);

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 3, 'Compact and bijou', NOW(), 0);

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 4, 'Sound system not yet wired in', NOW(), 0);

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 5, 'Unisex toilets.', NOW(), 0);

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 6, 'Brickwork a bit dodgy. Very nice otherwise', NOW(), 0);

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 7, 'Red tiles (local building requirement). Lots of silicon.', NOW(), 0);

insert into KingdomHallFeature(KingdomHallId, HallFeatureId, Comments, UpdateTime, UpdatedBy)
values (1, 8, '100 seats in brown. No seats with arm rests.', NOW(), 0);


-- Circuits
insert into Circuit(Name, CircuitOverseerId, UpdateTime, UpdatedBy) value ('London No. 12', 1, NOW(), 0);
insert into Circuit(Name, CircuitOverseerId, UpdateTime, UpdatedBy) value ('Fife No. 2', 2, NOW(), 0);
insert into Circuit(Name, CircuitOverseerId, UpdateTime, UpdatedBy) value ('North of Watford No. 17', 4, NOW(), 0);

-- Congregations
insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy, UpdateTime, UpdatedBy)
values('East Sunnytown', '12556', 1, 3, 1, 1, '95', '120+', '2500', '6000', '450', 'Focus on starting an Armenien group', NOW(), 0);

insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy, UpdateTime, UpdatedBy)
values('West Sunnytown', '12568', 1, 3, 1, 1, '63', '55', '1500', null, '250', 'Beat East Sunnytown at football', NOW(), 0);

insert into Congregation(Name, Number, KingdomHallId, CircuitId, RbcRegionId, RbcSubRegionId, Publishers,
                         Attendance, Funds, Loans, MonthlyIncome, Strategy, UpdateTime, UpdatedBy)
values('Grimsby', '13550', null, 2, null, null, null, null, null, null, null, null, NOW(), 0);

-- Title Holders
-- There is possibly a question over the constraint that prevents a cong. being the title
-- holder for multiple halls. This may happen during a sale/purchase, or if we keep old halls on record
update KingdomHall set TitleHolderId = 1 where KingdomHallId = 2;

update KingdomHall set TitleHolderId = 2 where KingdomHallId = 3;

insert into CongregationContact(CongregationId, CongregationRoleCode, PersonId, UpdateTime, UpdatedBy) values (2, 'CB', 3, NOW(), 0);

-- Skills
insert into Skill(Name, DepartmentId, Description, SkillCategoryId, UpdateTime, UpdatedBy)
values('Compo mixing' , 10,  'Smart enough not to stick his hands in the mix', 2, NOW(), 0);

insert into Skill(Name, DepartmentId, Description, SkillCategoryId, UpdateTime, UpdatedBy)
values('Wheel barrow handling' , 10,  null, 1, NOW(), 0);

insert into Skill(Name, DepartmentId, Description, SkillCategoryId, UpdateTime, UpdatedBy)
values('Block work', 10,  null, 1, NOW(), 0);

insert into Skill(Name, DepartmentId, Description, SkillCategoryId, UpdateTime, UpdatedBy)
values('Decorative brick laying' , 10,  'Does not smear all over the joints', 1, NOW(), 0);

insert into Skill(Name, DepartmentId, Description, SkillCategoryId, UpdateTime, UpdatedBy)
values('Structural brick laying' , 10,  'Good for walls hidden round the back', 1, NOW(), 0);

-- Qualifications
insert into Qualification(Name, Description, UpdateTime, UpdatedBy)
values ('Structural Engineer', 'BEng equivalent', NOW(), 0);

insert into Qualification(Name, Description, UpdateTime, UpdatedBy)
values ('Food preparation', 'HNC', NOW(), 0);

insert into Qualification(Name, Description, UpdateTime, UpdatedBy)
values ('Email technicial', 'Fully trained in the difference between reply and reply to all', NOW(), 0);

-- Volunteer
insert into Volunteer(PersonId, RbcStatusCode, AppointmentCode, FulltimeCode, Availability,
EmergencyContactId, EmergencyContactRelationshipCode, Gender, MaritalStatusCode, BaptismDate, InterviewDate,
InterviewerA, InterviewerB, InterviewComments, JoinedDate, FormDate, InterviewStatusCode, Oversight, OversightComments,
ReliefUK, ReliefUKComments, ReliefAbroad, ReliefAbroadComments, HHCFormCode, BadgeIssueDate)
values(5, 'AT', 'EL', null, 'TTTTTTT',
3, 'EL', 'M', 'SG', '1982-06-19', '2008-02-09',
1, 4, 'Jolly nice bloke', '2008-02-11', '2008-01-03', 'ID', true, 'Very capable...still a jolly nice bloke?',
true, '3 weeks notice', true, '6 weeks notice', 'HHCForm-1', '2010-04-17');

insert into VolunteerTrade(PersonId, Name, ExperienceDescription, ExperienceYears, UpdateTime, UpdatedBy)
values (5, "Balloon Modelling", "Worked for kids parties and street entertainer. Specialised in marine life", 3, NOW(), 0);
insert into VolunteerTrade(PersonId, Name, ExperienceDescription, ExperienceYears, UpdateTime, UpdatedBy)
values (5, "Juggling", "Balls and skittles. Only once tried to do chainsaws", 1, NOW(), 0);

-- update the person cong now. Can before because of the relationships between person, circuit and congregation
update Person set CongregationId = 1 where PersonId = 5;

insert into Assignment(PersonId, DepartmentId, AssignmentRoleCode, AssignedDate, TradeNumberId, TeamId, UpdateTime, UpdatedBy)
values(5, 10, 'AT', '2010-04-11', 1, 1, NOW(), 0);

insert into Assignment(PersonId, DepartmentId, AssignmentRoleCode, AssignedDate, TradeNumberId, TeamId, UpdateTime, UpdatedBy)
values(5, 51, 'AD', '2011-03-16', 2, 1, NOW(), 0);

insert into Assignment(PersonId, DepartmentId, AssignmentRoleCode, AssignedDate, TradeNumberId, TeamId, UpdateTime, UpdatedBy)
values(6, 22, 'VN', '2006-04-04', 1, 1, NOW(), 0);

insert into VolunteerSkill(PersonId, SkillId, Level, Comments, TrainingDate, TrainingResults, UpdateTime, UpdatedBy)
values (5, 1, 1, null, null, null, NOW(), 0);

insert into VolunteerSkill(PersonId, SkillId, Level, Comments, TrainingDate, TrainingResults, UpdateTime, UpdatedBy)
values (5, 2, 2, null, '2010-05-09', 'No problems', NOW(), 0);

insert into VolunteerSkill(PersonId, SkillId, Level, Comments, TrainingDate, TrainingResults, UpdateTime, UpdatedBy)
values (5, 4, 1, '... and fast too', null, null, NOW(), 0);

insert into VolunteerSkill(PersonId, SkillId, Level, Comments, TrainingDate, TrainingResults, UpdateTime, UpdatedBy)
values (6, 1, 1, null, null, null, NOW(), 0);

insert into VolunteerQualification(PersonId, QualificationId, Comments, UpdateTime, UpdatedBy)
values(5, 1, 'Maths is only ok', NOW(), 0);

insert into Volunteer(PersonId, RbcStatusCode, AppointmentCode, FulltimeCode, Availability,
EmergencyContactId, EmergencyContactRelationshipCode, Gender, MaritalStatusCode, BaptismDate, InterviewDate,
InterviewerA, InterviewerB, InterviewComments, JoinedDate, FormDate, InterviewStatusCode, Oversight, OversightComments,
ReliefUK, ReliefUKComments, ReliefAbroad, ReliefAbroadComments, HHCFormCode, BadgeIssueDate)
values(6, 'RA', null, null, 'TTFTTTT',
null, null, 'M', 'MR', null, null,
null, null, null, null, null, 'RI', false, null,
false, null, false, null, null, null);

update Person set congregationId = 2 where PersonId = 6;

insert into VolunteerQualification(PersonId, QualificationId, Comments, UpdateTime, UpdatedBy)
values(6, 2, 'Hand washing is a bit suspect', NOW(), 0);

insert into Volunteer(PersonId, RbcStatusCode, AppointmentCode, FulltimeCode, Availability,
EmergencyContactId, EmergencyContactRelationshipCode, Gender, MaritalStatusCode, BaptismDate, InterviewDate,
InterviewerA, InterviewerB, InterviewComments, JoinedDate, FormDate, InterviewStatusCode, Oversight, OversightComments,
ReliefUK, ReliefUKComments, ReliefAbroad, ReliefAbroadComments, HHCFormCode, BadgeIssueDate)
values(7, 'DN', null, 'RP', 'FFFFFFT',
3, 'WF', 'M', 'WD', '1999-07-10', '2012-05-29',
1, null, null, null, '2012-03-13', 'RI', false, null,
false, null, false, null, null, null);

update Person set congregationId = 3 where PersonId = 7;


-- projects
insert into Project(Name,ProjectTypeId,KingdomHallId,Priority,Street,Town,County,Postcode,Telephone,ContactPersonId,RequestDate,VisitDate,EstimateCost,
				StatusCode,SupportingCongregation,ProjectConstraints,CoordinatorId,UpdateTime, UpdatedBy)
values ('Slightly scruffy hall spruce', 1, 2, 2, null, null, null, null, null, 2, '2012-09-12', '2012-11-24', '£300 + VAT',
		'WP', 'Murmurings of approval all round', 'The cong is reknowned as terrible cooks', 4, NOW(), 0);

insert into ProjectWorkBrief(ProjectId, WorkFeatureId, Brief, UpdateTime, UpdatedBy)
values (1, 6, 'Fill and pain walls. Replace light fixtures. Move radiator to opposite wall. Replace floor tiles.', NOW(), 0);

insert into ProjectWorkBrief(ProjectId, WorkFeatureId, Brief, UpdateTime, UpdatedBy)
values (1, 3, 'Add shelfs along main wall. Fix the door lock.', NOW(), 0);

insert into ProjectWorkBrief(ProjectId, WorkFeatureId, Brief, UpdateTime, UpdatedBy)
values (1, 8, 'Replace slabs at front door', NOW(), 0);

insert into ProjectStage(ProjectId, ProjectStageTypeId, StatusCode, CreatedTime,
StartedTime, CompletedTime, UpdateTime, UpdatedBy)
values (1, 19, 'CP', '2013-06-13', '2013-06-15', '2013-06-17', '2013-06-19 11:45:00', 1);

insert into ProjectStage(ProjectId, ProjectStageTypeId, StatusCode, CreatedTime,
StartedTime, CompletedTime, UpdateTime, UpdatedBy)
values (1, 20, 'WP', '2013-06-24', '2013-06-25', null, '2013-06-21 17:45:00', 1);

insert into ProjectStage(ProjectId, ProjectStageTypeId, StatusCode, CreatedTime,
StartedTime, CompletedTime, UpdateTime, UpdatedBy)
values (1, 21, 'OH', '2013-07-13', null, null, '2013-07-08 11:45:00', 1);

insert into ProjectStageOrder(ProjectId, ProjectStageId, PreviousProjectStageId, NextProjectStageId, UpdateTime, UpdatedBy)
values (1, 1, null, 2, '2013-07-08 11:45:00', 1);

insert into ProjectStageOrder(ProjectId, ProjectStageId, PreviousProjectStageId, NextProjectStageId, UpdateTime, UpdatedBy)
values (1, 2, 1, 3, '2013-07-08 11:45:00', 1);

insert into ProjectStageOrder(ProjectId, ProjectStageId, PreviousProjectStageId, NextProjectStageId, UpdateTime, UpdatedBy)
values (1, 3, 2, null, '2013-07-08 11:45:00', 1);

insert into ProjectStageActivity(ProjectStageId, ProjectStageActivityTypeId, AssignedVolunteerId, StatusCode, Comments, UpdateTime, UpdatedBy)
values(1, 1, 5, 'OH', 'Should be straight forward', '2013-07-08 11:45:00', 1);

insert into ProjectStageActivity(ProjectStageId, ProjectStageActivityTypeId, AssignedVolunteerId, StatusCode, Comments, UpdateTime, UpdatedBy)
values(1, 2, 5, 'OH', 'Gonna be tricky!', '2013-07-08 11:46:00', 1);

insert into ProjectStageActivityTask (ProjectStageActivityId, Name, AssignedVolunteerId, StatusCode, Comments, CreatedTime,
StartedTime, CompletedTime, UpdateTime, UpdatedBy)
values (1, 'Ponder it for a while', 5, 'OH', 'Fingernail chewing optional', '2013-07-09 11:45:00',
'2013-07-09 14:23:30', '2013-07-10 08:17:00', '2013-07-10 08:17:00', 1);

insert into ProjectStageActivityTask (ProjectStageActivityId, Name, AssignedVolunteerId, StatusCode, Comments, CreatedTime,
StartedTime, CompletedTime, UpdateTime, UpdatedBy)
values (1, 'Ponder it a little bit more', 6, 'OH', null, '2013-07-11 17:25:48',
null, null, '2013-07-11 17:25:48', 1);

insert into ProjectStageActivityTaskEvent(ProjectStageActivityTaskId, ProjectStageActivityTaskEventTypeCode, Comments, CreateTime, CreatedBy) 
values (1, 'ST', null, '2013-07-09 14:23:30', 5);

insert into ProjectStageActivityTaskEvent(ProjectStageActivityTaskId, ProjectStageActivityTaskEventTypeCode, Comments, CreateTime, CreatedBy) 
values (1, 'NT', 'these are the notes I have added', '2013-07-09 14:25:30', 5);

insert into Project(Name,ProjectTypeId,KingdomHallId,Priority,Street,Town,County,Postcode,Telephone,ContactPersonId,RequestDate,VisitDate,EstimateCost,
                StatusCode,SupportingCongregation,ProjectConstraints,CoordinatorId, UpdateTime, UpdatedBy)
values ('Brand new hall near Hull', 2, null, null, '73 Industrial Street', 'Hull', null, 'HL12 5FD', '1234 53367', null, null, null, null,
        'OH', null, null, null, NOW(), 0);

-- volunteer attendance
insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended, UpdateTime, UpdatedBy)
values(1, 5, '2012-07-23', true, null, 10, true, NOW(), 0);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended, UpdateTime, UpdatedBy)
values(1, 5, '2012-07-24', true, null, 10, true, NOW(), 0);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended, UpdateTime, UpdatedBy)
values(1, 5, '2012-11-24', null, null, null, null, NOW(), 0);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended, UpdateTime, UpdatedBy)
values(1, 5, '2012-11-25', null, null, null, null, NOW(), 0);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended, UpdateTime, UpdatedBy)
values(1, 6, '2012-07-23', false, 6, 10, false, NOW(), 0);

insert into Attendance(ProjectId, PersonId, InviteDate, AbleToCome, InvitationConfirmationId, DepartmentId, Attended, UpdateTime, UpdatedBy)
values(1, 6, '2012-07-24', false, 3, 10, false, NOW(), 0);

insert into PersonChange(PersonId, OldForeName, OldMiddleName, OldSurname, OldStreet, OldTown, OldCounty, OldPostcode, OldTelephone, OldMobile, OldWorkPhone, OldEmail, 
                                   NewForeName, NewMiddleName, NewSurname, NewStreet, NewTown, NewCounty, NewPostcode, NewTelephone, NewMobile, NewWorkPhone, NewEmail,
                                   Comment, ChangeDate, FormUpdated)
values(3, 'OldBilly','OldAlfred', 'OldBonzo', '29 OldAcacia Road', 'OldEricsville', 'OldYorkshire', 'O01 7DU', '01904 551550', '07855 841311', '01904 551551', 'old.billy.bonzo@gmail.com',
          'NewBilly','NewAlfred', 'NewBonzo', '29 NewAcacia Road', 'NewEricsville', 'NewYorkshire', 'N01 7DU', '11904 551550', '17855 841311', '11904 551551', 'new.billy.bonzo@gmail.com',
          'Old to new', '2013-07-24', false);
insert into PersonChange(PersonId, ChangeDate, FormUpdated)
values(3, '2013-07-25', false);
insert into PersonChange(PersonId, ChangeDate, FormUpdated)
values(3, '2013-07-26', true);
-- Only run this if you want to test out the Email for Person Change functionality
-- Please use your email address rather than someone elses!!!
-- update Person set Email="ramindur.singh@blackcrowsys.com" where PersonId=1;
-- insert into MailRecipient (MailTypeId, PersonId) values (1,1);
