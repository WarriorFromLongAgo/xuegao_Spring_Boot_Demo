package com.xuegap.dtest;

import com.xuegao.atest.Person;
import com.xuegao.ctest.MainConfig;
import com.xuegao.dtest.Main3Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <br/> @PackageName：com.xuegap.ctest
 * <br/> @ClassName：TestC
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:36
 */
public class TestD {
    @Test
    public void test02() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main3Config.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        // 从容器中取两次
        Object person3 = context.getBean("person3");
        Object person33 = context.getBean("person3");
        // 同一块内存地址？
        System.out.println(person33 == person3);
    }

}