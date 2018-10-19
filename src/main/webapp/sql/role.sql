/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-13 21:28:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `code` varchar(255) DEFAULT NULL COMMENT '角色代码',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `validity` varchar(2) DEFAULT NULL COMMENT '有效性',
  `creator` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ADMIN', '系统管理员', '', '1', '1', '2018-07-13 19:34:44', null, null);
INSERT INTO `role` VALUES ('2', 'NORMAL', '普通用户', '', '1', '1', '2018-07-13 19:35:52', null, null);
