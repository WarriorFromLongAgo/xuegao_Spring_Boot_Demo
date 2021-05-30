package com.xuegap.ctest;

import com.xuegao.atest.Person;
import com.xuegao.ctest.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.java2d.pipe.SpanIterator;

import java.util.Arrays;

/**
 * <br/> @PackageName：com.xuegap.ctest
 * <br/> @ClassName：TestC
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:36
 */
public class TestC {
    @Test
    public void test02() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Person personC = (Person) context.getBean("personC");
        System.out.println(personC);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}