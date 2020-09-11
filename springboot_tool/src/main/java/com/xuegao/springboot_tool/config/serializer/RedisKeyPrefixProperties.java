package com.xuegao.springboot_tool.config.serializer;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.config
 * <br/> @ClassName：RedisKeyPrefixProperties
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/23 21:01
 */
// @Import({RedisKeyPrefixProperties.class})
@ConfigurationProperties(prefix = "spring.redis.prefix")
public class RedisKeyPrefixProperties {
    private Boolean enable = Boolean.TRUE;
    private String key;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}