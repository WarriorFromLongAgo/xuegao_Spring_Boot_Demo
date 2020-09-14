package com.xuegao.springboot_tool.config.resourcesproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.config
 * <br/> @ClassName：ConfigurationPropertiesConfig
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/10 14:01
 */
@ConfigurationProperties(prefix = "felord.def")
// @ConfigurationProperties(PREFIX)
public class ConfigurationPropertiesConfig {
    static final String PREFIX = "felord.def";
    private String name;
    private String blog;
    private String weChat;

    // 我们注意到我们可以使用weChat接收we-chat的值，
    // 因为这种形式支持从驼峰camel-case到短横分隔命名kebab-case的自动转换。




}