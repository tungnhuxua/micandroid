/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:45
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_part_content_real
-- ----------------------------
CREATE TABLE `tb_site_part_content_real` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '栏目内容关系ID',
  `part_id_` int(11) DEFAULT NULL COMMENT '栏目id',
  `sort_id_` int(11) DEFAULT NULL COMMENT '分类ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_part_content_real` VALUES ('1', '1', '3');
INSERT INTO `tb_site_part_content_real` VALUES ('2', '1', '6');
INSERT INTO `tb_site_part_content_real` VALUES ('3', '1', '1');
INSERT INTO `tb_site_part_content_real` VALUES ('4', '19', '6');
INSERT INTO `tb_site_part_content_real` VALUES ('5', '19', '10');
INSERT INTO `tb_site_part_content_real` VALUES ('6', '21', '2');
INSERT INTO `tb_site_part_content_real` VALUES ('7', '21', '7');
INSERT INTO `tb_site_part_content_real` VALUES ('8', '21', '5');
