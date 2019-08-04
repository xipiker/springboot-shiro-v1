/*
 Navicat MySQL Data Transfer

 Source Server         : xipiker
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : springboot_shiro_v1

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 04/08/2019 18:53:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for springShiroUser
-- ----------------------------
DROP TABLE IF EXISTS `springShiroUser`;
CREATE TABLE `springShiroUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `perms` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of springShiroUser
-- ----------------------------
BEGIN;
INSERT INTO `springShiroUser` VALUES (1, 'xipiker', '123456', 'user:add');
INSERT INTO `springShiroUser` VALUES (2, 'admin', 'admin', 'user:update');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
