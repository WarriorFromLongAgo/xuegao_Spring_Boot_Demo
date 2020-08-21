package com.xuegao.springboot_tool.spring.beanload;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public class DatasourceLoader {

    private String mode;

    public DatasourceLoader(Environment environment) {
        this.mode = environment.getProperty("config.save.mode");
        System.out.println("init DatasourceLoader for:" + mode);
    }

    @PostConstruct
    public void loadResourcres() {
        System.out.println("开始初始化资源");
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

