-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pitected
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pitected
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pitected` DEFAULT CHARACTER SET latin1 ;
USE `pitected` ;

-- -----------------------------------------------------
-- Table `pitected`.`device_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitected`.`device_type` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `pitected`.`devices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitected`.`devices` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `type` INT(11) NOT NULL DEFAULT '0',
  `status` INT(1) NULL DEFAULT NULL,
  `nodeID` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `device_typeFK_idx` (`type` ASC),
  CONSTRAINT `device_typeFK`
    FOREIGN KEY (`type`)
    REFERENCES `pitected`.`device_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `pitected`.`system`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitected`.`system` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `staticIP` VARCHAR(15) NOT NULL,
  `passphrase` CHAR(32) NOT NULL,
  `status` INT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `pitected`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitected`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `pin` INT(4) NOT NULL,
  `session` VARCHAR(45) NOT NULL,
  `privileges` VARCHAR(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `pitected`.`system_logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pitected`.`system_logs` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `status` INT(1) NOT NULL,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_idFK_idx` (`user_id` ASC),
  INDEX `log_typeFK_idx` (`type` ASC),
  CONSTRAINT `log_typeFK`
    FOREIGN KEY (`type`)
    REFERENCES `pitected`.`device_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_idFK`
    FOREIGN KEY (`user_id`)
    REFERENCES `pitected`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
