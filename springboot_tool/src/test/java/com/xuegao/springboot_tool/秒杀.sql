local表

 DROP TABLE IF EXISTS `t_product`;
 CREATE TABLE `t_product`  (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `name` varchar(255) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci DEFAULT '商品名称',
 `price` varchar(255) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci DEFAULT '商品价格',
 `stocks` int(10) COLLATE utf8_general_ci NOT NULL DEFAULT 0 comment '商品库存',
 `description` varchar(255) CHARACTER SET utf8 NOT NULL COLLATE utf8_general_ci DEFAULT '商品描述',
 PRIMARY KEY (`id`) USING BTREE
 ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic comment '商品表';

insert into t_product(id, name, price, stocks, description)
VALUES
(1, '秒杀', '100', '1000', '秒杀商品');

 DROP TABLE IF EXISTS `t_order`;
 CREATE TABLE `t_order`  (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `product_id` bigint(20) COLLATE utf8_general_ci NOT NULL default 0 comment '商品id',
 `number` int(10) COLLATE utf8_general_ci NOT NULL DEFAULT 0 comment '商品数量',
 PRIMARY KEY (`id`) USING BTREE
 ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic comment '订单表';

truncate t_order;

select count(id) from t_order;
select sum(number) from t_order;