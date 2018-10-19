/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-16 09:30:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `loginname` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_type` varchar(1) DEFAULT NULL COMMENT '用户类型',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `telephone` varchar(255) DEFAULT NULL COMMENT '座机号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `mobile_phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq',
  `status` varchar(255) DEFAULT NULL COMMENT '业务状态',
  `organid` bigint(20) DEFAULT NULL COMMENT '组织id',
  `bindip` varchar(255) DEFAULT NULL COMMENT '绑定IP',
  `validity` varchar(255) DEFAULT NULL COMMENT '有效性',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `modifydate` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(255) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`userid`),
  KEY `fk_user_organid` (`organid`)
) ENGINE=MyISAM AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('-1', 'admin', '05fe7461c607c33229772d402505601016a7d0ea', '1', '张三', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('2', 'whw', '05fe7461c607c33229772d402505601016a7d0ea', '1', '王宏伟', '男', '', '', '13345454567', '', '', '', '1', '2', '', '1', '2018-07-09 15:54:13', null, '2018-07-16 09:03:59', '-1');
INSERT INTO `user` VALUES ('3', 'lisi', '05fe7461c607c33229772d402505601016a7d0ea', '1', '李四', '男', '', '', '133563656356', '', '', '', '1', null, '', '0', '2018-07-10 09:22:25', null, '2018-07-11 09:36:05', '1');
INSERT INTO `user` VALUES ('4', '123qwe', '05fe7461c607c33229772d402505601016a7d0ea', '1', '王五', '男', '', '', '15656565656', '', '', '', '1', '1', '', '1', '2018-07-11 17:52:11', null, null, null);
INSERT INTO `user` VALUES ('5', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aa', 'aa', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('6', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('7', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('8', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('9', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('10', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('11', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('12', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('13', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('14', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('15', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('29', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('16', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('17', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('18', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('19', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('20', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('21', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('22', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('23', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('24', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('25', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('26', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('27', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('28', 'aaa', '05fe7461c607c33229772d402505601016a7d0ea', '1', 'aaa', '男', '', '', '13335353435', '', '', '', '1', '1', '', '1', '2018-07-09 15:21:47', null, '2018-07-13 09:32:42', null);
INSERT INTO `user` VALUES ('117', 'lqx', '05fe7461c607c33229772d402505601016a7d0ea', '1', '刘', '男', '', '', '15645454464', '', '', '', '1', '1', '', '1', '2018-07-16 09:10:22', '-1', '2018-07-16 09:11:30', '-1');
