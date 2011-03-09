/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:54:47
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_base_accessories_ategory_info
-- ----------------------------
CREATE TABLE `tb_base_accessories_ategory_info` (
  `id_` int(8) NOT NULL AUTO_INCREMENT COMMENT '网站附件分类ID',
  `sort_name_` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '分类名称',
  `sort_notes_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '分类说明',
  `parent_id_` int(8) DEFAULT '0' COMMENT '父级分类id',
  `lower_node_` int(2) DEFAULT NULL COMMENT '是否有下级节点',
  `sort_format_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '分类格式',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_base_accessories_ategory_info` VALUES ('11', '资讯文本', '资讯文本', '0', '2', 'txt');
INSERT INTO `tb_base_accessories_ategory_info` VALUES ('13', '小说章节内容', '小说章节内容', '0', null, null);
