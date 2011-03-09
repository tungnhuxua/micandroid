/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:45
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
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
