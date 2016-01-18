-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.11 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-07-25 19:31:26
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
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
