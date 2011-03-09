/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:55:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_article_info
-- ----------------------------
CREATE TABLE `tb_site_article_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '资讯文章内容id',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_article_info` VALUES ('100821841', '100821841');
INSERT INTO `tb_site_article_info` VALUES ('100821842', '100821842');
INSERT INTO `tb_site_article_info` VALUES ('100821843', '100821843');
INSERT INTO `tb_site_article_info` VALUES ('100821844', '100821844');
INSERT INTO `tb_site_article_info` VALUES ('100821845', '100821845');
INSERT INTO `tb_site_article_info` VALUES ('100822846', '100822846');
INSERT INTO `tb_site_article_info` VALUES ('100822847', '100822847');
INSERT INTO `tb_site_article_info` VALUES ('100822848', '100822848');
