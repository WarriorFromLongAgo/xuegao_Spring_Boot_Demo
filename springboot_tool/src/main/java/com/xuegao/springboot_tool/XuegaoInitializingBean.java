package com.xuegao.springboot_tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：XuegaoInitializingBean
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/16 15:48
 */
public class XuegaoInitializingBean implements InitializingBean {
    // Spring 框架提供了许多接口，可以使用这些接口来定制化 bean ，而非简单的 getter/setter 或者构造器注入。
    // 细翻 Spring Cloud Netflix、Spring Cloud Alibaba 等这些构建在 javax.swing.Spring Framework 的成熟框架源码，
    // 你会发现大量的扩展 bean 例如
    private static final Logger log = LoggerFactory.getLogger(XuegaoInitializingBean.class);

// --> instantiate
// --> BeanPostProcessor.postProcessBeforeInitialization
// --> @PostConstruct
// --> InitializingBean.afterPropertiesSet
// --> custom initMehotd
// --> BeanPostProcessor.postProcessAfterInitialization

    @PostConstruct
    public void postConstruct() {
        log.info("--> @PostConstruct ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("--> InitializingBean.afterPropertiesSet ");
    }

    public void initMethod() {
        log.info("--> custom initMehotd");
    }

    public XuegaoInitializingBean() {
        log.info("--> instantiate ");
    }
}