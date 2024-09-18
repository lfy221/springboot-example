CREATE DATABASE  IF NOT EXISTS `crash_course`;
USE `crash_course`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `idno` varchar(18) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `work_time` int(11) DEFAULT NULL COMMENT '1:全职 2:兼职',
  `score` decimal(8,2) DEFAULT NULL,
  `grade` int(255) DEFAULT NULL COMMENT '用户级别：1，初级；2，中级；3，高级',
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT '逻辑删除(0：正常 1:删除)',
  `version` int(255) DEFAULT NULL COMMENT '乐观锁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;