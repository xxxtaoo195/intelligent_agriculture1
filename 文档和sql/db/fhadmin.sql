/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : fhadmin

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 10/14/2018 21:40:39 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `weixin_command`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_command`;
CREATE TABLE `weixin_command` (
  `COMMAND_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL COMMENT '关键词',
  `COMMANDCODE` varchar(255) DEFAULT NULL COMMENT '应用路径',
  `CREATETIME` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `STATUS` int(1) NOT NULL COMMENT '状态',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`COMMAND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `weixin_command`
-- ----------------------------
BEGIN;
INSERT INTO `weixin_command` VALUES ('2636750f6978451b8330874c9be042c2', '锁定服务器', 'rundll32.exe user32.dll,LockWorkStation', '2015-05-10 21:25:06', '1', '锁定计算机'), ('46217c6d44354010823241ef484f7214', '打开浏览器', 'C:/Program Files/Internet Explorer/iexplore.exe', '2015-05-09 02:43:02', '1', '打开浏览器操作'), ('576adcecce504bf3bb34c6b4da79a177', '关闭浏览器', 'taskkill /f /im iexplore.exe', '2015-05-09 02:36:48', '2', '关闭浏览器操作'), ('854a157c6d99499493f4cc303674c01f', '关闭QQ', 'taskkill /f /im qq.exe', '2015-05-10 21:25:46', '1', '关闭QQ'), ('ab3a8c6310ca4dc8b803ecc547e55ae7', '打开QQ', 'D:/SOFT/QQ/QQ/Bin/qq.exe', '2015-05-10 21:25:25', '1', '打开QQ');
COMMIT;

-- ----------------------------
--  Table structure for `weixin_imgmsg`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_imgmsg`;
CREATE TABLE `weixin_imgmsg` (
  `IMGMSG_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL COMMENT '关键词',
  `CREATETIME` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `STATUS` int(11) NOT NULL COMMENT '状态',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  `TITLE1` varchar(255) DEFAULT NULL COMMENT '标题1',
  `DESCRIPTION1` varchar(255) DEFAULT NULL COMMENT '描述1',
  `IMGURL1` varchar(255) DEFAULT NULL COMMENT '图片地址1',
  `TOURL1` varchar(255) DEFAULT NULL COMMENT '超链接1',
  `TITLE2` varchar(255) DEFAULT NULL COMMENT '标题2',
  `DESCRIPTION2` varchar(255) DEFAULT NULL COMMENT '描述2',
  `IMGURL2` varchar(255) DEFAULT NULL COMMENT '图片地址2',
  `TOURL2` varchar(255) DEFAULT NULL COMMENT '超链接2',
  `TITLE3` varchar(255) DEFAULT NULL COMMENT '标题3',
  `DESCRIPTION3` varchar(255) DEFAULT NULL COMMENT '描述3',
  `IMGURL3` varchar(255) DEFAULT NULL COMMENT '图片地址3',
  `TOURL3` varchar(255) DEFAULT NULL COMMENT '超链接3',
  `TITLE4` varchar(255) DEFAULT NULL COMMENT '标题4',
  `DESCRIPTION4` varchar(255) DEFAULT NULL COMMENT '描述4',
  `IMGURL4` varchar(255) DEFAULT NULL COMMENT '图片地址4',
  `TOURL4` varchar(255) DEFAULT NULL COMMENT '超链接4',
  `TITLE5` varchar(255) DEFAULT NULL COMMENT '标题5',
  `DESCRIPTION5` varchar(255) DEFAULT NULL COMMENT '描述5',
  `IMGURL5` varchar(255) DEFAULT NULL COMMENT '图片地址5',
  `TOURL5` varchar(255) DEFAULT NULL COMMENT '超链接5',
  `TITLE6` varchar(255) DEFAULT NULL COMMENT '标题6',
  `DESCRIPTION6` varchar(255) DEFAULT NULL COMMENT '描述6',
  `IMGURL6` varchar(255) DEFAULT NULL COMMENT '图片地址6',
  `TOURL6` varchar(255) DEFAULT NULL COMMENT '超链接6',
  `TITLE7` varchar(255) DEFAULT NULL COMMENT '标题7',
  `DESCRIPTION7` varchar(255) DEFAULT NULL COMMENT '描述7',
  `IMGURL7` varchar(255) DEFAULT NULL COMMENT '图片地址7',
  `TOURL7` varchar(255) DEFAULT NULL COMMENT '超链接7',
  `TITLE8` varchar(255) DEFAULT NULL COMMENT '标题8',
  `DESCRIPTION8` varchar(255) DEFAULT NULL COMMENT '描述8',
  `IMGURL8` varchar(255) DEFAULT NULL COMMENT '图片地址8',
  `TOURL8` varchar(255) DEFAULT NULL COMMENT '超链接8',
  PRIMARY KEY (`IMGMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `weixin_imgmsg`
-- ----------------------------
BEGIN;
INSERT INTO `weixin_imgmsg` VALUES ('380b2cb1f4954315b0e20618f7b5bd8f', '首页', '2015-05-10 20:51:09', '1', '图文回复', '图文回复标题', '图文回复描述', 'http://a.hiphotos.baidu.com/image/h%3D360/sign=c6c7e73ebc389b5027ffe654b535e5f1/a686c9177f3e6709392bb8df3ec79f3df8dc55e3.jpg', 'www.baidu.com', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
COMMIT;

-- ----------------------------
--  Table structure for `weixin_key`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_key`;
CREATE TABLE `weixin_key` (
  `KEY_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(100) DEFAULT NULL COMMENT '用户名',
  `APPID` varchar(100) DEFAULT NULL COMMENT 'appid',
  `APPSECRET` varchar(100) DEFAULT NULL COMMENT 'appsecret',
  `ACCESS_TOKEN` varchar(100) DEFAULT NULL COMMENT 'access_token',
  `CREATETIME` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  `WXUSERNAME` varchar(100) DEFAULT NULL COMMENT '公众号',
  PRIMARY KEY (`KEY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `weixin_key`
-- ----------------------------
BEGIN;
INSERT INTO `weixin_key` VALUES ('58510f788aea4924b4a5438ecda5f749', 'itFteacher', '111111122222', '11112223332', '', '2017-03-29 02:31:03', 'itFteacher', 'itFteacher'), ('a1e5687bbbc34a8eb148bec252d371e0', 'faz', 'wx1e00fb94a1fcd7295', 'e0e5c163bbc1758b9e7e2ef142131b7', '', '2017-04-10 00:34:00', 'faz', 'fzqcsdzf');
COMMIT;

-- ----------------------------
--  Table structure for `weixin_mymenu`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_mymenu`;
CREATE TABLE `weixin_mymenu` (
  `MYMENU_ID` varchar(100) NOT NULL,
  `WXUSERNAME` varchar(100) DEFAULT NULL COMMENT '公众号',
  `USERNAME` varchar(100) DEFAULT NULL COMMENT '用户名',
  `TITLE` varchar(30) DEFAULT NULL COMMENT '菜单名称',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '类型',
  `CONTENT` varchar(100) DEFAULT NULL COMMENT '指向',
  `XID` varchar(10) DEFAULT NULL COMMENT 'XID',
  PRIMARY KEY (`MYMENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `weixin_mymenu`
-- ----------------------------
BEGIN;
INSERT INTO `weixin_mymenu` VALUES ('00ca43bbba2746429c8a6e0028a2f197', 'itFteacher', 'itFteacher', '', '', '', 'M33'), ('02c67417e56f43a6be5356d5c5201ef7', 'itFteacher', 'itFteacher', 'ddd', 'click', 'eee', 'M21'), ('0853ca56187044309ee5e2dfb93b2b16', 'fzqcsdzf', 'faz', '公司介绍', 'click', '首页', 'M31'), ('0977efdd9394427abf73bf43a6c82a7e', 'fzqcsdzf', 'faz', '', '', '', 'M34'), ('13e5b9a51f87430dabe2d8cc998d297c', 'itFteacher', 'itFteacher', '一级菜单', '', '', 'M1'), ('14e68258190b45cba4d21e132508252d', 'itFteacher', 'itFteacher', '', '', '', 'M13'), ('16230e4149134556ac5edf691d25be2a', 'itFteacher', 'itFteacher', '', '', '', 'M24'), ('19353267c4ea43fb903b88f6401ea41a', 'itFteacher', 'itFteacher', '', '', '', 'M25'), ('1e4b0c96e0084ee4b80c8ea6d37a667f', 'fzqcsdzf', 'faz', '', '', '', 'M25'), ('230f6447fee84bcd827de0ec73970660', 'itFteacher', 'itFteacher', 'dde', '', '', 'M2'), ('24315e33166647198dcbd31a44a1a866', 'itFteacher', 'itFteacher', '', '', '', 'M14'), ('35afd4adab304cde9a094d1df1d7ca97', 'fzqcsdzf', 'faz', '服务指南', '', '', 'M2'), ('3daed1d236224a729860e77fe7295ea7', 'itFteacher', 'itFteacher', '', '', '', 'M32'), ('3fbae4e173de4f36a6d721ca77b6480c', 'itFteacher', 'itFteacher', '', '', '', 'M34'), ('4532044ae6cd4c7cb1c89e2cfdb0d812', 'itFteacher', 'itFteacher', '', '', '', 'M22'), ('512c6fb9e4374ffa88397e5a5fcd7149', 'fzqcsdzf', 'faz', '', '', '', 'M13'), ('5f65f5be31e34b83b1f51afa4856c6db', 'fzqcsdzf', 'faz', '', '', '', 'M24'), ('5fb159eb85a049ef9e0f7b56c2c8538e', 'itFteacher', 'itFteacher', '', '', '', 'M12'), ('656c281ed7a142d6907baa6b36e93347', 'itFteacher', 'itFteacher', '', '', '', 'M23'), ('65c750aa4bf44b7a9de42352f8f409cb', 'fzqcsdzf', 'faz', '', '', '', 'M23'), ('679127c586784974a18e64a3660e0d11', 'itFteacher', 'itFteacher', '', '', '', 'M35'), ('71bb64b0be99487da8bb7f626f59ca92', 'fzqcsdzf', 'faz', '', '', '', 'M35'), ('72ccaa86b5eb4bdb85c368046f2c4a99', 'fzqcsdzf', 'faz', '', '', '', 'M22'), ('73438cf53ac9468895e6600b0a27437b', 'fzqcsdzf', 'faz', '会员卡', 'click', '会员卡', 'M11'), ('77730082177e4960aa01a75b5b011c59', 'fzqcsdzf', 'faz', '司考', 'click', '司考', 'M21'), ('82ce76710fd848028b158425bb88f61e', 'fzqcsdzf', 'faz', '', '', '', 'M32'), ('999a8965d4484facaaeb2768471207c5', 'itFteacher', 'itFteacher', '', '', '', 'M3'), ('aa09cedf69e04eda9bb3a0f3b77db46c', 'itFteacher', 'itFteacher', '', '', '', 'M15'), ('b003da264a08499e9c3c297001fbb080', 'fzqcsdzf', 'faz', '', '', '', 'M14'), ('b5ac19e5b3924dffa01ced024c5c0caa', 'fzqcsdzf', 'faz', '会员中心', '', '', 'M1'), ('bf05113f9ed84bba9874f081b3cc613f', 'fzqcsdzf', 'faz', '商城', 'click', '商城', 'M12'), ('c193c0f155034d68a79c662b4046699f', 'itFteacher', 'itFteacher', '', '', '', 'M11'), ('d162cdf5d8494dff9c7825fa0f900f69', 'fzqcsdzf', 'faz', '关于我们', '', '', 'M3'), ('d998cb443172408bb713cf2c04d2388a', 'fzqcsdzf', 'faz', '', '', '', 'M33'), ('e940253208d84c81ae8fbec4f5f299a9', 'itFteacher', 'itFteacher', '', '', '', 'M31'), ('f3e3fc45b54e4888b2670ac09d85b478', 'fzqcsdzf', 'faz', '', '', '', 'M15');
COMMIT;

-- ----------------------------
--  Table structure for `weixin_textmsg`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_textmsg`;
CREATE TABLE `weixin_textmsg` (
  `TEXTMSG_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL COMMENT '关键词',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `CREATETIME` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `STATUS` int(2) DEFAULT NULL COMMENT '状态',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`TEXTMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `weixin_textmsg`
-- ----------------------------
BEGIN;
INSERT INTO `weixin_textmsg` VALUES ('695cd74779734231928a253107ab0eeb', '吃饭', '吃了噢噢噢噢', '2015-05-10 22:52:27', '1', '文本回复'), ('d4738af7aea74a6ca1a5fb25a98f9acb', '关注', '这里是关注后回复的内容', '2015-05-11 02:12:36', '1', '关注回复');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
