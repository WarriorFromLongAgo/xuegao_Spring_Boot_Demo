package com.xuegao.springboot_tool.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：CaffeineUtil
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/28 19:59
 */
public class CaffeineUtil {
    private static Cache<String, String> caffeineCache = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build();

    public static void main(String[] args) {
        put("a", "111111");
        String a = get("a");
        System.out.println(a);

    }

    public static void put(String ticketNo, String ticket) {
        caffeineCache.put(ticketNo, ticket);
    }

    public static String get(String ticketNo) {
        return caffeineCache.getIfPresent(ticketNo);
    }
}