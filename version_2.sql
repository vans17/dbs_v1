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
INSERT INTO `company` VALUES ('Candid','Maharashtra','Airoli',400309),('Cipla','Maharashtra','Panvel',400204),('Meds co.','Maharashtra','Andheri',400209),('Ranbaxy','Maharashtra','Dadar',400207);
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
  `Customer_ID` int NOT NULL,
  `Age` int DEFAULT NULL,
  `Contact_no` int DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`)
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
  `Transaction_ID` int NOT NULL,
  `Purchase_date` date DEFAULT NULL,
  `Batch_no` int DEFAULT NULL,
  `Amount` int DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `Customer_ID` int DEFAULT NULL,
  PRIMARY KEY (`Transaction_ID`),
  KEY `cid_fk_idx` (`Customer_ID`),
  KEY `cbtchno_fk_idx` (`Batch_no`),
  CONSTRAINT `cbtchno_fk` FOREIGN KEY (`Batch_no`) REFERENCES `medicine` (`Batch_no`),
  CONSTRAINT `cid_fk` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`Customer_ID`),
  CONSTRAINT `ctrsctid_fk` FOREIGN KEY (`Transaction_ID`) REFERENCES `sales_log` (`Sales_transaction_ID`)
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
INSERT INTO `employee` VALUES (0,'','admin','0000-00-00','0000-00-00','',0),(9,'Seawoods','Ashwin Avinash Wadatkar','2024-05-25','2001-04-12','rootpassword',3600000);
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
INSERT INTO `employee_contact` VALUES (2160947810,0),(9769213816,0),(8779617377,9),(8879821241,9);
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
INSERT INTO `login` VALUES (0,'02:44:47','2021-04-03','','admin'),(0,'02:48:19','2021-04-03','','admin'),(0,'02:49:36','2021-04-03','','admin'),(0,'02:50:47','2021-04-03','','admin'),(0,'02:53:33','2021-04-03','','admin'),(0,'02:57:11','2021-04-03','','admin'),(0,'03:13:55','2021-04-03','','admin'),(0,'03:14:43','2021-04-03','','admin'),(0,'03:25:16','2021-04-03','','admin'),(0,'11:03:24','2021-04-03','','admin'),(0,'12:21:15','2021-04-05','','admin'),(0,'12:31:20','2021-04-03','','admin'),(0,'12:34:06','2021-04-03','','admin'),(0,'12:51:56','2021-04-03','','admin'),(0,'12:52:11','2021-04-03','','admin'),(0,'12:52:16','2021-04-03','','admin'),(0,'17:37:12','2021-04-03','','admin'),(0,'19:08:34','2021-04-05','','admin'),(0,'19:37:23','2021-04-05','','admin'),(0,'19:41:58','2021-04-05','','admin'),(0,'20:14:29','2021-04-05','','admin'),(9,'02:43:55','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'03:20:09','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'11:02:54','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'11:03:59','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'11:32:15','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'12:31:45','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'12:34:45','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'12:50:30','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'12:50:56','2021-04-03','rootpassword','Niwhsa'),(9,'12:51:39','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'12:52:35','2021-04-03','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:07:14','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:07:36','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:08:19','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:09:50','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:36:29','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:37:08','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:41:02','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:42:26','2021-04-05','rootpassword','Ashwin Avinash Wadatkar'),(9,'19:47:03','2021-04-05','rootpassword','Ashwin Avinash Wadatkar');
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
INSERT INTO `medicine` VALUES ('Ondem',21,'anti-pyretic','Ranbaxy',80,'2021-03-04','2022-04-04',30,150),('Pan-40',22,'anti-acidity','Meds co.',80,'2021-03-04','2022-04-04',30,150),('Dusting Powder',24,'anti-fungal','Candid',95,'2020-08-20','2022-08-20',25,195),('Imidazole',25,'analgesic','Cipla',25,'2020-05-20','2021-05-20',75,4);
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
  `Transcation_ID` int NOT NULL,
  `Amount` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Buyer_ID` int DEFAULT NULL,
  `Company` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Transcation_ID`,`Batch_no`),
  KEY `prchsebtchno_fk_idx` (`Batch_no`),
  KEY `prchsebyrid_fk_idx` (`Buyer_ID`),
  KEY `prchsecmpny_fk_idx` (`Company`),
  CONSTRAINT `prchsebtchno_fk` FOREIGN KEY (`Batch_no`) REFERENCES `medicine` (`Batch_no`),
  CONSTRAINT `prchsebyrid_fk` FOREIGN KEY (`Buyer_ID`) REFERENCES `employee` (`ID`),
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
  `Batch_no` int NOT NULL,
  `Sale_date` date DEFAULT NULL,
  `Sales_transaction_ID` int NOT NULL,
  `Amount` int DEFAULT NULL,
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

-- Dump completed on 2021-04-05 20:18:06
