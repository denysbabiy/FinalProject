-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema quizdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quizdb` DEFAULT CHARACTER SET utf8 ;
USE `quizdb` ;

-- -----------------------------------------------------
-- Table `quizdb`.`subjects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`subjects` ;

CREATE TABLE IF NOT EXISTS `quizdb`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_ua` VARCHAR(45) NOT NULL,
  `name_en` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name_ua` ASC) VISIBLE,
  UNIQUE INDEX `name_en_UNIQUE` (`name_en` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`quizzes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`quizzes` ;

CREATE TABLE IF NOT EXISTS `quizdb`.`quiz` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_ua` VARCHAR(45) NOT NULL,
  `name_en` VARCHAR(45) NOT NULL,
  `complexity` INTEGER(10) NOT NULL,
  `time` INT NULL,
  `request_quantity` INT NOT NULL DEFAULT 0,
  `subject_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_quiz_subject_idx` (`subject_id` ASC) VISIBLE,
  CONSTRAINT `fk_quiz_subject`
    FOREIGN KEY (`subject_id`)
    REFERENCES `quizdb`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`users` ;

CREATE TABLE IF NOT EXISTS `quizdb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `passhash` VARCHAR(45) NOT NULL,
  `is_blocked` TINYINT(1) NULL DEFAULT 0,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `is_admin` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO user values (1,'admin@gmail.com','admin','admin',0,'Денис','Бабій',1);


-- -----------------------------------------------------
-- Table `quizdb`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`questions` ;

CREATE TABLE IF NOT EXISTS `quizdb`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question_ua` VARCHAR(255) NOT NULL,
  `question_en` VARCHAR(255) NOT NULL,
  `quiz_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_questions_quizzes1_idx` (`quiz_id` ASC) VISIBLE,
  CONSTRAINT `fk_questions_quizzes1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quizdb`.`quiz` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`answers` ;

CREATE TABLE IF NOT EXISTS `quizdb`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `answer_ua` VARCHAR(255) NOT NULL,
  `answer_en` VARCHAR(255) NOT NULL,
  `is_correct` TINYINT(1) NOT NULL,
  `question_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answers_questions1_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`question_id`)
    REFERENCES `quizdb`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`quizz_results`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`quizz_results` ;

CREATE TABLE IF NOT EXISTS `quizdb`.`quiz_result` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `result` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `quiz_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_quizz_results_users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_quizz_results_quizzes1_idx` (`quiz_id` ASC) VISIBLE,
  CONSTRAINT `fk_quizz_results_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `quizdb`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_quizz_results_quizzes1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quizdb`.`quiz` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
