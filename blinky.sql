-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2024 at 03:33 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blinky`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(2) NOT NULL,
  `nm_admin` varchar(25) NOT NULL,
  `u_admin` varchar(50) NOT NULL,
  `pw_admin` varchar(255) NOT NULL,
  `tgl_ditambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `tgl_diupdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nm_admin`, `u_admin`, `pw_admin`, `tgl_ditambah`, `tgl_diupdate`) VALUES
(26, 'Edwin Setiawan, S.Kom.', 'edwin_setiawan', 'Sunshine321!', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(27, 'Franco Valentino, S.E.', 'franco_valentino', 'Summer2024', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(28, 'Susi Chandri, S.Ak.', 'susi_chandri', 'Fallout2319', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `id_dosen` int(6) NOT NULL,
  `id_prodi` int(6) NOT NULL,
  `nm_dosen` varchar(100) NOT NULL,
  `nidn` int(10) NOT NULL,
  `id_jabatan` int(6) NOT NULL,
  `u_dosen` varchar(50) NOT NULL,
  `pw_dosen` varchar(255) NOT NULL,
  `tgl_ditambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `tgl_diupdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`id_dosen`, `id_prodi`, `nm_dosen`, `nidn`, `id_jabatan`, `u_dosen`, `pw_dosen`, `tgl_ditambah`, `tgl_diupdate`) VALUES
(101, 61209, 'Ir. Yudi Haliman, S.H., M.H., M.M., FCBArb., FIIArb.', 1108096601, 1001, 'yudi_haliman', 'yudi_haliman1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(102, 59201, 'William Sandy, Ph.D.', 1112048701, 2001, 'william_sandy', 'william_sandy1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(103, 61209, 'Beny, S.E., M.M.', 1110047401, 2002, 'beny', 'beny1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(104, 59201, 'Brian Sebastian Salim, S.Kom., M.T.I.', 1121119102, 3001, 'brian_salim', 'brian_salim1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(105, 61209, 'William Wendy Ary, S.E., M.Sc.', 709019401, 3002, 'william_ary', 'william_ary1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(106, 94202, 'Raymond Wahyudi, S.E., M.M.', 1107098902, 3003, 'raymond_wahyudi', 'raymond_wahyudi1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(107, 94202, 'Dr. Marvello Yang, M.M., M.E.', 1104078901, 4001, 'marvello_yang', 'marvello_yang1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(108, 59201, 'Daniel Oktodeli Sihombing, S.T., M.Kom.', 1127108604, 4002, 'daniel_sihombing', 'daniel_sihombing1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(109, 59201, 'Filbert Ivander, S.Kom., M.T.I.', 1106109301, 5001, 'filbert_ivander', 'filbert_ivander1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(110, 59201, 'Yuricha, S.T., M.T.I.', 1102099502, 5001, 'yuricha', 'yuricha1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(111, 59201, 'Alex Cahyadi, B.Comp., M.Inf.Tech.', 1120048502, 5001, 'alex_cahyadi', 'alex_cahyadi1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(112, 61209, 'Fitri Yutika, S.E., M.Si.', 1113039401, 5002, 'fitri_yutika', 'fitri_yutika1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(113, 61209, 'Fry Melda Saragih, S.Ak., M.M.', 1118029501, 5002, 'fry_saragih', 'fry_saragih1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(114, 61209, 'Petrus Sentoso Tan, S.E., M.M.', 1124046801, 5002, 'petrus_tan', 'petrus_tan1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(115, 61209, 'Febiwenesya Forever, S.E., M.M.', 1117029301, 5002, 'febiwenesya_forever', 'febiwenesya_forever1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(116, 94202, 'Ratnawati, S.E., M.M.', 1104049601, 5003, 'ratnawati', 'ratnawati1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(117, 94202, 'Alip Hanoky, S.E., M.M.', 1127117001, 5003, 'alip_hanoky', 'alip_hanoky1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(118, 94202, 'Sebastian, S.E., M.M.', 1124116701, 5003, 'sebastian_liak', 'sebastian_liak1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(119, 94202, 'Afni Eliana Saragih, S.E., M.Si.', 0, 5003, 'afni_saragih', 'afni_saragih1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(120, 78012, 'Riky Vicaldo, S.E., M.M.', 0, 5004, 'riky_vicaldo', 'riky_vicaldo1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(121, 78012, 'Eric Prakarsa Putra, S.Kom., M.T.I.', 0, 5004, 'eric_putra', 'eric_putra1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(122, 78012, 'Marina Veridiana Nggai, S.H., M.H.', 0, 5004, 'marina_nggai', 'marina_nggai1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(123, 78012, 'Rolah Sri Rejeki Situmorang, S.Pd., M.Pd.', 0, 5004, 'rolah_situmorang', 'rolah_situmorang1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(124, 78012, 'Seniman, S.Pd., M.Div.', 0, 5004, 'seniman', 'seniman1', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(125, 78012, 'Huidiantono, S.Pd., M.Pd.', 0, 5004, 'huidiantono', 'huidiantono1', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `jabatan`
--

CREATE TABLE `jabatan` (
  `id_jabatan` int(6) NOT NULL,
  `nm_jabatan` varchar(50) NOT NULL,
  `tgl_ditambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `tgl_diupdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jabatan`
--

INSERT INTO `jabatan` (`id_jabatan`, `nm_jabatan`, `tgl_ditambah`, `tgl_diupdate`) VALUES
(1001, 'Rektor', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2001, 'Wakil Rektor Bidang Akademik dan Kemahasiswaan', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2002, 'Wakil Rektor Bidang SDM dan Alumni', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3001, 'Kepala Program Studi Sistem & Teknologi Informasi', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3002, 'Kepala Program Studi Bisnis Digital', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3003, 'Kepala Program Studi Kewirausahaan', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(4001, 'Kepala Lembaga Penelitian & Pengabdian Kepada Masy', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(4002, 'Kepala Lembaga Penjamin Mutu', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5001, 'Dosen Sistem & Teknologi Informasi', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5002, 'Dosen Bisnis Digital', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5003, 'Dosen Kewirausahaan', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5004, 'Dosen Umum', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id_mahasiswa` int(10) NOT NULL,
  `id_kelas` int(5) NOT NULL,
  `id_prodi` int(5) NOT NULL,
  `id_admin` int(2) NOT NULL,
  `nm_mahasiswa` varchar(30) NOT NULL,
  `pw_mahasiswa` varchar(255) NOT NULL,
  `angkatan` int(4) NOT NULL,
  `tgl_ditambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `tgl_diupdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id_mahasiswa`, `id_kelas`, `id_prodi`, `id_admin`, `nm_mahasiswa`, `pw_mahasiswa`, `angkatan`, `tgl_ditambah`, `tgl_diupdate`) VALUES
(22100001, 1, 59201, 26, 'Richard Marcell', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100002, 1, 59201, 26, 'Hazel Dixon', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100004, 1, 59201, 26, 'Darren Steven', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100006, 1, 59201, 26, 'Calvin Tai', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100007, 1, 59201, 26, 'Steven', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100008, 1, 59201, 26, 'Dustin Tiovino', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100009, 1, 59201, 26, 'Nieven Carlin', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100010, 1, 59201, 26, 'Fredella Cornelia Chok', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100012, 1, 59201, 26, 'Clay Aiken', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100013, 1, 59201, 26, 'Nikolas Liyantara', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100014, 1, 59201, 26, 'Charles Charity Scorpiono', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100015, 2, 59201, 26, 'Suhendri', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100016, 2, 59201, 26, 'Steve Ariyanto', '2022', 10, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100017, 2, 59201, 26, 'Romanus Saga Manao', '2022', 11, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100018, 2, 59201, 26, 'Fendy', '2022', 12, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100019, 1, 59201, 26, 'William', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100020, 2, 59201, 26, 'Julius Kayne', '2022', 13, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100023, 2, 59201, 26, 'Aldio', '2022', 14, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100024, 2, 59201, 26, 'Ella Mariati', '2022', 15, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100026, 1, 59201, 26, 'Erickson Andriano', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100027, 2, 59201, 26, 'Heylen Junila Saputry', '2022', 16, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100030, 2, 59201, 26, 'Hendi Lapenata', '2022', 17, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100031, 2, 59201, 26, 'Kristanto', '2022', 18, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100032, 2, 59201, 26, 'Wendy Yansah', '2022', 19, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100033, 2, 59201, 26, 'Chrisca Lolita', '2022', 20, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100034, 2, 59201, 26, 'Timothy Valentivo', '2022', 21, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100035, 2, 59201, 26, 'Edwin Theonardi', '2022', 22, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100036, 2, 59201, 26, 'Daniel Christ Winata', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100037, 1, 59201, 26, 'Grace Selianda Reva', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100042, 1, 59201, 26, 'William Richnady', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100044, 1, 59201, 26, 'Putu Rangga Deva Mertha', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100045, 1, 59201, 26, 'Oxana Gloria', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22100046, 1, 59201, 26, 'Meliana', '2022', 9, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200001, 3, 61209, 27, 'Hendrik Krisma', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200002, 3, 61209, 27, 'Nico Cristian', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200005, 3, 61209, 27, 'Jimi', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200006, 3, 61209, 27, 'Ivana Angelia', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200008, 3, 61209, 27, 'Fransisco Dede Manuel Endino', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200009, 3, 61209, 27, 'Richel Jayreh El Rapha S.', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200010, 3, 61209, 27, 'Steven', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200011, 3, 61209, 27, 'Buntoro Suakan', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200013, 3, 61209, 27, 'Doni Purnama', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200014, 3, 61209, 27, 'Daniel Fonseca', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200016, 3, 61209, 27, 'Cristina', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200017, 3, 61209, 27, 'Stiven', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200018, 3, 61209, 27, 'Stanley Phangkawira', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200019, 3, 61209, 27, 'Febriani Novianty', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200021, 3, 61209, 27, 'Kimberly Nathaneil', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200022, 3, 61209, 27, 'Evelyn Aurelia', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200023, 3, 61209, 27, 'Vira Andriani', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200025, 3, 61209, 27, 'Memed Hasan', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200027, 3, 61209, 27, 'Ester Chandra', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200028, 3, 61209, 27, 'Yopita', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200029, 3, 61209, 27, 'Valentino Chandra', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200030, 3, 61209, 27, 'Joel Imanuel', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200031, 3, 61209, 27, 'Chintya Natalie', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200032, 3, 61209, 27, 'Meisy', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200033, 3, 61209, 27, 'Adiel Jonathan Immanuel Purba', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200034, 3, 61209, 27, 'Vinsen Tandra', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200036, 3, 61209, 27, 'Jessica Sentoso', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200037, 3, 61209, 27, 'Mathew Abel Nugroho', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200038, 3, 61209, 27, 'Vincent Williamson', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22200041, 3, 61209, 27, 'Ellyca Rutaningsih', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300001, 4, 94202, 28, 'Jonathan Kenny Juen', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300002, 4, 94202, 28, 'Velissa Alvincia', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300003, 4, 94202, 28, 'Vinsensius', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300004, 4, 94202, 28, 'Nelly', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300005, 4, 94202, 28, 'Joni Riawan', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300006, 4, 94202, 28, 'Farrell Ionwyn Eduardo', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300007, 4, 94202, 28, 'Lina', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300008, 4, 94202, 28, 'Noviansius', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300009, 4, 94202, 28, 'Cellica Jolie', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300011, 4, 94202, 28, 'Ryan Putra Panjaya', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300012, 4, 94202, 28, 'Calvin Ryandra', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300015, 4, 94202, 28, 'Markus Aodi', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300016, 4, 94202, 28, 'Elda', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300017, 4, 94202, 28, 'Ferdy Widjaya', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300019, 4, 94202, 28, 'Noviana Krisnawati', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22300020, 4, 94202, 28, 'Dyson Jonathan', '2022', 23, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `prodi`
--

CREATE TABLE `prodi` (
  `id_prodi` int(5) NOT NULL,
  `nm_prodi` varchar(30) NOT NULL,
  `tgl_ditambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `tgl_diupdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prodi`
--

INSERT INTO `prodi` (`id_prodi`, `nm_prodi`, `tgl_ditambah`, `tgl_diupdate`) VALUES
(59201, 'Sistem dan Teknologi Informasi', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(61209, 'Bisnis Digital', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(94202, 'Kewirausahaan', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`id_dosen`);

--
-- Indexes for table `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id_jabatan`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id_mahasiswa`);

--
-- Indexes for table `prodi`
--
ALTER TABLE `prodi`
  ADD PRIMARY KEY (`id_prodi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `dosen`
--
ALTER TABLE `dosen`
  MODIFY `id_dosen` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- AUTO_INCREMENT for table `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `id_jabatan` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5005;

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id_mahasiswa` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22300021;

--
-- AUTO_INCREMENT for table `prodi`
--
ALTER TABLE `prodi`
  MODIFY `id_prodi` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94203;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
