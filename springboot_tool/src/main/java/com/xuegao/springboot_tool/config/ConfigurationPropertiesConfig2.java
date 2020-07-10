package com.xuegao.springboot_tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.config
 * <br/> @ClassName：ConfigurationPropertiesConfig
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/7/10 14:01
 */
@ConfigurationProperties(prefix = "felord.def")
// @ConfigurationProperties(PREFIX)
public class ConfigurationPropertiesConfig2 {
    static final String PREFIX = "felord";
    private Def def;
    private Dev dev;
    private Type type;

    public static class Def {
        private String name;
        private String blog;
        private String weChat;
    }

    public static class Dev {
        private String name;
        private String blog;
        private String weChat;
    }

    public enum Type {
        JUEJIN,
        SF,
        OSC,
        CSDN
    }


}