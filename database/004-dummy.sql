use ROMS;

insert into FixedReport(Name, Description, Query, UpdateTime, UpdatedBy)
values 
    ('Current Users', null, 'select * from User where PersonId > 0', CURRENT_TIMESTAMP, 0),
    ('Department Skill Volunteers', 'Count of number of volunteers per department skill', 'select Skill.SkillId, Department.Name AS DepartmentName, Skill.Name AS SkillName, count(*) as VolunteerCount
from Skill
inner join Department on Department.DepartmentId = Skill.SkillId
left outer join VolunteerSkill on VolunteerSkill.SkillId = Skill.SkillId
group by Skill.SkillId, Skill.Name, Department.Name
order by Department.Name, Skill.Name', CURRENT_TIMESTAMP, 0);

insert into ApplicationAccess(PersonId, ApplicationId, DepartmentAccess, NonDepartmentAccess, UpdateTime, UpdatedBy)
values
(1, 10, 'D', 'D', NOW(), 0),
(1, 11, 'D', 'D', NOW(), 0);