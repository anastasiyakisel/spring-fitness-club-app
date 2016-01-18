-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.11 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-08-07 12:43:36
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
-- Dumping data for table fitness-club.discount: ~6 rows (approximately)
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
REPLACE INTO `discount` (`discount_id`, `number_of_abonements`, `discount_percent`) VALUES
	(1, 2, 5),
	(2, 3, 7),
	(3, 4, 9),
	(4, 5, 10),
	(5, 6, 15),
	(6, 7, 20);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;

-- Dumping data for table fitness-club.groups: ~18 rows (approximately)
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
REPLACE INTO `groups` (`group_id`, `sporttype_id`, `capacity`, `day_of_week`, `start_time`, `duration`, `cost`, `people_registered`) VALUES
	(1, 5, 15, 'WE,SA', '08:00:00', 1, 25, 3),
	(2, 4, 25, 'WE,SA', '13:00:00', 1, 20, 4),
	(3, 1, 15, 'MO,WE,SA', '09:00:00', 1, 30, 5),
	(4, 3, 30, 'TU,TH,SU', '15:00:00', 1, 30, 4),
	(5, 6, 20, 'TU,FR', '14:00:00', 1, 50, 3),
	(6, 2, 30, 'MO,FR', '12:00:00', 1, 20, 1),
	(7, 7, 15, 'WE,TH,FR', '09:00:00', 1, 30, 8),
	(8, 8, 20, 'TU,FR', '19:00:00', 1, 30, 2),
	(9, 4, 15, 'WE,SA', '15:00:00', 2, 40, 15),
	(10, 8, 20, 'TH,SU', '21:00:00', 1, 25, 4),
	(11, 1, 15, 'WE,SA', '14:00:00', 1, 20, 4),
	(12, 7, 40, 'MO,FR', '12:00:00', 1, 25, 4),
	(13, 5, 30, 'TU,TH,SA', '13:00:00', 1, 60, 4),
	(14, 3, 25, 'WE,SA', '20:00:00', 1, 20, 6),
	(15, 4, 20, 'MO,FR', '17:00:00', 2, 30, 6),
	(16, 4, 15, 'TU,SA', '12:00:00', 1, 25, 9),
	(17, 6, 20, 'FR,SU', '11:00:00', 1, 30, 6),
	(18, 8, 30, 'TU,TH', '18:00:00', 1, 25, 3);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;

-- Dumping data for table fitness-club.person: ~13 rows (approximately)
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

-- Dumping data for table fitness-club.registration: ~23 rows (approximately)
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
REPLACE INTO `registration` (`registration_id`, `person_id`, `group_id`) VALUES
	(65, 2, 2),
	(78, 4, 10),
	(79, 4, 1),
	(80, 4, 12),
	(81, 4, 9),
	(82, 4, 11),
	(89, 2, 4),
	(91, 3, 13),
	(93, 3, 14),
	(94, 3, 7),
	(106, 2, 7),
	(111, 1, 3),
	(113, 1, 6),
	(115, 1, 14),
	(117, 1, 9),
	(118, 1, 15),
	(123, 1, 10),
	(124, 1, 18),
	(132, 1, 17),
	(133, 2, 5),
	(135, 3, 17),
	(136, 3, 17),
	(137, 3, 3),
	(138, 5, 9),
	(139, 5, 9),
	(140, 5, 7),
	(141, 5, 7),
	(142, 5, 7),
	(143, 5, 7),
	(144, 5, 8),
	(145, 6, 5),
	(146, 6, 17),
	(147, 6, 4),
	(148, 6, 14),
	(149, 6, 9),
	(150, 6, 15),
	(151, 6, 16),
	(152, 7, 3),
	(153, 7, 11),
	(154, 7, 9),
	(155, 7, 16),
	(156, 8, 9),
	(157, 8, 16),
	(158, 8, 8),
	(159, 8, 10),
	(160, 8, 18),
	(161, 9, 9),
	(162, 9, 16),
	(163, 9, 3),
	(164, 9, 11),
	(165, 10, 2),
	(166, 10, 9),
	(167, 10, 5),
	(168, 10, 17),
	(169, 10, 1),
	(170, 10, 13),
	(171, 11, 14),
	(172, 11, 7),
	(173, 11, 12),
	(174, 11, 9),
	(175, 11, 16),
	(176, 12, 9),
	(177, 12, 16),
	(178, 12, 7),
	(179, 12, 12),
	(180, 13, 4),
	(181, 13, 9),
	(182, 13, 16),
	(183, 13, 1),
	(184, 13, 13),
	(185, 14, 13),
	(186, 14, 9),
	(187, 14, 15),
	(188, 14, 16),
	(189, 14, 14),
	(190, 15, 12),
	(191, 15, 9),
	(192, 15, 15),
	(193, 15, 16),
	(194, 17, 2),
	(195, 17, 9),
	(196, 17, 15),
	(197, 17, 17),
	(198, 17, 3),
	(199, 17, 11),
	(200, 17, 10),
	(201, 17, 18),
	(202, 16, 4),
	(203, 16, 14),
	(204, 16, 2),
	(205, 16, 15);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;

-- Dumping data for table fitness-club.sporttype: ~8 rows (approximately)
/*!40000 ALTER TABLE `sporttype` DISABLE KEYS */;
REPLACE INTO `sporttype` (`sporttype_id`, `sport_type`, `calories_burned`) VALUES
	(1, 'EXERCISE_ROOM', 276),
	(2, 'AEROBICS', 390),
	(3, 'PILATES', 420),
	(4, 'YOGA', 438),
	(5, 'FITBALL', 480),
	(6, 'BELLY_DANCE', 270),
	(7, 'DANCE_MIX', 540),
	(8, 'STRATCHING', 132);
/*!40000 ALTER TABLE `sporttype` ENABLE KEYS */;

-- Dumping data for table fitness-club.statement: ~4 rows (approximately)
/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
REPLACE INTO `statement` (`statement_id`, `person_id`, `number_of_abonements`, `discount_percent`, `general_cost`) VALUES
	(12, 1, 8, 20, 176),
	(16, 4, 5, 10, 122),
	(17, 2, 6, 15, 162),
	(26, 3, 6, 15, 170),
	(27, 5, 7, 20, 184),
	(28, 6, 7, 20, 180),
	(29, 7, 4, 9, 105),
	(30, 8, 5, 10, 131),
	(31, 9, 4, 9, 105),
	(32, 10, 6, 15, 191),
	(33, 11, 5, 10, 126),
	(34, 12, 4, 9, 109),
	(35, 13, 5, 10, 162),
	(36, 14, 5, 10, 158),
	(37, 15, 4, 9, 109),
	(38, 17, 8, 20, 176),
	(39, 16, 4, 9, 91);
/*!40000 ALTER TABLE `statement` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
