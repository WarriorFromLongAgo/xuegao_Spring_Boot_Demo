package com.xuegao.springboot_tool.spring.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask2.class);

    // @Scheduled(cron = " 59 59 9 * * ? ")
    // @Scheduled(cron = " 59 59 14 * * ? ")
    @Scheduled(cron = " 0 7 14 * * ? ")
    public void doTask1() {
        // 2020-12-11 14:07:00.003 [INFO ] com.xuegao.springboot_tool.spring.schedule.ScheduledTask2.doTask1(ScheduledTask2.java:29) -  执行时间： 2020/12/11 14:07:00
        log.info(" 执行时间： {} ", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
    }
}