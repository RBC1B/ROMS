*/Script to add to new colunms to the Volunteer table. "DateCreated & CreatedBy" */

ALTER TABLE `roms`.`volunteer` ADD COLUMN `DateCreated` DATE NULL  AFTER `BadgeIssueDate` , ADD COLUMN `CreatedBy` VARCHAR(60) NULL  AFTER `DateCreated` ;