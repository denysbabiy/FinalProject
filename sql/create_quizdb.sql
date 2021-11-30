-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema quizdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quizdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quizdb` DEFAULT CHARACTER SET utf8;
USE `quizdb`;

-- -----------------------------------------------------
-- Table `quizdb`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`subject`;

CREATE TABLE IF NOT EXISTS `quizdb`.`subject`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`quiz`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`quiz`;

CREATE TABLE IF NOT EXISTS `quizdb`.`quiz`
(
    `id`               INT         NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(45) NOT NULL,
    `complexity`       INT(3)     NOT NULL,
    `time`             INT         NULL,
    `subject_id`       INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_quiz_subject_idx` (`subject_id` ASC) VISIBLE,
    CONSTRAINT `fk_quiz_subject`
        FOREIGN KEY (`subject_id`)
            REFERENCES `quizdb`.`subject` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`user`;

CREATE TABLE IF NOT EXISTS `quizdb`.`user`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `email`      VARCHAR(50) NOT NULL,
    `login`      VARCHAR(45) NOT NULL,
    `passhash`   VARCHAR(45) NOT NULL,
    `is_blocked` TINYINT(1)  NULL DEFAULT 0,
    `name`       VARCHAR(45) NOT NULL,
    `surname`    VARCHAR(45) NOT NULL,
    `is_admin`   TINYINT(1)  NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`question`;

CREATE TABLE IF NOT EXISTS `quizdb`.`question`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `question` VARCHAR(255) NOT NULL,
    `quiz_id`     INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_questions_quizzes1_idx` (`quiz_id` ASC) VISIBLE,
    CONSTRAINT `fk_questions_quizzes1`
        FOREIGN KEY (`quiz_id`)
            REFERENCES `quizdb`.`quiz` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`answer`;

CREATE TABLE IF NOT EXISTS `quizdb`.`answer`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `answer`   VARCHAR(255) ,
    `is_correct`  TINYINT(1)   ,
    `question_id` INT          ,
    PRIMARY KEY (`id`),
    INDEX `fk_answers_questions1_idx` (`question_id` ASC) VISIBLE,
    CONSTRAINT `fk_answers_questions1`
        FOREIGN KEY (`question_id`)
            REFERENCES `quizdb`.`question` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`quiz_result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`quiz_result`;

CREATE TABLE IF NOT EXISTS `quizdb`.`quiz_result`
(
    `id`          INT       NOT NULL AUTO_INCREMENT,
    `result`      DOUBLE      NOT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id`     INT       NOT NULL,
    `quiz_id`     INT       NOT NULL,
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
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`language`;

CREATE TABLE IF NOT EXISTS `quizdb`.`language`
(
    `id`         INT         NOT NULL,
    `short_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `short_name_UNIQUE` (`short_name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizdb`.`subject_description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quizdb`.`subject_description`;

CREATE TABLE IF NOT EXISTS `quizdb`.`subject_description`
(
    `language_id` INT         NOT NULL,
    `subject_id`  INT         NOT NULL,
    `name`        VARCHAR(500) NOT NULL,
    `description` VARCHAR(500) NULL,
    PRIMARY KEY (`language_id`, `subject_id`),
    INDEX `fk_language_has_subject_subject1_idx` (`subject_id` ASC) VISIBLE,
    INDEX `fk_language_has_subject_language1_idx` (`language_id` ASC) VISIBLE,
    CONSTRAINT `fk_language_has_subject_language1`
        FOREIGN KEY (`language_id`)
            REFERENCES `quizdb`.`language` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE ,
    CONSTRAINT `fk_language_has_subject_subject1`
        FOREIGN KEY (`subject_id`)
            REFERENCES `quizdb`.`subject` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;
INSERT INTO user values (2,'admin@gmail.com','admin',123,0,'Адімн','Adminovich',1);
INSERT INTO language values (1,'en');
INSERT INTO language values (2,'ua');


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
