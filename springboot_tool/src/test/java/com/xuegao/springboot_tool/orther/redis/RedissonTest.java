package com.xuegao.springboot_tool.orther.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.LongCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.orther.redis
 * <br/> @ClassName：RedissonTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/10/23 17:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void redissonTest() {
        redissonClient.getBucket("aaaaaaAAAaa1313", LongCodec.INSTANCE).set(1L, getSeconds(), TimeUnit.SECONDS);
        String incrStr = "incr";
        // Object incr = redissonClient.getBucket(incrStr).get();
        Object o = redissonClient.getBucket(incrStr, LongCodec.INSTANCE).get();
        System.out.println(o);
        long l = redissonClient.getAtomicLong(incrStr).incrementAndGet();
        System.out.println(l);
        if (l == 1L) {
            redissonClient.getBucket(incrStr, LongCodec.INSTANCE).set(1L, getSeconds(), TimeUnit.SECONDS);
        }
        System.out.println(l);

    }

    /**
     * 获取当前时间距离第二天0点还有多少秒  用来设置key过期时间
     */
    private int getSeconds() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }


}