/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-20 17:44:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `notice_address`
-- ----------------------------
DROP TABLE IF EXISTS `notice_address`;
CREATE TABLE `notice_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `noticeid` bigint(20) DEFAULT NULL,
  `addressid` bigint(20) DEFAULT NULL COMMENT '收件人id',
  `addresstype` varchar(255) DEFAULT NULL COMMENT '收件人类型',
  PRIMARY KEY (`id`),
  KEY `fk_notice_address_addressid` (`addressid`),
  KEY `fk_notice_address_noticeid` (`noticeid`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice_address
-- ----------------------------
