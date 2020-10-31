-- auto Generated on 2020-10-30
-- DROP TABLE IF EXISTS user_info;
CREATE TABLE `user_info`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `username`    varchar(50)     NOT NULL DEFAULT '' COMMENT '用户名',
    `password`    varchar(50)     NOT NULL DEFAULT '' COMMENT 'password',
    `email`       varchar(50)     NOT NULL DEFAULT '' COMMENT 'email',
    `nickname`    varchar(50)              DEFAULT '' COMMENT '昵称',
    `create_time` datetime        NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
    `update_time` datetime                 DEFAULT NULL COMMENT 'updateTime',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_nickname` (`nickname`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='user';
