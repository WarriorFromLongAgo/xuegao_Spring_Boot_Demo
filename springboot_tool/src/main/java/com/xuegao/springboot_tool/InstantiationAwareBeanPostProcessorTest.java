package com.xuegao.springboot_tool;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：InstantiationAwareBeanPostProcessorTest
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/19 15:13
 */
public class InstantiationAwareBeanPostProcessorTest implements InstantiationAwareBeanPostProcessor {

    // 提供了抽象函数postProcessAfterInstantiation，在Spring容器实例bean后，
    // 会扫描所有继承了InstantiationAwareBeanPostProcessor的类，
    // 并且调用其中被重写的函数postProcessAfterInstantiation，
    // 因此我们可以将容器实例化bean对象后要实现的业务放在该函数中。

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(" InstantiationAwareBeanPostProcessorTest postProcessBeforeInstantiation  1111 ");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println(" InstantiationAwareBeanPostProcessorTest postProcessAfterInstantiation 2222 ");
        return false;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println(" InstantiationAwareBeanPostProcessorTest postProcessProperties 3333 ");
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println(" InstantiationAwareBeanPostProcessorTest postProcessPropertyValues 4444 ");
        return null;
    }
}