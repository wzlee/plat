/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50133
Source Host           : localhost:3306
Source Database       : plat

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2013-08-21 16:02:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazz` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `leaf` tinyint(1) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i0cinnrasmf5up704gotfp0wv` (`pid`) USING BTREE,
  KEY `FK_t5q79q343h5dagly7r5avs1wj` (`parentId`) USING BTREE,
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `category` (`id`),
  CONSTRAINT `category_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'service', '10', '服务', '0', null, '服务', null);
INSERT INTO `category` VALUES ('2', 'service', '1001', '信息服务类', '0', '1', '信息服务类', null);
INSERT INTO `category` VALUES ('3', 'service', '1002', '投融资服务类', '0', '1', '投融资服务类', null);
INSERT INTO `category` VALUES ('4', 'service', '100101', '政策信息查询', '1', '2', '政策信息查询', null);
INSERT INTO `category` VALUES ('5', 'service', '100102', '法律法规信息查询类', '1', '2', '法律法规信息查询类', null);
INSERT INTO `category` VALUES ('6', 'service', '100201', '平安理财', '1', '3', '平安理财', null);
INSERT INTO `category` VALUES ('7', 'product', '', '', '0', null, '产品', null);
INSERT INTO `category` VALUES ('8', 'product', '', 'dfad', '0', '7', '服装', null);
INSERT INTO `category` VALUES ('9', 'product', '', '', '0', '8', '上衣', null);
INSERT INTO `category` VALUES ('21', 'QQ', '', '23432424', '0', null, 'qq', null);
INSERT INTO `category` VALUES ('32', 'product', '', '', '1', '9', 'T桖', null);
INSERT INTO `category` VALUES ('56', 'QQ', '', '', '1', '21', '个人', null);
INSERT INTO `category` VALUES ('70', 'QQ', '', '', '1', '21', '企业', null);
INSERT INTO `category` VALUES ('92', 'fd', '', 's', '0', '7', 'qq', null);
INSERT INTO `category` VALUES ('94', 'qq', '', '', '0', '21', '游戏', null);
INSERT INTO `category` VALUES ('96', 'qq', '', '', '1', '94', '斗地主', null);
INSERT INTO `category` VALUES ('97', 'sa', '', 'd', '1', '92', 'q', null);
INSERT INTO `category` VALUES ('99', 'fds', '', 'dfs', '0', '92', 'qwe', null);
INSERT INTO `category` VALUES ('100', 'df', '', 'ds', '0', '99', 'qw', null);

-- ----------------------------
-- Table structure for `channel`
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `ccode` varchar(255) DEFAULT NULL,
  `cdesc` varchar(255) DEFAULT NULL,
  `cindex` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `ctype` varchar(255) DEFAULT NULL,
  `mtcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `UK_e95x33b4b6ld9i6idq8b4galm` (`ccode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel
-- ----------------------------
INSERT INTO `channel` VALUES ('1', 'cjpd', null, '2', '财经频道', '金融', null);
INSERT INTO `channel` VALUES ('2', 'zbjd', null, '2', '珠宝鉴定', '珠宝', null);
INSERT INTO `channel` VALUES ('3', 'zbrj', null, '2', '正版软件', 'IT', null);
INSERT INTO `channel` VALUES ('4', 'rjgc', null, '3', '软件工程', 'IT', null);
INSERT INTO `channel` VALUES ('5', 'fwjg', null, '3', '服务机构', '金融', null);
INSERT INTO `channel` VALUES ('6', 'zb-fwjg', null, '3', '服务机构', '珠宝', null);
INSERT INTO `channel` VALUES ('7', 'IT-fwjg', null, '4', '服务机构', 'IT', null);
INSERT INTO `channel` VALUES ('8', 'jr-home', null, '1', '首页', '金融', null);
INSERT INTO `channel` VALUES ('9', 'zb-home', null, '1', '首页', '珠宝', null);
INSERT INTO `channel` VALUES ('10', 'IT-home', null, '1', '首页', 'IT', null);

-- ----------------------------
-- Table structure for `filemanager`
-- ----------------------------
DROP TABLE IF EXISTS `filemanager`;
CREATE TABLE `filemanager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazz` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filemanager
-- ----------------------------
INSERT INTO `filemanager` VALUES ('3', null, '2013-08-14 15:45:22', '1376466322930.jpg', '0');
INSERT INTO `filemanager` VALUES ('4', null, '2013-08-15 15:25:58', '1376551558461.jpg', '0');
INSERT INTO `filemanager` VALUES ('5', null, '2013-08-15 15:26:26', '1376551586529.jpg', '0');
INSERT INTO `filemanager` VALUES ('8', null, '2013-08-16 09:17:10', '1376615830158.jpg', '0');
INSERT INTO `filemanager` VALUES ('9', null, '2013-08-16 11:00:54', '1376622054226.jpg', '0');
INSERT INTO `filemanager` VALUES ('10', null, '2013-08-16 17:24:24', '1376645064077.JPG', '0');
INSERT INTO `filemanager` VALUES ('11', null, '2013-08-16 17:27:38', '1376645258113.jpg', '0');
INSERT INTO `filemanager` VALUES ('12', null, '2013-08-16 17:30:27', '1376645427511.jpg', '0');
INSERT INTO `filemanager` VALUES ('13', null, '2013-08-16 17:31:59', '1376645519235.jpg', '0');
INSERT INTO `filemanager` VALUES ('14', null, '2013-08-19 09:22:50', '1376875370417.jpg', '0');
INSERT INTO `filemanager` VALUES ('15', null, '2013-08-19 09:26:12', '1376875572348.JPG', '0');
INSERT INTO `filemanager` VALUES ('16', null, '2013-08-19 09:27:01', '1376875621734.JPG', '0');
INSERT INTO `filemanager` VALUES ('17', null, '2013-08-19 09:57:16', '1376877436421.jpg', '0');
INSERT INTO `filemanager` VALUES ('18', null, '2013-08-19 10:01:27', '1376877687104.jpg', '0');
INSERT INTO `filemanager` VALUES ('19', null, '2013-08-19 10:16:16', '1376878576673.JPG', '0');
INSERT INTO `filemanager` VALUES ('20', null, '2013-08-19 10:41:42', '1376880102713.jpg', '0');
INSERT INTO `filemanager` VALUES ('21', null, '2013-08-19 13:50:21', '1376891421839.jpg', '0');
INSERT INTO `filemanager` VALUES ('22', null, '2013-08-19 16:55:05', '1376902505485.jpg', '0');
INSERT INTO `filemanager` VALUES ('23', null, '2013-08-19 17:01:54', '1376902914024.jpg', '0');
INSERT INTO `filemanager` VALUES ('24', null, '2013-08-19 17:03:39', '1376903019929.jpg', '0');
INSERT INTO `filemanager` VALUES ('25', null, '2013-08-20 11:56:31', '1376970991347.jpg', '0');

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `linktype` varchar(255) DEFAULT NULL,
  `mchannel` varchar(255) DEFAULT NULL,
  `mcode` varchar(255) DEFAULT NULL,
  `mdesc` varchar(255) DEFAULT NULL,
  `mdirect` varchar(255) DEFAULT NULL,
  `micon` varchar(255) DEFAULT NULL,
  `mindex` varchar(255) DEFAULT NULL,
  `mname` varchar(255) DEFAULT NULL,
  `mposition` int(11) NOT NULL,
  `nauthor` tinyint(1) NOT NULL,
  `nlogin` tinyint(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `UK_d617lwhfwcg2jvoy3oxhgxtmx` (`mcode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', '1', 'IT-home', 'IT-home-1', 'ss', 'ss', '1376970991347.jpg', 'http://www.houjie88.com', 'sss', '1', '0', '1', 'opening');
INSERT INTO `module` VALUES ('2', '1', 'IT-home', 'qyfc-10', 'sssss', 'ssss', '1376875370417.jpg', 'http://www.houjie88.com', 'ssss', '2', '0', '0', 'opening');
INSERT INTO `module` VALUES ('4', '2', 'IT-home', 'zqyjt-5', 'jjjjj', 'jjj', '1376877436421.jpg', 'http://www.houjie88.com', 'gggg', '3', '1', '1', 'opening');
INSERT INTO `module` VALUES ('5', '2', 'IT-home', 'zqyjt-14', 'lllll', 'kkkk', '1376877687104.jpg', 'http://www.houjie88.com', 'jjj', '4', '1', '1', 'opening');
INSERT INTO `module` VALUES ('6', '1', 'IT-home', 'zqyjt-15', '我要更新啦！', 'ftp://192.168.0.220', '1376878576673.JPG', 'http://www.houjie88.com', '呵呵呵呵', '5', '0', '1', 'opening');
INSERT INTO `module` VALUES ('7', '1', 'IT-home', 'zqyjt-9', '哈哈哈哈哈', '点点滴滴', '1376880102713.jpg', 'http://www.houjie88.com', '任溶溶', '6', '0', '0', 'stoping');
INSERT INTO `module` VALUES ('8', '1', 'IT-home', 'zqyjt-13', 'kkkkkkk', '', '1376891421839.jpg', 'http://www.houjie88.com', 'hhhh', '7', '1', '0', 'opening');
INSERT INTO `module` VALUES ('9', '2', 'IT-home', 'IT-home', 'llllllllllllllllllllll', '', '1376902505485.jpg', 'http://www.houjie88.com', 'dddd', '14', '1', '0', 'opening');
INSERT INTO `module` VALUES ('10', '2', 'IT-home', 'IT-home-17', 'hhhhhhhhhhhhhhh', 'tttttt', '1376903019929.jpg', 'http://www.houjie88.com', 'gggg', '17', '1', '1', 'opening');

-- ----------------------------
-- Table structure for `mtemplate`
-- ----------------------------
DROP TABLE IF EXISTS `mtemplate`;
CREATE TABLE `mtemplate` (
  `mtid` int(11) NOT NULL AUTO_INCREMENT,
  `mtcode` varchar(255) DEFAULT NULL,
  `mtdesc` varchar(255) DEFAULT NULL,
  `mtname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mtid`),
  UNIQUE KEY `UK_pg0ujrr1cysxmstrw3sen33lq` (`mtcode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mtemplate
-- ----------------------------
INSERT INTO `mtemplate` VALUES ('1', 'theme1', '商务模版', '电子商务模版');
INSERT INTO `mtemplate` VALUES ('2', 'theme2', '自然模版', '大自然模版');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) DEFAULT NULL,
  `cid` int(11) NOT NULL,
  `content` longtext,
  `isTop` tinyint(1) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `pubdate` varchar(255) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('7', 'ewrewii', '1', 'WERWEREWREWREWR<br><br>', '0', '1376615830158.jpg', '2013-08-15 17:10:45', 'ewiiwe', 'woerewr');
INSERT INTO `news` VALUES ('13', 'asdfsd', '3', '阿萨德发生阿斯蒂芬阿斯蒂芬', '0', '', '2013-08-16 17:39:08', 'asdfasdf', 'asfdasf');
INSERT INTO `news` VALUES ('14', 'xxx', '4', '毛泽东屎记', '1', '', '2013-08-20 11:57:41', '匿名', '毛泽东屎记');

-- ----------------------------
-- Table structure for `rights`
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authCode` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `leaf` tinyint(1) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `idxtype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rights
-- ----------------------------
INSERT INTO `rights` VALUES ('6', null, null, '0', '1000', null, '用户数据管理', null);
INSERT INTO `rights` VALUES ('7', null, null, '0', '1000', null, '服务数据管理', null);
INSERT INTO `rights` VALUES ('9', null, null, '1', '6', 'yhzhgl|passportpanel', '用户账号管理', 'yhzhgl|passportpanel');
INSERT INTO `rights` VALUES ('10', null, null, '1', '6', null, '用户权限管理', 'yhqxgl|authoritypanel');
INSERT INTO `rights` VALUES ('11', null, null, '1', '6', null, '用户数据管理', 'yhsjgl|customerdatagrid');
INSERT INTO `rights` VALUES ('12', null, null, '1', '6', null, '服务数据统计', 'fwsjtj|servicedatagrid');
INSERT INTO `rights` VALUES ('13', null, null, '1', '6', null, '用户日志查询', 'yhrzcx|operateloggrid');
INSERT INTO `rights` VALUES ('14', null, null, '1', '7', null, '新增服务管理', 'xzfwgl|nservicegrid');
INSERT INTO `rights` VALUES ('15', null, null, '1', '7', null, '已上架服务管理', 'ysjfwgl|uservicegrid');
INSERT INTO `rights` VALUES ('16', null, null, '1', '7', null, '已下架服务管理', 'yxjfwgl|dservicegrid');
INSERT INTO `rights` VALUES ('17', null, null, '1', '7', null, '已删除服务管理', 'yscfwgl|rservicegrid');
INSERT INTO `rights` VALUES ('18', null, null, '1', '7', null, '服务审批管理', 'fwsjtjcx|aservicegrid');
INSERT INTO `rights` VALUES ('19', null, null, '1', '1003', null, '行业分类管理', 'hyflgl|industrygrid');
INSERT INTO `rights` VALUES ('20', null, null, '1', '1003', null, '行政区码管理', 'xzqmgl|areacodegrid');
INSERT INTO `rights` VALUES ('21', null, null, '1', '1003', null, '数据类别管理', 'sjlbgl|categorygrid');
INSERT INTO `rights` VALUES ('22', null, null, '1', '1003', null, '网站模板管理', 'wzmbgl|templategrid');
INSERT INTO `rights` VALUES ('24', null, null, '1', '1001', null, '服务申请管理', null);
INSERT INTO `rights` VALUES ('25', null, null, '1', '1001', null, '服务反馈管理', null);
INSERT INTO `rights` VALUES ('26', null, null, '1', '1001', null, '在线客服管理', null);
INSERT INTO `rights` VALUES ('28', null, null, '1', '1002', null, '新闻咨询管理', 'xwzxgl|newsgrid');
INSERT INTO `rights` VALUES ('29', null, null, '1', '1002', 'pdpzgl|chanelpanel', '频道配置管理', 'pdpzgl|chanelpanel');
INSERT INTO `rights` VALUES ('30', null, null, '1', '7', null, '服务综合查询', 'fwzhcx|qservicegrid');
INSERT INTO `rights` VALUES ('31', null, null, '1', '1003', null, '数据字典管理', 'sjzdgl|syscodegrid');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `roledesc` varchar(255) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '2013-08-12 09:56:43', '供管理员使用', '管理员', '用户数据管理, 服务数据管理, 新增服务管理, 已上架服务管理, 已下架服务管理, 已删除服务管理, 服务审批管理, 服务综合查询, 行业分类管理, 行政区码管理, 数据类别管理, 网站模板管理, 服务申请管理, 服务反馈管理, 在线客服管理');
INSERT INTO `role` VALUES ('2', '2013-08-12 09:56:43', '测试对比1', '测试', '用户数据管理, 服务数据管理, 新增服务管理, 已上架服务管理, 已下架服务管理, 已删除服务管理, 服务审批管理, 服务综合查询, 行业分类管理, 行政区码管理, 数据类别管理, 网站模板管理, 服务申请管理');
INSERT INTO `role` VALUES ('6', null, '4', '4', '用户数据管理, 服务数据管理, 新增服务管理, 已上架服务管理, 已下架服务管理, 已删除服务管理, 服务审批管理, 服务综合查询, 行业分类管理, 行政区码管理, 数据类别管理, 网站模板管理, 服务申请管理');
INSERT INTO `role` VALUES ('8', null, '3', '3', '用户数据管理, 服务数据管理, 新增服务管理, 已上架服务管理, 已下架服务管理, 已删除服务管理, 服务审批管理, 服务综合查询, 行业分类管理, 行政区码管理, 数据类别管理, 网站模板管理, 服务申请管理');

-- ----------------------------
-- Table structure for `role_rights`
-- ----------------------------
DROP TABLE IF EXISTS `role_rights`;
CREATE TABLE `role_rights` (
  `Role_id` int(11) NOT NULL,
  `rights_id` int(11) NOT NULL,
  KEY `FK_5my51uj4mrcp4lrvttch81fyi` (`rights_id`) USING BTREE,
  KEY `FK_gf7itudagtf04uwtnwsa5npgs` (`Role_id`) USING BTREE,
  CONSTRAINT `role_rights_ibfk_1` FOREIGN KEY (`rights_id`) REFERENCES `rights` (`id`),
  CONSTRAINT `role_rights_ibfk_2` FOREIGN KEY (`Role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 11264 kB; (`rights_id`) REFER `plat/rights`(`id';

-- ----------------------------
-- Records of role_rights
-- ----------------------------
INSERT INTO `role_rights` VALUES ('2', '22');
INSERT INTO `role_rights` VALUES ('2', '24');
INSERT INTO `role_rights` VALUES ('2', '25');
INSERT INTO `role_rights` VALUES ('2', '26');
INSERT INTO `role_rights` VALUES ('6', '6');
INSERT INTO `role_rights` VALUES ('6', '9');
INSERT INTO `role_rights` VALUES ('6', '10');
INSERT INTO `role_rights` VALUES ('6', '11');
INSERT INTO `role_rights` VALUES ('6', '12');
INSERT INTO `role_rights` VALUES ('6', '13');
INSERT INTO `role_rights` VALUES ('6', '7');
INSERT INTO `role_rights` VALUES ('6', '14');
INSERT INTO `role_rights` VALUES ('8', '6');
INSERT INTO `role_rights` VALUES ('8', '9');
INSERT INTO `role_rights` VALUES ('8', '10');
INSERT INTO `role_rights` VALUES ('8', '11');
INSERT INTO `role_rights` VALUES ('8', '12');
INSERT INTO `role_rights` VALUES ('8', '13');
INSERT INTO `role_rights` VALUES ('1', '6');
INSERT INTO `role_rights` VALUES ('1', '9');
INSERT INTO `role_rights` VALUES ('1', '10');
INSERT INTO `role_rights` VALUES ('1', '11');
INSERT INTO `role_rights` VALUES ('1', '12');
INSERT INTO `role_rights` VALUES ('1', '13');
INSERT INTO `role_rights` VALUES ('1', '7');
INSERT INTO `role_rights` VALUES ('1', '14');
INSERT INTO `role_rights` VALUES ('1', '15');
INSERT INTO `role_rights` VALUES ('1', '16');
INSERT INTO `role_rights` VALUES ('1', '17');
INSERT INTO `role_rights` VALUES ('1', '18');
INSERT INTO `role_rights` VALUES ('1', '30');
INSERT INTO `role_rights` VALUES ('1', '19');
INSERT INTO `role_rights` VALUES ('1', '20');
INSERT INTO `role_rights` VALUES ('1', '21');
INSERT INTO `role_rights` VALUES ('1', '22');
INSERT INTO `role_rights` VALUES ('1', '24');
INSERT INTO `role_rights` VALUES ('1', '25');
INSERT INTO `role_rights` VALUES ('1', '26');
INSERT INTO `role_rights` VALUES ('1', '28');
INSERT INTO `role_rights` VALUES ('1', '29');
INSERT INTO `role_rights` VALUES ('1', '31');

-- ----------------------------
-- Table structure for `service`
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryCode` varchar(255) DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `chargeMethod` varchar(255) DEFAULT NULL,
  `currentStatus` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lastStatus` varchar(255) DEFAULT NULL,
  `linkMan` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) NOT NULL,
  `organizeId` int(11) DEFAULT NULL,
  `organizeName` varchar(255) DEFAULT NULL,
  `registerTime` varchar(255) DEFAULT NULL,
  `serviceMethod` varchar(255) DEFAULT NULL,
  `serviceName` varchar(255) DEFAULT NULL,
  `serviceNo` varchar(255) DEFAULT NULL,
  `serviceNum` int(11) DEFAULT NULL,
  `serviceObject` varchar(255) DEFAULT NULL,
  `serviceProcedure` varchar(255) DEFAULT NULL,
  `serviceSource` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `catId` int(11) DEFAULT NULL,
  `orgId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pwm2c1t9cx5csfhntpo7g3gwg` (`catId`) USING BTREE,
  KEY `FK_gohkflo1wgpbequf46nt76rgs` (`orgId`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES ('1', '100102', '5', '政策信息查询', '免费', '已下架', '10086@qq.com', '下架审核中', '庞永锋', '0', '1', '政府机构', '2013-08-20 17:27:28', '信函服务,其他服务,上门服务', '小企业信函服务', '1376990848461', '0', '小型企业,大型企业,事业单位,民办非企业', 'asdfasdfasdfa山山水水<br>', '1', '010-123456', null, null);
INSERT INTO `service` VALUES ('15', '100102', '5', '政策信息查询', '免费', '新服务', '10086@qq.com', '', '庞永锋', '0', '2', '服务机构', '2013-08-21 09:54:40', '刊物服务,信函服务,上门服务,电话服务', 'Testaaaaaaaaaaa', '100201-1377050080677', '0', '小型企业,事业单位,民办非企业', '123456Test', '1', '123456', null, null);
INSERT INTO `service` VALUES ('7', '100102', '5', '法律法规信息查询类', '按年收费', '已上架', '10086@qq.com', '上架审核中', '庞永锋', '0', '2', '服务机构', '2013-08-20 18:07:14', '刊物服务,网络服务', '123', '100101-1376993234251', '0', '请选择服务对象...', '', '1', '', null, null);
INSERT INTO `service` VALUES ('2', '100102', '5', '法律法规信息查询类', '免费', '已下架', 'sdsa@163.com', '已删除', '夏迪', '0', '2', '服务机构', '2013-08-20 17:30:08', '刊物服务,网络服务', '5', '100102-1376991008753', '0', '中型企业,大型企业,事业单位,社会团体', 'asdf对司法斯蒂芬<br>', '1', '撒打发斯蒂芬', null, null);
INSERT INTO `service` VALUES ('3', '100101', '4', '政策信息查询', '按年收费', '已下架', '1213@16.com', '下架审核中', '夏迪', '0', '2', '服务机构', '2013-08-20 17:33:40', '上门服务,刊物服务,网络服务', '阿斯顿发斯蒂芬', '100101-1376991220511', '0', '中型企业,创业个人或团队,社会团体', '撒打发斯蒂芬谁谁谁<br>werewrewr<br>', '1', '四大发送到', null, null);
INSERT INTO `service` VALUES ('4', '100102', '5', '法律法规信息查询类', '按年收费', '变更审核中', '10086@qq.com', '已上架', '庞永锋', '0', '1', '政府机构', '2013-08-20 17:36:32', '上门服务,刊物服务', '阿斯顿发斯蒂芬', '100102-1376991392405', '0', '中型企业,个体工商户,事业单位', '阿斯顿发斯蒂芬<br>2342423432<br>', '2', '', null, null);
INSERT INTO `service` VALUES ('5', '100102', '5', '法律法规信息查询类', '按年收费', '已上架', '10086@qq.com', '上架审核中', '夏迪', '0', '2', '服务机构', '2013-08-20 17:39:36', '刊物服务,信函服务', '倒萨发斯蒂芬', '100102-1376991576012', '0', '小型企业,创业个人或团队,事业单位', '撒旦飞洒地方', '2', '撒', null, null);
INSERT INTO `service` VALUES ('6', '100101', '4', '政策信息查询', '免费', '新服务', '10086@qq.com', '已删除', '夏迪', '0', '2', '服务机构', '2013-08-20 18:01:40', '上门服务,刊物服务', 'aaaa', '100101-1376992900187', '0', '中型企业,个体工商户,事业单位', '中型企业, 个体工商户, 事业单位sdfsdfsdf<br>', '1', '', null, null);
INSERT INTO `service` VALUES ('8', '100101', '4', '政策信息查询', '按月收费', '新服务', '10086@qq.com', '上架审核中', '庞永锋', '0', '2', '服务机构', '2013-08-21 09:01:41', '刊物服务,网络服务,柜台式服务', '123', '100101-1377046901804', '0', '大型企业,个体工商户', 'sdffwerwrwe', '1', '', null, null);
INSERT INTO `service` VALUES ('9', '100101', '4', '政策信息查询', '免费', '新服务', '10086@qq.com', '已删除', '黑衣人', '0', '1', '政府机构', '2013-08-21 09:09:29', '刊物服务,信函服务', '阿飞威尔伯', '-1377047369905', '0', '中型企业,个体工商户,社会团体', 'none', '1', '110', null, null);
INSERT INTO `service` VALUES ('10', '100101', '4', '政策信息查询', '免费', '新服务', '10086@qq.com', '已删除', '夏迪', '0', '2', '服务机构', '2013-08-21 09:18:01', '上门服务,网络服务', '321', '100102-1377047881150', '0', '创业个人或团队,事业单位', '12334656<br>', '1', '123', null, null);
INSERT INTO `service` VALUES ('13', '100101', '4', '政策信息查询', '自费', '新服务', '123456@163.com', '', '胡杨婧', '0', '1', '政府机构', '2013-08-21 09:45:45', '上门服务,刊物服务,网络服务,电话服务', 'Test002', '100101-1377049545340', '0', '中型企业,个体工商户,社会团体,民办非企业', 'Test0020000fswerewrwerwe', '1', '123456', null, null);
INSERT INTO `service` VALUES ('11', '100101', '4', '政策信息查询', '按月收费', '已上架', '10086@qq.com', '变更审核中', '夏迪', '0', '2', '服务机构', '2013-08-21 09:33:38', '上门服务,刊物服务,电话服务,信函服务,其他服务', '331', '-1377048818266', '0', '小型企业,大型企业,个体工商户,社会团体', '445454', '1', '', null, null);
INSERT INTO `service` VALUES ('12', '100101', '4', '政策信息查询', '按年收费', '已上架', '1101203999@163.com', '变更审核中', '胡杨婧', '0', '2', '服务机构', '2013-08-21 09:35:43', '上门服务,信函服务,网络服务,刊物服务', 'Test001', '100102-1377048943541', '0', '大型企业,个体工商户,社会团体,民办非企业', 'Test001修改', '1', '1101203999', null, null);
INSERT INTO `service` VALUES ('14', '100101', '4', '政策信息查询', '免费', '新服务', 'p@pp.cn', '上架审核中', '胡杨婧', '0', '2', '服务机构', '2013-08-21 09:52:21', '电话服务,上门服务', '网络成就你我', '100101-1377049941500', '0', '小型企业,中型企业', 'xiaopxiaopxiaf', '2', '0755-123456', null, null);
INSERT INTO `service` VALUES ('16', '100101', '4', '政策信息查询', '按月收费', '已上架', '123@16.com', '上架审核中', '庞永锋', '0', '1', '政府机构', '2013-08-21 10:03:36', '其他服务,刊物服务,电话服务', '00010010001', '100101-1377050616646', '0', '中型企业,个体工商户,社会团体', '00010010001<br>sdfsfsdfsdfsdffsdf<br>', '1', '001001', null, null);
INSERT INTO `service` VALUES ('17', '100101', '4', '政策信息查询', '免费', '新服务', 'sdfsf@126.com', '已删除', '李志威', '0', '1', '政府机构', '2013-08-21 10:08:41', '电话服务,上门服务', '2342342', '100101-1377050921782', '0', '大型企业,创业个人或团队,小型企业', '32423423423432 <br>', '1', '234234', null, null);
INSERT INTO `service` VALUES ('18', '100101', '4', '政策信息查询', '免费', '新服务', '2342342@126.com', '已删除', '李志威', '0', '2', '服务机构', '2013-08-21 10:20:01', '电话服务', '财政引导', '-1377051601208', '0', '中型企业,大型企业,事业单位', '3244324444444444444<br>', '2', '23424234423423', null, null);
INSERT INTO `service` VALUES ('19', '100101', '4', '政策信息查询', '免费', '新服务', '234234@126.com', '已删除', '李志威', '0', '1', '政府机构', '2013-08-21 10:20:51', '上门服务,刊物服务,电话服务', '选定创业项目', '-1377051651363', '0', '小型企业,中型企业,创业个人或团队,个体工商户', '23444444444444444444<br>', '2', '342424324', null, null);
INSERT INTO `service` VALUES ('20', '100101', '4', '政策信息查询', '按月收费', '新服务', '10086@qq.com', '', '李志威', '0', '1', '政府机构', '2013-08-21 10:40:40', '刊物服务,网络服务', '23424', '-1377052840217', '0', '请选择服务对象...', '', '1', '', null, null);
INSERT INTO `service` VALUES ('21', '100101', '4', '政策信息查询', '按月收费', '新服务', '10086@qq.com', '', '李志威', '0', '2', '服务机构', '2013-08-21 10:46:49', '刊物服务,信函服务,网络服务', '信息检索', '100101-1377053209007', '0', '小型企业,中型企业', '', '1', 'ewrwer', null, null);
INSERT INTO `service` VALUES ('22', '100101', '4', '政策信息查询', '按月收费', '新服务', 'qweqw@163.com', '', '李志威', '0', '2', '服务机构', '2013-08-21 10:47:25', '刊物服务,网络服务', '战略咨询', '-1377053245231', '0', '请选择服务对象...', '', '1', 'qeqw', null, null);
INSERT INTO `service` VALUES ('23', '100101', '4', '政策信息查询', '免费', '变更审核中', 'erer@126.com', '已上架', '徐威峰', '0', '1', '政府机构', '2013-08-21 11:11:11', '电话服务,信函服务', '34324', '100101-1377054671040', '0', '创业个人或团队', '234234<br>we45342424<br>', '1', 'werwer', null, null);
INSERT INTO `service` VALUES ('24', '100101', '4', '政策信息查询', '免费', '已上架', '1321@126.com', '变更审核中', '徐威峰', '0', '2', '服务机构', '2013-08-21 11:17:58', '电话服务,网络服务,信函服务', 'good', '100101-1377055078481', '0', '小型企业', '23424234<br>4345345345<br>', '1', '1231231231', null, null);
INSERT INTO `service` VALUES ('25', '100101', '4', '政策信息查询', '免费', '已上架', 'saf@126.com', '变更审核中', '徐威峰', '0', '2', '服务机构', '2013-08-21 11:20:16', '上门服务,信函服务', 'bad', '100101-1377055216649', '0', '中型企业,大型企业,创业个人或团队', '3242342332432423432423432', '2', 'saf', null, null);

-- ----------------------------
-- Table structure for `servicedetail`
-- ----------------------------
DROP TABLE IF EXISTS `servicedetail`;
CREATE TABLE `servicedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operatStatus` int(11) DEFAULT NULL,
  `operatorTime` datetime DEFAULT NULL,
  `serialId` varchar(255) DEFAULT NULL,
  `servId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_j0skge291okuaitax8086pcdn` (`servId`) USING BTREE,
  CONSTRAINT `servicedetail_ibfk_1` FOREIGN KEY (`servId`) REFERENCES `service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of servicedetail
-- ----------------------------

-- ----------------------------
-- Table structure for `serviceorg`
-- ----------------------------
DROP TABLE IF EXISTS `serviceorg`;
CREATE TABLE `serviceorg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `areaCounty` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `industryCode` varchar(255) DEFAULT NULL,
  `leganPerson` varchar(255) DEFAULT NULL,
  `linkMan` varchar(255) DEFAULT NULL,
  `mainRemark` varchar(255) DEFAULT NULL,
  `openedTime` varchar(255) DEFAULT NULL,
  `orgCode` varchar(255) DEFAULT NULL,
  `orgName` varchar(255) DEFAULT NULL,
  `orgNo` varchar(255) DEFAULT NULL,
  `orgProperty` int(11) DEFAULT NULL,
  `personnel` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `postCode` varchar(255) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `regesterType` varchar(255) DEFAULT NULL,
  `registerAsset` double DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `shareType` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `webSite` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of serviceorg
-- ----------------------------
INSERT INTO `serviceorg` VALUES ('1', 'sdafsadf', 'asdfsadf', 'asdfasdf', 'asdf', 'asdfsadf', null, null, null, null, null, '政府机构', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `serviceorg` VALUES ('2', null, null, null, null, null, null, null, null, null, null, '服务机构', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `syscode`
-- ----------------------------
DROP TABLE IF EXISTS `syscode`;
CREATE TABLE `syscode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_96qgs57bjjb01d0p7pcfilioh` (`type`,`value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syscode
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fe6nqh4mlcjr068gcfrstmh2y` (`role_id`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 11264 kB; (`role_id`) REFER `plat/role`(`id`)';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'c2981e4b66039510681e12498ad624eb', 'chens', '1');
INSERT INTO `user` VALUES ('2', 'c2981e4b66039510681e12498ad624eb', 'chens1', '2');
INSERT INTO `user` VALUES ('3', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '1');
INSERT INTO `user` VALUES ('10', '098f6bcd4621d373cade4e832627b4f6', 'test', '6');
INSERT INTO `user` VALUES ('12', 'c4ca4238a0b923820dcc509a6f75849b', '1', '2');
INSERT INTO `user` VALUES ('13', 'c81e728d9d4c2f636f067f89cc14862c', '2', '2');
INSERT INTO `user` VALUES ('15', 'a87ff679a2f3e71d9181a67b7542122c', '4', '6');
INSERT INTO `user` VALUES ('16', 'e4da3b7fbbce2345d7772b0674a318d5', '5', '8');
INSERT INTO `user` VALUES ('17', '1679091c5a880faf6fb5e6087eb1b2dc', '6', '6');
INSERT INTO `user` VALUES ('18', 'c9f0f895fb98ab9159f51fd0297e236d', '8', '1');
