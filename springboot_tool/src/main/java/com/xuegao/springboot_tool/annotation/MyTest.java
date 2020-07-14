package com.xuegao.springboot_tool.annotation;

import java.lang.annotation.*;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.annotation
 * <br/> @ClassName：MyTest
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/7/14 12:34
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
}
