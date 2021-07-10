package com.xuegao.springboot_tool.sharding.constant;

/**
 * 分库分表配置信息
 *
 * @author pangjian
 * @date 2021/5/24 17:12
 * @version 1.0
 */
public class ShardOfferConstants {

    /**
     * 客户ID-分片键
     */
    public static final String CUSTOMER_ID = "customerId";

    /**
     * 数据库前缀
     */
    public static final String DB_PREFIX = "crm_was";

    /**
     * 客户报价
     */
    public static final String CUSTOMER_OFFER_PREFIX = "customer_offer";

    /**
     * 特惠客户报价
     */
    public static final String PROMOTE_CUSTOMER_OFFER_PREFIX = "promote_customer_offer";

    /**
     * 客户服务方式
     */
    public static final String CUSTOMER_SERVICE_TYPE_PREFIX = "customer_service_type";


    private ShardOfferConstants() {}


}