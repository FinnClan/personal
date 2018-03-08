-- phpMyAdmin SQL Dump
-- version 4.0.10.20
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 10, 2018 at 12:18 PM
-- Server version: 5.1.73-log
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ProfClothesCloset`
--

-- --------------------------------------------------------

--
-- Table structure for table `ArticleType`
--

DROP TABLE IF EXISTS `ArticleType`;
CREATE TABLE IF NOT EXISTS `ArticleType` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `Description` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `BarcodePrefix` varchar(5) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `AlphaCode` varchar(5) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `Status` enum('Active','Inactive') CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL DEFAULT 'Active',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `BarcodePrefix` (`BarcodePrefix`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `ArticleType`
--

INSERT INTO `ArticleType` (`ID`, `Description`, `BarcodePrefix`, `AlphaCode`, `Status`) VALUES
(1, 'Pant Suit', '01', 'PS', 'Active'),
(2, 'Skirt Suit', '02', 'SS', 'Active'),
(3, 'Blazer', '03', 'BL', 'Active'),
(4, 'Dress', '04', 'D', 'Active'),
(5, 'Shoe', '05', 'SH', 'Active'),
(6, 'Shirt', '06', 'ST', 'Active'),
(7, '3-Piece Pant Suit', '07', '3PS', 'Active'),
(8, 'Pants', '08', 'P', 'Active'),
(9, 'Trench', '09', 'TR', 'Active'),
(10, 'Top', '10', 'TP', 'Active'),
(11, 'Belt', '11', 'BE', 'Active'),
(12, 'Suit', '12', 'SU', 'Active'),
(13, 'Scarf', '13', 'SC', 'Active'),
(14, 'Coat', '14', 'C', 'Active'),
(15, 'Sweater', '15', 'SW', 'Active'),
(16, 'Jacket', '16', 'JK', 'Active'),
(17, 'Skirt', '17', 'SK', 'Active'),
(18, 'Vest', '18', 'V', 'Active'),
(19, 'Tie', '19', 'T', 'Active'),
(20, 'Gently Used Mens Underwear', '20', 'GUMW', 'Active'),
(21, 'Mens swimsuit', '21', 'MNSWM', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `ClothingRequest`
--

DROP TABLE IF EXISTS `ClothingRequest`;
CREATE TABLE IF NOT EXISTS `ClothingRequest` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `RequesterNetid` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `RequesterPhone` varchar(12) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `RequesterLastName` varchar(35) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `RequesterFirstName` varchar(35) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `RequestedGender` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `RequestedArticleType` int(5) NOT NULL,
  `RequestedColor1` int(5) NOT NULL,
  `RequestedColor2` int(5) DEFAULT NULL,
  `RequestedSize` varchar(20) NOT NULL,
  `RequestedBrand` varchar(30) NOT NULL,
  `Status` enum('Pending','Fulfilled','Removed') NOT NULL DEFAULT 'Pending',
  `FulfilItemBarcode` varchar(8) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `RequestMadeDate` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `RequestFulfilledDate` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Color`
--

DROP TABLE IF EXISTS `Color`;
CREATE TABLE IF NOT EXISTS `Color` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `Description` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `BarcodePrefix` varchar(5) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `AlphaCode` varchar(5) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `Status` enum('Active','Inactive') CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL DEFAULT 'Active',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `BarcodePrefix` (`BarcodePrefix`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `Color`
--

INSERT INTO `Color` (`ID`, `Description`, `BarcodePrefix`, `AlphaCode`, `Status`) VALUES
(1, 'Black', '01', 'BK', 'Active'),
(2, 'Blue', '02', 'BL', 'Active'),
(3, 'Brown', '03', 'BR', 'Active'),
(4, 'Beige', '04', 'BE', 'Active'),
(5, 'Grey', '05', 'GR', 'Active'),
(6, 'White', '06', 'WH', 'Active'),
(7, 'Pink', '07', 'P', 'Active'),
(8, 'Red', '08', 'R', 'Active'),
(9, 'Green', '09', 'G', 'Active'),
(10, 'Cream', '10', 'CR', 'Active'),
(11, 'Teal', '11', 'TE', 'Active'),
(12, 'Navy', '12', 'NV', 'Active'),
(13, 'Purple', '13', 'PR', 'Active'),
(14, 'Maroon', '14', 'M', 'Active'),
(15, 'Tan', '15', 'TA', 'Active'),
(16, 'Orange', '16', 'OR', 'Active'),
(17, 'Yellow', '17', 'Y', 'Active'),
(18, 'More than 4 colors', '18', '4+C', 'Active'),
(19, 'Print', '19', 'PN', 'Active'),
(20, 'Pin Stripes', '20', 'PS', 'Active'),
(21, 'Patterns', '21', 'PA', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
CREATE TABLE IF NOT EXISTS `Inventory` (
  `Barcode` varchar(8) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `Gender` varchar(5) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `Size` varchar(5) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `ArticleType` int(5) NOT NULL,
  `Color1` int(5) NOT NULL,
  `Color2` int(5) DEFAULT NULL,
  `Brand` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `Notes` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `Status` enum('Donated','Received','Removed') NOT NULL DEFAULT 'Donated',
  `DonorLastName` varchar(35) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `DonorFirstName` varchar(35) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `DonorPhone` varchar(12) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `DonorEmail` varchar(20) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `ReceiverNetid` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `ReceiverLastName` varchar(35) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `ReceiverFirstName` varchar(35) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `DateDonated` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `DateTaken` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`Barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
