/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_music_info
-- ----------------------------
CREATE TABLE `tb_site_music_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '音乐ID',
  `conent_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `singer_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '歌手名',
  `music_section_` int(11) DEFAULT NULL COMMENT '歌手地域',
  `music_format_` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '音乐格式',
  `music_style_` int(11) DEFAULT NULL COMMENT '音乐风格',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
