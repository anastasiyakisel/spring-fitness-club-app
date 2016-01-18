-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.11 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-07-25 19:29:53
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
-- Dumping data for table fitness-club.discount: ~6 rows (approximately)
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT IGNORE INTO `discount` (`discount_id`, `number_of_abonements`, `discount_percent`) VALUES
	(1, 2, 5),
	(2, 3, 7),
	(3, 4, 9),
	(4, 5, 10),
	(5, 6, 15),
	(6, 7, 20);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;

-- Dumping data for table fitness-club.groups: ~18 rows (approximately)
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT IGNORE INTO `groups` (`group_id`, `sporttype_id`, `capacity`, `day_of_week`, `start_time`, `duration`, `cost`) VALUES
	(1, 5, 15, 'WE,SA', '08:00:00', 1, 25),
	(2, 4, 25, 'WE,SA', '13:00:00', 1, 20),
	(3, 1, 15, 'MO,WE,SA', '09:00:00', 1, 30),
	(4, 3, 30, 'TU,TH,SU', '15:00:00', 1, 30),
	(5, 6, 20, 'TU,FR', '14:00:00', 1, 50),
	(6, 2, 30, 'MO,FR', '12:00:00', 1, 20),
	(7, 7, 15, 'WE,TH,FR', '09:00:00', 1, 30),
	(8, 8, 20, 'TU,FR', '19:00:00', 1, 30),
	(9, 4, 15, 'WE,SA', '15:00:00', 2, 40),
	(10, 8, 20, 'TH,SU', '21:00:00', 1, 25),
	(11, 1, 15, 'WE,SA', '14:00:00', 1, 20),
	(12, 7, 40, 'MO,FR', '12:00:00', 1, 25),
	(13, 5, 30, 'TU,TH,SA', '13:00:00', 1, 60),
	(14, 3, 25, 'WE,SA', '20:00:00', 1, 20),
	(15, 4, 20, 'MO,FR', '17:00:00', 2, 30),
	(16, 4, 15, 'TU,SA', '12:00:00', 1, 25),
	(17, 6, 20, 'FR,SU', '11:00:00', 1, 30),
	(18, 8, 30, 'TU,TH', '18:00:00', 1, 25);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;

-- Dumping data for table fitness-club.person: ~3 rows (approximately)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT IGNORE INTO `person` (`person_id`, `post`, `firstname`, `lastname`, `address`, `telephone`, `description`, `login`, `password`) VALUES
	(1, 'ADMIN', 'Anastasia', 'Kisel', 'Nemanskaya Street, 15-57', '80291742419', 'wear glasses', 'nastya', 'nastya'),
	(2, 'ADMIN', 'Artem', 'Baturin', 'Revolution Street, 24-75', '00331452783', 'good at football', 'artem', 'artem'),
	(3, 'CLIENT', 'Lena', 'Orlova', 'Kharyjina, 15-33', '80256743619', 'fat', 'lena', 'lena');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

-- Dumping data for table fitness-club.registration: ~0 rows (approximately)
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;

-- Dumping data for table fitness-club.sporttype: ~8 rows (approximately)
/*!40000 ALTER TABLE `sporttype` DISABLE KEYS */;
INSERT IGNORE INTO `sporttype` (`sporttype_id`, `sport_type`, `calories_burned`) VALUES
	(1, 'EXERCISE_ROOM', 276),
	(2, 'AEROBICS', 390),
	(3, 'PILATES', 420),
	(4, 'YOGA', 438),
	(5, 'FITBALL', 480),
	(6, 'BELLY_DANCE', 270),
	(7, 'DANCE_MIX', 540),
	(8, 'STRATCHING', 132);
/*!40000 ALTER TABLE `sporttype` ENABLE KEYS */;

-- Dumping data for table fitness-club.statement: ~0 rows (approximately)
/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
/*!40000 ALTER TABLE `statement` ENABLE KEYS */;

-- Dumping data for table fitness-club.timetable: ~19 rows (approximately)
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
INSERT IGNORE INTO `timetable` (`timetable_id`, `group_id`, `day_of_week`) VALUES
	(1, 4, 'TU'),
	(2, 4, 'TH'),
	(3, 4, 'SU'),
	(4, 3, 'MO'),
	(5, 3, 'WE'),
	(6, 3, 'SA'),
	(7, 1, 'WE'),
	(8, 1, 'SA'),
	(9, 2, 'WE'),
	(10, 2, 'SA'),
	(11, 7, 'WE'),
	(12, 7, 'TH'),
	(13, 7, 'FR'),
	(14, 5, 'TU'),
	(15, 5, 'FR'),
	(16, 8, 'TU'),
	(17, 8, 'FR'),
	(18, 6, 'MO'),
	(19, 6, 'FR');
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
