package com.xuegao.springboot_tool.spring.beanload;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class ClientBeanProcessor extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("准备实例: " + beanName);

        return super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
        }
        // 上面的实现比较简单，借助beanFactory#getBean来手动触发 bean 的实例，通过实现BeanFactoryAware接口来获取BeanFactory，
        // 因为实现InstantiationAwareBeanPostProcessor接口的类会优先于 Bean 被实例，以此来间接的达到我们的目的

        System.out.println("----------------------------------------");
        System.out.println(" bean load -----------------------------------");
        System.out.println("----------------------------------------");

        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        // 通过主动调用beanFactory#getBean来显示实例化目标bean
        DatasourceLoader propertyLoader = this.beanFactory.getBean(DatasourceLoader.class);
        System.out.println(propertyLoader);
    }
}
