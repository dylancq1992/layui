/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-10-19 10:48:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `role_user`
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_user
-- ----------------------------
