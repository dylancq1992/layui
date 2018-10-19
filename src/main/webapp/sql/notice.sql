/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-17 10:17:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` blob COMMENT '内容',
  `validity` varchar(255) DEFAULT NULL COMMENT '有效性',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `pubtime` datetime DEFAULT NULL COMMENT '发布时间',
  `pubname` varchar(255) DEFAULT NULL COMMENT '发布人姓名',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `modifydate` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(255) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`noticeid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
