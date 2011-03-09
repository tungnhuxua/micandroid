/*
MySQL Data Transfer
Source Host: localhost
Source Database: mmbook
Target Host: localhost
Target Database: mmbook
Date: 2010-9-13 12:57:55
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
