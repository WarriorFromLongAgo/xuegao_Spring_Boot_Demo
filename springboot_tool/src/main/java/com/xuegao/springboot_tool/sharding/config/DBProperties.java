package com.xuegao.springboot_tool.sharding.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(
    prefix = "spring.datasource"
)
public class DBProperties {
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE1 = "slave1";
    public static final String DB_SLAVE2 = "slave2";
    private Map<String, HikariDataSource> hikari;
    public static final int DB_SLAVE_NUM = 2;

    public DBProperties() {
    }

    public Map<String, HikariDataSource> getHikari() {
        return this.hikari;
    }

    public void setHikari(Map<String, HikariDataSource> hikari) {
        this.hikari = hikari;
    }
}
