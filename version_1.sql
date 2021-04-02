-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbs_v1
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `Name` varchar(45) NOT NULL,
  `State` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `Pin` int DEFAULT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_contact`
--

DROP TABLE IF EXISTS `company_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_contact` (
  `Company` varchar(45) NOT NULL,
  `Contact no.` int NOT NULL,
  PRIMARY KEY (`Company`,`Contact no.`),
  CONSTRAINT `cname_fk` FOREIGN KEY (`Company`) REFERENCES `company` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_contact`
--

LOCK TABLES `company_contact` WRITE;
/*!40000 ALTER TABLE `company_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Name` varchar(45) DEFAULT NULL,
  `Customer ID` int NOT NULL,
  `Age` int DEFAULT NULL,
  `Contact no.` int DEFAULT NULL,
  PRIMARY KEY (`Customer ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('Gabriel',500703,39,876587903);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_history`
--

DROP TABLE IF EXISTS `customer_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_history` (
  `Transaction ID` int NOT NULL,
  `Purchase date` date DEFAULT NULL,
  `Batch no.` int DEFAULT NULL,
  `Amount` int DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `Customer ID` int DEFAULT NULL,
  PRIMARY KEY (`Transaction ID`),
  KEY `cid_fk_idx` (`Customer ID`),
  KEY `cbtchno_fk_idx` (`Batch no.`),
  CONSTRAINT `cbtchno_fk` FOREIGN KEY (`Batch no.`) REFERENCES `medicine` (`Batch no.`),
  CONSTRAINT `cid_fk` FOREIGN KEY (`Customer ID`) REFERENCES `customer` (`Customer ID`),
  CONSTRAINT `ctrsctid_fk` FOREIGN KEY (`Transaction ID`) REFERENCES `sales_log` (`Sales transaction ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_history`
--

LOCK TABLES `customer_history` WRITE;
/*!40000 ALTER TABLE `customer_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `ID` int NOT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Date of joining` date DEFAULT NULL,
  `D.O.B` date DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Salary` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_contact`
--

DROP TABLE IF EXISTS `employee_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_contact` (
  `Contact no.` int NOT NULL,
  `ID` int NOT NULL,
  PRIMARY KEY (`Contact no.`,`ID`),
  KEY `eid_fk_idx` (`ID`),
  CONSTRAINT `eid_fk` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_contact`
--

LOCK TABLES `employee_contact` WRITE;
/*!40000 ALTER TABLE `employee_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `ID` int NOT NULL,
  `Time` time NOT NULL,
  `Date` date NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Time`,`Date`),
  CONSTRAINT `lid_fk` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `Name` varchar(45) DEFAULT NULL,
  `Batch no.` int NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Company` varchar(45) DEFAULT NULL,
  `Cost` int DEFAULT NULL,
  `D.O.M` date DEFAULT NULL,
  `D.O.E` date DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `MRP` int DEFAULT NULL,
  PRIMARY KEY (`Batch no.`),
  KEY `mcmpny_fk_idx` (`Company`),
  CONSTRAINT `mcmpny_fk` FOREIGN KEY (`Company`) REFERENCES `company` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_log`
--

DROP TABLE IF EXISTS `purchase_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_log` (
  `Batch no.` int DEFAULT NULL,
  `Purchase date` date DEFAULT NULL,
  `Transcation ID` int NOT NULL,
  `Amount` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Buyer ID` int DEFAULT NULL,
  `Company` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Transcation ID`),
  KEY `prchsebtchno_fk_idx` (`Batch no.`),
  KEY `prchsebyrid_fk_idx` (`Buyer ID`),
  KEY `prchsecmpny_fk_idx` (`Company`),
  CONSTRAINT `prchsebtchno_fk` FOREIGN KEY (`Batch no.`) REFERENCES `medicine` (`Batch no.`),
  CONSTRAINT `prchsebyrid_fk` FOREIGN KEY (`Buyer ID`) REFERENCES `employee` (`ID`),
  CONSTRAINT `prchsecmpny_fk` FOREIGN KEY (`Company`) REFERENCES `company` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_log`
--

LOCK TABLES `purchase_log` WRITE;
/*!40000 ALTER TABLE `purchase_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_log`
--

DROP TABLE IF EXISTS `sales_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_log` (
  `Batch no.` int DEFAULT NULL,
  `Sale date` date DEFAULT NULL,
  `Sales transaction ID` int NOT NULL,
  `Amount` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Seller ID` int DEFAULT NULL,
  `Buyer name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Sales transaction ID`),
  KEY `slsbtchno_fk_idx` (`Batch no.`),
  KEY `slsslrid_fk_idx` (`Seller ID`),
  CONSTRAINT `slsbtchno_fk` FOREIGN KEY (`Batch no.`) REFERENCES `medicine` (`Batch no.`),
  CONSTRAINT `slsslrid_fk` FOREIGN KEY (`Seller ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_log`
--

LOCK TABLES `sales_log` WRITE;
/*!40000 ALTER TABLE `sales_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dbs_v1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-25  0:27:50
