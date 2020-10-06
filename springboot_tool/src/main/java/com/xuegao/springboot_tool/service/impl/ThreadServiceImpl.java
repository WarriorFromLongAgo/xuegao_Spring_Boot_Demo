package com.xuegao.springboot_tool.service.impl;

import com.xuegao.springboot_tool.model.bo.CallCdr;
import com.xuegao.springboot_tool.service.interfaces.IThreadService;
import com.xuegao.springboot_tool.utils.RedisConvertUtils;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：ThreadServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/17 11:26
 */
@Service
public class ThreadServiceImpl implements IThreadService {

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

    @Override
    public void delayedQueueByRedissonClientOffer() {
        RBlockingQueue<CallCdr> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");
        RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);

        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 一分钟以后将消息发送到指定队列
        // 相当于1分钟后取消订单
        // 延迟队列包含callCdr 1分钟，然后将其传输到blockingFairQueue中
        // 在1分钟后就可以在blockingFairQueue 中获取callCdr了
        CallCdr callCdr = new CallCdr(30000.00);
        callCdr.setPutTime();
        delayedQueue.offer(callCdr, 1, TimeUnit.MINUTES);
    }

    @Override
    public void delayedQueueByRedissonClientTake() {
        RBlockingQueue<CallCdr> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");
        RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        while (true) {
            CallCdr callCdr = null;
            try {
                callCdr = blockingFairQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订单取消时间：" + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "==订单生成时间" + callCdr.getPutTime());

        }
    }
}