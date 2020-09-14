package com.xuegao.springboot_tool.spring.importselector.ordercase.bean.sub;

import org.springframework.stereotype.Component;

/**
 * Created by @author yihui in 11:30 19/12/14.
 */
@Component
public class SubDemo {

    private String name = "sub Demo";

    public SubDemo() {
        System.out.println(name);
    }
}
