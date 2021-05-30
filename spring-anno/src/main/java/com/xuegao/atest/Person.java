package com.xuegao.atest;

import org.springframework.beans.factory.InitializingBean;

/**
 * <br/> @PackageName：com.xuegao
 * <br/> @ClassName：Person
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:12
 */
public class Person implements InitializingBean {

    private String name;
    private Integer age;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person afterPropertiesSet");
    }
}