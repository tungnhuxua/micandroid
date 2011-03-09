/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-15 15:05:44
*/

SET FOREIGN_KEY_CHECKS=0;
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
  `MODEL_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模型ID',
  `UPDATE_TIME_` date DEFAULT NULL COMMENT '内容更新时间',
  `TAG_EXP_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签说明',
  `ORDER_TYPE_` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '排序类型',
  `VERSION_ID_` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模板本版ID',
  `COMPANY_ID_` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '发布单位',
  `list_destroy_` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '标题样式 1, ''标题'',2, ''序列+标题  3, ''标题+缩略图  4,序列+标题+缩略图 5  全标题, 6, 序列+全标题''  ',
  `MAKE_PEOPLE_` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '制作人',
  `MAKE_TIME_` date DEFAULT NULL COMMENT '制作时间',
  `CONTINUE_MODE_NO_` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '小说连载状态',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tc_tag_list_info` VALUES ('201', '284', '$catid', '图片列表', '0', '3', null, '15', null, '图片列表', 'ORDER BY id_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('222', '263', '$catid', '图片内容', '0', '20', null, '15', null, '图片内容', null, '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('242', '364', '17', '图片最新上架', '1', '6', null, '15', null, '图片最新上架', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('261', '364', '17', '图片上榜下载TOP10', '1', '4', null, '15', null, '图片按点击量排序', 'ORDER BY id_ ASC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('281', '241', '100', '资讯本地子栏目', '1', '4', null, '10', null, '资讯首页-本地子栏目', 'ORDER BY create_time_ ASC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('282', '241', '10', '资讯栏目最新内容', '1', '5', null, '10', null, '资讯首页-栏目最新内容', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('301', '241', '101', '资讯国内子栏目', '1', '3', null, '10', null, '资讯首页-国内子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('303', '241', '103', '资讯财经子栏目', '1', '3', null, '10', null, '资讯首页-财经子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('304', '241', '106', '资讯娱乐子栏目', '1', '3', null, '10', null, '资讯首页-娱乐子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('305', '241', '105', '资讯体育子栏目', '1', '3', null, '10', null, '资讯首页-体育子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('306', '241', '107', '资讯健康子栏目', '1', '3', null, '10', null, '资讯首页-健康子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('307', '241', '108', '资讯教育子栏目', '1', '3', null, '10', null, '资讯首页-教育子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('308', '241', '104', '资讯科技子栏目', '1', '3', null, '10', null, '资讯首页-科技子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('309', '241', '109', '资讯汽车子栏目', '1', '3', null, '10', null, '资讯首页-汽车子栏目', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('310', '241', '$catid', '资讯列表', '0', '20', null, '10', null, '资讯列表页', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('321', '481', '100', '首页本地栏目内容信息', '1', '3', null, '10', null, '首页本地栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('322', '481', '101', '首页国内栏目内容信息', '1', '2', null, '10', null, '首页国内栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('323', '481', '102', '首页环球栏目内容信息', '1', '1', null, '10', null, '首页环球栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('324', '481', '106', '首页娱乐栏目内容信息', '1', '2', null, '10', null, '首页娱乐栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('325', '481', '105', '首页体育栏目内容信息', '1', '2', null, '10', null, '首页体育栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('326', '481', '108', '首页教育栏目内容信息', '1', '2', null, '10', null, '首页教育栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('327', '481', '107', '首页健康栏目内容信息', '1', '2', null, '10', null, '首页健康栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('341', '561', '$catid', '铃声内容列表', '0', '20', null, '14', null, '铃声栏目列表页展现', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('361', '661', '200', '音乐栏目最新内容', '1', '10', null, '13', null, '音乐首页-栏目最新内容', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('381', '561', '16', '铃声首页最新内容列表', '1', '10', null, '14', null, '铃声首页最新内容列表', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('382', '561', '16', '铃声首页下载排行列表', '1', '10', null, '14', null, '铃声首页下载排行列表', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('383', '661', '15', '音乐栏目内容下载量', '1', '10', null, '13', null, '音乐首页栏目内容下载量', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('401', '661', '$catid', '音乐列表', '0', '20', null, '13', null, '音乐列表页', null, '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('421', '364', '13', '软件最新栏目数据', '1', '6', null, '12', null, '取软件栏目下所有子栏目最新数据', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('422', '364', '12', '主题最新栏目数据', '1', '6', null, '12', null, '取软件栏目下所有子栏目最新数据', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('423', '364', '12', '主题最热栏目数据', '1', '6', null, '12', null, '取软件栏目下所有子栏目最热数据', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('424', '364', '13', '软件最热栏目数据', '1', '6', null, '12', null, '取软件栏目下所有子栏目最热数据', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('441', '702', '$catid', '书城列表页标签', '0', '20', null, '11', null, '栏目列表页，带分页', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('442', '284', '$catid', '软件列表', '0', '4', null, '12', null, '软件列表页面', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('443', '284', '$catid', '主题列表', '0', '4', null, '12', null, '主题列表页面', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('461', '1141', '11', '书城最新连载列表', '1', '7', null, '11', null, '书城最新连载列表', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, '11');
INSERT INTO `tc_tag_list_info` VALUES ('462', '1141', '11', '书城新书上架列表', '1', '7', null, '11', null, '书城新书上架列表', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('463', '1141', '11', '书城阅读排名列表', '1', '10', null, '11', null, '书城阅读排名列表', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('464', '1141', '11', '书城小说上榜列表', '1', '7', null, '11', null, '书城小说上榜列表', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, '16');
INSERT INTO `tc_tag_list_info` VALUES ('465', '743', '14', '游戏栏目最新内容', '1', '10', null, '12', null, '游戏首页-栏目最新内容', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('466', '743', '14', '游戏栏目内容下载量', '1', '10', null, '12', null, '游戏首页-栏目内容下载量', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('467', '702', '$catid', '书城全本小说列表', '0', '20', null, '11', null, '书城全本小说列表，带分页', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, '16');
INSERT INTO `tc_tag_list_info` VALUES ('468', '702', '$catid', '书城连载小说列表', '0', '20', null, '11', null, '书城连载小说列表，带分页', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, '11');
INSERT INTO `tc_tag_list_info` VALUES ('481', '743', '$catid', '游戏列表', '0', '4', null, '12', null, '游戏列表页', null, '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('501', '702', '$catid', '书城内容页推荐阅读', '1', '5', null, '11', null, null, 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('521', '661', '15', '音乐详情TOP下载', '1', '5', null, '13', null, '音乐详情页TOP下载', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('522', '743', '14', '游戏详情TOP下载', '1', '5', null, '12', null, '游戏详情页TOP下载', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('541', '1081', '15', '音乐栏目标签列表', '0', '10', null, '13', null, '音乐栏目标签列表页', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('561', '1081', '12', '主题栏目标签列表', '0', '10', null, '12', null, '主题栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('562', '1081', '13', '软件栏目标签列表', '0', '10', null, '12', null, '软件栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('563', '1081', '14', '游戏栏目标签列表', '0', '10', null, '12', null, '游戏栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('564', '1081', '16', '铃声栏目标签列表', '0', '10', null, '14', null, '铃声栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('565', '1081', '17', '图片栏目标签列表', '0', '10', null, '15', null, '图片栏目标签列表页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('581', '481', '109', '首页汽车栏目内容信息', '1', '3', null, '10', null, '首页汽车栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('582', '481', '103', '首页财经栏目内容信息', '1', '2', null, '10', null, '首页财经栏目内容信息', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('601', '702', '11', '书城新书列表', '0', '20', null, '11', null, '全部新书,带分页', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('602', '702', '11', '书城排行列表', '0', '20', null, '11', null, '全部小说排行,带分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('621', '561', '16', '铃声最新列表', '0', '20', null, '14', null, '全部最新铃声，带分页', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('622', '561', '16', '铃声热门列表', '0', '20', null, '14', null, '按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('641', '743', '14', '游戏新品列表', '0', '20', null, '12', null, '最新游戏；分页', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('642', '661', '15', '音乐最新列表', '0', '20', null, '13', null, '最新音乐；分页', 'ORDER BY create_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('643', '743', '14', '游戏排行列表', '0', '20', null, '12', null, '游戏排行分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('644', '661', '15', '音乐排行列表', '0', '20', null, '13', null, '音乐排行分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('661', '284', '17', '图片热门列表', '0', '4', null, '15', null, '图片按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('662', '743', '13', '软件新书列表', '0', '10', null, '12', null, '全部新软件,带分页', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('664', '743', '12', '主题最新列表', '0', '10', null, '12', null, '全部新主题,带分页', 'ORDER BY modify_time_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('665', '743', '12', '主题热门列表', '0', '10', null, '12', null, '主题按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('666', '743', '13', '软件热门列表', '0', '10', null, '12', null, '软件按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('667', '743', '14', '游戏热门列表', '0', '10', null, '12', null, '游戏按点击量排序，带分页', 'ORDER BY view_count_ DESC', '1', null, '1', null, null, null);
INSERT INTO `tc_tag_list_info` VALUES ('668', '923', '17', '新闻最新列表', '1', '5', null, '15', '2010-09-15', '新闻,带分页', 'ORDER BY modify_time_ DESC', '1', null, '6', null, null, '');
INSERT INTO `tc_tag_list_info` VALUES ('669', '921', '1', '资讯列表标签', '0', '5', null, '10', '2010-09-15', '资讯列表标签', 'ORDER BY id_ ASC', '1', null, '2', null, '2010-09-15', '');
