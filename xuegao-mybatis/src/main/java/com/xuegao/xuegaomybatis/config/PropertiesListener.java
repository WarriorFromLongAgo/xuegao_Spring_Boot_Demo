package com.xuegao.xuegaomybatis.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * <br/> @PackageName：com.xuegao.xuegaomybatis.config
 * <br/> @ClassName：PropertiesListener
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/04/18 12:17
 */
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {
    private final String propertyFileName;

    public PropertiesListener(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
    }

    // 作者：慕冬雪
    // 链接：https://www.imooc.com/article/18252
    // 来源：慕课网
}