package com.xuegao.springboot_tool.config;


import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * <br/> @PackageName：com.xuegao.utils
 * <br/> @ClassName：RedissonConfig
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/26 22:55
 */
@Configuration
public class RedissonConfig {
    private final static Logger log = LoggerFactory.getLogger(RedissonConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    // @Value("${spring.redis.password}")
    private String password;

    // RedissonClient 调用 shutdown 方法

    /**
     * 指定组建的init方法和destroy的几种方法
     * 1：在配置类中 @Bean(initMethod = "init",destroyMethod = "destory")注解指定
     * 2：实现InitializingBean接口重写其afterPropertiesSet方法，实现DisposableBean接口重写destroy方法
     * 3：利用java的JSR250规范中的@PostConstruct标注在init方法上，@PreDestroy标注在destroy注解上
     */
    @Bean(destroyMethod = "shutdown")
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

    // @Bean(destroyMethod = "shutdown")
    // public RedissonClient redissonClientMasterSlave() {
    //     RedissonClient redissonClient = null;
    //     Config config = new Config();
    //     //单节点
    //     //添加主从配置
    //     config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress("","");
    //     // 集群模式配置 setScanInterval()扫描间隔时间，单位是毫秒, //可以用"rediss://"来启用SSL连接
    //     config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001").addNodeAddress("redis://127.0.0.1:7002");
    //     try {
    //         redissonClient = Redisson.create(config);
    //         log.info("创建 redissonClient 成功。。。。。。。。");
    //         return redissonClient;
    //     } catch (Exception e) {
    //         log.info("创建 redissonClient 失败。。。。。。。。。。。。");
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    // @Bean(destroyMethod = "shutdown")
    // public RedissonClient redissonYmlClient() throws IOException {
    // 1. 创建Config对象，读取配置属性
    // 2. 创建Redisson对象,传入Config对象
    // RedissonClient redissonClient = Redisson.create(Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream()));
    // return redissonClient;
    // }

    // /**
    //  * 单机模式自动装配
    //  * @return
    //  */
    // @Bean
    // @ConditionalOnProperty(name="redisson.address")
    // RedissonClient redissonSingle() {
    //     Config config = new Config();
    //     SingleServerConfig serverConfig = config.useSingleServer()
    //             .setAddress(redssionProperties.getAddress())
    //             .setTimeout(redssionProperties.getTimeout())
    //             .setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
    //             .setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());
    //
    //     if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
    //         serverConfig.setPassword(redssionProperties.getPassword());
    //     }
    //     return Redisson.create(config);
    // }

    // /**
    //  * 哨兵模式自动装配
    //  * @return
    //  */
    // @Bean
    // @ConditionalOnProperty(name="redisson.master-name")
    // RedissonClient redissonSentinel() {
    //     Config config = new Config();
    //     SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redssionProperties.getSentinelAddresses())
    //             .setMasterName(redssionProperties.getMasterName())
    //             .setTimeout(redssionProperties.getTimeout())
    //             .setMasterConnectionPoolSize(redssionProperties.getMasterConnectionPoolSize())
    //             .setSlaveConnectionPoolSize(redssionProperties.getSlaveConnectionPoolSize());
    //
    //     if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
    //         serverConfig.setPassword(redssionProperties.getPassword());
    //     }
    //     return Redisson.create(config);
    // }


}