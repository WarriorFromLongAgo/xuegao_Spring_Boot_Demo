package com.xuegao.springboot_tool.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 自定义配置RestTemplateUtil
 * <p>
 * 通过{@link HttpComponentsClientHttpRequestFactory}使用<br/>
 * 需要使用时,在@ComponentScan中配置此类或com.sf.cr.util.autoconfig.net路径,否则不可使用RedisUtil<br/>
 * 单独排除方式:@ComponentScan(excludeFilters=@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes={RestTemplateUtilConfig.class}))
 * </p>
 *
 * @author 80003093/tanquanfang
 * @date 2019-5-13
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RestTemplateProxyProperties.class)
@ConditionalOnClass(HttpComponentsClientHttpRequestFactory.class)
public class RestTemplateUtilConfig {

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(RestTemplateProxyProperties properties)
        throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        // 使用代理
        HttpComponentsClientHttpRequestFactory factory = null;

        if (properties.getProxyEnable()) {
            // 设置代理
            final String proxyUrl = properties.getProxyUrl();
            final int port = properties.getProxyPort();
            final int sslPort = properties.getSslProxyPort();

            log.info("用代理 proxy={}", proxyUrl);
            // 安全证书
            // TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
            // SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            // SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

            HttpHost proxy = new HttpHost(proxyUrl, port);
            HttpHost sslproxy = new HttpHost(proxyUrl, sslPort);

            // HttpRoutePlanner routePlanner = new SystemDefaultRoutePlanner(ProxySelector.getDefault());

            HttpClient httpClient = HttpClientBuilder.create().setRoutePlanner(new DefaultRoutePlanner(null) {

                @Override
                protected HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context)
                    throws HttpException {
                    List<String> useProxyHostList = properties.getUseProxyHostList();
                    if (ObjectUtils.isEmpty(useProxyHostList)) {
                        return null;
                    }

                    for (String needProxyHost : useProxyHostList) {
                        String targetHost = target.getHostName();
                        if (targetHost.equals(needProxyHost)) {
                            String schemeName = target.getSchemeName();
                            //log.info("determineProxy schemeName:{},targetHost:{},userProxyHost:{}", schemeName, targetHost, needProxyHost);
                            return RestTemplateProxyProperties.HTTPS.equalsIgnoreCase(schemeName) ? sslproxy : proxy;
                        }
                    }
                    return null;
                }

            }).build();

            // 使用代理   测试环境
            // curl –x proxy-public.intsit.sfdc.com.cn:80 "http://targetUrl"
            // curl –x proxy-public.intsit.sfdc.com.cn:443 "https://targetUrl"
            factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        } else {
        	log.info("不用代理");
            // 不使用代理
            // curl "targetUrl"
            factory = new HttpComponentsClientHttpRequestFactory();
        }

        factory.setConnectTimeout(5000);// 链接5s
        factory.setReadTimeout(10000);// 等待响应10s

        return new RestTemplate(factory);
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplateUtil restTemplateUtil(RestTemplate restTemplate) {
        RestTemplateUtil instance = RestTemplateUtil.getInstance();
        instance.setRestTemplate(restTemplate);
        return instance;
    }
}
