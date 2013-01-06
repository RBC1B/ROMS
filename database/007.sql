*/ Audit table creation script */

CREATE  TABLE `roms`.`Audit` (  `AuditID` BIGINT NOT NULL ,  `Field` VARCHAR(60) NULL ,  `OldValue` VARCHAR(60) NULL ,  `NewValue` VARCHAR(60) NULL ,  PRIMARY KEY (`AuditID`) );