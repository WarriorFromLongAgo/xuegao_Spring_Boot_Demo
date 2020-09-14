package com.xuegao.springboot_tool.utils.redisson;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import org.redisson.api.RBitSet;
import org.redisson.api.RBucket;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommands;

import java.util.Date;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils.redisson
 * <br/> @ClassName：BitSet
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/14 15:40
 */
public class BitSet {

    // //某一天的用户活跃数
    // @Override
    // public long countUsers(String date) {
    //     Date dateKey = DateUtil.parse(date, DatePattern.PURE_DATE_PATTERN);
    //     String key = DateUtil.formatDate(dateKey);
    //     RBitSet bitSet = redissonClient.getBitSet(key);
    //     return bitSet.cardinality();
    // }
    //
    // // 内存消耗
    // @Override
    // public long mem(String date) {
    //     Date dateKey = DateUtil.parse(date, DatePattern.PURE_DATE_PATTERN);
    //     String key = DateUtil.formatDate(dateKey);
    //     RBucket bucket = redissonClient.getBucket(key);
    //     return bucket.sizeInMemory();
    // }
    //
    //
    // //某一天的某一个用户是否活跃
    // @Override
    // public boolean user(String date, Long id) {
    //     Date dateKey = DateUtil.parse(date, DatePattern.PURE_DATE_PATTERN);
    //     String key = DateUtil.formatDate(dateKey);
    //     RBitSet bitSet = redissonClient.getBitSet(key);
    //     return bitSet.get(id);
    // }
    //
    // //任意时间段内某一个用户是否活跃
    // @Override
    // public boolean user(String dateBegin, String dateEnd, Long id) {
    //     Date dateBeginObj = DateUtil.parse(dateBegin, DatePattern.PURE_DATE_PATTERN);
    //     Date dateEndObj = DateUtil.parse(dateEnd, DatePattern.PURE_DATE_PATTERN);
    //
    //     String resultKey = "RESULT";
    //
    //     List<String> luaArgs = Lists.newArrayList();
    //     luaArgs.add("OR");
    //     luaArgs.add(resultKey);
    //
    //     long cnt = DateUtil.betweenDay(dateBeginObj, dateEndObj, true);
    //     for (int i = 0; i <= cnt; i++) {
    //         Date currDate = DateUtil.offsetDay(dateBeginObj, i);
    //         String key = DateUtil.formatDate(currDate);
    //         luaArgs.add(key);
    //     }
    //
    //     redisConnection.sync(StringCodec.INSTANCE, RedisCommands.BITOP, luaArgs.toArray(new String[]{}));
    //
    //     RBitSet bitSet = redissonClient.getBitSet(resultKey);
    //     boolean result = bitSet.get(id);
    //     redissonClient.getKeys().delete(resultKey);
    //     return result;
    // }
    //
    // //任意时间段内用户活跃数
    // @Override
    // public long act(String dateBegin, String dateEnd) {
    //     Date dateBeginObj = DateUtil.parse(dateBegin, DatePattern.PURE_DATE_PATTERN);
    //     Date dateEndObj = DateUtil.parse(dateEnd, DatePattern.PURE_DATE_PATTERN);
    //
    //     String resultKey = "RESULT_" + System.currentTimeMillis();
    //
    //     List<String> luaArgs = Lists.newArrayList();
    //     luaArgs.add("OR");
    //     luaArgs.add(resultKey);
    //
    //     long cnt = DateUtil.betweenDay(dateBeginObj, dateEndObj, true);
    //     for (int i = 0; i <= cnt; i++) {
    //         Date currDate = DateUtil.offsetDay(dateBeginObj, i);
    //         String key = DateUtil.formatDate(currDate);
    //         luaArgs.add(key);
    //     }
    //
    //     redisConnection.sync(StringCodec.INSTANCE, RedisCommands.BITOP, luaArgs.toArray(new String[]{}));
    //
    //     RBitSet bitSet = redissonClient.getBitSet(resultKey);
    //     long result = bitSet.cardinality();
    //
    //     redissonClient.getKeys().delete(resultKey);
    //     return result;
    // }





}