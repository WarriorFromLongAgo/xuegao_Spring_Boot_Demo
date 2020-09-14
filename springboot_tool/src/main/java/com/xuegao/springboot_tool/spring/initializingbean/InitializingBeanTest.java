package com.xuegao.springboot_tool.spring.initializingbean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.spring
 * <br/> @ClassName：InitializingBeanTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/21 17:48
 */

// 由结果可看出，在spring初始化bean的时候，如果该bean是实现了InitializingBean接口，并且同时在配置文件中指定了init-method，
//         系统则是先调用afterPropertiesSet方法，然后在调用init-method中指定的方法。
//
//         这方式在spring中是怎么实现的？
//
//         通过查看spring的加载bean的源码类(AbstractAutowireCapableBeanFactory)可看出其中奥妙
//
//         AbstractAutowireCapableBeanFactory类中的invokeInitMethods讲解的非常清楚，源码如下：

@Component
public class InitializingBeanTest implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ceshi InitializingBean");
    }

    public void testInit() {
        System.out.println("ceshi init-method");
    }
}