-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: fitness-club
-- ------------------------------------------------------
-- Server version	5.7.10

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
-- Table structure for table `DISCOUNT`
--

DROP TABLE IF EXISTS `DISCOUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DISCOUNT` (
  `discount_id` int(11) NOT NULL AUTO_INCREMENT,
  `number_of_abonements` int(11) DEFAULT NULL,
  `discount_percent` int(11) DEFAULT NULL,
  PRIMARY KEY (`discount_id`),
  UNIQUE KEY `discount_id_UNIQUE` (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DISCOUNT`
--

LOCK TABLES `DISCOUNT` WRITE;
/*!40000 ALTER TABLE `DISCOUNT` DISABLE KEYS */;
INSERT INTO `DISCOUNT` VALUES (1,2,5),(2,3,7),(3,4,9),(4,5,10),(5,6,15),(6,7,20);
/*!40000 ALTER TABLE `DISCOUNT` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `GROUPS` VALUES (1,5,15,'WE,SA','08:00:00',1,25,2),(2,4,25,'WE,SA','13:00:00',1,20,4),(3,1,15,'MO,WE,SA','09:00:00',1,30,6),(4,3,30,'TU,TH,SU','15:00:00',1,30,3),(5,6,20,'TU,FR','14:00:00',1,50,3),(6,2,30,'MO,FR','12:00:00',1,20,0),(7,7,15,'WE,TH,FR','09:00:00',1,30,8),(8,8,20,'TU,FR','19:00:00',1,30,2),(9,4,15,'WE,SA','15:00:00',2,40,12),(10,8,20,'TH,SU','21:00:00',1,25,3),(11,1,15,'WE,SA','14:00:00',1,20,6),(12,7,40,'MO,FR','12:00:00',1,25,4),(13,5,30,'TU,TH,SA','13:00:00',1,60,4),(14,3,25,'WE,SA','20:00:00',1,20,5),(15,4,20,'MO,FR','17:00:00',2,30,5),(16,4,15,'TU,SA','12:00:00',1,25,9),(17,6,20,'FR,SU','11:00:00',1,30,5),(18,8,30,'TU,TH','18:00:00',1,25,2);
/*!40000 ALTER TABLE `GROUPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REGISTRATION`
--

DROP TABLE IF EXISTS `REGISTRATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REGISTRATION` (
  `registration_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`registration_id`),
  UNIQUE KEY `registration_id_UNIQUE` (`registration_id`),
  KEY `personFK_idx` (`user_id`),
  KEY `groupFK_idx` (`group_id`),
  CONSTRAINT `groupFK` FOREIGN KEY (`group_id`) REFERENCES `GROUPS` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `personFK` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REGISTRATION`
--

LOCK TABLES `REGISTRATION` WRITE;
/*!40000 ALTER TABLE `REGISTRATION` DISABLE KEYS */;
INSERT INTO `REGISTRATION` VALUES (65,2,2),(78,4,10),(80,4,12),(81,4,9),(89,2,4),(91,3,13),(93,3,14),(94,3,7),(106,2,7),(133,2,5),(135,3,17),(136,3,17),(137,3,3),(140,5,7),(141,5,7),(142,5,7),(143,5,7),(144,5,8),(145,6,5),(146,6,17),(147,6,4),(148,6,14),(149,6,9),(150,6,15),(151,6,16),(152,7,3),(153,7,11),(154,7,9),(155,7,16),(156,8,9),(157,8,16),(158,8,8),(159,8,10),(160,8,18),(161,9,9),(162,9,16),(163,9,3),(164,9,11),(165,10,2),(166,10,9),(167,10,5),(168,10,17),(169,10,1),(170,10,13),(171,11,14),(172,11,7),(173,11,12),(174,11,9),(175,11,16),(176,12,9),(177,12,16),(178,12,7),(179,12,12),(180,13,4),(181,13,9),(182,13,16),(183,13,1),(184,13,13),(185,14,13),(186,14,9),(187,14,15),(188,14,16),(189,14,14),(190,15,12),(191,15,9),(192,15,15),(193,15,16),(194,17,2),(195,17,9),(196,17,15),(197,17,17),(198,17,3),(199,17,11),(200,17,10),(201,17,18),(203,16,14),(204,16,2),(205,16,15),(207,1,11),(209,1,3),(210,1,11),(211,1,3),(212,1,11);
/*!40000 ALTER TABLE `REGISTRATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPORTTYPE`
--

DROP TABLE IF EXISTS `SPORTTYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SPORTTYPE` (
  `sporttype_id` int(11) NOT NULL AUTO_INCREMENT,
  `sport_type` enum('EXERCISE_ROOM','AEROBICS','PILATES','YOGA','FITBALL','BELLY_DANCE','STRATCHING','DANCE_MIX') NOT NULL,
  `calories_burned` int(11) NOT NULL,
  PRIMARY KEY (`sporttype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPORTTYPE`
--

LOCK TABLES `SPORTTYPE` WRITE;
/*!40000 ALTER TABLE `SPORTTYPE` DISABLE KEYS */;
INSERT INTO `SPORTTYPE` VALUES (1,'EXERCISE_ROOM',276),(2,'AEROBICS',390),(3,'PILATES',420),(4,'YOGA',438),(5,'FITBALL',480),(6,'BELLY_DANCE',270),(7,'DANCE_MIX',540),(8,'STRATCHING',132);
/*!40000 ALTER TABLE `SPORTTYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATEMENT`
--

DROP TABLE IF EXISTS `STATEMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATEMENT` (
  `statement_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `number_of_abonements` int(11) NOT NULL,
  `discount_percent` int(11) NOT NULL,
  `general_cost` double DEFAULT NULL,
  PRIMARY KEY (`statement_id`),
  UNIQUE KEY `statement_id_UNIQUE` (`statement_id`),
  KEY `personFK_idx` (`user_id`),
  KEY `person_FK_idx` (`user_id`),
  CONSTRAINT `person_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATEMENT`
--

LOCK TABLES `STATEMENT` WRITE;
/*!40000 ALTER TABLE `STATEMENT` DISABLE KEYS */;
INSERT INTO `STATEMENT` VALUES (12,1,5,10,108),(16,4,3,7,83.7),(17,2,4,9,118.3),(26,3,6,15,170),(27,5,7,20,184),(28,6,7,20,180),(29,7,4,9,104.65),(30,8,5,10,130.5),(31,9,4,9,104.65),(32,10,6,15,191.25),(33,11,5,10,126),(34,12,4,9,109.2),(35,13,5,10,162),(36,14,5,10,157.5),(37,15,4,9,109.2),(38,17,8,20,176),(39,16,3,7,65.1),(40,18,0,0,0);
/*!40000 ALTER TABLE `STATEMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `role` enum('ROLE_ADMIN','ROLE_USER') NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idPERSON_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,'Anastasia','Kisel','57  Nemanskaya Street','(029) 174-2419','wear glasses','nastya','nastya',1,'ROLE_ADMIN'),(2,'Artem','Baturin','24 Revolution Street','(033) 145-2783','good at football','artem','artem',1,'ROLE_ADMIN'),(3,'Lena','Orlova','33 Kharyjina','(025) 674-3619','fat','lena','lena',1,'ROLE_USER'),(4,'Anna','Kisel','15 Gorodzetskaya ','(033) 546-3831','14 years','anya','anya',1,'ROLE_USER'),(5,'Svetlana','Kisel','17 Olshevskogo','(025) 643-8291','mother of two children','sveta','sveta',1,'ROLE_USER'),(6,'Dima','Luchkovsky','12 Lojonskaya','(033) 124-2793',NULL,'dima','dima',1,'ROLE_USER'),(7,'Pasha','Sheglinsky','14 Smolenskaya','(029) 643-5291',NULL,'pasha','pasha',1,'ROLE_USER'),(8,'Olga','Ivanova','15 Vera Khoryjaya','(033) 524-3111',NULL,'olga','olga',1,'ROLE_USER'),(9,'Sasha','Shamgin','22 Kosmonavtov','(029) 756-5323',NULL,'sasha','sasha',1,'ROLE_USER'),(10,'Vera','Yakovenko','12 Kalinovskogo','(044) 213-9786',NULL,'vera','vera',1,'ROLE_USER'),(11,'Lera','Rudenko','3 Slavinskogo','(033) 118-7654',NULL,'lera','lera',1,'ROLE_USER'),(12,'Kolya','Saminsky','22 Sedih','(025) 413-2532',NULL,'kolya','kolya',1,'ROLE_USER'),(13,'Yaroslav','Zadvorni','7 Teplichnaya ','(033) 654-9373',NULL,'slava','slava',1,'ROLE_USER'),(14,'Diana','Martines-Sokolovskaya','220 Ostroshisckogo','(025) 437-8321','student','dianka','dianka',1,'ROLE_USER'),(15,'Vasilisa','Kulik','22 Ostroshicskogo','(033) 242-1783',NULL,'vasya','vasya',1,'ROLE_USER'),(16,'Lana','Sibir','17 Snegirova','(033) 526-1183',NULL,'lana','lana',1,'ROLE_USER'),(17,'Denis','Kovalev','22 Pr. Nezavisimosti','(029) 536-9202',NULL,'denis','denis',1,'ROLE_USER'),(18,'test','test','3 Kuprevicha','(017) 202-0327','','test','test',1,'ROLE_USER'),(19,'anakis','anakis','1 Kuprevicha','(017) 202-0328','anakis','anakis','anakis',0,'ROLE_USER'),(20,'aaa','aaa','21 Kiseleva','(017) 202-0329','aaa','aaa','aaa',1,'ROLE_USER');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-31 16:35:35
