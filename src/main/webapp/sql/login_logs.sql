/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-09 09:37:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login_logs`
-- ----------------------------
DROP TABLE IF EXISTS `login_logs`;
CREATE TABLE `login_logs` (
  `logid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志表id',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `loginname` varchar(255) DEFAULT NULL COMMENT '登录名',
  `sessionid` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `ip_address` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `mac_address` varchar(255) DEFAULT NULL COMMENT 'mac地址',
  `area` varchar(255) DEFAULT NULL,
  `operating_system` varchar(255) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(255) DEFAULT NULL COMMENT '使用的浏览器',
  `user_status` varchar(255) DEFAULT NULL COMMENT '用户状态',
  `error_info` varchar(255) DEFAULT NULL COMMENT '错误信息',
  PRIMARY KEY (`logid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_logs
-- ----------------------------
