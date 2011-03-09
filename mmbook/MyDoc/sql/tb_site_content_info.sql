/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_content_info
-- ----------------------------
CREATE TABLE `tb_site_content_info` (
  `id_` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '内容ID',
  `sort_id_` int(11) DEFAULT NULL COMMENT '容内分类ID',
  `one_sort_id_` int(11) DEFAULT NULL COMMENT '一级分类ID',
  `title_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `title_full_` varchar(250) COLLATE utf8_bin DEFAULT NULL COMMENT '全标题',
  `synopsis_` varchar(2200) COLLATE utf8_bin DEFAULT NULL COMMENT '内容简介',
  `sources_` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '来源',
  `author_` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  `submit_nam_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '内容提交人',
  `submit_time_` date DEFAULT NULL COMMENT '提交时间',
  `update_nam_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `update_time_` date DEFAULT NULL COMMENT '最近修改时间',
  `previews_img_url_` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT '缩略图URL',
  `state_no_` int(11) DEFAULT '11' COMMENT '状态',
  `model_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模型ID',
  `sort_value_` int(11) DEFAULT '1' COMMENT '排序值',
  `comment_stat_` int(11) DEFAULT '1' COMMENT '评论状态',
  `view_class_` int(11) DEFAULT '1' COMMENT '查看级别',
  `show_time_` int(11) DEFAULT NULL COMMENT '显示时间段',
  `conent_url_` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT '内容URL',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_content_info` VALUES ('100821841', '5', '0', '挣扎在情感边缘的女人：红枕', '挣扎在情感边缘的女人：红枕(全本)', '内容简介', '女人', '刘仰', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211315524477.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821842', '5', '0', '[文化]\"挟尸要价\"的真实性被质疑', '澶ф硶甯堟墦鍙�', '内容简介', '鍟�', '郑彦英', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211315524477.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821843', '5', '0', '首任妻子揭秘与本-拉登的八年婚姻', '澶ф硶甯堟墦鍙�', '闃垮洓璋涙硶', '鍟�', '本-拉登', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211315524477.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821844', '5', '0', '闃垮洓璋涙硶', '', '', '', '', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211338103331.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821845', '5', '0', '闃垮洓璋涙硶', '', '', '', '', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211338103331.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100822846', '5', '3', '鎾掑湴鏂�', '鐨�', '鎾掑湴鏂�', '濂�', '椋庡厜濂�', null, '2010-08-22', null, '2010-08-22', 'upfile/image/xad/201008220128108523.jpg', null, '11', '1', '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100822847', '6', '3', '鎾掑湴鏂�', '鐨�', '鎾掑湴鏂�', '濂�', '椋庡厜濂�', null, '2010-08-22', null, '2010-08-22', 'upfile/image/xad/201008220128108523.jpg', null, '11', '1', '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100822848', '6', '1', 'bbbbbb', '1', 'dsf', '1', 'asdf', null, '2010-08-22', null, '2010-08-22', 'upfile/image/xad/201008221634241290.gif', null, '11', '1', '1', '1', '1', '1');
