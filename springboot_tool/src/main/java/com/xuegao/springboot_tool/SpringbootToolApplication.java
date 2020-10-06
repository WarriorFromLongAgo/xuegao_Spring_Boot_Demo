package com.xuegao.springboot_tool;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.xuegao.springboot_tool.spring.beanload.DemoBeanHuiHui;
import com.xuegao.springboot_tool.spring.beanload.EnableOrderClient;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
    // 作者：dalaoyang
    // 链接：https://juejin.im/post/6844903791825780744
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

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
