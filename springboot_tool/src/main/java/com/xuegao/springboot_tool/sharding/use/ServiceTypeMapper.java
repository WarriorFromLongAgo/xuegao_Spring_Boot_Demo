package com.xuegao.springboot_tool.sharding.use;// package com.xuegao.springboot_tool.sharding.use;
//
// import com.xuegao.springboot_tool.sharding.annotation.ShardRule;
// import com.xuegao.springboot_tool.sharding.constant.ShardOfferConstants;
// import org.apache.ibatis.annotations.Mapper;
//
// /**
//  * 分库分表，参数必须传入客户ID
//  * @author pangjian
//  * @date 2021-05-18
//  */
// @ShardRule(shardKey = ShardOfferConstants.CUSTOMER_ID
//         , shardTable = ShardOfferConstants.CUSTOMER_SERVICE_TYPE_PREFIX
//         , shardDB = ShardOfferConstants.DB_PREFIX)
// @Mapper
// public interface ServiceTypeMapper /*extends Mapper<Long>*/ {
// }