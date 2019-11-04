/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : topwulian_com

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 10/14/2018 18:02:29 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `QRTZ_BLOB_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_CALENDARS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR_NAME` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_CRON_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `CRON_EXPRESSION` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_FIRED_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `ENTRY_ID` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `JOB_GROUP` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_JOB_DETAILS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_LOCKS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `LOCK_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `QRTZ_LOCKS`
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_LOCKS` VALUES ('adminQuartzScheduler', 'STATE_ACCESS'), ('adminQuartzScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
--  Table structure for `QRTZ_PAUSED_TRIGGER_GRPS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_SCHEDULER_STATE`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `QRTZ_SCHEDULER_STATE`
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('adminQuartzScheduler', '192.168.0.1031539511293506', '1539511337490', '15000');
COMMIT;

-- ----------------------------
--  Table structure for `QRTZ_SIMPLE_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_SIMPROP_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `QRTZ_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `file_info`
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` varchar(32) NOT NULL COMMENT '文件md5',
  `contentType` varchar(128) NOT NULL,
  `size` int(11) NOT NULL,
  `path` varchar(255) NOT NULL COMMENT '物理路径',
  `url` varchar(1024) NOT NULL,
  `type` int(1) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sys_logs`
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `module` varchar(50) DEFAULT NULL COMMENT '模块名',
  `flag` tinyint(4) DEFAULT '1' COMMENT '成功失败',
  `remark` text COMMENT '备注',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sys_logs`
-- ----------------------------
BEGIN;
INSERT INTO `sys_logs` VALUES ('1', '1', 'web端登陆', '1', null, '2018-10-09 12:03:35'), ('2', '1', 'web端登陆', '1', null, '2018-10-09 12:21:28'), ('3', '1', 'web端登陆', '1', null, '2018-10-09 12:32:44'), ('4', '1', '退出', '1', null, '2018-10-09 12:34:00'), ('5', '1', 'web端登陆', '1', null, '2018-10-09 12:34:07'), ('6', '1', '退出', '1', null, '2018-10-09 12:35:08'), ('7', '1', 'web端登陆', '1', null, '2018-10-09 12:35:19'), ('8', '1', 'web端登陆', '1', null, '2018-10-09 12:58:11'), ('9', '1', 'web端登陆', '1', null, '2018-10-09 16:43:47'), ('10', '1', 'web端登陆', '1', null, '2018-10-09 18:02:56'), ('11', '1', '修改用户', '1', null, '2018-10-09 20:36:11'), ('12', '1', 'web端登陆', '1', null, '2018-10-09 22:18:25'), ('13', '1', '退出', '1', null, '2018-10-09 22:28:12'), ('14', '1', 'web端登陆', '1', null, '2018-10-09 22:28:14'), ('15', '1', '生成代码', '1', null, '2018-10-09 22:30:10'), ('16', '1', '保存菜单', '1', null, '2018-10-09 22:38:45'), ('17', '1', '修改菜单', '1', null, '2018-10-09 22:39:28'), ('18', '1', '退出', '1', null, '2018-10-09 22:39:41'), ('19', '1', 'web端登陆', '1', null, '2018-10-09 22:39:42'), ('20', '1', '保存角色', '1', null, '2018-10-09 22:39:56'), ('21', '1', '退出', '1', null, '2018-10-09 22:40:08'), ('22', '1', 'web端登陆', '1', null, '2018-10-09 22:40:09'), ('23', '1', '修改菜单', '1', null, '2018-10-09 22:46:54'), ('24', '1', '保存角色', '1', null, '2018-10-09 22:47:10'), ('25', '1', '退出', '1', null, '2018-10-09 22:47:12'), ('26', '1', 'web端登陆', '1', null, '2018-10-09 22:47:13'), ('27', '1', 'web端登陆', '1', null, '2018-10-10 23:02:14'), ('28', '1', '生成代码', '1', null, '2018-10-10 23:20:55'), ('29', '1', '修改菜单', '1', null, '2018-10-10 23:28:37'), ('30', '1', '修改菜单', '1', null, '2018-10-10 23:29:25'), ('31', '1', '保存角色', '1', null, '2018-10-10 23:29:36'), ('32', '1', null, '1', 'Fc5wGsTuvuiuFaFa8tsJUoMZcvHo2ujZsz', '2018-10-11 10:57:36'), ('33', '1', 'web端登陆', '1', null, '2018-10-11 11:08:21'), ('34', '1', '推送了一条消息:内容为:msg content', '1', 'Fc5wGsTuvuiuFaFa8tsJUoMZcvHo2ujZsz', '2018-10-11 11:19:05'), ('35', '1', 'web端登陆', '1', null, '2018-10-11 12:10:12'), ('36', '1', '生成代码', '1', null, '2018-10-11 12:11:02'), ('37', '1', 'web端登陆', '1', null, '2018-10-11 13:38:15'), ('38', '1', 'web端登陆', '1', null, '2018-10-11 14:10:12'), ('39', '1', 'web端登陆', '1', null, '2018-10-11 14:15:33'), ('40', '1', 'web端登陆', '1', null, '2018-10-11 15:26:09'), ('41', '1', 'web端登陆', '1', null, '2018-10-11 20:52:05'), ('42', '1', 'web端登陆', '1', null, '2018-10-11 21:21:27'), ('43', '1', 'web端登陆', '1', null, '2018-10-11 21:31:43'), ('44', '1', 'web端登陆', '1', null, '2018-10-11 21:36:00'), ('45', '1', 'web端登陆', '1', null, '2018-10-11 21:51:44'), ('46', '1', '温度传感器开关', '1', null, '2018-10-11 21:51:54'), ('47', '1', '温度传感器开关', '1', null, '2018-10-11 21:52:05'), ('48', '1', '温度传感器开关', '1', null, '2018-10-11 21:53:28'), ('49', '1', '温度传感器开关', '1', null, '2018-10-11 21:53:38'), ('50', '1', '温度传感器开关', '1', null, '2018-10-11 21:54:01'), ('51', '1', '温度传感器开关', '1', null, '2018-10-11 21:54:08'), ('52', '1', '保存菜单', '1', null, '2018-10-11 22:37:06'), ('53', '1', '保存角色', '1', null, '2018-10-11 22:37:39'), ('54', '1', 'web端登陆', '1', null, '2018-10-11 22:38:22'), ('55', '1', 'web端登陆', '1', null, '2018-10-13 22:57:21'), ('56', '1', '生成代码', '1', null, '2018-10-13 23:16:04'), ('57', '1', '保存菜单', '1', null, '2018-10-13 23:23:37'), ('58', '1', '修改菜单', '1', null, '2018-10-13 23:23:54'), ('59', '1', '修改菜单', '1', null, '2018-10-13 23:24:00'), ('60', '1', '保存角色', '1', null, '2018-10-13 23:24:11'), ('61', '1', 'web端登陆', '1', null, '2018-10-13 23:24:39'), ('62', '1', '修改菜单', '1', null, '2018-10-13 23:26:13'), ('63', '1', 'web端登陆', '1', null, '2018-10-13 23:40:31'), ('64', '1', '生成代码', '1', null, '2018-10-14 00:03:46'), ('65', '1', '生成代码', '1', null, '2018-10-14 00:04:01'), ('66', '1', '保存菜单', '1', null, '2018-10-14 00:07:33'), ('67', '1', '保存菜单', '1', null, '2018-10-14 00:08:11'), ('68', '1', '保存角色', '1', null, '2018-10-14 00:08:19'), ('69', '1', '退出', '1', null, '2018-10-14 00:08:21'), ('70', '1', 'web端登陆', '1', null, '2018-10-14 00:08:22'), ('71', '1', 'web端登陆', '1', null, '2018-10-14 00:09:12'), ('72', '1', 'web端登陆', '1', null, '2018-10-14 00:14:23'), ('73', '1', 'web端登陆', '1', null, '2018-10-14 00:17:14'), ('74', '1', '生成代码', '1', null, '2018-10-14 00:18:19'), ('75', '1', '生成代码', '1', null, '2018-10-14 00:18:34'), ('76', '1', 'web端登陆', '1', null, '2018-10-14 00:24:45'), ('77', '1', 'web端登陆', '1', null, '2018-10-14 00:43:06'), ('78', '1', 'web端登陆', '1', null, '2018-10-14 01:04:59'), ('79', '1', '修改菜单', '1', null, '2018-10-14 01:19:12'), ('80', '1', 'web端登陆', '1', null, '2018-10-14 01:19:43'), ('81', '1', 'web端登陆', '1', null, '2018-10-14 11:19:46'), ('82', '1', '退出', '1', null, '2018-10-14 11:39:18'), ('83', '1', 'web端登陆', '1', null, '2018-10-14 11:39:23'), ('84', '1', 'web端登陆', '1', null, '2018-10-14 13:32:18'), ('85', '1', 'web端登陆', '1', null, '2018-10-14 13:35:51'), ('86', '1', 'web端登陆', '1', null, '2018-10-14 14:00:24'), ('87', '1', 'web端登陆', '1', null, '2018-10-14 14:24:50'), ('88', '1', 'web端登陆', '1', null, '2018-10-14 14:34:10'), ('89', '1', '温度传感器开关', '1', null, '2018-10-14 15:47:19'), ('90', '1', '温度传感器开关', '1', null, '2018-10-14 15:57:14'), ('91', '1', '温度传感器开关', '1', null, '2018-10-14 15:58:11'), ('92', '1', '温度传感器开关', '1', null, '2018-10-14 15:59:23'), ('93', '1', '温度传感器开关', '1', null, '2018-10-14 16:00:23'), ('94', '1', '温度传感器开关', '1', null, '2018-10-14 16:02:49'), ('95', '1', '温度传感器开关', '1', null, '2018-10-14 16:02:51'), ('96', '1', '温度传感器开关', '1', null, '2018-10-14 16:05:09'), ('97', '1', 'web端登陆', '1', null, '2018-10-14 16:17:23'), ('98', '1', '温度传感器开关', '1', null, '2018-10-14 16:19:51'), ('99', '1', '遮阳网开关', '1', null, '2018-10-14 16:21:34'), ('100', '1', 'web端登陆', '1', null, '2018-10-14 16:23:48'), ('101', '1', '遮阳网开关', '1', null, '2018-10-14 16:23:52'), ('102', '1', '风机开关', '1', null, '2018-10-14 16:23:55'), ('103', '1', '灌溉泵开关', '1', null, '2018-10-14 16:23:57'), ('104', '1', '增氧泵开关', '1', null, '2018-10-14 16:24:00'), ('105', null, 'web端登陆', '0', '非法用户进入...', '2018-10-14 18:01:45'), ('106', '1', 'web端登陆', '1', null, '2018-10-14 18:01:48');
COMMIT;

-- ----------------------------
--  Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `css` varchar(30) DEFAULT NULL,
  `href` varchar(1000) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sys_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', 'fa-users', 'pages/user/userList.html', '1', '', '1'), ('2', '1', '用户查询', 'fa-user', 'pages/user/userList.html', '1', '', '2'), ('3', '2', '查询', '', '', '2', 'sys:user:query', '100'), ('4', '2', '新增', '', '', '2', 'sys:user:add', '100'), ('6', '0', '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword.html', '1', 'sys:user:password', '4'), ('7', '0', '系统设置', 'fa-gears', '', '1', '', '5'), ('8', '7', '菜单', 'fa-cog', 'pages/menu/menuList.html', '1', '', '6'), ('9', '8', '查询', '', '', '2', 'sys:menu:query', '100'), ('10', '8', '新增', '', '', '2', 'sys:menu:add', '100'), ('11', '8', '删除', '', '', '2', 'sys:menu:del', '100'), ('12', '7', '角色', 'fa-user-secret', 'pages/role/roleList.html', '1', '', '7'), ('13', '12', '查询', '', '', '2', 'sys:role:query', '100'), ('14', '12', '新增', '', '', '2', 'sys:role:add', '100'), ('15', '12', '删除', '', '', '2', 'sys:role:del', '100'), ('16', '0', '文件管理', 'fa-folder-open', 'pages/file/fileList.html', '1', '', '8'), ('17', '16', '查询', '', '', '2', 'sys:file:query', '100'), ('18', '16', '删除', '', '', '2', 'sys:file:del', '100'), ('19', '0', '数据源监控', 'fa-eye', 'druid/index.html', '1', '', '9'), ('20', '0', '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', '1', '', '10'), ('21', '0', '代码生成', 'fa-wrench', 'pages/generate/edit.html', '1', 'generate:edit', '11'), ('22', '0', '公告管理', 'fa-book', 'pages/notice/noticeList.html', '1', '', '12'), ('23', '22', '查询', '', '', '2', 'notice:query', '100'), ('24', '22', '添加', '', '', '2', 'notice:add', '100'), ('25', '22', '删除', '', '', '2', 'notice:del', '100'), ('26', '0', '日志查询', 'fa-reorder', 'pages/log/logList.html', '1', 'sys:log:query', '13'), ('27', '0', '邮件管理', 'fa-envelope', 'pages/mail/mailList.html', '1', '', '14'), ('28', '27', '发送邮件', '', '', '2', 'mail:send', '100'), ('29', '27', '查询', '', '', '2', 'mail:all:query', '100'), ('30', '0', '定时任务管理', 'fa-tasks', 'pages/job/jobList.html', '1', '', '15'), ('31', '30', '查询', '', '', '2', 'job:query', '100'), ('32', '30', '新增', '', '', '2', 'job:add', '100'), ('33', '30', '删除', '', '', '2', 'job:del', '100'), ('34', '0', 'excel导出', 'fa-arrow-circle-down', 'pages/excel/sql.html', '1', '', '16'), ('35', '34', '导出', '', '', '2', 'excel:down', '100'), ('36', '34', '页面显示数据', '', '', '2', 'excel:show:datas', '100'), ('37', '0', '字典管理', 'fa-reddit', 'pages/dict/dictList.html', '1', '', '17'), ('38', '37', '查询', '', '', '2', 'dict:query', '100'), ('39', '37', '新增', '', '', '2', 'dict:add', '100'), ('40', '37', '删除', '', '', '2', 'dict:del', '100'), ('41', '0', '温度监控', 'fa-asterisk', 'pages/temperature/temperatureList.html', '1', '', '18'), ('42', '7', '关于', 'fa-check-square', 'pages/system/help.html', '1', '', '100'), ('43', '0', '湿度监控', 'fa-adjust', 'pages/humidity/humidityList.html', '1', '', '19'), ('44', '0', 'PM2.5监控', 'fa-graduation-cap', 'pages/pm25/pm25List.html', '1', '', '20'), ('45', '0', '气压监测', 'fa-sun-o', 'pages/pressure/pressureList.html', '1', '', '100');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2017-05-01 13:25:39', '2018-10-14 00:08:19'), ('2', 'USER', '', '2017-08-01 21:47:31', '2017-10-05 21:59:26');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sys_role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4'), ('1', '6'), ('1', '7'), ('1', '8'), ('1', '9'), ('1', '10'), ('1', '11'), ('1', '12'), ('1', '13'), ('1', '14'), ('1', '15'), ('1', '16'), ('1', '17'), ('1', '18'), ('1', '19'), ('1', '20'), ('1', '21'), ('1', '22'), ('1', '23'), ('1', '24'), ('1', '25'), ('1', '26'), ('1', '27'), ('1', '28'), ('1', '29'), ('1', '30'), ('1', '31'), ('1', '32'), ('1', '33'), ('1', '34'), ('1', '35'), ('1', '36'), ('1', '37'), ('1', '38'), ('1', '39'), ('1', '40'), ('1', '41'), ('1', '42'), ('1', '43'), ('1', '44'), ('1', '45'), ('2', '1'), ('2', '2'), ('2', '3'), ('2', '4'), ('2', '6'), ('2', '7'), ('2', '8'), ('2', '9'), ('2', '10'), ('2', '11'), ('2', '12'), ('2', '13'), ('2', '14'), ('2', '15'), ('2', '16'), ('2', '17'), ('2', '18'), ('2', '19'), ('2', '20'), ('2', '21'), ('2', '22'), ('2', '23'), ('2', '24'), ('2', '25'), ('2', '30'), ('2', '31'), ('2', '34'), ('2', '36');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sys_role_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_user` VALUES ('1', '1'), ('2', '2');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `headImgUrl` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '1015ed3b5f1345911198c995f9ad097e', '87e03afa1f0122531f729c9a7453f475', '管理员', null, '17673125001', '', '410000', '1998-07-01', '0', '1', '2017-04-10 15:21:38', '2018-10-09 20:36:12'), ('2', 'user', '72c2e62dba72e5f178542313803f33d1', '143292269df8c63e2da1a9ba2aeff43c', '用户', null, '', '', '', null, '1', '1', '2017-08-01 21:47:18', '2017-08-01 21:47:18');
