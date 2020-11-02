package com.xuegao.springboot_tool.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 80003093/tanquanfang
 * @date 2019/06/18
 */
@Data
@ConfigurationProperties(prefix = "proxy.cr.inter")
public class RestTemplateProxyProperties {
    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    // 代理地址
    private String proxyUrl = "proxy-public.intsit.sfdc.com.cn";
    // 代理端口
    private Integer proxyPort = 80;
    // 代理端口
    private Integer sslProxyPort = 443;
    // 是否启用代理
    private Boolean proxyEnable = false;
    // 使用代理的主机集合
    private List<String> useProxyHostList = new ArrayList<>();
}
