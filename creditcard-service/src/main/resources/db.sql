CREATE DATABASE `creditcard` /*!40100 COLLATE 'latin1_swedish_ci' */

CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `credit_limit` numeric(10) DEFAULT NULL,
  `owner_id` varchar(45) DEFAULT NULL,
  `owner_id_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;



CREATE TABLE `movements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `type` varchar(45) NOT NULL,
  `amount` numeric(10) NOT NULL,
  `creditcard_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `creditcardmovementsFK_idx` (`creditcard_id`),
  CONSTRAINT `creditcardmovementsFK` FOREIGN KEY (`creditcard_id`) REFERENCES `creditcard` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

