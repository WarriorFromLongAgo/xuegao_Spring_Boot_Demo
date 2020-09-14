package com.xuegao.springboot_tool.spring.conditionbean.example.expression;

/**
 * Created by @author yihui in 10:36 18/10/18.
 */
public class ExpressFalseBean {
    private String name;

    public ExpressFalseBean(String name) {
        this.name = name;
    }

    public String getName() {
        return "express bean ：" + name;
    }
}
