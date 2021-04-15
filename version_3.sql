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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Name` varchar(45) DEFAULT NULL,
  `Customer_ID` varchar(45) NOT NULL,
  `Age` int DEFAULT NULL,
  `Contact_no` bigint DEFAULT NULL,
  `Address` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `Customer_ID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Transaction_ID`,`Batch_no`),
  KEY `cid_fk_idx` (`Customer_ID`),
  KEY `cbtchno_fk_idx` (`Batch_no`),
  CONSTRAINT `cbtchno_fk` FOREIGN KEY (`Batch_no`) REFERENCES `medicine` (`Batch_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2021-04-11 12:46:49
