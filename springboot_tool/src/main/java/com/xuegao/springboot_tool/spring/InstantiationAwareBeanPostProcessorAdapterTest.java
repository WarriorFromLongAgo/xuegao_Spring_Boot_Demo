package com.xuegao.springboot_tool.spring;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：InstantiationAwareBeanPostProcessorAdapterTest
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/19 17:46
 */
@Component
public class InstantiationAwareBeanPostProcessorAdapterTest extends InstantiationAwareBeanPostProcessorAdapter {

    public InstantiationAwareBeanPostProcessorAdapterTest() {
        super();
    }

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        return super.predictBeanType(beanClass, beanName);
    }

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        return super.determineCandidateConstructors(beanClass, beanName);
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        return super.getEarlyBeanReference(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println(" InstantiationAwareBeanPostProcessorAdapterTest postProcessAfterInstantiation ");

        ReflectionUtils.doWithLocalMethods(bean.getClass(), new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                ReceiveAnno anno = method.getAnnotation(ReceiveAnno.class);
                if (anno == null) {
                    return;
                }
                Class clz = anno.clz();
                try {
                    if (!IEvent.class.isInstance(clz.newInstance())) {
                        FormattingTuple message = MessageFormatter.format("{}没有实现IEvent接口", clz);
                        throw new RuntimeException(message.getMessage());
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                EventContainer.addEventToMap(clz, method, bean);
            }
        });


        return super.postProcessAfterInstantiation(bean, beanName);
    }
}
