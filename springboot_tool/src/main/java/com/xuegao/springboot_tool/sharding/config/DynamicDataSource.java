// package com.xuegao.springboot_tool.sharding.config;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
// public class DynamicDataSource extends AbstractRoutingDataSource {
//     private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
//
//     public DynamicDataSource() {
//     }
//
//     protected Object determineCurrentLookupKey() {
//         String soruce = DynamicDataSourceHolder.getDataSource();
//         logger.debug("DynamicDataSource currentSource: {}" + soruce);
//         return soruce;
//     }
// }
