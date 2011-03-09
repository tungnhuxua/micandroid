/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:24
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
INSERT INTO `tb_site_message_info` VALUES ('1', '删除留言', '如何删除别人给我的留言', '0', '11', '2', '1', '1', '2010-07-11');
INSERT INTO `tb_site_message_info` VALUES ('2', '回复删除', '进入管理平台，留言管理删除留言', '1', '22', '2', '1', '1', '2010-07-12');
INSERT INTO `tb_site_message_info` VALUES ('3', '你的QQ', '请问你的QQ号码多少？', '0', '33', '1', '2', '2', '2010-07-20');
