package com.xuegao.springboot_tool.config.redis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.util.List;

@Configuration
// @Import({RedisKeyPrefixProperties.class})
public class RedisTemplateConfig {
    // /**
    //  * key 生成
    //  * @return
    //  */
    // @Bean
    // public KeyGenerator simpleKeyGenerator() {
    //     return (o, method, objects) -> {
    //         StringBuilder stringBuilder = new StringBuilder();
    //         stringBuilder.append(o.getClass().getSimpleName());
    //         stringBuilder.append(".");
    //         stringBuilder.append(method.getName());
    //         stringBuilder.append("[");
    //         for (Object obj : objects) {
    //             stringBuilder.append(obj.toString());
    //         }
    //         stringBuilder.append("]");
    //         String build = stringBuilder.toString();
    //         return build;
    //     };
    // }
    // /**
    //  * 缓存前缀（追加一个冒号 : ）
    //  * @return
    //  */
    // private CacheKeyPrefix myKeyPrefix(){
    //     return name -> name + ":";
    // }

    // /**
    //  * Redis模板 默认序列化方式
    //  * @param factory
    //  * @return
    //  */
    // @Bean
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    //
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     //key序列化
    //     RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    //     template.setConnectionFactory(factory);
    //
    //     //key序列化方式
    //     template.setKeySerializer(redisSerializer);
    //
    //     template.afterPropertiesSet();
    //     return template;
    // }
    //
    // /**
    //  * Redis模板 Jackson2 序列化方式
    //  * @param factory
    //  * @return
    //  */
    // @Bean(name = "redisTemplateJackson")
    // public RedisTemplate<String, Object> redisTemplateJackson(RedisConnectionFactory factory) {
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     //key序列化
    //     RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    //     template.setConnectionFactory(factory);
    //
    //     //key序列化方式
    //     template.setKeySerializer(redisSerializer);
    //     //value序列化
    //     template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    //
    //     template.afterPropertiesSet();
    //     return template;
    // }

    @Bean
    // public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
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

    @Bean
    public ValueOperations<String, Serializable> valueOperations(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Serializable> listOperations(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public HashOperations<String, String, Serializable> hashOperations(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public SetOperations<String, Serializable> setOperations(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Serializable> zSetOperations(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    @Bean
    public HyperLogLogOperations<String, Serializable> hyperLogLogOperations(RedisTemplate<String, Serializable> redisTemplate) {
        return redisTemplate.opsForHyperLogLog();
    }

    // @Bean
    // public Long bitMapOperations(RedisTemplate<String, Serializable> redisTemplate) {
    //     return redisTemplate.execute(new RedisCallback<Long>() {
    //         @Override
    //         public Long doInRedis(RedisConnection connection) throws DataAccessException {
    //             // return connection.bitCount();
    //             // return connection.bitOp();
    //             // return connection.bitPos();
    //             // return connection.bitField();
    //             // return connection.getBit();
    //             // return connection.setBit();
    //             return 1L;
    //         }
    //     });
    // }
    //
    // @Bean
    // public List<Object> pipelined(RedisTemplate<String, Serializable> redisTemplate) {
    //     return redisTemplate.executePipelined(new RedisCallback<Long>() {
    //         @Override
    //         public Long doInRedis(RedisConnection connection) throws DataAccessException {
    //             Long aLong = connection.dbSize();
    //             connection.discard();
    //             return aLong;
    //         }
    //     });
    // }
}