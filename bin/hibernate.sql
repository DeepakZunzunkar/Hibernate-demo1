-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.11-beta-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema jarvishibernate
--

CREATE DATABASE IF NOT EXISTS jarvishibernate;
USE jarvishibernate;

--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `salary` double NOT NULL,
  `age` int(10) unsigned NOT NULL,
  `versionning` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`,`name`,`email`,`address`,`salary`,`age`,`versionning`) VALUES 
 (1,'Amit','amit@gmail.com','nagpur',300000,24,0),
 (2,'Ajeet','ajjet@gmail.com','pune',40000,35,0),
 (3,'James','james@gmail.com','neyork',50000,30,0),
 (4,'Sonoo','sn@gmail.com','mumbai',60000,30,0),
 (5,'Sarfraz','sarf@gmail.com','lahor',70000,40,0),
 (6,'Bob','bob@gmail.com','london',80000,25,0),
 (7,'Rahul','rahul@gmail.com','amravati',90000,24,0),
 (10,'ajay','jai@gmail.com','nanded',45000,25,3);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `emptemp`
--

DROP TABLE IF EXISTS `emptemp`;
CREATE TABLE `emptemp` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `salary` double NOT NULL,
  `age` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emptemp`
--

/*!40000 ALTER TABLE `emptemp` DISABLE KEYS */;
INSERT INTO `emptemp` (`id`,`name`,`email`,`address`,`salary`,`age`) VALUES 
 (1,'Amit','amit@gmail.com','nagpur',300000,24),
 (2,'Ajeet','ajjet@gmail.com','pune',40000,35),
 (3,'James','james@gmail.com','neyork',50000,30),
 (4,'Sonoo','sn@gmail.com','mumbai',60000,30),
 (5,'Sarfraz','sarf@gmail.com','lahor',70000,40),
 (6,'Bob','bob@gmail.com','london',80000,25),
 (7,'Rahul','rahul@gmail.com','amravati',90000,24),
 (8,'Rakesh','rk@gmail.com','nanded',25000,24),
 (10,'umesh','jai@gmail.com','nanded',45000,25);
/*!40000 ALTER TABLE `emptemp` ENABLE KEYS */;


--
-- Definition of table `stud`
--

DROP TABLE IF EXISTS `stud`;
CREATE TABLE `stud` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `mobileNo` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stud`
--

/*!40000 ALTER TABLE `stud` DISABLE KEYS */;
INSERT INTO `stud` (`id`,`name`,`mobileNo`) VALUES 
 (3,'varun','89652314'),
 (4,'rihan','456321789'),
 (5,'kallu','852369'),
 (6,'paresh','7852365'),
 (7,'suraj','45666223'),
 (8,'amit','855');
/*!40000 ALTER TABLE `stud` ENABLE KEYS */;


--
-- Definition of table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`,`name`,`address`) VALUES 
 (1,'deepak','chandrapur'),
 (2,'avi','avi@gmail.com'),
 (4,'ajit','baramati'),
 (9,'ajay','pune'),
 (11,'tivari','kanpur'),
 (12,'vibhuti','kanpur');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
