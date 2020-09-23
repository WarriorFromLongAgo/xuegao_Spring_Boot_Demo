package com.xuegao.springboot_tool;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.xuegao.springboot_tool.spring.beanload.DemoBeanHuiHui;
import com.xuegao.springboot_tool.spring.beanload.EnableOrderClient;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * <br/> @PackageName：com.xuegao.dao
 * <br/> @ClassName：SysUserinfoMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/28 10:15
 */

// 它可以扫描特定包下所有的被@ConfigurationProperties标记的配置类，并将它们进行IoC注入。
// @ConfigurationPropertiesScan
//     2.2.0
// {@link ConfigurationPropertiesScan} 同 {@link EnableConfigurationProperties} 二选一
@MapperScan(basePackages = "com.xuegao.springboot_tool.dao")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@ComponentScan(basePackages = {"com.xuegao"})
@EnableOrderClient
@EnableScheduling
public class SpringbootToolApplication {
    private final static Logger log = LoggerFactory.getLogger(SpringbootToolApplication.class);

    public SpringbootToolApplication(DemoBeanHuiHui demoBeanHuiHui) {
        demoBeanHuiHui.print();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringbootToolApplication.class, args);
    }

}

// @SpringBootApplication
// public class Ch5Application implements CommandLineRunner {
//
//     public static void main(String[] args) {
//         SpringApplication.run(Ch5Application.class, args);
//     }
//
//     @Override
//     public void run(String... args) throws Exception {
//         log.info("Ch5Application CommandLineRunner runs...");
//     }
// }
