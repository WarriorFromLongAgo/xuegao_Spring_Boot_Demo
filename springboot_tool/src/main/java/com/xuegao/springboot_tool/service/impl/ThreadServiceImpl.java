package com.xuegao.springboot_tool.service.impl;

import com.xuegao.springboot_tool.model.bo.CallCdr;
import com.xuegao.springboot_tool.mvc.exception.BusinessException;
import com.xuegao.springboot_tool.service.interfaces.IThreadService;
import com.xuegao.springboot_tool.utils.RedisConvertUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：ThreadServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/17 11:26
 */
@Service
public class ThreadServiceImpl implements IThreadService {
    private Logger log = LoggerFactory.getLogger(ThreadServiceImpl.class);

    static Lock lock = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    static ReentrantLock reentrantLock = new ReentrantLock();

    private static final Integer THUMBS_UP = 1;

    private ValueOperations<String, Serializable> valueOperations;
    private ZSetOperations<String, Serializable> zSetOperations;
    private RedissonClient redissonClient;

    @Autowired
    public ThreadServiceImpl(ValueOperations<String, Serializable> valueOperations,
                             ZSetOperations<String, Serializable> zSetOperations,
                             RedissonClient redissonClient) {
        this.valueOperations = valueOperations;
        this.zSetOperations = zSetOperations;
        this.redissonClient = redissonClient;
    }

    /**
     * <br/> @Title: 给一个微博点赞，或者取消点赞
     * <br/> @MethodName:  giveThumbsUpService
     * <br/> @param giveUserId:
     * <br/> @param articleId:
     * <br/> @param thumbsUpFlag:
     * <br/> @return java.lang.Boolean
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:30
     */
    @Override
    public Boolean giveThumbsUpService(Long giveUserId, Long articleId, Integer thumbsUpFlag) {
        // SysUserinfo sysUserinfo1 = new SysUserinfo();
        // sysUserinfo1.setId(1L);
        // sysUserinfo1.setName("set");
        // sysUserinfo1.setUsername("setusername");
        // valueOperations.set("set", sysUserinfo1);
        // SysUserinfo sysUserinfo = (SysUserinfo) valueOperations.get("set");
        // System.out.println(sysUserinfo);

        // Long zset = zSetOperations.count("zset", 99, 102);
        // System.out.println(zset);
        // Set<Serializable> zset1 = zSetOperations.rangeByScore("zset", 99, 102);
        // if (ObjectUtils.isNotEmpty(zset1)) {
        //     for (Serializable serializable : zset1) {
        //         SysUserinfo sysUserinfoTemp = (SysUserinfo) serializable;
        //         System.out.println(sysUserinfoTemp);
        //     }
        // }

        // thumbsUpFlag 等于 点赞
        if (THUMBS_UP.equals(thumbsUpFlag)) {
            // 用户的点赞列表
            zSetOperations.add(Long.toString(giveUserId), articleId, System.currentTimeMillis());
            // 文章的点赞列表
            zSetOperations.add(Long.toString(articleId), giveUserId, System.currentTimeMillis());
        } else {
            // 用户的点赞列表 删除
            zSetOperations.remove(Long.toString(giveUserId), articleId);
            // 文章的点赞列表删除
            zSetOperations.remove(Long.toString(articleId), giveUserId);
        }

        // 异步入库的操作
        // 添加到数据库
        // 重试三次，入库失败，打日志，发信息，发邮件
        return true;
    }

    /**
     * <br/> @Title: 一个微博的点赞人的列表，暂时不做分页了
     * <br/> @MethodName:  thumbsUpListByArticleIdService
     * <br/> @param requestUserId:
     * <br/> @param articleId:
     * <br/> @return java.util.List<java.lang.Long>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:29
     */
    @Override
    public List<Long> thumbsUpListByArticleIdService(Long requestUserId, Long articleId) {
        Set<Serializable> range = zSetOperations.reverseRange(Long.toString(articleId), 0, 1000);
        List<Long> longList = RedisConvertUtils.fastJsonDeserializeToSet(range, Long.class);
        // 里面存的是 文章的点赞人的id列表，去其他缓存里面获取这个人的信息就可以了。
        return longList;
    }

    /**
     * <br/> @Title: 一个人的点赞微博列表，暂时不做分页了。
     * <br/> @MethodName:  thumbsUpListByUserIdService
     * <br/> @param requestUserId:
     * <br/> @return java.util.List<java.lang.Long>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:28
     */
    @Override
    public List<Long> thumbsUpListByUserIdService(Long requestUserId) {
        Set<Serializable> range = zSetOperations.reverseRange(Long.toString(requestUserId), 0, 1000);
        // Set<Serializable> range = zSetOperations.rangeByLex(Long.toString(requestUserId), RedisZSetCommands.Range.range().)
        List<Long> longList = RedisConvertUtils.fastJsonDeserializeToSet(range, Long.class);
        // 里面存的是 人的点赞文章的id列表，去其他缓存里面获取这个人的信息就可以了。
        return longList;
    }

    /**
     * <br/> @Title: 一个文章有多少人点赞，返回的是 userid，按照时间顺序
     * <br/> @MethodName:  thumbsUpListCountService
     * <br/> @param articleId:
     * <br/> @return java.lang.Long
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:28
     */
    @Override
    public Long thumbsUpListCountService(Long articleId) {
        Long aLong = zSetOperations.zCard(Long.toString(articleId));
        return aLong;
    }

