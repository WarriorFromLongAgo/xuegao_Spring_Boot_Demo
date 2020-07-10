package com.xuegao.springboot_tool.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.config
 * <br/> @ClassName：ConfigYml
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/7/10 13:53
 */
public class ConfigYml {

    // 获取 yaml 中 felord.phone的值 并提供默认值 UNKNOWN
    @Value("${felord.phone:UNKNOWN}")
    private String phone;

}