package com.xuegao.ctest.service;

import com.xuegao.atest.Person;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * <br/> @PackageName：com.xuegao.ctest.service
 * <br/> @ClassName：ComponentScanFilter
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/30 19:37
 */
public class ComponentScanFilter implements TypeFilter {

    /**
     * <br/> @Title:
     * <br/> @Description:
     * <br/> @MethodName: match
     * <br/> @param metadataReader: 读取当前正在扫描类的信息
     * <br/> @param metadataReaderFactory: 获取获取到其他任何类的信息
     * <br/> @return: boolean
     * <br/> @author: xuegao
     * <br/> @date: 2021/5/30 19:38
     */
    @Override
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        System.out.println(" classMetadata className = " + className);
        if (className.contains("test")) {
            return true;
        }
        return false;

        // 不反悔 true
        // classMetadata className = com.xuegao.ctest.service.ComponentScanFilter
        // classMetadata className = com.xuegao.ctest.service.TestController
        // classMetadata className = com.xuegao.ctest.service.TestMapper
        // classMetadata className = com.xuegao.ctest.service.TestService
        // Person {name='cperson', age=24}
        // org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // org.springframework.context.annotation.internalCommonAnnotationProcessor
        // org.springframework.context.event.internalEventListenerProcessor
        // org.springframework.context.event.internalEventListenerFactory
        // mainConfig
        // personC

        // 返回 true
        // classMetadata className = com.xuegao.ctest.service.ComponentScanFilter
        // classMetadata className = com.xuegao.ctest.service.TestController
        // classMetadata className = com.xuegao.ctest.service.TestMapper
        // classMetadata className = com.xuegao.ctest.service.TestService
        // Person{name='cperson', age=24}
        // org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // org.springframework.context.annotation.internalCommonAnnotationProcessor
        // org.springframework.context.event.internalEventListenerProcessor
        // org.springframework.context.event.internalEventListenerFactory
        // mainConfig
        // componentScanFilter
        // testController
        // testMapper
        // testService
        // personC


    }
}