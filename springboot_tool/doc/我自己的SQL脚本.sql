DROP TABLE IF EXISTS sys_userinfo;
create table sys_userinfo
(
    `id`          bigint(20) AUTO_INCREMENT       NOT NULL comment ''主键'',
    `username`    varchar(20) CHARACTER SET utf8  NOT NULL COLLATE utf8_general_ci default ''0'' comment ''用户名'',
    `password`    varchar(20) CHARACTER SET utf8  NOT NULL COLLATE utf8_general_ci default ''0'' comment ''密码'',
    `status`      char(2) CHARACTER SET utf8      NOT NULL COLLATE utf8_general_ci default ''0'' comment ''0正常，1停用'',
    `delete_flag` char(1) CHARACTER SET utf8      NOT NULL COLLATE utf8_general_ci default ''0'' comment ''0未删除，1已删除'',
    `create_id`   bigint(20)                      NOT NULL COLLATE utf8_general_ci default 1 comment ''创建人id'',
    `create_name` varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''创建人真实名称'' comment ''创建人真实名称'',
    `create_time` datetime(0)                     NOT NULL COLLATE utf8_general_ci default now() comment ''创建时间'',
    `update_id`   int(20)                         NOT NULL COLLATE utf8_general_ci default 1 comment ''修改人id'',
    `update_name` varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''创建人真实名称'' comment ''修改人真实名称'',
    `update_time` datetime(0)                     NOT NULL COLLATE utf8_general_ci default now() comment ''修改时间'',
    PRIMARY KEY (`id`) USING BTREE
#     不设置名称的唯一索引，由代码控制
#     UNIQUE INDEX (username) using btree
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''用户信息表'';

# roleArr  varchar(255) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''ROLE_USER'' comment ''一个人的多个权限,逗号分割'',


DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`
(
    id            bigint(20) AUTO_INCREMENT       NOT NULL comment ''主键'',
    `name`        varchar(255) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci DEFAULT ''商品名称'' comment ''商品名称'',
    `price`       varchar(255) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci DEFAULT ''9999999999999.99'' comment ''商品价格'',
    `stocks`      int(10)                         NOT NULL COLLATE utf8_general_ci DEFAULT 0 comment ''商品库存'',
    `description` varchar(255) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci DEFAULT ''商品描述'' comment ''商品描述'',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''商品表'';

insert into t_product(id, name, price, stocks, description)
VALUES (null, ''秒杀'', ''100'', ''1000'', ''秒杀商品'');

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `id`         bigint(20)                         NOT NULL AUTO_INCREMENT,
    `product_id` bigint(20) COLLATE utf8_general_ci NOT NULL default 0 comment ''商品id'',
    `number`     int(10) COLLATE utf8_general_ci    NOT NULL DEFAULT 0 comment ''商品数量'',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''订单表'';

truncate t_order;

select count(id)
from t_order;
select sum(number)
from t_order;

insert into sys_userinfo (id, username, password, status)
select null, ''username'', ''password'', id
from t_product;

DROP TABLE IF EXISTS `t_time_hourglass`;
CREATE TABLE `t_time_hourglass`
(
    `id`           bigint(20)                           NOT NULL AUTO_INCREMENT,
    `pid`          bigint(20)                           NOT NULL default 0 comment ''事情的父id'',
    `description`  varchar(100) COLLATE utf8_general_ci NOT NULL default ''事情的描述'' comment ''事情的描述'',
    `current_time` datetime(0) COLLATE utf8_general_ci  NOT NULL DEFAULT now() comment ''当前时间'',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''时间沙漏表'';

insert into t_time_hourglass(id, description, `current_time`)
values (null, ''看博客，看网站'', ''2020-09-07 09:30:10'');
insert into t_time_hourglass(id, description, `current_time`)
values (null, ''看博客，看网站'', now());
insert into t_time_hourglass(id, pid, description, `current_time`)
values (null, 1, ''编写昨晚玩转libreoffice的使用记录 end'', now());

select *
from t_time_hourglass
order by id
limit 3 offset 5;

DROP TABLE IF EXISTS `t_my_jvm`;
CREATE TABLE `t_my_jvm`
(
    `id`            bigint(20)                           NOT NULL AUTO_INCREMENT,
    `configuration` varchar(100) COLLATE utf8_general_ci NOT NULL default '''' comment ''配置'',
    `description`   varchar(100) COLLATE utf8_general_ci NOT NULL default '''' comment ''事情的描述'',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''JVM问题排查'';
insert into t_my_jvm(id, configuration, description)
values (null, ''1'', ''jvm CPU彪高'');

DROP TABLE IF EXISTS `t_my_test`;
CREATE TABLE `t_my_test`
(
    `id`     bigint(20)                               NOT NULL AUTO_INCREMENT,
    `number` int(10) unsigned COLLATE utf8_general_ci NOT NULL DEFAULT 0 comment ''商品数量'',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''test'';
insert into t_my_test (id, number)
values (null, -1);

DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`
(
    `id`          bigint(20)                      NOT NULL AUTO_INCREMENT comment ''文章id'',
    `title`       varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''文章标题'' comment ''文章标题'',
    `text`        varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''文章的正文'' comment ''文章的正文'',
    `delete_flag` char(1) CHARACTER SET utf8      NOT NULL COLLATE utf8_general_ci default '''' comment ''0未删除，1已删除'',
    `create_id`   bigint(20)                      NOT NULL COLLATE utf8_general_ci default 0 comment ''创建人id'',
    `create_name` varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''创建人真实名称'' comment ''创建人真实名称'',
    `create_time` datetime(0)                     NOT NULL COLLATE utf8_general_ci default now() comment ''创建时间'',
    `update_id`   bigint(20)                      NOT NULL COLLATE utf8_general_ci default 0 comment ''修改人id'',
    `update_name` varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''创建人真实名称'' comment ''修改人真实名称'',
    `update_time` datetime(0)                     NOT NULL COLLATE utf8_general_ci default now() comment ''修改时间'',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''文章表'';

DROP TABLE IF EXISTS `t_thumbs_up_userinfo`;
CREATE TABLE `t_thumbs_up_userinfo`
(
    `id`           bigint(20)                      NOT NULL AUTO_INCREMENT comment ''主键'',
    `article_id`   bigint(20)                      NOT NULL COLLATE utf8_general_ci default 0 comment ''文章id'',
    `give_user_id` bigint(20)                      NOT NULL COLLATE utf8_general_ci default 0 comment ''点赞的用户id'',
    `user_id`      bigint(20)                      NOT NULL COLLATE utf8_general_ci default 0 comment ''被点赞的用户id'',
    `status`       char(1) CHARACTER SET utf8      NOT NULL COLLATE utf8_general_ci default '''' comment ''点赞状态，0取消，1点赞'',
    `delete_flag`  char(1) CHARACTER SET utf8      NOT NULL COLLATE utf8_general_ci default '''' comment ''0未删除，1已删除'',
    `create_id`    bigint(20)                      NOT NULL COLLATE utf8_general_ci default 0 comment ''创建人id'',
    `create_name`  varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''创建人真实名称'' comment ''创建人真实名称'',
    `create_time`  datetime(0)                     NOT NULL COLLATE utf8_general_ci default now() comment ''创建时间'',
    `update_id`    bigint(20)                      NOT NULL COLLATE utf8_general_ci default 0 comment ''修改人id'',
    `update_name`  varchar(100) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci default ''创建人真实名称'' comment ''修改人真实名称'',
    `update_time`  datetime(0)                     NOT NULL COLLATE utf8_general_ci default now() comment ''修改时间'',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_give_user_id` (`give_user_id`),
    INDEX `index_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic comment ''对文章的点赞记录'';

insert into t_thumbs_up_userinfo(id)
VALUES (null);





