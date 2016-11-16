DROP DATABASE IF EXISTS test;

CREATE DATABASE test;

CREATE TABLE `test`.`users` (
`ID` INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`NAME` VARCHAR(25) NOT NULL,
`AGE` INT NOT NULL,
`IS_ADMIN` BIT(1) NOT NULL DEFAULT false,
`CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('Artem', '23');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('Riki', '23');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('dwajot', '15');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('Petro', '22');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('Vasiliy', '30');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('Daniel', '20');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('Andrey', '24');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('artem', '23');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('user', '1');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('noname', '0');
INSERT INTO `test`.`users` (`NAME`, `AGE`) VALUES ('incognito', '0');