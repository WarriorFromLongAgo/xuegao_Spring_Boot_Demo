package com.xuegao.springboot_tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：XuegaoBeanPostProcessor
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/16 15:49
 */
@Configuration
public class XuegaoBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(XuegaoBeanPostProcessor.class);

// --> instantiate
// --> BeanPostProcessor.postProcessBeforeInitialization
// --> @PostConstruct
// --> InitializingBean.afterPropertiesSet
// --> custom initMehotd
// --> BeanPostProcessor.postProcessAfterInitialization

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("demoBean".equals(beanName)) {
            log.info("--> BeanPostProcessor.postProcessBeforeInitialization ");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("demoBean".equals(beanName)) {
            log.info("--> BeanPostProcessor.postProcessAfterInitialization ");
        }
        return bean;
    }
}