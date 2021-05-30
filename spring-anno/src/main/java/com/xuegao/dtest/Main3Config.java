package com.xuegao.dtest;

import com.xuegao.atest.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <br/> @PackageName：com.xuegao.dtest
 * <br/> @ClassName：Main3Config
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 19:50
 */
@Configuration
public class Main3Config {

    // 在getbean用到的时候，创建
    // @Scope(scopeName = "prototype")
    // 在容器创建的时候，创建
    @Scope(scopeName = "singleton")
    @Bean
    public Person person3() {
        Person person = new Person();
        person.setName("person3d");
        person.setAge(24);
        return person;
    }

}