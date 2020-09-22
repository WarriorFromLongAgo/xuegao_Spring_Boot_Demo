package com.xuegao.springboot_tool.mvc.interceptor;

import com.google.common.collect.Lists;
import com.xuegao.springboot_tool.constant.aop.annotation.RedisLimit;
import com.xuegao.springboot_tool.utils.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.mvc.interceptor
 * <br/> @ClassName：RedisLimitInterceptor
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/22 19:13
 */
public class RedisLimitInterceptor implements HandlerInterceptor {

    // 从配置文件获得服务名
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private ValueOperations<String, Serializable> valueOperations;

    // spel表达式解析器
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    // 参数名发现器
    // private DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            if (!method.isAnnotationPresent(RedisLimit.class)) {
                return true;
            }
            RedisLimit redisLimit = method.getAnnotation(RedisLimit.class);
            if (ObjectUtils.isEmpty(redisLimit)) {
                return true;
            }

            String spelKey = redisLimit.key();
            // spel 获得方法参数名数组
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(parameterNames)) {
                EvaluationContext evaluationContext = new StandardEvaluationContext();
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameterNames.length; i++) {
                    // 替换 spel 里的变量值为实际值， 比如 #user -->  user对象
                    evaluationContext.setVariable(parameterNames[i], parameters[i]);
                }
                // 解析出实际的日志信息
                Expression expression = spelExpressionParser.parseExpression(spelKey);
                Object value = expression.getValue(evaluationContext);
                if (ObjectUtils.isEmpty(value)) {
                    return true;
                }
                String s = value.toString();
                System.out.println(s);
            }

            StringBuilder key = new StringBuilder();
            RedisLimit.LimitType limitType = redisLimit.limitType();
            switch (limitType) {
                case IP:
                    key.append(applicationName).append(IPUtils.getIpAddr()).append(request.getRequestURI());
                    break;
                case CUSTOMER:
                    key.append(applicationName).append(redisLimit.key());
                    break;
                default:
                    key.append(applicationName).append(StringUtils.upperCase(method.getName()));
            }

            // 单位时间 允许访问的次数
            int limitCount = redisLimit.limitCount();
            // 限流时间
            int expireTime = redisLimit.expireTime();
            // 目前已经访问的最大次数
            Integer maxLimit = (Integer) valueOperations.get(key.toString());
            if (ObjectUtils.isEmpty(maxLimit)) {
                valueOperations.set(key.toString(), 1, expireTime, TimeUnit.MILLISECONDS);
            } else if (maxLimit < limitCount) {
                valueOperations.set(key.toString(), maxLimit + 1, expireTime, TimeUnit.MILLISECONDS);
            } else {
                output(response, "请求太过于频繁");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void output(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }


    }

}