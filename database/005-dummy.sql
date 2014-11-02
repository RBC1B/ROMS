insert into Project(Name, KingdomHallId, CoordinatorId, MinorWork, RequestDate) values
('KH 1', 1, 1, true, '2014-09-23'),
('KH 2', 2, 2, false, '2014-01-02');

insert into ProjectDepartmentSession (ProjectId, DepartmentId, FromDate, ToDate)
values
(1, 10, '2014/12/18', '2014/12/21'),
(2, 10, '2015/01/06', '2015/01/09');

insert into ProjectAvailability (ProjectDepartmentSessionId, PersonId)
values
(1,5),
(1,6),
(2,5),
(2,6);