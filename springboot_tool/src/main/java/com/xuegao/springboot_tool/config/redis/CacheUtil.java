package com.xuegao.springboot_tool.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.config.redis
 * <br/> @ClassName：RedisCluster
 * <br/> @Description：切换　redis　的库
 * <br/> @author：xuegao
 * <br/> @date：2020/9/14 17:39
 */
@Component
public class CacheUtil {

    // @Autowired
    // private RedisTemplate<String, Object> redisTemplate;

    // @Autowired
    // private JedisConnectionFactory jedisConnectionFactory;
    //
    // /**
    //  * 指定redis库
    //  */
    // public RedisTemplate getRedisTemplate(int index) {
    //     return getTemplate(redisTemplate, index);
    // }
    //
    // /**
    //  * 获取SpringRedisTemplate
    //  */
    // public RedisTemplate getTemplate(RedisTemplate redisTemplate, int index) {
    //     jedisConnectionFactory.setDatabase(index);
    //     redisTemplate.setConnectionFactory(jedisConnectionFactory);
    //     return redisTemplate;
    // }
}