package com.xuegao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * <br/> @PackageName：com.fff.redistemplate_test.utils
 * <br/> @ClassName：AppRunner
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/4/30 9:23
 */
@Component
public class AppRunner implements CommandLineRunner, Order {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

    // private final IRedisService redisService;
    //
    // public AppRunner(IRedisService redisService){
    //     this.redisService = redisService;
    // }

    @Override
    public void run(String... args) {
        LOGGER.info(".... Fetching books");
        // redisService.getUserInfoById(1341128242626592L);
        // redisService.getUserInfoById(1341128244723744L);
        // redisService.getUserInfoById(1341128232140832L);
        // redisService.getUserInfoById(1341128240529440L);
        // redisService.getUserInfoById(1341128244723776L);
        LOGGER.info("isbn-1234 -->");
        LOGGER.info("isbn-4567 -->");
        LOGGER.info("isbn-1234 -->");
        LOGGER.info("isbn-4567 -->");
        LOGGER.info("isbn-1234 -->");
    }

    @Override
    public int value() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}