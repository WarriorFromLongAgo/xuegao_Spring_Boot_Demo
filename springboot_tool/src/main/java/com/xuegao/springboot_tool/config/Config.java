package com.xuegao.springboot_tool.config;

import com.xuegao.springboot_tool.config.resourcesproperties.ConfigurationPropertiesConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.config
 * <br/> @ClassName：Config
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/10 14:08
 */
// 我们还可以使用注解@EnableConfigurationProperties进行注册，这样就不需要显式声明配置类为Spring Bean了。
@EnableConfigurationProperties({ConfigurationPropertiesConfig.class})
@Configuration
public class Config {
}