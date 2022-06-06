CREATE DATABASE `stealthy_striver_db_v2`;
use `stealthy_striver_db_v2`;
CREATE TABLE `ss_user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(28) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别 1-男 2-女',
  `introduce_sign` varchar(100) NOT NULL DEFAULT '' COMMENT '个性签名',
  `head_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '注销标识字段(0-正常 1-已注销)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

CREATE TABLE `ss_user_token` (
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `token` varchar(32) NOT NULL COMMENT 'token值(32位字符串)',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `expire_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'token过期时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uq_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `ss_record` (
  `ss_id` int NOT NULL AUTO_INCREMENT COMMENT '偷博记录的id',
  `ss_user_id` int NOT NULL COMMENT '偷博仔id',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '日志IP',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `create_time` datetime DEFAULT NULL COMMENT '开始偷博的时间',
  `duraiton` int DEFAULT '0' COMMENT '持续时间(天数计)',
  `status` tinyint(1) DEFAULT NULL COMMENT '达成目标1;未完成0;进行中:2',
  PRIMARY KEY (`ss_id`) USING BTREE,
  KEY `ss_user_id` (`ss_user_id`),
  CONSTRAINT `ss_record_ibfk_1` FOREIGN KEY (`ss_user_id`) REFERENCES `ss_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;