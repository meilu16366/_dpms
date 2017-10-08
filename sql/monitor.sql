CREATE DATABASE  IF NOT EXISTS `monitor` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `monitor`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: monitor
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `da_btf`
--

DROP TABLE IF EXISTS `da_btf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_btf` (
  `btfid` int(11) NOT NULL,
  `ctime` datetime NOT NULL,
  `ActivePower` decimal(10,4) DEFAULT NULL,
  `ReactivePower` decimal(10,4) DEFAULT NULL,
  `Cos` decimal(10,4) DEFAULT NULL,
  `Uab` decimal(10,4) DEFAULT NULL,
  `Ubc` decimal(10,4) DEFAULT NULL,
  `Uca` decimal(10,4) DEFAULT NULL,
  `Ua` decimal(10,4) DEFAULT NULL,
  `Ub` decimal(10,4) DEFAULT NULL,
  `Uc` decimal(10,4) DEFAULT NULL,
  `Ia` decimal(10,4) DEFAULT NULL,
  `Ib` decimal(10,4) DEFAULT NULL,
  `Ic` decimal(10,4) DEFAULT NULL,
  `Pa` decimal(10,4) DEFAULT NULL,
  `Pb` decimal(10,4) DEFAULT NULL,
  `Pc` decimal(10,4) DEFAULT NULL,
  `Qa` decimal(10,4) DEFAULT NULL,
  `Qb` decimal(10,4) DEFAULT NULL,
  `Qc` decimal(10,4) DEFAULT NULL,
  `Sa` decimal(10,4) DEFAULT NULL,
  `Sb` decimal(10,4) DEFAULT NULL,
  `Sc` decimal(10,4) DEFAULT NULL,
  `S` decimal(10,4) DEFAULT NULL,
  `GridFrequency` decimal(10,4) DEFAULT NULL,
  `GMPPow` decimal(10,4) DEFAULT NULL,
  `GMNPow` decimal(10,4) DEFAULT NULL,
  `GMPReaPow` decimal(10,4) DEFAULT NULL,
  `GMNReaPow` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`btfid`,`ctime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_btf`
--

LOCK TABLES `da_btf` WRITE;
/*!40000 ALTER TABLE `da_btf` DISABLE KEYS */;
/*!40000 ALTER TABLE `da_btf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `da_elm`
--

DROP TABLE IF EXISTS `da_elm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_elm` (
  `elmid` int(11) NOT NULL,
  `collecttime` datetime NOT NULL,
  `ForwardP` decimal(22,4) DEFAULT NULL,
  `ForwardReaP` decimal(22,4) DEFAULT NULL,
  `BackwardP` decimal(22,4) DEFAULT NULL,
  `BackwardReaP` decimal(22,4) DEFAULT NULL,
  PRIMARY KEY (`elmid`,`collecttime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_elm`
--

LOCK TABLES `da_elm` WRITE;
/*!40000 ALTER TABLE `da_elm` DISABLE KEYS */;
/*!40000 ALTER TABLE `da_elm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `da_emi`
--

DROP TABLE IF EXISTS `da_emi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_emi` (
  `emiid` int(11) NOT NULL,
  `ctime` datetime NOT NULL,
  `humidity` decimal(22,4) DEFAULT NULL,
  `temperature` decimal(22,4) DEFAULT NULL,
  `panelTemp` decimal(22,4) DEFAULT NULL,
  `instRadiationIevel` decimal(22,4) DEFAULT NULL,
  `instRadiationTilt` decimal(22,4) DEFAULT NULL,
  `allRadiationIevel` decimal(22,4) DEFAULT NULL,
  `allRadiationTilt` decimal(22,4) DEFAULT NULL,
  `windSpeed` decimal(22,4) DEFAULT NULL,
  `winDir` decimal(22,4) DEFAULT NULL,
  `pressure` decimal(22,4) DEFAULT NULL,
  PRIMARY KEY (`emiid`,`ctime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_emi`
--

LOCK TABLES `da_emi` WRITE;
/*!40000 ALTER TABLE `da_emi` DISABLE KEYS */;
/*!40000 ALTER TABLE `da_emi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `da_erroreq`
--

DROP TABLE IF EXISTS `da_erroreq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_erroreq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eqname` varchar(100) DEFAULT NULL,
  `descript` varchar(500) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `keeptime` decimal(10,2) DEFAULT NULL,
  `eqtype` varchar(20) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_erroreq`
--

LOCK TABLES `da_erroreq` WRITE;
/*!40000 ALTER TABLE `da_erroreq` DISABLE KEYS */;
/*!40000 ALTER TABLE `da_erroreq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `da_event`
--

DROP TABLE IF EXISTS `da_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collectid` int(11) DEFAULT NULL,
  `eqtype` varchar(10) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `eqname` varchar(50) DEFAULT NULL,
  `eventtype` int(1) DEFAULT NULL COMMENT '1遥信变位，2操作事件，3遥测越限',
  `describes` varchar(500) DEFAULT NULL,
  `action` int(11) DEFAULT NULL,
  `sure` char(1) DEFAULT NULL,
  `issoe` char(1) DEFAULT NULL,
  `mid` varchar(30) DEFAULT NULL,
  `elevel` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_event`
--

LOCK TABLES `da_event` WRITE;
/*!40000 ALTER TABLE `da_event` DISABLE KEYS */;
INSERT INTO `da_event` VALUES (1,1,'1','2017-10-08 12:12:33','1#逆变器',2,'adsf',1,'Y','N',NULL,1),(2,1,'1','2017-10-08 12:12:33','1#逆变器',2,'ds',1,'Y','Y',NULL,1),(3,1,'1','2017-10-08 12:12:33','1#逆变器',2,'sdf',0,'Y','Y',NULL,1),(4,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','Y',NULL,1),(5,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','Y',NULL,1),(6,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','N',NULL,1),(7,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','N',NULL,1),(8,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','N',NULL,1),(9,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','N',NULL,1),(10,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','N',NULL,1),(11,1,'1','2017-10-08 12:12:33','1#逆变器',2,'d',0,'Y','N',NULL,1);
/*!40000 ALTER TABLE `da_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `da_inverter`
--

DROP TABLE IF EXISTS `da_inverter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_inverter` (
  `inverterid` int(11) NOT NULL,
  `ctime` datetime NOT NULL,
  `TotalCapacity` decimal(10,4) DEFAULT NULL,
  `DayCapacity` decimal(10,4) DEFAULT NULL,
  `dcpower` decimal(10,4) DEFAULT NULL,
  `ACPower` decimal(10,4) DEFAULT NULL,
  `Uab` decimal(10,4) DEFAULT NULL,
  `Ubc` decimal(10,4) DEFAULT NULL,
  `Uca` decimal(10,4) DEFAULT NULL,
  `Ia` decimal(10,4) DEFAULT NULL,
  `Ib` decimal(10,4) DEFAULT NULL,
  `Ic` decimal(10,4) DEFAULT NULL,
  `GridFrequency` decimal(10,4) DEFAULT NULL,
  `PowerFactor` decimal(10,4) DEFAULT NULL,
  `InvertorTemp` decimal(10,4) DEFAULT NULL,
  `ReactivePower` decimal(10,4) DEFAULT NULL,
  `DCU1` decimal(10,4) DEFAULT NULL,
  `DCU2` decimal(10,4) DEFAULT NULL,
  `DCU3` decimal(10,4) DEFAULT NULL,
  `DCU4` decimal(10,4) DEFAULT NULL,
  `DCU5` decimal(10,4) DEFAULT NULL,
  `DCU6` decimal(10,4) DEFAULT NULL,
  `DCU7` decimal(10,4) DEFAULT NULL,
  `DCU8` decimal(10,4) DEFAULT NULL,
  `DCI1` decimal(10,4) DEFAULT NULL,
  `DCI2` decimal(10,4) DEFAULT NULL,
  `DCI3` decimal(10,4) DEFAULT NULL,
  `DCI4` decimal(10,4) DEFAULT NULL,
  `DCI5` decimal(10,4) DEFAULT NULL,
  `DCI6` decimal(10,4) DEFAULT NULL,
  `DCI7` decimal(10,4) DEFAULT NULL,
  `DCI8` decimal(10,4) DEFAULT NULL,
  `Efficient` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`inverterid`,`ctime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_inverter`
--

LOCK TABLES `da_inverter` WRITE;
/*!40000 ALTER TABLE `da_inverter` DISABLE KEYS */;
/*!40000 ALTER TABLE `da_inverter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `da_inverterday`
--

DROP TABLE IF EXISTS `da_inverterday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_inverterday` (
  `inverterdayid` int(11) NOT NULL AUTO_INCREMENT,
  `inverterid` int(11) DEFAULT NULL,
  `ctime` date DEFAULT NULL,
  `daycap` decimal(10,4) DEFAULT NULL,
  `efficiency` decimal(10,4) DEFAULT NULL,
  `hour` decimal(10,4) DEFAULT NULL,
  `maxdcpower` decimal(10,4) DEFAULT NULL,
  `maxacpower` decimal(10,4) DEFAULT NULL,
  `avgtemperature` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`inverterdayid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_inverterday`
--

LOCK TABLES `da_inverterday` WRITE;
/*!40000 ALTER TABLE `da_inverterday` DISABLE KEYS */;
INSERT INTO `da_inverterday` VALUES (1,1,'2017-09-27',1.0000,2.0000,3.0000,4.0000,5.0000,6.0000);
/*!40000 ALTER TABLE `da_inverterday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `da_psquotaday`
--

DROP TABLE IF EXISTS `da_psquotaday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `da_psquotaday` (
  `psdayid` int(11) NOT NULL AUTO_INCREMENT,
  `ctime` date DEFAULT NULL,
  `nbqdaycap` decimal(10,4) DEFAULT NULL,
  `dbdaycap` decimal(10,4) DEFAULT NULL,
  `bwdaycap` decimal(10,4) DEFAULT NULL,
  `hours` decimal(10,4) DEFAULT NULL,
  `maxnbqpower` decimal(10,4) DEFAULT NULL,
  `maxnbqtime` datetime DEFAULT NULL,
  `maxbwpower` decimal(10,4) DEFAULT NULL,
  `maxbwtime` datetime DEFAULT NULL,
  `avgnbqefficiency` decimal(10,4) DEFAULT NULL,
  `CO2` decimal(10,4) DEFAULT NULL,
  `coal` decimal(10,4) DEFAULT NULL,
  `totalRadia` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`psdayid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `da_psquotaday`
--

LOCK TABLES `da_psquotaday` WRITE;
/*!40000 ALTER TABLE `da_psquotaday` DISABLE KEYS */;
INSERT INTO `da_psquotaday` VALUES (1,'2017-10-01',111.0000,222.0000,333.0000,3.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'2017-10-02',44.0000,44.0000,44.0000,44.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'2017-10-03',65.0000,76.0000,98.0000,90.0000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `da_psquotaday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_btf`
--

DROP TABLE IF EXISTS `f_btf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_btf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `models` varchar(50) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  `comply` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_btf`
--

LOCK TABLES `f_btf` WRITE;
/*!40000 ALTER TABLE `f_btf` DISABLE KEYS */;
/*!40000 ALTER TABLE `f_btf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_elm`
--

DROP TABLE IF EXISTS `f_elm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_elm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `models` varchar(50) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  `comply` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `rate` decimal(10,4) DEFAULT NULL,
  `sumway` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_elm`
--

LOCK TABLES `f_elm` WRITE;
/*!40000 ALTER TABLE `f_elm` DISABLE KEYS */;
/*!40000 ALTER TABLE `f_elm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_emi`
--

DROP TABLE IF EXISTS `f_emi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_emi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `models` varchar(50) DEFAULT NULL,
  `inittype` varchar(50) DEFAULT NULL,
  `angle` int(11) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  `mains` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_emi`
--

LOCK TABLES `f_emi` WRITE;
/*!40000 ALTER TABLE `f_emi` DISABLE KEYS */;
/*!40000 ALTER TABLE `f_emi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_inverter`
--

DROP TABLE IF EXISTS `f_inverter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_inverter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `models` varchar(50) DEFAULT NULL,
  `power` decimal(10,4) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  `comply` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `startCap` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_inverter`
--

LOCK TABLES `f_inverter` WRITE;
/*!40000 ALTER TABLE `f_inverter` DISABLE KEYS */;
INSERT INTO `f_inverter` VALUES (1,'1#逆变器','YG1',500.0000,'1','AA',1,'DD',NULL,NULL),(2,'2#逆变器','YG1',500.0000,'1','BB',2,'DD',NULL,NULL),(3,'3#逆变器','YG1',500.0000,'1','CC',3,'DD',NULL,NULL),(4,'4#逆变器','YG1',500.0000,'1','CC',4,'DD',NULL,NULL);
/*!40000 ALTER TABLE `f_inverter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_inverterstateconf`
--

DROP TABLE IF EXISTS `f_inverterstateconf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_inverterstateconf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(50) DEFAULT NULL,
  `normalrun` varchar(200) DEFAULT NULL,
  `normalstop` varchar(200) DEFAULT NULL,
  `errorrun` varchar(200) DEFAULT NULL,
  `errorstop` varchar(200) DEFAULT NULL,
  `bread` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_inverterstateconf`
--

LOCK TABLES `f_inverterstateconf` WRITE;
/*!40000 ALTER TABLE `f_inverterstateconf` DISABLE KEYS */;
INSERT INTO `f_inverterstateconf` VALUES (1,'YG1','run','wait','error','fualt','break'),(2,'YG2','run','stop','error','fualt','break');
/*!40000 ALTER TABLE `f_inverterstateconf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_mergep`
--

DROP TABLE IF EXISTS `f_mergep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_mergep` (
  `mpid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `comply` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  `models` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_mergep`
--

LOCK TABLES `f_mergep` WRITE;
/*!40000 ALTER TABLE `f_mergep` DISABLE KEYS */;
/*!40000 ALTER TABLE `f_mergep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_mergepoint`
--

DROP TABLE IF EXISTS `f_mergepoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_mergepoint` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `models` varchar(50) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `comply` varchar(100) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_mergepoint`
--

LOCK TABLES `f_mergepoint` WRITE;
/*!40000 ALTER TABLE `f_mergepoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `f_mergepoint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_switchanger`
--

DROP TABLE IF EXISTS `f_switchanger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `f_switchanger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `comply` varchar(100) DEFAULT NULL,
  `collectid` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `models` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_switchanger`
--

LOCK TABLES `f_switchanger` WRITE;
/*!40000 ALTER TABLE `f_switchanger` DISABLE KEYS */;
/*!40000 ALTER TABLE `f_switchanger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_function`
--

DROP TABLE IF EXISTS `sys_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_function` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `attachurl` varchar(100) DEFAULT NULL,
  `disabled` char(1) DEFAULT 'Y',
  `mid` int(11) DEFAULT NULL,
  `orderno` varchar(20) DEFAULT NULL,
  `defultwork` char(1) DEFAULT 'N',
  PRIMARY KEY (`fid`),
  KEY `FK_Reference_3` (`mid`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`mid`) REFERENCES `sys_modul` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_function`
--

LOCK TABLES `sys_function` WRITE;
/*!40000 ALTER TABLE `sys_function` DISABLE KEYS */;
INSERT INTO `sys_function` VALUES (1,'JC1','逆变器监测','逆变器','1111','','N',4,'1','N'),(6,'111','111','','111','','N',2,'','N'),(30,'特特','111','','33333','','N',2,'','N'),(31,'test1','1','1','1','1','N',2,'1','N'),(33,'特特色1','eee','1','1','1','N',2,'1','N');
/*!40000 ALTER TABLE `sys_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_listvalue`
--

DROP TABLE IF EXISTS `sys_listvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_listvalue` (
  `listid` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `value` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `orderno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`listid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_listvalue`
--

LOCK TABLES `sys_listvalue` WRITE;
/*!40000 ALTER TABLE `sys_listvalue` DISABLE KEYS */;
INSERT INTO `sys_listvalue` VALUES (2,'TEST','测试','T1','测试1','1','1');
/*!40000 ALTER TABLE `sys_listvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_modul`
--

DROP TABLE IF EXISTS `sys_modul`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_modul` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `orderno` varchar(20) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `disable` char(1) DEFAULT 'N',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_modul`
--

LOCK TABLES `sys_modul` WRITE;
/*!40000 ALTER TABLE `sys_modul` DISABLE KEYS */;
INSERT INTO `sys_modul` VALUES (1,'功能模块','','功能模块','1',0,'N'),(2,'基础信息','tt','修改密码','2',1,'N'),(3,'设备监测',NULL,'设备监测','1',1,'Y'),(4,'逆变器监测',',,','逆变器监测','1',3,'N');
/*!40000 ALTER TABLE `sys_modul` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_param`
--

DROP TABLE IF EXISTS `sys_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_param` (
  `paramid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `value` varchar(200) DEFAULT NULL,
  `option1` varchar(200) DEFAULT NULL,
  `option2` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`paramid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_param`
--

LOCK TABLES `sys_param` WRITE;
/*!40000 ALTER TABLE `sys_param` DISABLE KEYS */;
INSERT INTO `sys_param` VALUES (1,'pageSize','15',NULL,NULL,'分页数',NULL),(2,'inverter.dci','8','null','null','组串逆变器最大支路数','null'),(3,'radiation.start','06:00:00','','','辐射开始时间','WERQ'),(4,'radiation.end','21:00:00','',NULL,'辐射结束时间',NULL),(5,'MEI.cycle','5',NULL,NULL,'气象仪采集间隔（分钟）',NULL),(6,'ACPOWER.data','da_inverter','ACPower',NULL,'交流功率取值方式',NULL),(7,'now.radian','1_QXY_YC_1_BevelRadiation',NULL,NULL,'瞬时辐射强度取值测点',NULL),(8,'total.radian','1_QXY_YC_1_TotalRadiation',NULL,NULL,'累计辐射量取值测点',NULL),(9,'m.temperature','1_QXY_YC_1_ModeTemp',NULL,NULL,'组件温度',NULL),(10,'h.temperature','1_QXY_YC_1_Temperature',NULL,NULL,'环境温度',NULL),(11,'inverter.daycap','DayCapacity',NULL,NULL,'逆变器实时日发电量（_id字段）',NULL),(12,'inverter.acpower','ACPower',NULL,NULL,'逆变器实时交流功率（_id字段）',NULL),(13,'inverter.dcpower','DCPower',NULL,NULL,'逆变器实时直流功率（_id字段）',NULL),(14,'inverter.branch','DCI',NULL,NULL,'逆变器的直流（或交流）支路',NULL),(15,'EMI.realradian','120',NULL,NULL,'瞬时辐射量比较值',NULL),(16,'EMI.radianpercent','0.6',NULL,NULL,'环境监测仪辐射量平均值的百分比（不能为0）',NULL),(17,'equipment','NBQ',NULL,NULL,'发电量选择（NBQ,BWD,DB）',NULL),(18,'DAYCAP','nbqdaycap',NULL,NULL,'累计发电量设备选择（nbqdaycap,bwdaycap,dbdaycap）',NULL),(19,'ps.daycap.type','NBQ','DayCapacity',NULL,'电站累计发电量获取方式',NULL),(20,'ps.acpower.type','NBQ','ACPower',NULL,'电站累计交流功率获取方式',NULL),(21,'ps.dcpower.type','NBQ','DCPower',NULL,'电站累计直流功率获取方式',NULL),(22,'sys.title','光伏电站监控系统',NULL,NULL,'系统名称',NULL),(23,'inverter.totalcap','TotalCapacity',NULL,NULL,'逆变器实时总发电量（_id字段）',NULL),(24,'inverter.dcilow','0.8',NULL,NULL,'支路偏低阈值',NULL),(26,'ps.info','演示电站1','5','2017-06-30','value:电站名称,option1:电站容量,option2:投产日期',NULL);
/*!40000 ALTER TABLE `sys_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_taskscheduler`
--

DROP TABLE IF EXISTS `sys_taskscheduler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_taskscheduler` (
  `taskid` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `className` varchar(200) DEFAULT NULL,
  `cycle` varchar(20) DEFAULT NULL,
  `disabled` char(1) DEFAULT 'N',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`taskid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_taskscheduler`
--

LOCK TABLES `sys_taskscheduler` WRITE;
/*!40000 ALTER TABLE `sys_taskscheduler` DISABLE KEYS */;
INSERT INTO `sys_taskscheduler` VALUES (1,'JB1','日报','com.kx.frame.job.DayQuotaJob','0 0 1 * * ?','N',NULL);
/*!40000 ALTER TABLE `sys_taskscheduler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sysname` varchar(100) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `orderno` varchar(6) DEFAULT NULL,
  `disabled` char(1) DEFAULT 'Y',
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (7,'admin','lueSGJZetyySpUndWjMBEg==','管理员',NULL,NULL,NULL,NULL,'1','N',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-08 18:23:31
