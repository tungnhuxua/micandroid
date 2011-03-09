/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:55:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_part_subject_real
-- ----------------------------
CREATE TABLE `tb_part_subject_real` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '栏目与主题关联id',
  `subject_id_` int(11) DEFAULT NULL COMMENT '主题ID',
  `part_id_` int(11) DEFAULT NULL COMMENT '栏目ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
