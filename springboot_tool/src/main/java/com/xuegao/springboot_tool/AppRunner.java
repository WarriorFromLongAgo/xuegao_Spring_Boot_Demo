package com.xuegao.springboot_tool;

import com.xuegao.springboot_tool.service.interfaces.IMyJvmService;
import com.xuegao.springboot_tool.spring.schedule.SchedulerTask;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * <br/> @PackageName：com.fff.redistemplate_test.utils
 * <br/> @ClassName：AppRunner
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/4/30 9:23
 */
@Component
public class AppRunner implements CommandLineRunner, Order {

    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    // @Autowired
    // private SchedulerTask schedulerTask;

    @Override
    public void run(String... args) {
        // ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        //     @Override
        //     public Thread newThread(@NotNull Runnable runnable) {
        //         Thread thread = new Thread(runnable);
        //         thread.setName(" scheduledThreadPoolExecutor 1 ");
        //         return thread;
        //     }
        // });
        // scheduledThreadPoolExecutor.schedule(new Runnable() {
        //     @Override
        //     public void run() {
        //         schedulerTask.scanToken();
        //     }
        // }, 1, TimeUnit.SECONDS);
        log.info("AppRunner   CommandLineRunner Order Integer.MAX_VALUE ");
    }

    @Override
    public int value() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}