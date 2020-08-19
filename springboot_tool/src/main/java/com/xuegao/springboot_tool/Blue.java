package com.xuegao.springboot_tool;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Blue implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    // 当前bean的名字
    // 解析后的字符串为：你好Windows 10 年龄：360
    // 传入的ioc：org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@2805d709, started on Wed Aug 19 09:56:58 CST 2020

    private ApplicationContext applicationContext;

    // ApplicationContextAware接口使用的比较多，我们先来说说这个接口，通过ApplicationContextAware接口我们可以获取到IOC容器。
    // 首先，我们创建一个Blue类，并实现ApplicationContextAware接口，在实现的setApplicationContext()中将ApplicationContext输出，
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc：" + applicationContext);
        this.applicationContext = applicationContext;
    }

    // 使Blue类再实现一个BeanNameAware接口，我们可以通过BeanNameAware接口获取到当前bean在Spring容器中的名称，如下所示。
    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字");
    }

    // 我们通过EmbeddedValueResolverAware接口能够获取到StringValue解析器
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String resolveStringValue = resolver.resolveStringValue("你好${os.name} 年龄：#{20*18}");
        System.out.println("解析后的字符串为：" + resolveStringValue);
    }

    // XxxAware的底层原理是由XxxAwareProcessor类实现的， 例如，我们这里以ApplicationContextAware接口为例，
    // ApplicationContextAware接口的底层原理就是由ApplicationContextAwareProcessor类实现的。
    // 从ApplicationContextAwareProcessor类的源码可以看出，其实现了BeanPostProcessor接口，本质上都是后置处理器
    // class ApplicationContextAwareProcessor implements BeanPostProcessor


}

// 作者：冰_河
// 链接：https://juejin.im/post/6862362813822664718
// 来源：掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

