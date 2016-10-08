-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: hostel
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `availability`
--

DROP TABLE IF EXISTS `availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `availability` (
  `Date` date NOT NULL,
  `HostelID` int(11) NOT NULL,
  `FreePlace` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`Date`,`HostelID`),
  KEY `fk_Availability_Hostel1_idx` (`HostelID`),
  CONSTRAINT `fk_Availability_Hostel1` FOREIGN KEY (`HostelID`) REFERENCES `hostel` (`HostelID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availability`
--

LOCK TABLES `availability` WRITE;
/*!40000 ALTER TABLE `availability` DISABLE KEYS */;
INSERT INTO `availability` VALUES ('2016-08-01',1,32),('2016-08-02',1,32),('2016-08-03',1,32),('2016-08-04',1,32),('2016-08-05',1,32),('2016-08-06',1,32),('2016-08-07',1,32),('2016-08-08',1,32),('2016-08-09',1,32),('2016-08-10',1,32),('2016-08-11',1,32),('2016-08-12',1,32),('2016-08-13',1,32),('2016-08-14',1,32),('2016-08-15',1,32),('2016-08-16',1,32),('2016-08-17',1,32),('2016-08-18',1,32),('2016-08-19',1,32),('2016-08-20',1,32),('2016-08-21',1,32),('2016-08-22',1,32),('2016-08-23',1,32),('2016-08-24',1,32),('2016-08-25',1,32),('2016-08-26',1,32),('2016-08-27',1,32),('2016-08-28',1,32),('2016-08-29',1,32),('2016-08-30',1,32),('2016-08-31',1,32),('2016-09-01',1,27),('2016-09-02',1,27),('2016-09-03',1,25),('2016-09-04',1,27),('2016-09-05',1,28),('2016-09-06',1,26),('2016-09-07',1,19),('2016-09-08',1,19),('2016-09-09',1,19),('2016-09-10',1,19),('2016-09-11',1,19),('2016-09-12',1,19),('2016-09-13',1,18),('2016-09-14',1,14),('2016-09-15',1,18),('2016-09-16',1,18),('2016-09-17',1,18),('2016-09-18',1,18),('2016-09-19',1,18),('2016-09-20',1,18),('2016-09-21',1,17),('2016-09-22',1,24),('2016-09-23',1,24),('2016-09-24',1,24),('2016-09-25',1,23),('2016-09-26',1,23),('2016-09-27',1,24),('2016-09-28',1,24),('2016-09-29',1,29),('2016-09-30',1,30);
/*!40000 ALTER TABLE `availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `ClientID` int(11) NOT NULL AUTO_INCREMENT,
  `Surname` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Discount` tinyint(1) NOT NULL DEFAULT '0',
  `Banned` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'любовь','любить','любимая',0,0),(2,'Круглик','Антон','df@dfs.com',0,0),(3,'Suprunyuk','Anton','antsuprunyuk@gmail.com',0,0),(4,'Kruglik','Anton','antsuprunyuk@gmail.com',0,0);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `CommentID` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(500) NOT NULL,
  `ClientID` int(11) NOT NULL,
  `HostelID` int(11) NOT NULL,
  PRIMARY KEY (`CommentID`),
  KEY `fk_Comment_Client1_idx` (`ClientID`),
  KEY `fk_Comment_Hostel1_idx` (`HostelID`),
  CONSTRAINT `fk_Comment_Client1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Hostel1` FOREIGN KEY (`HostelID`) REFERENCES `hostel` (`HostelID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `CreditCardNumber` varchar(16) NOT NULL,
  `CreditCardType` enum('VISA','MASTERCARD','MAESTRO') NOT NULL,
  `CreditCardExpirationDate` date NOT NULL,
  `Money` decimal(10,2) NOT NULL DEFAULT '0.00',
  `ClientID` int(11) NOT NULL,
  PRIMARY KEY (`CreditCardNumber`),
  KEY `fk_CreditCard_Client1_idx` (`ClientID`),
  CONSTRAINT `fk_CreditCard_Client1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demand`
--

DROP TABLE IF EXISTS `demand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demand` (
  `DemandID` int(11) NOT NULL AUTO_INCREMENT,
  `HostelID` int(11) NOT NULL,
  `ClientID` int(11) NOT NULL,
  `DateIN` date NOT NULL,
  `DateOUT` date NOT NULL,
  `PlaceNumber` int(11) NOT NULL,
  `Purpose` enum('PAYMENT','BOOKING') NOT NULL,
  `Decision` enum('AWAITING','APPROVED','DISAPPROVED') NOT NULL DEFAULT 'AWAITING',
  PRIMARY KEY (`DemandID`),
  KEY `fk_Demands_Clients_idx` (`ClientID`),
  KEY `fk_Demand_Hostel1_idx` (`HostelID`),
  CONSTRAINT `fk_Demand_Hostel1` FOREIGN KEY (`HostelID`) REFERENCES `hostel` (`HostelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Demands_Clients` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demand`
--

LOCK TABLES `demand` WRITE;
/*!40000 ALTER TABLE `demand` DISABLE KEYS */;
INSERT INTO `demand` VALUES (29,1,4,'2016-09-14','2016-09-28',1,'PAYMENT','APPROVED'),(31,1,4,'2016-09-07','2016-09-14',1,'BOOKING','AWAITING'),(32,1,4,'2016-09-25','2016-09-30',1,'PAYMENT','AWAITING');
/*!40000 ALTER TABLE `demand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel`
--

DROP TABLE IF EXISTS `hostel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel` (
  `HostelID` int(11) NOT NULL AUTO_INCREMENT,
  `Address` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `Country` varchar(45) NOT NULL,
  `PlaceNumber` int(11) NOT NULL,
  `DayPrice` decimal(5,2) NOT NULL,
  PRIMARY KEY (`HostelID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel`
--

LOCK TABLES `hostel` WRITE;
/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` VALUES (1,'Beruta','Minsk','Belarus',32,25.00);
/*!40000 ALTER TABLE `hostel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(45) NOT NULL,
  `Userpass` varchar(64) NOT NULL,
  `ClientID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  KEY `fk_User_Client1_idx` (`ClientID`),
  CONSTRAINT `fk_User_Client1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin1','177eb25f56ba3acc72c717838008b3cfc332fbce5a9c414dbc6287fa5e12a708',NULL),('admin2','248f0766d941f9fbb456666d32443c1ddc4c88e3fce25be2f7cf2444c655b122',NULL),('admin3','8c7beb59f1bdaaecf58c8c0d153421326c05d7245c2ec94321f046b5c756b784',NULL),('Alerty','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `Username` varchar(45) NOT NULL,
  `UserRole` enum('CLIENT','ADMIN') NOT NULL DEFAULT 'CLIENT',
  PRIMARY KEY (`Username`),
  CONSTRAINT `fk_UserRole_User1` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES ('admin1','ADMIN'),('admin2','ADMIN'),('admin3','ADMIN'),('Alerty','CLIENT');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-12 13:59:07
