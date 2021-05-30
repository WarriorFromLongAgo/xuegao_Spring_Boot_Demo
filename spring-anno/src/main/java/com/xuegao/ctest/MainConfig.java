package com.xuegao.ctest;

import com.xuegao.atest.Person;
import com.xuegao.ctest.service.ComponentScanFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * <br/> @PackageName：com.xuegao.ctest
 * <br/> @ClassName：MainConfig
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 18:32
 */
@Configuration
// @ComponentScan(basePackages = "com/xuegao/ctest")
@ComponentScan(basePackages = "com/xuegao/ctest", includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {ComponentScanFilter.class})
}, useDefaultFilters = false)
// @ComponentScan(basePackages = "com/xuegao/ctest", includeFilters = {
//         @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
// }, useDefaultFilters = false)
public class MainConfig {

    @Bean
    public Person personC() {
        Person person = new Person();
        person.setName("cperson");
        person.setAge(24);
        return person;
    }

}