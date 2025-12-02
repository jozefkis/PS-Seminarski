/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.32-MariaDB : Database - seminarski
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `seminarski`;

/*Table structure for table `caj` */

DROP TABLE IF EXISTS `caj`;

CREATE TABLE `caj` (
  `idCaj` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(30) NOT NULL,
  `cena` decimal(10,2) unsigned NOT NULL CHECK (`cena` > 0),
  `korisnickoUputstvo` varchar(200) NOT NULL,
  `opis` varchar(200) NOT NULL,
  PRIMARY KEY (`idCaj`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `caj` */

insert  into `caj`(`idCaj`,`naziv`,`cena`,`korisnickoUputstvo`,`opis`) values 
(1,'Wellness',3000.00,'*DODATI*','*DODATI*'),
(2,'Protiv prehlade',2500.00,'*DODATI*','*DODATI*');

/*Table structure for table `kupac` */

DROP TABLE IF EXISTS `kupac`;

CREATE TABLE `kupac` (
  `idKupac` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `telefon` varchar(12) NOT NULL,
  `idMesto` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`idKupac`),
  UNIQUE KEY `unique_telefon` (`telefon`),
  KEY `idMesto` (`idMesto`),
  CONSTRAINT `kupac_ibfk_1` FOREIGN KEY (`idMesto`) REFERENCES `mesto` (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kupac` */

insert  into `kupac`(`idKupac`,`ime`,`prezime`,`telefon`,`idMesto`) values 
(3,'Žika','Žikić','0648889999',3),
(5,'Miloš','Milošević','0657770907',2),
(6,'Marko','Marković','0655055005',1),
(7,'Petar','Petrovic','0659990909',1),
(10,'Dusan','Spasic','0659099009',2);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `idMesto` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  PRIMARY KEY (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`idMesto`,`naziv`) values 
(1,'Beograd'),
(2,'Novi Sad'),
(3,'Niš');

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `idRacun` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `datum` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `ukupanIznos` decimal(15,2) NOT NULL CHECK (`ukupanIznos` > 0),
  `idTravar` bigint(20) unsigned NOT NULL,
  `idKupac` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`idRacun`),
  KEY `idTravar` (`idTravar`),
  KEY `idKupac` (`idKupac`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`idTravar`) REFERENCES `travar` (`idTravar`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`idKupac`) REFERENCES `kupac` (`idKupac`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racun` */

insert  into `racun`(`idRacun`,`datum`,`ukupanIznos`,`idTravar`,`idKupac`) values 
(1,'2025-11-28 15:43:52',6000.00,3,10);

/*Table structure for table `sertifikat` */

DROP TABLE IF EXISTS `sertifikat`;

CREATE TABLE `sertifikat` (
  `idSertifikat` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(30) NOT NULL,
  `opis` varchar(100) NOT NULL,
  PRIMARY KEY (`idSertifikat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sertifikat` */

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `idRacun` bigint(20) unsigned NOT NULL,
  `rb` int(11) NOT NULL DEFAULT 1 CHECK (`rb` > 0),
  `kolicina` int(11) NOT NULL CHECK (`kolicina` > 0),
  `cena` decimal(10,2) NOT NULL CHECK (`cena` > 0),
  `iznos` decimal(10,2) NOT NULL,
  `idCaj` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`idRacun`,`rb`),
  KEY `idCaj` (`idCaj`),
  CONSTRAINT `stavkaracuna_ibfk_1` FOREIGN KEY (`idRacun`) REFERENCES `racun` (`idRacun`) ON DELETE NO ACTION,
  CONSTRAINT `stavkaracuna_ibfk_2` FOREIGN KEY (`idCaj`) REFERENCES `caj` (`idCaj`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`idRacun`,`rb`,`kolicina`,`cena`,`iznos`,`idCaj`) values 
(1,1,2,3000.00,6000.00,1);

/*Table structure for table `travar` */

DROP TABLE IF EXISTS `travar`;

CREATE TABLE `travar` (
  `idTravar` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `telefon` varchar(15) NOT NULL,
  `korisnickoIme` varchar(30) NOT NULL,
  `sifra` varchar(30) NOT NULL,
  PRIMARY KEY (`idTravar`),
  UNIQUE KEY `unique_telefon` (`telefon`),
  UNIQUE KEY `unique_korisnickoIme` (`korisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `travar` */

insert  into `travar`(`idTravar`,`ime`,`prezime`,`telefon`,`korisnickoIme`,`sifra`) values 
(1,'Pera','Perić','0604445555','perape','perape'),
(3,'Ana','Anić','0601115555','anci','anci');

/*Table structure for table `trvs` */

DROP TABLE IF EXISTS `trvs`;

CREATE TABLE `trvs` (
  `idTravar` bigint(20) unsigned NOT NULL,
  `idSertifikat` bigint(20) unsigned NOT NULL,
  `datumIzdavanja` date NOT NULL,
  PRIMARY KEY (`idTravar`,`idSertifikat`),
  KEY `idSertifikat` (`idSertifikat`),
  CONSTRAINT `trvs_ibfk_1` FOREIGN KEY (`idTravar`) REFERENCES `travar` (`idTravar`) ON DELETE NO ACTION,
  CONSTRAINT `trvs_ibfk_2` FOREIGN KEY (`idSertifikat`) REFERENCES `sertifikat` (`idSertifikat`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `trvs` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