COMMIT;

-- ----------------------------
--  Table structure for `t_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL,
  `k` varchar(16) NOT NULL,
  `val` varchar(64) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`k`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `t_dict`
-- ----------------------------
BEGIN;
INSERT INTO `t_dict` VALUES ('1', 'sex', '0', '女', '2017-11-17 09:58:24', '2017-11-18 14:21:05'), ('2', 'sex', '1', '男', '2017-11-17 10:03:46', '2017-11-17 10:03:46'), ('3', 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09'), ('4', 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09'), ('5', 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09'), ('6', 'noticeStatus', '0', '草稿', '2017-11-17 16:26:06', '2017-11-17 16:26:09'), ('7', 'noticeStatus', '1', '发布', '2017-11-17 16:26:06', '2017-11-17 16:26:09'), ('8', 'isRead', '0', '未读', '2017-11-17 16:26:06', '2017-11-17 16:26:09'), ('9', 'isRead', '1', '已读', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
COMMIT;

-- ----------------------------
--  Table structure for `t_humidity`
-- ----------------------------
DROP TABLE IF EXISTS `t_humidity`;
CREATE TABLE `t_humidity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `h` int(11) DEFAULT NULL,
  `ddatetime` datetime DEFAULT NULL,
  `obtid` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `t_humidity`
