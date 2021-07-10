// package com.xuegao.springboot_tool.sharding.aspect;
//
// import com.baomidou.mybatisplus.core.toolkit.StringUtils;
// import com.xuegao.springboot_tool.sharding.annotation.SlaveDataSource;
// import com.xuegao.springboot_tool.sharding.config.DynamicDataSourceHolder;
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.stereotype.Component;
//
// import java.util.Objects;
// import java.util.Random;
//
// @Aspect
// @Component
// @Configuration
// public class DataSourceAspect {
//     private static final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);
//     @Value("${spring.datasource.names:slave1}")
//     private String dynamicDatasource;
//
//     public DataSourceAspect() {
//     }
//
//     @Pointcut("execution(* com.*.*..provider..controller..*.*(..))&&@annotation(slaveDataSource)")
//     public void controllerAnnotarion(SlaveDataSource slaveDataSource) {
//     }
//
//     @Before("controllerAnnotarion(slaveDataSource)")
//     public void before(JoinPoint joinPoint, SlaveDataSource slaveDataSource) {
//         try {
//             String dataSourceName = "";
//             if (!Objects.isNull(slaveDataSource)) {
//                 dataSourceName = slaveDataSource.value();
//             }
//
//             if (StringUtils.isBlank(dataSourceName)) {
//                 dataSourceName = getDbWithRandom(this.dynamicDatasource);
//             }
//
//             DynamicDataSourceHolder.putDataSource(dataSourceName);
//         } catch (Exception var4) {
//             log.error("DataSourceAspect current thread " + Thread.currentThread().getName() + " add data to ThreadLocal error", var4);
//         }
//
//     }
//
//     public static String getDbWithRandom(String dbs) {
//         String[] dynamicDbs = dbs.split(",");
//         int num = (new Random()).nextInt(dynamicDbs.length);
//         return dynamicDbs[num];
//     }
//
//     @After("controllerAnnotarion(slaveDataSource)")
//     public void after(JoinPoint joinPoint, SlaveDataSource slaveDataSource) {
//         DynamicDataSourceHolder.removeDataSource();
//     }
// }
