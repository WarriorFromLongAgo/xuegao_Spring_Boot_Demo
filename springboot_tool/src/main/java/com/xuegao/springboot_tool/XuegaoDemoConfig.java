package com.xuegao.springboot_tool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class XuegaoDemoConfig {

// --> instantiate
// --> BeanPostProcessor.postProcessBeforeInitialization
// --> @PostConstruct
// --> InitializingBean.afterPropertiesSet
// --> custom initMehotd
// --> BeanPostProcessor.postProcessAfterInitialization

    // package org.springframework.beans.factory.support;
    // initializeBean 主要是这个类的这个方法的作用
    // 执行BeanPostProcessor.postProcessBeforeInitialization
    // Object wrappedBean = wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
    // 执行用户自定义初始化and JSR 250 定义的方法
    // invokeInitMethods(beanName, wrappedBean, mbd);
    // 执行执行BeanPostProcessor.postProcessAfterInitialization
    // wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
    // return wrappedBean;
    // 作者：冷冷zz
    // 链接：https://juejin.im/post/6844904182822993928
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    @Bean(initMethod = "initMethod")
    public XuegaoInitializingBean demoBean() {
        return new XuegaoInitializingBean();
    }
}
