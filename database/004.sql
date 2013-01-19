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


*/Script to add to new colunms to the Volunteer table. "DateCreated & CreatedBy" */

ALTER TABLE `roms`.`volunteer` ADD COLUMN `DateCreated` DATE NULL  AFTER `BadgeIssueDate` , ADD COLUMN `CreatedBy` VARCHAR(60) NULL  AFTER `DateCreated` ;

*/Change to add in "Relief in UK/Abroad" and releavant comments field */

ALTER TABLE `roms`.`volunteer` DROP COLUMN `ReliefAbroadComments` , DROP COLUMN `ReliefAbroad` , CHANGE COLUMN `ReliefUK` `Relief in UK/Abroad` TINYINT(1) NOT NULL  , CHANGE COLUMN `ReliefUKComments` `ReliefComments` VARCHAR(50) NULL  ;


*/Scipt to add "BadgeExpiryDate" and "PDFAcknowledgements" field */

ALTER TABLE `roms`.`volunteer` ADD COLUMN `BadgeExpiryDate` DATE NULL DEFAULT NULL COMMENT 'This needs to be set to 3 Years after the BadgeIssueDate'  AFTER `BadgeIssueDate` , ADD COLUMN `PDFAcknowledgements` BLOB NULL  AFTER `CreatedBy` ;


*/ Audit table creation script */

CREATE  TABLE `roms`.`Audit` (  `AuditID` BIGINT NOT NULL ,  `Field` VARCHAR(60) NULL ,  `OldValue` VARCHAR(60) NULL ,  `NewValue` VARCHAR(60) NULL ,  PRIMARY KEY (`AuditID`) );
