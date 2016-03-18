-- phpMyAdmin SQL Dump
-- version 4.1.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 14, 2016 at 09:59 PM
-- Server version: 5.1.71-community-log
-- PHP Version: 5.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pitected`
--
CREATE DATABASE IF NOT EXISTS `pitected`;
USE `pitected`;
-- --------------------------------------------------------

--
-- Table structure for table `cameras`
--

CREATE TABLE IF NOT EXISTS `cameras` (
  `id` int(11) NOT NULL,
  `camera_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `camera_log`
--

CREATE TABLE IF NOT EXISTS `camera_log` (
  `id` int(11) NOT NULL,
  `camera_id` int(11) DEFAULT NULL,
  `path` varchar(45) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `camera_fk_idx` (`camera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `door`
--

CREATE TABLE IF NOT EXISTS `door` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `status` int(1) NOT NULL,
  `system_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `door`
--

INSERT INTO `door` (`id`, `name`, `status`, `system_id`) VALUES
(1, 'Door 1', 0, 1),
(2, 'Door 2', 0, 1),
(3, 'Door 3', 0, 1),
(4, 'Door 4', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `door_log`
--

CREATE TABLE IF NOT EXISTS `door_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `door_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `door_fk_idx` (`door_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `door_log`
--

INSERT INTO `door_log` (`id`, `door_id`, `status`, `timestamp`) VALUES
(1, 1, 0, '2015-12-01 06:00:00'),
(2, 2, 1, '2015-12-02 06:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `motion`
--

CREATE TABLE IF NOT EXISTS `motion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `system_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `motion`
--

INSERT INTO `motion` (`id`, `name`, `status`, `system_id`) VALUES
(1, 'Motion 1', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `motion_log`
--

CREATE TABLE IF NOT EXISTS `motion_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `motion_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `motion_fk_idx` (`motion_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `motion_log`
--

INSERT INTO `motion_log` (`id`, `motion_id`, `status`, `timestamp`) VALUES
(1, 1, 1, '2015-12-05 06:22:46'),
(2, 1, 0, '2015-12-03 06:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `privileges`
--

CREATE TABLE IF NOT EXISTS `privileges` (
  `id` int(11) NOT NULL,
  `privilege_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `privileges`
--

INSERT INTO `privileges` (`id`, `privilege_name`) VALUES
(0, 'admin'),
(1, 'can_arm'),
(2, 'can_disarm');

-- --------------------------------------------------------

--
-- Table structure for table `system`
--

CREATE TABLE IF NOT EXISTS `system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staticIP` varchar(15) NOT NULL,
  `passphrase` char(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `system`
--

INSERT INTO `system` (`id`, `staticIP`, `passphrase`, `name`, `status`) VALUES
(1, '192.168.1.3', 'phassphrase', 'system1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `system_log`
--

CREATE TABLE IF NOT EXISTS `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `system_fk_idx` (`system_id`),
  KEY `user_fk_idx` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `system_log`
--

INSERT INTO `system_log` (`id`, `system_id`, `user_id`, `status`, `timestamp`) VALUES
(1, 1, 1, 1, '2015-11-19 01:49:56'),
(2, 1, 1, 0, '2015-12-03 06:00:00'),
(3, 1, 2, 1, '2015-12-05 06:44:44');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` char(32) NOT NULL,
  `keyCode` varchar(32) NOT NULL,
  `RFIDCode` varchar(10) NOT NULL,
  `system_id` int(11) NOT NULL,
  `session` varchar(45) NOT NULL,
  `gcm_token` varchar(155) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `keyCode`, `RFIDCode`, `system_id`, `session`, `gcm_token`) VALUES
(1, 'margo', '805a1f1ed2fc112568447dc6e170c52a', 'e10adc3949ba59abbe56e057f20f883e', '', 1, '56d07788d2684', 'cGOKvGiNTBQ:APA91bHtetf6NqV30MOODSvZyikKeEz0nZsvRKlNtmF7NU-dFoGRQcUIfK-Dsc4n2KB5_buiVub4nP6yMjr2k-ECazA6ec-0fQNOQ4tZ2_ClvzNG08uwPv7Frjc9r32oMGOL4veQm5Bd'),
(2, 'test2', 'password2', '6c44e5cd17f0019c64b042e4a745412a', '', 1, '', ''),
(3, 'android', 'studio', '', '', 0, '', ''),
(4, 'android', 'studio', '', '', 1, '1', '');

-- --------------------------------------------------------

--
-- Table structure for table `user_privilege`
--

CREATE TABLE IF NOT EXISTS `user_privilege` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `privilege_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `privilege_fk_idx` (`privilege_id`),
  KEY `user_fk_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
