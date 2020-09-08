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
        // 栈内存溢出
        // stackOverflowError();
        // 堆内存溢出
        // outOfMemoryError();
        // CPU 占用高
        threadWhile();
    }

    List<String> strList = new ArrayList<>();

    public void outOfMemoryError() {
        try {
            while (true) {
                strList.add(" outOfMemoryError ");
            }
        } catch (Exception e) {
            // java.lang.OutOfMemoryError: Java heap space
            e.printStackTrace();
        }
    }

    int i = 0;

    public void stackOverflowError() {
        LOGGER.error(" stackOutOf = " + (++i));
        stackOverflowError();
    }

    public void threadWhile() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CPU();
                }
            }).start();
        }
    }

    public void CPU() {
        while (true) {

        }
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