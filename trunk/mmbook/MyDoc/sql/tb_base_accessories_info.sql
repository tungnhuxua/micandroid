/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:54:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_base_accessories_info
-- ----------------------------
CREATE TABLE `tb_base_accessories_info` (
  `id_` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '附件ID',
  `accessories_type_id_` int(11) DEFAULT NULL COMMENT '网站附件分类ID',
  `subordination_id_` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '附属ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `sort_id_` int(11) DEFAULT NULL COMMENT '分类ID',
  `content_url_` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '附件URL',
  `format_` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '附件格式',
  `file_size_` int(11) DEFAULT NULL COMMENT '附件文件大小',
  `notes_` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '附件说明',
  `file_surfix_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '文件后缀',
  `insert_time_` date DEFAULT NULL COMMENT '新增时间',
  PRIMARY KEY (`id_`),
  KEY `sdfasdfasdfa` (`accessories_type_id_`),
  CONSTRAINT `sdfasdfasdfa` FOREIGN KEY (`accessories_type_id_`) REFERENCES `tb_base_accessories_ategory_info` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_base_accessories_info` VALUES ('10082209127069', '11', '100822848', '100822848', '2', 'upfile/type/productId/10082216379027.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-22');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821120706779', '11', '100821821', '100821821', '2', 'upfile/type/productId/20100821120706337.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821131617660', '11', '100821841', '100821841', '2', 'upfile/type/productId/20100821131611200.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('201008211320315395', '11', '100821842', '100821842', '2', 'upfile/type/productId/201008211320315152.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821132503845', '11', '100821843', '100821843', '2', 'upfile/type/productId/2010082113250831.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821133817218', '11', '100821844', '100821844', '5', 'upfile/type/productId/201008211338177960.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('201008211345541056', '11', '100821845', '100821845', '5', 'upfile/type/productId/201008211345541483.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100822013316925', '11', '100822846', '100822846', '5', 'upfile/type/productId/201008220133169520.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-22');
INSERT INTO `tb_base_accessories_info` VALUES ('201008220138494800', '11', '100822847', '100822847', '5', 'upfile/type/productId/201008220138499400.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-22');
