-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2019 at 07:41 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarymanagemnetsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `AID` int(255) NOT NULL,
  `AuthorName` varchar(255) NOT NULL,
  `Contact` varchar(255) NOT NULL,
  `Address` text NOT NULL,
  `AuthorImage` mediumblob NOT NULL,
  `SubmitDate` date NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`AID`, `AuthorName`, `Contact`, `Address`, `AuthorImage`, `SubmitDate`, `RID`) VALUES
(1, 'Majid Tunio', '234234324', 'Karachi', '', '2019-10-11', 1420);

-- --------------------------------------------------------

--
-- Table structure for table `bookpublisher`
--

CREATE TABLE `bookpublisher` (
  `PID` int(255) NOT NULL,
  `PublisherName` varchar(255) NOT NULL,
  `Description` text NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Contact` varchar(255) NOT NULL,
  `Address` text NOT NULL,
  `SubmitDate` date NOT NULL,
  `Image` mediumblob NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookpublisher`
--

INSERT INTO `bookpublisher` (`PID`, `PublisherName`, `Description`, `Email`, `Contact`, `Address`, `SubmitDate`, `Image`, `RID`) VALUES
(1, 'JobME.pk', 'This is book publishing company', 'info@jobme.pk', '1232121', 'Hyderabad', '2019-10-11', '', 1420);

-- --------------------------------------------------------

--
-- Table structure for table `bookrental`
--

CREATE TABLE `bookrental` (
  `BID` int(255) NOT NULL,
  `RentIssueDate` date NOT NULL,
  `RentExpiryDate` date NOT NULL,
  `RentReservedDate` date NOT NULL,
  `GivenToRollID` int(255) NOT NULL,
  `GivenToEmail` varchar(255) NOT NULL,
  `GivenToContact` varchar(255) NOT NULL,
  `GivenToAddress` text NOT NULL,
  `SubmitDate` date NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookrental`
--

INSERT INTO `bookrental` (`BID`, `RentIssueDate`, `RentExpiryDate`, `RentReservedDate`, `GivenToRollID`, `GivenToEmail`, `GivenToContact`, `GivenToAddress`, `SubmitDate`, `RID`) VALUES
(1, '2019-10-01', '2019-10-31', '2019-10-31', 1417, 'MajidPalpu@Gmail.con', '03313437605', 'Shahdadkot', '2019-10-01', 1417),
(2, '2019-11-01', '2019-10-09', '2019-10-16', 1418, 'KamranKhan@Gmail.con', '044464979', 'Hyderabad', '2019-10-01', 1418),
(3, '2019-11-01', '2019-10-16', '2019-10-16', 1418, 'KamranKhan@Gmail.con', '044464979', 'Hyderabad', '2019-10-01', 1418);

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `BID` int(255) NOT NULL,
  `BookTitle` varchar(255) NOT NULL,
  `BookType` varchar(255) NOT NULL,
  `Copies` int(255) NOT NULL,
  `Description` text NOT NULL,
  `BookImage` mediumblob NOT NULL,
  `SubmitDate` int(255) NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`BID`, `BookTitle`, `BookType`, `Copies`, `Description`, `BookImage`, `SubmitDate`, `RID`) VALUES
(1, 'Die In Ocean', 'Story', 10, 'This is an story of a caveman who lived to die in ocean', '', 20191011, 1420);

-- --------------------------------------------------------

--
-- Table structure for table `bookstock`
--

CREATE TABLE `bookstock` (
  `BID` int(255) NOT NULL,
  `AvailableBooks` int(255) NOT NULL,
  `IssuedBooks` int(255) NOT NULL,
  `ReturnBooks` int(255) NOT NULL,
  `SubmitDate` int(255) NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `CID` int(11) NOT NULL,
  `CagegoryName` varchar(255) NOT NULL,
  `Description` text NOT NULL,
  `SubmitDate` date NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `BID` int(255) NOT NULL,
  `DepartmentName` varchar(255) NOT NULL,
  `Description` text NOT NULL,
  `SubmitDate` date NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `SID` int(255) NOT NULL,
  `RollID` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `FatherName` varchar(255) NOT NULL,
  `FatherOccupasion` text NOT NULL,
  `CNIC` varchar(255) NOT NULL,
  `Religion` varchar(255) NOT NULL,
  `GaurdianName` varchar(255) NOT NULL,
  `GaurdianCNIC` varchar(255) NOT NULL,
  `GaurdianContact` varchar(255) NOT NULL,
  `GaurdianOccupassion` text NOT NULL,
  `Contact` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Address` text NOT NULL,
  `Landline` varchar(255) NOT NULL,
  `Department` varchar(255) NOT NULL,
  `Subjects` varchar(255) NOT NULL,
  `Session` varchar(255) NOT NULL,
  `JionDate` date NOT NULL,
  `CurrentSemester` int(255) NOT NULL,
  `TotalSemester` int(255) NOT NULL,
  `Image` mediumblob NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`SID`, `RollID`, `Name`, `FatherName`, `FatherOccupasion`, `CNIC`, `Religion`, `GaurdianName`, `GaurdianCNIC`, `GaurdianContact`, `GaurdianOccupassion`, `Contact`, `Email`, `Address`, `Landline`, `Department`, `Subjects`, `Session`, `JionDate`, `CurrentSemester`, `TotalSemester`, `Image`, `RID`) VALUES
(1, 1417, 'Majid Hussain', 'Manzoor Ahmed', 'Dry Cleaning Factory', '13123-123123-13', 'Islam', 'Manzoor Ahmed', '2343242-24234-24', '45454545', 'Dry Cleaner', '232424324', 'asd@adsa.com', 'Shahdakot', '074401334', 'Computer Science', 'Programming, Operating System, Maths', '2019-10-10', '2019-10-10', 4, 8, '', 1417),
(2, 1418, 'Kamran Qutrio', 'Khadam Hussain', 'SDM', '13123-123123-13', 'Islam', 'Khadam Hussain', '2343242-24234-24', '45454545', 'SDM', '232424324', 'KamranQutrio@aGmail.com', 'Hyderabad', '074401334', 'BBA', 'Accounting,Fiance,etc', '2019-10-10', '2019-10-10', 6, 8, '', 1418);

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `TID` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `FatherName` varchar(255) NOT NULL,
  `Department` varchar(255) NOT NULL,
  `Subjects` varchar(255) NOT NULL,
  `Contact` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Address` text NOT NULL,
  `JionDate` date NOT NULL,
  `Image` mediumblob NOT NULL,
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UID` int(255) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Address` text NOT NULL,
  `Role` int(255) NOT NULL,
  `JionDate` date NOT NULL,
  `LeftDate` date NOT NULL,
  `Lastsession` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `RID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`AID`);

--
-- Indexes for table `bookpublisher`
--
ALTER TABLE `bookpublisher`
  ADD PRIMARY KEY (`PID`);

--
-- Indexes for table `bookrental`
--
ALTER TABLE `bookrental`
  ADD PRIMARY KEY (`BID`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`BID`);

--
-- Indexes for table `bookstock`
--
ALTER TABLE `bookstock`
  ADD PRIMARY KEY (`BID`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`CID`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`BID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`SID`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`TID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY `AID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bookpublisher`
--
ALTER TABLE `bookpublisher`
  MODIFY `PID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bookrental`
--
ALTER TABLE `bookrental`
  MODIFY `BID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `BID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bookstock`
--
ALTER TABLE `bookstock`
  MODIFY `BID` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `CID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `BID` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `SID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `TID` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UID` int(255) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
