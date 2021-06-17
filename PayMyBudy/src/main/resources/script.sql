-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le : jeu. 03 juin 2021 à 18:06
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `paymybudy`
--

-- --------------------------------------------------------

--
-- Structure de la table `bankaccount`
--

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `bankaccount`;
SET foreign_key_checks = 1;
CREATE TABLE IF NOT EXISTS `bankaccount` (
  `idaccount` int(11)  AUTO_INCREMENT,
  `FK_iduser` int(11) ,
  `iban` varchar(200) ,
  `date` datetime ,
  PRIMARY KEY (`idaccount`),
  UNIQUE KEY `FK_iduser` (`FK_iduser`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `depositwithdrawal`
--

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `depositwithdrawal`;
SET foreign_key_checks = 1;
CREATE TABLE IF NOT EXISTS `depositwithdrawal` (
  `idoperation` int(11)  AUTO_INCREMENT,
  `date` datetime ,
  `amount` double ,
  `labelmovement` double ,
  PRIMARY KEY (`idoperation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `friends`
--

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `friends`;
SET foreign_key_checks = 1;
CREATE TABLE IF NOT EXISTS `friends` (
  `id` int(11)  AUTO_INCREMENT,
  `id_friend` int(11) ,
  PRIMARY KEY (`id`),
  KEY `id_friend` (`id_friend`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `transfert`
--

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `transfert`;
SET foreign_key_checks = 1;
CREATE TABLE IF NOT EXISTS `transfert` (
  `idTransfert` int(11)  AUTO_INCREMENT,
  `date` datetime ,
  `amount` double ,
  `label` varchar(50) ,
  `FK_idUser` int(11) ,
  `recipient` int(11) ,
  PRIMARY KEY (`idTransfert`),
  UNIQUE KEY `FK_idUser` (`FK_idUser`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
SET foreign_key_checks = 1;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11)  AUTO_INCREMENT,
  `firstname` varchar(50) ,
  `name` varchar(50) ,
  `birthdate` date ,
  `address` varchar(100) ,
  `email` varchar(75) ,
  `password` varchar(100) ,
  `moneyavailable` double ,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bankaccount`
--
ALTER TABLE `bankaccount`
  ADD CONSTRAINT `bankaccount_ibfk_1` FOREIGN KEY (`FK_iduser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`id_friend`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `transfert`
--
ALTER TABLE `transfert`
  ADD CONSTRAINT `transfert_ibfk_1` FOREIGN KEY (`FK_idUser`) REFERENCES `user` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
