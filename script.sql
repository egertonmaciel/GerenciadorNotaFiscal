CREATE DATABASE `gerenciador_nota_fiscal`;

USE `gerenciador_nota_fiscal`;

CREATE TABLE `empresa` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_fantasia` varchar(255) DEFAULT NULL,
  `razao_social` varchar(255) DEFAULT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `tipo_empresa` enum('TOMADORA','PRESTADORA') NOT NULL,
  `ativo` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`);
)

CREATE TABLE `nota_fiscal` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numero` int(10) unsigned NOT NULL,
  `data` datetime NOT NULL,
  `valor` double NOT NULL,
  `tomadora` int(10) unsigned NOT NULL,
  `prestadora` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_prestadora` (`prestadora`),
  KEY `FK_tomadora` (`tomadora`),
  CONSTRAINT `FK_prestadora` FOREIGN KEY (`prestadora`) REFERENCES `empresa` (`id`),
  CONSTRAINT `FK_tomadora` FOREIGN KEY (`tomadora`) REFERENCES `empresa` (`id`)
);

