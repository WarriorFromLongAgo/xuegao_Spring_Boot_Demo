package com.xuegao.springboot_tool.orther.redis;

import com.xuegao.springboot_tool.model.doo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.orther.redis
 * <br/> @ClassName：RedisTemplateTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/1 17:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    private final static Logger log = LoggerFactory.getLogger(RedisTemplateTest.class);

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void redisTemplateTest1() {
        redisTemplate.opsForValue().set("key", "value");
        Serializable value = redisTemplate.opsForValue().get("key");
        System.out.println(value);
        // value
    }

    @Test
    public void redisTemplateTest2() {
        Product product = new Product();
        product.setId(100L);
        product.setName("name");
        product.setPrice("100");
        product.setStocks(1000);
        product.setDescription("setDescription");
        redisTemplate.opsForValue().set("key", product);
        Serializable value = redisTemplate.opsForValue().get("key");
        System.out.println(value);
        // Product {id=100, name='name', price='100', stocks=1000, description='setDescription'}
    }

}