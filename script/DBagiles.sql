-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: metodos_agiles
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `contribuyente`
--

DROP TABLE IF EXISTS `contribuyente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contribuyente` (
  `dni` int NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `calle` varchar(200) DEFAULT NULL,
  `nro_casa` int DEFAULT NULL,
  `grupo_sanguineo` varchar(2) DEFAULT NULL,
  `rh` varchar(1) DEFAULT NULL,
  `es_donante` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contribuyente`
--

LOCK TABLES `contribuyente` WRITE;
/*!40000 ALTER TABLE `contribuyente` DISABLE KEYS */;
/*!40000 ALTER TABLE `contribuyente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `licencia`
--

DROP TABLE IF EXISTS `licencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `licencia` (
  `id_licencia` int NOT NULL,
  `dni_titular` int NOT NULL,
  `nombre_titular` varchar(100) DEFAULT NULL,
  `apellido_titular` varchar(100) DEFAULT NULL,
  `fecha_nac_titular` date DEFAULT NULL,
  `calle_titular` varchar(200) DEFAULT NULL,
  `nro_casa_titular` int DEFAULT NULL,
  `clase` varchar(1) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `grupo_sang_titular` varchar(2) DEFAULT NULL,
  `rh_titular` varchar(1) DEFAULT NULL,
  `es_donante_titular` varchar(2) DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `administrador` varchar(200) DEFAULT NULL,
  `vigente` varchar(2) DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  PRIMARY KEY (`id_licencia`),
  KEY `dni_titular_idx` (`dni_titular`),
  CONSTRAINT `dni_titular_fk` FOREIGN KEY (`dni_titular`) REFERENCES `titular` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licencia`
--

LOCK TABLES `licencia` WRITE;
/*!40000 ALTER TABLE `licencia` DISABLE KEYS */;
INSERT INTO `licencia` VALUES (1,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','no hay observaciones','2021-04-20','Juan Perez','no','2022-07-09'),(2,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','no hay observaciones','2022-07-09','Juan Perez','no','2025-07-09'),(3,16227700,'Norberto','Quarto','1958-04-04','Tacuari',7106,'B','original','A','-','si','no hay observaciones','2023-04-04','Juan Perez','no',NULL),(4,45000000,'Juan','Riquelme','2003-06-11','San jeronimo',1802,'B','original','B','+','si','utiliza lentes','2024-06-13','Juan Perez','no','2029-06-11'),(5,45000000,'Juan','Riquelme','2003-06-11','San jeronimo',1802,'B','original','B','+','si','usa lentes','2024-06-13','Juan Perez','si','2029-06-11'),(6,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'C','original','A','+','no','tiene mucha facha','2024-06-13','Juan Perez','no','2029-07-09'),(7,16227700,'Norberto','Quarto','1958-04-04','Tacuari',7106,'G','original','A','-','si','','2024-06-13','Juan Perez','si','2027-04-04'),(8,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(9,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(10,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(11,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(12,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','maneja bien','2024-06-13','Juan Perez','no','2029-07-09'),(13,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(14,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','BUENAS','2024-06-13','Juan Perez','no','2029-07-09'),(15,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(16,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(17,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','no','2029-07-09'),(18,44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'B','original','A','+','no','','2024-06-13','Juan Perez','si','2029-07-09');
/*!40000 ALTER TABLE `licencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titular`
--

DROP TABLE IF EXISTS `titular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `titular` (
  `dni` int NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `calle` varchar(200) DEFAULT NULL,
  `nro_casa` int DEFAULT NULL,
  `grupo_sanguineo` varchar(2) DEFAULT NULL,
  `rh` varchar(1) DEFAULT NULL,
  `es_donante` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titular`
--

LOCK TABLES `titular` WRITE;
/*!40000 ALTER TABLE `titular` DISABLE KEYS */;
INSERT INTO `titular` VALUES (16227700,'Norberto','Quarto','1958-04-04','Tacuari',7106,'A','-','si'),(43425352,'Felipe','Cabello','2001-09-06','Alvear',1735,'B','+','si'),(44288049,'Martin','Weidmann','2002-07-09','4 de enero',2985,'A','+','no'),(45000000,'Juan','Riquelme','2003-06-11','San jeronimo',1802,'B','+','si'),(49123456,'Pedro','Gimenez','2007-06-04','Suipacha',2500,'A','-','no');
/*!40000 ALTER TABLE `titular` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-25 17:32:44
