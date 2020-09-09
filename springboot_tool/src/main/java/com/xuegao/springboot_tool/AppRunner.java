package com.xuegao.springboot_tool;

import com.xuegao.springboot_tool.service.interfaces.IMyJvmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private IMyJvmService myJvmService;

    @Override
    public void run(String... args) {
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