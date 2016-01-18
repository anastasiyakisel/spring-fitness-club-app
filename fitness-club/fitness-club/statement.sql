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
-- Dumping data for table fitness-club.statement: ~17 rows (approximately)
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
