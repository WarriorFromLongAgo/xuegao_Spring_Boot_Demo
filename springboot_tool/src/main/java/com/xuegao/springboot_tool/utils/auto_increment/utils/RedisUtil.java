// package com.xuegao.springboot_tool.utils.auto_increment.utils;
//
// import io.lettuce.core.RedisClient;
// import io.lettuce.core.RedisFuture;
// import io.lettuce.core.RedisURI;
// import io.lettuce.core.api.async.RedisAsyncCommands;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.support.atomic.RedisAtomicLong;
// import org.springframework.stereotype.Component;
//
// import java.time.Duration;
// import java.util.concurrent.ExecutionException;
//
// /**
//  * <br/> @PackageName：com.fff.auto_increment.utils
//  * <br/> @ClassName：RedisUtil
//  * <br/> @Description：
//  * <br/> @author：feijm
//  * <br/> @date：2020/4/6 23:07
//  */
// @Component
// public class RedisUtil {
//
//     @Autowired
//     private RedisTemplate redisTemplate;
//
//     private static RedisAsyncCommands<String, String> lettuce;
//     private static final String REDIS_HOST = "127.0.0.1";
//     private static final int REDIS_PORT = 6379;
//     private static final int REDIS_EXPIRE = 1;
//     private static final int REDIS_MAX_IDLE = 10;
//     private static final int REDIS_MAX_ACTIVE = 10;
//
//     static {
//         RedisURI redisURI = new RedisURI();
//         redisURI.setHost(REDIS_HOST);
//         redisURI.setPort(REDIS_PORT);
//         // redisURI.setPassword("");
//         redisURI.setTimeout(Duration.ofSeconds(2000));
// //        redisURI.setUnit(TimeUnit.SECONDS);
//         // 也可直接将url的字符串传入 RedisClient.create()方法中
//         // eg:redis://[password@]host[:port][/databaseNumber]
//         RedisClient client = RedisClient.create(redisURI); //
//         // 从redis客户端中获取一个异步的redis缓冲池
//         lettuce = client.connect().async();
// //        pool = client.asyncPool(REDIS_MAX_IDLE, REDIS_MAX_ACTIVE);
//         // 参数说明：REDIS_MAX_IDLE 为本缓冲池中最大闲置连接数量 // REDIS_MAX_ACTIVE为本缓冲池中最大活动连接数量
//     }
//
//
//     // 关闭服务器时 关闭缓冲池
//     public static void shutDown() {
//         lettuce.shutdown(true);
//     }
//
//     public String getStr(String key) throws ExecutionException, InterruptedException {
//         RedisFuture<String> stringRedisFuture = lettuce.get(key);
//         return stringRedisFuture.get();
//     }
//
//     public String setStr(String key, String value) throws ExecutionException, InterruptedException {
//         RedisFuture<String> set = lettuce.set(key, value);
//         return set.get();
//     }
//
//     public Long getAuto(String key) {
//         // http://localhost:8080/order1?key=1
//         // http://localhost:8080/order?key=1
//         // RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
//         RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
//         long l = redisAtomicLong.get();
//         return l;
//     }
// }