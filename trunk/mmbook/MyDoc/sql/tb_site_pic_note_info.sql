/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_pic_note_info
-- ----------------------------
CREATE TABLE `tb_site_pic_note_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '图片ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `pic_title_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '图片小标题',
  `pic_format_` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '图片格式',
  `pic_types_` int(11) DEFAULT NULL COMMENT '图片类型',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
