package com.xuegao.springboot_tool.utils.redisson;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import org.redisson.api.RBitSet;
import org.redisson.api.RBucket;
import org.redisson.api.RHyperLogLog;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils.redisson
 * <br/> @ClassName：HyLog
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/14 15:40
 */
public class HyLog {

    // // 某一天的用户活跃数
    // @Override
    // public long countUsers(String date) {
    //     Date dateKey = DateUtil.parse(date, DatePattern.PURE_DATE_PATTERN);
    //     String key = DateUtil.formatDate(dateKey);
    //     RHyperLogLog hyperLogLog = redissonClient.getHyperLogLog(key);
    //     return hyperLogLog.count();
    // }
    //
    // // 内存消耗
    // @Override
    // public long mem(String date) {
    //     Date dateKey = DateUtil.parse(date, DatePattern.PURE_DATE_PATTERN);
    //     String key = DateUtil.formatDate(dateKey);
    //     RHyperLogLog hyperLogLog = redissonClient.getHyperLogLog(key);
    //     return hyperLogLog.sizeInMemory();
    // }
    //
    // //任意时间段内用户活跃数
    // @Override
    // public long act(String dateBegin, String dateEnd) {
    //     String resultKey = "RESULT_" + System.currentTimeMillis();
    //     RHyperLogLog hyperLogLog = redissonClient.getHyperLogLog(resultKey);
    //
    //     Date dateBeginObj = DateUtil.parse(dateBegin, DatePattern.PURE_DATE_PATTERN);
    //     Date dateEndObj = DateUtil.parse(dateEnd, DatePattern.PURE_DATE_PATTERN);
    //     long cnt = DateUtil.betweenDay(dateBeginObj, dateEndObj, true);
    //     List<String> keyList = new ArrayList<>();
    //     for (int i = 0; i <= cnt; i++) {
    //         Date currDate = DateUtil.offsetDay(dateBeginObj, i);
    //         String key = DateUtil.formatDate(currDate);
    //         keyList.add(key);
    //     }
    //
    //     hyperLogLog.mergeWith(keyList.toArray(new String[]{}));
    //     long result = hyperLogLog.count();
    //     redissonClient.getKeys().delete(resultKey);
    //     return result;
    // }
}