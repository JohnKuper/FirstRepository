CREATE DATABASE IF NOT exists `phonestation`  DEFAULT CHARACTER SET utf8;

use phonestation;

DROP TABLE IF exists subscribers;

CREATE TABLE `subscribers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Surname` varchar(30) DEFAULT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Patronymic` varchar(30) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Flatnumber` varchar(4) DEFAULT NULL,
  `DateofRegistration` varchar(20) DEFAULT NULL,
  `Tarrif` varchar(50) DEFAULT NULL);

INSERT INTO `subscribers`(`ID`,`Surname`,`Name`,`Patronymic`,`Address`,`Flatnumber`,`DateofRegistration`,`Tarrif`)
VALUES (1,'Кузнецов','Петр','Николаевич','Кирова 125','56','19.07.2014','Безлимитный');

INSERT INTO `subscribers`(`ID`,`Surname`,`Name`,`Patronymic`,`Address`,`Flatnumber`,`DateofRegistration`,`Tarrif`)
VALUES (2,'Лебедев','Алексей','Михайлович','Ленина 13','216','19.07.2014','Поминутный');

INSERT INTO `subscribers`(`ID`,`Surname`,`Name`,`Patronymic`,`Address`,`Flatnumber`,`DateofRegistration`,`Tarrif`)
VALUES (3,'Белов','Александр','Петрович','Дзержинского 67','33','20.07.2014','Смешанный');

INSERT INTO `subscribers`(`ID`,`Surname`,`Name`,`Patronymic`,`Address`,`Flatnumber`,`DateofRegistration`,`Tarrif`)
VALUES (4,'Егорова','Ксения','Владимировна','Удмуртская 89','21','21.07.2014','Безлимитный');

INSERT INTO `subscribers`(`ID`,`Surname`,`Name`,`Patronymic`,`Address`,`Flatnumber`,`DateofRegistration`,`Tarrif`)
VALUES (5,'Мартынова','Наталья','Сергеевна','Песочная 35','99','21.07.2014','Безлимитный');
