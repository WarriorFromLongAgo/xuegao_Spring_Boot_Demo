package com.xuegao.springboot2_3_rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot23RabbitmqApplication {
    
    private static Logger log = LoggerFactory.getLogger(Springboot23RabbitmqApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Springboot23RabbitmqApplication.class, args);
    }

}
