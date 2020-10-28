package com.xuegao.springboot_tool.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * mybatis plus config
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Configuration
public class MyBatisPlusConfig {
    /**
     * 配置mybatis-plus 分页查件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // /** MyBatis 配置 */
    // @Bean
    // public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
    //     SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
    //     // 单数据源方式
    //     factory.setDataSource(dataSource);
    //
    //     //配置分页插件，详情请查阅官方文档
    //     PageHelper pageHelper = new PageHelper();
    //     Properties properties = new Properties();
    //     //分页尺寸为0时查询所有纪录不再执行分页
    //     properties.setProperty("pageSizeZero", "true");
    //     //页码<=0 查询第一页，页码>=总页数查询最后一页
    //     properties.setProperty("reasonable", "true");
    //     //支持通过 Mapper 接口参数来传递分页参数
    //     properties.setProperty("supportMethodsArguments", "true");
    //     properties.setProperty("params", "count=countSql");
    //     //切换数据源，自动解析不同数据库的分页
    //     properties.setProperty("autoRuntimeDialect", "true");
    //     pageHelper.setProperties(properties);
    //
    //     //添加插件
    //
    //     //添加XML目录
    //     ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    //     factory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
    //
    //     return factory.getObject();
    // }
}