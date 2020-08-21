package com.xuegao.springboot_tool.spring.beanload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ClientAutoConfiguration {
    @Bean
    public DatasourceLoader propertyLoader(Environment environment) {
        return new DatasourceLoader(environment);
    }
}

