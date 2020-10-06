package com.xuegao.springboot_tool.constant.aop.aspect;

import com.alibaba.fastjson.JSONObject;
import com.xuegao.springboot_tool.constant.aop.annotation.RedisLimit;
import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.mvc.exception.RedisLimitException;
import com.xuegao.springboot_tool.utils.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.constant.aop.aspect
 * <br/> @ClassName：RedisLimit
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/23 16:48
 */
@Aspect
@Component
public class RedisLimitAspect {
    private Logger log = LoggerFactory.getLogger(RedisLimitAspect.class);

    // 从配置文件获得服务名
    @Value("${spring.application.name}")
    private String applicationName;

    // @Autowired
    // private HttpServletRequest request;
    //
    // @Autowired
    // private HttpServletResponse response;

    @Autowired
    private ValueOperations<String, Serializable> valueOperations;

    // spel表达式解析器
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    // 参数名发现器
    private DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Pointcut("@annotation(com.xuegao.springboot_tool.constant.aop.annotation.RedisLimit)")
    public void RedisLimitAspect() {
    }

    @Around("RedisLimitAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        RedisLimit redisLimit = method.getAnnotation(RedisLimit.class);
        if (ObjectUtils.isEmpty(redisLimit)) {
            return proceedingJoinPoint.proceed();
        }

        String spElKey = redisLimit.key();
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        // 解析过后的Spring表达式对象
        Expression expression = spelExpressionParser.parseExpression(spElKey);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 通过joinPoint获取被注解方法的形参
        Object[] args = proceedingJoinPoint.getArgs();
        // 给上下文赋值
        if (ObjectUtils.isEmpty(paramNames)) {
            return proceedingJoinPoint.proceed();
        }
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        String key = limitType(method, redisLimit, expression.getValue(context, String.class));
        // 限流时间
        int expireTime = redisLimit.expireTime();
        // 单位时间 允许访问的次数
        int limitCount = redisLimit.limitCount();
        // 目前已经访问的最大次数
        Integer maxLimit = (Integer) valueOperations.get(key);
        if (ObjectUtils.isEmpty(maxLimit)) {
            valueOperations.set(key, 1, expireTime, TimeUnit.MILLISECONDS);
        } else if (maxLimit < limitCount) {
            valueOperations.set(key, maxLimit + 1, expireTime, TimeUnit.MILLISECONDS);
        } else {
            throw new RedisLimitException("请求过于频繁");
        }
        Object proceed = proceedingJoinPoint.proceed();

        return proceed;
    }

    @NotNull
    private String limitType(Method method, RedisLimit redisLimit, String spELKey) {
        RedisLimit.LimitType limitType = redisLimit.limitType();
        StringBuilder key = new StringBuilder();
        switch (limitType) {
            case IP:
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (ObjectUtils.isEmpty(attributes)) {
                    key.append(applicationName).append(IPUtils.getIpAddr());
                } else {
                    HttpServletRequest request = attributes.getRequest();
                    key.append(applicationName).append(IPUtils.getIpAddr()).append(request.getRequestURI());
                }
                break;
            case CUSTOMER:
                key.append(applicationName).append(spELKey);
                break;
            default:
                key.append(applicationName).append(StringUtils.upperCase(method.getName()));
        }
        return key.toString();
    }

    private void output(String message) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (!ObjectUtils.isEmpty(attributes) && !ObjectUtils.isEmpty(attributes.getResponse())) {
            // HttpServletResponse response = attributes.getResponse();
            // response.setContentType(MediaType.APPLICATION_JSON.toString());
            // response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            // ServletOutputStream outputStream = null;
            // try {
            //     String json = JSONObject.toJSON(WrappedResponse.fail(message)).toString();
            //     outputStream = response.getOutputStream();
            //     outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            // } catch (IOException e) {
            //     e.printStackTrace();
            // } finally {
            //     if (!ObjectUtils.isEmpty(outputStream)) {
            //         outputStream.flush();
            //         outputStream.close();
            //     }
            // }
        }
    }
}