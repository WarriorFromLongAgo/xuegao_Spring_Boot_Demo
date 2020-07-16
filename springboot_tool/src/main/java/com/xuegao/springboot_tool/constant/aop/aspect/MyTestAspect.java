package com.xuegao.springboot_tool.constant.aop.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.constant.aop.aspect
 * <br/> @ClassName：MyTestAspect
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/7/14 12:35
 */
@Aspect
@Component
public class MyTestAspect {

    @Pointcut("@annotation(com.xuegao.springboot_tool.constant.aop.annotation.MyTest)")
    public void myTest() {

    }

    @Before(value = "myTest()")
    public void afterReturing() {
        System.out.println("111111111111111111");
        System.out.println("111111111111111111");
        System.out.println("111111111111111111");
        System.out.println("111111111111111111");
        System.out.println("111111111111111111");
    }


}