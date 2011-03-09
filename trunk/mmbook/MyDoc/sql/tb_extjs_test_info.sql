/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:55:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_extjs_test_info
-- ----------------------------
CREATE TABLE `tb_extjs_test_info` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '功能ID',
  `function_name_` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '功能名称',
  `parent_id_` int(4) unsigned zerofill NOT NULL COMMENT '上级功能ID',
  `types_` char(1) COLLATE utf8_bin NOT NULL COMMENT '功能类型',
  `insert_time_` date DEFAULT NULL COMMENT '新增时间',
  `telephone_` varchar(13) COLLATE utf8_bin DEFAULT NULL COMMENT '系联电话',
  `mobile_telephone_` varchar(13) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `frequency_` int(4) DEFAULT NULL COMMENT '次数',
  `upfile_url_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '上传文件URL',
  `rating_` int(4) DEFAULT NULL COMMENT '等级',
  `whether_default_` char(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否为默认功能',
  `function_explain_` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '功能说明',
  `aggregate_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '功能集合体',
  PRIMARY KEY (`id_`),
  KEY `parent_id_` (`parent_id_`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_extjs_test_info` VALUES ('1', '测试功能', '0000', '1', '2010-07-08', '010-33665522', '13838380438', '11', 'http://www.google.com/', '1', '0', '测试功能说明书', null);
INSERT INTO `tb_extjs_test_info` VALUES ('2', '国务院', '0000', '1', '2010-07-08', '010-55225585', null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('3', '外交部', '0002', '1', '2010-07-08', null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('4', '国防部', '0002', '1', '2010-07-08', null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('5', '党组书记', '0003', '1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('6', '主任：张平', '0005', '1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('7', '解放军', '0004', '1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('8', '常中央', '0000', '1', null, null, null, null, null, null, null, null, null);
