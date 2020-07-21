package com.xuegao.springboot_tool.mvc.mvcconfigurer;

import com.xuegao.springboot_tool.mvc.interceptor.LoginlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

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

    // 配置静态资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/**");
    }

    //重写父类提供的跨域请求处理的接口，跨域处理
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //是否发送Cookie信息
                .allowCredentials(true)
                //放行哪些原始域(请求方式)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                //放行哪些原始域(头部信息)
                .allowedHeaders("*")
                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Header1", "Header2");
    }
}