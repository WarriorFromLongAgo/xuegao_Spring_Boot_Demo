package com.xuegao.springboot_tool.config;

import org.springframework.context.annotation.Configuration;

/**
 * 生成验证码配置
 * @author wenbin
 * @version V1.0
 */
@Configuration
public class KaptchaConfig {

    // @Bean(name="captchaProducer")
    // public DefaultKaptcha getDefaultKaptcha(){
    //     DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
    //     Properties properties = new Properties();
    //     // 图片边框
    //     properties.setProperty("kaptcha.border", "no");
    //     //去除干扰线
    //     properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
    //     // 边框颜色
    //     properties.setProperty("kaptcha.border.color", "105,179,90");
    //     // 字体颜色
    //     properties.setProperty("kaptcha.textproducer.font.color", "black");
    //     // 图片宽
    //     properties.setProperty("kaptcha.image.width", "100");
    //     // 图片高
    //     properties.setProperty("kaptcha.image.height", "50");
    //     // 字体大小
    //     properties.setProperty("kaptcha.textproducer.font.size", "35");
    //     // 验证码长度
    //     properties.setProperty("kaptcha.textproducer.char.length", "4");
    //     // 字体
    //     properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
    //     Config config = new Config(properties);
    //     defaultKaptcha.setConfig(config);
    //
    //     return defaultKaptcha;
    // }
}