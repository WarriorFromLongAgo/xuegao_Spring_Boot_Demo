package com.xuegao.springboot_tool.sharding.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAutoConfiguration
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    @Autowired
    private DBProperties properties;
    @Autowired
    private ShardSourceProperties shardSourceProperties;

    public DataSourceConfig() {
    }

    @Bean(
            name = {"dataSource"}
    )
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap(5);
        Map<String, HikariDataSource> hikaris = this.properties.getHikari();
        HikariDataSource masterDataSource = null;
        if (Objects.nonNull(hikaris)) {
            Iterator var4 = hikaris.entrySet().iterator();

            while(var4.hasNext()) {
                Entry<String, HikariDataSource> entry = (Entry)var4.next();
                targetDataSources.put(entry.getKey(), entry.getValue());
                if (masterDataSource == null && "master".equals(entry.getKey())) {
                    masterDataSource = (HikariDataSource)entry.getValue();
                }
            }
        }

        if (targetDataSources.size() < 2) {
            logger.info("Can't find slave db");
        }

        if (masterDataSource == null) {
            logger.error("Can't find master db, project will be exit");
            System.exit(0);
        }

        if (this.shardSourceProperties != null && !CollectionUtils.isEmpty(this.shardSourceProperties.getShardHikari())) {
            targetDataSources.putAll(this.shardSourceProperties.getShardHikari());
        }

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

    public static void main(String[] args) {
        System.out.println(TimeUnit.SECONDS.toMillis(30L));
    }
}
