package com.xuegao.springboot_tool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <br/> @PackageName：com.xuegao.dao
 * <br/> @ClassName：UserInfoMapper
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/28 10:15
 */
@MapperScan(basePackages = "com.xuegao.springboot_tool.dao")
@SpringBootApplication
public class SpringbootToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootToolApplication.class, args);
    }

}
