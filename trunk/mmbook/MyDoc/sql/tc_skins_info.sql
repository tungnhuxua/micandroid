/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tc_skins_info
-- ----------------------------
CREATE TABLE `tc_skins_info` (
  `id_` varchar(10) COLLATE utf8_bin NOT NULL,
  `version_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `skin_name_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `skin_dir_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `isdefault_` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tc_skins_info` VALUES ('1', '1', 'wap1x 默认风格', 'onedefult', '0');
INSERT INTO `tc_skins_info` VALUES ('2', '2', 'wap2.0默认风格', 'twodefult', '1');
INSERT INTO `tc_skins_info` VALUES ('3', '2', 'wap2.0宽屏风格', 'widthtwo', '0');
INSERT INTO `tc_skins_info` VALUES ('4', '1', 'wap1x宽屏风格', 'widthone', '1');
