package com.xuegao.springboot_tool.utils;

/**
 * <br/> @PackageName：com.xuegao.utils
 * <br/> @ClassName：RedissonConfig
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/6/26 22:55
 */

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class RedissonConfig {

    Logger log = LoggerFactory.getLogger(getClass());

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    // @Value("${spring.redis.password}")
    private String password;


    @Bean
    public RedissonClient redissonClient() {
        RedissonClient redissonClient = null;
        Config config = new Config();
        //单节点
        config.useSingleServer().setAddress("redis://" + host + ":" + port);

        if (StringUtils.isEmpty(password)) {
            config.useSingleServer().setPassword(null);
        } else {
            config.useSingleServer().setPassword(password);
        }
        //添加主从配置
        // config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        // 集群模式配置 setScanInterval()扫描间隔时间，单位是毫秒, //可以用"rediss://"来启用SSL连接
        // config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001").addNodeAddress("redis://127.0.0.1:7002");
        try {
            redissonClient = Redisson.create(config);
            log.info("创建 redissonClient 成功。。。。。。。。");
            return redissonClient;
        } catch (Exception e) {
            log.info("创建 redissonClient 失败。。。。。。。。。。。。");
            e.printStackTrace();
            return null;
        }
    }



}