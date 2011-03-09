/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:55:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_basic_parameters_info
-- ----------------------------
CREATE TABLE `tb_basic_parameters_info` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '基本参数ID',
  `parameters_name_` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '基本参数名称',
  `parameters_value_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '基本参数值',
  `parameters_tag_` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '基本参数标签',
  `parameters_type_` int(4) DEFAULT NULL COMMENT '类型参数:1,系统参数;2,平台参数',
  `parameters_note_` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '基本参数说明',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_basic_parameters_info` VALUES ('4', 'WEB部署URL', '/mmbook', 'weburl', '1', 'WEB管理平台发布URL');
INSERT INTO `tb_basic_parameters_info` VALUES ('5', '标签存在位置', '/res/tag', 'tagurl', '1', '标签存在位置');
INSERT INTO `tb_basic_parameters_info` VALUES ('15', '上的发生地方', '', '', '1', '按时打发');
INSERT INTO `tb_basic_parameters_info` VALUES ('18', '发生地方', '', '', '2', '按时打发似的');
INSERT INTO `tb_basic_parameters_info` VALUES ('19', '系统', 'sdfasdfa', '1', '1', '宝宝');
