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
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
