package com.xuegao.springboot_tool.annotation;

import java.lang.annotation.*;

/**
 * 锁的注解
 * 幂等性
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {

    /**
     * @author fly
     */
    String key() default "";
}
