package com.xuegao.springboot_tool.config;

import com.xuegao.springboot_tool.config.serializer.PrefixStringKeySerializer;
import com.xuegao.springboot_tool.config.serializer.RedisKeyPrefixProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
// @Import({RedisKeyPrefixProperties.class})
public class RedisLimiterHelper {

    @Bean
    public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 自己实现序列化
        // PrefixStringKeySerializer prefixStringKeySerializer = new PrefixStringKeySerializer(new RedisKeyPrefixProperties());
        // redisTemplate.setKeySerializer(prefixStringKeySerializer);
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}