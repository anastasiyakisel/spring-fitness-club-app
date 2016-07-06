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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,'Anastasia','Kisel','57  Nemanskaya Street','(029) 174-2419','wear glasses','nastya','nastya',1,'ROLE_ADMIN'),(2,'Artem','Baturin','24 Revolution Street','(033) 145-2783','good at football','artem','artem',1,'ROLE_ADMIN'),(3,'Lena','Orlova','33 Kharyjina','(025) 674-3619','fat','lena','lena',1,'ROLE_USER'),(4,'Anna','Kisel','15 Gorodzetskaya ','(033) 546-3831','14 years','anya','anya',1,'ROLE_USER'),(5,'Svetlana','Kisel','17 Olshevskogo','(025) 643-8291','mother of two children','sveta','sveta',1,'ROLE_USER'),(6,'Dima','Luchkovsky','12 Lojonskaya','(033) 124-2793',NULL,'dima','dima',1,'ROLE_USER'),(7,'Pasha','Sheglinsky','14 Smolenskaya','(029) 643-5291',NULL,'pasha','pasha',1,'ROLE_USER'),(8,'Olga','Ivanova','15 Vera Khoryjaya','(033) 524-3111',NULL,'olga','olga',1,'ROLE_USER'),(9,'Sasha','Shamgin','22 Kosmonavtov','(029) 756-5323',NULL,'sasha','sasha',1,'ROLE_USER'),(10,'Vera','Yakovenko','12 Kalinovskogo','(044) 213-9786',NULL,'vera','vera',1,'ROLE_USER'),(11,'Lera','Rudenko','3 Slavinskogo','(033) 118-7654',NULL,'lera','lera',1,'ROLE_USER'),(12,'Kolya','Saminsky','22 Sedih','(025) 413-2532',NULL,'kolya','kolya',1,'ROLE_USER'),(13,'Yaroslav','Zadvorni','7 Teplichnaya ','(033) 654-9373',NULL,'slava','slava',1,'ROLE_USER'),(14,'Diana','Martines-Sokolovskaya','220 Ostroshisckogo','(025) 437-8321','student','dianka','dianka',1,'ROLE_USER'),(15,'Vasilisa','Kulik','22 Ostroshicskogo','(033) 242-1783',NULL,'vasya','vasya',1,'ROLE_USER'),(16,'Lana','Sibir','17 Snegirova','(033) 526-1183',NULL,'lana','lana',1,'ROLE_USER'),(17,'Denis','Kovalev','22 Pr. Nezavisimosti','(029) 536-9202',NULL,'denis','denis',1,'ROLE_USER'),(18,'test','test','3 Kuprevicha','(017) 202-0327','','test','test',1,'ROLE_USER'),(19,'anakis','anakis','1 Kuprevicha','(017) 202-0328','anakis','anakis','anakis',0,'ROLE_USER'),(20,'aaa','aaa','21 Kiseleva','(017) 202-0329','aaa','aaa','aaa',1,'ROLE_USER'),(21,'Alladin','Alladin','223 Gorodzetskaya','(033) 123-4567','','alladin','alladin',1,'ROLE_USER'),(22,'jasmin','jasmin','22 alala','(033) 123-5678','','jasmin','jasmin',1,'ROLE_USER'),(23,'Alolo','Alolo','15 Alolo','(024) 789-8790','Alolo','alolo','alolo',1,'ROLE_USER'),(24,'alolo1','alolo1','12 WebContent','(020) 567-8989','alolo1','alolo1','alolo1',1,'ROLE_USER'),(25,'alolo2','alolo2','2 alolo','(111) 234-7865','alolo2','alolo2','alolo2',1,'ROLE_USER'),(26,'alolo3','alolo3','3 alolo','(111) 342-1212','alolo3','alolo3','alolo3',1,'ROLE_USER'),(27,'alolo4','alolo4','3 dfdf','(123) 567-2123','alolo4','alolo4','alolo4',1,'ROLE_USER'),(28,'alolo5','alolo5','12 skydrive','(123) 567-4545','alolo5','alolo5','alolo5',1,'ROLE_USER'),(29,'alolo6','alolo6','6 alo','(123) 456-3546','alolo6','alolo6','alolo6',1,'ROLE_USER'),(30,'alolo7','alolo7','7 alolo','(123) 789-3647','alolo7','alolo7','alolo7',1,'ROLE_USER'),(31,'alolo8','alolo8','8 alolo','(123) 786-4673','v','alolo8','alolo8',1,'ROLE_USER'),(32,'alolo9','alolo9','9 alolo','(123) 789-4738','alolo9','alolo9','alolo9',1,'ROLE_USER'),(33,'alolo11','alolo11','11 alolo','(123) 789-4738','alolo11','alolo11','alolo11',1,'ROLE_USER'),(34,'alolo12','alolo12','123 alolo','(123) 876-5345','alolo12','alolo12','alolo12',1,'ROLE_USER'),(35,'alolo13','alolo13','13 alolo','(124) 576-8934','alolo13','alolo13','alolo13',1,'ROLE_USER'),(36,'alala1','alala1','1 alala','(123) 987-5768','alala1','alala1','alala1',1,'ROLE_USER'),(37,'Dasha','Dasha','23 Navgarodskiy','(029) 456-5786','5.5 years','dasha','dasha',1,'ROLE_USER');
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

-- Dump completed on 2016-07-06 15:52:32