-- ----------------------------
BEGIN;
INSERT INTO `t_humidity` VALUES ('1', '100', '2018-10-04 22:35:20', '11111', '2018-10-10 23:43:19', '2018-10-10 23:43:21'), ('2', '12', '2018-10-08 23:02:32', '11111', '2018-10-10 23:43:23', '2018-10-10 23:43:25'), ('3', '14', '2018-10-10 23:47:44', '11111', '2018-10-10 23:43:27', '2018-10-10 23:43:31'), ('4', '16', '2018-10-12 23:55:54', '11111', '2018-10-10 23:43:33', '2018-10-10 23:43:29'), ('5', '20', '2018-10-13 00:00:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('6', '10', '2018-10-13 00:37:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('7', '40', '2018-10-13 00:55:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('8', '90', '2018-10-13 01:02:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('9', '50', '2018-10-13 01:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('10', '30', '2018-10-13 02:22:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('11', '80', '2018-10-13 02:33:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('12', '60', '2018-10-13 02:50:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('13', '10', '2018-10-13 03:12:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('14', '10', '2018-10-13 03:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('15', '30', '2018-10-13 04:07:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('16', '50', '2018-10-13 05:42:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('17', '80', '2018-10-13 06:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('18', '100', '2018-10-13 07:48:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('19', '15', '2018-10-13 09:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37');
COMMIT;

-- ----------------------------
--  Table structure for `t_job`
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cron` varchar(64) NOT NULL,
  `springBeanName` varchar(64) NOT NULL COMMENT 'springBean名',
  `methodName` varchar(64) NOT NULL COMMENT '方法名',
  `isSysJob` tinyint(1) NOT NULL COMMENT '是否是系统job',
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jobName` (`jobName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_mail`
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '发送人',
  `subject` varchar(255) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '正文',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_mail_to`
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_to`;
CREATE TABLE `t_mail_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mailId` int(11) NOT NULL,
  `toUser` varchar(128) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1成功，0失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `content` text NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_notice_read`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_read`;
CREATE TABLE `t_notice_read` (
  `noticeId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`noticeId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_pm25`
-- ----------------------------
DROP TABLE IF EXISTS `t_pm25`;
CREATE TABLE `t_pm25` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pm` int(11) DEFAULT NULL,
  `ddatetime` datetime DEFAULT NULL,
  `obtid` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='Pm2.5';

-- ----------------------------
--  Records of `t_pm25`
-- ----------------------------
BEGIN;
INSERT INTO `t_pm25` VALUES ('1', '10', '2018-10-04 00:35:20', '11111', '2018-10-10 23:43:19', '2018-10-10 23:43:21'), ('2', '120', '2018-10-08 01:35:32', '11111', '2018-10-10 23:43:23', '2018-10-10 23:43:25'), ('3', '140', '2018-10-10 02:30:44', '11111', '2018-10-10 23:43:27', '2018-10-10 23:43:31'), ('4', '160', '2018-10-12 03:24:54', '11111', '2018-10-10 23:43:33', '2018-10-10 23:43:29'), ('5', '200', '2018-10-13 03:56:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('6', '100', '2018-10-13 04:33:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('7', '110', '2018-10-13 04:58:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('8', '90', '2018-10-13 05:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('9', '50', '2018-10-13 06:00:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('10', '30', '2018-10-13 06:20:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('11', '80', '2018-10-13 06:40:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('12', '60', '2018-10-13 07:02:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('13', '100', '2018-10-13 07:28:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('14', '10', '2018-10-13 07:44:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('15', '30', '2018-10-13 08:00:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('16', '50', '2018-10-13 08:30:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('17', '80', '2018-10-13 08:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('18', '100', '2018-10-13 09:06:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('19', '70', '2018-10-13 09:40:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37');
COMMIT;

-- ----------------------------
--  Table structure for `t_pressure`
-- ----------------------------
DROP TABLE IF EXISTS `t_pressure`;
CREATE TABLE `t_pressure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p` int(11) DEFAULT NULL,
  `ddatetime` datetime DEFAULT NULL,
  `obtid` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='气压表';

-- ----------------------------
--  Records of `t_pressure`
-- ----------------------------
BEGIN;
INSERT INTO `t_pressure` VALUES ('1', '600', '2018-10-04 04:35:20', '11111', '2018-10-10 23:43:19', '2018-10-10 23:43:21'), ('2', '870', '2018-10-08 04:55:32', '11111', '2018-10-10 23:43:23', '2018-10-10 23:43:25'), ('3', '790', '2018-10-10 05:00:44', '11111', '2018-10-10 23:43:27', '2018-10-10 23:43:31'), ('4', '670', '2018-10-12 05:35:54', '11111', '2018-10-10 23:43:33', '2018-10-10 23:43:29'), ('5', '200', '2018-10-13 06:05:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('6', '400', '2018-10-13 06:40:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('7', '200', '2018-10-13 07:00:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('8', '900', '2018-10-13 07:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('9', '500', '2018-10-13 08:23:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('10', '700', '2018-10-13 08:58:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('11', '400', '2018-10-13 09:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('12', '600', '2018-10-13 10:42:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('13', '890', '2018-10-13 10:59:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('14', '1000', '2018-10-13 11:23:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('15', '300', '2018-10-13 11:39:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('16', '500', '2018-10-13 12:00:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('17', '800', '2018-10-13 12:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('18', '700', '2018-10-13 13:16:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('19', '500', '2018-10-13 14:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37');
COMMIT;

-- ----------------------------
--  Table structure for `t_temperature`
-- ----------------------------
DROP TABLE IF EXISTS `t_temperature`;
CREATE TABLE `t_temperature` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t` double DEFAULT NULL,
  `ddatetime` datetime DEFAULT NULL,
  `obtid` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `t_temperature`
-- ----------------------------
BEGIN;
INSERT INTO `t_temperature` VALUES ('1', '10', '2018-10-04 14:00:20', '11111', '2018-10-10 23:43:19', '2018-10-10 23:43:21'), ('2', '12', '2018-10-08 14:35:32', '11111', '2018-10-10 23:43:23', '2018-10-10 23:43:25'), ('3', '14', '2018-10-10 14:55:44', '11111', '2018-10-10 23:43:27', '2018-10-10 23:43:31'), ('4', '16', '2018-10-12 15:05:54', '11111', '2018-10-10 23:43:33', '2018-10-10 23:43:29'), ('5', '20', '2018-10-13 15:16:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('6', '-10', '2018-10-13 15:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('7', '11', '2018-10-13 16:22:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('8', '9', '2018-10-13 16:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('9', '5', '2018-10-13 16:55:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('10', '3', '2018-10-13 17:00:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('11', '8', '2018-10-13 17:45:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('12', '-6', '2018-10-13 17:55:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('13', '-10', '2018-10-13 18:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('14', '-1', '2018-10-13 18:57:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('15', '3', '2018-10-13 19:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('16', '5', '2018-10-13 19:45:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('17', '8', '2018-10-13 20:00:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('18', '10', '2018-10-13 20:35:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37'), ('19', '7', '2018-10-13 20:58:20', '11111', '2018-10-10 23:43:36', '2018-10-10 23:43:37');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
