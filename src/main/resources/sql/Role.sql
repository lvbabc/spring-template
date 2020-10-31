-- auto Generated on 2020-10-30
-- DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `role_name`   varchar(50)     NOT NULL DEFAULT '' COMMENT '角色名',
    `create_time` datetime        NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
    `update_time` datetime                 DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='role';
