package com.xuegao.springboot_tool.config;

import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Configuration
public class DbConfig {

    // @Value("${project.database}")
    // private String database;
    // @Resource
    // private  SysGeneratorMysqlMapper sysGeneratorMysqlMapper;
    // @Resource
    // private SysGeneratorOracleMapper sysGeneratorOracleMapper;
    // @Resource
    // private SysGeneratorSqlServerMapper sysGeneratorSqlServerMapper;

    // @Bean
    // @Primary
    // public GeneratorMapper getGeneratorMapper(){
    //     if(Constant.DB_TYPE_MYSQL.equalsIgnoreCase(database)){
    //         return sysGeneratorMysqlMapper;
    //     }else if(Constant.DB_TYPE_ORACLE.equalsIgnoreCase(database)){
    //         return sysGeneratorOracleMapper;
    //     }else if(Constant.DB_TYPE_SQL_SERVER.equalsIgnoreCase(database)){
    //         return sysGeneratorSqlServerMapper;
    //     }else {
    //         throw new BusinessException("不支持当前数据库：" + database);
    //     }
    // }
}
