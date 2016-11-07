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
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `discount_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `number_of_abonements` int(11) NOT NULL,
  `discount_percent` int(11) NOT NULL,
  PRIMARY KEY (`discount_id`),
  UNIQUE KEY `discount_id_UNIQUE` (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,2,5),(2,3,7),(3,4,9),(4,5,10),(5,6,15),(6,7,20);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `group_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `sporttype_id` bigint(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `day_of_week` varchar(45) NOT NULL,
  `start_time` time NOT NULL,
  `duration` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `people_registered` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_id_UNIQUE` (`group_id`),
  KEY `sporttypeFK_idx` (`sporttype_id`),
  CONSTRAINT `sporttypeFK` FOREIGN KEY (`sporttype_id`) REFERENCES `sporttype` (`sporttype_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,5,15,'WE,SA','08:00:00',1,25,1),(2,4,25,'WE,SA','13:00:00',1,20,4),(3,1,15,'MO,WE,SA','09:00:00',1,30,2),(4,3,30,'TU,TH,SU','15:00:00',1,30,7),(5,6,20,'TU,FR','14:00:00',1,50,3),(6,2,30,'MO,FR','12:00:00',1,20,1),(7,7,15,'WE,TH,FR','09:00:00',1,30,8),(8,8,20,'TU,FR','19:00:00',1,30,2),(9,4,15,'WE,SA','15:00:00',2,40,14),(10,8,20,'TH,SU','21:00:00',1,25,3),(11,1,15,'WE,SA','14:00:00',1,20,4),(12,7,40,'MO,FR','12:00:00',1,25,4),(13,5,30,'TU,TH,SA','13:00:00',1,60,5),(14,3,25,'WE,SA','20:00:00',1,20,9),(15,4,20,'MO,FR','17:00:00',2,30,5),(16,4,15,'TU,SA','12:00:00',1,25,10),(17,6,20,'FR,SU','11:00:00',1,30,5),(18,8,30,'TU,TH','18:00:00',1,25,2);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `registration_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `group_id` bigint(11) NOT NULL,
  PRIMARY KEY (`registration_id`),
  UNIQUE KEY `registration_id_UNIQUE` (`registration_id`),
  KEY `personFK_idx` (`user_id`),
  KEY `groupFK_idx` (`group_id`),
  CONSTRAINT `groups_FK` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (65,2,2),(78,4,10),(80,4,12),(81,4,9),(89,2,4),(91,3,13),(93,3,14),(94,3,7),(106,2,7),(133,2,5),(135,3,17),(136,3,17),(137,3,3),(140,5,7),(141,5,7),(142,5,7),(143,5,7),(144,5,8),(145,6,5),(146,6,17),(147,6,4),(148,6,14),(149,6,9),(150,6,15),(151,6,16),(153,7,11),(154,7,9),(155,7,16),(156,8,9),(157,8,16),(158,8,8),(159,8,10),(160,8,18),(161,9,9),(162,9,16),(163,9,3),(164,9,11),(165,10,2),(166,10,9),(167,10,5),(168,10,17),(170,10,13),(171,11,14),(172,11,7),(173,11,12),(174,11,9),(175,11,16),(176,12,9),(177,12,16),(178,12,7),(179,12,12),(180,13,4),(181,13,9),(182,13,16),(184,13,13),(185,14,13),(186,14,9),(187,14,15),(188,14,16),(189,14,14),(190,15,12),(191,15,9),(192,15,15),(193,15,16),(194,17,2),(195,17,9),(196,17,15),(197,17,17),(199,17,11),(200,17,10),(201,17,18),(203,16,14),(204,16,2),(205,16,15),(230,4,6),(231,4,4),(232,4,14),(233,1,9),(238,1,11),(240,1,9),(242,1,16),(243,1,4),(244,1,14),(245,4,4),(246,4,14),(247,2,4),(248,2,14),(249,5,1),(250,5,13);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sporttype`
--

