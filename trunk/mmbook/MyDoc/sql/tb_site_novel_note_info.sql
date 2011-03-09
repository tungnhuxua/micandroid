/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_novel_note_info
-- ----------------------------
CREATE TABLE `tb_site_novel_note_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '小说ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `chapter_count_` int(11) DEFAULT NULL COMMENT '章节数',
  `serialstory_stat_` int(11) DEFAULT NULL COMMENT '连载状态',
  `author_note_` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '小说作者信息',
  `word_count_` int(11) DEFAULT '0' COMMENT '总字数',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_novel_note_info` VALUES ('1007282', '1007282', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728283', '100728283', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728284', '100728284', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728285', '100728285', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728286', '100728286', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728287', '100728287', '0', null, '刘德华天意', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728288', '100728288', '0', null, '我的boss叫恶狐她，一个现代的可爱女孩，本不信鬼神之说。可是，却偏偏遇到一个被封印了千年的无耻“恶狐”，而且，这个“畜生”还帅到足以让她流口水！！！更可恶的是，她“悲惨“的小奴仆也由此开始。 ', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('288100819', '288100819', '0', null, '鎾掔殑鍙戠敓鍦版柟', '0');
