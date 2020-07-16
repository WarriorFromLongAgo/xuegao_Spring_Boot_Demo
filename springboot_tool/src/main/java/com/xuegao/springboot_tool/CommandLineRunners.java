package com.xuegao.springboot_tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunners {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> log.info("CommandLineRunners commandLineRunner");
    }
}

