package com.xuegao.springboot_tool.utils;// package com.fff.my_blog.utils;
//
// import io.lettuce.core.RedisClient;
// import io.lettuce.core.RedisFuture;
// import io.lettuce.core.RedisURI;
// import io.lettuce.core.api.async.RedisAsyncCommands;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.connection.RedisStringCommands;
// import org.springframework.data.redis.connection.ReturnType;
// import org.springframework.data.redis.core.RedisCallback;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.core.types.Expiration;
// import org.springframework.data.redis.support.atomic.RedisAtomicLong;
// import org.springframework.stereotype.Component;
//
// import javax.annotation.PostConstruct;
// import java.nio.charset.Charset;
// import java.time.Duration;
// import java.util.concurrent.ExecutionException;
// import java.util.concurrent.TimeUnit;
//
// /**
//  * <br/> @PackageName：com.fff.auto_increment.utils
//  * <br/> @ClassName：RedisUtil
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2020/4/6 23:07
//  */
// @Component
// @Slf4j
// public class RedisUtils {
//
//     @Autowired
//     private RedisTemplate<String, String> redisTemplate;
//
//     /**
//      * 维护一个本类的静态变量
//      */
//     private RedisUtils redisUtils;
//
//     @PostConstruct
//     public void init() {
//         redisUtils = this;
//         redisUtils.redisTemplate = this.redisTemplate;
//     }
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
//
//     /**
//      * <br/> @Title:
//      * <br/> @MethodName:  getAuto
//      * <br/> @Return java.lang.Long
//      * <br/> @Description: 获取自增的id
//      * <br/> @author: fjm
//      * <br/> @date:  2020/4/6 23:27
//      */
//     public Long getAuto(String key) {
//         // http://localhost:8080/order1?key=1
//         // http://localhost:8080/order?key=1
//         // RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
//         RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
//         long l = redisAtomicLong.incrementAndGet();
//         System.out.println(l);
//         long l2 = redisAtomicLong.get();
//         System.out.println(l2);
//         return l;
//     }
//
//
//     private static final String UNLOCK_LUA;
//
//     /**
//      * 释放锁脚本，原子操作
//      */
//     static {
//         StringBuilder sb = new StringBuilder();
//         sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
//         sb.append("then ");
//         sb.append("    return redis.call(\"del\",KEYS[1]) ");
//         sb.append("else ");
//         sb.append("    return 0 ");
//         sb.append("end ");
//         UNLOCK_LUA = sb.toString();
//     }
//
//     /**
//      * 获取分布式锁，原子操作
//      *
//      * @param lockKey
//      * @param requestId 唯一ID, 可以使用UUID.randomUUID().toString();
//      * @param expire
//      * @param timeUnit
//      * @return
//      */
//     public boolean tryLock(String lockKey, String requestId, long expire, TimeUnit timeUnit) {
//         try {
//             RedisCallback<Boolean> callback = (connection) -> {
//                 return connection.set(
//                         lockKey.getBytes(Charset.forName("UTF-8")),
//                         requestId.getBytes(Charset.forName("UTF-8")),
//                         Expiration.seconds(timeUnit.toSeconds(expire)),
//                         RedisStringCommands.SetOption.SET_IF_ABSENT);
//             };
//             return (Boolean) redisTemplate.execute(callback);
//         } catch (Exception e) {
//             log.error("redis lock error.", e);
//         }
//         return false;
//     }
//
//     /**
//      * 释放锁
//      *
//      * @param lockKey
//      * @param requestId 唯一ID
//      * @return
//      */
//     public boolean releaseLock(String lockKey, String requestId) {
//         RedisCallback<Boolean> callback = (connection) -> {
//             return connection.eval(
//                     UNLOCK_LUA.getBytes(),
//                     ReturnType.BOOLEAN,
//                     1,
//                     lockKey.getBytes(Charset.forName("UTF-8")),
//                     requestId.getBytes(Charset.forName("UTF-8")));
//         };
//         return (Boolean) redisTemplate.execute(callback);
//     }
//
//     /**
//      * 获取Redis锁的value值
//      *
//      * @param lockKey
//      * @return
//      */
//     public String get(String lockKey) {
//         try {
//             RedisCallback<String> callback = (connection) -> {
//                 return new String(connection.get(lockKey.getBytes()), Charset.forName("UTF-8"));
//             };
//             return (String) redisTemplate.execute(callback);
//         } catch (Exception e) {
//             log.error("get redis occurred an exception", e);
//         }
//         return null;
//     }
// }