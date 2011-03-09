/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:39
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
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
