package com.xuegao.springboot_tool.utils;

import redis.clients.jedis.Jedis;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：JedisUtil
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/15 11:44
 */
public class JedisUtilLockTest1 {

    private static Jedis jedis = new Jedis("localhost");

    public static void main(String[] args) {

    }

    private static void test() {
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
        // bar
    }

    // jedis 加锁
    // private static boolean lock(String id) {
    //     long startTime = System.currentTimeMillis();
    //
    //     try {
    //         for (; ; ) {
    //             // String lock = jedis.set("LOCK_KEY", id, params);
    //             if ("OK".equals(lock)) {
    //                 return true;
    //             }
    //             //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
    //             long diff = System.currentTimeMillis() - startTime;
    //             if (diff >= timeOut) {
    //                 return false;
    //             }
    //
    //             try {
    //                 Thread.sleep(100);
    //             } catch (InterruptedException e) {
    //                 e.printStackTrace();
    //             }
    //
    //         }
    //     } finally {
    //         jedis.close();
    //     }
    // }

    // 解锁
    // public boolean unLock(String id) {
    //
    //     String script = "" +
    //             " if redis.call('get',KEYS[1]) == ARGV[1] then " +
    //             "   return redis.call('del',KEYS[1]) " +
    //             " else " +
    //             "   return 0 " +
    //             " end ";
    //     try {
    //         String result = jedis.eval(script, Collections.singletonList("LOCK_KEY"), Collections.singletonList(id)).toString();
    //         return "1".equals(result);
    //     } finally {
    //         jedis.close();
    //     }
    // }

}

class TimeServcie {
    private static long time;

    public static long getTime() {
        return time;
    }

    public static void setTime(long time) {
        TimeServcie.time = time;
    }

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long timeMillis = System.currentTimeMillis();
                    setTime(timeMillis);
                }
            }
        }).start();
    }
}