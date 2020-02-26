-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: synapse_database
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `destiny_for` varchar(50) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_unique` (`title`),
  KEY `fk_my_instruktor` (`instructor_id`),
  CONSTRAINT `fk_my_instruktor` FOREIGN KEY (`instructor_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (15,'Dodawanie 1','test z dodwania','Klasa 3',13,'Matematyka',0),(16,'Dodawanie 4','test z dodwania','Klasa 8',13,'Matematyka',1),(17,'Stolice Europy','Test ze stolic Europy','Liceum klasa 1',13,'Geografia',0),(18,'Dodawanie 5','test z dodawania','Klasa 4',13,'Matematyka',1),(19,'Dodawanie 7','test z dodwania','Liceum klasa 3',13,'Matematyka',0),(21,'Dodawanie 8','test z dodwania','Liceum klasa 3',13,'Matematyka',0),(23,'Dodawanie 2','test z dodwania','Klasa 8',13,'Matematyka',1),(24,'Dodawanie 3','test z dodwania','Klasa 8',13,'Matematyka',0),(25,'Dodawanie 6','test z dodwania','Klasa 8',13,'Matematyka',0),(26,'Dodawanie 9','test z dodwania','Klasa 8',13,'Matematyka',0),(27,'Dodawanie 10','test z dodwania','Klasa 8',13,'Matematyka',0),(28,'Dodawanie 11','test z dodwania','Klasa 8',13,'Matematyka',0),(29,'filmik','aaa','Klasa 8',13,'Matematyka',1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-14 14:42:26
