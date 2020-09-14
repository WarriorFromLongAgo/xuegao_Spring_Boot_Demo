package com.xuegao.springboot_tool.spring.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.spring
 * <br/> @ClassName：ScheduledTask
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/27 13:40
 */
@Component
public class ScheduledTask2 {
    // cron 表达式
    // https://cron.qqe2.com/

    private final static Logger log = LoggerFactory.getLogger(ScheduledTask2.class);

    // @Async
    // @Scheduled(cron = "0/1 0/1 * * * ? ")
    // public void doTask1() {
    //     log.info(" {} 扫描过期Token", LocalDateTime.now());
    // }
}