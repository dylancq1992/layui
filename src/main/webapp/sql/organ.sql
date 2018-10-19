/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-16 17:47:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `organ`
-- ----------------------------
DROP TABLE IF EXISTS `organ`;
CREATE TABLE `organ` (
  `ORGANID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织标识',
  `PARENTORGANID` bigint(20) NOT NULL COMMENT '父组织标识:01省职建处，02省鉴定中心，03省教研所，04全省技工院校，05全省鉴定机构，06市（区县）鉴定中心，07全省培训机构，08市（区县）人社局',
  `ORGAN_TYPE` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL COMMENT '代码',
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `validity` varchar(255) DEFAULT NULL COMMENT '有效性',
  `districtc_code` varchar(255) DEFAULT NULL COMMENT '主管单位行政区划代码',
  `grade` varchar(255) DEFAULT NULL COMMENT '机构等级   省级 市级  县级',
  `createdate` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `levels` varchar(255) DEFAULT NULL COMMENT '层级',
  PRIMARY KEY (`ORGANID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organ
-- ----------------------------
INSERT INTO `organ` VALUES ('1', '0', '1', '000000', '浙江省人力资源和社会保障厅', '', '1', '330000', '省级', null, null, '2018-07-11 16:32:16', '1', '1');
INSERT INTO `organ` VALUES ('2', '1', '1', '000001', '全省技工院校', '1', '1', '330000', '省级', null, null, '2018-07-11 16:32:38', '1', '2');
INSERT INTO `organ` VALUES ('3', '2', null, 'G00001', '第一所技工院校', '1', '1', '330000', '省级', '2018-07-10 14:12:01', '1', '2018-07-11 17:21:53', null, '3');
INSERT INTO `organ` VALUES ('4', '2', null, 'G00002', '第二所技工院校', '', '1', '330200', '市级', '2018-07-10 14:28:55', null, '2018-07-11 16:11:43', '1', '3');
INSERT INTO `organ` VALUES ('5', '2', '1', '1111', '第三所技工院校', '', '1', '330100', '省级', '2018-07-11 16:15:06', '1', '2018-07-11 16:15:16', '1', '3');
