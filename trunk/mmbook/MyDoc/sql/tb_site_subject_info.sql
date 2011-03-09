/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:03
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_subject_info
-- ----------------------------
CREATE TABLE `tb_site_subject_info` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主题ID',
  `model_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模型ID',
  `subject_name_` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '主题名称',
  `full_name_` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '主题完整名称',
  `subject_notes_` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '主题说明',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
