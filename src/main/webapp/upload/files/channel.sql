/*
SQLyog Ultimate v9.62 
MySQL - 5.6.12 : Database - plat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`plat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `plat`;

/*Table structure for table `channel` */

DROP TABLE IF EXISTS `channel`;

CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `cindex` int(11) DEFAULT NULL,
  `leaf` tinyint(1) DEFAULT NULL,
  `ccode` varchar(255) DEFAULT NULL,
  `chttp` varchar(255) DEFAULT NULL,
  `mtcode` varchar(255) DEFAULT NULL,
  `cdesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e95x33b4b6ld9i6idq8b4galm` (`ccode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `channel` */

insert  into `channel`(`id`,`pid`,`cname`,`cindex`,`leaf`,`ccode`,`chttp`,`mtcode`,`cdesc`) values (1,0,'枢纽平台',NULL,0,'pivot',NULL,NULL,NULL),(2,0,'服装',NULL,0,'costume',NULL,NULL,NULL),(3,0,'LED',NULL,0,'led',NULL,NULL,NULL),(4,0,'电子装备',NULL,0,'electron',NULL,NULL,NULL),(5,0,'港澳',NULL,0,'hkmacao',NULL,NULL,NULL),(6,0,'工业设计',NULL,0,'product',NULL,NULL,NULL),(7,0,'机械',NULL,0,'machinery',NULL,NULL,NULL),(8,0,'家具',NULL,0,'furniture',NULL,NULL,NULL),(9,0,'软件',NULL,0,'IT',NULL,NULL,NULL),(10,0,'物联网',NULL,0,'zigbee',NULL,NULL,NULL),(11,0,'物流',NULL,0,'logistics',NULL,NULL,NULL),(12,0,'新材料',NULL,0,'newmaterial',NULL,NULL,NULL),(13,0,'医疗器械',NULL,0,'medical',NULL,NULL,NULL),(14,0,'珠宝',NULL,0,'jewelry',NULL,NULL,NULL),(15,0,'钟表',NULL,0,'horologe',NULL,NULL,NULL),(16,0,'其他',NULL,0,'other',NULL,NULL,NULL),(17,1,'首页',1,1,'pivot-1',NULL,NULL,NULL),(18,1,'服务商城',2,1,'pivot-2',NULL,NULL,NULL),(19,1,'新闻资讯',3,1,'pivot-3',NULL,NULL,NULL),(20,1,'企业百科',4,1,'pivot-4',NULL,NULL,NULL),(21,1,'企业百科',5,1,'pivot-5',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
