/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:56:02
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
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
