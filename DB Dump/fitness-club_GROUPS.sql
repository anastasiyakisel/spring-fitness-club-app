-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: localhost    Database: fitness-club
-- ------------------------------------------------------
-- Server version	5.6.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `GROUPS`
--

DROP TABLE IF EXISTS `GROUPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GROUPS` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `sporttype_id` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `day_of_week` varchar(45) NOT NULL,
  `start_time` time NOT NULL,
  `duration` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `people_registered` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_id_UNIQUE` (`group_id`),
  KEY `sporttypeFK_idx` (`sporttype_id`),
  CONSTRAINT `sporttype_FK` FOREIGN KEY (`sporttype_id`) REFERENCES `SPORTTYPE` (`sporttype_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUPS`
--

LOCK TABLES `GROUPS` WRITE;
/*!40000 ALTER TABLE `GROUPS` DISABLE KEYS */;
INSERT INTO `GROUPS` VALUES (1,5,15,'WE,SA','08:00:00',1,25,3),(2,4,25,'WE,SA','13:00:00',1,20,5),(3,1,15,'MO,WE,SA','09:00:00',1,30,6),(4,3,30,'TU,TH,SU','15:00:00',1,30,5),(5,6,20,'TU,FR','14:00:00',1,50,5),(6,2,30,'MO,FR','12:00:00',1,20,0),(7,7,15,'WE,TH,FR','09:00:00',1,30,8),(8,8,20,'TU,FR','19:00:00',1,30,2),(9,4,15,'WE,SA','15:00:00',2,40,14),(10,8,20,'TH,SU','21:00:00',1,25,4),(11,1,15,'WE,SA','14:00:00',1,20,4),(12,7,40,'MO,FR','12:00:00',1,25,4),(13,5,30,'TU,TH,SA','13:00:00',1,60,4),(14,3,25,'WE,SA','20:00:00',1,20,5),(15,4,20,'MO,FR','17:00:00',2,30,5),(16,4,15,'TU,SA','12:00:00',1,25,8),(17,6,20,'FR,SU','11:00:00',1,30,6),(18,8,30,'TU,TH','18:00:00',1,25,3);
/*!40000 ALTER TABLE `GROUPS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-06  1:15:27