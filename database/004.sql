CREATE TABLE `projectworks` (
  `AssignmentID` bigint(20) NOT NULL,
  `RecipientID` bigint(20) DEFAULT NULL,
  `WorkDetail` varchar(100) DEFAULT NULL,
  `Comments` blob,
  `Status` bigint(20) DEFAULT NULL,
  `Date Started` date DEFAULT NULL,
  `Date Completed` date DEFAULT NULL,
  `projectworkscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`AssignmentID`),
  KEY `FK_Projassignment_idx` (`RecipientID`),
  KEY `FK_Projstatus_idx` (`Status`),
  KEY `FK_Projrecipients_idx` (`RecipientID`),
  CONSTRAINT `FK_Projrecipients` FOREIGN KEY (`RecipientID`) REFERENCES `projrecipient` (`RecipientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Projstatus` FOREIGN KEY (`Status`) REFERENCES `projectstatus` (`ProjectStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Projassignment` FOREIGN KEY (`AssignmentID`) REFERENCES `projassignments` (`AssignmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `projassignments` (
  `AssignmentID` bigint(20) NOT NULL,
  `Assignment` varchar(45) DEFAULT NULL,
  `Description` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`AssignmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Project Works, Assignment lookup table'$$


CREATE TABLE `projrecipient` (
  `RecipientID` bigint(20) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`RecipientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Lookup table to store Project Works, Recipient info'$$


CREATE TABLE `projectstatus` (
  `ProjectStatusId` bigint(20) NOT NULL,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ProjectStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$