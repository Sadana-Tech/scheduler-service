CREATE DATABASE  IF NOT EXISTS `scheduler_details`

DROP TABLE IF EXISTS `job_details`;

CREATE TABLE `job_details` (
  `id` int NOT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `start_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_dt` timestamp DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;