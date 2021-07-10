package com.xuegao.springboot_tool.sharding.aspect;

import com.xuegao.springboot_tool.sharding.annotation.ShardRule;
import com.xuegao.springboot_tool.sharding.config.DynamicDataSourceHolder;
import com.xuegao.springboot_tool.sharding.config.DynamicTableHolder;
import com.xuegao.springboot_tool.sharding.shardrule.ShardRuleInterface;
import com.xuegao.springboot_tool.utils.SpringUtils;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Configuration
public class ShardAspect {
    private static final Logger log = LoggerFactory.getLogger(ShardAspect.class);
    private static final ConcurrentHashMap<String, Integer> METHOD_SHARD_KEY_MAP = new ConcurrentHashMap();

    public ShardAspect() {
    }

    @After("execution(* com.*.*..provider.mapper..*.*(..))")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceHolder.removeDataSource();
        DynamicTableHolder.clearTable();
    }

    @Before("execution(* com.*.*..provider.mapper.shard..*.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        ShardRule shardRule = (ShardRule)declaringClass.getAnnotation(ShardRule.class);
        if (shardRule != null) {
            String shardKey = shardRule.shardKey();
            String shardDB = shardRule.shardDB();
            String shardTable = shardRule.shardTable();
            ShardRuleInterface rule = (ShardRuleInterface) SpringUtils.getBean(shardRule.shardRule());
            String signName = sign.toString();
            Integer shardParamIdx = (Integer)METHOD_SHARD_KEY_MAP.getOrDefault(signName, -1);
            if (shardParamIdx == -1) {
                Annotation[][] annotations = method.getParameterAnnotations();

                for(int i = 0; i < annotations.length && shardParamIdx < 0; ++i) {
                    Annotation[] paramAnnotations = annotations[i];
                    if (paramAnnotations != null && paramAnnotations.length != 0) {
                        Annotation[] var15 = paramAnnotations;
                        int var16 = paramAnnotations.length;

                        for(int var17 = 0; var17 < var16; ++var17) {
                            Annotation annotation = var15[var17];
                            if (annotation instanceof Param && shardKey.equals(((Param)annotation).value())) {
                                shardParamIdx = i;
                                METHOD_SHARD_KEY_MAP.put(signName, shardParamIdx);
                                break;
                            }
                        }
                    }
                }
            }

            Object[] args = joinPoint.getArgs();
            if (shardParamIdx != -1 && shardParamIdx < args.length) {
                Object arg = args[shardParamIdx];
                Integer key = null;
                if (arg != null && !"".equals(arg)) {
                    key = Integer.valueOf(arg.toString());
                }

                if (key == null) {
                    log.error("[分库分表] 参数非Integer 类型 {}", arg);
                } else {
                    String realDB = rule.getDatabaseByRule(shardDB, key);
                    String realTable = rule.getTableByRule(shardTable, key);
                    DynamicDataSourceHolder.putDataSource(realDB);
                    DynamicTableHolder.putTable(realTable);
                    log.debug("[分库分表] 查询db: {} table: {}", realDB, realTable);
                }
            } else {
                log.error("[分库分表] 参数获取错误，没有找到分片键");
            }
        }
    }
}
