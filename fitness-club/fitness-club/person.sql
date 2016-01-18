-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.11 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-08-07 12:43:45
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
-- Dumping data for table fitness-club.person: ~17 rows (approximately)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
REPLACE INTO `person` (`person_id`, `post`, `firstname`, `lastname`, `address`, `telephone`, `description`, `login`, `password`) VALUES
	(1, 'ADMIN', 'Anastasia', 'Kisel', 'Nemanskaya Street, 15-57', '80291742419', 'wear glasses', 'nastya', 'nastya'),
	(2, 'ADMIN', 'Artem', 'Baturin', 'Revolution Street, 24-75', '00331452783', 'good at football', 'artem', 'artem'),
	(3, 'CLIENT', 'Lena', 'Orlova', 'Kharyjina, 15-33', '80256743619', 'fat', 'lena', 'lena'),
	(4, 'CLIENT', 'Anna', 'Kisel', 'Gorodzetskaya , 15-60', '80335463831', '14 years', 'anya', 'anya'),
	(5, 'CLIENT', 'Svetlana', 'Kisel', 'Olshevskogo, 17-22', '80256438291', 'mother of two children', 'sveta', 'sveta'),
	(6, 'CLIENT', 'Dima', 'Luchkovsky', 'Lojonskaya, 32-12', '80331242793', NULL, 'dima', 'dima'),
	(7, 'CLIENT', 'Pasha', 'Sheglinsky', 'Smolenskaya, 14-43', '80296435291', NULL, 'pasha', 'pasha'),
	(8, 'CLIENT', 'Olga', 'Ivanova', 'Vera Khoryjaya, 13-42', '80335243111', NULL, 'olga', 'olga'),
	(9, 'CLIENT', 'Sasha', 'Shamgin', 'Kosmonavtov, 54-22', '80297565323', NULL, 'sasha', 'sasha'),
	(10, 'CLIENT', 'Vera', 'Yakovenko', 'Kalinovskogo, 23-12', '80442139786', NULL, 'vera', 'vera'),
	(11, 'CLIENT', 'Lera', 'Rudenko', 'Slavinskogo, 31-53', '80331187654', NULL, 'lera', 'lera'),
	(12, 'CLIENT', 'Kolya', 'Saminsky', 'Sedih, 44-22', '80254132532', NULL, 'kolya', 'kolya'),
	(13, 'CLIENT', 'Yaroslav', 'Zadvorni', 'Teplichnaya , 66-99', '80336549373', NULL, 'slava', 'slava'),
	(14, 'CLIENT', 'Diana', 'Martines-Sokolovskaya', 'Ostroshisckogo, 87-120', '80254378321', 'student', 'dianka', 'dianka'),
	(15, 'CLIENT', 'Vasilisa', 'Kulik', 'Ostroshicskogo, 22-34', '80332421783', NULL, 'vasya', 'vasya'),
	(16, 'CLIENT', 'Lana', 'Sibir', 'Snegirova, 77-31', '80335261183', NULL, 'lana', 'lana'),
	(17, 'CLIENT', 'Denis', 'Kovalev', 'Pr. Nezavisimosti, 22-675', '80295369202', NULL, 'denis', 'denis');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
