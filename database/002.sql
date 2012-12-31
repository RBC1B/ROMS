/* Add the CircuitOverseerId to the Circuit table */
use ROMS;

alter table Circuit
    add column CircuitOverseerId bigint(20),
    add constraint foreign key (CircuitOverseerId) references Person(PersonId) on delete cascade;
