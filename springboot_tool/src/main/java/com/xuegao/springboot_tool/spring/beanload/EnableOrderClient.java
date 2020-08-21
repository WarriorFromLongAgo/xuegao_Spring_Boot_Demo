package com.xuegao.springboot_tool.spring.beanload;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ClientAutoConfiguration.class, ClientBeanProcessor.class})
public @interface EnableOrderClient {
}
// 请注意上面的注解中，导入上面的自动配置类，和ClientBeanProcessor，所以上一节中的spring.factories文件可以不需要哦