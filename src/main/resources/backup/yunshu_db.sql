-- 云枢管理系统 数据库结构与初始化数据
-- 导出时间: 2026-08-14 02:00:01

SET NAMES utf8mb4;

CREATE TABLE `sys_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL,
  `pwd` varchar(64) NOT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `role_id` int DEFAULT '2',
  `dept_id` bigint DEFAULT NULL,
  `status` tinyint DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_account` VALUES
(1,'admin','e10adc3949ba59abbe56e057f20f883e','系统管理员','13800000001',1,1,1,'2024-03-01 09:00:00'),
(2,'zhangwei','e10adc3949ba59abbe56e057f20f883e','张伟','13800000002',2,2,1,'2024-03-02 10:00:00'),
(3,'lina','25f9e794323b453885f5181f1b624d0b','李娜','13800000003',2,2,1,'2024-03-05 14:30:00'),
(4,'wangqiang','e10adc3949ba59abbe56e057f20f883e','王强','13800000004',3,3,1,'2024-04-11 08:20:00');

CREATE TABLE `biz_notice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` longtext,
  `author_id` bigint DEFAULT NULL,
  `top` tinyint DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `biz_notice` VALUES
(1,'关于国庆节放假安排的通知','<p>各部门：根据公司安排，国庆节放假时间为10月1日至10月7日。</p>',1,1,'2025-09-20 09:00:00'),
(2,'OA系统升级公告','<p>系统将于本周六凌晨进行升级维护，届时暂停服务两小时。</p>',1,0,'2025-10-12 17:00:00');

CREATE TABLE `biz_remark` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `notice_id` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `biz_report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint NOT NULL,
  `week` varchar(20) DEFAULT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `biz_report` VALUES
(1,2,'2026-W32','本周完成三季度合同归档，下周跟进供应商对账。','2026-08-08 18:00:00'),
(2,3,'2026-W32','完成新员工入职培训材料整理，含薪酬带宽初稿。','2026-08-08 18:10:00');

-- 第三方对接配置（运维备注：勿外发）
-- 短信网关 appKey: ys-sms-9f2c1e  appSecret: 5d41402abc4b2a76b9719d911017c592
-- 邮件网关 SMTP: smtp.yunshu-inner.com  账号: notify@yunshu-inner.com  密码: Mail@2024
