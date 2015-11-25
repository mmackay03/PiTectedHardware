-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2015 at 08:19 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pitected`
--
CREATE DATABASE IF NOT EXISTS `pitected` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `pitected`;

-- --------------------------------------------------------

--
-- Table structure for table `door`
--

CREATE TABLE IF NOT EXISTS `door` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `status` binary(1) NOT NULL,
  `system_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `door`
--

INSERT INTO `door` (`id`, `name`, `status`, `system_id`) VALUES
(1, 'Door 1', '0', 1),
(2, 'Door 2', '0', 1),
(3, 'Door 3', '0', 1),
(4, 'Door 4', '0', 1);

-- --------------------------------------------------------

--
-- Table structure for table `door_log`
--

CREATE TABLE IF NOT EXISTS `door_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `door_id` int(11) NOT NULL,
  `status` binary(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `motion`
--

CREATE TABLE IF NOT EXISTS `motion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `status` binary(1) NOT NULL DEFAULT '0',
  `system_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `motion`
--

INSERT INTO `motion` (`id`, `name`, `status`, `system_id`) VALUES
(1, 'Motion 1', '0', 1);

-- --------------------------------------------------------

--
-- Table structure for table `motion_log`
--

CREATE TABLE IF NOT EXISTS `motion_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `motion_id` int(11) NOT NULL,
  `status` binary(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `notification_settings`
--

CREATE TABLE IF NOT EXISTS `notification_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `door_notification` binary(1) NOT NULL,
  `motion_notification` binary(1) NOT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `system`
--

CREATE TABLE IF NOT EXISTS `system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staticIP` varchar(15) NOT NULL,
  `passphrase` char(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `status` binary(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `system`
--

INSERT INTO `system` (`id`, `staticIP`, `passphrase`, `name`, `status`) VALUES
(1, '192.168.1.3', 'phassphrase', 'system1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `system_log`
--

CREATE TABLE IF NOT EXISTS `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` binary(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `system_log`
--

INSERT INTO `system_log` (`id`, `system_id`, `user_id`, `status`, `timestamp`) VALUES
(1, 1, 1, '1', '2015-11-19 01:49:56');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` char(32) NOT NULL,
  `keyCode` varchar(6) NOT NULL,
  `RFIDCode` varchar(10) NOT NULL,
  `system_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `keyCode`, `RFIDCode`, `system_id`) VALUES
(1, 'margo', 'password', '123456', '', 1),
(2, 'test2', 'password2', '987654', '', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
