/*Script to Alter the Circuit table */


ALTER TABLE Circuit ADD COLUMN CircuitOverseerID BIGINT(20);

/*Script to add FK relationship to Person table */


ALTER TABLE Circuit ADD 
      CONSTRAINT FK_Person
      FOREIGN    KEY (CircuitOverseerID)
      REFERENCES Person(PersonID);


/*Script to add values to CircuitOverseerId column */


update ROMS.Circuit set CircuitOverseerId = 1
where CircuitID = 1;

update ROMS.Circuit set CircuitOverseerId = 2
where CircuitID = 2;

update ROMS.Circuit set CircuitOverseerId = 4
where CircuitID = 3;