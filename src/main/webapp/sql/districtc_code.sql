/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-07-14 13:34:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `districtc_code`
-- ----------------------------
DROP TABLE IF EXISTS `districtc_code`;
CREATE TABLE `districtc_code` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `PARENTID` bigint(20) DEFAULT NULL COMMENT '父级字典项ID',
  `ORDERNO` varchar(255) DEFAULT NULL COMMENT '显示顺序',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '状态',
  `LEVELS` varchar(255) DEFAULT NULL COMMENT '级别',
  `LIST_NUM` varchar(255) DEFAULT NULL COMMENT '列表号',
  `APPRAISE_ORGAN` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL COMMENT '0:省、市、县，1：行业',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=331184 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of districtc_code
-- ----------------------------
INSERT INTO `districtc_code` VALUES ('330000', '浙江省', null, '1', '1', 'A', null, '00', '0');
INSERT INTO `districtc_code` VALUES ('330100', '杭州市', '330000', null, '1', 'B', null, '01', '0');
INSERT INTO `districtc_code` VALUES ('330102', '上城区', '330100', null, '1', 'C', null, '14', '0');
INSERT INTO `districtc_code` VALUES ('330103', '下城区', '330100', null, '1', 'C', null, '15', '0');
INSERT INTO `districtc_code` VALUES ('330104', '江干区', '330100', null, '1', 'C', null, '17', '0');
INSERT INTO `districtc_code` VALUES ('330105', '拱墅区', '330100', null, '1', 'C', null, '16', '0');
INSERT INTO `districtc_code` VALUES ('330106', '西湖区', '330100', null, '1', 'C', null, '18', '0');
INSERT INTO `districtc_code` VALUES ('330108', '滨江区', '330100', null, '1', 'C', null, '19', '0');
INSERT INTO `districtc_code` VALUES ('330109', '萧山区', '330100', null, '1', 'C', null, '20', '0');
INSERT INTO `districtc_code` VALUES ('330110', '余杭区', '330100', null, '1', 'C', null, '21', '0');
INSERT INTO `districtc_code` VALUES ('330122', '桐庐县', '330100', null, '1', 'C', null, '24', '0');
INSERT INTO `districtc_code` VALUES ('330127', '淳安县', '330100', null, '1', 'C', null, '26', '0');
INSERT INTO `districtc_code` VALUES ('330182', '建德市', '330100', null, '1', 'C', null, '25', '0');
INSERT INTO `districtc_code` VALUES ('330183', '富阳市', '330100', null, '1', 'C', null, '22', '0');
INSERT INTO `districtc_code` VALUES ('330185', '临安市', '330100', null, '1', 'C', null, '23', '0');
INSERT INTO `districtc_code` VALUES ('330200', '宁波市', '330000', null, '1', 'B', null, '02', '0');
INSERT INTO `districtc_code` VALUES ('330203', '海曙区', '330200', null, '1', 'C', null, '27', '0');
INSERT INTO `districtc_code` VALUES ('330204', '江东区', '330200', null, '1', 'C', null, '29', '0');
INSERT INTO `districtc_code` VALUES ('330205', '江北区', '330200', null, '1', 'C', null, '28', '0');
INSERT INTO `districtc_code` VALUES ('330206', '北仑区', '330200', null, '1', 'C', null, '31', '0');
INSERT INTO `districtc_code` VALUES ('330211', '镇海区', '330200', null, '1', 'C', null, '30', '0');
INSERT INTO `districtc_code` VALUES ('330212', '鄞州区', '330200', null, '1', 'C', null, '32', '0');
INSERT INTO `districtc_code` VALUES ('330225', '象山县', '330200', null, '1', 'C', null, '36', '0');
INSERT INTO `districtc_code` VALUES ('330226', '宁海县', '330200', null, '1', 'C', null, '37', '0');
INSERT INTO `districtc_code` VALUES ('330281', '余姚市', '330200', null, '1', 'C', null, '34', '0');
INSERT INTO `districtc_code` VALUES ('330282', '慈溪市', '330200', null, '1', 'C', null, '33', '0');
INSERT INTO `districtc_code` VALUES ('330283', '奉化市', '330200', null, '1', 'C', null, '35', '0');
INSERT INTO `districtc_code` VALUES ('330300', '温州市', '330000', null, '1', 'B', null, '03', '0');
INSERT INTO `districtc_code` VALUES ('330302', '鹿城区', '330300', null, '1', 'C', null, '39', '0');
INSERT INTO `districtc_code` VALUES ('330303', '龙湾区', '330300', null, '1', 'C', null, '38', '0');
INSERT INTO `districtc_code` VALUES ('330304', '瓯海区', '330300', null, '1', 'C', null, '40', '0');
INSERT INTO `districtc_code` VALUES ('330322', '洞头县', '330300', null, '1', 'C', null, '45', '0');
INSERT INTO `districtc_code` VALUES ('330324', '永嘉县', '330300', null, '1', 'C', null, '46', '0');
INSERT INTO `districtc_code` VALUES ('330326', '平阳县', '330300', null, '1', 'C', null, '43', '0');
INSERT INTO `districtc_code` VALUES ('330327', '苍南县', '330300', null, '1', 'C', null, '44', '0');
INSERT INTO `districtc_code` VALUES ('330328', '文成县', '330300', null, '1', 'C', null, '47', '0');
INSERT INTO `districtc_code` VALUES ('330329', '泰顺县', '330300', null, '1', 'C', null, '48', '0');
INSERT INTO `districtc_code` VALUES ('330381', '瑞安市', '330300', null, '1', 'C', null, '42', '0');
INSERT INTO `districtc_code` VALUES ('330382', '乐清市', '330300', null, '1', 'C', null, '41', '0');
INSERT INTO `districtc_code` VALUES ('330400', '嘉兴市', '330000', null, '1', 'B', null, '05', '0');
INSERT INTO `districtc_code` VALUES ('330402', '南湖区', '330400', null, '1', 'C', null, '52', '0');
INSERT INTO `districtc_code` VALUES ('330411', '秀洲区', '330400', null, '1', 'C', null, '53', '0');
INSERT INTO `districtc_code` VALUES ('330421', '嘉善县', '330400', null, '1', 'C', null, '55', '0');
INSERT INTO `districtc_code` VALUES ('330424', '海盐县', '330400', null, '1', 'C', null, '57', '0');
INSERT INTO `districtc_code` VALUES ('330481', '海宁市', '330400', null, '1', 'C', null, '54', '0');
INSERT INTO `districtc_code` VALUES ('330482', '平湖市', '330400', null, '1', 'C', null, '56', '0');
INSERT INTO `districtc_code` VALUES ('330483', '桐乡市', '330400', null, '1', 'C', null, '58', '0');
INSERT INTO `districtc_code` VALUES ('330500', '湖州市', '330000', null, '1', 'B', null, '04', '0');
INSERT INTO `districtc_code` VALUES ('330502', '吴兴区', '330500', null, '1', 'C', null, null, '0');
INSERT INTO `districtc_code` VALUES ('330503', '南浔区', '330500', null, '1', 'C', null, '77', '0');
INSERT INTO `districtc_code` VALUES ('330521', '德清县', '330500', null, '1', 'C', null, '49', '0');
INSERT INTO `districtc_code` VALUES ('330522', '长兴县', '330500', null, '1', 'C', null, '50', '0');
INSERT INTO `districtc_code` VALUES ('330523', '安吉县', '330500', null, '1', 'C', null, '51', '0');
INSERT INTO `districtc_code` VALUES ('330600', '绍兴市', '330000', null, '1', 'B', null, '06', '0');
INSERT INTO `districtc_code` VALUES ('330602', '越城区', '330600', null, '1', 'C', null, '59', '0');
INSERT INTO `districtc_code` VALUES ('330621', '柯桥区', '330600', null, '1', 'C', null, '60', '0');
INSERT INTO `districtc_code` VALUES ('330624', '新昌县', '330600', null, '1', 'C', null, '62', '0');
INSERT INTO `districtc_code` VALUES ('330681', '诸暨市', '330600', null, '1', 'C', null, '63', '0');
INSERT INTO `districtc_code` VALUES ('330682', '上虞市', '330600', null, '1', 'C', null, '64', '0');
INSERT INTO `districtc_code` VALUES ('330683', '嵊州市', '330600', null, '1', 'C', null, '61', '0');
INSERT INTO `districtc_code` VALUES ('330700', '金华市', '330000', null, '1', 'B', null, '07', '0');
INSERT INTO `districtc_code` VALUES ('330702', '婺城区', '330700', null, '1', 'C', null, null, '0');
INSERT INTO `districtc_code` VALUES ('330703', '金东区', '330700', null, '1', 'C', null, '65', '0');
INSERT INTO `districtc_code` VALUES ('330723', '武义县', '330700', null, '1', 'C', null, '67', '0');
INSERT INTO `districtc_code` VALUES ('330726', '浦江县', '330700', null, '1', 'C', null, '71', '0');
INSERT INTO `districtc_code` VALUES ('330727', '磐安县', '330700', null, '1', 'C', null, '69', '0');
INSERT INTO `districtc_code` VALUES ('330781', '兰溪市', '330700', null, '1', 'C', null, '66', '0');
INSERT INTO `districtc_code` VALUES ('330782', '义乌市', '330700', null, '1', 'C', null, '70', '0');
INSERT INTO `districtc_code` VALUES ('330783', '东阳市', '330700', null, '1', 'C', null, '68', '0');
INSERT INTO `districtc_code` VALUES ('330784', '永康市', '330700', null, '1', 'C', null, '72', '0');
INSERT INTO `districtc_code` VALUES ('330800', '衢州市', '330000', null, '1', 'B', null, '08', '0');
INSERT INTO `districtc_code` VALUES ('330802', '柯城区', '330800', null, '1', 'C', null, '73', '0');
INSERT INTO `districtc_code` VALUES ('330803', '衢江区', '330800', null, '1', 'C', null, '74', '0');
INSERT INTO `districtc_code` VALUES ('330822', '常山县', '330800', null, '1', 'C', null, '76', '0');
INSERT INTO `districtc_code` VALUES ('330824', '开化县', '330800', null, '1', 'C', null, '78', '0');
INSERT INTO `districtc_code` VALUES ('330825', '龙游县', '330800', null, '1', 'C', null, '75', '0');
INSERT INTO `districtc_code` VALUES ('330881', '江山市', '330800', null, '1', 'C', null, '79', '0');
INSERT INTO `districtc_code` VALUES ('330900', '舟山市', '330000', null, '1', 'B', null, '11', '0');
INSERT INTO `districtc_code` VALUES ('330902', '定海区', '330900', null, '1', 'C', null, '12', '0');
INSERT INTO `districtc_code` VALUES ('330903', '普陀区', '330900', null, '1', 'C', null, '97', '0');
INSERT INTO `districtc_code` VALUES ('330921', '岱山县', '330900', null, '1', 'C', null, '98', '0');
INSERT INTO `districtc_code` VALUES ('330922', '嵊泗县', '330900', null, '1', 'C', null, '99', '0');
INSERT INTO `districtc_code` VALUES ('331000', '台州市', '330000', null, '1', 'B', null, '10', '0');
INSERT INTO `districtc_code` VALUES ('331002', '椒江区', '331000', null, '1', 'C', null, '90', '0');
INSERT INTO `districtc_code` VALUES ('331003', '黄岩区', '331000', null, '1', 'C', null, '88', '0');
INSERT INTO `districtc_code` VALUES ('331004', '路桥区', '331000', null, '1', 'C', null, '89', '0');
INSERT INTO `districtc_code` VALUES ('331021', '玉环县', '331000', null, '1', 'C', null, '96', '0');
INSERT INTO `districtc_code` VALUES ('331022', '三门县', '331000', null, '1', 'C', null, '95', '0');
INSERT INTO `districtc_code` VALUES ('331023', '天台县', '331000', null, '1', 'C', null, '94', '0');
INSERT INTO `districtc_code` VALUES ('331024', '仙居县', '331000', null, '1', 'C', null, '93', '0');
INSERT INTO `districtc_code` VALUES ('331081', '温岭市', '331000', null, '1', 'C', null, '92', '0');
INSERT INTO `districtc_code` VALUES ('331082', '临海市', '331000', null, '1', 'C', null, '91', '0');
INSERT INTO `districtc_code` VALUES ('331100', '丽水市', '330000', null, '1', 'B', null, '09', '0');
INSERT INTO `districtc_code` VALUES ('331102', '莲都区', '331100', null, '1', 'C', null, '80', '0');
INSERT INTO `districtc_code` VALUES ('331121', '青田县', '331100', null, '1', 'C', null, '85', '0');
INSERT INTO `districtc_code` VALUES ('331122', '缙云县', '331100', null, '1', 'C', null, '81', '0');
INSERT INTO `districtc_code` VALUES ('331123', '遂昌县', '331100', null, '1', 'C', null, '82', '0');
INSERT INTO `districtc_code` VALUES ('331124', '松阳县', '331100', null, '1', 'C', null, '83', '0');
INSERT INTO `districtc_code` VALUES ('331125', '云和县', '331100', null, '1', 'C', null, '86', '0');
INSERT INTO `districtc_code` VALUES ('331126', '庆元县', '331100', null, '1', 'C', null, '13', '0');
INSERT INTO `districtc_code` VALUES ('331127', '景宁畲族自治县', '331100', null, '1', 'C', null, '84', '0');
INSERT INTO `districtc_code` VALUES ('331181', '龙泉市', '331100', null, '1', 'C', null, '87', '0');
INSERT INTO `districtc_code` VALUES ('330490', '港区', '330400', null, '1', 'C', null, null, '0');
INSERT INTO `districtc_code` VALUES ('330491', '嘉兴经济技术开发区', '330400', null, '1', 'C', null, null, '0');
INSERT INTO `districtc_code` VALUES ('331182', '医药行业', '330000', '1', '1', 'C', null, '00', '1');
INSERT INTO `districtc_code` VALUES ('330207', '鄞州区', '330200', null, '1', 'C', null, '31', '0');
INSERT INTO `districtc_code` VALUES ('331183', '建设行业', '330000', '1', '1', 'C', null, '00', '1');
