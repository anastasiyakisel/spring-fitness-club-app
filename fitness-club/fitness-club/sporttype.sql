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
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
