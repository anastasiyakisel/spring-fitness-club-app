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
-- Table structure for table `PERSON`
--

DROP TABLE IF EXISTS `PERSON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSON` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `post` enum('Admin','Client') DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `idPERSON_UNIQUE` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON`
--

LOCK TABLES `PERSON` WRITE;
/*!40000 ALTER TABLE `PERSON` DISABLE KEYS */;
INSERT INTO `PERSON` VALUES (1,'Anastasia','Kisel','Nemanskaya Street, 15-57','80291742419','wear glasses','nastya','nastya','Admin'),(2,'Artem','Baturin','Revolution Street, 24-75','00331452783','good at football','artem','artem','Admin'),(3,'Lena','Orlova','Kharyjina, 15-33','80256743619','fat','lena','lena','Client'),(4,'Anna','Kisel','Gorodzetskaya , 15-60','80335463831','14 years','anya','anya','Client'),(5,'Svetlana','Kisel','Olshevskogo, 17-22','80256438291','mother of two children','sveta','sveta','Client'),(6,'Dima','Luchkovsky','Lojonskaya, 32-12','80331242793',NULL,'dima','dima','Client'),(7,'Pasha','Sheglinsky','Smolenskaya, 14-43','80296435291',NULL,'pasha','pasha','Client'),(8,'Olga','Ivanova','Vera Khoryjaya, 13-42','80335243111',NULL,'olga','olga','Client'),(9,'Sasha','Shamgin','Kosmonavtov, 54-22','80297565323',NULL,'sasha','sasha','Client'),(10,'Vera','Yakovenko','Kalinovskogo, 23-12','80442139786',NULL,'vera','vera','Client'),(11,'Lera','Rudenko','Slavinskogo, 31-53','80331187654',NULL,'lera','lera','Client'),(12,'Kolya','Saminsky','Sedih, 44-22','80254132532',NULL,'kolya','kolya','Client'),(13,'Yaroslav','Zadvorni','Teplichnaya , 66-99','80336549373',NULL,'slava','slava','Client'),(14,'Diana','Martines-Sokolovskaya','Ostroshisckogo, 87-120','80254378321','student','dianka','dianka','Client'),(15,'Vasilisa','Kulik','Ostroshicskogo, 22-34','80332421783',NULL,'vasya','vasya','Client'),(16,'Lana','Sibir','Snegirova, 77-31','80335261183',NULL,'lana','lana','Client'),(17,'Denis','Kovalev','Pr. Nezavisimosti, 22-675','80295369202',NULL,'denis','denis','Client');
/*!40000 ALTER TABLE `PERSON` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-06  1:15:26
