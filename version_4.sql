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
INSERT INTO `company` VALUES ('alstar meds','gujarat','ahemadabad',203715),('Cipla','Maharashtra','Mumbai',400307);
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
  `Contact_no` varchar(45) NOT NULL,
  PRIMARY KEY (`Company`,`Contact_no`),
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
  `Customer_ID` bigint NOT NULL AUTO_INCREMENT,
  `Age` int DEFAULT NULL,
  `Contact_no` bigint DEFAULT NULL,
  `Address` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('1',2,3,4,'5'),('Ashwin Avinash Wadatkar',9,20,8879821241,'A-1301, Gahlot Majesty');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_history`
--

DROP TABLE IF EXISTS `customer_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_history` (
  `Transaction_ID` varchar(45) NOT NULL,
  `Purchase_date` date DEFAULT NULL,
  `Batch_no` int NOT NULL,
  `Amount` double DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `Customer_ID` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Transaction_ID`,`Batch_no`),
  KEY `cid_fk_idx` (`Customer_ID`),
  KEY `cbtchno_fk_idx` (`Batch_no`),
  CONSTRAINT `cbtchno_fk` FOREIGN KEY (`Batch_no`) REFERENCES `medicine` (`Batch_no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_history`
--

LOCK TABLES `customer_history` WRITE;
/*!40000 ALTER TABLE `customer_history` DISABLE KEYS */;
INSERT INTO `customer_history` VALUES ('202104142254432','2021-04-14',25,540,'22:54:44',2),('202104142256402','2021-04-14',25,540,'22:56:40',2),('202104142300059','2021-04-14',25,540,'23:00:06',9),('202104142317352','2021-04-14',25,108,'23:17:35',2),('202104171317099','2021-04-17',218,1296,'13:17:09',9),('202104171318219','2021-04-17',25,108,'13:18:21',9),('202104181320459','2021-04-18',25,2268,'13:20:45',9),('202104181320459','2021-04-18',218,2268,'13:20:45',9);
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
  `Date_of_joining` date DEFAULT NULL,
  `D_O_B` date DEFAULT NULL,
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
INSERT INTO `employee` VALUES (0,'NA','admin','0000-00-00','0000-00-00','',0),(9,'Seawoods','Ashwin','2024-05-23','2001-04-12','rootpassword',3600000);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_contact`
--

DROP TABLE IF EXISTS `employee_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_contact` (
  `Contact_no` bigint NOT NULL,
  `ID` int NOT NULL,
  PRIMARY KEY (`Contact_no`,`ID`),
  KEY `eid_fk_idx` (`ID`),
  CONSTRAINT `eid_fk` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_contact`
--

LOCK TABLES `employee_contact` WRITE;
/*!40000 ALTER TABLE `employee_contact` DISABLE KEYS */;
INSERT INTO `employee_contact` VALUES (8779617377,9),(8879821241,9);
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
INSERT INTO `login` VALUES (0,'13:04:59','2021-04-11','','admin'),(0,'13:25:59','2021-04-18','','admin'),(0,'13:43:45','2021-04-17','','admin'),(0,'23:19:35','2021-04-14','','admin'),(9,'13:19:48','2021-04-18','rootpassword','Ashwin'),(9,'22:58:57','2021-04-14','rootpassword','Ashwin');
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
  `Batch_no` int NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Company` varchar(45) DEFAULT NULL,
  `Cost` int DEFAULT NULL,
  `D_O_M` date DEFAULT NULL,
  `D_O_E` date DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `MRP` int DEFAULT NULL,
  PRIMARY KEY (`Batch_no`),
  KEY `mcmpny_fk_idx` (`Company`),
  CONSTRAINT `mcmpny_fk` FOREIGN KEY (`Company`) REFERENCES `company` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES ('abcd',21,'efgh','alstar meds',20,'2020-08-20','2021-08-20',0,200),('efgh',22,'abcd','alstar meds',20,'2020-01-01','2021-01-01',25,200),('Paracetamol',25,'anti-pyretic','Cipla',25,'2020-09-04','2022-09-04',4,100),('Remdesivir',218,'anti-covid','Cipla',255,'2021-04-09','2022-05-07',20,600);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_log`
--

DROP TABLE IF EXISTS `purchase_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_log` (
  `Batch_no` int NOT NULL,
  `Purchase_date` date DEFAULT NULL,
  `Transcation_ID` varchar(45) NOT NULL,
  `Amount` double DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Buyer_ID` int DEFAULT NULL,
  `Company` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Transcation_ID`,`Batch_no`),
  KEY `prchsebtchno_fk_idx` (`Batch_no`),
  KEY `prchsebyrid_fk_idx` (`Buyer_ID`),
  KEY `prchsecmpny_fk_idx` (`Company`),
  CONSTRAINT `prchsebyrid_fk` FOREIGN KEY (`Buyer_ID`) REFERENCES `employee` (`ID`),
  CONSTRAINT `prchsecmpny_fk` FOREIGN KEY (`Company`) REFERENCES `company` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_log`
--

LOCK TABLES `purchase_log` WRITE;
/*!40000 ALTER TABLE `purchase_log` DISABLE KEYS */;
INSERT INTO `purchase_log` VALUES (218,'2021-04-17','202104171211520',6375,25,0,'Cipla');
/*!40000 ALTER TABLE `purchase_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_log`
--

DROP TABLE IF EXISTS `sales_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_log` (
  `Batch_no` int NOT NULL,
  `Sale_date` date DEFAULT NULL,
  `Sales_transaction_ID` varchar(45) NOT NULL,
  `Amount` double DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Seller_ID` int DEFAULT NULL,
  `Buyer_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Sales_transaction_ID`,`Batch_no`),
  KEY `slsbtchno_fk_idx` (`Batch_no`),
  KEY `slsslrid_fk_idx` (`Seller_ID`),
  CONSTRAINT `slsbtchno_fk` FOREIGN KEY (`Batch_no`) REFERENCES `medicine` (`Batch_no`),
  CONSTRAINT `slsslrid_fk` FOREIGN KEY (`Seller_ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_log`
--

LOCK TABLES `sales_log` WRITE;
/*!40000 ALTER TABLE `sales_log` DISABLE KEYS */;
INSERT INTO `sales_log` VALUES (25,'2021-04-14','202104142254432',540,5,0,'1'),(25,'2021-04-14','202104142256402',540,5,0,'1'),(25,'2021-04-14','202104142300059',540,5,9,'Ashwin Avinash Wadatkar'),(25,'2021-04-14','202104142317352',108,1,0,'1'),(25,'2021-04-16','20210416230223Hemlo',108,1,0,'Ashwin'),(218,'2021-04-17','202104171317099',1296,2,0,'Ashwin Avinash Wadatkar'),(25,'2021-04-17','202104171318219',108,1,0,'Ashwin Avinash Wadatkar'),(25,'2021-04-18','202104181320459',2268,3,9,'Ashwin Avinash Wadatkar'),(218,'2021-04-18','202104181320459',2268,3,9,'Ashwin Avinash Wadatkar');
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

-- Dump completed on 2021-04-18 13:34:31
