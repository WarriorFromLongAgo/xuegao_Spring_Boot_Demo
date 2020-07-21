package com.xuegao.springboot_tool.constant.aop.annotation;

import java.lang.annotation.*;

/**
 * LogAnnotation
 *
 * @author 数据插入到数据库
 * @version V1.0
 * @date 2020年3月18日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /** 模块 */
    String title() default "";

    /** 功能 */
    String action() default "";
}
