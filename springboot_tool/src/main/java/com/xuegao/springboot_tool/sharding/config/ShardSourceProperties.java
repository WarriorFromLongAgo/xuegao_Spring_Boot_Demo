// package com.xuegao.springboot_tool.sharding.config;
//
// import com.zaxxer.hikari.HikariDataSource;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.stereotype.Component;
//
// import java.util.Map;
//
// @Component
// @ConfigurationProperties(
//     prefix = "shardDataSource"
// )
// public class ShardSourceProperties {
//     private static final Logger log = LoggerFactory.getLogger(ShardSourceProperties.class);
//     private Map<String, HikariDataSource> shardHikari;
//
//     public ShardSourceProperties() {
//     }
//
//     public Map<String, HikariDataSource> getShardHikari() {
//         return this.shardHikari;
//     }
//
//     public void setShardHikari(Map<String, HikariDataSource> shardHikari) {
//         this.shardHikari = shardHikari;
//     }
// }
