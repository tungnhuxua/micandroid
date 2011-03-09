/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:54:59
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
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
