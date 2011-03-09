/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_content_sort_info
-- ----------------------------
CREATE TABLE `tb_site_content_sort_info` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '网站内容分类ID',
  `model_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模型ID',
  `classify_name_` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '网站内容分类名称',
  `sort_byname_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '分类别名',
  `lower_node_` int(4) DEFAULT '2' COMMENT '是否有下级节点:1有，2没有',
  `parent_id_` int(4) DEFAULT '0' COMMENT '上级节点ID',
  `classify_grade_` int(4) DEFAULT '1' COMMENT '等级:级别，1、2、3',
  `sort_value_` int(4) DEFAULT '0' COMMENT '排序值:默认为0',
  `effective_` int(4) DEFAULT '1' COMMENT '是否有效:0 无效．1 有效',
  `classify_nature_` int(4) DEFAULT '1' COMMENT '分类性质:为拓展分类用途预留字段，默认为1',
  `classify_notes_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `insert_time_` date DEFAULT NULL COMMENT '新增时间',
  `logo_url_` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT 'LogoUrl',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_content_sort_info` VALUES ('1', null, '国际热点', null, '1', '0', '1', '1', '1', '1', null, '2010-09-04', null);
INSERT INTO `tb_site_content_sort_info` VALUES ('2', null, '最新国际时讯', null, '1', '1', '2', '2', '1', '1', null, '2010-07-20', null);
INSERT INTO `tb_site_content_sort_info` VALUES ('3', null, '体育', null, '2', '0', '1', '0', '1', '1', null, '2010-08-21', null);
INSERT INTO `tb_site_content_sort_info` VALUES ('4', null, '中超', null, '2', '3', '1', '0', '1', '1', null, null, null);
INSERT INTO `tb_site_content_sort_info` VALUES ('5', null, '赛程', null, '1', '4', '1', '0', '1', '1', null, null, null);
INSERT INTO `tb_site_content_sort_info` VALUES ('6', null, '国内热点', null, '1', '0', '1', '0', '1', '1', null, '2010-09-04', null);
INSERT INTO `tb_site_content_sort_info` VALUES ('7', '11', '11', '', '2', '1', '2', null, null, null, '', '2010-09-04', '');
INSERT INTO `tb_site_content_sort_info` VALUES ('8', '', 'sdfasdf', '', '2', '6', '2', null, null, null, '', '2010-09-04', '');
INSERT INTO `tb_site_content_sort_info` VALUES ('9', '1', '啊sdfasdf的', '深度', '1', '6', '2', '2', '1', '1', '3', '2010-09-04', '额v');
INSERT INTO `tb_site_content_sort_info` VALUES ('10', '1', '读书法', '撒地方', '2', '9', '3', '1', '1', '1', '1', '2010-09-04', '1');
