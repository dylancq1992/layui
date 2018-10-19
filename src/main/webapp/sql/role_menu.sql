/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-13 08:38:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menuid` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `creator` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `isleaf` varbinary(255) DEFAULT NULL COMMENT '是否是叶子节点',
  PRIMARY KEY (`id`),
  KEY `fk_role_menu_roleid` (`roleid`),
  KEY `fk_role_menu_menuid` (`menuid`)
) ENGINE=MyISAM AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
