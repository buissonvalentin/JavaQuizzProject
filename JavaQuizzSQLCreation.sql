-- --------------------------------------------------------
-- Host:                         eu-cdbr-azure-west-b.cloudapp.net
-- Server version:               5.5.56-log - MySQL Community Server (GPL)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Data exporting was unselected.
-- Dumping structure for table buisson_valentin.questions
CREATE TABLE IF NOT EXISTS `questions` (
  `type` tinytext,
  `correctAnswer` text,
  `question` text,
  `correctAnswers` text,
  `propositions` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Data exporting was unselected.
-- Dumping structure for table buisson_valentin.results
CREATE TABLE IF NOT EXISTS `results` (
  `user` tinytext,
  `score` int(11) DEFAULT NULL,
  `date` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