    /**
     * <br/> @Title: redisson 延迟队列
     * <br/> @MethodName:  delayedQueueByRedissonClientTake
     * <br/> take：获取并移除队列的超时元素，如果没有则wait当前线程，直到有元素满足超时条件，返回结果。
     * <br/> @return void
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/10/09 19:58
     */
    @Override
    public void delayedQueueByRedissonClientTake() {
        RBlockingQueue<CallCdr> blockingFairQueue = redissonClient.getBlockingQueue("redisson_delay_queue");
        RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        new Thread(new Runnable() {
            @Override
            public void run() {
                CallCdr callCdr = null;
                try {
                    callCdr = blockingFairQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ObjectUtils.isNotEmpty(callCdr)) {
                    System.out.println("订单取消时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) + "==订单生成时间：" + callCdr.getPutTime());
                }
            }
        }).start();
    }

    public void delayedQueueByRedissonClientPoll() {
        RBlockingQueue<CallCdr> blockingFairQueue = redissonClient.getBlockingQueue("redisson_delay_queue");
        RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        new Thread(new Runnable() {
            @Override
            public void run() {
                CallCdr callCdr = blockingFairQueue.poll();
                if (ObjectUtils.isNotEmpty(callCdr)) {
                    System.out.println("订单取消时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) + "==订单生成时间：" + callCdr.getPutTime());
                }
            }
        }).start();
    }

    @Override
    public void delayedQueueByRedissonClientOffer() {
        // 这里使用了两个queue，对delayedQueue的offer操作是直接进入delayedQueue，但是delay是作用在目标队列上，这里就是RBlockingQueue
        try {
            RBlockingDeque<CallCdr> stringRedissonDelayQueue = redissonClient.getBlockingDeque("redisson_delay_queue");
            RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(stringRedissonDelayQueue);
            CallCdr callCdr = new CallCdr();
            callCdr.setPutTime();
            callCdr.setSalary(100.0);
            callCdr.setZhiFu(false);
            delayedQueue.offer(callCdr, 30, TimeUnit.MINUTES);
            System.out.println("stringRedissonDelayQueue.contains(callCdr) = " + stringRedissonDelayQueue.contains(callCdr));
            TimeUnit.SECONDS.sleep(1);
            System.out.println("stringRedissonDelayQueue.contains(callCdr) = " + stringRedissonDelayQueue.contains(callCdr));
            TimeUnit.SECONDS.sleep(2);
            System.out.println("stringRedissonDelayQueue.contains(callCdr) = " + stringRedissonDelayQueue.contains(callCdr));
            TimeUnit.SECONDS.sleep(1);
            System.out.println("stringRedissonDelayQueue.contains(callCdr) = " + stringRedissonDelayQueue.contains(callCdr));

            // stringRedissonDelayQueue.contains(demo) = false
            // stringRedissonDelayQueue.contains(demo) = false
            // stringRedissonDelayQueue.contains(demo) = true
            // stringRedissonDelayQueue.contains(demo) = true

            // redisson的DelayedQueue使用上是将元素及延时信息入队，之后定时任务将到期的元素转移到目标队列
            // 这里使用了三个结构来存储，一个是目标队列list；一个是原生队列list，添加的是带有延时信息的结构体；一个是timeoutSetName的zset，元素是结构体，其score为timeout值
            // 作者：go4it
            // 链接：https://juejin.im/post/6844903683247833102
            // 来源：掘金
            // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reentrantLockTest(String userId) {
        System.out.println("reentrantLockTest userId 已进入一 = " + userId);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        if (lock2.tryLock()) {
            try {
                System.out.println("reentrantLockTest userId 已进入二 = " + userId);
                method11(userId);
                TimeUnit.SECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
                System.out.println("reentrantLockTest userId 已离开三 = " + userId);
            }
        }
    }

    public void method11(String userId) {
        lock.lock();
        try {
            System.out.println("reentrantLockTest1 userId 第二层 = " + userId);
            method22(userId);
        } finally {
            lock.unlock();
        }
    }

    public void method22(String userId) {

        lock.lock();
        try {
            System.out.println("reentrantLockTest1 userId 第三层 = " + userId);
        } finally {
            lock.unlock();
        }
    }

    // 可重入是指同一个线程如果首次获得了这把锁，那么因为它是这把锁的拥有者，因此有权利再次获取这把锁
    // 如果是不可重入锁，那么第二次获得锁时，自己也会被锁挡住
    // ReentrantLock和synchronized都是可重入锁。

    // 可打断是指在等待锁的过程中，其它线程可以用interrupt方法终止我的等待。synchronized锁是不可打断的。
    // 我们要想在等锁的过程中被打断，就要使用lockInterruptibly()方法对lock对象加锁，而不是lock()方法

    @Override
    public void reentrantLockTest2(String userId) {
        try {
            System.out.println("reentrantLockTest2 userId 已进入一 = " + userId);
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            lock.lock();
            System.out.println("reentrantLockTest2 userId 已进入二 = " + userId);
            method1(userId);
            TimeUnit.SECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("reentrantLockTest2 userId 已离开三 = " + userId);
        }
    }

    public void method1(String userId) {
        lock.lock();
        try {
            System.out.println("reentrantLockTest2 userId 第二层 = " + userId);
            method2(userId);
        } finally {
            lock.unlock();
        }
    }

    public void method2(String userId) {
        lock.lock();
        try {
            System.out.println("reentrantLockTest2 userId 第三层 = " + userId);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void reentrantReadWriteLockTest(String userId) {
        // Lock lock = new ReentrantReadWriteLock.WriteLock();

    }
}