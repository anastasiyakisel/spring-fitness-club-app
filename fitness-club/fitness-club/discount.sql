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
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
