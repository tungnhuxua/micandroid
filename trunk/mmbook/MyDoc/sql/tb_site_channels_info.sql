/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:55:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_channels_info
-- ----------------------------
CREATE TABLE `tb_site_channels_info` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '频道ID',
  `site_id_` int(11) DEFAULT NULL COMMENT '网站ID',
  `channels_name_` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '频道名称',
  `show_type_` int(4) unsigned zerofill DEFAULT '0000' COMMENT '频道显示方式:默认为0，见显示方式表',
  `sort_value_` int(4) unsigned zerofill DEFAULT NULL COMMENT '频道排序值',
  `effective_` int(4) DEFAULT NULL COMMENT '频道是否有效:0 无效．1 有效',
  `channels_notes_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '频道备注',
  `insert_time_` date DEFAULT NULL COMMENT '频道新增时间',
  `logo_url_` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT 'LogoUrl',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_channels_info` VALUES ('1', '1', '首页', '0000', '0001', '1', '新闻频道说明', '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('2', '1', '资讯', '0000', '0002', '1', '1', '2010-07-12', null);
INSERT INTO `tb_site_channels_info` VALUES ('3', '1', '小说', '0000', '0003', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('4', '1', '音乐', '0000', '0004', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('5', '1', '软件', '0000', '0005', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('6', '1', '图片', '0000', '0006', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('7', '1', '留言版', '0000', '0007', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('8', '1', '联系我们', '0000', '0008', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('12', '1', '论坛', '0000', '0001', '1', '论坛', '2010-08-31', null);
