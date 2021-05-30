package com.xuegap.etest;

import com.xuegao.etest.Main4Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <br/> @PackageName：com.xuegap.ctest
 * <br/> @ClassName：TestC
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:36
 */
public class TestE {
    @Test
    public void test02() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main4Config.class);
        System.out.println("ioc 创建完成");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        Object person3 = context.getBean("personE");
        System.out.println(person3);
    }

}