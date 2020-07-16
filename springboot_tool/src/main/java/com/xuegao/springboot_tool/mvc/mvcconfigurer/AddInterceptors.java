package com.xuegao.springboot_tool.mvc.mvcconfigurer;

import com.xuegao.springboot_tool.mvc.interceptor.LoginlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <br/> @PackageName：com.cherrys.schooldemo.mvc.mvcconfigurer
 * <br/> @ClassName：AddInterceptor
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/7/16 11:31
 */
@Configuration
public class AddInterceptors implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new LoginlerInterceptor());
        /**
         * 添加拦截的路径
         * /为根路径
         * /*为一级路径
         * /** 为所有路径包括多级
         */
        interceptor.addPathPatterns("/**");

        //排除不拦截的，包括自己登录的页面不用拦截
        interceptor.excludePathPatterns("/login");
        interceptor.excludePathPatterns("/user/handle");
    }
}