-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: subsnap
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `PROJECTS`
--

DROP TABLE IF EXISTS `PROJECTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROJECTS` (
  `project_id` int(11) NOT NULL,
  `project_name` varchar(45) DEFAULT NULL,
  `project_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `writer` varchar(45) DEFAULT NULL,
  `director` varchar(45) DEFAULT NULL,
  `star` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `project_id_UNIQUE` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROJECTS`
--

LOCK TABLES `PROJECTS` WRITE;
/*!40000 ALTER TABLE `PROJECTS` DISABLE KEYS */;
INSERT INTO `PROJECTS` VALUES (1,'TEST1','2014-11-22 21:42:00',NULL,NULL,NULL),(2,'TEST2','2014-11-22 21:42:00',NULL,NULL,NULL),(3,'TEST3','2014-11-22 23:30:38',NULL,NULL,NULL),(4,'project3','2014-11-22 23:56:03',NULL,NULL,NULL);
/*!40000 ALTER TABLE `PROJECTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SENDS`
--

DROP TABLE IF EXISTS `SENDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SENDS` (
  `send_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `send_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`send_id`),
  UNIQUE KEY `send_id_UNIQUE` (`send_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SENDS`
--

LOCK TABLES `SENDS` WRITE;
/*!40000 ALTER TABLE `SENDS` DISABLE KEYS */;
INSERT INTO `SENDS` VALUES (1,1,'2014-11-22 21:46:36'),(2,2,'2014-11-22 21:46:36');
/*!40000 ALTER TABLE `SENDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `send_emails`
--

DROP TABLE IF EXISTS `send_emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `send_emails` (
  `send_email_address` varchar(45) NOT NULL,
  `send_id` varchar(45) NOT NULL,
  `send_email_name` varchar(45) DEFAULT NULL,
  `send_email_id` int(11) NOT NULL,
  `send_email_title` text,
  `send_email_body` text,
  `isWatermarked` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`send_email_id`),
  UNIQUE KEY `send_email_id_UNIQUE` (`send_email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `send_emails`
--

LOCK TABLES `send_emails` WRITE;
/*!40000 ALTER TABLE `send_emails` DISABLE KEYS */;
INSERT INTO `send_emails` VALUES ('user1@gmail.com','1','user1',1,NULL,NULL,'false'),('user2@gmail.com','2','user2',2,NULL,NULL,'false');
/*!40000 ALTER TABLE `send_emails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-23 11:55:14
