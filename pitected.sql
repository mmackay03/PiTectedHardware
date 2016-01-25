-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2016 at 05:59 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pitected`
--

-- --------------------------------------------------------

--
-- Table structure for table `cameras`
--

CREATE TABLE IF NOT EXISTS `cameras` (
  `id` int(11) NOT NULL,
  `camera_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `camera_log`
--

CREATE TABLE IF NOT EXISTS `camera_log` (
  `id` int(11) NOT NULL,
  `camera_id` int(11) DEFAULT NULL,
  `path` varchar(45) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `door`
--

CREATE TABLE IF NOT EXISTS `door` (
`id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `status` int(1) NOT NULL,
  `system_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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
`id` int(11) NOT NULL,
  `door_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
`id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `system_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

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
`id` int(11) NOT NULL,
  `motion_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
  `privilege_name` varchar(45) DEFAULT NULL
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
`id` int(11) NOT NULL,
  `staticIP` varchar(15) NOT NULL,
  `passphrase` char(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

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
`id` int(11) NOT NULL,
  `system_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

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
`id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` char(32) NOT NULL,
  `keyCode` varchar(32) NOT NULL,
  `RFIDCode` varchar(10) NOT NULL,
  `system_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `keyCode`, `RFIDCode`, `system_id`) VALUES
(1, 'margo', 'password', 'e10adc3949ba59abbe56e057f20f883e', '', 1),
(2, 'test2', 'password2', '6c44e5cd17f0019c64b042e4a745412a', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_privilege`
--

CREATE TABLE IF NOT EXISTS `user_privilege` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `privilege_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cameras`
--
ALTER TABLE `cameras`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `camera_log`
--
ALTER TABLE `camera_log`
 ADD PRIMARY KEY (`id`), ADD KEY `camera_fk_idx` (`camera_id`);

--
-- Indexes for table `door`
--
ALTER TABLE `door`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `door_log`
--
ALTER TABLE `door_log`
 ADD PRIMARY KEY (`id`), ADD KEY `door_fk_idx` (`door_id`);

--
-- Indexes for table `motion`
--
ALTER TABLE `motion`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `motion_log`
--
ALTER TABLE `motion_log`
 ADD PRIMARY KEY (`id`), ADD KEY `motion_fk_idx` (`motion_id`);

--
-- Indexes for table `privileges`
--
ALTER TABLE `privileges`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `system`
--
ALTER TABLE `system`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `system_log`
--
ALTER TABLE `system_log`
 ADD PRIMARY KEY (`id`), ADD KEY `system_fk_idx` (`system_id`), ADD KEY `user_fk_idx` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_privilege`
--
ALTER TABLE `user_privilege`
 ADD PRIMARY KEY (`id`), ADD KEY `privilege_fk_idx` (`privilege_id`), ADD KEY `user_fk_idx` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `door`
--
ALTER TABLE `door`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `door_log`
--
ALTER TABLE `door_log`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `motion`
--
ALTER TABLE `motion`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `motion_log`
--
ALTER TABLE `motion_log`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `system`
--
ALTER TABLE `system`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `system_log`
--
ALTER TABLE `system_log`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `camera_log`
--
ALTER TABLE `camera_log`
ADD CONSTRAINT `camera_fk` FOREIGN KEY (`camera_id`) REFERENCES `mydb`.`cameras` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `door_log`
--
ALTER TABLE `door_log`
ADD CONSTRAINT `door_fk` FOREIGN KEY (`door_id`) REFERENCES `door` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `motion_log`
--
ALTER TABLE `motion_log`
ADD CONSTRAINT `motion_fk` FOREIGN KEY (`motion_id`) REFERENCES `motion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `system_log`
--
ALTER TABLE `system_log`
ADD CONSTRAINT `sys_fk` FOREIGN KEY (`system_id`) REFERENCES `system` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_privilege`
--
ALTER TABLE `user_privilege`
ADD CONSTRAINT `privilege_fk` FOREIGN KEY (`privilege_id`) REFERENCES `mydb`.`privileges` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
