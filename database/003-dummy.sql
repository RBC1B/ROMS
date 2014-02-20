use ROMS;

insert into InterviewSession(KingdomHallId, Date, Time, Comments, UpdateTime, UpdatedBy)
values 
    (null, '2014-01-01', '1500', 'Brian will organise tea and cakes', '2014-02-13 18:27:00', 0),
    (2, '2014-02-01', '1200', null, '2014-01-13 20:04:40', 0),
    (1, '2014-02-15', '1430', null, '2013-12-18 09:56:23', 0);
    
insert into VolunteerInterviewSession (InterviewSessionId, PersonId, VolunteerInterviewStatusCode, Comments, UpdateTime, UpdatedBy)
values 
    (1, 6, 'IT', null, '2014-02-13 18:27:00', 0),
    (1, 8, 'CC', 'Washing his hair that day', '2014-02-13 18:27:00', 0),
    (2, 9, 'CP', 'Was not wearing socks!', '2014-02-13 18:27:00', 0);
