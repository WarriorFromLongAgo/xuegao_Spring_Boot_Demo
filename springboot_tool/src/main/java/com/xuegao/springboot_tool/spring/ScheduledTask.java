package com.xuegao.springboot_tool.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.spring
 * <br/> @ClassName：ScheduledTask
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/8/27 13:40
 */
@Component
public class ScheduledTask {
    // cron 表达式
    // https://cron.qqe2.com/

    private final static Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Scheduled(cron = "0 0/1 * * * ?")
    public void doTask1() {
        log.debug(" {} 扫描过期Token", LocalDateTime.now());
    }
}