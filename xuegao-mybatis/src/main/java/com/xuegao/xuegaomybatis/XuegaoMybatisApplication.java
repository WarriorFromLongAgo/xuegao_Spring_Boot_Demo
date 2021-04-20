package com.xuegao.xuegaomybatis;

import com.xuegao.xuegaomybatis.config.PropertiesListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XuegaoMybatisApplication {

    public static void main(String[] args) {
        // SpringApplication.run(XuegaoMybatisApplication.class, args);

        SpringApplication application = new SpringApplication(XuegaoMybatisApplication.class);
        // 第四种方式：注册监听器
        application.addListeners(new PropertiesListener("app-config.properties"));
        application.run(args);

        // 作者：慕冬雪
        // 链接：https://www.imooc.com/article/18252
        // 来源：慕课网
    }

}