DROP TABLE IF EXISTS `sporttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sporttype` (
  `sporttype_id` bigint(12) NOT NULL AUTO_INCREMENT,
  `sport_type` enum('EXERCISE_ROOM','AEROBICS','PILATES','YOGA','FITBALL','BELLY_DANCE','STRATCHING','DANCE_MIX') NOT NULL,
  `calories_burned` int(11) NOT NULL,
  PRIMARY KEY (`sporttype_id`),
  UNIQUE KEY `sporttype_id_UNIQUE` (`sporttype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sporttype`
--

LOCK TABLES `sporttype` WRITE;
/*!40000 ALTER TABLE `sporttype` DISABLE KEYS */;
INSERT INTO `sporttype` VALUES (1,'EXERCISE_ROOM',276),(2,'AEROBICS',390),(3,'PILATES',420),(4,'YOGA',438),(5,'FITBALL',480),(6,'BELLY_DANCE',270),(7,'DANCE_MIX',540),(8,'STRATCHING',132);
/*!40000 ALTER TABLE `sporttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statement`
--

DROP TABLE IF EXISTS `statement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statement` (
  `statement_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `number_of_abonements` int(11) NOT NULL,
  `discount_percent` int(11) NOT NULL,
  `general_cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`statement_id`),
  KEY `personFK_idx` (`user_id`),
  KEY `person_FK_idx` (`user_id`),
  CONSTRAINT `userFK` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statement`
--

LOCK TABLES `statement` WRITE;
/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
INSERT INTO `statement` VALUES (12,1,6,15,175),(16,4,8,20,210),(17,2,6,15,180),(26,3,6,15,200),(27,5,7,20,235),(28,6,7,20,225),(29,7,4,9,115),(30,8,5,10,130),(31,9,4,9,115),(32,10,5,10,200),(33,11,5,10,126),(34,12,4,9,109),(35,13,4,9,155),(36,14,5,10,175),(37,15,4,9,120),(38,17,7,20,190),(39,16,3,7,70),(40,18,0,0,0);
/*!40000 ALTER TABLE `statement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` bigint(12) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `role` enum('ROLE_ADMIN','ROLE_USER') NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Anastasia','Kisel','57  Nemanskaya Street','(029) 174-2419','wear glasses','nastya','nastya',1,'ROLE_ADMIN'),(2,'Artem','Baturin','24 Revolution Street','(033) 145-2783','good at football','artem','artem',1,'ROLE_ADMIN'),(3,'Lena','Orlova','33 Kharyjina','(025) 674-3619','fat','lena','lena',1,'ROLE_USER'),(4,'Anna','Kisel','15 Gorodzetskaya ','(033) 546-3831','14 years','anya','anya',1,'ROLE_USER'),(5,'Svetlana','Kisel','17 Olshevskogo','(025) 643-8291','mother of two children','sveta','sveta',1,'ROLE_USER'),(6,'Dima','Luchkovsky','12 Lojonskaya','(033) 124-2793',NULL,'dima','dima',1,'ROLE_USER'),(7,'Pasha','Sheglinsky','14 Smolenskaya','(029) 643-5291',NULL,'pasha','pasha',1,'ROLE_USER'),(8,'Olga','Ivanova','15 Vera Khoryjaya','(033) 524-3111',NULL,'olga','olga',1,'ROLE_USER'),(9,'Sasha','Shamgin','22 Kosmonavtov','(029) 756-5323',NULL,'sasha','sasha',1,'ROLE_USER'),(10,'Vera','Yakovenko','12 Kalinovskogo','(044) 213-9786',NULL,'vera','vera',1,'ROLE_USER'),(11,'Lera','Rudenko','3 Slavinskogo','(033) 118-7654',NULL,'lera','lera',1,'ROLE_USER'),(12,'Kolya','Saminsky','22 Sedih','(025) 413-2532',NULL,'kolya','kolya',1,'ROLE_USER'),(13,'Yaroslav','Zadvorni','7 Teplichnaya ','(033) 654-9373',NULL,'slava','slava',1,'ROLE_USER'),(14,'Diana','Martines-Sokolovskaya','220 Ostroshisckogo','(025) 437-8321','student','dianka','dianka',1,'ROLE_USER'),(15,'Vasilisa','Kulik','22 Ostroshicskogo','(033) 242-1783',NULL,'vasya','vasya',1,'ROLE_USER'),(16,'Lana','Sibir','17 Snegirova','(033) 526-1183',NULL,'lana','lana',1,'ROLE_USER'),(17,'Denis','Kovalev','22 Pr. Nezavisimosti','(029) 536-9202',NULL,'denis','denis',1,'ROLE_USER'),(18,'test','test','3 Kuprevicha','(017) 202-0327','','test','test',1,'ROLE_USER'),(19,'anakis','anakis','1 Kuprevicha','(017) 202-0328','anakis','anakis','anakis',0,'ROLE_USER'),(20,'aaa','aaa','21 Kiseleva','(017) 202-0329','aaa','aaa','aaa',1,'ROLE_USER'),(21,'Alladin','Alladin','223 Gorodzetskaya','(033) 123-4567','','alladin','alladin',1,'ROLE_USER'),(22,'jasmin','jasmin','22 alala','(033) 123-5678','','jasmin','jasmin',1,'ROLE_USER'),(23,'Alolo','Alolo','15 Alolo','(024) 789-8790','Alolo','alolo','alolo',1,'ROLE_USER'),(24,'alolo1','alolo1','12 WebContent','(020) 567-8989','alolo1','alolo1','alolo1',1,'ROLE_USER'),(25,'alolo2','alolo2','2 alolo','(111) 234-7865','alolo2','alolo2','alolo2',1,'ROLE_USER'),(26,'alolo3','alolo3','3 alolo','(111) 342-1212','alolo3','alolo3','alolo3',1,'ROLE_USER'),(27,'alolo4','alolo4','3 dfdf','(123) 567-2123','alolo4','alolo4','alolo4',1,'ROLE_USER'),(28,'alolo5','alolo5','12 skydrive','(123) 567-4545','alolo5','alolo5','alolo5',1,'ROLE_USER'),(29,'alolo6','alolo6','6 alo','(123) 456-3546','alolo6','alolo6','alolo6',1,'ROLE_USER'),(30,'alolo7','alolo7','7 alolo','(123) 789-3647','alolo7','alolo7','alolo7',1,'ROLE_USER'),(31,'alolo8','alolo8','8 alolo','(123) 786-4673','v','alolo8','alolo8',1,'ROLE_USER'),(32,'alolo9','alolo9','9 alolo','(123) 789-4738','alolo9','alolo9','alolo9',1,'ROLE_USER'),(33,'alolo11','alolo11','11 alolo','(123) 789-4738','alolo11','alolo11','alolo11',1,'ROLE_USER'),(34,'alolo12','alolo12','123 alolo','(123) 876-5345','alolo12','alolo12','alolo12',1,'ROLE_USER'),(35,'alolo13','alolo13','13 alolo','(124) 576-8934','alolo13','alolo13','alolo13',1,'ROLE_USER'),(36,'alala1','alala1','1 alala','(123) 987-5768','alala1','alala1','alala1',1,'ROLE_USER'),(37,'Dasha','Dasha','23 Navgarodskiy','(029) 456-5786','5.5 years','dasha','dasha',1,'ROLE_USER'),(38,'Alena','Visoka','223 MonetniyDvor','(029) 845-3849','','alena','alena',1,'ROLE_USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-07 22:04:19
