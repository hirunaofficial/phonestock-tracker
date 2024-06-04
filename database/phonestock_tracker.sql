-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2024 at 02:29 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `phonestock_tracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `phones`
--

CREATE TABLE `phones` (
                          `id` int(11) NOT NULL,
                          `brand` varchar(255) NOT NULL,
                          `model` varchar(255) NOT NULL,
                          `qty` int(11) NOT NULL,
                          `color` varchar(255) NOT NULL,
                          `ram` int(11) NOT NULL,
                          `storage` int(11) NOT NULL,
                          `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phones`
--

INSERT INTO `phones` (`id`, `brand`, `model`, `qty`, `color`, `ram`, `storage`, `price`) VALUES
                                                                                             (1, 'Samsung', 'Galaxy S21', 50, 'Black', 8, 128, '150000.00'),
                                                                                             (2, 'Apple', 'iPhone 12', 30, 'White', 6, 64, '200000.00'),
                                                                                             (3, 'Xiaomi', 'Redmi Note 10', 40, 'Blue', 6, 128, '80000.00'),
                                                                                             (4, 'OnePlus', 'OnePlus 9', 20, 'Silver', 12, 256, '180000.00'),
                                                                                             (5, 'Realme', 'Realme 8 Pro', 35, 'Yellow', 8, 128, '100000.00'),
                                                                                             (6, 'Huawei', 'P40 Pro', 25, 'Silver', 8, 256, '160000.00'),
                                                                                             (7, 'Oppo', 'Find X3 Pro', 15, 'Black', 12, 256, '190000.00'),
                                                                                             (8, 'Vivo', 'V21', 45, 'Pink', 8, 128, '120000.00'),
                                                                                             (9, 'Google', 'Pixel 5', 10, 'Green', 8, 128, '220000.00'),
                                                                                             (10, 'Nokia', 'Nokia 8.3', 20, 'Blue', 8, 128, '140000.00'),
                                                                                             (11, 'Sony', 'Xperia 5 III', 30, 'Black', 8, 256, '230000.00'),
                                                                                             (12, 'Motorola', 'Moto G100', 25, 'Silver', 8, 128, '90000.00'),
                                                                                             (13, 'LG', 'LG Velvet', 15, 'White', 6, 128, '110000.00'),
                                                                                             (14, 'Asus', 'Zenfone 8', 20, 'Black', 8, 256, '170000.00'),
                                                                                             (15, 'Lenovo', 'Legion Duel 2', 10, 'Red', 16, 512, '250000.00'),
                                                                                             (16, 'HTC', 'HTC Desire 21 Pro', 20, 'Blue', 6, 128, '95000.00'),
                                                                                             (17, 'BlackBerry', 'BlackBerry KEY2', 5, 'Silver', 6, 64, '180000.00'),
                                                                                             (18, 'Microsoft', 'Microsoft Surface Duo', 8, 'White', 8, 256, '300000.00'),
                                                                                             (19, 'ZTE', 'ZTE Axon 30 Ultra', 12, 'Black', 16, 256, '280000.00'),
                                                                                             (20, 'Infinix', 'Infinix Note 10 Pro', 18, 'Purple', 8, 256, '85000.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `phones`
--
ALTER TABLE `phones`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `phones`
--
ALTER TABLE `phones`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
