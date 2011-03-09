/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_novel_content_info
-- ----------------------------
CREATE TABLE `tb_site_novel_content_info` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '章节ID',
  `nove_id_` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '小说ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `chapter_parent_id_` int(11) DEFAULT '0' COMMENT '上级章节ID',
  `name_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '章节名称',
  `chapter_no_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '章节号',
  `chapter_mode_no_` int(11) DEFAULT NULL COMMENT '章节状态',
  `create_time_` date DEFAULT NULL COMMENT '新增时间',
  `creator_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '新增人',
  `modify_time_` date DEFAULT NULL COMMENT '修改时间',
  `modifior_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `chapter_count_` int(11) DEFAULT '1' COMMENT '章节点击量',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
