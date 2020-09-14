package com.xuegao.springboot_tool.spring.springfactory2.bean;

import com.xuegao.springboot_tool.spring.springfactory2.modal.SelfBean;
import lombok.extern.slf4j.Slf4j;

/**
 * 基于自定义注解进行加载的方式
 * Created by @author yihui in 09:10 18/9/29.
 */
@Slf4j
@SelfBean
public class AnoBean {
    public String sayHello(String name) {
        return "hello " + name;
    }

}
