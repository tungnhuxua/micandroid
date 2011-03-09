/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tc_version_info
-- ----------------------------
CREATE TABLE `tc_version_info` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `VERSION_NAME_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `VERSION_DIR_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tc_version_info` VALUES ('1', 'wap1.x', 'wap1');
INSERT INTO `tc_version_info` VALUES ('2', 'wap2.0', 'wap2');
