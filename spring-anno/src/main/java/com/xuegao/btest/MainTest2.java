package com.xuegao.btest;

import com.xuegao.atest.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * <br/> @PackageName：com.xuegao.btest.config
 * <br/> @ClassName：MainTest2
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:23
 */
public class MainTest2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) context.getBean("person01");
        Person person01 = (Person) context.getBean("person01");
        Person person02 = (Person) context.getBean("person022");
        System.out.println(person);
        System.out.println("person01 = " + person01);
        System.out.println("person022 = " + person02);

        // 获取到的是，方法的名称
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        System.out.println(Arrays.asList(beanNamesForType));
    }
}