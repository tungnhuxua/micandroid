/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 13:01:13
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
-- Table structure for tb_base_accessories_info
-- ----------------------------
CREATE TABLE `tb_base_accessories_info` (
  `id_` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '附件ID',
  `accessories_type_id_` int(11) DEFAULT NULL COMMENT '网站附件分类ID',
  `subordination_id_` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '附属ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `sort_id_` int(11) DEFAULT NULL COMMENT '分类ID',
  `content_url_` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '附件URL',
  `format_` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '附件格式',
  `file_size_` int(11) DEFAULT NULL COMMENT '附件文件大小',
  `notes_` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '附件说明',
  `file_surfix_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '文件后缀',
  `insert_time_` date DEFAULT NULL COMMENT '新增时间',
  PRIMARY KEY (`id_`),
  KEY `sdfasdfasdfa` (`accessories_type_id_`),
  CONSTRAINT `sdfasdfasdfa` FOREIGN KEY (`accessories_type_id_`) REFERENCES `tb_base_accessories_ategory_info` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_base_keycontent_real
-- ----------------------------
CREATE TABLE `tb_base_keycontent_real` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '关键字与内容关联ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '网站内_内容ID',
  `keywords_id_` int(11) DEFAULT NULL COMMENT '关键字ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Table structure for tb_content_content_real
-- ----------------------------
CREATE TABLE `tb_content_content_real` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '内容与内容关联ID',
  `main_content_id_` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '主内容ID',
  `relating_content_id_` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '关联内容ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Table structure for tb_part_subject_real
-- ----------------------------
CREATE TABLE `tb_part_subject_real` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '栏目与主题关联id',
  `subject_id_` int(11) DEFAULT NULL COMMENT '主题ID',
  `part_id_` int(11) DEFAULT NULL COMMENT '栏目ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_site_article_info
-- ----------------------------
CREATE TABLE `tb_site_article_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '资讯文章内容id',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Table structure for tb_site_channels_part_real
-- ----------------------------
CREATE TABLE `tb_site_channels_part_real` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '频道栏目关系',
  `channels_id_` int(4) DEFAULT NULL COMMENT '频道id:外键与频道表主键对应',
  `part_id_` int(4) DEFAULT NULL COMMENT '栏目id:外键与栏目表主键对应',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Table structure for tb_site_message_info
-- ----------------------------
CREATE TABLE `tb_site_message_info` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `message_title_` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '留言标题',
  `message_content_` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '留言内容',
  `reply_id_` int(4) DEFAULT NULL COMMENT '回复留言ID',
  `message_user_` int(4) DEFAULT NULL COMMENT '留言人ID',
  `message_type_` int(4) DEFAULT NULL COMMENT '留言类别:1 用户间留言,2 网站留言',
  `whether_reader_` int(4) DEFAULT NULL COMMENT '是否已查看:1  已查看,2  未查看',
  `whether_reply_` int(11) DEFAULT NULL COMMENT '是否已回复:1  已回复,2  未回复',
  `insert_time_` date DEFAULT NULL COMMENT '留言时间:默认为系统时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_site_music_info
-- ----------------------------
CREATE TABLE `tb_site_music_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '音乐ID',
  `conent_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `singer_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '歌手名',
  `music_section_` int(11) DEFAULT NULL COMMENT '歌手地域',
  `music_format_` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '音乐格式',
  `music_style_` int(11) DEFAULT NULL COMMENT '音乐风格',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Table structure for tb_site_novel_note_info
-- ----------------------------
CREATE TABLE `tb_site_novel_note_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '小说ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `chapter_count_` int(11) DEFAULT NULL COMMENT '章节数',
  `serialstory_stat_` int(11) DEFAULT NULL COMMENT '连载状态',
  `author_note_` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '小说作者信息',
  `word_count_` int(11) DEFAULT '0' COMMENT '总字数',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_site_part_content_real
-- ----------------------------
CREATE TABLE `tb_site_part_content_real` (
  `id_` int(4) NOT NULL AUTO_INCREMENT COMMENT '栏目内容关系ID',
  `part_id_` int(11) DEFAULT NULL COMMENT '栏目id',
  `sort_id_` int(11) DEFAULT NULL COMMENT '分类ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Table structure for tb_site_pic_note_info
-- ----------------------------
CREATE TABLE `tb_site_pic_note_info` (
  `id_` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '图片ID',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '内容ID',
  `pic_title_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '图片小标题',
  `pic_format_` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '图片格式',
  `pic_types_` int(11) DEFAULT NULL COMMENT '图片类型',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_site_subject_info
-- ----------------------------
CREATE TABLE `tb_site_subject_info` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主题ID',
  `model_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模型ID',
  `subject_name_` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '主题名称',
  `full_name_` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '主题完整名称',
  `subject_notes_` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '主题说明',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_subject_content_real
-- ----------------------------
CREATE TABLE `tb_subject_content_real` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主题与内容关联id',
  `content_id_` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '网站内容ID',
  `subject_id_` int(11) DEFAULT NULL COMMENT '主题ID',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tc_skins_info
-- ----------------------------
CREATE TABLE `tc_skins_info` (
  `id_` varchar(10) COLLATE utf8_bin NOT NULL,
  `version_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `skin_name_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `skin_dir_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `isdefault_` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tc_tag_list_info
-- ----------------------------
CREATE TABLE `tc_tag_list_info` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '标签ID',
  `TEMPLET_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模板ID',
  `sort_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '分类ID',
  `TAG_NAME_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名称',
  `IF_PAGE_` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否分页',
  `ROW_NUM_` int(11) DEFAULT '4' COMMENT '行数',
  `INSERT_TIME_` date DEFAULT NULL COMMENT '内容入库时间',
  `POS_ID_` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '推荐位ID',
  `KEY_ID_` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '关键字ID',
  `MODEL_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模型ID',
  `UPDATE_TIME_` date DEFAULT NULL COMMENT '内容更新时间',
  `TAG_EXP_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签说明',
  `ORDER_TYPE_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '排序类型',
  `VERSION_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模板本版ID',
  `COMPANY_ID_` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '发布单位',
  `FIELDS_CODE_` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '字段',
  `MAKE_PEOPLE_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '制作人',
  `MAKE_TIME_` date DEFAULT NULL COMMENT '制作时间',
  `IFSALE_` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否收费',
  `CONTINUE_MODE_NO_` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '小说连载状态',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tc_version_info
-- ----------------------------
CREATE TABLE `tc_version_info` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `VERSION_NAME_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `VERSION_DIR_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tg_cms_model
-- ----------------------------
CREATE TABLE `tg_cms_model` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `MODEL_NAME_` varchar(50) COLLATE utf8_bin NOT NULL,
  `DEPICT_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DEAL_CLASS_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `DEAL_OBJECT_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `TABLE_NAME_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tg_cms_modelfield
-- ----------------------------
CREATE TABLE `tg_cms_modelfield` (
  `id_` varchar(10) COLLATE utf8_bin NOT NULL,
  `model_id_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `field_name_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `field_remark_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `isselect_` char(1) COLLATE utf8_bin DEFAULT NULL,
  `table_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tg_cms_tag_category
-- ----------------------------
CREATE TABLE `tg_cms_tag_category` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `TEMPLET_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_ID_` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `TAG_NAME_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ROW_NUM_` int(11) DEFAULT NULL,
  `TAG_EXP_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `VERSION_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `tag_sort_` varchar(10) COLLATE utf8_bin DEFAULT 'category' COMMENT '标签分类:channels频道,category栏目,sort分类',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tg_cms_tag_content
-- ----------------------------
CREATE TABLE `tg_cms_tag_content` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `TEMPLET_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TAG_NAME_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IF_PAGE_` char(1) COLLATE utf8_bin DEFAULT NULL,
  `ROW_NUM_` int(11) DEFAULT NULL,
  `INSERT_TIME_` date DEFAULT NULL,
  `POS_ID_` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `KEY_ID_` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `MODEL_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_TIME_` date DEFAULT NULL,
  `TAG_EXP_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_TYPE_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `COMPANY_ID_` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `FIELDS_CODE_` varchar(3000) COLLATE utf8_bin DEFAULT NULL,
  `MAKE_PEOPLE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MAKE_TIME_` date DEFAULT NULL,
  `IFSALE_` char(1) COLLATE utf8_bin DEFAULT NULL,
  `CONTINUE_MODE_NO_` char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tg_cms_tag_rmdation
-- ----------------------------
CREATE TABLE `tg_cms_tag_rmdation` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `TEMPLET_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TAG_NAME_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `IF_PAGE_` char(1) COLLATE utf8_bin DEFAULT NULL,
  `ROW_NUM_` int(11) DEFAULT NULL,
  `TAG_EXP_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `RECOMM_ID_` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `RECOMM_NAME_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `VERSION_ID_` varchar(10) COLLATE utf8_bin NOT NULL,
  `ORDER_TYPE_` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tg_cms_templet
-- ----------------------------
CREATE TABLE `tg_cms_templet` (
  `ID_` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '模板ID',
  `TEMPLET_NAME_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '模板名称',
  `TEMPLET_TYPE_` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '模板类型',
  `INSERT_TIME_` date DEFAULT NULL COMMENT '入库时间',
  `UPDATE_TIME_` date DEFAULT NULL COMMENT '更新时间',
  `TEMPLET_CONTENT_` blob COMMENT '模板内容',
  `WEB_TEMPLET_TYPE_` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '模板类型  1:首页11:栏目首页12：列表页13：详细页14：下载页15：预览页16:章节列表页20：栏目标签21：内容标签22:推荐位标签',
  `FILE_NAME_` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `VERSION_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模板方案ID',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_base_accessories_ategory_info` VALUES ('11', '资讯文本', '资讯文本', '0', '2', 'txt');
INSERT INTO `tb_base_accessories_ategory_info` VALUES ('13', '小说章节内容', '小说章节内容', '0', null, null);
INSERT INTO `tb_base_accessories_info` VALUES ('10082209127069', '11', '100822848', '100822848', '2', 'upfile/type/productId/10082216379027.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-22');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821120706779', '11', '100821821', '100821821', '2', 'upfile/type/productId/20100821120706337.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821131617660', '11', '100821841', '100821841', '2', 'upfile/type/productId/20100821131611200.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('201008211320315395', '11', '100821842', '100821842', '2', 'upfile/type/productId/201008211320315152.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821132503845', '11', '100821843', '100821843', '2', 'upfile/type/productId/2010082113250831.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100821133817218', '11', '100821844', '100821844', '5', 'upfile/type/productId/201008211338177960.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('201008211345541056', '11', '100821845', '100821845', '5', 'upfile/type/productId/201008211345541483.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-21');
INSERT INTO `tb_base_accessories_info` VALUES ('20100822013316925', '11', '100822846', '100822846', '5', 'upfile/type/productId/201008220133169520.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-22');
INSERT INTO `tb_base_accessories_info` VALUES ('201008220138494800', '11', '100822847', '100822847', '5', 'upfile/type/productId/201008220138499400.txt', 'txt', '11', '资讯内容', 'txt', '2010-08-22');
INSERT INTO `tb_base_keycontent_real` VALUES ('1', '100821841', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('2', '100821841', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('3', '100821841', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('4', '288100819', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('5', '288100819', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('6', '288100819', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('7', '288100819', '4');
INSERT INTO `tb_base_keycontent_real` VALUES ('8', '819100820', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('9', '819100820', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('10', '819100820', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('11', '819100820', '4');
INSERT INTO `tb_base_keycontent_real` VALUES ('12', '820100820', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('13', '820100820', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('14', '820100820', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('15', '820100820', '4');
INSERT INTO `tb_base_keycontent_real` VALUES ('16', '819100820', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('17', '819100820', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('18', '819100820', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('19', '819100820', '4');
INSERT INTO `tb_base_keycontent_real` VALUES ('20', '100821821', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('21', '100821821', '4');
INSERT INTO `tb_base_keycontent_real` VALUES ('22', '100821820', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('23', '100821820', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('24', '100821820', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('25', '100821842', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('26', '100821842', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('27', '100821842', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('28', '100821843', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('29', '100821843', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('30', '100821843', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('31', '100821844', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('32', '100821844', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('33', '100821844', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('34', '100821845', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('35', '100821845', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('36', '100821845', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('37', '100822846', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('38', '100822846', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('39', '100822846', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('40', '100822847', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('41', '100822847', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('42', '100822847', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('54', '100822848', '1');
INSERT INTO `tb_base_keycontent_real` VALUES ('55', '100822848', '2');
INSERT INTO `tb_base_keycontent_real` VALUES ('56', '100822848', '3');
INSERT INTO `tb_base_keycontent_real` VALUES ('57', '100822848', '4');
INSERT INTO `tb_base_keywords_info` VALUES ('1', '0', '男歌手', '男歌手', '1');
INSERT INTO `tb_base_keywords_info` VALUES ('2', '1', '刘德华', '刘德华', '1');
INSERT INTO `tb_base_keywords_info` VALUES ('3', '2', '天意', '天意', '2');
INSERT INTO `tb_base_keywords_info` VALUES ('4', '1', '李正紧', '李正紧', '2');
INSERT INTO `tb_basic_parameters_info` VALUES ('4', 'WEB部署URL', '/mmbook', 'weburl', '1', 'WEB管理平台发布URL');
INSERT INTO `tb_basic_parameters_info` VALUES ('5', '标签存在位置', '/res/tag', 'tagurl', '1', '标签存在位置');
INSERT INTO `tb_basic_parameters_info` VALUES ('15', '上的发生地方', '', '', '1', '按时打发');
INSERT INTO `tb_basic_parameters_info` VALUES ('18', '发生地方', '', '', '2', '按时打发似的');
INSERT INTO `tb_basic_parameters_info` VALUES ('19', '系统', 'sdfasdfa', '1', '1', '宝宝');
INSERT INTO `tb_extjs_test_info` VALUES ('1', '测试功能', '0000', '1', '2010-07-08', '010-33665522', '13838380438', '11', 'http://www.google.com/', '1', '0', '测试功能说明书', null);
INSERT INTO `tb_extjs_test_info` VALUES ('2', '国务院', '0000', '1', '2010-07-08', '010-55225585', null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('3', '外交部', '0002', '1', '2010-07-08', null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('4', '国防部', '0002', '1', '2010-07-08', null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('5', '党组书记', '0003', '1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('6', '主任：张平', '0005', '1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('7', '解放军', '0004', '1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_extjs_test_info` VALUES ('8', '常中央', '0000', '1', null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_site_article_info` VALUES ('100821841', '100821841');
INSERT INTO `tb_site_article_info` VALUES ('100821842', '100821842');
INSERT INTO `tb_site_article_info` VALUES ('100821843', '100821843');
INSERT INTO `tb_site_article_info` VALUES ('100821844', '100821844');
INSERT INTO `tb_site_article_info` VALUES ('100821845', '100821845');
INSERT INTO `tb_site_article_info` VALUES ('100822846', '100822846');
INSERT INTO `tb_site_article_info` VALUES ('100822847', '100822847');
INSERT INTO `tb_site_article_info` VALUES ('100822848', '100822848');
INSERT INTO `tb_site_channels_info` VALUES ('1', '1', '首页', '0000', '0001', '1', '新闻频道说明', '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('2', '1', '资讯', '0000', '0002', '1', '1', '2010-07-12', null);
INSERT INTO `tb_site_channels_info` VALUES ('3', '1', '小说', '0000', '0003', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('4', '1', '音乐', '0000', '0004', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('5', '1', '软件', '0000', '0005', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('6', '1', '图片', '0000', '0006', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('7', '1', '留言版', '0000', '0007', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('8', '1', '联系我们', '0000', '0008', '1', null, '2010-07-13', null);
INSERT INTO `tb_site_channels_info` VALUES ('12', '1', '论坛', '0000', '0001', '1', '论坛', '2010-08-31', null);
INSERT INTO `tb_site_channels_part_real` VALUES ('1', '1', '1');
INSERT INTO `tb_site_channels_part_real` VALUES ('2', '1', '2');
INSERT INTO `tb_site_channels_part_real` VALUES ('3', '2', '3');
INSERT INTO `tb_site_channels_part_real` VALUES ('4', '2', '4');
INSERT INTO `tb_site_channels_part_real` VALUES ('5', '3', '5');
INSERT INTO `tb_site_channels_part_real` VALUES ('6', '3', '6');
INSERT INTO `tb_site_channels_part_real` VALUES ('7', '4', '13');
INSERT INTO `tb_site_channels_part_real` VALUES ('8', '4', '14');
INSERT INTO `tb_site_channels_part_real` VALUES ('9', '5', '12');
INSERT INTO `tb_site_channels_part_real` VALUES ('10', '5', '11');
INSERT INTO `tb_site_channels_part_real` VALUES ('11', '6', '8');
INSERT INTO `tb_site_channels_part_real` VALUES ('12', '6', '7');
INSERT INTO `tb_site_channels_part_real` VALUES ('13', '7', '9');
INSERT INTO `tb_site_channels_part_real` VALUES ('14', '8', '10');
INSERT INTO `tb_site_channels_part_real` VALUES ('19', '12', '8');
INSERT INTO `tb_site_channels_part_real` VALUES ('20', '12', '9');
INSERT INTO `tb_site_content_info` VALUES ('100821841', '5', '0', '挣扎在情感边缘的女人：红枕', '挣扎在情感边缘的女人：红枕(全本)', '内容简介', '女人', '刘仰', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211315524477.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821842', '5', '0', '[文化]\"挟尸要价\"的真实性被质疑', '澶ф硶甯堟墦鍙�', '内容简介', '鍟�', '郑彦英', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211315524477.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821843', '5', '0', '首任妻子揭秘与本-拉登的八年婚姻', '澶ф硶甯堟墦鍙�', '闃垮洓璋涙硶', '鍟�', '本-拉登', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211315524477.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821844', '5', '0', '闃垮洓璋涙硶', '', '', '', '', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211338103331.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100821845', '5', '0', '闃垮洓璋涙硶', '', '', '', '', null, '2010-08-21', null, '2010-08-21', 'upfile/image/xad/201008211338103331.jpg', null, '11', null, '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100822846', '5', '3', '鎾掑湴鏂�', '鐨�', '鎾掑湴鏂�', '濂�', '椋庡厜濂�', null, '2010-08-22', null, '2010-08-22', 'upfile/image/xad/201008220128108523.jpg', null, '11', '1', '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100822847', '6', '3', '鎾掑湴鏂�', '鐨�', '鎾掑湴鏂�', '濂�', '椋庡厜濂�', null, '2010-08-22', null, '2010-08-22', 'upfile/image/xad/201008220128108523.jpg', null, '11', '1', '1', '1', '1', '');
INSERT INTO `tb_site_content_info` VALUES ('100822848', '6', '1', 'bbbbbb', '1', 'dsf', '1', 'asdf', null, '2010-08-22', null, '2010-08-22', 'upfile/image/xad/201008221634241290.gif', null, '11', '1', '1', '1', '1', '1');
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
INSERT INTO `tb_site_infomation_info` VALUES ('1', 'mmbook', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_site_message_info` VALUES ('1', '删除留言', '如何删除别人给我的留言', '0', '11', '2', '1', '1', '2010-07-11');
INSERT INTO `tb_site_message_info` VALUES ('2', '回复删除', '进入管理平台，留言管理删除留言', '1', '22', '2', '1', '1', '2010-07-12');
INSERT INTO `tb_site_message_info` VALUES ('3', '你的QQ', '请问你的QQ号码多少？', '0', '33', '1', '2', '2', '2010-07-20');
INSERT INTO `tb_site_novel_note_info` VALUES ('1007282', '1007282', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728283', '100728283', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728284', '100728284', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728285', '100728285', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728286', '100728286', '0', null, 'sdfasdfa', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728287', '100728287', '0', null, '刘德华天意', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('100728288', '100728288', '0', null, '我的boss叫恶狐她，一个现代的可爱女孩，本不信鬼神之说。可是，却偏偏遇到一个被封印了千年的无耻“恶狐”，而且，这个“畜生”还帅到足以让她流口水！！！更可恶的是，她“悲惨“的小奴仆也由此开始。 ', '0');
INSERT INTO `tb_site_novel_note_info` VALUES ('288100819', '288100819', '0', null, '鎾掔殑鍙戠敓鍦版柟', '0');
INSERT INTO `tb_site_part_content_real` VALUES ('1', '1', '3');
INSERT INTO `tb_site_part_content_real` VALUES ('2', '1', '6');
INSERT INTO `tb_site_part_content_real` VALUES ('3', '1', '1');
INSERT INTO `tb_site_part_content_real` VALUES ('4', '19', '6');
INSERT INTO `tb_site_part_content_real` VALUES ('5', '19', '10');
INSERT INTO `tb_site_part_content_real` VALUES ('6', '21', '2');
INSERT INTO `tb_site_part_content_real` VALUES ('7', '21', '7');
INSERT INTO `tb_site_part_content_real` VALUES ('8', '21', '5');
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
INSERT INTO `tc_skins_info` VALUES ('1', '1', 'wap1x 默认风格', 'onedefult', '0');
INSERT INTO `tc_skins_info` VALUES ('2', '2', 'wap2.0默认风格', 'twodefult', '1');
INSERT INTO `tc_skins_info` VALUES ('3', '2', 'wap2.0宽屏风格', 'widthtwo', '0');
INSERT INTO `tc_skins_info` VALUES ('4', '1', 'wap1x宽屏风格', 'widthone', '1');
INSERT INTO `tc_tag_list_info` VALUES ('201', '284', '$catid', '图片列表', '0', '3', null, null, null, '15', null, '图片列表', 'ORDER BY id_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.from_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('222', '263', '$catid', '图片内容', '0', '20', null, null, null, '15', null, '图片内容', null, '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.modifior_,tg_content_release.slt_img_url_,tg_image_content_release.small_img_url_,tg_image_content_release.median_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.summary_,tg_content_release.part_id_,tg_content_release.creator_,tg_content_release.modify_time_,tg_image_content_release.slt_img_size_,tg_image_content_release.small_img_size_,tg_image_content_release.median_img_size_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.model_id_,tg_content_release.from_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_image_content_release.slt_img_ext_,tg_image_content_release.small_img_ext_,tg_image_content_release.median_img_ext_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('242', '364', '17', '图片最新上架', '1', '6', null, null, null, '15', null, '图片最新上架', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('261', '364', '17', '图片上榜下载TOP10', '1', '4', null, null, null, '15', null, '图片按点击量排序', 'ORDER BY id_ ASC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('281', '241', '100', '资讯本地子栏目', '1', '4', null, null, null, '10', null, '资讯首页-本地子栏目', 'ORDER BY create_time_ ASC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('282', '241', '10', '资讯栏目最新内容', '1', '5', null, null, null, '10', null, '资讯首页-栏目最新内容', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('301', '241', '101', '资讯国内子栏目', '1', '3', null, null, null, '10', null, '资讯首页-国内子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('302', '241', '102', '资讯环球子栏目', '1', '2', null, null, null, '10', null, '资讯首页-环球子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('303', '241', '103', '资讯财经子栏目', '1', '3', null, null, null, '10', null, '资讯首页-财经子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('304', '241', '106', '资讯娱乐子栏目', '1', '3', null, null, null, '10', null, '资讯首页-娱乐子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('305', '241', '105', '资讯体育子栏目', '1', '3', null, null, null, '10', null, '资讯首页-体育子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('306', '241', '107', '资讯健康子栏目', '1', '3', null, null, null, '10', null, '资讯首页-健康子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('307', '241', '108', '资讯教育子栏目', '1', '3', null, null, null, '10', null, '资讯首页-教育子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('308', '241', '104', '资讯科技子栏目', '1', '3', null, null, null, '10', null, '资讯首页-科技子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('309', '241', '109', '资讯汽车子栏目', '1', '3', null, null, null, '10', null, '资讯首页-汽车子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('310', '241', '$catid', '资讯列表', '0', '20', null, null, null, '10', null, '资讯列表页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('321', '481', '100', '首页本地栏目内容信息', '1', '3', null, null, null, '10', null, '首页本地栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('322', '481', '101', '首页国内栏目内容信息', '1', '2', null, null, null, '10', null, '首页国内栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('323', '481', '102', '首页环球栏目内容信息', '1', '1', null, null, null, '10', null, '首页环球栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('324', '481', '106', '首页娱乐栏目内容信息', '1', '2', null, null, null, '10', null, '首页娱乐栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('325', '481', '105', '首页体育栏目内容信息', '1', '2', null, null, null, '10', null, '首页体育栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('326', '481', '108', '首页教育栏目内容信息', '1', '2', null, null, null, '10', null, '首页教育栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('327', '481', '107', '首页健康栏目内容信息', '1', '2', null, null, null, '10', null, '首页健康栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('341', '561', '$catid', '铃声内容列表', '0', '20', null, null, null, '14', null, '铃声栏目列表页展现', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('361', '661', '200', '音乐栏目最新内容', '1', '10', null, null, null, '13', null, '音乐首页-栏目最新内容', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('381', '561', '16', '铃声首页最新内容列表', '1', '10', null, null, null, '14', null, '铃声首页最新内容列表', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('382', '561', '16', '铃声首页下载排行列表', '1', '10', null, null, null, '14', null, '铃声首页下载排行列表', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('383', '661', '15', '音乐栏目内容下载量', '1', '10', null, null, null, '13', null, '音乐首页栏目内容下载量', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('401', '661', '$catid', '音乐列表', '0', '20', null, null, null, '13', null, '音乐列表页', null, '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.from_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('421', '364', '13', '软件最新栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最新数据', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('422', '364', '12', '主题最新栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最新数据', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('423', '364', '12', '主题最热栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最热数据', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('424', '364', '13', '软件最热栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最热数据', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('441', '702', '$catid', '书城列表页标签', '0', '20', null, null, null, '11', null, '栏目列表页，带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('442', '284', '$catid', '软件列表', '0', '4', null, null, null, '12', null, '软件列表页面', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.model_id_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('443', '284', '$catid', '主题列表', '0', '4', null, null, null, '12', null, '主题列表页面', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('461', '1141', '11', '书城最新连载列表', '1', '7', null, null, null, '11', null, '书城最新连载列表', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '11');
INSERT INTO `tc_tag_list_info` VALUES ('462', '1141', '11', '书城新书上架列表', '1', '7', null, null, null, '11', null, '书城新书上架列表', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('463', '1141', '11', '书城阅读排名列表', '1', '10', null, null, null, '11', null, '书城阅读排名列表', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('464', '1141', '11', '书城小说上榜列表', '1', '7', null, null, null, '11', null, '书城小说上榜列表', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '16');
INSERT INTO `tc_tag_list_info` VALUES ('465', '743', '14', '游戏栏目最新内容', '1', '10', null, null, null, '12', null, '游戏首页-栏目最新内容', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('466', '743', '14', '游戏栏目内容下载量', '1', '10', null, null, null, '12', null, '游戏首页-栏目内容下载量', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('467', '702', '$catid', '书城全本小说列表', '0', '20', null, null, null, '11', null, '书城全本小说列表，带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '16');
INSERT INTO `tc_tag_list_info` VALUES ('468', '702', '$catid', '书城连载小说列表', '0', '20', null, null, null, '11', null, '书城连载小说列表，带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '11');
INSERT INTO `tc_tag_list_info` VALUES ('481', '743', '$catid', '游戏列表', '0', '4', null, null, null, '12', null, '游戏列表页', null, '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('501', '702', '$catid', '书城内容页推荐阅读', '1', '5', null, null, null, '11', null, null, 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_book_content_release.continue_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('521', '661', '15', '音乐详情TOP下载', '1', '5', null, null, null, '13', null, '音乐详情页TOP下载', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('522', '743', '14', '游戏详情TOP下载', '1', '5', null, null, null, '12', null, '游戏详情页TOP下载', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('541', '1081', '15', '音乐栏目标签列表', '0', '10', null, null, '$keyid', '13', null, '音乐栏目标签列表页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('561', '1081', '12', '主题栏目标签列表', '0', '10', null, null, '$keyid', '12', null, '主题栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('562', '1081', '13', '软件栏目标签列表', '0', '10', null, null, '$keyid', '12', null, '软件栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('563', '1081', '14', '游戏栏目标签列表', '0', '10', null, null, '$keyid', '12', null, '游戏栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('564', '1081', '16', '铃声栏目标签列表', '0', '10', null, null, '$keyid', '14', null, '铃声栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('565', '1081', '17', '图片栏目标签列表', '0', '10', null, null, '$keyid', '15', null, '图片栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('581', '481', '109', '首页汽车栏目内容信息', '1', '3', null, null, null, '10', null, '首页汽车栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('582', '481', '103', '首页财经栏目内容信息', '1', '2', null, null, null, '10', null, '首页财经栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('601', '702', '11', '书城新书列表', '0', '20', null, null, null, '11', null, '全部新书,带分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('602', '702', '11', '书城排行列表', '0', '20', null, null, null, '11', null, '全部小说排行,带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('621', '561', '16', '铃声最新列表', '0', '20', null, null, null, '14', null, '全部最新铃声，带分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('622', '561', '16', '铃声热门列表', '0', '20', null, null, null, '14', null, '按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('641', '743', '14', '游戏新品列表', '0', '20', null, null, null, '12', null, '最新游戏；分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('642', '661', '15', '音乐最新列表', '0', '20', null, null, null, '13', null, '最新音乐；分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('643', '743', '14', '游戏排行列表', '0', '20', null, null, null, '12', null, '游戏排行分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('644', '661', '15', '音乐排行列表', '0', '20', null, null, null, '13', null, '音乐排行分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('661', '284', '17', '图片热门列表', '0', '4', null, null, '100824689,100824690,100824691', '15', null, '图片按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.model_id_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('662', '743', '13', '软件新书列表', '0', '10', null, null, '100824694,100824696', '12', null, '全部新软件,带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('664', '743', '12', '主题最新列表', '0', '10', null, null, '100824701,100824710,100824725', '12', null, '全部新主题,带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('665', '743', '12', '主题热门列表', '0', '10', null, null, '100824701,100824710,100824725', '12', null, '主题按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('666', '743', '13', '软件热门列表', '0', '10', null, null, '100824727,100824735', '12', null, '软件按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('667', '743', '14', '游戏热门列表', '0', '10', null, null, '100824727,100824735', '12', null, '游戏按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('668', '284', '17', '图片最新列表', '0', '10', null, null, '100824727,100824735', '15', null, '全部图片,带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tc_version_info` VALUES ('1', 'wap1.x', 'wap1');
INSERT INTO `tc_version_info` VALUES ('2', 'wap2.0', 'wap2');
INSERT INTO `tg_cms_model` VALUES ('10', '资讯', '资讯', 'ClassName', 'NewsContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('11', '书籍', '书籍', 'ClassName', 'BookContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('12', '下载', '下载', 'ClassName', 'SoftwareContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('13', '音乐', '音乐', 'ClassName', 'MusicContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('14', '铃声', '铃声', 'ClassName', 'RingContentRelease', '');
INSERT INTO `tg_cms_model` VALUES ('15', '图片', '图片', 'ClassName', 'ImageContentRelease', '');
INSERT INTO `tg_cms_modelfield` VALUES ('141', '0', 'id_', '内容ID', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('142', '0', 'business_id_', '业务ID', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('143', '0', 'title_', '标题', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('144', '0', 'full_title_', '完整标题', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('145', '0', 'summary_', '内容介绍', '0', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('146', '0', 'model_id_', '内容分类', '0', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('147', '0', 'pay_mode_no_', '收费方式', '0', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('148', '0', 'part_id_', '栏目ID', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('149', '0', 'from_', '来源', '0', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('150', '0', 'author_', '作者', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('151', '0', 'creator_', '创建人', '0', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('152', '0', 'create_time_', '创建时间', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('153', '0', 'modifior_', '最近修改人', '0', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('154', '0', 'modify_time_', '最近修改时间', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('155', '0', 'mode_no_', '状态', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('156', '10', 'text_', '正文', '0', 'tg_news_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('157', '11', 'continue_mode_no_', '连载状态', '0', 'tg_book_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('158', '11', 'chapter_count_', '章节数', '0', 'tg_book_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('159', '11', 'cover_img_url_', '封面图片', '0', 'tg_book_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('172', '12', 'img_one_url_', '截图1', '0', 'tg_software_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('173', '12', 'img_two_url_', '截图2', '0', 'tg_software_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('174', '12', 'img_three_url_', '截图3', '0', 'tg_software_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('181', '13', 'singer_', '歌手名', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('182', '13', 'section_', '地域', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('183', '13', 'full_version_url_', '完整版URL', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('184', '13', 'full_version_size_', '完整版大小', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('185', '13', 'full_version_ext_', '完整版格式', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('186', '13', 'high_version_url_', '高潮版URL', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('187', '13', 'high_version_size_', '高潮版大小', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('188', '13', 'high_version_ext_', '高潮版格式', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('189', '13', 'ring_version_url_', '铃声版URL', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('190', '13', 'ring_version_size_', '铃声版大小', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('191', '13', 'ring_version_ext_', '铃声版格式', '0', 'tg_music_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('192', '14', 'file_url_', '铃声URL', '0', 'tg_ring_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('193', '14', 'file_size_', '铃声大小', '0', 'tg_ring_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('194', '14', 'file_ext_', '铃声格式', '0', 'tg_ring_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('195', '0', 'slt_img_url_', '缩略图URL', '0', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('196', '15', 'slt_img_size_', '缩略图大小', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('197', '15', 'slt_img_ext_', '缩略图格式', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('198', '15', 'small_img_url_', '小图URL', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('199', '15', 'small_img_size_', '小图大小', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('200', '15', 'small_img_ext_', '小图格式', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('201', '15', 'median_img_url_', '中图URL', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('202', '15', 'median_img_size_', '中图大小', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('203', '15', 'median_img_ext_', '中图格式', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('204', '15', 'big_img_url_', '大图URL', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('205', '15', 'big_img_size_', '大图大小', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('206', '15', 'big_img_ext_', '大图格式', '0', 'tg_image_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('207', '0', 'review_mode_no_', '评论状态', '1', 'tg_content_release');
INSERT INTO `tg_cms_modelfield` VALUES ('208', '0', 'view_count_', '浏览量', '1', 'tg_content_release');
INSERT INTO `tg_cms_tag_category` VALUES ('361', '263', '240,241,242,244,245,246,247,248,249,243', '图片所有子栏目', '10', '图片所有子栏目', '1', 'category');
INSERT INTO `tg_cms_tag_category` VALUES ('381', '281', '100,101,102,103,104,105,106,107,108,109', '资讯首页二级栏目', '10', '资讯首页二级栏目', '1', 'category');
INSERT INTO `tg_cms_tag_category` VALUES ('421', '402', '105,106,107', '资讯首页子栏目2', '3', '资讯首页子栏目2-娱乐体育健康', '1', 'category');
INSERT INTO `tg_cms_tag_category` VALUES ('422', '402', '101,102,103', '资讯首页子栏目1', '3', '资讯首页子栏目1-国内环球财经', '1', 'category');
INSERT INTO `tg_cms_tag_category` VALUES ('423', '402', '104,108,109', '资讯首页子栏目3', '3', '资讯首页子栏目3-教育科技汽车', '1', 'category');
INSERT INTO `tg_cms_tag_category` VALUES ('441', '442', '10,11,12,13,14,15,16,17,19', '首页一级栏目', '10', '首页头部显示一级栏目', '1', 'category');
INSERT INTO `tg_cms_tag_category` VALUES ('461', '461', '100,101,102,103', '首页第一屏栏目', '5', '取资讯五个子栏目', '1', 'channels');
INSERT INTO `tg_cms_tag_category` VALUES ('462', '461', '105,106,108,109', '首页第二屏栏目', '5', '', '1', 'channels');
INSERT INTO `tg_cms_tag_category` VALUES ('463', '1101', '11,12,15,17', '首页第三屏栏目', '4', '', '1', 'channels');
INSERT INTO `tg_cms_tag_category` VALUES ('464', '1101', '501,13,14,16', '首页第四屏栏目', '4', '', '1', 'channels');
INSERT INTO `tg_cms_tag_category` VALUES ('481', '542', '227,228,229,230,231,222,226,220,221,223,224,225', '铃声首页二级栏目列表', '100', '铃声首页二级栏目列表', '1', 'category');
INSERT INTO `tg_cms_tag_category` VALUES ('501', '621', '200,201,202,203,204,205,206,207', '音乐首页二级栏目', '10', '音乐首页二级栏目', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('521', '666', '120,121,122,123,124,125,126,127', '书城首页栏目列表', '100', '书城首页栏目列表', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('541', '687', '140,141,142,143,144,145,146,147,148,149,150,151', '主题所有子栏目', '12', '主题栏目首页显示所有分类', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('542', '688', '167,160,161,162,163,164,165,166,168,169', '软件所有子栏目', '10', '软件栏目首页显示所有分类', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('561', '744', '180,181,182,183,184,185,186,187', '游戏首页分类', '8', '游戏首页-游戏分类', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('562', '688', '8,9', 'sdfasdfasdf夺', '10', 'sdfas工daaaa', '1', 'channels');
INSERT INTO `tg_cms_tag_category` VALUES ('563', '666', '9,10,11', '花样百出', '10', '甘开', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('564', '3', '1,2', 'xxx标签比', '10', '频道标签比', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('565', '263', '$catid', '测试频道标签xx', '10', '测试栏目标签22', '1', 'channels');
INSERT INTO `tg_cms_tag_category` VALUES ('566', '281', '3,6', 'sdfasdfa', '104', '测试栏目标签22', '1', null);
INSERT INTO `tg_cms_tag_category` VALUES ('567', '263', '2,7', 'sadfasdf可耕地', '44', 'asdfa', '1', null);
INSERT INTO `tg_cms_tag_category` VALUES ('568', '263', '1,3,6', '测试类别标签', '105', '测试类别标签', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('569', '3', '1,2', 'xxx标签比', '10', '频道标签比', '1', 'sort');
INSERT INTO `tg_cms_tag_category` VALUES ('570', '3', '1,2', 'xxx标签比', '10', '频道标签比', '1', 'sort');
INSERT INTO `tg_cms_tag_content` VALUES ('201', '284', '$catid', '图片列表', '0', '3', null, null, null, '15', null, '图片列表', 'ORDER BY id_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.from_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('222', '263', '$catid', '图片内容', '0', '20', null, null, null, '15', null, '图片内容', null, '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.modifior_,tg_content_release.slt_img_url_,tg_image_content_release.small_img_url_,tg_image_content_release.median_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.summary_,tg_content_release.part_id_,tg_content_release.creator_,tg_content_release.modify_time_,tg_image_content_release.slt_img_size_,tg_image_content_release.small_img_size_,tg_image_content_release.median_img_size_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.model_id_,tg_content_release.from_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_image_content_release.slt_img_ext_,tg_image_content_release.small_img_ext_,tg_image_content_release.median_img_ext_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('242', '364', '17', '图片最新上架', '1', '6', null, null, null, '15', null, '图片最新上架', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('261', '364', '17', '图片上榜下载TOP10', '1', '4', null, null, null, '15', null, '图片按点击量排序', 'ORDER BY id_ ASC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('281', '241', '100', '资讯本地子栏目', '1', '4', null, null, null, '10', null, '资讯首页-本地子栏目', 'ORDER BY create_time_ ASC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('282', '241', '10', '资讯栏目最新内容', '1', '5', null, null, null, '10', null, '资讯首页-栏目最新内容', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('301', '241', '101', '资讯国内子栏目', '1', '3', null, null, null, '10', null, '资讯首页-国内子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('302', '241', '102', '资讯环球子栏目', '1', '2', null, null, null, '10', null, '资讯首页-环球子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('303', '241', '103', '资讯财经子栏目', '1', '3', null, null, null, '10', null, '资讯首页-财经子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('304', '241', '106', '资讯娱乐子栏目', '1', '3', null, null, null, '10', null, '资讯首页-娱乐子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('305', '241', '105', '资讯体育子栏目', '1', '3', null, null, null, '10', null, '资讯首页-体育子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('306', '241', '107', '资讯健康子栏目', '1', '3', null, null, null, '10', null, '资讯首页-健康子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('307', '241', '108', '资讯教育子栏目', '1', '3', null, null, null, '10', null, '资讯首页-教育子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('308', '241', '104', '资讯科技子栏目', '1', '3', null, null, null, '10', null, '资讯首页-科技子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('309', '241', '109', '资讯汽车子栏目', '1', '3', null, null, null, '10', null, '资讯首页-汽车子栏目', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('310', '241', '$catid', '资讯列表', '0', '20', null, null, null, '10', null, '资讯列表页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('321', '481', '100', '首页本地栏目内容信息', '1', '3', null, null, null, '10', null, '首页本地栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('322', '481', '101', '首页国内栏目内容信息', '1', '2', null, null, null, '10', null, '首页国内栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('323', '481', '102', '首页环球栏目内容信息', '1', '1', null, null, null, '10', null, '首页环球栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('324', '481', '106', '首页娱乐栏目内容信息', '1', '2', null, null, null, '10', null, '首页娱乐栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('325', '481', '105', '首页体育栏目内容信息', '1', '2', null, null, null, '10', null, '首页体育栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('326', '481', '108', '首页教育栏目内容信息', '1', '2', null, null, null, '10', null, '首页教育栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('327', '481', '107', '首页健康栏目内容信息', '1', '2', null, null, null, '10', null, '首页健康栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('341', '561', '$catid', '铃声内容列表', '0', '20', null, null, null, '14', null, '铃声栏目列表页展现', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('361', '661', '200', '音乐栏目最新内容', '1', '10', null, null, null, '13', null, '音乐首页-栏目最新内容', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('381', '561', '16', '铃声首页最新内容列表', '1', '10', null, null, null, '14', null, '铃声首页最新内容列表', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('382', '561', '16', '铃声首页下载排行列表', '1', '10', null, null, null, '14', null, '铃声首页下载排行列表', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('383', '661', '15', '音乐栏目内容下载量', '1', '10', null, null, null, '13', null, '音乐首页栏目内容下载量', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('401', '661', '$catid', '音乐列表', '0', '20', null, null, null, '13', null, '音乐列表页', null, '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.from_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('421', '364', '13', '软件最新栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最新数据', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('422', '364', '12', '主题最新栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最新数据', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('423', '364', '12', '主题最热栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最热数据', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('424', '364', '13', '软件最热栏目数据', '1', '6', null, null, null, '12', null, '取软件栏目下所有子栏目最热数据', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('441', '702', '$catid', '书城列表页标签', '0', '20', null, null, null, '11', null, '栏目列表页，带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('442', '284', '$catid', '软件列表', '0', '4', null, null, null, '12', null, '软件列表页面', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.model_id_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('443', '284', '$catid', '主题列表', '0', '4', null, null, null, '12', null, '主题列表页面', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('461', '1141', '11', '书城最新连载列表', '1', '7', null, null, null, '11', null, '书城最新连载列表', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '11');
INSERT INTO `tg_cms_tag_content` VALUES ('462', '1141', '11', '书城新书上架列表', '1', '7', null, null, null, '11', null, '书城新书上架列表', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('463', '1141', '11', '书城阅读排名列表', '1', '10', null, null, null, '11', null, '书城阅读排名列表', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('464', '1141', '11', '书城小说上榜列表', '1', '7', null, null, null, '11', null, '书城小说上榜列表', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '16');
INSERT INTO `tg_cms_tag_content` VALUES ('465', '743', '14', '游戏栏目最新内容', '1', '10', null, null, null, '12', null, '游戏首页-栏目最新内容', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('466', '743', '14', '游戏栏目内容下载量', '1', '10', null, null, null, '12', null, '游戏首页-栏目内容下载量', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('467', '702', '$catid', '书城全本小说列表', '0', '20', null, null, null, '11', null, '书城全本小说列表，带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '16');
INSERT INTO `tg_cms_tag_content` VALUES ('468', '702', '$catid', '书城连载小说列表', '0', '20', null, null, null, '11', null, '书城连载小说列表，带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, '11');
INSERT INTO `tg_cms_tag_content` VALUES ('481', '743', '$catid', '游戏列表', '0', '4', null, null, null, '12', null, '游戏列表页', null, '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('501', '702', '$catid', '书城内容页推荐阅读', '1', '5', null, null, null, '11', null, null, 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_book_content_release.continue_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('521', '661', '15', '音乐详情TOP下载', '1', '5', null, null, null, '13', null, '音乐详情页TOP下载', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('522', '743', '14', '游戏详情TOP下载', '1', '5', null, null, null, '12', null, '游戏详情页TOP下载', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('541', '1081', '15', '音乐栏目标签列表', '0', '10', null, null, '$keyid', '13', null, '音乐栏目标签列表页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('561', '1081', '12', '主题栏目标签列表', '0', '10', null, null, '$keyid', '12', null, '主题栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('562', '1081', '13', '软件栏目标签列表', '0', '10', null, null, '$keyid', '12', null, '软件栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('563', '1081', '14', '游戏栏目标签列表', '0', '10', null, null, '$keyid', '12', null, '游戏栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('564', '1081', '16', '铃声栏目标签列表', '0', '10', null, null, '$keyid', '14', null, '铃声栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('565', '1081', '17', '图片栏目标签列表', '0', '10', null, null, '$keyid', '15', null, '图片栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('581', '481', '109', '首页汽车栏目内容信息', '1', '3', null, null, null, '10', null, '首页汽车栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('582', '481', '103', '首页财经栏目内容信息', '1', '2', null, null, null, '10', null, '首页财经栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.view_count_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.review_mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('601', '702', '11', '书城新书列表', '0', '20', null, null, null, '11', null, '全部新书,带分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('602', '702', '11', '书城排行列表', '0', '20', null, null, null, '11', null, '全部小说排行,带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('621', '561', '16', '铃声最新列表', '0', '20', null, null, null, '14', null, '全部最新铃声，带分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('622', '561', '16', '铃声热门列表', '0', '20', null, null, null, '14', null, '按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('641', '743', '14', '游戏新品列表', '0', '20', null, null, null, '12', null, '最新游戏；分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('642', '661', '15', '音乐最新列表', '0', '20', null, null, null, '13', null, '最新音乐；分页', 'ORDER BY create_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('643', '743', '14', '游戏排行列表', '0', '20', null, null, null, '12', null, '游戏排行分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('644', '661', '15', '音乐排行列表', '0', '20', null, null, null, '13', null, '音乐排行分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.author_,tg_music_content_release.singer_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('661', '284', '17', '图片热门列表', '0', '4', null, null, '100824689,100824690,100824691', '15', null, '图片按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.model_id_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('662', '743', '13', '软件新书列表', '0', '10', null, null, '100824694,100824696', '12', null, '全部新软件,带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('664', '743', '12', '主题最新列表', '0', '10', null, null, '100824701,100824710,100824725', '12', null, '全部新主题,带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('665', '743', '12', '主题热门列表', '0', '10', null, null, '100824701,100824710,100824725', '12', null, '主题按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('666', '743', '13', '软件热门列表', '0', '10', null, null, '100824727,100824735', '12', null, '软件按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('667', '743', '14', '游戏热门列表', '0', '10', null, null, '100824727,100824735', '12', null, '游戏按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.review_mode_no_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_,tg_content_release.view_count_', null, null, null, null);
INSERT INTO `tg_cms_tag_content` VALUES ('668', '284', '17', '图片最新列表', '0', '10', null, null, '100824727,100824735', '15', null, '全部图片,带分页', 'ORDER BY modify_time_ DESC', '1', null, 'tg_content_release.id_,tg_content_release.full_title_,tg_content_release.pay_mode_no_,tg_content_release.author_,tg_content_release.slt_img_url_,tg_content_release.review_mode_no_,tg_content_release.business_id_,tg_content_release.part_id_,tg_content_release.modify_time_,tg_content_release.view_count_,tg_content_release.title_,tg_content_release.create_time_,tg_content_release.mode_no_', null, null, null, null);
INSERT INTO `tg_cms_templet` VALUES ('1', '频道标签', '', '2010-08-02', '2010-08-02', 0x3C424C4F423E, '20', 'mybookmore4', '1');
INSERT INTO `tg_cms_templet` VALUES ('1021', '[软件下载]未适配机型列表', '', '2010-08-09', '2010-08-09', 0x3C424C4F423E, '18', 'agent_show_list', '1');
INSERT INTO `tg_cms_templet` VALUES ('1041', '包月包内容列表页', '', '2010-08-10', '2010-08-10', 0x3C424C4F423E, '18', 'fee_show_more', '1');
INSERT INTO `tg_cms_templet` VALUES ('2', '栏目标签', '', '2010-08-02', '2010-08-02', 0x3C424C4F423E, '20', 'mybookmore3', '1');
INSERT INTO `tg_cms_templet` VALUES ('262', '图片首页', '0', '2010-07-22', '2010-07-22', 0x3C424C4F423E, '11', 'category_image', '1');
INSERT INTO `tg_cms_templet` VALUES ('263', '[标签]图片所有子栏目列表', '1', '2010-07-22', '2010-07-22', 0x3C424C4F423E, '20', 'tag_category_image_allsub', '1');
INSERT INTO `tg_cms_templet` VALUES ('281', '[标签]资讯首页二级栏目标签', '1', '2010-07-22', '2010-07-22', 0x3C424C4F423E, '20', 'tag_category_sub', '1');
INSERT INTO `tg_cms_templet` VALUES ('282', '图片列表页', '0', '2010-07-22', '2010-07-22', 0x3C424C4F423E, '12', 'list_image', '1');
INSERT INTO `tg_cms_templet` VALUES ('284', '[标签]图片内容列表模板有图片', '1', '2010-07-22', '2010-07-22', 0x3C424C4F423E, '21', 'tag_content_list', '1');
INSERT INTO `tg_cms_templet` VALUES ('3', '分类标签', '', '2010-08-02', '2010-08-02', 0x3C424C4F423E, '20', 'tag_sort_list', '1');
INSERT INTO `tg_cms_templet` VALUES ('4', '小说列表', '', '2010-08-02', '2010-08-02', 0x3C424C4F423E, '12', 'mybookmore1', '1');
INSERT INTO `tg_cms_templet` VALUES ('441', '资讯详情页', '', '2010-07-24', '2010-07-24', 0x3C424C4F423E, '13', 'show_news', '1');
INSERT INTO `tg_cms_templet` VALUES ('5', '我的书包列表页', '', '2010-08-02', '2010-08-02', 0x3C424C4F423E, '18', 'mybookmoreq', '1');
INSERT INTO `tg_cms_templet` VALUES ('707', '书城详情页', '', '2010-07-27', '2010-07-27', 0x3C424C4F423E, '13', 'show_books', '1');
INSERT INTO `tg_cms_templet` VALUES ('709', '主题详细页', '', '2010-07-27', '2010-07-27', 0x3C424C4F423E, '13', 'show_topics', '1');
INSERT INTO `tg_cms_templet` VALUES ('721', '游戏首页', '', '2010-07-27', '2010-07-27', 0x3C424C4F423E, '11', 'category_games', '1');
INSERT INTO `tg_cms_templet` VALUES ('921', '我的书包页', '', '2010-08-02', '2010-08-02', 0x3C424C4F423E, '18', 'mybook', '1');
INSERT INTO `tg_cms_templet` VALUES ('923', '我的书包列表页', '', '2010-08-02', '2010-08-02', 0x3C424C4F423E, '18', 'mybookmore', '1');
