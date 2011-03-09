/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:55:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_base_keywords_info
-- ----------------------------
CREATE TABLE `tb_base_keywords_info` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '关键字ID',
  `parent_id_` int(11) DEFAULT '0' COMMENT '上级关键字ID',
  `keywords_value_` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '关键值',
  `keywords_notes_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '关键字说明',
  `lower_node_` int(11) DEFAULT '2' COMMENT '是否有下级节点',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_base_keywords_info` VALUES ('1', '0', '男歌手', '男歌手', '1');
INSERT INTO `tb_base_keywords_info` VALUES ('2', '1', '刘德华', '刘德华', '1');
INSERT INTO `tb_base_keywords_info` VALUES ('3', '2', '天意', '天意', '2');
INSERT INTO `tb_base_keywords_info` VALUES ('4', '1', '李正紧', '李正紧', '2');
