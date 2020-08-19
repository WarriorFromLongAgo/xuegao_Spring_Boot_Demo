package com.xuegao.springboot_tool.spring;

import java.lang.annotation.*;

@Documented

@Retention
        (
                RetentionPolicy
                        .
                        RUNTIME
        )

@Target
        ({
                ElementType
                        .
                        METHOD
        })

public @interface

ReceiveAnno {


// 监听的事件


    Class
            clz
            ();

}