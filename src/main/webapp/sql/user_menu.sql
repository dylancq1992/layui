/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-16 10:57:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_menu`
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL,
  `menuid` bigint(20) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `isleaf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_menu_userid` (`userid`),
  KEY `fk_user_menu_menuid` (`menuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
