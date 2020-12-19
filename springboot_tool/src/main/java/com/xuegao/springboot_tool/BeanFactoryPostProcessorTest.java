package com.xuegao.springboot_tool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：BeanFactoryPostProcessorTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/17 12:29
 */
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(" BeanFactoryPostProcessorTest ");

        // Spring常用BeanFactoryPostProcessor
        // spring也提供了不少BeanFactoryPostProcessor的实现，最常用的几个：
        // PropertySourcesPlaceholderConfigurer 、 CustomerEditorConfigurer、 PropertyOverrideConfigurer等。

        // 【PropertyPlaceholderConfigurer】可以从属性文件、属性类获取属性
        // 【PreferencesPlaceholderConfigurer】对属性来源具有优先级顺序
        // 【PropertySourcesPlaceholderConfigurer 】提供了比PropertyPlaceholderConfigurer更强大的功能，
        // 可以从属性文件、属性类、环境、PropertySources等获取属性（Spring3.1以后推荐使用这个类）
        // 【PropertyOverrideConfigurer】可以读取带有bean id的属性直接向bean注入属性，而不需要配置

        // DataSource dataSource = beanFactory.getBean("dataSource", DataSource.class);
        // DataSource DruidDataSource = beanFactory.getBean("DruidDataSource", DruidDataSource.class);
        // DruidStatProperties DruidStatProperties = beanFactory.getBean("DruidStatProperties", DruidStatProperties.class);

    }
}