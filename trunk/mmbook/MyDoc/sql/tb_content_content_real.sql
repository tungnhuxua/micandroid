/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:55:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_content_content_real
-- ----------------------------
CREATE TABLE `tb_content_content_real` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '内容与内容关联ID',
  `main_content_id_` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '主内容ID',
  `relating_content_id_` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '关联内容ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
