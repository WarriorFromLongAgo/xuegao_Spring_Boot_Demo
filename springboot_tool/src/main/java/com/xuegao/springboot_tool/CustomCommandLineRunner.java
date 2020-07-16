package com.xuegao.springboot_tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        log.info("CustomCommandLineRunner runs...");
    }
}

