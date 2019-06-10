-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 09, 2019 at 08:54 PM
-- Server version: 10.3.14-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;



--
-- Table structure for table `kargolar`
--

CREATE TABLE `kargolar` (
  `Id` int(11) NOT NULL,
  `gönderen` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `gönderen_tel` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `alıcı` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `alıcı_tel` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `alıcı_adres` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `verilis_tarihi` timestamp NULL DEFAULT current_timestamp(),
  `durum` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `kurye` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `kargolar`
--

INSERT INTO `kargolar` (`Id`, `gönderen`, `gönderen_tel`, `alıcı`, `alıcı_tel`, `alıcı_adres`, `verilis_tarihi`, `durum`, `kurye`) VALUES
(4, 'mus', '134134314', 'tan', '134134134', 'ANKARA', '2019-01-01 18:12:54', 'Teslim', ''),
(5, 'Harun', '242354325', 'mst', '3353222', 'KARABUK', '2019-03-02 12:45:27', 'Teslim', ''),
(6, 'mustafa', '134135', 'mehmet', '2452525', 'KARABUK', '2019-04-13 13:38:50', 'Subede', ''),
(8, 'selin', '1542515', 'mustafa', '23562536', 'ANKARA', '2019-04-16 16:36:09', 'Kuryede', ''),
(9, 'murat', '1542515', 'tan', '23562536', 'ANKARA', '2019-04-26 23:27:15', 'Subede', ''),
(10, 'sezer', '134531', 'tan', '425245', 'ANKARA', '2019-04-26 23:50:30', 'Subede', ''),
(11, 'sezer', '1542515', 'tan', '23562536', 'ANKARA', '2019-04-27 00:19:58', 'Kuryede', 'kurye3'),
(14, 'tan', '12451245', 'murat', '132451435', 'KARABUK', '2019-04-28 17:28:06', 'Subede', 'kurye4'),
(15, 'tan', '12451245', 'murat', '132451435', 'KARABUK', '2019-04-28 18:43:02', 'Subede', 'kurye4');

-- --------------------------------------------------------

--
-- Table structure for table `kargo_istek`
--

CREATE TABLE `kargo_istek` (
  `Id` int(50) NOT NULL,
  `kullanici` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `enlem` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `boylam` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `sehir` varchar(50) CHARACTER SET utf32 COLLATE utf32_turkish_ci NOT NULL,
  `durum` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `konum`
--

CREATE TABLE `konum` (
  `Id` int(11) NOT NULL,
  `kurye` varchar(20) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `enlem` double NOT NULL,
  `boylam` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `konum`
--

INSERT INTO `konum` (`Id`, `kurye`, `enlem`, `boylam`) VALUES
(1, 'kurye1', 41.2553985, 32.6798498),
(4, 'kurye3', 37.421998333333335, -122.08400000000002),
(21, 'kurye4', 37.421998333333335, -122.08400000000002),
(22, 'dsgf', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `kullanicilar`
--

CREATE TABLE `kullanicilar` (
  `Id` int(11) NOT NULL,
  `adsoy` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `email` varchar(40) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `sifre` varchar(15) COLLATE utf8_turkish_ci NOT NULL,
  `yetki` varchar(10) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `kullanicilar`
--

INSERT INTO `kullanicilar` (`Id`, `adsoy`, `email`, `sifre`, `yetki`) VALUES
(1, 'Tansel Şarman', 'tansel@hotmail', '1234', 'Admin'),
(2, 'tansel şarman', 'tansel1907@hotmail.com', '1234', 'Şube Çalış'),
(5, 'calisan1', 'calısan@hotmail.com', '1234', 'Sube Çalış');

-- --------------------------------------------------------

--
-- Table structure for table `kurye`
--

CREATE TABLE `kurye` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `yetki` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `country` varchar(50) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `kurye`
--

INSERT INTO `kurye` (`id`, `username`, `password`, `email`, `yetki`, `country`) VALUES
(28, 'kurye1', '81dc9bdb52d04dc20036dbd8313ed055', 'kurye@hotmail.com', 'Kurye', ''),
(30, 'kurye2', '1234', 'kurye2@hotmail.com', 'Kurye', ''),
(31, 'kurye3', '1234', 'kurye3@hotmail.com', 'Kurye', 'ANKARA'),
(32, 'kurye4', '1234', 'kurye4@hotmail.com', 'Kurye', 'KARABUK'),
(35, 'dsgf', '245425', 'sdghsfgh', 'Kurye', 'KARABUK');

-- --------------------------------------------------------

--
-- Table structure for table `mesajlar`
--

CREATE TABLE `mesajlar` (
  `Id` int(11) NOT NULL,
  `gonderen` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `alici` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `mesaj` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `deger` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mesajlar`
--

INSERT INTO `mesajlar` (`Id`, `gonderen`, `alici`, `mesaj`, `deger`) VALUES
(20, 'kurye3', 'tan', 'Kargonuzu 1 saat içinde getireceğiz müsait misiniz?', 'goruldu'),
(22, 'tan', 'kurye3', 'Müsaitim bekliyorum...', 'goruldu');

-- --------------------------------------------------------

--
-- Table structure for table `musteriler`
--

CREATE TABLE `musteriler` (
  `id` int(11) NOT NULL,
  `username` varchar(100) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `password` varchar(55) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `yetki` varchar(55) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `musteriler`
--

INSERT INTO `musteriler` (`id`, `username`, `password`, `email`, `yetki`, `address`, `phone`) VALUES
(15, 'müsteri', '81dc9bdb52d04dc20036dbd8313ed055', 'müsteri@hotmail.com', 'Musteri', '', ''),
(16, 'müsteri', '81dc9bdb52d04dc20036dbd8313ed055', 'müsteri@hotmail.com', 'Musteri', '', ''),
(17, 'müsteri2', '81dc9bdb52d04dc20036dbd8313ed055', 'müsteri2@hotmail.com', 'Musteri', '', ''),
(18, 'müsteri3', '81dc9bdb52d04dc20036dbd8313ed055', 'müsteri3@hotmail.com', 'Musteri', '', ''),
(19, 'müsteri4', '81dc9bdb52d04dc20036dbd8313ed055', 'müsteri4@hotmail.com', 'Musteri', '', ''),
(20, 'müsteri', 'ed6032fd0c5638807b6b4be9c9dbc723', 'tansel@hotmail.com', 'Musteri', '', ''),
(21, 'müsteri1', '81dc9bdb52d04dc20036dbd8313ed055', 'tansel@hotmail.com', 'Musteri', '', ''),
(22, 'must1', '81dc9bdb52d04dc20036dbd8313ed055', 'must1@mail.com', 'Musteri', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `ortak`
--

CREATE TABLE `ortak` (
  `id` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `yetki` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL,
  `country` varchar(50) CHARACTER SET utf16 COLLATE utf16_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ortak`
--

INSERT INTO `ortak` (`id`, `username`, `password`, `email`, `yetki`, `country`) VALUES
(35, 'kurye1', '81dc9bdb52d04dc20036dbd8313ed055', 'kurye@hotmail.com', 'Kurye', ''),
(36, 'müsteri', '81dc9bdb52d04dc20036dbd8313ed055', 'müsteri@hotmail.com', 'Musteri', ''),
(51, 'must6', '1234', 'sfhjshfj', 'Musteri', ''),
(52, 'kurye2', '81dc9bdb52d04dc20036dbd8313ed055', 'kurye2@hotmail.com', 'Kurye', ''),
(53, 'kurye2', '1234', 'kurye2@hotmail.com', 'Kurye', ''),
(54, 'sfg', '1234', 'asfg', 'Musteri', ''),
(55, 'hgjgfdhjghj', 'cghjdghjfghcj', 'ghjcgfhjfcghj', 'Musteri', ''),
(56, 'xfhjxhfjc', 'nbmcnmz', 'xvzb', 'Musteri', 'cnvmcnm'),
(57, 'tansel', '1234', 'tanselmail', 'Musteri', 'karabuk'),
(58, 'kurye3', '1234', 'kurye3@hotmail.com', 'Kurye', 'ANKARA'),
(59, 'fgsdfgsfdg', 'fxdghgdfhdfh', 'dhfjghjhgdj', 'Musteri', 'ZONGULDAK'),
(60, 'tan', '1234', 'tan@hotmail.com', 'Musteri', 'KARABUK'),
(63, 'kurye4', '1234', 'kurye4@hotmail.com', 'Kurye', 'KARABUK'),
(64, 'dsgf', '245425', 'sdghsfgh', 'Kurye', 'KARABUK');

-- --------------------------------------------------------

--
-- Table structure for table `sehirler`
--

CREATE TABLE `sehirler` (
  `Id` int(20) NOT NULL,
  `sehir` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sehirler`
--

INSERT INTO `sehirler` (`Id`, `sehir`) VALUES
(1, 'KARABUK'),
(2, 'ANKARA'),
(3, 'KASTAMONU'),
(4, 'ZONGULDAK');

-- --------------------------------------------------------

--
-- Table structure for table `siparisler`
--

CREATE TABLE `siparisler` (
  `Id` int(50) NOT NULL,
  `gonderen` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `gonderen_tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `alici` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `alici_tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `alici_adres` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `verilis_tarihi` timestamp NULL DEFAULT current_timestamp(),
  `durum` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `kurye` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `tan` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nat` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kargolar`
--
ALTER TABLE `kargolar`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `kargo_istek`
--
ALTER TABLE `kargo_istek`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `konum`
--
ALTER TABLE `konum`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `kullanicilar`
--
ALTER TABLE `kullanicilar`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `kurye`
--
ALTER TABLE `kurye`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mesajlar`
--
ALTER TABLE `mesajlar`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `musteriler`
--
ALTER TABLE `musteriler`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ortak`
--
ALTER TABLE `ortak`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sehirler`
--
ALTER TABLE `sehirler`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `siparisler`
--
ALTER TABLE `siparisler`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kargolar`
--
ALTER TABLE `kargolar`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `kargo_istek`
--
ALTER TABLE `kargo_istek`
  MODIFY `Id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `konum`
--
ALTER TABLE `konum`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `kullanicilar`
--
ALTER TABLE `kullanicilar`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `kurye`
--
ALTER TABLE `kurye`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `mesajlar`
--
ALTER TABLE `mesajlar`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `musteriler`
--
ALTER TABLE `musteriler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `ortak`
--
ALTER TABLE `ortak`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `sehirler`
--
ALTER TABLE `sehirler`
  MODIFY `Id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `siparisler`
--
ALTER TABLE `siparisler`
  MODIFY `Id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
