package com.xuegao.atest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <br/> @PackageName：com.xuegao
 * <br/> @ClassName：MainTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:15
 */
public class MainTest {
    // private static ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) context.getBean("person");
        System.out.println(person);
    }
}