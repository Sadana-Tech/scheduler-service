create schema scheduler_details;

CREATE TABLE `scheduler_details`.`job_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_name` VARCHAR(45) NULL,
  `start_dt` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `end_dt` DATETIME NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
