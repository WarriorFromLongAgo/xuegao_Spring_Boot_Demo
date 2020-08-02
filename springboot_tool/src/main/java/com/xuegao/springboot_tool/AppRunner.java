package com.xuegao.springboot_tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void run(String... args) {

        LOGGER.info("=========================================");
        LOGGER.info("=========================================");
        LOGGER.info("=========================================");

        List<String> str = new ArrayList<>();
        System.out.println("内存 和 CPU分界");
        new Thread(() -> {
            int result = 0;
            while (true) {
                String s = new byte[1000000000].toString() + "=" + result;
                System.out.println(s);
                str.add(s);
                System.out.println("str = " + str.size());
                Runtime rt = Runtime.getRuntime();
                System.out.println("freeMemory = " + rt.freeMemory() / (1024 * 1024));
                System.out.println("maxMemory = " + rt.maxMemory() / (1024 * 1024));
                System.out.println("totalMemory = " + rt.totalMemory() / (1024 * 1024));
                result++;
                if (result > Integer.MAX_VALUE / 2) {
                    result = 0;
                }
            }
        }).start();
        LOGGER.info("=========================================");
        LOGGER.info("=========================================");
        LOGGER.info("=========================================");
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