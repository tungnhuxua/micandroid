/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 14:52:49
*/

SET FOREIGN_KEY_CHECKS=0;
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
