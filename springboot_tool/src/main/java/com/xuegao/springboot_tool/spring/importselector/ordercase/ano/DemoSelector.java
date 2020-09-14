package com.xuegao.springboot_tool.spring.importselector.ordercase.ano;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by @author yihui in 18:19 19/12/13.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @Import(ConfigSelector.class)
public @interface DemoSelector {
    String value() default "all";
}
