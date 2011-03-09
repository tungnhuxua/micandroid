/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_part_info
-- ----------------------------
CREATE TABLE `tb_site_part_info` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '栏目ID',
  `part_name_` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '栏目名称',
  `lower_node_` int(4) DEFAULT '2' COMMENT '是否有下级节点:1有，2没有',
  `part_parent_id_` int(4) DEFAULT NULL COMMENT '父站栏目id:一级父id为0',
  `show_type_` int(4) DEFAULT NULL COMMENT '显示方式:默认为0，见显示方式表',
  `sort_value_` int(4) unsigned zerofill DEFAULT NULL COMMENT '排序值:默认为0',
  `effective_` int(4) DEFAULT NULL COMMENT '是否有效:0 无效．1 有效',
  `part_notes_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '栏目说明',
  `insert_time_` date DEFAULT NULL COMMENT '新增时间:默认为系统时间',
  `classify_grade_` int(4) DEFAULT '1' COMMENT '等级',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_part_info` VALUES ('1', '网站导航', '1', '0', '0', '0001', '1', '', '2010-07-20', '1');
INSERT INTO `tb_site_part_info` VALUES ('2', '关于我们', '1', '0', '0', '0002', '1', null, '2010-07-20', '1');
INSERT INTO `tb_site_part_info` VALUES ('3', '新闻', '1', '0', '0', '0003', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('4', '科技', '1', '0', '0', '0004', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('5', '连载', '1', '0', '0', '0005', '1', '', '2010-07-28', '1');
INSERT INTO `tb_site_part_info` VALUES ('6', '排行', '1', '0', '0', '0006', '1', '', '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('7', '美女图片', '1', '0', '0', '0007', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('8', '风景图片', '1', '0', '0', '0008', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('9', '留言版', '1', '0', '0', '0009', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('10', '联系我们', '1', '0', '0', '0010', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('11', '手机软件', '1', '0', '0', '0011', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('12', '电脑软件', '1', '0', '0', '0012', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('13', '经典音乐', '1', '0', '0', '0013', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('14', '流行音乐', '1', '0', '0', '0014', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('15', '分类', '1', '0', '0', '0015', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('16', '国内', '1', '3', '0', '0016', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('17', '国外', '1', '3', '0', '0017', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('18', '军事', '1', '4', '0', '0018', '1', null, '2010-08-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('19', 'imya测试', '1', '15', '1', '0001', '1', '测试', '2010-09-02', '1');
INSERT INTO `tb_site_part_info` VALUES ('20', '打发', null, '1', '3', '0003', '1', '3', '2010-09-03', '1');
INSERT INTO `tb_site_part_info` VALUES ('21', '测试3', '2', '19', '1', '0001', '1', '1', '2010-09-04', '1');
INSERT INTO `tb_site_part_info` VALUES ('187', '测试', '2', '1', '0', '0187', '1', null, '2010-09-08', '1');
