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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(128) DEFAULT NULL,
  `answer_a` varchar(128) DEFAULT NULL,
  `answer_b` varchar(128) DEFAULT NULL,
  `answer_c` varchar(128) DEFAULT NULL,
  `answer_d` varchar(128) DEFAULT NULL,
  `student_answer` varchar(128) DEFAULT NULL,
  `correct_answer` varchar(128) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `resource` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_course` (`course_id`),
  CONSTRAINT `fk_course` FOREIGN KEY (`course_id`) REFERENCES `test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (20,'ile to jest 8 + 8?','16','24','12','5',NULL,'16',18,NULL,NULL),(21,'ile to jest 1 + 1?','2','4','6','1',NULL,'2',18,NULL,NULL),(26,'ile to jest 8 + 8?','16','11','21','14',NULL,'16',16,'',NULL),(28,'ile jest 2 + 2? ','1','3','2','4',NULL,'4',19,NULL,NULL),(29,'ile jest 2 + 3? ','1','2','3','5',NULL,'5',19,NULL,NULL),(30,'ile jest 2 + 2? ','1','2','3','4',NULL,'4',21,NULL,NULL),(31,'ile jest 2 + 3? ','1','2','5','3',NULL,'5',21,NULL,NULL),(32,'ile jest 4 + 4? ','7','8','2','4',NULL,'8',21,NULL,NULL),(33,'ile jest 5 + 5? ','1','5','19','10',NULL,'10',21,NULL,NULL),(34,'ile jest 2 + 2? ','1','4','2','3',NULL,'4',23,NULL,NULL),(35,'ile jest 4 + 4? ','8','2','3','4',NULL,'8',23,NULL,NULL),(36,'ile jest 5 + 5? ','10','2','3','1',NULL,'10',23,NULL,NULL),(37,'ile jest 2 + 2? ','3','2','1','4',NULL,'4',24,NULL,NULL),(38,'ile jest 4 + 4? ','3','4','8','9',NULL,'8',24,NULL,NULL),(39,'ile jest 2 + 3? ','3','6','5','4',NULL,'5',24,NULL,NULL),(40,'ile jest 5 + 5? ','1','2','51','10',NULL,'10',25,NULL,NULL),(41,'ile jest 5 + 5? ','1','10','2','1',NULL,'10',25,NULL,NULL),(42,'ile jest 4 + 4? ','2','4','7','8',NULL,'8',25,NULL,NULL),(43,'ile jest 2 + 3? ','3','2','6','5',NULL,'5',26,NULL,NULL),(44,'ile jest 4 + 4? ','14','8','34','4',NULL,'8',26,NULL,NULL),(45,'ile jest 4 + 4? ','2','6','9','8',NULL,'8',27,NULL,NULL),(46,'ile jest 5 + 5? ','10','2','45','2',NULL,'10',27,NULL,NULL),(47,'ile jest 2 + 2? ','3','4','43','1',NULL,'4',27,NULL,NULL),(48,'ile jest 2 + 2? ','2','5','4','43',NULL,'4',28,NULL,NULL),(49,'ile jest 5 + 5? ','10','2','4','1',NULL,'10',28,NULL,NULL),(50,'ile jest 2 + 3? ','6','5','3','2',NULL,'5',28,NULL,NULL),(51,'ile jest 5 + 5? ','10','2','4','2',NULL,'10',28,NULL,NULL),(54,'Co widzisz na obrazku? ','Mazde 3 ','Mazde 2','Mazde 5','Mazde 6',NULL,'Mazde 3',29,'image','https://m.autocentrum.pl/ac-file/gallery-photo/5cacafa057502af973132bd5/mazda-3.jpg'),(55,'Co widzisz na obrazku? ','a','a','a','a',NULL,'a',29,NULL,'https://d-mf.ppstatic.pl/art/7x/ni/xm84dkg8skow8o4kgow00/mazda3-tcr-race-car-1-de085d68a9-0-750-0-0.1200.jpg'),(56,'Co widzisz na obrazku? ','a','a','a','a',NULL,'a',29,'video','https://www.youtube.com/watch?v=pnuJr1dYtpA,https://d-mf.ppstatic.pl/art/7x/ni/xm84dkg8skow8o4kgow00/mazda3-tcr-race-car-1-de085d68a9-0-750-0-0.1200.jpg'),(57,'Co widzisz na obrazku? ','a','a','a','a',NULL,'a',29,NULL,'https://www.dailymotion.com/video/x78y1st?playlist=x5v2j4,https://pl.cdn.mazda.media/a2aabf878672428e963a2beb31969532/bd9fd63b643b430b98423b75f2854d91.jpg?4a5c64'),(58,'ile jest 2 + 2? ','a','a','a','a',NULL,'a',29,'text',''),(59,'ile jest 2 + 2? ','3','4','2','1',NULL,'4',16,'text',''),(60,'ile jest 4 + 4? ','10','8','3','1',NULL,'8',16,'text','');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-14 14:42:25
