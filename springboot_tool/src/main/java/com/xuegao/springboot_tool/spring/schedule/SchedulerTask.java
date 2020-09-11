package com.xuegao.springboot_tool.spring.schedule;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author:byteblogs
 * @Date:2018/09/27 12:52
 */
@Component
public class SchedulerTask {
    private final static Logger log = LoggerFactory.getLogger(SchedulerTask.class);

    // @Autowired
    // private AuthTokenDao authTokenDao;

    // @Async
    // @Scheduled(cron = "0/1 0/1 * * * ? ")
    // private void scanToken() {
        // log.info(" {} 扫描过期Token", LocalDateTime.now());
        // authTokenDao.delete(new LambdaQueryWrapper<AuthToken>().le(AuthToken::getExpireTime, LocalDateTime.now()));
    // }
}
