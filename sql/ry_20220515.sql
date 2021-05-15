/*
 Navicat Premium Data Transfer

 Source Server         : media
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 47.115.49.189:3306
 Source Schema         : ruoyi-media

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 15/05/2021 11:45:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (15, 'website_banner', '首页banner表', NULL, NULL, 'WebsiteBanner', 'crud', 'com.ruoyi.website', 'website', 'banner', '首页banner', 'thh', '0', '/', '{}', 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03', NULL);
INSERT INTO `gen_table` VALUES (17, 'website_link', '站点友情链接', NULL, NULL, 'WebsiteLink', 'crud', 'com.ruoyi.website', 'website', 'link', '站点友情链接', 'thh', '0', '/', '{}', 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 230 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (197, '15', 'banner_id', 'ID', 'int(20)', 'Long', 'bannerId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (198, '15', 'title', '标题', 'varchar(20)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (199, '15', 'image_url', '图片地址', 'varchar(500)', 'String', 'imageUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (200, '15', 'link_url', '链接地址', 'varchar(500)', 'String', 'linkUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (201, '15', 'sort', '排序', 'int(10) unsigned', 'Integer', 'sort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (202, '15', 'del_flag', '逻辑删除 1（true）已删除， 0（false）未删除', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (203, '15', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (204, '15', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (205, '15', 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (206, '15', 'update_by', '更新人', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (207, '15', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2021-05-05 15:35:21', '', '2021-05-05 15:56:03');
INSERT INTO `gen_table_column` VALUES (219, '17', 'link_id', '主键', 'bigint(20)', 'Long', 'linkId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (220, '17', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 2, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (221, '17', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 3, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (222, '17', 'status', '状态(1有效, 0无效)', 'tinyint(3) unsigned', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 4, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (223, '17', 'sort', '排序', 'int(11)', 'Long', 'sort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (224, '17', 'link_name', '名称', 'varchar(50)', 'String', 'linkName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (225, '17', 'link_url', '链接', 'varchar(255)', 'String', 'linkUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (226, '17', 'link_target', '跳转方式(_blank，_self)', 'varchar(50)', 'String', 'linkTarget', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (227, '17', 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (228, '17', 'update_by', '更新人', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');
INSERT INTO `gen_table_column` VALUES (229, '17', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2021-05-05 16:17:20', '', '2021-05-05 16:17:40');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fired_time` bigint(0) NOT NULL,
  `sched_time` bigint(0) NOT NULL,
  `priority` int(0) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001791DD0D58078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001791DD0D58078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001791DD0D58078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_checkin_time` bigint(0) NOT NULL,
  `checkin_interval` bigint(0) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'DESKTOP-MJHPK561621049097110', 1621050347460, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `repeat_count` bigint(0) NOT NULL,
  `repeat_interval` bigint(0) NOT NULL,
  `times_triggered` bigint(0) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(0) NULL DEFAULT NULL,
  `int_prop_2` int(0) NULL DEFAULT NULL,
  `long_prop_1` bigint(0) NULL DEFAULT NULL,
  `long_prop_2` bigint(0) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(0) NULL DEFAULT NULL,
  `prev_fire_time` bigint(0) NULL DEFAULT NULL,
  `priority` int(0) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `start_time` bigint(0) NOT NULL,
  `end_time` bigint(0) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(0) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1621049100000, -1, 5, 'PAUSED', 'CRON', 1621049098000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 1621049100000, -1, 5, 'PAUSED', 'CRON', 1621049099000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 1621049100000, -1, 5, 'PAUSED', 'CRON', 1621049099000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-04-29 21:28:15', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-04-29 21:28:15', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2021-04-29 21:28:15', '', NULL, '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-04-29 21:28:12', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(0) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 0, '中国', 'china', 'movie_country', NULL, NULL, 'N', '0', 'admin', '2021-05-01 17:55:38', 'admin', '2021-05-01 17:56:52', '中国');
INSERT INTO `sys_dict_data` VALUES (101, 0, '美国', 'american', 'movie_country', NULL, NULL, 'N', '0', 'admin', '2021-05-01 17:56:33', '', NULL, '美国');
INSERT INTO `sys_dict_data` VALUES (102, 0, '日本', 'japan', 'movie_country', NULL, NULL, 'N', '0', 'admin', '2021-05-01 17:57:35', '', NULL, '日本');
INSERT INTO `sys_dict_data` VALUES (103, 0, '韩国', 'korea', 'movie_country', NULL, NULL, 'N', '0', 'admin', '2021-05-01 17:58:08', '', NULL, '韩国');
INSERT INTO `sys_dict_data` VALUES (104, 0, '印度', 'india', 'movie_country', NULL, NULL, 'N', '0', 'admin', '2021-05-01 17:58:34', '', NULL, '印度');
INSERT INTO `sys_dict_data` VALUES (105, 0, '正常', '0', 'wm_movie_status', NULL, NULL, 'N', '0', 'admin', '2021-05-01 18:08:22', '', NULL, '正常');
INSERT INTO `sys_dict_data` VALUES (106, 0, '关闭', '1', 'wm_movie_status', NULL, NULL, 'N', '0', 'admin', '2021-05-01 18:08:32', '', NULL, '关闭');
INSERT INTO `sys_dict_data` VALUES (107, 0, '电视剧', 'teleplay', 'wm_movie_type', NULL, NULL, 'N', '0', 'admin', '2021-05-01 20:15:56', '', NULL, '电视剧');
INSERT INTO `sys_dict_data` VALUES (108, 0, '电影', ' film', 'wm_movie_type', NULL, NULL, 'N', '0', 'admin', '2021-05-01 20:16:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (109, 0, '户外', 'outdoor', 'movie_label', NULL, NULL, 'N', '0', 'admin', '2021-05-13 23:08:32', 'admin', '2021-05-13 23:25:18', '户外');
INSERT INTO `sys_dict_data` VALUES (110, 0, '功夫', 'KungFu', 'movie_label', NULL, NULL, 'N', '0', 'admin', '2021-05-13 23:08:55', 'admin', '2021-05-13 23:25:23', '1');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2021-04-29 21:28:15', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '电影国家', 'movie_country', '0', 'admin', '2021-05-01 17:53:34', '', NULL, '电影国家列表');
INSERT INTO `sys_dict_type` VALUES (101, '电影状态', 'wm_movie_status', '0', 'admin', '2021-05-01 18:07:17', '', NULL, '电影状态');
INSERT INTO `sys_dict_type` VALUES (102, '电影类型', 'wm_movie_type', '0', 'admin', '2021-05-01 20:15:13', '', NULL, '电影类型');
INSERT INTO `sys_dict_type` VALUES (103, '电影标签', 'movie_label', '0', 'admin', '2021-05-13 23:06:49', '', NULL, '电影标签');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2021-04-29 21:28:16', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2021-04-29 21:28:16', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2021-04-29 21:28:16', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 182 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-29 22:32:34');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-04-30 19:36:01');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-30 19:36:06');
INSERT INTO `sys_logininfor` VALUES (103, 'ry', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-30 19:38:13');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-30 20:13:49');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-01 08:16:41');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-01 09:11:47');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-01 09:24:37');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-01 15:48:06');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-01 16:34:43');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-01 20:14:48');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 08:58:16');
INSERT INTO `sys_logininfor` VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 10:00:12');
INSERT INTO `sys_logininfor` VALUES (113, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 10:00:40');
INSERT INTO `sys_logininfor` VALUES (114, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 10:03:32');
INSERT INTO `sys_logininfor` VALUES (115, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 10:43:01');
INSERT INTO `sys_logininfor` VALUES (116, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 10:52:56');
INSERT INTO `sys_logininfor` VALUES (117, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 11:02:50');
INSERT INTO `sys_logininfor` VALUES (118, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 11:22:05');
INSERT INTO `sys_logininfor` VALUES (119, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 11:59:29');
INSERT INTO `sys_logininfor` VALUES (120, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 12:59:40');
INSERT INTO `sys_logininfor` VALUES (121, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 13:17:17');
INSERT INTO `sys_logininfor` VALUES (122, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 13:21:53');
INSERT INTO `sys_logininfor` VALUES (123, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 13:36:32');
INSERT INTO `sys_logininfor` VALUES (124, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 14:02:15');
INSERT INTO `sys_logininfor` VALUES (125, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 14:09:29');
INSERT INTO `sys_logininfor` VALUES (126, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 14:16:22');
INSERT INTO `sys_logininfor` VALUES (127, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 14:19:43');
INSERT INTO `sys_logininfor` VALUES (128, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 14:28:38');
INSERT INTO `sys_logininfor` VALUES (129, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 14:45:03');
INSERT INTO `sys_logininfor` VALUES (130, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 14:45:46');
INSERT INTO `sys_logininfor` VALUES (131, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 14:50:37');
INSERT INTO `sys_logininfor` VALUES (132, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码已失效', '2021-05-02 14:54:02');
INSERT INTO `sys_logininfor` VALUES (133, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-05-02 14:54:06');
INSERT INTO `sys_logininfor` VALUES (134, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 14:54:10');
INSERT INTO `sys_logininfor` VALUES (135, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 15:01:22');
INSERT INTO `sys_logininfor` VALUES (136, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 15:12:30');
INSERT INTO `sys_logininfor` VALUES (137, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 15:14:54');
INSERT INTO `sys_logininfor` VALUES (138, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-02 15:26:10');
INSERT INTO `sys_logininfor` VALUES (139, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-05-02 15:29:50');
INSERT INTO `sys_logininfor` VALUES (140, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-05-02 15:29:51');
INSERT INTO `sys_logininfor` VALUES (141, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-02 15:29:53');
INSERT INTO `sys_logininfor` VALUES (142, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 08:52:01');
INSERT INTO `sys_logininfor` VALUES (143, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 09:36:50');
INSERT INTO `sys_logininfor` VALUES (144, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-03 09:41:35');
INSERT INTO `sys_logininfor` VALUES (145, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-05-03 09:43:31');
INSERT INTO `sys_logininfor` VALUES (146, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 09:43:35');
INSERT INTO `sys_logininfor` VALUES (147, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 11:04:49');
INSERT INTO `sys_logininfor` VALUES (148, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 11:13:57');
INSERT INTO `sys_logininfor` VALUES (149, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 11:35:45');
INSERT INTO `sys_logininfor` VALUES (150, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 11:54:35');
INSERT INTO `sys_logininfor` VALUES (151, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 12:33:46');
INSERT INTO `sys_logininfor` VALUES (152, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 12:37:44');
INSERT INTO `sys_logininfor` VALUES (153, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 13:05:24');
INSERT INTO `sys_logininfor` VALUES (154, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-05-03 13:07:49');
INSERT INTO `sys_logininfor` VALUES (155, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 13:07:53');
INSERT INTO `sys_logininfor` VALUES (156, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 14:03:18');
INSERT INTO `sys_logininfor` VALUES (157, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 14:17:24');
INSERT INTO `sys_logininfor` VALUES (158, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 14:22:37');
INSERT INTO `sys_logininfor` VALUES (159, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 14:35:52');
INSERT INTO `sys_logininfor` VALUES (160, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 16:20:07');
INSERT INTO `sys_logininfor` VALUES (161, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 16:31:21');
INSERT INTO `sys_logininfor` VALUES (162, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-03 16:35:34');
INSERT INTO `sys_logininfor` VALUES (163, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-04 21:25:38');
INSERT INTO `sys_logininfor` VALUES (164, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-04 21:44:45');
INSERT INTO `sys_logininfor` VALUES (165, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-04 22:56:05');
INSERT INTO `sys_logininfor` VALUES (166, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-05 14:43:48');
INSERT INTO `sys_logininfor` VALUES (167, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-05 15:29:02');
INSERT INTO `sys_logininfor` VALUES (168, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-05 16:23:13');
INSERT INTO `sys_logininfor` VALUES (169, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-05 18:02:45');
INSERT INTO `sys_logininfor` VALUES (170, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-05 18:15:19');
INSERT INTO `sys_logininfor` VALUES (171, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-05 18:21:26');
INSERT INTO `sys_logininfor` VALUES (172, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-05 19:05:58');
INSERT INTO `sys_logininfor` VALUES (173, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2021-05-09 09:15:34');
INSERT INTO `sys_logininfor` VALUES (174, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-09 09:15:38');
INSERT INTO `sys_logininfor` VALUES (175, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-09 09:15:56');
INSERT INTO `sys_logininfor` VALUES (176, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-09 10:01:48');
INSERT INTO `sys_logininfor` VALUES (177, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-09 10:02:42');
INSERT INTO `sys_logininfor` VALUES (178, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-13 23:00:04');
INSERT INTO `sys_logininfor` VALUES (179, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-13 23:14:38');
INSERT INTO `sys_logininfor` VALUES (180, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-05-13 23:24:33');
INSERT INTO `sys_logininfor` VALUES (181, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-13 23:24:37');
INSERT INTO `sys_logininfor` VALUES (182, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:01:11');
INSERT INTO `sys_logininfor` VALUES (183, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-05-14 13:01:15');
INSERT INTO `sys_logininfor` VALUES (184, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:01:19');
INSERT INTO `sys_logininfor` VALUES (185, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码已失效', '2021-05-14 13:03:52');
INSERT INTO `sys_logininfor` VALUES (186, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:03:56');
INSERT INTO `sys_logininfor` VALUES (187, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:14:20');
INSERT INTO `sys_logininfor` VALUES (188, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:19:59');
INSERT INTO `sys_logininfor` VALUES (189, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:29:52');
INSERT INTO `sys_logininfor` VALUES (190, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:31:29');
INSERT INTO `sys_logininfor` VALUES (191, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:33:51');
INSERT INTO `sys_logininfor` VALUES (192, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-05-14 13:34:43');
INSERT INTO `sys_logininfor` VALUES (193, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-05-14 13:34:50');
INSERT INTO `sys_logininfor` VALUES (194, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:36:40');
INSERT INTO `sys_logininfor` VALUES (195, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 13:44:14');
INSERT INTO `sys_logininfor` VALUES (196, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 01:48:15');
INSERT INTO `sys_logininfor` VALUES (197, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 03:11:28');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(0) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(0) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2027 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', '0', '', 'system', 'admin', '2021-04-29 21:28:13', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2021-04-29 21:28:13', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2021-04-29 21:28:13', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 4, 'http://ruoyi.vip', NULL, 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2021-04-29 21:28:13', '', NULL, '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2021-04-29 21:28:13', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2021-04-29 21:28:13', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2021-04-29 21:28:13', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2021-04-29 21:28:13', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2021-04-29 21:28:13', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2021-04-29 21:28:13', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2021-04-29 21:28:13', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2021-04-29 21:28:13', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2021-04-29 21:28:13', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2021-04-29 21:28:13', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2021-04-29 21:28:13', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2021-04-29 21:28:13', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2021-04-29 21:28:13', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2021-04-29 21:28:13', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2021-04-29 21:28:13', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2021-04-29 21:28:13', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2021-04-29 21:28:13', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2021-04-29 21:28:13', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2021-04-29 21:28:13', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '媒资管理', 0, 5, 'media', NULL, 1, 0, 'M', '0', '0', '', 'tab', 'admin', '2021-05-01 16:52:09', 'admin', '2021-05-03 09:35:51', '');
INSERT INTO `sys_menu` VALUES (2008, '电影管理', 2000, 1, 'movie', 'media/movie/index', 1, 0, 'C', '0', '0', 'media:movie:list', 'table', 'admin', '2021-05-01 17:20:43', 'admin', '2021-05-01 17:32:45', '电影管理菜单');
INSERT INTO `sys_menu` VALUES (2009, '电影管理查询', 2008, 1, '#', '', 1, 0, 'F', '0', '0', 'media:movie:query', '#', 'admin', '2021-05-01 17:20:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2010, '电影管理新增', 2008, 2, '#', '', 1, 0, 'F', '0', '0', 'media:movie:add', '#', 'admin', '2021-05-01 17:20:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '电影管理修改', 2008, 3, '#', '', 1, 0, 'F', '0', '0', 'media:movie:edit', '#', 'admin', '2021-05-01 17:20:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '电影管理删除', 2008, 4, '#', '', 1, 0, 'F', '0', '0', 'media:movie:remove', '#', 'admin', '2021-05-01 17:20:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '电影管理导出', 2008, 5, '#', '', 1, 0, 'F', '0', '0', 'media:movie:export', '#', 'admin', '2021-05-01 17:20:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2014, '首页banner', 2026, 1, 'banner', 'website/banner/index', 1, 0, 'C', '0', '0', 'website:banner:list', '#', 'admin', '2021-05-05 16:00:55', 'admin', '2021-05-05 16:05:48', '首页banner菜单');
INSERT INTO `sys_menu` VALUES (2015, '首页banner查询', 2014, 1, '#', '', 1, 0, 'F', '0', '0', 'website:banner:query', '#', 'admin', '2021-05-05 16:00:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2016, '首页banner新增', 2014, 2, '#', '', 1, 0, 'F', '0', '0', 'website:banner:add', '#', 'admin', '2021-05-05 16:00:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2017, '首页banner修改', 2014, 3, '#', '', 1, 0, 'F', '0', '0', 'website:banner:edit', '#', 'admin', '2021-05-05 16:00:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2018, '首页banner删除', 2014, 4, '#', '', 1, 0, 'F', '0', '0', 'website:banner:remove', '#', 'admin', '2021-05-05 16:00:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2019, '首页banner导出', 2014, 5, '#', '', 1, 0, 'F', '0', '0', 'website:banner:export', '#', 'admin', '2021-05-05 16:00:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2020, '站点友情链接', 2026, 1, 'link', 'website/link/index', 1, 0, 'C', '0', '0', 'website:link:list', '#', 'admin', '2021-05-05 16:01:12', 'admin', '2021-05-05 16:05:56', '站点友情链接菜单');
INSERT INTO `sys_menu` VALUES (2021, '站点友情链接查询', 2020, 1, '#', '', 1, 0, 'F', '0', '0', 'website:link:query', '#', 'admin', '2021-05-05 16:01:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '站点友情链接新增', 2020, 2, '#', '', 1, 0, 'F', '0', '0', 'website:link:add', '#', 'admin', '2021-05-05 16:01:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '站点友情链接修改', 2020, 3, '#', '', 1, 0, 'F', '0', '0', 'website:link:edit', '#', 'admin', '2021-05-05 16:01:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2024, '站点友情链接删除', 2020, 4, '#', '', 1, 0, 'F', '0', '0', 'website:link:remove', '#', 'admin', '2021-05-05 16:01:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '站点友情链接导出', 2020, 5, '#', '', 1, 0, 'F', '0', '0', 'website:link:export', '#', 'admin', '2021-05-05 16:01:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2026, '站点配置', 0, 6, 'website', NULL, 1, 0, 'M', '0', '0', NULL, 'guide', 'admin', '2021-05-05 16:05:22', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2021-04-29 21:28:16', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2021-04-29 21:28:16', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(0) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(0) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(0) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 411 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (308, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":24,\"movieId\":94,\"filesize\":57707514,\"params\":{},\"title\":\"第二集\",\"url\":\"/profile/movie/video/2021/05/03/12b5e237-5175-4b5c-a8a6-e069f8c8a66c.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/03/67e0dd69-7851-4e98-a127-1733b86d10bd.jpg\",\"updateTime\":1620525775088,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:02:55');
INSERT INTO `sys_oper_log` VALUES (309, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/03/67e0dd69-7851-4e98-a127-1733b86d10bd.jpg\",\"updateTime\":1620525776920,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:02:56');
INSERT INTO `sys_oper_log` VALUES (310, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/43fe8d61-43f5-469e-bdef-ae996efb0751.jpg\"}', 0, NULL, '2021-05-09 10:03:09');
INSERT INTO `sys_oper_log` VALUES (311, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":68871355,\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}}', 0, NULL, '2021-05-09 10:05:21');
INSERT INTO `sys_oper_log` VALUES (312, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/09/43fe8d61-43f5-469e-bdef-ae996efb0751.jpg\",\"updateTime\":1620525924998,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:05:25');
INSERT INTO `sys_oper_log` VALUES (313, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"},{\"ext\":\"mp4\",\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/09/43fe8d61-43f5-469e-bdef-ae996efb0751.jpg\",\"updateTime\":1620525959104,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:05:59');
INSERT INTO `sys_oper_log` VALUES (314, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":26,\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/09/43fe8d61-43f5-469e-bdef-ae996efb0751.jpg\",\"updateTime\":1620525979971,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:06:20');
INSERT INTO `sys_oper_log` VALUES (315, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":26,\"length\":\"180分钟1\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/09/43fe8d61-43f5-469e-bdef-ae996efb0751.jpg\",\"updateTime\":1620525988530,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:06:28');
INSERT INTO `sys_oper_log` VALUES (316, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":26,\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/09/43fe8d61-43f5-469e-bdef-ae996efb0751.jpg\",\"updateTime\":1620525997224,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:06:37');
INSERT INTO `sys_oper_log` VALUES (317, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":26,\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/09/43fe8d61-43f5-469e-bdef-ae996efb0751.jpg\",\"updateTime\":1620525999395,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:06:39');
INSERT INTO `sys_oper_log` VALUES (318, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":66473699,\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"}}', 0, NULL, '2021-05-09 10:07:07');
INSERT INTO `sys_oper_log` VALUES (319, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"wmMovieVideoList\":[{\"ext\":\"1221\",\"movieVideoId\":7,\"length\":\"2121\",\"movieId\":95,\"filesize\":12212,\"params\":{},\"title\":\"122121\",\"url\":\"/profile/movie/video/2021/05/03/c015f4a4-d306-4a2e-a83e-bbbb798d5d6c.mp4\"},{\"ext\":\"mp4\",\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"}],\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/03/a9109a8e-f31b-4dd3-985a-54ced7169f90.png\",\"updateTime\":1620526028532,\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:07:08');
INSERT INTO `sys_oper_log` VALUES (320, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"}],\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/03/a9109a8e-f31b-4dd3-985a-54ced7169f90.png\",\"updateTime\":1620526031160,\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:07:11');
INSERT INTO `sys_oper_log` VALUES (321, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"}],\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/03/a9109a8e-f31b-4dd3-985a-54ced7169f90.png\",\"updateTime\":1620526039794,\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:07:19');
INSERT INTO `sys_oper_log` VALUES (322, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":148267788,\"title\":\"09_支付模块构建(中)\",\"url\":\"/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4\"}}', 0, NULL, '2021-05-09 10:07:35');
INSERT INTO `sys_oper_log` VALUES (323, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"},{\"ext\":\"mp4\",\"movieId\":95,\"filesize\":148267788,\"params\":{},\"title\":\"09_支付模块构建(中)\",\"url\":\"/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4\"}],\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/03/a9109a8e-f31b-4dd3-985a-54ced7169f90.png\",\"updateTime\":1620526057116,\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:07:37');
INSERT INTO `sys_oper_log` VALUES (324, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"},{\"ext\":\"mp4\",\"movieId\":95,\"filesize\":148267788,\"params\":{},\"title\":\"09_支付模块构建(中)\",\"url\":\"/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4\"}],\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/03/a9109a8e-f31b-4dd3-985a-54ced7169f90.png\",\"updateTime\":1620526065044,\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:07:45');
INSERT INTO `sys_oper_log` VALUES (325, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":30032601,\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"}}', 0, NULL, '2021-05-09 10:08:00');
INSERT INTO `sys_oper_log` VALUES (326, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526081286,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:08:01');
INSERT INTO `sys_oper_log` VALUES (327, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526083088,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:08:03');
INSERT INTO `sys_oper_log` VALUES (328, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526094459,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:08:14');
INSERT INTO `sys_oper_log` VALUES (329, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526108806,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:08:28');
INSERT INTO `sys_oper_log` VALUES (330, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526109986,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:08:30');
INSERT INTO `sys_oper_log` VALUES (331, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526115052,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:08:35');
INSERT INTO `sys_oper_log` VALUES (332, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":36350392,\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}}', 0, NULL, '2021-05-09 10:08:44');
INSERT INTO `sys_oper_log` VALUES (333, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526132226,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:08:52');
INSERT INTO `sys_oper_log` VALUES (334, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"},{\"ext\":\"mp4\",\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526140563,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:09:00');
INSERT INTO `sys_oper_log` VALUES (335, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526151569,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:09:11');
INSERT INTO `sys_oper_log` VALUES (336, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"updateTime\":1620526153295,\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:09:13');
INSERT INTO `sys_oper_log` VALUES (337, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":94474050,\"title\":\"98_Nacos之服务提供者注册\",\"url\":\"/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4\"}}', 0, NULL, '2021-05-09 10:09:26');
INSERT INTO `sys_oper_log` VALUES (338, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1121\",\"movieId\":97,\"remark\":\"21212121\",\"readCount\":0,\"title\":\"1\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"length\":\"10分钟\",\"movieId\":97,\"filesize\":94474050,\"params\":{},\"title\":\"98_Nacos之服务提供者注册\",\"url\":\"/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4\"}],\"publishTime\":1620748800000,\"images\":\"/profile/movie/images/2021/05/04/6f6d9773-6c24-453d-8d3d-a5c41ccf0879.jpg\",\"updateTime\":1620526172241,\"params\":{},\"createTime\":1620136010000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:09:32');
INSERT INTO `sys_oper_log` VALUES (339, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1121\",\"movieId\":97,\"remark\":\"21212121\",\"readCount\":0,\"title\":\"1\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"length\":\"10分钟\",\"movieId\":97,\"filesize\":94474050,\"params\":{},\"title\":\"98_Nacos之服务提供者注册\",\"url\":\"/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4\"}],\"publishTime\":1620748800000,\"images\":\"/profile/movie/images/2021/05/04/6f6d9773-6c24-453d-8d3d-a5c41ccf0879.jpg\",\"updateTime\":1620526173211,\"params\":{},\"createTime\":1620136010000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:09:33');
INSERT INTO `sys_oper_log` VALUES (340, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\"}', 0, NULL, '2021-05-09 10:09:50');
INSERT INTO `sys_oper_log` VALUES (341, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...\",\"movieId\":98,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\",\"params\":{},\"createTime\":1620526297301,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:11:37');
INSERT INTO `sys_oper_log` VALUES (342, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":19543974,\"title\":\"104_Nacos之Group分组方案\",\"url\":\"/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4\"}}', 0, NULL, '2021-05-09 10:12:00');
INSERT INTO `sys_oper_log` VALUES (343, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...\",\"movieId\":99,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":99,\"filesize\":19543974,\"params\":{},\"title\":\"104_Nacos之Group分组方案\",\"url\":\"/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\",\"params\":{},\"createTime\":1620526322018,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:12:02');
INSERT INTO `sys_oper_log` VALUES (344, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...\",\"movieId\":100,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":100,\"filesize\":19543974,\"params\":{},\"title\":\"104_Nacos之Group分组方案\",\"url\":\"/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\",\"params\":{},\"createTime\":1620526325228,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:12:05');
INSERT INTO `sys_oper_log` VALUES (345, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/ee426ca2-f86a-4f99-8c38-2b99953ea09d.jpg\"}', 0, NULL, '2021-05-09 10:12:51');
INSERT INTO `sys_oper_log` VALUES (346, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...\",\"movieId\":99,\"readCount\":0,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":51,\"movieId\":99,\"filesize\":19543974,\"params\":{},\"title\":\"104_Nacos之Group分组方案\",\"url\":\"/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/ee426ca2-f86a-4f99-8c38-2b99953ea09d.jpg\",\"updateTime\":1620526373686,\"params\":{},\"createTime\":1620526322000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:12:53');
INSERT INTO `sys_oper_log` VALUES (347, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/18da023a-6f00-48b6-9891-da06df38ba40.jpg\"}', 0, NULL, '2021-05-09 10:13:05');
INSERT INTO `sys_oper_log` VALUES (348, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...\",\"movieId\":100,\"readCount\":0,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":52,\"movieId\":100,\"filesize\":19543974,\"params\":{},\"title\":\"104_Nacos之Group分组方案\",\"url\":\"/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/18da023a-6f00-48b6-9891-da06df38ba40.jpg\",\"updateTime\":1620526387632,\"params\":{},\"createTime\":1620526325000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:13:07');
INSERT INTO `sys_oper_log` VALUES (349, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"侦探柯南是一部动漫类型的，于1996在日本上映，目前的语言版本有。主角工藤新一原本是一位颇具名声的高中生侦探，在目击黑衣组织的地下交易后，准备追踪时却被黑衣人琴酒袭昏，名侦探柯南播放地址获取自网路，如有侵权请联系...\",\"movieId\":97,\"remark\":\"21212121\",\"readCount\":0,\"title\":\"名侦探柯南\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":50,\"length\":\"10分钟\",\"movieId\":97,\"filesize\":94474050,\"params\":{},\"title\":\"98_Nacos之服务提供者注册\",\"url\":\"/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4\"}],\"publishTime\":1620748800000,\"images\":\"/profile/movie/images/2021/05/04/6f6d9773-6c24-453d-8d3d-a5c41ccf0879.jpg\",\"updateTime\":1620526442374,\"params\":{},\"createTime\":1620136010000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:14:02');
INSERT INTO `sys_oper_log` VALUES (350, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...\",\"movieId\":100,\"readCount\":0,\"title\":\"斗罗大陆\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":52,\"movieId\":100,\"filesize\":19543974,\"params\":{},\"title\":\"104_Nacos之Group分组方案\",\"url\":\"/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/18da023a-6f00-48b6-9891-da06df38ba40.jpg\",\"updateTime\":1620526501099,\"params\":{},\"createTime\":1620526325000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:15:01');
INSERT INTO `sys_oper_log` VALUES (351, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系\",\"movieId\":98,\"readCount\":0,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\",\"updateTime\":1620526555117,\"params\":{},\"createTime\":1620526297000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:15:55');
INSERT INTO `sys_oper_log` VALUES (352, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":28422255,\"title\":\"58_Hystrix之服务熔断理论\",\"url\":\"/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4\"}}', 0, NULL, '2021-05-09 10:16:04');
INSERT INTO `sys_oper_log` VALUES (353, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系\",\"movieId\":98,\"readCount\":0,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":98,\"filesize\":28422255,\"params\":{},\"title\":\"58_Hystrix之服务熔断理论\",\"url\":\"/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\",\"updateTime\":1620526564835,\"params\":{},\"createTime\":1620526297000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:16:04');
INSERT INTO `sys_oper_log` VALUES (354, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系\",\"movieId\":98,\"readCount\":0,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":98,\"filesize\":28422255,\"params\":{},\"title\":\"58_Hystrix之服务熔断理论\",\"url\":\"/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\",\"updateTime\":1620526566666,\"params\":{},\"createTime\":1620526297000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:16:06');
INSERT INTO `sys_oper_log` VALUES (355, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系\",\"movieId\":98,\"readCount\":0,\"title\":\"只要你说你爱我真人版\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":54,\"movieId\":98,\"filesize\":28422255,\"params\":{},\"title\":\"58_Hystrix之服务熔断理论\",\"url\":\"/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/14706908-ef0d-4623-bc29-bab513fccb72.jpg\",\"updateTime\":1620526586561,\"params\":{},\"createTime\":1620526297000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:16:26');
INSERT INTO `sys_oper_log` VALUES (356, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/d63c1c56-e2f7-44e4-8d8f-c580bce5640e.jpg\"}', 0, NULL, '2021-05-09 10:16:51');
INSERT INTO `sys_oper_log` VALUES (357, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":17081030,\"title\":\"86_Stream编码常用注解简介\",\"url\":\"/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4\"}}', 0, NULL, '2021-05-09 10:17:38');
INSERT INTO `sys_oper_log` VALUES (358, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"什么叫做爱是一部喜剧片类型的电影，于2013在中国大陆上映，目前的语言版本有汉语普通话。《什么叫做爱》2013年爆笑情感微电影，通过一部神器的电话，引发的爱情，喜剧，励志，情感，动作，惊悚，什么叫做爱播放地址获取自网路，如有侵权请联系...\",\"movieId\":101,\"remark\":\"什么叫做爱是一部喜剧片类型的电影，于2013在中国大陆上映，目前的语言版本有汉语普通话。《什么叫做爱》2013年爆笑情感微电影，通过一部神器的电话，引发的爱情，喜剧，励志，情感，动作，惊悚，什么叫做爱播放地址获取自网路，如有侵权请联系...\",\"title\":\"什么叫做爱\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":101,\"filesize\":17081030,\"params\":{},\"title\":\"86_Stream编码常用注解简介\",\"url\":\"/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4\"}],\"publishTime\":1621526400000,\"images\":\"/profile/movie/images/2021/05/09/d63c1c56-e2f7-44e4-8d8f-c580bce5640e.jpg\",\"params\":{},\"createTime\":1620526659668,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:17:39');
INSERT INTO `sys_oper_log` VALUES (359, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"什么叫做爱是一部喜剧片类型的电影，于2013在中国大陆上映，目前的语言版本有汉语普通话。《什么叫做爱》2013年爆笑情感微电影，通过一部神器的电话，引发的爱情，喜剧，励志，情感，动作，惊悚，什么叫做爱播放地址获取自网路，如有侵权请联系...\",\"movieId\":102,\"remark\":\"什么叫做爱是一部喜剧片类型的电影，于2013在中国大陆上映，目前的语言版本有汉语普通话。《什么叫做爱》2013年爆笑情感微电影，通过一部神器的电话，引发的爱情，喜剧，励志，情感，动作，惊悚，什么叫做爱播放地址获取自网路，如有侵权请联系...\",\"title\":\"什么叫做爱\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":102,\"filesize\":17081030,\"params\":{},\"title\":\"86_Stream编码常用注解简介\",\"url\":\"/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4\"}],\"publishTime\":1621526400000,\"images\":\"/profile/movie/images/2021/05/09/d63c1c56-e2f7-44e4-8d8f-c580bce5640e.jpg\",\"params\":{},\"createTime\":1620526661456,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:17:41');
INSERT INTO `sys_oper_log` VALUES (360, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/0b793efb-395b-4700-bf3b-a12115fbeecb.jpg\"}', 0, NULL, '2021-05-09 10:18:39');
INSERT INTO `sys_oper_log` VALUES (361, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！  本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。 八月未央由稻田影视免费提供在线播放服务\",\"movieId\":102,\"remark\":\"稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！\\n\\n本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。\\n八月未央由稻田影视免费提供在线播放服务\",\"readCount\":0,\"title\":\"八月未央\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":56,\"movieId\":102,\"filesize\":17081030,\"params\":{},\"title\":\"86_Stream编码常用注解简介\",\"url\":\"/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4\"}],\"publishTime\":1621526400000,\"images\":\"/profile/movie/images/2021/05/09/0b793efb-395b-4700-bf3b-a12115fbeecb.jpg\",\"updateTime\":1620526722376,\"params\":{},\"createTime\":1620526661000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:18:42');
INSERT INTO `sys_oper_log` VALUES (362, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/1b7b8d81-178c-485b-986e-d9f32fbe0b66.png\"}', 0, NULL, '2021-05-09 10:21:43');
INSERT INTO `sys_oper_log` VALUES (363, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系\",\"movieId\":98,\"readCount\":0,\"title\":\"只要你说你爱我真人版\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":54,\"movieId\":98,\"filesize\":28422255,\"params\":{},\"title\":\"58_Hystrix之服务熔断理论\",\"url\":\"/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/1b7b8d81-178c-485b-986e-d9f32fbe0b66.png\",\"updateTime\":1620526905494,\"params\":{},\"createTime\":1620526297000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:21:45');
INSERT INTO `sys_oper_log` VALUES (364, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系\",\"movieId\":98,\"readCount\":0,\"title\":\"只要你说你爱我\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":54,\"movieId\":98,\"filesize\":28422255,\"params\":{},\"title\":\"58_Hystrix之服务熔断理论\",\"url\":\"/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/1b7b8d81-178c-485b-986e-d9f32fbe0b66.png\",\"updateTime\":1620526967849,\"params\":{},\"createTime\":1620526297000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:22:47');
INSERT INTO `sys_oper_log` VALUES (365, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系\",\"movieId\":98,\"readCount\":0,\"title\":\"只要你爱我\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":54,\"movieId\":98,\"filesize\":28422255,\"params\":{},\"title\":\"58_Hystrix之服务熔断理论\",\"url\":\"/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/1b7b8d81-178c-485b-986e-d9f32fbe0b66.png\",\"updateTime\":1620526983542,\"params\":{},\"createTime\":1620526297000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:23:03');
INSERT INTO `sys_oper_log` VALUES (366, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/07dfbf32-fb84-43df-97d6-fb9e888d0845.jpg\"}', 0, NULL, '2021-05-09 10:25:11');
INSERT INTO `sys_oper_log` VALUES (367, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...\",\"movieId\":99,\"readCount\":0,\"title\":\"妹妹的诱惑\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":51,\"movieId\":99,\"filesize\":19543974,\"params\":{},\"title\":\"104_Nacos之Group分组方案\",\"url\":\"/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4\"}],\"publishTime\":1620576000000,\"images\":\"/profile/movie/images/2021/05/09/07dfbf32-fb84-43df-97d6-fb9e888d0845.jpg\",\"updateTime\":1620527113870,\"params\":{},\"createTime\":1620526322000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:25:13');
INSERT INTO `sys_oper_log` VALUES (368, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/49a57f2f-e2ed-4304-ad73-5ad8efc0ddab.jpg\"}', 0, NULL, '2021-05-09 10:26:06');
INSERT INTO `sys_oper_log` VALUES (369, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":34,\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":35,\"movieId\":95,\"filesize\":148267788,\"params\":{},\"title\":\"09_支付模块构建(中)\",\"url\":\"/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4\"}],\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/09/49a57f2f-e2ed-4304-ad73-5ad8efc0ddab.jpg\",\"updateTime\":1620527168475,\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:26:08');
INSERT INTO `sys_oper_log` VALUES (370, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/24b378ac-0f26-427c-ab6d-80bd3b40f8a5.jpg\"}', 0, NULL, '2021-05-09 10:26:48');
INSERT INTO `sys_oper_log` VALUES (371, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/d22182bb-7d6a-430c-97b2-3407c46e29d8.jpg\"}', 0, NULL, '2021-05-09 10:27:19');
INSERT INTO `sys_oper_log` VALUES (372, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/927a6123-87dd-4553-b5ad-d7da5b042356.jpg\"}', 0, NULL, '2021-05-09 10:27:52');
INSERT INTO `sys_oper_log` VALUES (373, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！  本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。 八月未央由稻田影视免费提供在线播放服务\",\"movieId\":102,\"remark\":\"稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！\\n\\n本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。\\n八月未央由稻田影视免费提供在线播放服务\",\"readCount\":0,\"title\":\"八月未央\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":56,\"movieId\":102,\"filesize\":17081030,\"params\":{},\"title\":\"86_Stream编码常用注解简介\",\"url\":\"/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4\"}],\"publishTime\":1621526400000,\"images\":\"/profile/movie/images/2021/05/09/927a6123-87dd-4553-b5ad-d7da5b042356.jpg\",\"updateTime\":1620527275059,\"params\":{},\"createTime\":1620526661000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:27:55');
INSERT INTO `sys_oper_log` VALUES (374, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/9e37bc14-864a-4578-886a-97fd1b91db83.jpg\"}', 0, NULL, '2021-05-09 10:28:46');
INSERT INTO `sys_oper_log` VALUES (375, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":34,\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"08_支付模块构建(上)\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":35,\"movieId\":95,\"filesize\":148267788,\"params\":{},\"title\":\"09_支付模块构建(中)\",\"url\":\"/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4\"}],\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/09/9e37bc14-864a-4578-886a-97fd1b91db83.jpg\",\"updateTime\":1620527328917,\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:28:49');
INSERT INTO `sys_oper_log` VALUES (376, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/2c91fb86-3e3d-4f47-8c5e-0d47df101c7a.jpg\"}', 0, NULL, '2021-05-09 10:33:28');
INSERT INTO `sys_oper_log` VALUES (377, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"china\",\"movieType\":\" film\",\"description\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"movieId\":103,\"remark\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"title\":\"正义荣耀\",\"wmMovieVideoList\":[],\"publishTime\":1622044800000,\"images\":\"/profile/movie/images/2021/05/09/2c91fb86-3e3d-4f47-8c5e-0d47df101c7a.jpg\",\"params\":{},\"createTime\":1620527652889,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:34:12');
INSERT INTO `sys_oper_log` VALUES (378, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":22384625,\"title\":\"79_Bus之RabbitMQ环境配置\",\"url\":\"/profile/movie/video/2021/05/09/7c227467-d082-448d-9d27-3a90768153a4.mp4\"}}', 0, NULL, '2021-05-09 10:34:30');
INSERT INTO `sys_oper_log` VALUES (379, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"china\",\"movieType\":\" film\",\"description\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"movieId\":104,\"remark\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"title\":\"正义荣耀\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":104,\"filesize\":22384625,\"params\":{},\"title\":\"79_Bus之RabbitMQ环境配置\",\"url\":\"/profile/movie/video/2021/05/09/7c227467-d082-448d-9d27-3a90768153a4.mp4\"}],\"publishTime\":1622044800000,\"images\":\"/profile/movie/images/2021/05/09/2c91fb86-3e3d-4f47-8c5e-0d47df101c7a.jpg\",\"params\":{},\"createTime\":1620527671752,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:34:31');
INSERT INTO `sys_oper_log` VALUES (380, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/09/ccbdad77-faaa-4aad-bd31-410969e46a62.jpg\"}', 0, NULL, '2021-05-09 10:35:31');
INSERT INTO `sys_oper_log` VALUES (381, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"korea\",\"movieType\":\" film\",\"description\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"movieId\":105,\"remark\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"title\":\"阿衰\",\"wmMovieVideoList\":[],\"publishTime\":1620144000000,\"images\":\"/profile/movie/images/2021/05/09/ccbdad77-faaa-4aad-bd31-410969e46a62.jpg\",\"params\":{},\"createTime\":1620527756676,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:35:56');
INSERT INTO `sys_oper_log` VALUES (382, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":33415892,\"title\":\"89_Stream之消息重复消费\",\"url\":\"/profile/movie/video/2021/05/09/1c419244-aec7-40b2-a34a-3637be923bbf.mp4\"}}', 0, NULL, '2021-05-09 10:36:05');
INSERT INTO `sys_oper_log` VALUES (383, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"korea\",\"movieType\":\" film\",\"description\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"movieId\":106,\"remark\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"title\":\"阿衰\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":106,\"filesize\":33415892,\"params\":{},\"title\":\"89_Stream之消息重复消费\",\"url\":\"/profile/movie/video/2021/05/09/1c419244-aec7-40b2-a34a-3637be923bbf.mp4\"}],\"publishTime\":1620144000000,\"images\":\"/profile/movie/images/2021/05/09/ccbdad77-faaa-4aad-bd31-410969e46a62.jpg\",\"params\":{},\"createTime\":1620527779413,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:36:19');
INSERT INTO `sys_oper_log` VALUES (384, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"korea\",\"movieType\":\" film\",\"description\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"movieId\":107,\"remark\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"title\":\"阿衰\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":107,\"filesize\":33415892,\"params\":{},\"title\":\"89_Stream之消息重复消费\",\"url\":\"/profile/movie/video/2021/05/09/1c419244-aec7-40b2-a34a-3637be923bbf.mp4\"}],\"publishTime\":1620144000000,\"images\":\"/profile/movie/images/2021/05/09/ccbdad77-faaa-4aad-bd31-410969e46a62.jpg\",\"params\":{},\"createTime\":1620527781217,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-09 10:36:21');
INSERT INTO `sys_oper_log` VALUES (385, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"电影标签\",\"remark\":\"电影标签\",\"params\":{},\"dictType\":\"movie_label\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:06:49');
INSERT INTO `sys_oper_log` VALUES (386, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"户外\",\"dictSort\":0,\"remark\":\"户外\",\"params\":{},\"dictType\":\"movie_label\",\"dictLabel\":\"outdoor\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:08:32');
INSERT INTO `sys_oper_log` VALUES (387, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"功夫\",\"dictSort\":0,\"remark\":\"1\",\"params\":{},\"dictType\":\"movie_label\",\"dictLabel\":\"KungFu\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:08:55');
INSERT INTO `sys_oper_log` VALUES (388, '字典类型', 9, 'com.ruoyi.web.controller.system.SysDictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/system/dict/type/clearCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:09:12');
INSERT INTO `sys_oper_log` VALUES (389, '字典数据', 2, 'com.ruoyi.web.controller.system.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"outdoor\",\"dictSort\":0,\"remark\":\"户外\",\"params\":{},\"dictType\":\"movie_label\",\"dictLabel\":\"户外\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1620918512000,\"dictCode\":109,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:18:10');
INSERT INTO `sys_oper_log` VALUES (390, '字典数据', 2, 'com.ruoyi.web.controller.system.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"KungFu\",\"dictSort\":0,\"remark\":\"1\",\"params\":{},\"dictType\":\"movie_label\",\"dictLabel\":\"功夫\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1620918535000,\"dictCode\":110,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:18:18');
INSERT INTO `sys_oper_log` VALUES (391, '字典类型', 9, 'com.ruoyi.web.controller.system.SysDictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/system/dict/type/clearCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:18:23');
INSERT INTO `sys_oper_log` VALUES (392, '字典类型', 9, 'com.ruoyi.web.controller.system.SysDictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/system/dict/type/clearCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:19:45');
INSERT INTO `sys_oper_log` VALUES (393, '字典类型', 9, 'com.ruoyi.web.controller.system.SysDictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/system/dict/type/clearCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:22:21');
INSERT INTO `sys_oper_log` VALUES (394, '字典数据', 2, 'com.ruoyi.web.controller.system.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"outdoor\",\"dictSort\":0,\"remark\":\"户外\",\"params\":{},\"dictType\":\"movie_label\",\"dictLabel\":\"户外\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1620918512000,\"dictCode\":109,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:25:18');
INSERT INTO `sys_oper_log` VALUES (395, '字典数据', 2, 'com.ruoyi.web.controller.system.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"KungFu\",\"dictSort\":0,\"remark\":\"1\",\"params\":{},\"dictType\":\"movie_label\",\"dictLabel\":\"功夫\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1620918535000,\"dictCode\":110,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:25:23');
INSERT INTO `sys_oper_log` VALUES (396, '字典类型', 9, 'com.ruoyi.web.controller.system.SysDictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/system/dict/type/clearCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:25:26');
INSERT INTO `sys_oper_log` VALUES (397, '字典类型', 9, 'com.ruoyi.web.controller.system.SysDictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/system/dict/type/clearCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:25:26');
INSERT INTO `sys_oper_log` VALUES (398, '字典类型', 9, 'com.ruoyi.web.controller.system.SysDictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/system/dict/type/clearCache', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:25:27');
INSERT INTO `sys_oper_log` VALUES (399, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":26,\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/12/001713-15049738339a69.jpg\",\"updateTime\":1620919895033,\"label\":\"11\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:31:35');
INSERT INTO `sys_oper_log` VALUES (400, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":26,\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/12/001713-15049738339a69.jpg\",\"updateTime\":1620920100387,\"label\":\"KungFu\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:35:00');
INSERT INTO `sys_oper_log` VALUES (401, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"侦探柯南是一部动漫类型的，于1996在日本上映，目前的语言版本有。主角工藤新一原本是一位颇具名声的高中生侦探，在目击黑衣组织的地下交易后，准备追踪时却被黑衣人琴酒袭昏，名侦探柯南播放地址获取自网路，如有侵权请联系...\",\"movieId\":97,\"remark\":\"21212121\",\"readCount\":0,\"title\":\"名侦探柯南\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":50,\"length\":\"10分钟\",\"movieId\":97,\"filesize\":94474050,\"params\":{},\"title\":\"98_Nacos之服务提供者注册\",\"url\":\"/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4\"}],\"publishTime\":1620748800000,\"images\":\"/profile/movie/images/2021/05/12/001857-1505578737d340.jpg\",\"updateTime\":1620920107804,\"label\":\"KungFu\",\"params\":{},\"createTime\":1620136010000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:35:07');
INSERT INTO `sys_oper_log` VALUES (402, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"侦探柯南是一部动漫类型的，于1996在日本上映，目前的语言版本有。主角工藤新一原本是一位颇具名声的高中生侦探，在目击黑衣组织的地下交易后，准备追踪时却被黑衣人琴酒袭昏，名侦探柯南播放地址获取自网路，如有侵权请联系...\",\"movieId\":97,\"remark\":\"21212121\",\"readCount\":0,\"title\":\"名侦探柯南\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":50,\"length\":\"10分钟\",\"movieId\":97,\"filesize\":94474050,\"params\":{},\"title\":\"98_Nacos之服务提供者注册\",\"url\":\"/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4\"}],\"publishTime\":1620748800000,\"images\":\"/profile/movie/images/2021/05/12/001857-1505578737d340.jpg\",\"updateTime\":1620921471395,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620136010000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:57:51');
INSERT INTO `sys_oper_log` VALUES (403, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":26,\"length\":\"180分钟\",\"movieId\":94,\"filesize\":68871355,\"params\":{},\"title\":\"01_前言闲聊和课程说明\",\"url\":\"/profile/movie/video/2021/05/09/c119f6ec-1dab-4543-836d-0a8e23aaed01.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/12/001713-15049738339a69.jpg\",\"updateTime\":1620921483360,\"label\":\"outdoor,KungFu\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:58:03');
INSERT INTO `sys_oper_log` VALUES (404, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！  本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。 八月未央由稻田影视免费提供在线播放服务\",\"movieId\":102,\"remark\":\"稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！\\n\\n本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。\\n八月未央由稻田影视免费提供在线播放服务\",\"readCount\":0,\"title\":\"八月未央\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":56,\"movieId\":102,\"filesize\":17081030,\"params\":{},\"title\":\"86_Stream编码常用注解简介\",\"url\":\"/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4\"}],\"publishTime\":1621526400000,\"images\":\"/profile/movie/images/2021/05/12/002858-15014321386902.jpg\",\"updateTime\":1620921523918,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620526661000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:58:44');
INSERT INTO `sys_oper_log` VALUES (405, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"侦探柯南是一部动漫类型的，于1996在日本上映，目前的语言版本有。主角工藤新一原本是一位颇具名声的高中生侦探，在目击黑衣组织的地下交易后，准备追踪时却被黑衣人琴酒袭昏，名侦探柯南播放地址获取自网路，如有侵权请联系...\",\"movieId\":97,\"remark\":\"21212121\",\"readCount\":0,\"title\":\"名侦探柯南\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":50,\"length\":\"10分钟\",\"movieId\":97,\"filesize\":94474050,\"params\":{},\"title\":\"98_Nacos之服务提供者注册\",\"url\":\"/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4\"}],\"publishTime\":1620748800000,\"images\":\"/profile/movie/images/2021/05/12/001857-1505578737d340.jpg\",\"updateTime\":1620921550547,\"label\":\"outdoor\",\"params\":{},\"createTime\":1620136010000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-13 23:59:10');
INSERT INTO `sys_oper_log` VALUES (406, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":47,\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/12/001756-15040234769744.jpg\",\"updateTime\":1620921984059,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 00:06:24');
INSERT INTO `sys_oper_log` VALUES (407, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":47,\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/12/001756-15040234769744.jpg\",\"updateTime\":1620922163162,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 00:09:23');
INSERT INTO `sys_oper_log` VALUES (408, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"korea\",\"movieType\":\" film\",\"description\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"movieId\":105,\"remark\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"readCount\":0,\"title\":\"阿衰\",\"wmMovieVideoList\":[],\"publishTime\":1620144000000,\"images\":\"/profile/movie/images/2021/05/12/003101-15029874610de5.jpg\",\"updateTime\":1620922176580,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620527757000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 00:09:36');
INSERT INTO `sys_oper_log` VALUES (409, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"korea\",\"movieType\":\" film\",\"description\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"movieId\":105,\"remark\":\"阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...\",\"readCount\":0,\"title\":\"阿衰\",\"wmMovieVideoList\":[],\"publishTime\":1620144000000,\"images\":\"/profile/movie/images/2021/05/12/003101-15029874610de5.jpg\",\"updateTime\":1620922195145,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620527757000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 00:09:55');
INSERT INTO `sys_oper_log` VALUES (410, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"93_Sleuth之zipkin搭建安装\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":47,\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"141_Seata业务数据库准备\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/12/001756-15040234769744.jpg\",\"updateTime\":1620922240544,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 00:10:40');
INSERT INTO `sys_oper_log` VALUES (411, '电影封面', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadImages()', 'POST', 1, 'admin', NULL, '/media/movie/upload/images', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"url\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\"}', 0, NULL, '2021-05-14 13:46:48');
INSERT INTO `sys_oper_log` VALUES (412, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\",\"updateTime\":1621000018479,\"label\":\"outdoor,KungFu\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 13:46:59');
INSERT INTO `sys_oper_log` VALUES (413, '电影视频', 2, 'com.ruoyi.web.controller.media.WmMovieController.uploadVideo()', 'POST', 1, 'admin', NULL, '/media/movie/upload/video', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"ext\":\"mp4\",\"filesize\":64867562,\"title\":\"1-1\",\"url\":\"/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4\"}}', 0, NULL, '2021-05-14 13:47:36');
INSERT INTO `sys_oper_log` VALUES (414, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieId\":94,\"filesize\":64867562,\"params\":{},\"title\":\"1-1\",\"url\":\"/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\",\"updateTime\":1621000059814,\"label\":\"outdoor,KungFu\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 13:47:40');
INSERT INTO `sys_oper_log` VALUES (415, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":60,\"movieId\":94,\"filesize\":64867562,\"params\":{},\"title\":\"1-1\",\"url\":\"/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\",\"updateTime\":1621000098416,\"label\":\"outdoor,KungFu\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 13:48:18');
INSERT INTO `sys_oper_log` VALUES (416, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"极为平凡的御宅族大学生·岩谷尚文，受到在图书馆发现的一本书所引导，被召唤到了异世界。他被赋予的使命，是作为装备着剑、枪、弓、盾的四圣勇者之一“盾之勇者”，驱逐给世界带来混沌的灾害“波”。因为大冒险而心潮澎湃，和同伴一同踏上旅程的尚文。但，他刚出发没几天就遭到背叛，金钱和立场全都失去。变得无法相信他人的尚文，驱使着奴隶少女·拉芙塔莉雅，向波和世界发起对抗——。究竟他能否打破这种绝望的状况？失去一切的男人的成名奇幻故事，开幕。\",\"movieId\":94,\"remark\":\"1111\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"盾之勇者成名录\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":60,\"movieId\":94,\"filesize\":64867562,\"params\":{},\"title\":\"1-1\",\"url\":\"/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4\"}],\"unlikesCount\":1,\"publishTime\":1619971200000,\"images\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\",\"updateTime\":1621000100142,\"label\":\"outdoor,KungFu\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 13:48:20');
INSERT INTO `sys_oper_log` VALUES (417, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"vue入门\",\"movieId\":94,\"remark\":\"\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"vue入门\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":60,\"movieId\":94,\"filesize\":64867562,\"params\":{},\"title\":\"1-1\",\"url\":\"/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4\"}],\"unlikesCount\":1,\"publishTime\":1620921600000,\"images\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\",\"updateTime\":1621000140580,\"label\":\"outdoor\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 13:49:01');
INSERT INTO `sys_oper_log` VALUES (418, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"vue入门\",\"movieId\":94,\"remark\":\"\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"vue入门\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":60,\"movieId\":94,\"filesize\":64867562,\"params\":{},\"title\":\"1-1\",\"url\":\"/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4\"}],\"unlikesCount\":1,\"publishTime\":1620921600000,\"images\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\",\"updateTime\":1621000142195,\"label\":\"outdoor\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 13:49:02');
INSERT INTO `sys_oper_log` VALUES (419, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"china\",\"movieType\":\" film\",\"description\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"movieId\":103,\"remark\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"readCount\":0,\"title\":\"正义荣耀\",\"wmMovieVideoList\":[],\"price\":0,\"publishTime\":1622044800000,\"images\":\"/profile/movie/images/2021/05/12/003008-1504456208b441.jpg\",\"updateTime\":1621001826162,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620527653000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:17:06');
INSERT INTO `sys_oper_log` VALUES (420, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":47,\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"price\":1,\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/12/001756-15040234769744.jpg\",\"updateTime\":1621001845371,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:17:25');
INSERT INTO `sys_oper_log` VALUES (421, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"india\",\"isComment\":\"Y\",\"followCount\":1,\"movieType\":\"teleplay\",\"publishBy\":\"11\",\"description\":\"vue入门\",\"movieId\":94,\"remark\":\"\",\"delFlag\":\"\",\"readCount\":1,\"title\":\"vue入门\",\"likesCount\":1,\"shareCount\":1,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":60,\"movieId\":94,\"filesize\":64867562,\"params\":{},\"title\":\"1-1\",\"url\":\"/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4\"}],\"price\":1,\"unlikesCount\":1,\"publishTime\":1620921600000,\"images\":\"/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png\",\"updateTime\":1621002051136,\"label\":\"outdoor\",\"params\":{},\"commentCount\":1,\"createTime\":1619862539000,\"collectionCount\":1,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:20:51');
INSERT INTO `sys_oper_log` VALUES (422, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"rate\":0.0,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":34,\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":35,\"movieId\":95,\"filesize\":148267788,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4\"}],\"price\":0,\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/12/001740-15050602605349.jpg\",\"updateTime\":1621003802644,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:50:02');
INSERT INTO `sys_oper_log` VALUES (423, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"N\",\"country\":\"japan\",\"isComment\":\"Y\",\"movieType\":\" film\",\"description\":\"12121\",\"movieId\":95,\"remark\":\"212121\",\"readCount\":0,\"title\":\"好看电影\",\"rate\":5.0,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":34,\"movieId\":95,\"filesize\":66473699,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":35,\"movieId\":95,\"filesize\":148267788,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4\"}],\"price\":0,\"publishTime\":1619452800000,\"images\":\"/profile/movie/images/2021/05/12/001740-15050602605349.jpg\",\"updateTime\":1621003818873,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620018769000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:50:19');
INSERT INTO `sys_oper_log` VALUES (424, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"country\":\"china\",\"movieType\":\" film\",\"description\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"movieId\":103,\"remark\":\"MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...\",\"readCount\":0,\"title\":\"正义荣耀\",\"rate\":1.0,\"wmMovieVideoList\":[],\"price\":0,\"publishTime\":1622044800000,\"images\":\"/profile/movie/images/2021/05/12/003008-1504456208b441.jpg\",\"updateTime\":1621003877285,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620527653000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:51:17');
INSERT INTO `sys_oper_log` VALUES (425, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"rate\":1.0,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":47,\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"price\":1,\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/12/001756-15040234769744.jpg\",\"updateTime\":1621004044232,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:54:04');
INSERT INTO `sys_oper_log` VALUES (426, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"Y\",\"country\":\"china\",\"isComment\":\"Y\",\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":96,\"remark\":\"1221\",\"readCount\":0,\"title\":\"测试电影\",\"rate\":1.0,\"wmMovieVideoList\":[{\"ext\":\"mp4\",\"movieVideoId\":38,\"length\":\"10分钟\",\"movieId\":96,\"filesize\":30032601,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4\"},{\"ext\":\"mp4\",\"movieVideoId\":47,\"length\":\"55分钟\",\"movieId\":96,\"filesize\":36350392,\"params\":{},\"title\":\"第一集\",\"url\":\"/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4\"}],\"price\":1,\"publishTime\":1620057600000,\"images\":\"/profile/movie/images/2021/05/12/001756-15040234769744.jpg\",\"updateTime\":1621004046124,\"label\":\"outdoor,KungFu\",\"params\":{},\"createTime\":1620026930000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-14 14:54:06');
INSERT INTO `sys_oper_log` VALUES (427, '站点友情链接', 2, 'com.ruoyi.web.controller.website.WebsiteLinkController.edit()', 'PUT', 1, 'admin', NULL, '/website/link', '127.0.0.1', '内网IP', '{\"linkTarget\":\"_blank\",\"updateTime\":1621044828426,\"sort\":90,\"params\":{},\"linkName\":\"蘑菇博客\",\"linkId\":1,\"createTime\":1561017476000,\"linkUrl\":\"http://www.ithhit.cn\",\"status\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:13:48');
INSERT INTO `sys_oper_log` VALUES (428, '站点友情链接', 2, 'com.ruoyi.web.controller.website.WebsiteLinkController.edit()', 'PUT', 1, 'admin', NULL, '/website/link', '127.0.0.1', '内网IP', '{\"linkTarget\":\"_blank\",\"updateTime\":1621044858116,\"sort\":100,\"params\":{},\"linkName\":\"百度\",\"linkId\":2,\"createTime\":1561544238000,\"linkUrl\":\"http://www.baidu.com\",\"status\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:14:18');
INSERT INTO `sys_oper_log` VALUES (429, '站点友情链接', 3, 'com.ruoyi.web.controller.website.WebsiteLinkController.remove()', 'DELETE', 1, 'admin', NULL, '/website/link/4', '127.0.0.1', '内网IP', '{linkIds=4}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:14:32');
INSERT INTO `sys_oper_log` VALUES (430, '站点友情链接', 1, 'com.ruoyi.web.controller.website.WebsiteLinkController.add()', 'POST', 1, 'admin', NULL, '/website/link', '127.0.0.1', '内网IP', '{\"linkTarget\":\"1\",\"sort\":1,\"params\":{},\"linkName\":\"若依\",\"createTime\":1621044951049,\"linkUrl\":\"http://vip.ruoyi,com\",\"status\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:15:51');
INSERT INTO `sys_oper_log` VALUES (431, '站点友情链接', 2, 'com.ruoyi.web.controller.website.WebsiteLinkController.edit()', 'PUT', 1, 'admin', NULL, '/website/link', '127.0.0.1', '内网IP', '{\"linkTarget\":\"_blank\",\"updateTime\":1621044961064,\"sort\":999,\"params\":{},\"linkName\":\"若依\",\"linkId\":5,\"createTime\":1621044951000,\"linkUrl\":\"http://vip.ruoyi,com\",\"status\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:16:01');
INSERT INTO `sys_oper_log` VALUES (432, '站点友情链接', 2, 'com.ruoyi.web.controller.website.WebsiteLinkController.edit()', 'PUT', 1, 'admin', NULL, '/website/link', '127.0.0.1', '内网IP', '{\"linkTarget\":\"_blank\",\"updateTime\":1621044969440,\"sort\":999,\"params\":{},\"linkName\":\"若依\",\"linkId\":5,\"createTime\":1621044951000,\"linkUrl\":\"http://vip.ruoyi.com\",\"status\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:16:09');
INSERT INTO `sys_oper_log` VALUES (433, '站点友情链接', 2, 'com.ruoyi.web.controller.website.WebsiteLinkController.edit()', 'PUT', 1, 'admin', NULL, '/website/link', '127.0.0.1', '内网IP', '{\"linkTarget\":\"_blank\",\"updateTime\":1621045023837,\"sort\":999,\"params\":{},\"linkName\":\"若依\",\"linkId\":5,\"createTime\":1621044951000,\"linkUrl\":\"http://ruoyi.vip/\",\"status\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:17:04');
INSERT INTO `sys_oper_log` VALUES (434, '首页banner', 2, 'com.ruoyi.web.controller.website.WebsiteBannerController.edit()', 'PUT', 1, 'admin', NULL, '/website/banner', '127.0.0.1', '内网IP', '{\"bannerId\":1,\"remark\":\"1\",\"updateTime\":1621045136337,\"sort\":1,\"delFlag\":\"0\",\"params\":{},\"title\":\"测试\",\"createTime\":1620203586000,\"imageUrl\":\"http://localhost:7070/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg\",\"linkUrl\":\"/movie\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 02:18:56');
INSERT INTO `sys_oper_log` VALUES (435, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"china\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"2121\",\"movieId\":108,\"remark\":\"212112\",\"readCount\":0,\"title\":\"1111\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"publishTime\":1620316800000,\"label\":\"outdoor\",\"params\":{},\"commentCount\":0,\"createTime\":1621048932623,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"collectionCount\":0,\"commentCount\":0,\"country\":\"china\",\"createTime\":1621048933000,\"description\":\"2121\",\"followCount\":0,\"isComment\":\"0\",\"isDownload\":\"0\",\"label\":\"outdoor\",\"likesCount\":0,\"movieId\":108,\"movieType\":\"teleplay\",\"params\":{},\"price\":0.00,\"publishTime\":1620316800000,\"rate\":0.0,\"readCount\":0,\"remark\":\"212112\",\"shareCount\":0,\"status\":\"0\",\"title\":\"1111\",\"unlikesCount\":0,\"wmMovieVideoList\":[]}}', 0, NULL, '2021-05-15 03:22:28');
INSERT INTO `sys_oper_log` VALUES (436, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"american\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"112\",\"movieId\":109,\"remark\":\"21211\",\"readCount\":0,\"title\":\"1212\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"label\":\"outdoor\",\"params\":{},\"commentCount\":0,\"createTime\":1621048979652,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"collectionCount\":0,\"commentCount\":0,\"country\":\"american\",\"createTime\":1621048980000,\"description\":\"112\",\"followCount\":0,\"isComment\":\"0\",\"isDownload\":\"0\",\"label\":\"outdoor\",\"likesCount\":0,\"movieId\":109,\"movieType\":\"teleplay\",\"params\":{},\"price\":0.00,\"rate\":0.0,\"readCount\":0,\"remark\":\"21211\",\"shareCount\":0,\"status\":\"0\",\"title\":\"1212\",\"unlikesCount\":0,\"wmMovieVideoList\":[]}}', 0, NULL, '2021-05-15 03:23:05');
INSERT INTO `sys_oper_log` VALUES (437, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"11221\",\"movieId\":110,\"remark\":\"2112\",\"readCount\":0,\"title\":\"122123\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"label\":\"outdoor\",\"params\":{},\"commentCount\":0,\"createTime\":1621049133758,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"collectionCount\":0,\"commentCount\":0,\"createTime\":1621049134000,\"description\":\"11221\",\"followCount\":0,\"isComment\":\"0\",\"isDownload\":\"0\",\"label\":\"outdoor\",\"likesCount\":0,\"movieId\":110,\"movieType\":\"teleplay\",\"params\":{},\"price\":0.00,\"rate\":0.0,\"readCount\":0,\"remark\":\"2112\",\"shareCount\":0,\"status\":\"0\",\"title\":\"122123\",\"unlikesCount\":0,\"wmMovieVideoList\":[]}}', 0, NULL, '2021-05-15 03:25:55');
INSERT INTO `sys_oper_log` VALUES (438, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"american\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":111,\"remark\":\"212121\",\"readCount\":0,\"title\":\"12212121\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"publishTime\":1620921600000,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049295070,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"collectionCount\":0,\"commentCount\":0,\"country\":\"american\",\"createTime\":1621049295000,\"description\":\"1221\",\"followCount\":0,\"isComment\":\"0\",\"isDownload\":\"0\",\"label\":\"KungFu\",\"likesCount\":0,\"movieId\":111,\"movieType\":\"teleplay\",\"params\":{},\"price\":0.00,\"publishTime\":1620921600000,\"rate\":0.0,\"readCount\":0,\"remark\":\"212121\",\"shareCount\":0,\"status\":\"0\",\"title\":\"12212121\",\"unlikesCount\":0,\"wmMovieVideoList\":[]}}', 0, NULL, '2021-05-15 03:28:15');
INSERT INTO `sys_oper_log` VALUES (439, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"china\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":112,\"remark\":\"21211\",\"readCount\":0,\"title\":\"1232312213\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049374653,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"collectionCount\":0,\"commentCount\":0,\"country\":\"china\",\"createTime\":1621049375000,\"description\":\"1221\",\"followCount\":0,\"isComment\":\"0\",\"isDownload\":\"0\",\"label\":\"KungFu\",\"likesCount\":0,\"movieId\":112,\"movieType\":\"teleplay\",\"params\":{},\"price\":0.00,\"rate\":0.0,\"readCount\":0,\"remark\":\"21211\",\"shareCount\":0,\"status\":\"0\",\"title\":\"1232312213\",\"unlikesCount\":0,\"wmMovieVideoList\":[]}}', 0, NULL, '2021-05-15 03:29:34');
INSERT INTO `sys_oper_log` VALUES (440, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"china\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":112,\"remark\":\"21211\",\"readCount\":0,\"title\":\"1232312213\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"updateTime\":1621049376916,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049375000,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 03:29:37');
INSERT INTO `sys_oper_log` VALUES (441, '电影管理', 3, 'com.ruoyi.web.controller.media.WmMovieController.remove()', 'DELETE', 1, 'admin', NULL, '/media/movie/108,109,110,111,112', '127.0.0.1', '内网IP', '{movieIds=108,109,110,111,112}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 03:29:49');
INSERT INTO `sys_oper_log` VALUES (442, '电影管理', 1, 'com.ruoyi.web.controller.media.WmMovieController.add()', 'POST', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"japan\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":113,\"remark\":\"122121\",\"readCount\":0,\"title\":\"121221\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"publishTime\":1621526400000,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049409525,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"collectionCount\":0,\"commentCount\":0,\"country\":\"japan\",\"createTime\":1621049410000,\"description\":\"1221\",\"followCount\":0,\"isComment\":\"0\",\"isDownload\":\"0\",\"label\":\"KungFu\",\"likesCount\":0,\"movieId\":113,\"movieType\":\"teleplay\",\"params\":{},\"price\":0.00,\"publishTime\":1621526400000,\"rate\":0.0,\"readCount\":0,\"remark\":\"122121\",\"shareCount\":0,\"status\":\"0\",\"title\":\"121221\",\"unlikesCount\":0,\"wmMovieVideoList\":[]}}', 0, NULL, '2021-05-15 03:30:09');
INSERT INTO `sys_oper_log` VALUES (443, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"japan\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":113,\"remark\":\"122121\",\"readCount\":0,\"title\":\"121221\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"publishTime\":1621526400000,\"updateTime\":1621049426527,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049410000,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 03:30:26');
INSERT INTO `sys_oper_log` VALUES (444, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"japan\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":113,\"remark\":\"122121\",\"readCount\":0,\"title\":\"121221\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"publishTime\":1621526400000,\"updateTime\":1621049427938,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049410000,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 03:30:28');
INSERT INTO `sys_oper_log` VALUES (445, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"japan\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":113,\"remark\":\"122121\",\"readCount\":0,\"title\":\"121221\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"publishTime\":1621526400000,\"updateTime\":1621049428554,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049410000,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 03:30:28');
INSERT INTO `sys_oper_log` VALUES (446, '电影管理', 2, 'com.ruoyi.web.controller.media.WmMovieController.edit()', 'PUT', 1, 'admin', NULL, '/media/movie', '127.0.0.1', '内网IP', '{\"isDownload\":\"0\",\"country\":\"japan\",\"isComment\":\"0\",\"followCount\":0,\"movieType\":\"teleplay\",\"description\":\"1221\",\"movieId\":113,\"remark\":\"122121\",\"readCount\":0,\"title\":\"121221\",\"likesCount\":0,\"shareCount\":0,\"rate\":0.0,\"wmMovieVideoList\":[],\"price\":0,\"unlikesCount\":0,\"publishTime\":1621526400000,\"updateTime\":1621049429040,\"label\":\"KungFu\",\"params\":{},\"commentCount\":0,\"createTime\":1621049410000,\"collectionCount\":0,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 03:30:29');
INSERT INTO `sys_oper_log` VALUES (447, '电影管理', 3, 'com.ruoyi.web.controller.media.WmMovieController.remove()', 'DELETE', 1, 'admin', NULL, '/media/movie/113', '127.0.0.1', '内网IP', '{movieIds=113}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-05-15 03:30:43');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(0) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2021-04-29 21:28:13', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2021-04-29 21:28:13', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(0) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2021-04-29 21:28:13', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2021-04-29 21:28:13', '', NULL, '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(0) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '/profile/avatar/2021/05/02/fa1e6a77-eb51-4616-9aca-00de015b2382.jpeg', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2021-04-29 21:28:12', 'admin', '2021-04-29 21:28:12', '', '2021-05-02 11:38:22', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2021-04-29 21:28:12', 'admin', '2021-04-29 21:28:12', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `post_id` bigint(0) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for website_banner
-- ----------------------------
DROP TABLE IF EXISTS `website_banner`;
CREATE TABLE `website_banner`  (
  `banner_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '链接地址',
  `sort` int unsigned NULL COMMENT '排序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`banner_id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页banner表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website_banner
-- ----------------------------
INSERT INTO `website_banner` VALUES (1, '测试', 'http://localhost:7070/profile/movie/images/2021/05/03/f6dd8c57-cff8-475c-89c0-8310d7d929fa.jpg', '/movie', 1, '0', '2021-05-05 16:33:06', '2021-05-15 10:18:56', NULL, NULL, '1');
INSERT INTO `website_banner` VALUES (2, '测试2', 'http://localhost:7070/profile/movie/images/2021/05/03/67e0dd69-7851-4e98-a127-1733b86d10bd.jpg', 'http://baidu.com', 0, '0', '2021-05-05 17:49:21', NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for website_collect
-- ----------------------------
DROP TABLE IF EXISTS `website_collect`;
CREATE TABLE `website_collect`  (
  `collect_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的uid',
  `movie_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客的uid',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`collect_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for website_comment
-- ----------------------------
DROP TABLE IF EXISTS `website_comment`;
CREATE TABLE `website_comment`  (
  `id` bigint(0) NOT NULL COMMENT '评论id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论人userId',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '评论人名称',
  `movie_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论的电影id',
  `movie_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '评论的文章标题',
  `parent_comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '父评论id',
  `parent_comment_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '父评论的用户id',
  `reply_comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '被回复的评论id',
  `reply_comment_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '被回复的评论用户id',
  `comment_level` tinyint(0) NOT NULL DEFAULT 1 COMMENT '评论等级[ 1 一级评论 默认 ，2 二级评论]',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '评论的内容',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态 (1 有效，0 逻辑删除)',
  `likes` int(0) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `top` tinyint(0) NOT NULL DEFAULT 0 COMMENT '置顶状态[ 1 置顶，0 不置顶 默认 ]',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_article_id`(`movie_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `idx_parent_comment_id`(`parent_comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for website_link
-- ----------------------------
DROP TABLE IF EXISTS `website_link`;
CREATE TABLE `website_link`  (
  `link_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` tinyint unsigned NOT NULL COMMENT '状态(1有效, 0无效)',
  `sort` int(0) NOT NULL DEFAULT 1 COMMENT '排序',
  `link_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接',
  `link_target` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '跳转方式(_blank，_self)',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站点友情链接' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website_link
-- ----------------------------
INSERT INTO `website_link` VALUES (1, '2019-06-20 15:57:56', '2021-05-15 10:13:48', 1, 90, '蘑菇博客', 'http://www.ithhit.cn', '_blank', NULL, NULL, NULL);
INSERT INTO `website_link` VALUES (2, '2019-06-26 18:17:18', '2021-05-15 10:14:18', 1, 100, '百度', 'http://www.baidu.com', '_blank', NULL, NULL, NULL);
INSERT INTO `website_link` VALUES (3, '2020-04-09 21:36:12', '2020-04-09 21:36:12', 1, 10, 'IT云', 'https:/www.doityun.com', '_blank', NULL, NULL, NULL);
INSERT INTO `website_link` VALUES (5, '2021-05-15 10:15:51', '2021-05-15 10:17:04', 0, 999, '若依', 'http://ruoyi.vip/', '_blank', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wm_actor
-- ----------------------------
DROP TABLE IF EXISTS `wm_actor`;
CREATE TABLE `wm_actor`  (
  `actor_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `awards` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `label` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签  actor代表演员 director表示导演',
  PRIMARY KEY (`actor_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wm_actor
-- ----------------------------
INSERT INTO `wm_actor` VALUES (1, '托德·菲利普斯Todd Phillips', 'http://localhost:8088/images/actor/abb52226-4c40-4f4a-a621-c0e53b42c68e.jpg', '　托德·菲利普斯，好莱坞著名导演，以性喜剧《宿醉》成名。   ', '2019年 第76届威尼斯电影节 主竞赛单元 金狮奖 小丑/\r\n2013年 第17届好莱坞电影奖 年度电影(提名) 宿醉3/\r\n2007年 第79届奥斯卡金像奖 最佳改编剧本(提名) 波拉特', NULL);
INSERT INTO `wm_actor` VALUES (3, '杰昆·菲尼克斯Joaquin Phoenix', 'http://localhost:8088/images/actor/2b7e937a-5cd2-4329-a79e-34b91f7a6e0c.jpg', '　　杰昆·菲尼克斯，是90年代最出名的童星瑞凡·菲尼克斯的亲弟弟，三十出头的他戏龄已经高达二十几年。杰昆出道的第一部片子叫《spacecamp》，后来又在《Russkies》 (1987)和《Parenthood 》(1989)中演出，他表演相当不错。\r\n　　不像一般的美国家庭，杰昆的家庭教育严格，但父母却十分鼓励孩子们从事表演，很自然的，杰昆就像父母希望的那样做了，他紧紧跟随他的哥哥瑞凡·菲尼克斯和瑞恩·菲尼克斯步入了演艺圈。那时候，他叫做Leaf，直到九十年代初他才改名叫Joaquin。\r\n　　在2005年的新片《一往无前》里，杰昆又带给银幕更个人化与风格化的表演，为这部传记电影打上了菲尼克斯式的表演烙印。凭借在《一往无前》中的出色演出,他获得第78届奥斯卡最佳男主角提名及第63届金球奖喜剧/音乐类最佳男演员提名', '2019年 第34届美国独立精神奖 最佳男主角(提名) 你从未在此/\r\n2018年 第21届英国独立电影奖 最佳男主角(提名) 你从未在此/\r\n2017年 第70届戛纳电影节 主竞赛单元 最佳男演员 你从未在此/				', NULL);
INSERT INTO `wm_actor` VALUES (4, '罗伯特·德尼罗 Robert De Niro', 'http://localhost:8088/images/actor/0b77e62a-d191-4e01-b2ee-7b247dbf54d9.jpg', '　　奥斯卡影帝罗伯特·德尼罗塑造过的所有角色中，最著名的当属《教父2》中年轻的唐·科莱昂和《愤怒的公牛》中的拳击手杰克·拉莫塔，他最经典的一句台词则是《出租汽车司机》中“你在跟我讲话？”凭借马丁·斯科塞斯1973年影片《穷街陋巷》一举成名的德尼罗，既可以扮演心狠手辣的黑帮分子，又能在《午夜狂奔》、《摇尾狗》、《老大靠边闪》和《拜见岳父岳母》等喜剧中尽现搞笑天份。\r\n　　1943年8月，罗伯特·德尼罗出生在纽约一个艺术家家庭，两岁时父母离异，他跟着母亲生活在格林威治村。16岁高中毕业后他跟着路瑟·詹姆斯（Luther James）和李·斯特拉斯伯格（Lee Strasberg）学习表演，参演了几部外百老汇剧，1965年在法国影片《Trois chambres à Manhattan》中饰演一个小角色，60年代后期他在《帅气逃兵》、《嗨，妈妈》、《婚礼舞会》等影片中担任主演。进入70年代，德尼罗的事业开始绽放异彩，出演了《血腥妈妈》、《我的子弹会转弯》和《天生大赢家》等影片后，与马丁·斯科塞斯首度合作的《穷街陋巷》为他赢得了纽约影评人协会奖和美国国家影评人协会奖，此后他又凭借《战鼓轻悄》获得奥斯卡提名，1974年的《教父2》让他如愿捧得奥斯卡影帝桂冠，两年后的《出租汽车司机》更是好评如潮，获得多项最佳男演员奖。此后他的主要作品有《愤怒的公牛》、《不可触犯》、《午夜狂奔》、《我们不是天使》、《盗亦有道》、《疯狗马子》等。1993年他首执导棒，自导自演了《布鲁克斯故事》。2003年罗伯特·德尼罗被美国电影学会授予终生成就奖，以表彰他对电影事业的杰出贡献。', '2019年 第71届黄金时段艾美奖 喜剧类 最佳客串男演员(提名) 周六夜现场/ 第四十四季\r\n\r\n2018年 第75届金球奖 电视类 迷你剧/电视电影最佳男主角(提名) 欺诈圣手/\r\n\r\n2017年 第69届黄金时段艾美奖 迷你剧/电视电影 最佳男主角(提名) 欺诈圣手				', NULL);
INSERT INTO `wm_actor` VALUES (5, '新海诚 Makoto Shinkai', 'http://localhost:8088/images/actor/8c8d3ee6-b72b-40ca-96c7-8c4a61fe2f85.jpg', '　　新海 诚（Makoto Shinkai，まこと しんかい，1973年2月9日－），男，居住在东京。日本长野县野泽北高等学校、日本中央大学文学部国文学专攻毕业。1996-2001年就职于Falcom游戏会社，目前自由职业，以制作个人动画为主。绚丽的2D动画效果配合近乎完美的节奏感，给人以强烈的感官震撼。这就是新海诚在职F社5年间的代表作之一。 1996年，新海诚在大学结业后加入日本Falcom游戏会社（通常在相关仕事中以新津诚名义出现），负责程序、美工、动画与宣传影片制作。在职Falcom期间，新海氏开始了个人动画短片创作。包括1997年的黑白短片《遥远世界》（1分30秒）、1998年的3D短片《被包围的世界》（30秒）、1999-2000年的黑白短片《她和她的猫》（5分钟）。 2000年初夏，新海氏开始制作个人映像动画《星之声》。但由于工作的繁忙，导致《星之声》一度停滞不前。2001年5月，新海氏向Falcom提交辞职。之后《星之声》的制作步上正轨，2002年1月宣告完工，历时共计7个月。此外，新海氏也曾外注参与制作Minori游戏会社游戏《Bittersweet Fools》（2001）、《Wind·a breath of heart》（2002）片头动画。 2003年新海诚新作《云的彼端，约定的地方》（预定片长50分钟）。在背景设定与情节方面，与架空感十足的《星之声》相比更加的平稳自然。由田泽潮担任人设更是弥补了新海人设较苍白的缺陷。担任Minori游戏会社游戏《EF - the first tale》(2006)的动画监制(Animation Movie Director)。', '2017年 第40届日本电影学院奖 最佳导演(提名) 你的名字。\r\n2016年 第41届报知映画赏 特别奖 你的名字。\r\n2005年 第8届上海国际电影节 亚洲新人奖 最佳影片(提名) 云的彼端，约定的地方				', NULL);
INSERT INTO `wm_actor` VALUES (7, '森七菜 Nana Mori', 'http://localhost:8088/images/actor/bc6132fb-df68-4220-850d-c28001787822.jpg', '00后新生代日本女演员。', '...				', NULL);
INSERT INTO `wm_actor` VALUES (9, '神木隆之介 Ryûnosuke Kamiki', 'http://localhost:8088/images/actor/128555fc-7d5b-4546-953b-6baae079dfc0.jpg', '　　神木隆之介，日本男演员、声优。出生时身患救治率仅为1%的感染症，却奇迹般地获救，并被小心翼翼地养大。童年时期非常漂亮和可爱的样貌，导致他曾经常被误以为是女孩子，并成为动漫大国日本的漫画作家、动画制作人创作人物形象时的灵感的源泉。日本畅销漫画《投捕手》（的画作者柚庭千景曾在该系列作品的第六卷后记中承认青波的形象设计灵感源自演员神木隆之介。吉卜力工作室作品中的《买下星星的日子》的主人公诺那、《借物少女阿丽埃蒂》中的少年翔的形象也都是以他为原型创作的。进入变声期后，终于变得像男孩子了，但秀美帅气的外形、透明纯真的气质与丰富的演艺经历、精致迫真的演技令其在同代人中始终是一个特别的存在。', '2013年 第77届日剧学院赏 最佳男配角 家族游戏。\r\n2007年 第54届日剧学院赏 最佳男主角(提名) 侦探学园Q。\r\n2006年 第29届日本电影学院奖 最佳新人 妖怪大战争。				', NULL);
INSERT INTO `wm_actor` VALUES (10, '上白石萌音 Mone Kamishiraishi', 'http://localhost:8088/images/actor/09150bc7-90bc-43da-ac82-f7ca36fa6f35.jpg', '　　上白石萌音，日本童星。东宝芸能旗下艺人。 ', '2015年 第38届日本电影学院奖 最佳新人 窈窕舞妓				', NULL);
INSERT INTO `wm_actor` VALUES (11, '弗兰克·德拉邦特 Frank Darabont', 'http://localhost:8088/images/actor/bef1ebf7-36a2-44a8-8b6b-884908b0ca8f.jpg', '　　弗兰克·达拉邦特为数极少的导演作品却为他赢得了全世界影迷的爱戴：比如《肖申克的救赎》和《绿里奇迹》。\r\n　　比起导演这个工作，弗兰克·达拉邦特更适合被称为编剧，然而他为数极少的导演作品却为他赢得了全世界影迷的爱戴：比如《肖申克的救赎》和《绿里奇迹》。而2001年，他的奥斯卡野心之作《忘了我是谁》让金·凯瑞在正剧上演足了瘾，却票房、口碑双双失利。2007年的作品是根据斯蒂芬·金小说改编的《迷雾》。', '2000年 第72届奥斯卡金像奖 最佳影片(提名) 绿里奇迹\r\n2000年 第72届奥斯卡金像奖 最佳改编剧本(提名) 绿里奇迹\r\n1995年 第67届奥斯卡金像奖 最佳改编剧本(提名) 肖申克的救赎				', NULL);
INSERT INTO `wm_actor` VALUES (12, '蒂姆·罗宾斯 Tim Robbins', 'http://localhost:8088/images/actor/25c9b8e5-a745-4167-bcfb-f514e612e5ee.jpg', '　　 蒂姆·罗宾斯，原名蒂莫西·佛朗西斯·罗宾斯（Timothy Francis Robbins），生于美国加利福尼亚州西科维纳市，中学就读于 Stuyvesant High School，后升到 University of New York at Plattsburgh，就读两年后再转升到 U.C.L.A.，于 1980年毕业及取得戏剧学位。曾是专业棒球运动员，对演戏情有独钟。\r\n　　他还是一位导演、编剧以及制片人。作为演员，他于1992年凭借《超级大玩家》（The Player）荣获戛纳电影节和金球奖的双料影帝，在2004年又凭借《神秘河》（Mystic River）荣获第76届奥斯卡最佳男配角奖。作为导演和编剧，他1995年的作品《死囚上路》（Dead Man Walking）在1996年获得了奥斯卡的三项提名并且在同年的柏林电影节上获得四项大奖。在各个影片中，罗宾斯以精湛的演技，塑造了一个个知性、沉静的银幕形象，为蒂姆·罗宾斯在影坛奠定了结实的基础，被称为好莱坞真正电影艺术家。', '2016年 第30届西班牙戈雅奖 最佳男配角(提名) 美好的一天\r\n2012年 第69届金球奖 电视类 最佳男配角(提名) 真实电影\r\n2004年 第76届奥斯卡金像奖 最佳男配角 神秘河				', NULL);
INSERT INTO `wm_actor` VALUES (13, '摩根·弗里曼 Morgan Freeman', 'http://localhost:8088/images/actor/c7578261-d91b-41a2-a0e1-02aac49c38eb.jpg', '　　摩根·弗里曼 （Morgan Freeman），美国黑人男演员、导演。1989年凭借着在《为戴西小姐开车》中的忠厚诚实、善良勇敢的老司机形象为他赢得了金球奖和全美影评奖。2005年凭借克林特·伊斯特伍德导演的《百万美元宝贝》获得第77届奥斯卡最佳男配角奖。摩根·弗里曼于1990年代成名，并演出过许多著名的好莱坞电影。\r\n　　IMDB TOP 001的《肖申克救赎》中，弗里曼塑造的囚犯瑞德的形象更为深入人心。', '2018年 第24届美国演员工会奖 终身成就奖\r\n2012年 第69届金球奖 终身成就奖\r\n2010年 第82届奥斯卡金像奖 最佳男主角(提名) 成事在人				', NULL);
INSERT INTO `wm_actor` VALUES (14, '吕克·贝松 Luc Besson', 'http://localhost:8088/images/actor/fa50cb2d-b09b-4e60-a9bf-554e640c522b.jpg', '吕克·贝松1959年3月18日生于法国巴黎，幼年的理想是做一名潜水运动员和航海家，但是17岁时的一次潜水事故打破了他的童年梦想，贝松很快调整了自己人生的目标，决心做一名电影制片人。于是19岁那年来到美国洛杉矶，学习了3个月的电影制作课程并开始拍摄一些试验短片。1983年，贝松的电影处女作《最后的战斗》问世，这部多次获奖的黑白、宽银幕的无声科幻片，据说是23岁的他用3法郎六苏拍成的。吕克·贝松似乎向世人证明了，在法国即使没有受过专业的电影教育，找不到投资，依旧可以拍出与众不同的电影。也正因如此，贝松曾经一度被奉为法国年轻导演的开路先锋，不管他乐意不乐意这一称呼。《地铁》和《碧海蓝天》为吕克·贝松在国内和国际赢得了声誉，虽然当年的法国凯撒奖有点吝啬地仅仅将“最佳录音”和“最佳音乐”奖授予《碧海蓝天》，但是贝松却公开表示这部影片是其导演生涯中最为得意的作品。毕竟，这部作品倾注了贝松少年时的激情和梦想，贝松终于可以用他的摄影机捕捉他童年时代所看到的海洋的光影和生命。影片中的潜水员杰克最后葬身大海，更确切的说，那是他真正的生存，杰克莫不就是贝松理想中的化身？', '2017年 第4届豆瓣电影年度榜单 最受关注的导演(提名)\r\n2011年 第16届釜山国际电影节 荣誉奖				', NULL);
INSERT INTO `wm_actor` VALUES (15, '让·雷诺 Jean Reno', 'http://localhost:8088/images/actor/7c29abaa-6019-426b-8ac9-45fce4da845c.jpg', '　　让·雷诺于1948年7月30日出生于北非摩洛哥的卡萨布兰卡，原本生活在西班牙的父母当年为逃避弗朗西斯科的纳粹统治来到了北非。让·雷诺12岁和父母一起回到巴黎生活。雷诺17岁时到法国系统学习戏剧表演，并渐渐在法国的电视、舞台剧和电影上崭露头角。在让·雷诺12岁回巴黎生活后没几年，他的母亲就去世了，这件事给他带来了很大的打击，也在各方面影响了他的生活。\r\n　　作为法国中生代演员中出类拔萃的一个，让·雷诺的功底扎实，戏路多变，他的演技在好莱坞也十分出名。比较出名的作品有《我们的故事》《这个杀手不太冷》《碟中谍》《爱情的魔力》《哥斯拉》等等。', '...				', NULL);
INSERT INTO `wm_actor` VALUES (16, '娜塔莉·波特曼 Natalie Portman', 'http://localhost:8088/images/actor/806245bc-2f18-417e-a06e-1ec139ad17af.jpg', '　　娜塔丽·波特曼，美国著名女演员。因出演吕克·贝松导演的《杀手里昂》一鸣惊人，自此开始了一边读书一边拍戏的生涯。\r\n　　娜塔莉好像总能与好莱坞大腕们同台演出。接下来1995年她在麦克尔曼的《盗火线》中做了艾尔帕西诺的继女，1996年在《火星人攻击地球》中出演杰克尼科尔森的女儿。同年她又出现在伍迪·艾伦的音乐喜剧《人人都说我爱你》里。娜塔莉在剧中的轻松表演赢得了观众的喜爱。\r\n　　1996年娜塔莉在电影《美丽佳人》中的演出又一次获得好评如潮。在剧中她扮演的女孩叫马蒂，那可是个颇有心计的早熟女孩，一心一意想把心上人从他的未婚妻手中夺走。评论家对娜塔莉在剧中的演技评价甚高，有一点绝不可否认：她是整部剧中最出色的亮点。\r\n　　1997年娜塔莉作出一个惊人的决定：她竟然推掉《洛丽塔》和《罗密欧与朱莉叶》两部巨片，心甘情愿地登上舞台出演《安妮·弗兰克的日记》。这个举动曾让众人大惑不解，但是从《安妮》一剧每次结束后长达近一个小时的谢幕中，我们就明白她演的有多么出色。娜塔莉在舞台演了整整1年，直到1998年才离开《安妮》剧组。\r\n　　世纪末娜塔莉出演了她最为广泛议论的角色——在风靡全球的乔治卢卡斯的《星球大战前传：幽灵的威胁》中饰演高贵而美丽的阿米达拉女王。尽管毁誉皆有，这部影片创下了全球巨额票房却是一个不争的事实。而娜塔莉的美丽形象也如同当年的赫本世人皆知。\r\n　　从最初站在镁光灯下到今天，昔日的女孩如今已长大，纯真依旧，但是却多了份成熟女人的自信与风情。娜塔莉正用自己的实际行动向世界证明——她是好莱坞新一代演员中最具实力，最有前途的女星之一。', '2017年 第89届奥斯卡金像奖 最佳女主角(提名) 第一夫人\r\n2016年 第26届哥谭独立电影奖 最佳女演员(提名) 第一夫人\r\n2015年 第68届戛纳电影节 金摄影机奖（导演处女作奖）(提名) 爱与黑暗的故事				', NULL);
INSERT INTO `wm_actor` VALUES (17, '朱塞佩·托纳多雷 Giuseppe Tornatore', 'http://localhost:8088/images/actor/f63fb703-fdd8-4717-af8f-11852a7354c3.jpg', '　　意大利写实电影流派的新贵导演，1956年出生于西西里岛靠近巴勒莫（Palermo）的巴格里亚镇（Bagheria）。在年纪很小的时候就开始了工作，最初的职业是当摄影师，他拍的照片被刊登在不同的摄影杂志 上。16岁时他参与了皮兰德罗（Pirandello Luigi）和菲利波（De Filippo）的两出戏剧，这以后他开始涉足电影，初期主要是拍一些纪录片，应该说这段工作经历对他以后的电影风格的形成有很大影响。 在意大利浩如烟海的电影导演中，托纳托雷绝对是最耀眼的明星之一…… 与安东尼奥尼的内敛含蓄不同，托纳托雷的讲述更加直白和清晰；也不同于罗贝托·贝尼尼的机智风趣，托纳托雷更注重平实稳健；亦区别于帕索里尼的诡异另类，托纳托雷为我们娓娓道来的是一个平凡的近乎没有故事的故事……', '2013年 第26届欧洲电影奖 观众选择奖(提名) 最佳出价\r\n2011年 第55届意大利大卫奖 最佳纪录片(提名) 最后的嘉多帕多\r\n2009年 第66届威尼斯电影节 金狮奖(提名) 巴阿里亚				', NULL);
INSERT INTO `wm_actor` VALUES (18, '蒂姆·罗斯 Tim Roth', 'http://localhost:8088/images/actor/a6a217f0-6b0f-4a45-8725-e8a698f613bc.jpg', '　　蒂姆·罗斯1961年5月14日出生于英国伦敦，善于模仿各种口音，常被误认为美国人。他在坎伯维尔艺术学院学习过雕塑，之后退学，一心追求表演事业。第一个突破是1982年在英国电视电影《英国制造》中扮演的角色。1984年演出电影处女作《隶你十年》，他扮演一个刚出道的菜鸟杀手。《隶你十年》让他拿到包括BAFTA在内的一系列的“最有前途新人奖”。尽管如此，由于蒂姆·罗斯对商业电影十分反感，坚持走独立路线，这使他在相当长一段时间内都没有合适的工作，在赤贫线上生活。他做过超级市场的员工，也做过电话推销，生活一片混乱。最后在阿姆斯特丹睡下水道的时候他被彼得·格林纳威发现并带回英国，在《情欲色香味》中扮演大盗的手下。 之后是第一部带给他以国际性声誉的电影，与罗伯特·奥特曼合作，反映凡高晚年生活的《凡高与提奥》。接下来，他的名字再度与加里·奥德曼联系到了一起。黑色喜剧《罗森·格兰兹与吉尔·登斯顿之死》，赢得的是热烈的赞扬。之后，因为政治上的立场，他被赶出了英国。\r\n　　1992年，他与初出茅庐的昆汀·塔伦蒂诺合作了震惊影坛的黑色经典《落水狗》，片中的Mr.Orange一角为他赢得了在独立电影界显赫的声名和更多的演出机会。这之后蒂姆·罗斯的发展可谓一帆风顺，和他合作的导演包括昆汀、WoodyAllen、GiuseppeTornatore、WimWenders、弗朗西斯·福特·科波拉等等，他依然坚持着自己的原则，为影迷奉献越来越多经典的角色和影片。', '2017年 第32届美国独立精神奖 最佳男主角(提名) 慢性\r\n2015年 第19届好莱坞电影奖 年度群像演技 八恶人\r\n2014年 第13届华盛顿影评人协会奖 最佳群戏(提名) 塞尔玛				', NULL);
INSERT INTO `wm_actor` VALUES (19, '普路特·泰勒·文斯 Pruitt Taylor Vince', 'http://localhost:8088/images/actor/9d0a3fcd-b294-4464-a00e-6331c1fbe011.jpg', '  普鲁伊特·泰勒·文斯（1960年7月5日出生）是美国著名的演员，曾多次在电影和电视上露面，出生于路易斯安那州的巴吞鲁日。他就读于路易斯安那州立大学，因为一个错误开始演戏；他高中注册时的一个电脑错误安排他上表演课，文斯从未离开过这个科目。\r\n\r\n  与第一次婚姻离婚后，文斯于2003年与朱利安·马特利格再婚。他有病理性眼球震颤，一种使人的眼睛不由自主地移动的疾病。\r\n\r\n文斯的电影处女作是在吉姆·贾马斯克的《法律的堕落》中，但他的镜头却在剪辑室的地板上。他在许多主要电影中都有突出的配角，包括在《密西西比州的燃烧》中饰演一个头脑迟钝的三K党成员，在《肯尼迪总统》中饰演李鲍尔斯，在《无名小卒》中饰演主角最好的朋友。他还与蒂姆·罗斯一起主演了朱塞佩·托纳托雷的《1900传奇》。他在詹姆斯·曼戈尔德的独立电影《沉重》中扮演了第一个主角，扮演了一个可爱的、沉默的超重厨师，暗恋着一个女招待（丽芙·泰勒）。\r\n\r\n  文斯经常交替扮演英雄和恶棍两个角色，比如《美丽女孩》中可爱的小镇酒吧老板、身份上有多重性格的连环杀手（与曼戈尔德导演的第二次合作）、s1mone中衣衫褴褛的八卦专栏作家和贝蒂护士中傲慢的警长。他也可以被视为爱从零地沃尔特。文斯还在《天使之心》中扮演南方警察，在《被困》中扮演绑架者的助手，在《奥利弗·斯通的天生杀手》中扮演监狱副看守。\r\n\r\n1997年，文斯因在电视连续剧《谋杀一号》第二季扮演连环杀手克利福德·班克斯而获得艾美奖。最近，他出演了电影《康斯坦丁》。其他电影名称还包括《新黑色中国月亮》和心理恐怖片《雅各布的梯子》。\r\n\r\n客串电视节目', '...			', NULL);
INSERT INTO `wm_actor` VALUES (20, '詹姆斯·卡梅隆 James Cameron', 'http://localhost:8088/images/actor/2307b692-5e33-4d8e-86f4-9dcb8f9b6575.jpg', '　詹姆斯·卡梅隆，加拿大电影导演，擅长拍摄动作片以及科幻电影。詹姆斯·卡梅隆曾在加州的两年制社区学院学习物理学，但没多久又改主修英文，但在开学前自己休学。他到南加州大学的图书馆找资料自修学习电影特效技术，并用开卡车赚钱的空档写剧本。\r\n　　1977年，詹姆斯·卡梅隆看到了乔治·卢卡斯的经典科幻影片《星球大战》，他激动地意识到这就是他想要创造的东西。这使得詹姆斯·卡梅隆确立了自己的人生方向并开始为此忙碌起来，从未接受过专业训练的他开始到处寻找机会成为电影人，熟悉从镜头到摄影机导轨的各种电影制作器材，甚至和朋友制定了人生第一个拍摄计划，想要利用手头的设备和自制的模型制作一部10分钟的科幻影片！\r\n　　詹姆斯·卡梅隆是幸运的，他的才华很快就得到了好莱坞制片人罗杰·卡曼的赏识，从罗杰·卡曼那里他得到了人生第一份电影方面的工作——为卡曼工作室1980年的影片《星空大战》制作特技模型，第二年他就升职为这个工作室的另一部影片《恐怖星系》的第二小组导演和电影制作设计师。詹姆斯·卡梅隆是少有的以特技设计出身的导演之一，在以后的电影创作中，詹姆斯·卡梅隆一向把特技制作放在一个极其重要的位置，而且还经常亲自参与设计和实施特技的制作。', '2010年 第82届奥斯卡金像奖 最佳影片(提名) 阿凡达\r\n2009年 第14届圣地亚哥影评人协会奖 最佳导演(提名) 阿凡达\r\n1998年 第70届奥斯卡金像奖 最佳影片 泰坦尼克号				', NULL);
INSERT INTO `wm_actor` VALUES (21, '莱昂纳多·迪卡普里奥 Leonardo DiCaprio', 'http://localhost:8088/images/actor/ecf1c369-3716-4d86-af9a-df21ef3b24f4.jpg', '莱昂纳多·迪卡普里奥（Leonardo DiCaprio），1974年11月11日出生于美国加利福尼亚州洛杉矶，美国影视演员、制作人。\r\n1990年，16岁的他参加了电视剧《成长的烦恼》的拍摄。1993年，他凭借《不一样的天空》中的表演获得了第66届奥斯卡金像奖最佳男配角奖和第51届美国电影电视金球奖最佳男配角的提名  。1995年，拍摄电影《罗密欧与朱丽叶》，荣膺第47届柏林国际电影节最佳男主角。1997年，莱昂纳多主演了《泰坦尼克号》。2002年，莱昂纳多出演了史蒂文斯皮尔伯格导演的《逍遥法外》，并因此获得了金球奖男主角的提名。2004年，和马丁西科塞斯合作的《飞行家》为莱昂迎来一个美国金球奖最佳男演员奖。2006年，凭借《无间道风云》和《血钻》获得金球奖最佳男主角的双提名。2010，他出演了《盗梦空间》。2013年，他主演了电影《了不起的盖茨比》。2014年1月，他凭借《华尔街之狼》中的表演获得了金球奖最佳男主角奖；9月17日，被联合国授予联合国“和平使者”，关注气候变化  。2015年，莱昂纳多·迪卡普里奥收入2900万美元，居福布斯全球演员富豪榜排名第13位。\r\n2016年，凭借电影《荒野猎人》获得第73届金球奖最佳男主角奖和第88届奥斯卡金像奖最佳男主角奖 。', '2016年 第88届奥斯卡金像奖 最佳男主角 荒野猎人\r\n2015年 第14届华盛顿影评人协会奖 最佳男主角 荒野猎人\r\n2014年 第86届奥斯卡金像奖 最佳影片(提名) 华尔街之狼				', NULL);
INSERT INTO `wm_actor` VALUES (22, '凯特·温丝莱特 Kate Winslet', 'http://localhost:8088/images/actor/ad0690d1-66a0-4368-99cc-aa766a4ff1b4.jpg', '　　凯特·温斯莱特，英国著名女演员与歌手。曾获得演员工会奖与英国电影学院奖，并在第66届金球奖中以《革命之路》获戏剧类最佳女主角奖，并同时以《生死朗读》获戏剧类最佳女配角奖。凯特·温斯莱特也曾获得格莱美奖，并6次入围奥斯卡奖与艾美奖，最终以《生死朗读》获得奥斯卡最佳女主角奖。她于22岁时第2次入围奥斯卡奖，成为史上最年轻就获得2次入围的演员。《纽约杂志》称赞凯特·温斯莱特是“她的世代中最杰出的英语电影演员”。\r\n　　凯特·温斯莱特从电视上开始演员生涯，并在1991年英国广播公司的儿童科幻小说电视系列剧《Dark Season》中演出。然后她在1992年的电视电影《Anglo-Saxon Attitudes》与1993年的电视系列剧《急诊室》中演出。她在电影中的首次演出是彼得·杰克森所执导的《梦幻天堂》，并饰演女主角茱丽叶·休姆，凯特·温斯莱特这次演出受到许多正面的评价。\r\n　　之后，凯特·温斯莱特与艾玛·汤普逊演出李安执导的《理智与情感》，并且因此成名（尤其在英国）。凯特·温斯莱特也因这次演出获得英国电影学院奖（最佳女配角奖），并首次入围奥斯卡奖。她在1997年卖座电影《泰坦尼克号》中担任女主角萝丝·道森-卡维特，并因此闻名全球。', '2017年 第21届好莱坞电影奖 年度女主角 摩天轮\r\n2016年 第88届奥斯卡金像奖 最佳女配角(提名) 史蒂夫·乔布斯\r\n2015年 第18届英国独立电影奖 综艺奖				', NULL);
INSERT INTO `wm_actor` VALUES (23, '宫崎骏 Hayao Miyazaki', 'http://localhost:8088/images/actor/e396dd02-4772-4d23-a750-f5e9c6060382.jpg', '　　1922年，法国影评家埃利·福尔满含感情的预言：“终有一天动画片会具有纵深感，造型高超，色彩有层次……会有德拉克洛瓦的心灵、鲁本斯的魅力、戈雅的激情、米开朗基罗的活力。一种视觉交响乐，较之最伟大的音乐家创作的有声交响乐更为令人激动。”八十年后，世界动画界最接近埃利·福尔梦想的，首推宫崎骏。\r\n　　宫崎骏可以说是日本动画界的一个传奇，可以说没有他的话日本的动画事业会大大的逊色。他是第一位将动画上升到人文高度的思想者，同时也是日本三代动画家中，承前启后的精神支柱人物。宫崎骏在打破手冢治虫巨人阴影的同时，用自己坚毅的性格和永不妥协的奋斗又为后代动画家做出了榜样。\r\n　　宫崎骏的动画片是能够和迪斯尼、梦工厂共分天下的一支重要的东方力量。宫崎骏的每部作品，题材虽然不同，但却将梦想、环保、人生、生存这些令人反思的讯息，融合其中。他这份执著，不单令全球人产生共鸣，更受到全世界所重视，连美国动画王国迪斯尼，都要斟介宫崎骏的动画电影发行版权(亚洲地区除外)。', '2018年 第44届洛杉矶影评人协会奖 终身成就奖\r\n2015年 第87届奥斯卡金像奖 终身成就奖\r\n2014年 第86届奥斯卡金像奖 最佳动画长片(提名) 起风了				', NULL);
INSERT INTO `wm_actor` VALUES (24, '柊瑠美 Rumi Hiiragi', 'http://localhost:8088/images/actor/21d80c5e-2661-4aeb-8278-c8ee4588fce1.jpg', '　　柊 瑠美（非原名）（ひいらぎ るみ、1987年8月1日 - ）\r\n　　出生地：东京都葛饰区\r\n　　身高：155cm\r\n　　血型：A型\r\n　　星座：狮子座\r\n　　东京都立九段高等学校、日本大学文艺系毕业。\r\n　　自从6岁作为儿童角色开始工作，多数以CM演出。\r\n　　2001年公开电影『千与千寻』被担当主人公荻野千寻配音。', '...				', NULL);
INSERT INTO `wm_actor` VALUES (25, '入野自由 Miyu Irino', 'http://localhost:8088/images/actor/f8107a94-7bd9-474c-b6ad-941693f75fa1.jpg', '　　入野自由，日本男性声优，隶属日本junction公司旗下。代表作品：《我们仍未知道那天所看见的花的名字》宿海仁太 、《翼·年代记》小狼、《千与千寻》赈早见琥珀主、《天使怪盗》丹羽大助等。\r\n　　曾参加电视剧テレビドラマ「3年B组金八先生」。1999年出演过电影《ウルトラマンティガ&ウルトラマンダイナ&ウルトラマンガイア》，后来又客串演出了电视系列《ウルトラマンガイア》。在儿童时代，他就已参加过TV版动画《逮捕令》的配音工作。\r\n　　2001年，年仅13岁的入野自由开始了自己的声优生涯，在《千与千寻的神隐》，虽然声调平静，但干净透明的童音已经博得不少人的好感。入野自由2003年在《D.N.ANGEL》中担纲主角，更加引人注目，清亮而未经雕琢的嗓音，令人回味。\r\n　　由于在人气动画《D.N.ANGEL》中担纲主角，他更加引人注目了。丹羽大助是可爱的孩子，14岁的孩子，象水果一样漂亮，天空一样明朗。小自由软软的声音象棉花糖一样，演技也不错，当然不能跟中生代的各位著名的声优前辈们同日而语，但是入野的经验已是颇为丰富了，声线的变化也很自然。\r\n　　现在在《翼·年代记》与广播剧《私立堀镡学园》担任小狼与小龙（堀镡学园）的声优，使入野的人气更高。《翼》中的小狼更是给人一种亲切的感觉。\r\n　　最近还在PS2游戏《王国之心》为主角Sora配音，仍然表现的十分出色。不管怎样，清亮而未经雕琢的嗓音，就是这孩子的最大魅力吧！', '...				', NULL);
INSERT INTO `wm_actor` VALUES (26, '加布里埃莱·穆奇诺 Gabriele Muccino', 'http://localhost:8088/images/actor/e59054de-0cc8-4da5-8aa9-149953fb63dc.jpg', '　　加布里尔·穆奇诺，意大利导演，1967年5月20日出生于罗马。成名作有《最后一吻》、《同床异梦》等。', '2016年 第73届威尼斯电影节 酷儿狮奖(提名) 夏日光年\r\n2010年 第13届上海国际电影节 金爵奖 最佳影片 再吻我一次\r\n2002年 第18届圣丹斯电影节 观众奖 世界电影单元剧情片 最后一吻				', NULL);
INSERT INTO `wm_actor` VALUES (27, '威尔·史密斯 Will Smith', 'http://localhost:8088/images/actor/849029fa-7850-40e1-a70a-6c97acd84c49.jpg', '　　美国演员，高中毕业后痴心于歌唱事业，遂决定放弃上大学，做全职的艺人。1989年首次获得葛莱美奖“最佳饶舌歌演唱奖”。进而成为NBC电视影集《活力王子》(The Fresh Prince of Bel Air)的男主角。\r\n　　1993年以主角身份跃登大银幕，在一部讽刺纽约知识分子的文艺片《六度分离》之中，扮演一个自称是黑人巨星薜尼鲍迪儿子的老千。1995年主演警匪动作片《绝地战警》，大获成功。威尔·史密斯的片酬也由此升到五百万美金一部，大量的片商纷纷找上门来。1996年威尔·史密斯拍了一部科幻片《独立日》，大爆票房纪录，成为当年的票房冠军。之后，威尔·史密斯主演了《黑衣人》、《黑超特警组》，全球票房奏捷后，身价飙到巅峰。近几年接拍了许多卖座的动作片，始终为影迷爱戴。威尔·史密斯接替丹佐尔·华盛顿成为好莱坞最炙手可热的黑人男星。', '2016年 第73届金球奖 电影类 剧情片最佳男主角(提名) 震荡效应\r\n2015年 第19届好莱坞电影奖 年度男主角 震荡效应\r\n2014年 第34届金酸莓奖 最差男配角 重返地球				', NULL);
INSERT INTO `wm_actor` VALUES (28, '贾登·史密斯 Jaden Smith', 'http://localhost:8088/images/actor/063959b2-856d-479b-9c37-4a9f5c58ab88.jpg', '　　贾登·史密斯，1998年7月8日出生于美国加州洛杉矶，是威尔·史密斯的儿子，也是《功夫梦》的主演。在《功夫梦》盛大的首映式上，贾登跳太空步、打醉拳，还大方地展示自己的八块腹肌，惹得成龙不禁大叫要和威尔·史密斯换儿子。2006年他和父亲一同出演了《当幸福来敲门》，不俗的表现让他成为了不可多得的童星，前途一片光明。', '2014年 第34届金酸莓奖 最差男主角 重返地球\r\n2013年 第22届MTV电影奖 MTV电影奖 Biggest Summer Teen Bad Ass(提名) 重返地球\r\n2011年 第20届MTV电影奖 MTV电影奖 最佳动作明星(提名)				', NULL);
INSERT INTO `wm_actor` VALUES (29, '罗伯·莱纳 Rob Reiner', 'http://localhost:8088/images/actor/0035c082-a5a2-443f-8a08-d36d64ec797b.jpg', '　　罗伯·莱纳以演员起家，却因执导的第一部电影《摇滚万岁》(1984) 而成名，为自己在好莱坞界赢得具有票房潜力的商业导演之美名；尤其以两部在评论界倍受好评、票房上亦获成功的喜剧《公主新娘》(1987)及《当哈利碰上莎莉》(1989) 最受人瞩目；此外，青少年电影《犯贱情人》(1985)、由史蒂芬·金的惊悚小说改编而成的《危情十日》(1990)证明他有尝试各种影片类型的能力。1993年以《义海雄风》提名当年奥斯卡最佳影片奖，此后更成为一名制片人。\r\n　　而《伴我同行》等具有艺术气质的作品更让他获得了评论界与影迷的一致赞誉，在商业片和文艺片都有很高的建树，又是一例好莱坞电影全才的成功案例。', '1993年 第65届奥斯卡金像奖 最佳影片(提名) 好人寥寥				', NULL);
INSERT INTO `wm_actor` VALUES (30, '玛德琳·卡罗尔 Madeline Carroll', 'http://localhost:8088/images/actor/2300536c-8669-4a03-927f-894b76f6fe71.jpg', '　　Madeline Carroll 1996年3月18日在美国加利福尼亚州洛杉矶市出生，并且在那里长大。Madeline 三岁时就成为了模特，很快她在谢尔曼橡树市的一家美甲店里被文化经纪人 Wendy 发现，不久便开始了她的演艺生涯。\r\n　　Madeline 到目前为止已经拍摄过50多部全国性商业广告并在其中担任主角。她也客串和出演了几集电视剧，比如：《迷失》第三季的20集出现过，饰演和本杰明小时候一起的Annie；《铁证悬案》（Cold Case）（2003年、2009年），《实习医生格蕾》（Grey\'s Anatomy）（2008年）和《别对我撒谎》（Lie to Me）（2009年）。Madeline 还在电影《关键投票》（Swing Vote ）（2008年），《生化危机3：灭绝》（Resident Evil ：Extinction）（2007年）和《来电惊魂》（When a Stranger Calls）（2006年）中崭露头角。2008和2009这两年对于 Madeline 来说是惊喜不断的，它们会在2010年给 Madeline 带来更多片约。在2010年她将会有二部故事片《邻家特工》（The Spy Next Door）（2010年），《怦然心动》（Flipped）（2010年）和一部独立影片《咖啡》（cafe）（2010年）上映。\r\n　　Madeline 非常喜欢恐怖电影，到目前为止她最喜爱的角色是来自《恶夜狂魔》（Night Stalker）（2005年）的 Julie Medlock 和来自《生化危机3：灭绝》（Resident Evil：Extinction）（2007年）的 White Queen 。Madeline 也喜欢和他的三个兄弟还有小狗 Spot 玩。', '...			', NULL);
INSERT INTO `wm_actor` VALUES (31, '卡兰·麦克奥利菲 Callan McAuliffe', 'http://localhost:8088/images/actor/a6969cff-eda2-452c-8be1-f56ea30af8ea.jpg', '　　麦考利夫出生在悉尼郊区。就读于一所苏格兰人寄宿学校。当时他是学校唱诗班的领唱，并且通过了伦敦Trinity musical theatre的考试。可以说卡兰是童星出身，八岁的时候他就开始演戏。\r\n　　2007年卡兰在澳大利亚电视台拍摄的连续剧《Comedy Inc.》和《Blue Water High》中亮相，并引起了强烈的反响。 2009年，他主演的澳大利亚独立制作短片《Franswa Sharl》。这部短片让卡兰大步迈进了澳大利亚和美国的大银幕。\r\n　　2010年8月，卡兰主演了他的好莱坞处女作----电影《Flipped》，这部根据2001年Wendelin Van Draanen同名小说改编，由Warner Bros出品，ROB REINER导演的充满了浪漫气息的电影让卡兰成功的登陆好莱坞。\r\n　　随后，2010年5月，卡兰出演了Disney出品，Steven Spielberg和Michael Bay制作的科幻电影《关键第四号》中Sam一角。该片导演为D.J.Caruso，于当月在匹兹堡开机，预计于2011年2月18日公映。 此外，麦考利夫还将出演澳大利亚电视台拍摄的电视连续剧《Cloudstreet》。在这部由同名小说改编的电视剧中，卡兰饰演主角少年Quick Lamb。', '...				', NULL);
INSERT INTO `wm_actor` VALUES (32, '李·昂克里奇 Lee Unkrich', 'http://localhost:8088/images/actor/296a262f-5160-4f21-8dd3-c40937149832.jpg', '...', '2018年 第90届奥斯卡金像奖 最佳动画长片 寻梦环游记\r\n2017年 第89届美国国家评论协会奖 最佳动画片 寻梦环游记\r\n2011年 第83届奥斯卡金像奖 最佳改编剧本(提名) 玩具总动员3				', NULL);
INSERT INTO `wm_actor` VALUES (33, '安东尼·冈萨雷斯 Anthony Gonzalez', 'http://localhost:8088/images/actor/2954917f-f936-4b18-b0f4-6484eacd6bee.jpg', '...', '2018年 第45届动画安妮奖 最佳配音 寻梦环游记\r\n2017年 第16届华盛顿影评人协会奖 最佳配音 寻梦环游记				', NULL);
INSERT INTO `wm_actor` VALUES (34, '盖尔·加西亚·贝纳尔 Gael García Bernal', 'http://localhost:8088/images/actor/5ad52a63-b8e9-4788-addc-5361aefa3e44.jpg', '　　1978年出生於墨西哥瓜达拉哈拉市的演员世家，幼年时期便曾参与多部短片演出。17岁旅行至欧洲，并在两年後进入伦敦中央戏剧学院就读，成为第一位获选进入该校就读的墨西哥籍学生。\r\n　　他第一次受到国际影坛注目，是演出大导演阿利安卓岗札雷伊纳利图所执导的《爱情像母狗》（Amores Perros），该片曾获得奥斯卡最佳外语片提名。而他也因为本片夺得银羚奖最佳男主角及芝加哥电影节银雨果奖最佳男主角的殊荣。不久，他与导演阿方索克朗合作的公路电影《你他妈的也是》更让他与剧中另一位主角狄耶哥卢纳共同摘下威尼斯影展马塞罗?马斯托依安尼(Marcello Mastroianni)奖，从此星路大开。\r\n　　2004年，《中央车站》导演华特沙勒斯相中他俊美的外表与精湛演技，邀他在《革命前夕的摩托车日记》中演出传奇英雄切格瓦拉。这次他获得英国奥斯卡最佳男主角提名，并被英国电影协会誉为「最具潜力的明日之星」！同年稍晚，西班牙国宝阿莫多瓦与他合作《坏教慾》。在这部颇负争议的作品里，他大胆诠释幼年遭神父性侵，成年後迷上变装、变性，命运堪称坎坷的男主角。\r\n　　盖尔贾西亚贝纳近期作品包括《上帝的私生子》、《火线交错》等。这位有拉丁美洲梁朝伟之称的性格小生已然成为全球影坛光芒四射的超级巨星！', '2019年 第22届上海国际电影节 金爵奖 最佳影片(提名) 逃离小镇\r\n2017年 第74届金球奖 电视类 音乐/喜剧类剧集最佳男主角(提名) 丛林中的莫扎特 第三季\r\n2016年 第73届金球奖 电视类 音乐/喜剧类剧集最佳男主角 丛林中的莫扎特 第一季				', NULL);
INSERT INTO `wm_actor` VALUES (35, '罗曼·波兰斯基 Roman Polanski', 'http://localhost:8088/images/actor/f4a5e370-02b4-41f4-81ab-fb70df30350e.jpg', '　　罗曼·波兰斯基，1933年8月18日出生在侨居巴黎的一个波兰籍犹太人家庭里。1960年3月，波兰斯基从波兰国家电影学校毕业，带着一部新拍摄的短故事片。他来到了他的出生地--法国巴黎，在这里，他的作品引起了欧洲电影界的注意。回国后，他拍摄了使他多次获得电影节大奖并名噪世界影坛的影片《水中刀》。2009年9月27日，瑞士苏黎世电影节组委会宣布，因美国发出的为期31年的通缉令，而逮捕波兰斯基。波兰斯基本人去年委托其律师向美国法院提出免罪申请，要求重新审视当时的判案过程。\r\n　　2009年9月遭瑞士警方拘捕软禁后，2010年7月12日重获自由。\r\n　　2010年7月17日，波兰斯基的妻子法国演员埃玛纽埃勒-塞捏在第44届蒙特勒爵士音乐节演出。这是波兰斯基获释后首次公开亮相。瑞士当局12日宣布，拒绝向美国引渡被控诱奸少女的波兰裔法国大导演罗曼 波兰斯基，立即解除对波兰斯基的软禁。波兰斯基当天重获自由。', '2019年 第76届威尼斯电影节 主竞赛单元 金狮奖 (提名) 我控诉\r\n2014年 第39届法国凯撒电影奖 最佳影片(提名) 穿裘皮的维纳斯\r\n2013年 第66届戛纳电影节 主竞赛单元 金棕榈奖(提名) 穿裘皮的维纳斯				', NULL);
INSERT INTO `wm_actor` VALUES (36, '艾德里安·布洛迪 Adrien Brody', 'http://localhost:8088/images/actor/8577d3f0-147e-4d89-9e18-192162c85265.jpg', '　　1973年4月14日出生于纽约州纽约市，自小就立志长大当演员。12岁时他在一次儿童聚会上表演过魔术师，这是他第一次登台表演。阿德里安的母亲、著名摄影家西尔维亚·普莱奇从家庭照片中看出他的表演天赋，鼓励他去参加各种表演班，先后进入表演艺术高中和美国戏剧艺术学院就读。他在美国戏剧艺术学院学习期间，有机会参加了学校年轻人的周末节目，在那里，他很快发现了自己已进入了戏剧界的大门。\r\n　　2002年，主演了著名波兰导演罗曼·波兰斯基执导的历史传记片《钢琴家》。阿德里安扎实的表演，将温文而雅的斯皮尔曼忧郁之情演绎得感人至深。他甚至为了拍摄此片&quot;放弃了一切&quot;，每天要练4个小时的钢琴，体重也下降了30磅。当然，阿德里安的努力也取得了丰厚的回报，先后获得了法国恺撒、波士顿影评协会、美国影评人协会最佳男演员奖，第56届英国学院奖、第60届金球奖最佳男演员提名、第75届奥斯卡奖最佳男演员，成为奥斯卡历史上最年轻的影帝。作为演技派的代表人物，阿德里安·布劳迪在本片中令人赞叹的表现，将他的演艺事业推到了顶峰。', '2015年 第67届黄金时段艾美奖 迷你剧/电视电影 最佳男主角(提名) 胡迪尼\r\n2014年 第13届华盛顿影评人协会奖 最佳群戏(提名) 布达佩斯大饭店\r\n2013年 第3届豆瓣电影鑫像奖 鑫豆单元 最佳男演员(外语)(提名) 超脱				', NULL);
INSERT INTO `wm_actor` VALUES (37, '托马斯·克莱舒曼 Thomas Kretschmann', 'http://localhost:8088/images/actor/2a6dccf6-7d29-47e7-8a42-7fd7d48b8b0c.jpg', '　托马斯·克莱彻曼，1962年出生于东德德绍，80年代曾是东德奥林匹克游泳队队员，19岁离开东德前往西德，自此迈入演艺生涯。曾七次出演二战时的德国军官，最为大家熟悉的是在《钢琴师》中扮演的德国军官“Wilm Hosenfeld”，以及经典战争片《U-571》与《决战斯大林格勒》中的表演。', '	...			', NULL);
INSERT INTO `wm_actor` VALUES (38, '莫妮卡·贝鲁奇 Monica Bellucci', 'http://localhost:8088/images/actor/302dced1-5d55-41bf-82a7-e575e796950f.jpg', '　莫妮卡·贝鲁奇，出生于意大利的女演员兼模特儿。她凭着意大利电影《西西里的美丽传说》在国际影坛打开知名度，之后亦接连参演了不少好莱坞电影。\r\n　　莫妮卡·贝鲁奇拥有难以抗拒的美艳性感，堪称意大利女神。她的美来自于成熟女性的性感诱惑，黑色的大眼睛里燃烧着足以让人融化的热力，曲线玲珑的身材每一寸都在书写着“我是女人”的字样，“意大利首席性感偶像”的称号当之无愧。', '2017年 第65届圣塞巴斯蒂安国际电影节 终身成就奖				', NULL);
INSERT INTO `wm_actor` VALUES (39, '朱塞佩·苏尔法罗 Giuseppe Sulfaro', 'http://localhost:8088/images/actor/d5310f39-3eae-48ae-947f-913fac03e079.jpg', '...', '...		', NULL);
INSERT INTO `wm_actor` VALUES (40, '蒂姆·波顿 Tim Burton', 'http://localhost:8088/images/actor/7e758b09-64f7-4df7-a5b1-3d27449fd99f.jpg', '　　美国电影导演，生于加利福尼亚州的伯班克。他在加州艺术学院学习时得到了迪士尼的奖学金，这是用来赞助给年轻动画人以帮助他们成就梦想的基金。由此他开始正式成为迪士尼的动画师，之后成为导演。蒂姆·伯顿热衷描绘错位，善于运用象征和隐喻的手法，常以黑色幽默，独特的视角而著称。其代表作有《蝙蝠侠》、《大鱼》、《剪刀手爱德华》等。', '2019年 第64届意大利大卫奖 终生成就奖\r\n2016年 第3届豆瓣电影年度榜单 最受关注的导演(提名)\r\n2013年 第85届奥斯卡金像奖 最佳动画长片(提名) 科学怪狗				', NULL);
INSERT INTO `wm_actor` VALUES (41, '约翰尼·德普 Johnny Depp', 'http://localhost:8088/images/actor/8c7d1a11-877e-471d-b078-abb8c353c20b.jpg', '　　约翰尼·德普，1963年6月出生于美国肯塔基州的一个中产家庭，在佛罗里达州长大。15岁时他的父母离异，这让他一度成为不良少年并染上毒瘾。在舅父的唱诗班他迷恋上了音乐，还曾经自组摇滚乐队。在1983年，为了成为摇滚歌手，他前往洛杉矶发展。同年，他与他的第一任妻子，化妆师Lori Anne Allison结婚，并通过她认识了尼古拉斯·凯奇。正是在凯奇的大力引荐下，约翰尼·德普开始踏入影视圈，走上星路。\r\n　　小时候他不喜欢念书，经常一个人关在房间里苦练吉他，高中辍学后，他前往洛杉矶寻求发展，希望成为摇滚歌星。但阴错阳差，一次偶然试镜却让他与表演结了缘。他领衔主演的电视剧《龙虎少年队》播出后，迅速成为美国青少年的偶像。1984年，约翰尼·德普进入电影圈，在恐怖片《猛鬼街》中饰演角色，随后又在鬼才导演蒂姆·波顿的《剪刀手爱德华》中扮演主角，并凭借此片首次获得金球奖提名。拍摄期间，他与片中扮演女主角的维诺娜·赖德陷入热恋并订了婚，但这场婚约仅维持了三年便宣告破裂。1994年，他与鬼才导演蒂姆·波顿二次合作了另类黑白片《艾德伍德》，他凭借精湛绝伦的演出第三次荣获金球奖最佳男主角奖提名。此后，德普比波顿的合作一直持续至今。但在此期间，德普也出演了一系列脍炙人口的影片、塑造了很多令人津津乐道的角色，其中以《加勒比海盗》系列中的“杰克船长”最为著名。\r\n　　除了演戏，约翰尼·德普在导演方面也显现出了才华。他于1997年自编自导自演了《勇气》(The Brave)一片，有大牌明星马龙·白兰度加盟提携，影片夺得了多项奖项，1997年的坎城影展并将它列入竞赛片,让他在好莱坞出尽了风头。不仅如此，2009年德普又将小说《朗姆酒日记》搬上了银幕，自导自演。', '2019年 第39届金酸莓奖 最差男主角(提名) 淘气大侦探\r\n2018年 第38届金酸莓奖 最差男主角(提名) 加勒比海盗5：死无对证\r\n2017年 第37届金酸莓奖 最差男配角(提名) 爱丽丝梦游仙境2：镜中奇遇记				', NULL);
INSERT INTO `wm_actor` VALUES (42, '薇诺娜·瑞德 Winona Ryder', 'http://localhost:8088/images/actor/5aebb61d-3608-4f42-99f9-0fd32dc84c96.jpg', '　薇诺娜·瑞德，美国女演员，曾在美国戏剧学院修演艺课。1985年，瑞德寄出了一个试演录像带，希望能在电影《Desert Blom》里串上一角，但是不获录用。尽管如此，一位叫戴维•塞尔哲的作家和导演对她印象深刻，并让她在《美国小子》里饰演主角的朋友。之后将姓氏改为“瑞德”，做为她在片尾工作人员名单出现的名字。1987年，薇诺娜出演了影片《少女离家时》，被洛杉矶时报评为“一个值得注意的处女作”。1988年在影片《阴间大法师》中饰演了一个穿着歌德式服装的颓丧少女，获得了绝大多数的正面评价。\r\n　　1990年，薇诺娜在三部片子中演出，第一部是和她后来的男友，约翰尼·德普一起主演的《剪刀手爱德华》。之后，她去了意大利罗马拍弗朗西斯•科波拉的《教父III》。但她以“完全不能起床”为由辞演了此片，生病让瑞德取消了这计划。 薇诺娜的下一个角色出现在《怒海娇娃》（1990年）。薇诺娜凭片中Charlotte Flax一角，获得了金球奖最佳女配角提名。\r\n　　在演过多部少女片后，1991年接演吉姆·贾木许的《地球之夜》正式迈入成熟角色，而次年大导演科波拉的《惊情四百年》更将她推上票房明星的地位，自此成为好莱坞新一代的接班女星之一。\r\n　　长相甜美的赖德，一双水汪汪的大眼睛不知迷死多少影迷，帅哥约翰尼·德普、才子马特·达蒙都曾是她的裙下之臣。\r\n　　虽然被上天无限眷顾，现实中的薇诺娜却相当反叛，甚至因为盗窃而引起轩然大波，也因此严重影响了她近年在影坛的发展。这几年她重整旗鼓，以全新的心态和形象开展事业。2010年与波曼一起出演的影片《黑天鹅》，虽然在片中只是扮演配角，但薇诺娜的表现让喜爱她的影迷没有失望。', '2017年 第74届金球奖 电视类 剧情类剧集最佳女主角(提名) 怪奇物语 第一季\r\n1995年 第67届奥斯卡金像奖 最佳女主角(提名) 小妇人\r\n1994年 第66届奥斯卡金像奖 最佳女配角(提名) 纯真年代				', NULL);
INSERT INTO `wm_actor` VALUES (43, 'M·奈特·沙马兰 M. Night Shyamalan', 'http://localhost:8088/images/actor/53bc8358-527b-47f3-9146-279b126b2602.jpg', '　　生于印度，但成长于美国费城。父母及十二名近亲都是医生，但他除了在执导的《鬼眼》中客串了希尔医生一角外，并没有子承父业，而是从事了电影事业。八岁那年，沙马兰看了斯皮尔伯格执导的《法柜奇兵》一片后立志要做导演，父亲送了他一台超八米厘摄影机，到他十五岁时已经拍摄了四十五部短片，并开始着手写长片剧本。完成纽约大学电影系的学业后，他以家人赞助的七十五万美金拍成了首部作品《Praying With Anger》，描述他重回印度探访其出生地的故事，获得1993年美国电影学院影展的「最佳个人首部电影奖」。这使得他很容易筹募资金拍摄他的第二部编导作品《Wide Awake》，在他曾上学的费城天主教中学拍摄。1999年的主流灵异恐怖片《鬼眼》更大获成功，使他的电影事业平步青云。\r\n　　2000年他执导了影片《不死劫》(Unbreakable)，《第六感》的主演布鲁斯·威利斯再度出马，在这部片中他在一起车祸后开始不断受到神秘事件的困扰，尽管影片继承了《第六感》中的所有成功元素，却出人意料的票房冷淡。沙马兰遂打消了把这系列拍成三部曲的年头。也拒绝了执导《印第安纳琼斯》和《哈里波特》系列续集的邀请，决定重新开始，他的下一部电影《天兆》讲述了在农场生活的一家人一天早上发现自己的田里出现了“麦田怪圈”的事情，有了梅尔·吉布森的领衔，影片的票房轻易的突破了两亿美元。他的恐怖之旅延续到2004年的《神秘村》(The Village)，2006年的作品《水中女妖》(Lady in the Water)以及2008年的作品《灭顶之灾》，可是这三部作品票房均不理想。', '2014年 第34届金酸莓奖 最差导演(提名) 重返地球\r\n2011年 第31届金酸莓奖 最差导演 最后的风之子\r\n2009年 第29届金酸莓奖 最差导演(提名) 灭顶之灾				', NULL);
INSERT INTO `wm_actor` VALUES (44, '布鲁斯·威利斯 Bruce Willis', 'http://localhost:8088/images/actor/776e692a-cb94-421a-8379-f2555a63db30.jpg', '　　布鲁斯·威利斯，美国影视演员、制片人。其成名作是电视剧集《蓝色月光》，在《低俗小说》和《十二只猴子》等影片里有不错的表现，最成功的当数《虎胆龙威》系列。\r\n　　青少年期曾当过警卫、酒保，1980年起在几部电影中扮演不起眼的小角色，1984年参加电视影集《双面娇娃》试镜，获得入选，展开正式演艺生涯，并将触角伸向大银幕。1988年，关键性的一次转折，他在影片《虎胆龙威》中饰演了一位在封闭的大楼中孤身勇斗匪徒的警探约翰麦卡伦。影片上映后大受欢迎，成为当年全美最为卖座的影片之一。布鲁斯也由此一炮走红，一举成为国际瞩目的超级巨星。\r\n　　之后他相继出演了该片的后续三部，时间跨度近20年，均取得不错的票房成绩，银幕硬汉形象深入人心，全球影迷无数。除了在主流商业片中打打杀杀，布鲁斯也尝试过爱情片(《我们的故事》)、惊悚片(《灵异第六感》)、家庭片(《还我童真》)、独立电影(《领头狗》)甚至动画片配音(《篱笆墙外》)……其代表作除了《虎胆龙威》系列，还有吕克·贝松的科幻经典《第五元素》、昆汀·塔伦蒂诺的名作《低俗小说》以及罗伯特·罗德里格兹的《罪恶之城》、《刑房》等另类影片，可谓类型丰富。他还自己编剧(1994年他自己主演的《哈德森之鹰》)，2009年还将推出自己执导的影片《琼的三个故事》。\r\n　　官方这样评价他：体格强壮、轮廓鲜明，略带些谢顶的他向世人展示了这样一个典范，即只要做自己喜欢做的事情并充满决心和信心去达到目标的话，每个人都能够创造自己的命运。一个真正的男子汉，而非商业化的英雄。', '2019年 第39届金酸莓奖 最差男主角(提名) 虎胆追凶\r\n2013年 第28届美国独立精神奖 最佳男配角(提名) 月升王国\r\n2012年 第22届哥谭独立电影奖 最佳群戏(提名) 月升王国				', NULL);
INSERT INTO `wm_actor` VALUES (45, '海利·乔·奥斯蒙 Haley Joel Osment', 'http://localhost:8088/images/actor/a0a204aa-ba10-4cb9-8cab-f22cdcf32b22.jpg', '　　海利·乔·奥斯蒙特，美国童星。他的父亲是演员尤金·奥斯蒙特，母亲特里莎·奥斯蒙特是位教师。4岁时海利和父母逛家具店时被导演相中拍摄广告。几个月之后，他在《阿甘正传》里扮演汤姆·汉克斯的儿子，角色虽小但令人印象深刻，同年在电视剧集“Thunder Alley”（1994）里扮演爱德华·阿斯纳的曾孙。\r\n　　由于他卓越的喜剧表演才能，在“Thunder Alley”后又接演了两部情景喜剧。诺曼·杰威森给了他在电影《妈咪也疯狂》里扮演主角的机会，他轻松的扮演了这个戏剧性的角色。但是，直到热门电影《第六感》，这个年轻的演员才被公众所熟知。他对于角色Cole Sear极佳的塑造使之在11岁时就得到了一项奥斯卡的提名以及后来的获奖者迈克尔·凯恩的赞美之词“当我知道你获得提名，并看到你的表演时，我就知道你一定会赢。”之后，在《让爱传出去》里他的表演同样精彩。\r\n　　除了精彩的表演之外，他也在同行中赢得了相当的荣誉。在史蒂芬·斯皮尔伯格的科幻大片《人工智能》中，他担纲主演智能机器人，不负众望，其表现令人惊艳。但之后奥斯蒙特的演艺事业并未出现长足进步，陷入了童星成年后难再走红的怪圈。', '2000年 第72届奥斯卡金像奖 最佳男配角(提名) 第六感				', NULL);
INSERT INTO `wm_actor` VALUES (46, '梅尔·吉布森 Mel Gibson', 'http://localhost:8088/images/actor/3351cd35-6c85-4715-8754-d8f76df92dea.jpg', '　　梅尔·吉布森，出生于美国纽约州皮克斯基尔，是一位美籍爱尔兰裔澳大利亚电影演员、导演及制片。他的著名作品有电影系列《冲锋飞车队》、电影系列《轰天炮》、《勇敢的心》和他导演的《耶稣受难记》。', '2018年 第38届金酸莓奖 最差男配角 老爸当家2\r\n2017年 第89届奥斯卡金像奖 最佳导演(提名) 血战钢锯岭\r\n2016年 第20届好莱坞电影奖 年度导演 血战钢锯岭				', NULL);
INSERT INTO `wm_actor` VALUES (47, '安德鲁·加菲尔德 Andrew Garfield', 'http://localhost:8088/images/actor/2e2ddee1-d761-46db-bd09-e84f415d2eef.jpg', '　安德鲁·加菲尔德1983年生在美国洛杉矶，父亲是美国人，而母亲是英国人。3岁时父母搬回英国，在英国萨利郡长大。安德鲁曾在伦敦大学附属戏剧中心学校接受表演训练，出演过曼切斯特皇家交换剧院的《罗密欧与朱丽叶》和《Kas》等剧，为此获得04年MEN Theatre Award和06年Evening Standard Theatre Award奖肯定，06和07年凭借在伦敦西区舞台上的表现获得了Evening Standard Theater Awards的杰出新人奖和伦敦戏剧评论家协会颁发的最具潜力新人奖。此外他还在Channel 4电视剧《疯狂的荷尔蒙》中有固定的角色，并客串过《神秘博士》等热门剧。\r\n　　2007年通过多伦多电影节首映的青少年犯罪题材影片《男孩A》中安德鲁·加菲尔德出演了标题人物，一位在少教所里度过了大部分人生的的24岁男孩，他所犯下的滔天罪恶是谋杀了一个只有2岁的婴孩。该片与《荷尔蒙》同样改编自乔纳森·特里盖尔（Jonathan Trigell）获奖小说，探讨了被社会不负责任地描述为“天性邪恶”的少年犯走上犯罪道路的深层家庭和社会根源，是一部富有同情心的感人作品。 接下来加菲尔德又得到了在备受期待的《狮入羊口》中与罗伯特·雷德福、汤姆·克鲁斯以及梅丽尔·斯特里普对戏的机会，古装宫廷剧《另一个波琳家的女孩》中也有个小角色。正因如此，安德鲁·加菲尔德进入了《综艺》列出的年度最值得关注的10位新人名单。此后又加盟了特瑞·吉列姆执导的新片《帕那索斯博士的奇幻秀》。加菲尔德还凭借《男孩A》成为欧洲电影组织选定的9位闪亮新星之一，亮相08年2月的柏林电影节。之后他在英国电影和电视学院（BAFTA TV Award）颁奖礼上拿下了最佳电视电影男演员奖。\r\n　　2010年7月，在经过长时间的等待之后，新任蜘蛛侠的人选终于确定了。美国当地时间本周四，《蜘蛛侠》系列电影的发行公司索尼影业宣布，英国籍美国裔演员安德鲁·加菲尔德成为了托比·马奎尔的接班人，将在第四部《蜘蛛侠》系列电影中出演主角彼得·帕克。\r\n　　2010年，他与凯拉.奈特莉，凯芮.穆里根共同参演了英国科幻电影《别让我走》。同年他也在大卫.芬奇的电影《社交网络》里扮演被爱德华多.萨瓦林一角，加菲尔德因为这部电影而获得了更广泛的关注。', '2017年 第89届奥斯卡金像奖 最佳男主角(提名) 血战钢锯岭\r\n2016年 第15届华盛顿影评人协会奖 最佳男主角(提名) 血战钢锯岭\r\n2014年 第16届美国青少年选择奖 最佳科幻/奇幻电影男演员(提名) 超凡蜘蛛侠2				', NULL);
INSERT INTO `wm_actor` VALUES (48, '萨姆·沃辛顿 Sam Worthington', 'http://localhost:8088/images/actor/b95d9ac9-b1e5-4057-8a9f-ad9427fbcd50.jpg', '　　萨姆·沃辛顿出生于英格兰。他童年时举家移民至西澳洲的罗金汉姆地区，于位在费里曼图的约翰·寇汀表演艺术学校取得学位，接着在雪梨的国家艺术戏剧学院修习表演课程。沃辛顿的国际演艺生涯从接演一系列好莱坞电影的小角色开始，包括《阿凡达》、《哈特战争》和《抢救前线》等。', '2013年 第3届豆瓣电影鑫像奖 豆渣单元 最渣男演员(外语)(提名) 诸神之怒\r\n2010年 第19届MTV电影奖 MTV电影奖 最佳吻戏(提名) 阿凡达\r\n2009年 第11届美国青少年选择奖 Choice Movie Fresh Face Male(提名) 终结者2018				', NULL);
INSERT INTO `wm_actor` VALUES (49, '岩井俊二 Shunji Iwai', 'http://localhost:8088/images/actor/56bb1f16-d43e-4745-b2ff-4e1f0646ad08.jpg', '　　岩井俊二（Shunji Iwai，日文：いわい しゅんじ），日本电影导演，作家及记录片导演。日本新电影运动旗手，被誉为日本最有潜质的新近“映像作家”，也有中国影迷称他为“日本王家卫”。九十年代以来，日本电影复苏，在亚洲乃至世界范围内掀起了一场声势浩大的“日本新电影运动”。除了今村昌平、熊井启、筱田正浩等老一辈导演继续有佳作问世外，而北野武、岩井俊二、周防正行、竹中直人等一批中青年导演也都由于他们优秀的电影作品开始为世界所知。其中以影像清新独特、感情细腻丰富著称的岩井俊二堪称日本年轻一代导演中的佼佼者。\r\n　　岩井俊二不仅精通影视编导，还写作小说、散文，对于绘画、音乐也同样颇具功力。至今为止，他已经出版了《情书》、《燕尾蝶》、《华莱士人鱼》、《关于莉莉周的一切》四本小说以及《垃圾筐里的戏剧》、《现在和那时的岩井俊二》两本散文随笔。另外，他还创作了多部电影的绘画分镜头剧本和《情书》、《燕尾蝶》的漫画，在《四月物语》中的吉他与钢琴配乐，都是他亲自创作并演奏的。', '2019年 第38届香港电影金像奖 最佳两岸华语电影(提名) 你好，之华\r\n2018年 第55届台北金马影展 金马奖 最佳原著剧本(提名) 你好，之华\r\n2016年 第3届豆瓣电影年度榜单 最受关注的导演(提名)				', NULL);
INSERT INTO `wm_actor` VALUES (50, '中山美穗 Miho Nakayama', 'http://localhost:8088/images/actor/da0d2f7c-811e-4203-9c29-f73478f384a7.jpg', '　　中山美穗在1985年被星探发掘，凭借一部叫《惊动您了》的电视剧进入演艺圈。本着当时的偶像“演中山美穗而优则唱”的原则。同年6月，她发行了单曲《C》，开始了偶像歌星的生活。1986年的《水手服反叛同盟》和1987年的《偶像妈妈》令美穗成为当时红极一时的偶像，唱片销量也一路长红，而她更凭端正、美丽的相貌多次当选为“最美丽的偶像”。', '1998年 第21届日本电影学院奖 最佳女主角(提名) 东京日和\r\n1995年 第4届日剧学院赏 最佳女主角 全为了你\r\n1992年 第15届日本电影学院奖 话题奖 最具话题演员奖 拥抱波浪				', NULL);
INSERT INTO `wm_actor` VALUES (51, '丰川悦司 Etsushi Toyokawa', 'http://localhost:8088/images/actor/22f870a3-3cd2-4d3f-8b11-6aae0c741346.jpg', '　　丰川悦司出生在日本大阪西城的一个普通家庭，后来他们搬了好几次家，在\r\n　　丰川悦司(8张)东京都、千叶县都曾居住过。丰川爸爸是名鞋匠，母亲是家庭主妇，她有个比他大三岁的姐姐，还有一个妹妹。受母亲和姐妹们的影响，丰川迷上了芝居。据说他从上小学两三年级起就开始读《荧幕》等杂志了。丰川的父亲是个电影迷，丰川还在幼儿园的时候就经常跟随父亲去电影院看有关怪兽的影片。\r\n　　中学时，丰川迷上了篮球，身高也就在这时急速长高。丰川大学时进入文学部，并参加了大学戏剧研究部的一员。在此期间他曾先后参加了两次演出。此后，丰川对演剧产生了浓厚的兴趣。\r\n　　大学二年级时，丰川瞒着家里人辍学，决定迈进演艺界。20岁时他来到东京，并加入剧团\"Gekidan Sanjyumaru\"以磨练演技，但是成功并非一朝可得，那时的他必须为了房租而兼职。\r\n　　1983年，他初次登上舞台，参加一部剧的演出。此后，又先后多次参加舞台演出，并于1989年退团。\r\n　　在退团前他对电影产生兴趣，并在朋友的介绍下，参加了电影《你喜欢上了我》的演出。1991年丰川凭借电影《十二份温柔的日本人》受到人们注意，1992年分别在电影《星闪闪》、电视剧《NIGHT HEAD》中担当主角，1993年与当红女星宫泽理惠主演的电视剧《青春牡丹灯笼》引起了广泛关注。他在1994年的《爱没有明天》、1995年的《为爱生存》等剧中的出色表现得到人们的认可。\r\n　　丰川之所以在日本迅速窜红，主要原因有三点，一是因为他够酷的造型，二是他高大挺拔的身材（186cm），三便是他身份迥异的的角色。', '2012年 第74届日剧学院赏 最佳男主角(提名) 美雨\r\n2011年 第34届日本电影学院奖 最佳男主角(提名) 必死剑鸟刺\r\n2010年 第35届报知映画赏 最佳男主角 爱妻家				', NULL);
INSERT INTO `wm_actor` VALUES (52, '布莱恩·辛格 Bryan Singer', 'http://localhost:8088/images/actor/7dc46fad-2963-4c7f-ac0d-b441eb7f9e47.jpg', '　　布莱恩·辛格，生于美国新泽西，在犹太区长大，高中毕业后进入纽约市视觉艺术学校就读，后转学至南加州大学电影系。首部导演长片《公众通路》，在1993年圣丹斯影展获评审团大奖。两年后，以剧本布局巧妙的《刺激惊爆点》获东京影展青年导演竞赛的金樱花奖，自此一举成名。2002年执导科幻巨片《X战警》获商业成功，已成好莱坞大导演之一。\r\n　　童年与一非犹太裔朋友组成一个“纳粹俱乐部”，但不久便被其母亲发现而被迫解散，但由此而令他对自己的犹太人身份特别在意。南加州大学电影系毕业后编导了短片《狮穴》，由其高中同学伊森霍克主演，描述一群高中好友毕业数年后重聚却发现感情已疏远的故事。', '1998年 第11届东京国际电影节 主竞赛单元 最佳影片(提名) 纳粹追凶\r\n1996年 第49届英国电影学院奖 电影奖 最佳影片(提名) 非常嫌疑犯\r\n1995年 第8届东京国际电影节 青年电影竞赛 青年电影樱花金奖 非常嫌疑犯				', NULL);
INSERT INTO `wm_actor` VALUES (53, '拉米·马雷克 Rami Malek', 'http://localhost:8088/images/actor/3cdf3964-8b36-44b2-8f2a-a54bc958c697.jpg', '　　拉米·萨义德·马雷克 （Rami Said Malek；رامي مالك），埃及裔美国演员，1981年5月12日生于美国洛杉矶，有一个孪生弟弟。拉米参演了多部为人熟知的电影和电视剧，譬如《家庭战争》、《太平洋战争》以及《博物馆奇妙夜》等。拉米获埃文斯维尔大学美术学士学位，现居美国洛杉矶。', '2019年 第91届奥斯卡金像奖 最佳男主角 波西米亚狂想曲\r\n2018年 第17届华盛顿影评人协会奖 最佳男主角(提名) 波西米亚狂想曲\r\n2017年 第74届金球奖 电视类 剧情类剧集最佳男主角(提名) 黑客军团 第二季				', NULL);
INSERT INTO `wm_actor` VALUES (54, '本·哈迪 Ben Hardy', 'http://localhost:8088/images/actor/769cafe1-7ce8-4b44-84e6-9d5b36d7e847.jpg', '　英国演员本·哈迪（Ben Hardy）今天确认加盟《X战警：天启》（X-Men: Apocalypse），他将扮演一个神秘角色（或演年轻版\"天使\"），据说该角色在漫威漫画中也是一个比较重要的角色。\r\n　　1991年出生的本·哈迪（Ben Hardy）本姓“琼斯”（Jones），“哈迪”是他的艺名。其代表作是BBC肥皂剧《东区人》（EastEnders），他在其中饰演“Peter Beale”，不过前不久刚刚离开该剧。', '...				', NULL);
INSERT INTO `wm_actor` VALUES (55, 'Gilles Maheu', 'http://localhost:8088/images/actor/bc872819-caf1-4f51-bae4-9cccd4dd9ced.jpg', '...', '...				', NULL);
INSERT INTO `wm_actor` VALUES (56, '海伦娜·赛加拉 Hélène Ségara', 'http://localhost:8088/images/actor/cc392131-b94b-4974-89dc-2b0e537ac259.jpg', '...', '...				', NULL);
INSERT INTO `wm_actor` VALUES (57, '加劳 Garou', 'http://localhost:8088/images/actor/e79e1088-5f72-410b-a4ba-a13cfce823de.jpg', '　　获得了法国文化部授予的 艺术和文学勋章（军官级）', '...			', NULL);
INSERT INTO `wm_actor` VALUES (58, '克里斯多夫·甘斯 Christophe Gans', 'http://localhost:8088/images/actor/f0360b83-a6ac-479d-b71a-5f570ddaa590.jpg', '...', '2014年 第27届欧洲电影奖 观众选择奖(提名) 美女与野兽				', NULL);
INSERT INTO `wm_actor` VALUES (59, '拉达·米切尔 Radha Mitchell', 'http://localhost:8088/images/actor/1a1920b0-5396-495a-8b33-1e499e1ddd01.jpg', '　　澳大利亚女演员拉妲·米契尔——伍迪·艾伦《双生美莲达》中的美莲达，她的名字“Radha”是印度教主神Krishna的伴侣的名字。她的母亲曾是意大利天桥上的模特，但后来痴迷上印度文化。拉妲和母亲一样也是严格的素食主义者和瑜珈修习者。 十五岁开始出演电视剧，1997年的两部独立小片《高档货》和《破碎之梦》令她初露锋芒，2000年凭借范·迪塞尔主演的科幻动作片《星际传奇》进入好莱坞主流视野。之后又在《狙击电话亭》中饰演科林·法瑞尔的妻子，《怒火救援》中达科塔·范宁的母亲，并同马克·福斯特第二次合作了《寻找梦幻岛》一片，扮演约翰尼·德普的妻子，是一位势利的反派角色。拉妲也同黑暗的恐怖片非常有缘，出演过《鬼府来客》、《寂静岭》、《野兽》等， 这位澳大利亚女子似乎有着特别强韧的神经才被恐怖片导演屡屡看中。伍迪·艾伦是在看了拉妲在《十段渺小的爱情故事》中的表演之后，亲自打电话邀她出演美莲达的角色的。这令她感到难以置信。此后她又与乔什·哈奈特合作《莫扎特与鲸鱼》，还在《黄石的孩子》中与发哥上演吻戏，令很多中国观众记住了她的名字。在《杀出狂人镇》 2010年版中饰演男主角怀有身孕的妻子朱迪。', '...			', NULL);
INSERT INTO `wm_actor` VALUES (60, '肖恩·宾 Sean Bean', 'http://localhost:8088/images/actor/713e764a-aa8d-42e6-a0e5-ad1dddde0f9b.jpg', '　　肖恩·宾(SEAN BEAN)是英国演员，1959年4月17日出生于英国的谢菲尔德。父亲是一个钢铁手艺人，母亲是一个秘书，他还有一个妹妹。少年时代的肖恩是当地一帮小混混的头，有时候甚至会引来警察的干涉，十几岁的时候他的腿受伤，于是此前一直在校对踢球的肖恩足球明星之梦就此告吹。令肖恩在英国扬名的是一部改编自伯恩·柯威尔同名系列小说《沙普》（SHARPE）的电视剧集，在这部以19世纪初的动乱欧洲为历史背景的作品里，主人公沙普是个来自约克郡的穷小子，为了谋生而加入军队，从此开始了他的冒险生涯。从1993到1997年，肖恩一共拍了15部。', '...				', NULL);
INSERT INTO `wm_actor` VALUES (61, '罗伯托·贝尼尼 Roberto Benigni', 'http://localhost:8088/images/actor/93d8c080-43dd-4642-bd98-8b7585507f51.jpg', '　　1952年10月，罗伯托·贝尼尼出生于意大利托斯卡纳区，上面有三个姐姐。10岁时小罗伯托就爱上了舞台表演，少年时代从事过小丑和魔术师助理等工作。1971年来到罗马，并于次年首度登台表演喜剧《I Burosaui》。经过了五年舞台生活，贝尼尼参与创作了喜剧片《Berlinguer ti Voglio Bene》的剧本并在片中扮演Cioni Mario一角。此后很快获得《请求庇护》（Chiedo asilo）、《Il Pap’occhio》和《Il Minestrone》等影片中主要角色。进入80年代，在编剧、导演和表演领域十分活跃，喜剧才华得到进一步发挥。1986年首部英语对白影片《不法之徒》（Down By Law），神经质喜剧表演引起了国际性关注并获得意大利电影新闻记者协会最佳男演员银丝带奖。1993年出演了《顽皮警察》（Son of the Pink Panther），次年首度制作了影片《Il Mostro》后，开始把目光投向战争题材。1997年自编、自导、自演了描写纳粹集中营生活《美丽人生》（Life Is Beautiful）。该片国际上取得巨大成功，获奖无数，其中包括奥斯卡最佳男演员和四项最佳男演员银丝带奖。2000年获得欧洲电影奖、世界电影杰出成就奖。息影三年后，2002年贝尼尼带着《木偶奇遇记》（Pinocchio）重返影坛，却很不幸地获得了金酸莓最差导演奖。2005年执导了自己创作剧本影片《老虎和雪》（La Tigre e la neve）并在片中担任主演。', '1999年 第71届奥斯卡金像奖 最佳导演(提名) 美丽人生\r\n1999年 第71届奥斯卡金像奖 最佳男主角 美丽人生\r\n1998年 第51届戛纳电影节 主竞赛单元 金棕榈奖(提名) 美丽人生				', NULL);
INSERT INTO `wm_actor` VALUES (62, '罗伯托·贝尼尼 Roberto Benigni', 'http://localhost:8088/images/actor/ef3831ec-3004-4c84-978a-f603aa77e03f.jpg', '　1952年10月，罗伯托·贝尼尼出生于意大利托斯卡纳区，上面有三个姐姐。10岁时小罗伯托就爱上了舞台表演，少年时代从事过小丑和魔术师助理等工作。1971年来到罗马，并于次年首度登台表演喜剧《I Burosaui》。经过了五年舞台生活，贝尼尼参与创作了喜剧片《Berlinguer ti Voglio Bene》的剧本并在片中扮演Cioni Mario一角。此后很快获得《请求庇护》（Chiedo asilo）、《Il Pap’occhio》和《Il Minestrone》等影片中主要角色。进入80年代，在编剧、导演和表演领域十分活跃，喜剧才华得到进一步发挥。1986年首部英语对白影片《不法之徒》（Down By Law），神经质喜剧表演引起了国际性关注并获得意大利电影新闻记者协会最佳男演员银丝带奖。1993年出演了《顽皮警察》（Son of the Pink Panther），次年首度制作了影片《Il Mostro》后，开始把目光投向战争题材。1997年自编、自导、自演了描写纳粹集中营生活《美丽人生》（Life Is Beautiful）。该片国际上取得巨大成功，获奖无数，其中包括奥斯卡最佳男演员和四项最佳男演员银丝带奖。2000年获得欧洲电影奖、世界电影杰出成就奖。息影三年后，2002年贝尼尼带着《木偶奇遇记》（Pinocchio）重返影坛，却很不幸地获得了金酸莓最差导演奖。2005年执导了自己创作剧本影片《老虎和雪》（La Tigre e la neve）并在片中担任主演。', '1999年 第71届奥斯卡金像奖 最佳导演(提名) 美丽人生\r\n1999年 第71届奥斯卡金像奖 最佳男主角 美丽人生\r\n1998年 第51届戛纳电影节 主竞赛单元 金棕榈奖(提名) 美丽人生				', NULL);
INSERT INTO `wm_actor` VALUES (63, '尼可莱塔·布拉斯基 Nicoletta Braschi', 'http://localhost:8088/images/actor/1d00511f-9993-4be2-960e-457a0f046953.jpg', '　尼可莱塔·布拉斯基，意大利演员。她的朴实掩盖不了她的高贵，她的优雅，她举手投足间有一种淡淡的性感，很平淡，却让人回味，不知不觉由内而外的欣赏她。Braschi与Benigni相识于片场，贝尼尼对其一见钟情，随后结婚。婚后两人感情甚笃，布拉斯基不仅是贝尼尼生活中的女主角，更是做了贝尼尼几乎所有导演影片中的女主角，是影坛著名的“夫妻档”。《不法之徒》《遮蔽的天空》《木偶奇遇记》《老虎与白雪》', '2019年 第64届意大利大卫奖 最佳女配角(提名) 幸福的拉扎罗				', NULL);
INSERT INTO `wm_actor` VALUES (64, '克里斯托弗·诺兰 Christopher Nolan', 'http://localhost:8088/images/actor/c516ba28-c9fc-4ef6-9a4c-a409c95a98a0.jpg', '克里斯托弗·诺兰，1970年7月30日出生于伦敦，英国导演、编剧、摄影师及制片人。\r\n1996年，克里斯托弗·诺兰拍摄了个人首部故事片《追随》，在旧金山电影节上放映并受到关注。2000年，诺兰凭《记忆碎片》获得第74届奥斯卡和第59届金球奖最佳原创剧本提名。2005年，执导拍摄电影《蝙蝠侠：侠影之谜》。2006年，执导作品《致命魔术》以其诡异的气氛和精细的结构获土星奖最佳科幻电影。2008年，凭借电影《黑暗骑士》获第35届土星奖最佳编剧奖，该片上映一周就打破北美多项票房纪录成为全球第四部票房达到10亿美元的电影。2012年，再度执导系列电影《蝙蝠侠：黑暗骑士崛起》，该片获土星奖最佳导演提名。2010年，凭《盗梦空间》获得金球奖最佳导演及最佳原创剧本提名。\r\n2015年3月，凭借执导电影《星际穿越》入围第41届美国科幻恐怖电影奖土星奖最佳导演 。2017年，凭借《敦刻尔克》荣获亚特兰大影评人协会奖最佳导演 、第90届奥斯卡金像奖最佳导演提名。', '2018年 第90届奥斯卡金像奖 最佳影片(提名) 敦刻尔克\r\n2017年 第16届华盛顿影评人协会奖 最佳导演 敦刻尔克\r\n2015年 第2届豆瓣电影年度榜单 最受关注的导演(提名)				', NULL);
INSERT INTO `wm_actor` VALUES (65, '约瑟夫·高登-莱维特 Joseph Gordon-Levitt', 'http://localhost:8088/images/actor/87d3fc48-eb09-47d4-86e3-119652671726.jpg', '　　约瑟夫·高登-莱维特，美国男演员。童星出身的他1992年初上银幕时，只有11岁，在导演罗伯特．雷德福的《大河恋》中饰演主角的哥哥，此片让他赢得Young Artist Award for Best Actor Under Ten大奖。\r\n　　1999年，约瑟夫·高登-莱维特被《Teen People Magazine》选为21岁以下最热门的21位明星之一。1996年的美国热门电视连续剧《外星人报到》，为约瑟夫赢得两项大奖：Hollywood Reporter Young Star Awards以及美国演员工会奖。\r\n　　2005年以儿童性侵犯为题材的电影《神秘肌肤》中，约瑟芬·戈登·罗维特饰演的男孩尼尔令人对其演技大开眼界。尽管年纪轻轻，作为一个戏路宽广的演员，约瑟夫·高登-莱维特已经在电视和电影表演方面展现出卓越的的自我。很多人认为约瑟夫与希斯·莱杰长相有些相似，可以说是“帅版希斯·莱杰”；2010年在参演了诺兰导演的《盗梦空间》后，被广大中国影迷昵称为“囧瑟夫”。', '2017年 第21届金卫星奖 电影部门 剧情片最佳男主角(提名) 斯诺登\r\n2014年 第23届MTV电影奖 MTV电影奖 最佳吻戏(提名) 唐璜\r\n2013年 第19届美国演员工会奖 电影奖 最佳群戏(提名) 林肯				', NULL);
INSERT INTO `wm_actor` VALUES (66, '昆汀·塔伦蒂诺 Quentin Tarantino', 'http://localhost:8088/images/actor/c6a7e4d2-ce5d-4390-9a4b-a27f0ffbccbe.jpg', '　　毫无疑问，昆汀是个鬼才。而所谓“鬼才”，就是“鬼才知道他在想什么”。\r\n　　美国导演，演员及奥斯卡获奖编剧。生于美国田纳西州的挪克斯维尔。昆汀这个名字来源于影片《枪之烟火》中的角色。他在20世纪九十年代作为风格独特的导演迅速成名，他擅长非线性讲述故事，难忘的对白及血腥场面，将美国本土B级片的黑色暴力风格发扬光大。昆汀是20世纪90年代美国独立电影革命中重要的年轻导演，以独特的个性和对商业电影和艺术电影均有深刻理解著称。\r\n　　他两岁时随家迁居洛杉矶，在这座电影气息浓厚的城市长大，童年起就爱上了电影。18岁中学毕业后，在曼哈顿海滩一家名为“录像档案馆”的录像租赁店工作。此间，逐渐领会并掌握了众多电影知识和技法。1991年，他凭出售《致命浪漫》剧本所得的5万美元，决定开始拍摄自己的第三个剧本《落水狗》。1994年编导的第二部影片《低俗小说》大获全胜，夺得戛纳影展金棕榈奖，次年获奥斯卡最佳原著剧本奖，奠定了他的好莱坞地位。1997年的《危险关系》表现平淡，03、04年连续推出《杀死比尔Ⅰ》和《杀死比尔2 》，再掀昆式暴力美学高潮。后与好友罗伯特·罗德里格兹合导《刑房》，献出《金刚不坏》，受到影迷追捧。2009年拍摄了布拉德·皮特领衔主演的《无耻混蛋》。', '2019年 第72届戛纳电影节 主竞赛单元 金棕榈奖(提名) 好莱坞往事\r\n2016年 第73届金球奖 电影类 最佳编剧(提名) 八恶人\r\n2015年 第87届美国国家评论协会奖 最佳原创剧本 八恶人				', NULL);
INSERT INTO `wm_actor` VALUES (67, '乌玛·瑟曼 Uma Thurman', 'http://localhost:8088/images/actor/1e9038d0-6263-4456-9385-83d663174d93.jpg', '　　著名的好莱坞女星乌玛·瑟曼天生丽质，身高178厘米，身材纤细，一向是不少影迷心目中的标准美女，乌玛·瑟曼1970年4月29日出生在美国波士顿，父亲是研究印度佛教哲学的专家，母亲来自瑞典，是个心理分析医生。东方文化的熏陶和一袭欧洲风韵，使美丽的乌玛瑟曼气质独特，且有种神秘感。乌玛·瑟曼有一种其他女演员无法匹敌的致命性感魅力。她的表演力戒张扬，在平淡中表现人性深度。\r\n　　乌玛的童年极不快乐，由于来自信奉佛教的家庭，在全部信仰基督的美国小镇上她倍受歧视，被人讥讽为“丑陋”。她没有读大学，16岁便离开了家乡只身闯荡纽约，17岁时她已成为一名很不错的模特。 乌玛最早是在VOGUE上拍摄家居照片出道，那些慵懒的家居形象让人印象深刻。\r\n　　1988年，她有了电影处女作《亲吻老爸说晚安》，接下来的《终极天将》和《危险关系》瑟曼开始崭露头角，但她并未寻着这条青春玉女的路线走下去。1990年，著名导演菲利普·考夫曼推出第一部标有“NC-17”级的影片《情迷六月花》，乌玛-瑟曼在影片中饰演美艳绝伦且孤傲不羁的巴黎女子琼，在丈夫作家亨利和爱慕者妮恩之间大玩双性恋情。1992年在《致命恋人》中她是配合姐姐制造命案的手。《 蓝调女牛仔》使她成了长着巨大拇指且有着同性恋倾向的女孩西西，这些角色都有些怪异，但乌玛瑟曼的表演却力戒张扬，在平淡中表现人性深度。不过乌玛-瑟曼主演的影片大多没有大红大紫，所以想不起她在银幕上的经典形象，倒是几部做配角的电影让她大放异彩，有的甚至成为影片的标志。最著名的当属《低俗小说》，黑色短发装扮下的乌玛完全是另外一种风情，她性感狂野，趴在床上跷着脚叼着烟眼神迷离的样子成为电影史上最经典的海报之一。《危险关系》中纯得连偷尝禁果都闪耀着圣洁光茫的处女塞西尔，《蝙蝠侠与罗宾》中妖艳邪恶、身体象藤蔓一样扭曲的毒藤女，虽然失败，却让乌玛一显身手。之后，她和连姆·尼尔森主演了新版的《悲惨世界》，与拉尔夫·费因斯合作了《复仇者》，与法国著名演员杰拉尔·德帕迪约合作了古装剧《欲望巴黎》，参演了英国文艺片名导詹姆斯·艾弗瑞的作品《金碗》等。', '2019年 第64届意大利大卫奖 大卫特别奖\r\n2012年 第64届黄金时段艾美奖 剧情类 最佳客串女演员(提名) 名声大噪 第一季\r\n2007年 第16届MTV电影奖 MTV电影奖 最佳打斗(提名) 我的超级前女友				', NULL);
INSERT INTO `wm_actor` VALUES (68, '刘伟强 Andrew Lau', 'http://localhost:8088/images/actor/c88ae4a9-999c-47c5-b076-ae4a77e92e98.jpg', '　　刘伟强，中国香港著名导演。代表作品有《风云》、《无间道》、《伤城》、《头文字D》等。曾斩获香港电影金像奖、台湾电影金马奖和香港电影金紫荆奖最佳导演等奖项。\r\n　　刘伟强1960年出生于香港，自小爱好摄影，1980年中学毕业后投身邵氏。第一部参与的电影为刘家良的《十八般武艺》，当一名摄影小工。1985年正式担任摄影师，作品有《龙虎风云》和 《旺角卡门》等。1990年开始执导筒，拍摄了《朋党》，《人皮灯笼》，《庙街故事》等影片。\r\n　　1995年与文隽，王晶合组最佳拍档电影制作公司，1996年执导最佳拍档的创业作《古惑仔之人在江湖》，结果此片成绩斐然，接连三部《古惑仔》系列电影全部进入当年香港十大卖座电影行列，掀起香港影坛的江湖片热潮，《古惑仔》系列更是连拍六集，长盛不衰。刘伟强也一举进入一线导演行列，其后一发而不可收，连续拍摄了多部大制作卖座强片。\r\n　　近些年来已成为振兴香港影坛的中流砥柱，尤其善于拍摄漫画改编电影以及大量运用电脑特技的影片。2002-2003年刘伟强与麦兆辉执导的无间道三部曲则把刘的事业推向的颠峰。曾被王家卫御用摄影师杜可风称赞为“刘是香港手提摇镜最棒的”。刘伟强导演至少引领了三次香港电影的新浪潮，第一次《古惑仔》一系列，之后《中华英雄》、《风云》，再就是《无间道三部曲》。', '2013年 第32届香港电影金像奖 最佳摄影(提名) 大上海\r\n2007年 第26届香港电影金像奖 最佳摄影 伤城\r\n2006年 第25届香港电影金像奖 最佳导演(提名) 头文字D				', NULL);
INSERT INTO `wm_actor` VALUES (69, '刘德华 Andy Lau', 'http://localhost:8088/images/actor/f163f717-c4a8-412c-8964-ed43305989b3.jpg', '　　刘德华，著名演员和歌手。1990年代被封为香港“四大天王”之一，亦是大中华地区极具代表性的艺人之一。 刘德华是个多线发展的艺人：作为歌手，他是吉尼斯世界纪录大全中，获奖最多的香港歌手；电影方面他曾三度夺得香港电影金像奖最佳男主角、以及获得两座台湾金马奖影帝，截止2010年参与出演的电影已经超过140部。\r\n　　刘德华现在是映艺集团的老板，作为投资人参与制作了多部华语电影。\r\n　　1999年，刘德华获得“香港十大杰出青年”的荣誉，2000年11月则顺利荣登“世界十大杰出青年”，成为获此殊荣的少数几位香港艺人。2006年7月7日，由于他“是香港最受尊重和喜爱的演艺名人之一，对香港电影及音乐贡献良多。其严谨专业的工作态度，足以成为年轻人的典范”和“以表彰他在表演艺术方面的成就”的原因而被香港演艺学院授予荣誉院士称号，成为获此荣誉的少数几位香港演员之一。\r\n　　刘德华笃信佛教，法号慧果，热心公益，时常参与慈善活动。2008年，刘德华获香港特别行政区政府委任为太平绅士。2010年4月23日，刘德华获任中国残疾人福利基金会理事并担任副理事长。\r\n　　2010年5月2日，刘德华获颁第十二届世界杰出华人奖同时获颁授加拿大纽奔驰域蓝仕桥大学荣誉博士学位。', '2018年 第37届香港电影金像奖 最佳男主角(提名) 拆弹专家\r\n2016年 第35届香港电影金像奖 最佳男主角(提名) 失孤\r\n2015年 第30届中国电影金鸡奖 最佳男主角(提名) 失孤				', NULL);
INSERT INTO `wm_actor` VALUES (70, '梁朝伟 Tony Leung Chiu Wai', 'http://localhost:8088/images/actor/309f04e8-792d-4f32-a033-5119dae6a9f2.jpg', '梁朝伟，1962年6月27日出生于中国香港，祖籍广东省台山市，中国香港男演员、歌手，国家一级演员，毕业于香港无线训练班第11期。\r\n1982年考入无线电视艺员训练班，正式进入演艺界。20世纪80年代拍摄了多部电视剧。1984年因出演电视剧《鹿鼎记》中的韦小宝一角受到关注。1990年主演电影《喋血街头》 。1994年凭借电影《重庆森林》获得第14届香港电影金像奖最佳男主角奖、第31届台湾电影金马奖最佳男主角奖。1997年凭借电影《春光乍泄》获得第17届香港电影金像奖最佳男主角奖 。1998年主演电影《暗花》。2000年凭借电影《花样年华》获得第53届戛纳国际电影节最佳男演员奖以及第20届香港电影金像奖最佳男主角。\r\n2002年主演张艺谋执导的电影《英雄》 。2003年凭借电影《无间道》获得第22届香港电影金像奖最佳男主角奖、第40届台湾电影金马奖最佳男主角奖 。2005年凭借电影《2046》获得第24届香港电影金像奖最佳男主角奖 。2007年凭借电影《色·戒》获得第44届台湾电影金马奖最佳男主角奖。2011年主演王家卫执导的影片《一代宗师》。2014年受邀担任第64届柏林国际电影节主竞赛单元评委。2015年1月8日，主演《一代宗师》3D版上映 。同年，接拍由王家卫监制的电影《摆渡人》。2016年11月拍摄古装喜剧片《捉妖记2》。2015年获得法国文化部颁授的“法国艺术与文学军官勋章”。2017年3月，主演电影《捉妖记2》。', '2014年 第33届香港电影金像奖 最佳男主角(提名) 一代宗师\r\n2013年 第50届台北金马影展 金马奖 最佳男主角(提名) 一代宗师\r\n2009年 第28届香港电影金像奖 最佳男主角(提名) 赤壁(上)				', NULL);
INSERT INTO `wm_actor` VALUES (71, '史蒂文·斯皮尔伯格 Steven Spielberg', 'http://localhost:8088/images/actor/1f18bae8-ae81-4c03-8f07-5ca89d22dde6.jpg', '史蒂文·斯皮尔伯格（Steven Allan Spielberg），1946年12月18日出生于美国俄亥俄州辛辛那提市，美籍犹太裔导演、编剧、制片人。\r\n1974年，完成了个人首部电影长片《横冲直撞大逃亡》。1975年，执导了惊悚电影《大白鲨》，凭借该片获得了第33届美国电影电视金球奖电影类-最佳导演提名 。1981年6月12日，拍摄的动作冒险电影《夺宝奇兵》上映。1982年6月11日，执导的科幻家庭电影《外星人E.T.》上映，凭借该片获得了第40届美国电影电视金球奖电影类-最佳导演提名。1985年12月18日，执导的剧情片《紫色》上映。1993年6月11日，拍摄的科幻冒险电影《侏罗纪公园》上映；11月30日，执导的战争电影《辛德勒的名单》上映 ，凭借该片获得了第51届美国电影电视金球奖电影类-最佳导演和第66届奥斯卡金像奖最佳导演奖  。1998年7月24日，拍摄的战争电影《拯救大兵瑞恩》上映，凭借该片获得了第56届美国电影电视金球奖电影类-最佳导演和第71届奥斯卡金像奖最佳导演奖 。2002年6月21日，执导的科幻悬疑电影《少数派报告》上映，由此获得了第29届土星奖最佳导演奖 。2005年12月23日，拍摄的惊悚电影《慕尼黑》上映。2009年1月12日，获得了第66届美国电影电视金球奖终身成就奖 。\r\n2011年10月26日，执导的3D动画电影《丁丁历险记：独角兽号的秘密》上映。2012年11月16日，拍摄的传记电影《林肯》上映。2015年10月16日，执导的传记电影《间谍之桥》上映。2018年3月26日，拍摄的科幻冒险片《头号玩家》首映。', '2019年 第55届美国声音效果协会奖 Filmmaker Award\r\n2018年 第90届奥斯卡金像奖 最佳影片(提名) 华盛顿邮报\r\n2017年 第89届美国国家评论协会奖 最佳影片 华盛顿邮报				', NULL);
INSERT INTO `wm_actor` VALUES (72, '连姆·尼森 Liam Neeson', 'http://localhost:8088/images/actor/9ec14b44-f5ac-473b-b5fa-3ed791b91b5f.jpg', '　　连姆•尼森出生于北爱尔兰，父母亲是学者，姊妹为教师。尼森曾经在吉尼斯啤酒厂做过叉车工，还当过卡车司机，助理建筑师以及业余拳击手。他也曾为了成为一名教师而在贝尔法斯特的圣\r\n　　玛丽师范学院求学。但1976年，他加入贝尔法斯特剧场表演队伍，在戏剧“The Rising People”中奉献了第一次专业表演。\r\n　　两年后，他来到都柏林的阿比剧院继续演出经典剧目。在那里，他被导演约翰•保曼（John Boorman）发掘，演出了首部电影《黑暗时代》（Excalibur，1981）。随着本片的上映，陆续在20几部电影中演出各式各样的角色，其中包含和梅尔•吉柏逊、安东尼•霍普金斯合演的《叛逆巡航》（1984年）。他在片中饰演一位因质疑信仰而饱受折磨的神职人员，并因此获得Evening Standard戏剧奖。利亚姆•尼森接下来获得赞扬的电影有法庭片《铁律柔情》（1989年），他饰演一位又聋又哑的越战老兵；和戴安•基顿合演的《好母亲》（1989年），饰演一位狂热的雕刻家；及惊悚科幻片《魔侠震天雷》（1990年），饰演一位因意外毁容而被迫隐居的科学家。随后，在《天降神迹》（Leap of Faith，1992）和《贤伉俪》(Husbands and Wives，1992）中的出色表演，为尼森赢得了在史蒂芬•斯皮尔伯格的电影《辛德勒的名单》（Schindler\'s List，1993）中扮演奥斯卡•辛德勒的机会。该片让尼森获得了金球奖和奥斯卡奖提名。', '2016年 第15届华盛顿影评人协会奖 最佳配音 当怪物来敲门\r\n2013年 第33届金酸莓奖 最差男配角(提名) 超级战舰\r\n2005年 第62届金球奖 电影类 剧情片最佳男主角(提名) 金赛性学教授				', NULL);
INSERT INTO `wm_actor` VALUES (73, '本·金斯利 Ben Kingsley', 'http://localhost:8088/images/actor/f701bc9a-3866-4cad-97fb-d2ac4c2abda3.jpg', '　　本·金斯利，有着英国、东印度和南非的血统。父亲是旅英的印度裔全科医师，母亲则是一个英裔模特和演员。由于受到母亲的熏陶，自幼爱好戏剧，并发现自己善于模仿动作表情，于是在曼彻斯特开始参加业余戏剧表演。1967年，刚23岁的金斯利首次在伦敦西区的奥德乌奇(Aldwych)剧场参加了职业表演。在这以后参加了著名的皇家莎士比亚剧团(Royal Shakespeare Company)、皇家宫廷剧团(Royal Court)和国家剧院(National Theatr)，经常登台表演莎翁名剧。', '2016年 第22届美国演员工会奖 电视奖 电视电影/迷你剧最佳男演员(提名) 图坦卡蒙\r\n2015年 第42届动画安妮奖 最佳配音 盒子怪\r\n2013年 第15届美国青少年选择奖 最佳电影反派(提名) 钢铁侠3				', NULL);
INSERT INTO `wm_actor` VALUES (74, '弗朗西斯·福特·科波拉 Francis Ford Coppola', 'http://localhost:8088/images/actor/c804ae71-2872-41d1-9744-10204f0de486.jpg', '　　弗朗西斯·福特·科波拉是名震世界影坛的大导演。1939年，他出生于美国底特律一个意大利移民家庭，其父是位音乐指挥家兼作曲家。科波拉童年时就混迹于剧院后台，还经常偷看父亲的演出。他从小就对电影十分着迷，17岁时，他进入了霍夫斯特拉学院戏剧系，在参加校内戏剧演出时，他几乎什么都能干。大学毕业后，科波拉进入加州电影学院专攻电影，其间，他应聘参加了好莱坞导演罗杰·考尔曼的摄制组，当了一名打杂工。\r\n　　从加州电影学院毕业的科波拉起先充当的是编剧的角色。但一开始,名不见经传的他的作品往往被人忽视、贬低。直到1970年，他因《巴顿将军》（《PATTON》）一片获得奥斯卡最佳剧本奖时，他的才能才得到了社会的承认。\r\n　　此后，科波拉又满怀热情地担任了导演的职务。他的最大的特点就是善于把影片的立意与美国观众的需要、美国社会的注意力相结合。1972年，科波拉执导了影片《教父》(《The Godfather》),他有意识地去表现了美国黑手党的活动与美国政界、司法界的关系，而且还从同情的视角去表现了这些黑社会人物的“人性”。他们并不是一般影片中常见的那种杀气腾腾的恶霸歹徒，而是同样重视家庭伦理的“严父”和“富有责任心”的“有志青年”。这种新的构思使美国观众有耳目一新之感，特别是影片宣传的那种奋斗精神与美国社会一贯提倡的“美国精神”十分契合，在观众中引起了极大的反响。\r\n　　弗朗西斯·福特·科波拉、马丁·斯科塞斯、乔治·卢卡斯、史蒂文·斯皮尔伯格合称好莱坞80年代四大导演。', '2010年 第82届奥斯卡金像奖 埃尔文·G·撒尔伯格纪念奖\r\n2002年 第50届圣塞巴斯蒂安国际电影节 50周年特别大奖\r\n1992年 第49届威尼斯电影节 终身成就金狮奖				', NULL);
INSERT INTO `wm_actor` VALUES (75, '马龙·白兰度 Marlon Brando', 'http://localhost:8088/images/actor/032713cc-03f5-4ed9-ab93-0a39e82a57bc.jpg', '马龙·白兰度（Marlon Brando，1924年4月3日—2004年7月1日），出生于美国内布拉斯加州奥马哈市，美国影视演员。\r\n1950年，他主演了影片《男儿本色》。1951年，他凭借在《欲望号街车》中的表演而获得第24届奥斯卡金像奖最佳男主角提名。1953年，马龙·白兰度凭借《凯撒大帝》再次荣获英国电影学院最佳外国男演员奖和奥斯卡最佳男主角提名。1954年，他凭借《码头风云》获得第27届奥斯卡金像奖最佳男主角奖。1972年，他主演的《教父》令他再次荣获奥斯卡金像奖最佳男主角奖。1979年，他出演了又一部科波拉执导的《现代启示录》，之后，他还出演了《巴黎最后的探戈》《超人》和《血染的季节》等。2001年，他客串了《大买卖》，这是他最后一次在大银幕上亮相。1999年，被美国电影学会选为“百年来最伟大的男演员”第4名。\r\n2004年7月1日，马龙·白兰度在洛杉矶一家医院逝世，享年80岁。', '1990年 第62届奥斯卡金像奖 最佳男配角(提名) 血染的季节\r\n1989年 第3届东京国际电影节 主竞赛单元 最佳男演员 血染的季节\r\n1974年 第46届奥斯卡金像奖 最佳男主角(提名) 巴黎最后的探戈				', NULL);
INSERT INTO `wm_actor` VALUES (76, '阿尔·帕西诺 Al Pacino', 'http://localhost:8088/images/actor/04a3a1e9-cf0f-479a-827e-d68d0e0dbae6.jpg', '　　阿尔·帕西诺（1940年4月25日－）出生于美国纽约市，美国著名电影演员，1992年奥斯卡最佳男主角奖得主。\r\n　　早年，帕西诺就因出演《教父》（The Godfather）及《教父2》而家喻户晓。\r\n　　1993年，因在电影《闻香识女人》（Scent of a Woman）中的精湛演技，获得当年奥斯卡最佳男演员奖。剧中，他扮演性格暴躁，偏激的退休军官。在一次手榴弹事故中双目失明。但是，他可以靠灵敏的鼻子辨别周围女人身上的香味。他在受雇照顾他的高中生查理的陪同下，准备去纽约享受最后一次奢华的旅程。\r\n　　2003年他出演了HBO改编的电视连续短剧《天使在美国》，扮演美国历史上臭名昭著的麦卡锡主义者罗依·康（Roy Cohn），出色的演技为他带来了第61届金球奖和第56届艾美奖电视连续短剧最佳男主角的殊荣。2007年，艾尔更夺得的美国电影学会颁发的终身成就奖。\r\n　　帕西诺生于纽约市曼哈顿一个意大利裔家庭，两岁时父母离异，母亲带他到布朗克斯区的祖父母家居住。他父亲移居到加州做保险及餐厅的生意。童年时期，重复模仿同样的电影情节及演员的对白成了他的嗜好之一。帕西诺对学校不感兴趣，但在学校接触戏剧之后，他的兴趣与天份得到充分的发挥。开始了舞台生涯之后，有很长的一段时间，帕西诺在失意与穷困中度过，甚至必须向人借钱坐巴士去试镜。\r\n　　1966年帕西诺进入位于纽约市颇负盛名的“演员工作室”（The Actors Studio），在传奇的表演艺术教师李·史特拉斯伯格（Lee Strasberg）席下学习。终于在The Indian Wants the Bronx剧中成功演出并获得跨年度（1966-67）的Obie奖。建立起一些名声之后，帕西诺又以Does the Tiger Wear the Necktie? 一剧赢得东尼奖。1969年，首次参与电影Me, Natalie的演出。1971年，在The Panic in Needle Park中饰演毒瘾者受到注目。1972年，参与《教父》（The Godfather）第一集的演出更是改变他的一生。', '2019年 第23届好莱坞电影奖 年度男配角 爱尔兰人\r\n2016年 第73届金球奖 电影类 音乐/喜剧片最佳男主角(提名) 丹尼·科林斯\r\n2014年 第71届金球奖 电视类 迷你剧/电视电影最佳男主角(提名) 菲尔·斯派特				', NULL);
INSERT INTO `wm_actor` VALUES (77, '泰伊·谢里丹 Tye Sheridan', 'http://localhost:8088/images/actor/07fb34c1-e7ab-48d7-a8ff-20d2263b903c.jpg', '泰伊·凯尔·谢里丹于1996年11月11日出生于得克萨斯州的埃尔克哈特，她的父亲是一家美容院的老板斯蒂芬妮（赖特）和为UPS工作的布莱恩·谢里丹。他有一个妹妹，麦迪逊。他的家人在埃尔克哈特住了好几代。\r\n\r\n他从幼儿园起就在埃尔克哈特独立学校就读，在那里他在学术和体育方面都很出色。他小时候参加过少年足球和棒球联盟；八年级时，他是初中足球队的四分卫，曾被授予田径运动员的称号\r\n\r\n在他的第一部电影上映之前，他只在学校里演过几部戏，后来被从10000个男孩中挑选出来，在特伦斯·马利克的《生命之树》中扮演杰西卡·查斯坦和布拉德·皮特的小儿子。在拍摄这部电影时，他带着母亲和妹妹搬到了史密斯维尔，住了四个月\r\n\r\n2012年，他加入了《泥巴》剧组，扮演埃利斯的角色。该片由2012戛纳电影节候选导演杰夫·尼科尔斯执导。这部电影由马修·麦康纳和里斯·威瑟斯彭主演。\r\n\r\n谢里丹还在尼古拉斯·凯奇主演的电影《乔》中饰演加里·琼斯。凭借其出色的表演，他在第70届威尼斯国际电影节上获得了马塞洛·马斯特罗瓦尼最佳男演员奖。\r\n\r\n2013年，他加入了由凯尔·威尔莫夫斯基（Kyle Wilamowski）和凯特琳·德维尔（Kaitlyn Dever）共同执导的新片《青草斑斑》（Grass Stants）的演员阵容。他还将与查理兹·塞隆、克洛莫雷茨和尼古拉斯·霍尔特一起在《黑暗之地》中扮演本，并将从约翰·特拉沃尔塔主演的理查德·德奥维迪奥的剧本中剪掉《伪造者》。\r\n\r\n业余时间喜欢打棒球、打篮球、打猎和钓鱼。也喜欢骑四轮车，和朋友和家人在一起，玩电子游戏。', '2018年 第21届上海国际电影节 金爵奖 最佳男演员 星期五的孩子\r\n2014年 第19届美国评论家选择电影奖 最佳青少年演员(提名) 污泥\r\n2013年 第70届威尼斯电影节 马塞洛·马斯托依安尼奖（新锐演员奖） 乔				', NULL);
INSERT INTO `wm_actor` VALUES (78, '奥利维亚·库克 Olivia Cooke', 'http://localhost:8088/images/actor/e37019e2-4643-45df-be16-b608db58407b.jpg', '　　奥利维亚·库克（Olivia Cooke），出生于英国曼彻斯特，英国新生代90后女演员，2011年，以BBC《黑暗救赎》里的Meg一角出道，在《黑暗救赎》的男主克里斯托弗·埃克莱斯顿的推荐下，参演英国电视剧《鬼宅秘闻》，饰演Nancy，18岁的时候，奥利维亚只身前往温哥华，参与参演美国电视剧《贝兹旅馆》的试镜，顺利通过，在Bradley和Emma中，选择饰演Emma Decody。后被《ME AND EARL AND THE DYING GIRL》制片方钦定为女主Rachael~', '2018年 第27届MTV电影奖 MTV电影奖 最佳吻戏(提名) 头号玩家\r\n2018年 第27届MTV电影奖 MTV电影奖 最佳银幕组合(提名) 头号玩家				', NULL);
INSERT INTO `wm_actor` VALUES (79, '温子仁 James Wan', 'http://localhost:8088/images/actor/05af2d29-9b46-4c27-8cf5-3388bb05c617.jpg', '　　出生于马来西亚、成长于澳洲的华裔导演。早年在墨尔本上大学时主修的专业是中国民族划分。对于东方异域文化的了解对詹姆斯·温的导演风格也有着很大的影响，而造就他对于《电锯惊魂》和《死寂》(dead silence)之类血浆片独特口味的更重要的因素是他14岁时父亲过世的打击。', '2015年 第19届好莱坞电影奖 最佳卖座片 速度与激情7\r\n2015年 第2届豆瓣电影年度榜单 最受关注的导演(提名)\r\n2013年 第17届好莱坞电影奖 年度电影(提名) 招魂				', NULL);
INSERT INTO `wm_actor` VALUES (80, '维拉·法米加 Vera Farmiga', 'http://localhost:8088/images/actor/69a6b2ed-1e80-406f-81cc-d1b38354c2c7.jpg', '　　维拉·法梅加全名Vera Ann Farmiga，1973年8月出生于美国新泽西的Passaic County，是Michael (Mykola) Farmiga和Lyubov (Lyuba) Farmiga夫妇七个孩子中的老二。这是一个严格信奉乌克兰天主教的家庭，法梅加一直到六岁才开始讲英语，先后入读乌克兰天主教小学和公共学校，坐在钢琴后面她是一个戴着近视眼睛的害羞小姑娘，跳舞时才会换上隐形眼睛，十多岁之际法梅加曾经接受过乌克兰民族传统舞蹈的训练。1991年，维拉·法梅加从Hunderton中心地区高中毕业，进入锡拉丘兹(雪城)大学表演艺术学院之前还曾有过成为一名验光师的想法。法梅加的演艺事业正式开始于1996年，出演戏剧《Taking Sides》而登上在百老汇的舞台，之后的作品包括《The Tempest》、《The Glass Menagerie》、《Hamlet》以及广受好评的《Second-Hand Smoke》等。同时在1997年，维拉·法梅加出任FOX剧集《Roar》的女主角，可惜收视成绩并不理想，与她演对手戏的则是当时尚未成名的希斯·莱杰。此后的法梅加接拍多部影片，出演了《传媒陷阱》、《The Opportunists》中克里斯托弗·沃肯的女儿以及《纽约的秋天》中理察·基尔的女儿等。凭借2004年影片《深入骨头》中生活困窘的吸毒母亲的角色，法梅加获得了圣丹斯电影节和洛城电影节评论家协会的最佳女演员奖，其他精彩表现还包括2004年《谍网迷魂》中参议院的女儿、2005年《Neverwas》中的精神病患者等等，戏路相当宽泛。2006年的三部作品《持续惊恐》、《解构生活》、《无间行者》中，法梅加分别作为保罗·沃克饰演的不法分子的妻子、一位富有幽默感的妓女以及一位医生出现，马丁·斯科塞斯的奥斯卡最佳影片《无间行者》也使得她在2006年度受到前所未有的关注，还成为某杂志评选出的希望成为下一个邦女郎的女演员之一。法梅加的前任丈夫Sebastian Roché和她是在97年合作《Roar》之际相识的，然而于2005年离异。目前她往返于新泽西和洛山矶两处的家里，闲暇时间的爱好包括阅读和钢琴演奏，再就是和她的宠物安哥拉山羊们呆在一起。', '2019年 第71届黄金时段艾美奖 迷你剧/电视电影 最佳女配角(提名) 有色眼镜\r\n2015年 第5届美国评论家选择电视奖 剧情类最佳女主角(提名) 贝茨旅馆 第三季\r\n2014年 第23届MTV电影奖 MTV电影奖 最佳惊恐戏表演(提名) 招魂				', NULL);
INSERT INTO `wm_actor` VALUES (81, '帕特里克·威尔森 Patrick Wilson', 'http://localhost:8088/images/actor/44ac6215-68cd-4217-bf5e-720d2149a5e4.jpg', '　　帕特里克的父亲约翰是名电视主持人，妈妈玛丽是个专业歌手同时还会教授学生如何发声，父母的遗传基因这让他在戏剧表演方面更多了一份天赋。成长在这样一个文艺家庭，也让帕特里克从小就对登上舞台充满梦想。\r\n　　可以说，帕特里克·威尔森是才华型的演员，他毕业于世界顶尖的内基·梅隆私立大学的戏剧系，并且取得了美术学士学位，值得一提的是，曾因《钢琴课》一片荣获奥斯卡最佳女主角殊荣的霍莉·亨特也毕业于此，良好的学业经历为帕特里克登上表演舞台打下了坚实的基础，科班毕业也让他在接下来的表演岁月里表现得游刃有余。\r\n　　大学毕业后，帕特里克·威尔森就开始在百老汇演出，与其他表演者命运不同的是，帕特里克超然的自信和俊美的外形获得大部分剧组的青睐，再加上良好的艺术修为，使得帕特里克一开始便得到男主角的头衔，而真正让他成功地走到戏剧巅峰的就是歌剧《俄克拉荷马》与根据同名电影改编的音乐剧《光猪六壮士》，这两出歌剧让帕特里克两度获得托尼奖最佳音乐剧男主角的提名。在《俄克拉荷马》中，帕特里克扮演的库利是个乐观、幽默、热情、以捉弄女主角为乐的年轻牛仔，虽然四肢发达且头脑却像孩子般顽皮。以帕特里克的柔弱书生气能够体现出那种让人信服的粗犷也着实不容易。他在演出中刚出场时哼唱的那句“Oh，What a Beautiful Mornin”完全得益于他那天生的好嗓音，听起来也格外让人赏心悦目；而他在歌剧版《光猪六壮士》中的那幕经典的性感舞蹈，也曾经令台下的女观众和男同志们尖叫到疯狂。\r\n　　正是在舞台上的顺风顺水让帕特里克受到了好莱坞的关注，冥冥中，他的表演舞台注定将变得更加宽广。', '2016年 第73届金球奖 电视类 迷你剧/电视电影最佳男主角(提名) 冰血暴 第二季\r\n2004年 第61届金球奖 电视类 最佳男配角(提名) 天使在美国				', NULL);
INSERT INTO `wm_actor` VALUES (82, '马修·麦康纳 Matthew McConaughey', 'http://localhost:8088/images/actor/3af22088-963b-4e8c-ab77-588bd3e06f0a.jpg', '　　马修·麦康纳，美国演员。早先他没有对表演产生兴趣，而是立志要做一名律师，在德州大学的学习期间，受到一本名为《世界上最伟大的销售员》的灵感启发，从而改变志向，开始学习电影。他的表演生涯开始于1991年在学生的电影作品中出演角色，在1992年执导了一部短片。在早期马修·麦康纳的表演生涯中，他只是饰演一些追逐女孩子的花花公子角色，在1994年的电影《德州电锯杀人狂 4》饰演一个阴郁疯狂的嗜血杀手，开始转变戏路。在2000年他参与了电影《猎杀U-571》的表演。马修·麦康纳同是还在喜剧片中出演角色，如《缘分没法挡》、《十日拍拖手册》，他觉得最有意思的银幕角色是在《火焰末日》扮演一名美国战士。近两年麦康纳又出演了《撒哈拉骑兵》、《利欲两心》、《赖家王老五》等商业片，知名度和影响力直线上升。', '2015年 第72届金球奖 电视类 迷你剧/电视电影最佳男主角(提名) 真探 第一季\r\n2014年 第86届奥斯卡金像奖 最佳男主角 达拉斯买家俱乐部\r\n2013年 第8届罗马电影节 最佳男演员 达拉斯买家俱乐部				', NULL);
INSERT INTO `wm_actor` VALUES (83, '安妮·海瑟薇 Anne Hathaway', 'http://localhost:8088/images/actor/158878e9-6299-4404-8954-b5d8fc4354c3.jpg', '　　安妮·海瑟薇，美国电影和舞台剧演员，父亲是位律师，母亲则是歌手兼演舞台剧演员。她在大学里主修英文，副修妇女研究，她说过，如果当不成演员，她会想当一位文学家或是心理学家。\r\n　　在《公主日记》的试镜时，安妮意外地从椅子上摔了下来，结果反而被导演一眼相中，得到了扮演主角米娅的机会，并一举成名。\r\n　　安妮拥有灿烂明媚的笑容和高雅端正的气质，还带有一点点的调皮可爱，被誉为“茱莉亚·罗伯茨和奥黛莉·赫本”的综合体。', '2018年 第5届豆瓣电影年度榜单 最受关注的外语女演员(提名)\r\n2014年 第1届豆瓣电影年度榜单 最受关注的女演员(提名)\r\n2013年 第85届奥斯卡金像奖 最佳女配角 悲惨世界				', NULL);
INSERT INTO `wm_actor` VALUES (84, '水桥研二 Kenji Mizuhashi', 'http://localhost:8088/images/actor/29826668-5d6e-4cdd-884b-fe2daa627bed.jpg', '...', '...				', NULL);
INSERT INTO `wm_actor` VALUES (85, '近藤好美 Yoshimi Kondôu', 'http://localhost:8088/images/actor/48b9c8a1-6dad-4c65-bc32-1b0dc1a156fa.jpg', '...', '...				', NULL);
INSERT INTO `wm_actor` VALUES (86, '花泽香菜 Kana Hanazawa', 'http://localhost:8088/images/actor/97109ff7-251c-4b0c-a2a6-c1463c90b4f5.jpg', '　　日本著名女性声优、歌手，童星出身，所属事务所为大泽事务所。 在动画《ZEGAPAIN》中首次出演女主角守凪了子，由于其独特的棒读大受好评而为人所认知。 其他代表角色有《化物语》千石抚子、《Angel Beats!》立华奏、《命运石之门》 椎名真由理、《出包王女》结城美柑、《无头骑士异闻录》园原杏里、《我的妹妹不可能那么可爱》五更琉璃、《妖狐×仆SS》髅髅宫歌留多、《绝园的暴风雨》不破爱花、《魔笛MAGI》练红玉、《来自新世界》秋月真理亚、《心理测量者》常守朱、《来自风平浪静的明天》向井户爱花、《伪恋》小野寺小咲、《监狱学园》绿川花等。', '...			', NULL);
INSERT INTO `wm_actor` VALUES (87, '日高法子 Noriko Hidaka', 'http://localhost:8088/images/actor/17777be0-495a-40d5-bfa4-149f3f70e30b.jpg', '　　日高のり子（旧姓伊东），日本著名女性声优及演员，目前也是广播主持人。1962年5月31日出生于东京都千代田区九段，早年以童星身份进入演艺圈为东京宝映所属，后来转向声优方面发展，成名作为《棒球英豪》的浅仓南一角。曾经所属81 Produce经纪公司（1996年春天受邀加入，2006年3月辞去了在81的工作），1992年5月结婚，平成21年（2009年）1月19日与三矢雄二一起设立了「コンビネーション」事务所，目前隶属于Combination。', '...				', NULL);
INSERT INTO `wm_actor` VALUES (88, '坂本千夏 Chika Sakamoto', 'http://localhost:8088/images/actor/bd489a03-367f-4ff6-b3ec-575d6247b415.jpg', '...', '...				', NULL);
INSERT INTO `wm_actor` VALUES (98, '阿德瓦·香登 Advait Chandan', 'http://localhost:8088/images/actor/6926e17f-354a-4f35-8b6b-0659073e0b8d.jpg', '　导演，阿米尔汗经纪人，首次导演原创剧本《神秘巨星》大获成功。', '...', NULL);
INSERT INTO `wm_actor` VALUES (99, '塞伊拉·沃西 Zaira Wasim', 'http://localhost:8088/images/actor/c3b5ff71-5bd7-4199-bc00-8cbbb68877a2.jpg', '　神秘巨星 小女神 女一号', '....', NULL);
INSERT INTO `wm_actor` VALUES (100, '阿米尔·汗 Aamir Khan', 'http://localhost:8088/images/actor/525f9c15-ef3c-4ff0-bdc6-ab6732342b70.jpg', '　　阿米尔·汗，印度著名男演员。8岁时出演一部轰动印度全国的电影，是公认的很有前途的童星，但长大后他却坚决不愿从影，而一心去打网球，而且打得还不错，曾经获得过马哈拉施特拉邦的网球冠军。随着年纪的增长，才抛弃网球重回大银幕。阿米尔的罗曼史就是一部电影的好题材。\n　　他21岁的时候爱上了邻居家的女孩，但由于宗教原因（女孩家是印度教徒，他是一个正统的穆斯林），两方家长都坚决反对这桩婚事，两个年轻人决定私奔。现在他们过着幸福美满的生活，甚至常常被印度政府和印度政党拿来当作印度教和穆斯林和睦相处的典范。\n　　2006年元旦，象征新年开始的一个节日，印度“阿汤哥”阿米尔这一天在印度西部山区度假村举行婚礼，迎娶第二任妻子基琳·拉奥。41岁的阿米尔汗将与拉奥完婚。\n　　2001年阿米尔·汗出演的《印度往事》曾获得了奥斯卡最佳外语片提名。2005年，制作了电影《Ketan Mehta\'s The Rising》(2005)。2007年，阿米尔·汗开始尝试导演一职，并因导演影片《Taare Zameen par》获得Filmfare电影节最佳导演奖 。', '2018年 第5届豆瓣电影年度榜单 最受关注的外语男演员(提名)\n2017年 第4届豆瓣电影年度榜单 最受关注的外语男演员', NULL);
INSERT INTO `wm_actor` VALUES (101, '延尚昊 Sang-ho Yeon', 'http://localhost:8088/images/actor/989d3dbb-8051-4d74-a01f-ffbb68aca82a.jpg', '　　延相昊(Sang-ho Yeon)，1978年出生于韩国首尔，韩国动画导演、编剧。\n　　2000年延相昊以执导短片《D-Day》出道。自2002年在祥明大学西洋画科系毕业后，加入韩国Hahn Shin漫画公司，任职制作企划 。2004年，延相昊创立StudioDaDaShow，从事漫画及动画电影的制作与行销。执导的《爱是蛋白质》在2008年第4届韩国独立动画电影节上被观众选为电影节之星。2011年，创作首部动画长片《猪猡之王》夺得第16届釜山国际电影节“电影导演工会最佳导演奖”、“Movie Collage奖”和“NETPAC奖”三个奖项，表扬其对韩国电影工业的贡献 。2013年，执导的《伪善者》获得第46届西班牙锡切斯奇幻电影节得到最佳动画片奖 。2016年4月，创作的动画电影《首尔站》于第34届布鲁塞尔国际奇幻电影节上获得白银鸦奖 。5月，执导的首部真人长篇电影《釜山行》于第69届戛纳国际电影节午夜展映单元亮相 。', '2017年 第53届百想艺术大赏 电影类 最佳新人导演 釜山行\n2016年 第37届韩国电影青龙奖 最佳影片(提名) 釜山行\n2012年 第65届戛纳电影节 金摄影机奖（导演处女作奖）(提名) 猪猡之王', NULL);
INSERT INTO `wm_actor` VALUES (102, '孔侑 Yoo Gong', 'http://localhost:8088/images/actor/b9ae7d2a-7157-40cd-b9d0-cce064574884.jpg', '　　 孔侑是韩国最受欢迎的男演员及模特之一。孔侑因在电视剧《饼干老师星星糖》中的演出而受到欢迎，2007年孔侑与尹恩惠合演的《咖啡王子一号店》更将他的事业推到最高峰，同时使他获得MBC优秀男演员奖。2007年12月孔侑宣布2008年1月14日入伍，入伍前他举办的影友会门票于三十三秒即抢购一空，创下最快销售记录。孔侑在圈内还颇有人缘，金善雅、赵仁成等都是他的亲密好友。', '2017年 第11届亚洲电影大奖 最佳男主角(提名) 釜山行\n2017年 第53届百想艺术大赏 电视类 最佳男主角奖 孤单又灿烂的神：鬼怪\n2016年 第3届豆瓣电影年度榜单 最受关注的男演员', NULL);
INSERT INTO `wm_actor` VALUES (103, '郑有美 Yu-mi Jung', 'http://localhost:8088/images/actor/317e1216-258d-400c-bd44-3a6e7717f869.jpg', '　　郑有美，韩国演员，毕业于首尔艺术大学电影系，在校期间出演过十部左右的短片电影；2004年，在金钟观导演执导的六分钟短篇电影《How To Operate A Polaroid Camera》中，郑有美饰演一位向单恋着的男孩请教如何使用宝丽莱相机的少女，该片曾多次在韩国国内外短篇电影节上获奖，成为韩国短篇电影的名作。曾关注短片电影节的知名导演金知云，在《How To Operate A Polaroid Camera》中发现了她，因此她客串了金知云导演的电影《甜蜜人生》，从而正式进入忠武路的视线。', '2018年 第55届韩国电影大钟奖 最佳女配角(提名) 念力\n2016年 第37届韩国电影青龙奖 最佳女配角(提名) 釜山行\n2016年 第3届豆瓣电影年度榜单 最受关注的女演员(提名)', NULL);
INSERT INTO `wm_actor` VALUES (104, '詹姆斯·麦克特格 James McTeigue', 'http://localhost:8088/images/actor/2a9a86b9-6f84-4acf-85fa-ce79f6d04d35.jpg', 'Grew up in Sydney, Australia.', '....', NULL);
INSERT INTO `wm_actor` VALUES (105, '雨果·维文 Hugo Weaving', 'http://localhost:8088/images/actor/23fcd8e3-f224-4b8d-8957-76b007bf295e.jpg', '　　雨果·维文是澳大利亚最著名的演员之一。他1981年毕业于国家艺术学院，之后在影视舞台上辛勤耕耘。他经典角色之一是在彼德·杰克森的史诗影片《魔戒》中出演林谷主人精灵王艾尔隆。\n　　雨果·维文1960年生于尼日利亚，家中三兄妹他居中，有一位哥哥西蒙和一位妹妹安妮。毕业于澳大利亚国立戏剧艺术学院，这位蓝眼睛、气质独特的演员首次担纲主演是1983年社会道德题材的低成本剧情片《城市边缘》，据称这是澳大利亚第一部从同情的角度刻划土著人所处恶劣环境的影片。', '2019年 第23届金卫星奖 电视部门 最佳男配角 梅尔罗斯\n2012年 第14届美国青少年选择奖 最佳电影打斗(提名) 美国队长', NULL);
INSERT INTO `wm_actor` VALUES (106, '奥里奥尔·保罗 Oriol Paulo', 'http://localhost:8088/images/actor/05dc1b00-e68d-4367-b46b-55075b240ee9.jpg', '　　奥里奥尔·保罗（Oriol Paulo），1975年出生于西班牙，西班牙导演。\n　　2010年，担任惊悚悬疑电影《茱莉娅的眼睛》的编剧，该片由古勒姆·莫拉雷斯执导，贝伦·鲁艾达、路易斯·霍马、帕布罗·德奎联合主演，讲述了罹患功能退化症的茱莉娅在视力逐渐减退的同时寻找妹妹莎拉自杀真相的故事 。\n　　2012年 ，执导由贝伦·鲁艾达、雨果·席尔瓦、奥拉·加里多联袂主演的惊悚悬疑电影《女尸谜案》，他凭借该片入围第27届西班牙戈雅奖-最佳新人导演。\n　　2017年9月15日，执导的悬疑电影《看不见的客人》在中国大陆上映，该片由马里奥·卡萨斯、阿娜·瓦格纳、芭芭拉·蓝妮联合主演，讲述了企业家艾德里安在事业如日中天之时被卷入一桩谋杀案中，为了洗脱罪名，他请来了金牌女律师弗吉尼亚为自己辩护 。', '2013年 第27届西班牙戈雅奖 最佳新人导演(提名) 女尸谜案', NULL);
INSERT INTO `wm_actor` VALUES (107, '马里奥·卡萨斯 Mario Casas', 'http://localhost:8088/images/actor/6d41cb1e-8acd-4b86-87f7-74a8425205ac.jpg', '　　马里奥·卡萨斯，西班牙演员。代表作有《看不见的客人》《地心营救》等。卡萨斯1986年6月12日生于西班牙拉科鲁尼亚，在家中排老大，有四个弟弟。1994年，8岁的卡萨斯随全家搬至巴塞罗那。儿时的卡萨斯梦想着当警察、消防员或是足球运动员。直到17岁入读Cristina Rota School of Dramatic Arts后，卡萨斯决定要做一名演员。在出演了几部电视剧以后，卡萨斯接拍了他的电影处女作《夏季雨》，本片是由安东尼奥·班德拉斯2006年指导的爱情片。而2007年至2010年期间他参演的西班牙电视剧《巴克的朋友们》则帮他打响了国际知名度，开始在年轻一代的西班牙演员中崭露头角。2009年，卡萨斯参演了两部在西班牙国内大热的影片《性、派对和谎言》《坏坏爱》。2010年，他搭档曾在《裂缝》中贡献精彩表演的María Valverde主演电影《天空上三公尺》（又译《恋爱大过天》），本片成为2010年度西班牙票房第一。由原班人马出演的续集《我需要你》2012年上映。', '....', NULL);
INSERT INTO `wm_actor` VALUES (108, '阿娜·瓦格纳 Ana Wagener', 'http://localhost:8088/images/actor/ab166428-53d0-45b3-9002-026cfe437995.jpg', '安娜·瓦格纳1962年出生于拉斯帕尔马斯德格兰加那利，格兰加那利，加那利群岛，西班牙的安娜·阿尔瓦雷斯·瓦格纳。她是一名演员，以《双唇》（2010）、《康塔蒂埃姆波》（2016）和《艾尔博拉》（2000）而闻名。', '2019年 第33届西班牙戈雅奖 最佳女配角(提名) 王国\n2012年 第26届西班牙戈雅奖 最佳女配角 沉睡的声音\n2011年 第25届西班牙戈雅奖 最佳女配角(提名) 美错', NULL);
INSERT INTO `wm_actor` VALUES (109, '饺子 Yu Yang', 'http://localhost:8088/images/actor/98bd45f7-402b-402d-b1e3-cc6018a4ae4f.jpg', '　　杨宇，四川泸州人，1980年出生，毕业于华西医科大学药学院.', '....', NULL);
INSERT INTO `wm_actor` VALUES (110, '吕艳婷 Yanting Lü', 'http://localhost:8088/images/actor/935139d5-a237-4a26-85db-a3a6227521cf.jpg', '　　四川电影电视学院播音主持系教师。她曾是央视动画《山海界》主要配音演员，已有十年执教经验。', '....', NULL);
INSERT INTO `wm_actor` VALUES (111, '囧森瑟夫 Joseph', 'http://localhost:8088/images/actor/342256a8-24ae-440f-aada-9166da9d81a0.jpg', '　声娱配音演员', '....', NULL);

-- ----------------------------
-- Table structure for wm_movie
-- ----------------------------
DROP TABLE IF EXISTS `wm_movie`;
CREATE TABLE `wm_movie`  (
  `movie_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `movie_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电影类型',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `publish_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布人',
  `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0发布 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `read_count` int(0) NULL DEFAULT 0 COMMENT '阅读数',
  `comment_count` int(0) NULL DEFAULT NULL COMMENT '评论数',
  `follow_count` int(0) NULL DEFAULT NULL COMMENT '关注数',
  `collection_count` int(0) NULL DEFAULT NULL COMMENT '收藏数',
  `likes_count` int(0) NULL DEFAULT NULL COMMENT '喜欢数',
  `unlikes_count` int(0) NULL DEFAULT NULL COMMENT '不喜欢数',
  `share_count` int(0) NULL DEFAULT NULL COMMENT '分享数',
  `is_comment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否可以评论',
  `is_download` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否可以下载',
  `price` decimal(19, 2) NULL DEFAULT NULL COMMENT '价格',
  `rate` double(10, 1) NULL DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`movie_id`) USING BTREE,
  UNIQUE INDEX `wm_movie_pk_index`(`movie_id`) USING BTREE COMMENT '主键唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电影表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wm_movie
-- ----------------------------
INSERT INTO `wm_movie` VALUES (94, '/profile/movie/images/2021/05/14/d4a21813-b6ab-43c7-bdf5-0fd644f26391.png', 'vue入门', 'teleplay', 'india', 'outdoor', 'vue入门', '11', '2021-05-14 00:00:00', '0', NULL, '2021-05-01 17:48:59', NULL, '2021-05-14 22:20:51', '', '', 1, 1, 1, 1, 1, 1, 1, 'Y', 'N', 1.00, 4.9);
INSERT INTO `wm_movie` VALUES (95, '/profile/movie/images/2021/05/12/001740-15050602605349.jpg', '好看电影', ' film', 'japan', 'outdoor,KungFu', '12121', NULL, '2021-04-27 00:00:00', '0', NULL, '2021-05-03 13:12:49', NULL, '2021-05-14 22:50:19', NULL, '212121', 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'N', 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (96, '/profile/movie/images/2021/05/12/001756-15040234769744.jpg', '测试电影', 'teleplay', 'china', 'outdoor,KungFu', '1221', NULL, '2021-05-04 00:00:00', '0', NULL, '2021-05-03 15:28:50', NULL, '2021-05-14 22:54:06', NULL, '1221', 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'Y', 1.00, 4.9);
INSERT INTO `wm_movie` VALUES (97, '/profile/movie/images/2021/05/12/001857-1505578737d340.jpg', '名侦探柯南', 'teleplay', 'china', 'outdoor', '侦探柯南是一部动漫类型的，于1996在日本上映，目前的语言版本有。主角工藤新一原本是一位颇具名声的高中生侦探，在目击黑衣组织的地下交易后，准备追踪时却被黑衣人琴酒袭昏，名侦探柯南播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-12 00:00:00', '0', NULL, '2021-05-04 21:46:50', NULL, '2021-05-13 23:59:11', NULL, '21212121', 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'Y', 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (98, '/profile/movie/images/2021/05/12/001902-1504023542a407.jpg', '只要你爱我', ' film', 'japan', NULL, '只要你说你爱我真人版是一部爱情片类型的电影，于2014在日本上映，目前的语言版本有日语。东明高中的黑泽大和（福士苍汰 饰）是所有女孩崇拜追逐的对象，而他在一次意外遭遇后，偏偏对几乎没有人注，只要你说你爱我真人版播放地址获取自网路，如有侵权请联系', NULL, '2021-05-10 00:00:00', '0', NULL, '2021-05-09 10:11:37', NULL, '2021-05-09 10:23:04', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'Y', 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (99, '/profile/movie/images/2021/05/12/001945-1504541985012d.jpg', '妹妹的诱惑', ' film', 'japan', NULL, '斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-10 00:00:00', '0', NULL, '2021-05-09 10:12:02', NULL, '2021-05-09 10:25:14', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'Y', 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (100, '/profile/movie/images/2021/05/12/002712-15014320321832.jpg', '斗罗大陆', ' film', 'japan', NULL, '斗罗大陆是一部动漫类型的，于2018在内地上映，目前的语言版本有。唐门外门弟子唐三，因偷学内门绝学为唐门所不容，跳崖明志时却发现没有死，反而以另外一个身份来到了另一个，斗罗大陆播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-10 00:00:00', '0', NULL, '2021-05-09 10:12:05', NULL, '2021-05-09 10:15:01', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'Y', 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (101, '/profile/movie/images/2021/05/12/002724-1502987244428b.jpg', '什么叫做爱', ' film', 'china', NULL, '什么叫做爱是一部喜剧片类型的电影，于2013在中国大陆上映，目前的语言版本有汉语普通话。《什么叫做爱》2013年爆笑情感微电影，通过一部神器的电话，引发的爱情，喜剧，励志，情感，动作，惊悚，什么叫做爱播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-21 00:00:00', '0', NULL, '2021-05-09 10:17:40', NULL, NULL, NULL, '什么叫做爱是一部喜剧片类型的电影，于2013在中国大陆上映，目前的语言版本有汉语普通话。《什么叫做爱》2013年爆笑情感微电影，通过一部神器的电话，引发的爱情，喜剧，励志，情感，动作，惊悚，什么叫做爱播放地址获取自网路，如有侵权请联系...', 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'Y', 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (102, '/profile/movie/images/2021/05/12/002858-15014321386902.jpg', '八月未央', ' film', 'china', 'outdoor,KungFu', '稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！  本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。 八月未央由稻田影视免费提供在线播放服务', NULL, '2021-05-21 00:00:00', '0', NULL, '2021-05-09 10:17:41', NULL, '2021-05-13 23:58:44', NULL, '稻田影视每日更新大量最新电影电视剧，为喜爱影视的朋友提供在线观影的平台 剧情片八月未央是李凯的一部作品，由钟楚曦,谭松韵,罗晋参演，当前被点击了134次，平均评分6.0。大家有什么好的影视作品欢迎留言投稿！\n\n本片讲述了可爱率真的林小乔（谭松韵 饰）遇见了沉默寡言的未央（钟楚曦 饰），小乔的热情开朗使性格迥异的她们迅速成为了闺蜜，而小乔未婚夫朝颜（罗晋 饰）的出现使得三人的感情产生了微妙的变化，他们的关系也由此变得错综复杂起怀疑并不是缺点。总是疑，而并不下断语，这才是缺点。。\n八月未央由稻田影视免费提供在线播放服务', 0, NULL, NULL, NULL, NULL, NULL, NULL, 'Y', 'Y', 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (103, '/profile/movie/images/2021/05/12/003008-1504456208b441.jpg', '正义荣耀', ' film', 'china', 'outdoor,KungFu', 'MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-27 00:00:00', '0', NULL, '2021-05-09 10:34:13', NULL, '2021-05-14 22:51:17', NULL, 'MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (104, '/profile/movie/images/2021/05/12/003020-15026418205ea4.jpg', '正义荣耀', ' film', 'china', NULL, 'MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-27 00:00:00', '0', NULL, '2021-05-09 10:34:32', NULL, NULL, NULL, 'MC战纪是一部动漫类型的，于2020在大陆上映，目前的语言版本有国语。讲你太善良了，这个世界会把你啃得尸骨无存。----Sheldon述《我的世界》主角史蒂夫成长并成为传，MC战纪播放地址获取自网路，如有侵权请联系...', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (105, '/profile/movie/images/2021/05/12/003101-15029874610de5.jpg', '阿衰', ' film', 'korea', 'outdoor,KungFu', '阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-05 00:00:00', '0', NULL, '2021-05-09 10:35:57', NULL, '2021-05-14 00:09:55', NULL, '阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (106, '/profile/movie/images/2021/05/12/102751-1526610471f10a.jpg', '阿衰', ' film', 'korea', NULL, '阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-05 00:00:00', '0', NULL, '2021-05-09 10:36:19', NULL, NULL, NULL, '阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 4.9);
INSERT INTO `wm_movie` VALUES (107, '/profile/movie/images/2021/05/12/102950-1505788190ad5e.jpg', '阿衰', ' film', 'korea', NULL, '阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...', NULL, '2021-05-05 00:00:00', '0', NULL, '2021-05-09 10:36:21', NULL, NULL, NULL, '阿衰是一部动漫类型的，于未知在国漫上映，目前的语言版本有普通话。神级漫画家的史诗剧作，连载15年销量过亿的人气喜剧动画一位爱吃臭豆腐的普通中学生带你开启爆笑校园故事，阿衰播放地址获取自网路，如有侵权请联系...', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 4.9);

-- ----------------------------
-- Table structure for wm_movie_actor
-- ----------------------------
DROP TABLE IF EXISTS `wm_movie_actor`;
CREATE TABLE `wm_movie_actor`  (
  `actor_id` int(0) NOT NULL,
  `movie_id` bigint(0) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主演actor  演员',
  INDEX `wm_movie_actor_index_actor_id`(`actor_id`) USING BTREE,
  INDEX `wm_movie_actor_index_movie_id`(`movie_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wm_movie_actor
-- ----------------------------
INSERT INTO `wm_movie_actor` VALUES (1, 44, NULL);
INSERT INTO `wm_movie_actor` VALUES (3, 44, NULL);
INSERT INTO `wm_movie_actor` VALUES (4, 44, NULL);
INSERT INTO `wm_movie_actor` VALUES (5, 39, NULL);
INSERT INTO `wm_movie_actor` VALUES (7, 39, NULL);
INSERT INTO `wm_movie_actor` VALUES (5, 16, NULL);
INSERT INTO `wm_movie_actor` VALUES (9, 16, NULL);
INSERT INTO `wm_movie_actor` VALUES (10, 16, NULL);
INSERT INTO `wm_movie_actor` VALUES (14, 4, NULL);
INSERT INTO `wm_movie_actor` VALUES (15, 4, NULL);
INSERT INTO `wm_movie_actor` VALUES (16, 4, NULL);
INSERT INTO `wm_movie_actor` VALUES (17, 3, NULL);
INSERT INTO `wm_movie_actor` VALUES (18, 3, NULL);
INSERT INTO `wm_movie_actor` VALUES (19, 3, NULL);
INSERT INTO `wm_movie_actor` VALUES (20, 5, NULL);
INSERT INTO `wm_movie_actor` VALUES (21, 5, NULL);
INSERT INTO `wm_movie_actor` VALUES (22, 5, NULL);
INSERT INTO `wm_movie_actor` VALUES (23, 17, NULL);
INSERT INTO `wm_movie_actor` VALUES (24, 17, NULL);
INSERT INTO `wm_movie_actor` VALUES (25, 17, NULL);
INSERT INTO `wm_movie_actor` VALUES (26, 20, NULL);
INSERT INTO `wm_movie_actor` VALUES (27, 20, NULL);
INSERT INTO `wm_movie_actor` VALUES (28, 20, NULL);
INSERT INTO `wm_movie_actor` VALUES (29, 21, NULL);
INSERT INTO `wm_movie_actor` VALUES (30, 21, NULL);
INSERT INTO `wm_movie_actor` VALUES (31, 21, NULL);
INSERT INTO `wm_movie_actor` VALUES (32, 23, NULL);
INSERT INTO `wm_movie_actor` VALUES (33, 23, NULL);
INSERT INTO `wm_movie_actor` VALUES (34, 23, NULL);
INSERT INTO `wm_movie_actor` VALUES (35, 26, NULL);
INSERT INTO `wm_movie_actor` VALUES (36, 26, NULL);
INSERT INTO `wm_movie_actor` VALUES (37, 26, NULL);
INSERT INTO `wm_movie_actor` VALUES (17, 27, NULL);
INSERT INTO `wm_movie_actor` VALUES (38, 27, NULL);
INSERT INTO `wm_movie_actor` VALUES (39, 27, NULL);
INSERT INTO `wm_movie_actor` VALUES (40, 30, NULL);
INSERT INTO `wm_movie_actor` VALUES (41, 30, NULL);
INSERT INTO `wm_movie_actor` VALUES (42, 30, NULL);
INSERT INTO `wm_movie_actor` VALUES (43, 31, NULL);
INSERT INTO `wm_movie_actor` VALUES (44, 31, NULL);
INSERT INTO `wm_movie_actor` VALUES (45, 31, NULL);
INSERT INTO `wm_movie_actor` VALUES (46, 34, NULL);
INSERT INTO `wm_movie_actor` VALUES (47, 34, NULL);
INSERT INTO `wm_movie_actor` VALUES (48, 34, NULL);
INSERT INTO `wm_movie_actor` VALUES (49, 35, NULL);
INSERT INTO `wm_movie_actor` VALUES (50, 35, NULL);
INSERT INTO `wm_movie_actor` VALUES (51, 35, NULL);
INSERT INTO `wm_movie_actor` VALUES (52, 36, NULL);
INSERT INTO `wm_movie_actor` VALUES (53, 36, NULL);
INSERT INTO `wm_movie_actor` VALUES (54, 36, NULL);
INSERT INTO `wm_movie_actor` VALUES (55, 37, NULL);
INSERT INTO `wm_movie_actor` VALUES (56, 37, NULL);
INSERT INTO `wm_movie_actor` VALUES (57, 37, NULL);
INSERT INTO `wm_movie_actor` VALUES (58, 38, NULL);
INSERT INTO `wm_movie_actor` VALUES (59, 38, NULL);
INSERT INTO `wm_movie_actor` VALUES (60, 38, NULL);
INSERT INTO `wm_movie_actor` VALUES (61, 40, NULL);
INSERT INTO `wm_movie_actor` VALUES (62, 40, NULL);
INSERT INTO `wm_movie_actor` VALUES (63, 40, NULL);
INSERT INTO `wm_movie_actor` VALUES (64, 41, NULL);
INSERT INTO `wm_movie_actor` VALUES (21, 41, NULL);
INSERT INTO `wm_movie_actor` VALUES (65, 41, NULL);
INSERT INTO `wm_movie_actor` VALUES (66, 43, NULL);
INSERT INTO `wm_movie_actor` VALUES (18, 43, NULL);
INSERT INTO `wm_movie_actor` VALUES (67, 43, NULL);
INSERT INTO `wm_movie_actor` VALUES (68, 45, NULL);
INSERT INTO `wm_movie_actor` VALUES (69, 45, NULL);
INSERT INTO `wm_movie_actor` VALUES (70, 45, NULL);
INSERT INTO `wm_movie_actor` VALUES (71, 46, NULL);
INSERT INTO `wm_movie_actor` VALUES (72, 46, NULL);
INSERT INTO `wm_movie_actor` VALUES (73, 46, NULL);
INSERT INTO `wm_movie_actor` VALUES (74, 47, NULL);
INSERT INTO `wm_movie_actor` VALUES (75, 47, NULL);
INSERT INTO `wm_movie_actor` VALUES (76, 47, NULL);
INSERT INTO `wm_movie_actor` VALUES (71, 48, NULL);
INSERT INTO `wm_movie_actor` VALUES (77, 48, NULL);
INSERT INTO `wm_movie_actor` VALUES (78, 48, NULL);
INSERT INTO `wm_movie_actor` VALUES (79, 49, NULL);
INSERT INTO `wm_movie_actor` VALUES (80, 49, NULL);
INSERT INTO `wm_movie_actor` VALUES (81, 49, NULL);
INSERT INTO `wm_movie_actor` VALUES (64, 51, NULL);
INSERT INTO `wm_movie_actor` VALUES (82, 51, NULL);
INSERT INTO `wm_movie_actor` VALUES (83, 51, NULL);
INSERT INTO `wm_movie_actor` VALUES (5, 53, NULL);
INSERT INTO `wm_movie_actor` VALUES (84, 52, NULL);
INSERT INTO `wm_movie_actor` VALUES (85, 52, NULL);
INSERT INTO `wm_movie_actor` VALUES (5, 52, NULL);
INSERT INTO `wm_movie_actor` VALUES (25, 53, NULL);
INSERT INTO `wm_movie_actor` VALUES (86, 53, NULL);
INSERT INTO `wm_movie_actor` VALUES (98, 90, NULL);
INSERT INTO `wm_movie_actor` VALUES (99, 90, NULL);
INSERT INTO `wm_movie_actor` VALUES (100, 90, NULL);
INSERT INTO `wm_movie_actor` VALUES (101, 91, NULL);
INSERT INTO `wm_movie_actor` VALUES (102, 91, NULL);
INSERT INTO `wm_movie_actor` VALUES (103, 91, NULL);
INSERT INTO `wm_movie_actor` VALUES (16, 92, NULL);
INSERT INTO `wm_movie_actor` VALUES (104, 92, NULL);
INSERT INTO `wm_movie_actor` VALUES (105, 92, NULL);
INSERT INTO `wm_movie_actor` VALUES (106, 93, NULL);
INSERT INTO `wm_movie_actor` VALUES (107, 93, NULL);
INSERT INTO `wm_movie_actor` VALUES (108, 93, NULL);
INSERT INTO `wm_movie_actor` VALUES (109, 64, NULL);
INSERT INTO `wm_movie_actor` VALUES (110, 64, NULL);
INSERT INTO `wm_movie_actor` VALUES (111, 64, NULL);
INSERT INTO `wm_movie_actor` VALUES (1, 2, NULL);

-- ----------------------------
-- Table structure for wm_movie_video
-- ----------------------------
DROP TABLE IF EXISTS `wm_movie_video`;
CREATE TABLE `wm_movie_video`  (
  `movie_video_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `ext` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `length` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '播放长度',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `storage_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `movie_id` bigint(0) NULL DEFAULT NULL COMMENT '电影id',
  `filesize` bigint(0) NULL DEFAULT NULL COMMENT '文件大小字节数',
  PRIMARY KEY (`movie_video_id`) USING BTREE,
  UNIQUE INDEX `wm_movie_video_pk_index`(`movie_video_id`) USING BTREE COMMENT '主键唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电影视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wm_movie_video
-- ----------------------------
INSERT INTO `wm_movie_video` VALUES (34, '第一集', '/profile/movie/video/2021/05/09/460bb44e-fc8e-41dd-9bb1-a650e34ac1fb.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 95, 66473699);
INSERT INTO `wm_movie_video` VALUES (35, '第一集', '/profile/movie/video/2021/05/09/69934ca2-f288-4bb7-957a-b8cb8e3222f9.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 95, 148267788);
INSERT INTO `wm_movie_video` VALUES (38, '第一集', '/profile/movie/video/2021/05/09/5e27ff9c-d17d-4b1e-9c68-3514d45639d0.mp4', 'mp4', '10分钟', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 96, 30032601);
INSERT INTO `wm_movie_video` VALUES (47, '第一集', '/profile/movie/video/2021/05/09/bdbf9304-2457-420d-baa8-4d2459e1689c.mp4', 'mp4', '55分钟', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 96, 36350392);
INSERT INTO `wm_movie_video` VALUES (50, '第一集', '/profile/movie/video/2021/05/09/d98b18d1-8d22-4410-ac50-7e2bc01ea989.mp4', 'mp4', '10分钟', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 97, 94474050);
INSERT INTO `wm_movie_video` VALUES (51, '第一集', '/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 99, 19543974);
INSERT INTO `wm_movie_video` VALUES (52, '第一集', '/profile/movie/video/2021/05/09/c03fd556-52ba-4d43-8ac3-85b4f7783579.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 19543974);
INSERT INTO `wm_movie_video` VALUES (54, '第一集', '/profile/movie/video/2021/05/09/3d80d81a-b958-42fb-91ca-42e027d11d20.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 98, 28422255);
INSERT INTO `wm_movie_video` VALUES (55, '第一集', '/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 101, 17081030);
INSERT INTO `wm_movie_video` VALUES (56, '第一集', '/profile/movie/video/2021/05/09/db7c9aeb-bec7-4f4c-93cf-17708b80b105.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 102, 17081030);
INSERT INTO `wm_movie_video` VALUES (57, '第一集', '/profile/movie/video/2021/05/09/7c227467-d082-448d-9d27-3a90768153a4.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 104, 22384625);
INSERT INTO `wm_movie_video` VALUES (58, '第一集', '/profile/movie/video/2021/05/09/1c419244-aec7-40b2-a34a-3637be923bbf.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 106, 33415892);
INSERT INTO `wm_movie_video` VALUES (59, '第一集', '/profile/movie/video/2021/05/09/1c419244-aec7-40b2-a34a-3637be923bbf.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 107, 33415892);
INSERT INTO `wm_movie_video` VALUES (60, '1-1', '/profile/movie/video/2021/05/14/aff76f23-b982-4426-bbfc-35f20e173759.mp4', 'mp4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 94, 64867562);

SET FOREIGN_KEY_CHECKS = 1;