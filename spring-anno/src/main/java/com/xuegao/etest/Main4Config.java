package com.xuegao.etest;

import com.xuegao.atest.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * <br/> @PackageName：com.xuegao.dtest
 * <br/> @ClassName：Main3Config
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 19:50
 */
@Configuration
public class Main4Config {

    @Lazy
    @Bean
    public Person personE() {
        Person person = new Person();
        person.setName("personE");
        person.setAge(24);
        System.out.println("添加 person");
        return person;
    }

}