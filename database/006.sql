-- February 2015 Changes to database
-- change is for LDC form submissions for forms > 2.5 years
use ROMS;

alter table Volunteer add column SubmitNewLDCFormEmailSent boolean default false;

alter table Volunteer_AUD add column SubmitNewLDCFormEmailSent boolean default false;