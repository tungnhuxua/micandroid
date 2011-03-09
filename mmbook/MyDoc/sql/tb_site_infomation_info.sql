/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_site_infomation_info
-- ----------------------------
CREATE TABLE `tb_site_infomation_info` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `site_name_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '网站名称',
  `config_id_` int(11) DEFAULT NULL COMMENT '配置ID',
  `ftp_upload_id_` int(11) DEFAULT NULL COMMENT '上传ftp',
  `domain_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '域名',
  `path_` varchar(225) COLLATE utf8_bin DEFAULT NULL COMMENT '路径',
  `short_name_` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '网站简称',
  `current_system_` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '当前系统',
  `static_dir_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '静态页面存在URL',
  `locale_admin_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '后台本地化',
  `locale_front_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '前台本地化',
  `is_relative_path_` int(11) DEFAULT NULL COMMENT '是否使用相对路径',
  `tpl_solution_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '模板方案',
  `dynamic_suffix_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '动态页后缀',
  `static_suffix_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '静态页后缀',
  `protocol_` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '协议',
  `is_static_on_` int(11) DEFAULT NULL COMMENT '是否开启静态化',
  `domain_alias_` varchar(250) COLLATE utf8_bin DEFAULT NULL COMMENT '域名别名',
  `domain_redirect_` varchar(250) COLLATE utf8_bin DEFAULT NULL COMMENT '域名重定向',
  `final_step_` int(11) DEFAULT NULL COMMENT '终审级别',
  `after_check_` int(11) DEFAULT NULL COMMENT '审核后',
  `logo_url_` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT '网站LOGO',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_site_infomation_info` VALUES ('1', 'mmbook', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
