package com.xuegao.springboot_tool.utils.springconver;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils.springconver
 * <br/> @ClassName：StringToClassConverter
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/19 16:11
 */
public class StringToClassConverter implements Converter<String, Class>, ApplicationContextAware {

    // private classmappermanager

    // StringToClassConverter是我自定义的转换器，作用是将字符串转换为对应的class

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public Class convert(String source) {
        return null;
    }
}