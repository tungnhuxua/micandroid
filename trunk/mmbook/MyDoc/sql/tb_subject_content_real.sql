/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_subject_content_real
-- ----------------------------
CREATE TABLE `tb_subject_content_real` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主题与内容关联id',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '网站内容ID',
  `subject_id_` int(11) DEFAULT NULL COMMENT '主题ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
