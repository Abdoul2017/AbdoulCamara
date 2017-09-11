-- MySQL Script generated by MySQL Workbench
-- Sun Sep 10 17:20:45 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering



-- -----------------------------------------------------
-- Schema SuperHero
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SuperHero` ;

-- -----------------------------------------------------
-- Schema SuperHero
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SuperHero` DEFAULT CHARACTER SET utf8 ;
USE `SuperHero` ;

-- -----------------------------------------------------
-- Table `SuperHero`.`SuperPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero`.`SuperPerson` (
  `SuperPersonID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(100) NOT NULL,
  `SuperPower` VARCHAR(45) NULL,
  PRIMARY KEY (`SuperPersonID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero`.`Location` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(45) NOT NULL,
  `LocationDescription` VARCHAR(45) NOT NULL,
  `LocationLong` VARCHAR(30) NOT NULL,
  `LocationLat` VARCHAR(30) NOT NULL,
  `LocationAddress` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero`.`Sighting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero`.`Sighting` (
  `SightingID` INT NOT NULL AUTO_INCREMENT,
  `SightingDate` DATE NOT NULL,
  `LocationID` INT NOT NULL,
  PRIMARY KEY (`SightingID`, `LocationID`),
  INDEX `fk_Sighting_Location1_idx` (`LocationID` ASC),
  CONSTRAINT `fk_Sighting_Location1`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperHero`.`Location` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero`.`Organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero`.`Organization` (
  `OrganizationID` INT NOT NULL AUTO_INCREMENT,
  `OrganizationName` VARCHAR(45) NOT NULL,
  `OrganizationDescription` VARCHAR(45) NOT NULL,
  `OrganizationAddress` VARCHAR(45) NOT NULL,
  `OrganizationCity` VARCHAR(45) NOT NULL,
  `OrganizationState` VARCHAR(45) NOT NULL,
  `OrganizationPhone` VARCHAR(10) NOT NULL,
  `OrganizationEmail` VARCHAR(60) NOT NULL,
  `OrganizationZipCode` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`OrganizationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero`.`SuperPersonOrganization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero`.`SuperPersonOrganization` (
  `SuperPersonID` INT NOT NULL,
  `OrganizationID` INT NOT NULL,
  PRIMARY KEY (`SuperPersonID`, `OrganizationID`),
  INDEX `fk_SuperPerson_has_Organization_Organization1_idx` (`OrganizationID` ASC),
  INDEX `fk_SuperPerson_has_Organization_SuperPerson_idx` (`SuperPersonID` ASC),
  CONSTRAINT `fk_SuperPerson_has_Organization_SuperPerson`
    FOREIGN KEY (`SuperPersonID`)
    REFERENCES `SuperHero`.`SuperPerson` (`SuperPersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SuperPerson_has_Organization_Organization1`
    FOREIGN KEY (`OrganizationID`)
    REFERENCES `SuperHero`.`Organization` (`OrganizationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero`.`SuperPersonSighting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero`.`SuperPersonSighting` (
  `SuperPersonID` INT NOT NULL,
  `SightingID` INT NOT NULL,
  PRIMARY KEY (`SuperPersonID`, `SightingID`),
  INDEX `fk_SuperPerson_has_Sighting_Sighting1_idx` (`SightingID` ASC),
  INDEX `fk_SuperPerson_has_Sighting_SuperPerson1_idx` (`SuperPersonID` ASC),
  CONSTRAINT `fk_SuperPerson_has_Sighting_SuperPerson1`
    FOREIGN KEY (`SuperPersonID`)
    REFERENCES `SuperHero`.`SuperPerson` (`SuperPersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SuperPerson_has_Sighting_Sighting1`
    FOREIGN KEY (`SightingID`)
    REFERENCES `SuperHero`.`Sighting` (`SightingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


