package com.xuegao.btest;

import com.xuegao.atest.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br/> @PackageName：com.xuegao.btest.config
 * <br/> @ClassName：MainConfig
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:20
 */
@Configuration
public class MainConfig {

    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("fjmconfig");
        person.setAge(24);
        return person;
    }

    @Bean
    public Person person01() {
        Person person = new Person();
        person.setName("fjmconfig");
        person.setAge(24);
        return person;
    }

    @Bean(name = "person022")
    public Person person02() {
        Person person = new Person();
        person.setName("fjmconfig");
        person.setAge(24);
        return person;
    }

}