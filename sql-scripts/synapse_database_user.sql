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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK DETAILdA` (`student_id`),
  KEY `FK DETAIL1A` (`instructor_id`),
  CONSTRAINT `FK DETdAILA` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FK DEsTAILaA` FOREIGN KEY (`instructor_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (21,'toporj51','$2a$10$nffWEtM3k0pykPoIZLSfw.QCyZMUcza3k9uV3FjnWKGjPzCs/xinG','Tomasz','Zatorski','tomek@gmail.com',NULL,13),(22,'toporj52','$2a$10$bnz1okcd8jHGAww4OcgVs.7EocvH7syZ/YDVV2s/Z8iodCuDN8vHW','Marcin','Kula','markul001@gmail.com',25,NULL),(23,'toporj50','$2a$10$DsShZfGsToWiib0a0eY.1unepRSCSWzp4G2XD/CchviiCQ4fkhKXG','Agnieszka','Losek','aga@gmail.com',NULL,14),(24,'toporj53','$2a$10$MiP58irv6AmI2LyC7Vc.TOnu7FsPQxBtvj3J4jPqs7qsGdYM.sIHS','Tomek','Dobosz','tomek@gmail.com',26,NULL),(25,'toporj55','$2a$10$3vTt2P6Hd5xhgyH2oRvjzuKrc0CW/GkXj9mOGjfxC./3WRfK/jufO','Roman','Dmowski','Roman@gmail.com',27,NULL),(26,'toporj56','$2a$10$z7tf/dzhh.x.VQmJ09y5L.dY3Ayb/os93VSKKS7/gB9ccPKG//Tvu','Loki','Doki','loki@gmail.com',NULL,15);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
