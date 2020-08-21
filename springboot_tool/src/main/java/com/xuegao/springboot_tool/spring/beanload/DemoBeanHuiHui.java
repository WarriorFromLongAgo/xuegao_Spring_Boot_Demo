package com.xuegao.springboot_tool.spring.beanload;

import org.springframework.stereotype.Component;

@Component
public class DemoBeanHuiHui {

    public DemoBeanHuiHui() {
        System.out.println("demo bean init!");
    }

    public void print() {
        System.out.println("print demo bean ");
    }
}
