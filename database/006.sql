*/Change to add in "Relief in UK/Abroad" and releavant comments field */

ALTER TABLE `roms`.`volunteer` DROP COLUMN `ReliefAbroadComments` , DROP COLUMN `ReliefAbroad` , CHANGE COLUMN `ReliefUK` `Relief in UK/Abroad` TINYINT(1) NOT NULL  , CHANGE COLUMN `ReliefUKComments` `ReliefComments` VARCHAR(50) NULL  ;