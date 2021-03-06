package com.xuegao.springboot_tool.constant.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.constant.aop.aspect
 * <br/> @ClassName：MyTestAspect
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/14 12:35
 */
@Aspect
@Component
public class MyTestAspect {
    // Around
    // Around

    // Before
    // Before

    // indexController = hello

    // AfterReturning
    // AfterReturning

    // After
    // After

    // Around
    // Around

    @Pointcut("@annotation(com.xuegao.springboot_tool.constant.aop.annotation.MyTest)")
    public void myTest() {

    }

    @Before(value = "myTest()")
    public void Before() {
        // 直接报错
        // int a = 2 / 0;
        System.out.println("Before");
        System.out.println("Before");
    }

    @Around(value = "myTest()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        List<String> stringList = new ArrayList<>();

        Object proceed = null;
        try {
            System.out.println("Around");
            System.out.println("Around");
            // 直接报错，返回，只打印两行
            // int a = 2 / 0;
            proceed = proceedingJoinPoint.proceed();
            // 直接报错，打印上面的一堆
            // int a = 2 / 0;
            System.out.println("Around");
            System.out.println("Around");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            stringList.add("1111");
            stringList.add("2222");
        }
        stringList.parallelStream().forEach(s -> System.out.print(s));
        return proceed;
    }

    @After(value = "myTest()")
    public void After() {
        // 直接报错
        // int a = 2 / 0;
        System.out.println("After");
        System.out.println("After");
    }

    @AfterReturning(value = "myTest()")
    public void AfterReturning() {
        // 直接报错
        // int a = 2 / 0;
        System.out.println("AfterReturning");
        System.out.println("AfterReturning");
    }

    @AfterThrowing(value = "myTest()", throwing = "throwable")
    public void AfterThrowing(Throwable throwable) {
        // 走不到这里
        System.out.println("AfterThrowing");
        System.out.println("异常抛出通知=======================" + throwable);
        System.out.println("AfterThrowing");
    }


}