package com.xuegao.springboot_tool.spring;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.spring
 * <br/> @ClassName：ScheduledTask
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/8/27 13:40
 */
@EnableScheduling
public class ScheduledTask {
    // cron 表达式
    // https://cron.qqe2.com/

    @Scheduled(cron = "0/2 0/2 * * * ? ")
    public void doTask1() {
        System.out.println("我是定时任务1");
    }
}