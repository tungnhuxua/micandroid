/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tg_cms_model
-- ----------------------------
CREATE TABLE `tg_cms_model` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `MODEL_NAME_` varchar(50) COLLATE utf8_bin NOT NULL,
  `DEPICT_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DEAL_CLASS_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `DEAL_OBJECT_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `TABLE_NAME_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tg_cms_model` VALUES ('10', '资讯', '资讯', 'ClassName', 'NewsContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('11', '书籍', '书籍', 'ClassName', 'BookContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('12', '下载', '下载', 'ClassName', 'SoftwareContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('13', '音乐', '音乐', 'ClassName', 'MusicContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('14', '铃声', '铃声', 'ClassName', 'RingContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('15', '图片', '图片', 'ClassName', 'ImageContentRelease', '');
