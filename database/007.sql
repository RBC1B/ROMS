*/Scipt to add "BadgeExpiryDate" and "PDFAcknowledgements" field */

ALTER TABLE `roms`.`volunteer` ADD COLUMN `BadgeExpiryDate` DATE NULL DEFAULT NULL COMMENT 'This needs to be set to 3 Years after the BadgeIssueDate'  AFTER `BadgeIssueDate` , ADD COLUMN `PDFAcknowledgements` BLOB NULL  AFTER `CreatedBy` ;