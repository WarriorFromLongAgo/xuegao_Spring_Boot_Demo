package com.xuegao.springboot2_3_security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.xuegao.springboot2_3_security.dao")
@SpringBootApplication
public class Springboot23SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot23SecurityApplication.class, args);
    }

}
