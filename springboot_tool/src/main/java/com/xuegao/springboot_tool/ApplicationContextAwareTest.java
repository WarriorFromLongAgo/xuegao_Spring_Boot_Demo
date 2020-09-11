package com.xuegao.springboot_tool;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：ApplicationContextAwareTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/19 9:49
 */
public class ApplicationContextAwareTest implements ApplicationContextAware {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ApplicationContextAwareTest.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // 从Employee类的源码可以看出，实现ApplicationContextAware接口的话，需要实现setApplicationContext()方法。
    // 在IOC容器启动并创建Employee对象时，Spring会调用setApplicationContext()方法，
    // 并且会将ApplicationContext对象传入到setApplicationContext()方法中，
    // 我们只需要在Employee类中定义一个ApplicationContext类型的成员变量来接收setApplicationContext()方法的参数，
    // 就可以使用ApplicationContext对象了。


}