package com.xuegao.springboot_tool.config.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.xuegao.springboot_tool.utils.RedisUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.config.redis
 * <br/> @ClassName：SfRedisConfig
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/02 11:02
 */
public class SfRedisConfig {

    @Bean
    @ConditionalOnMissingBean
    public RedisUtil redisUtil(RedisConnectionFactory redisConnectionFactory) {


        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        if(redisConnectionFactory instanceof LettuceConnectionFactory) {
            ((LettuceConnectionFactory)redisConnectionFactory).setDatabase(1);
        }
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        GenericFastJsonRedisSerializer valueSerializer = new GenericFastJsonRedisSerializer();
        // value序列化方式采用fastjson
        redisTemplate.setValueSerializer(valueSerializer);
        // hash的value序列化方式采用fastjson
        redisTemplate.setHashValueSerializer(valueSerializer);

        //redisTemplate.setStringSerializer(stringRedisSerializer);


        RedisUtil instance = RedisUtil.getInstance();
        instance.setRedisTemplate(redisTemplate);
        redisTemplate.afterPropertiesSet();



        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        if(redisConnectionFactory instanceof LettuceConnectionFactory) {
            ((LettuceConnectionFactory)redisConnectionFactory).setDatabase(1);
        }

        stringRedisTemplate.setValueSerializer(stringRedisSerializer);
        stringRedisTemplate.setKeySerializer(stringRedisSerializer);
        instance.setStringRedisTemplate(stringRedisTemplate);


        stringRedisTemplate.afterPropertiesSet();
        return instance;
    }
}