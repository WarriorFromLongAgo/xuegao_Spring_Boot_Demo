package com.xuegao.springboot_tool.mvc.mvcconfigurer;

import com.xuegao.springboot_tool.mvc.interceptor.RedisLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <br/> @PackageName：com.cherrys.schooldemo.mvc.mvcconfigurer
 * <br/> @ClassName：AddInterceptor
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/16 11:31
 */
@Configuration
public class AddInterceptors implements WebMvcConfigurer {

    // @Autowired
    // private RedisLimitInterceptor redisLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // InterceptorRegistration loginlerInterceptor = registry.addInterceptor(new LoginlerInterceptor());
        // /**
        //  * 添加拦截的路径
        //  * /为根路径
        //  * /*为一级路径
        //  * /** 为所有路径包括多级
        //  */
        // loginlerInterceptor.addPathPatterns("/**");
        // // 排除不拦截的，包括自己登录的页面不用拦截
        // loginlerInterceptor.excludePathPatterns("/login");
        // loginlerInterceptor.excludePathPatterns("/user/handle");
        //
        // List<String> excludePath = new ArrayList<>();
        // // 排除拦截，除了注册登录(此时还没token)，其他都拦截
        // // 注册
        // excludePath.add("/login");
        // // 静态资源
        // excludePath.add("/static/**");
        // // 静态资源
        // excludePath.add("/assets/**");

        // registry.addInterceptor(new TokenInterceptor())
        //         .addPathPatterns("/**")
        //         .excludePathPatterns(excludePath);

        // registry.addInterceptor(redisLimitInterceptor)
        //         .addPathPatterns("/**");
    }

    // 配置静态资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问/swagger-ui.html找不到页面，原因是Swagger的页面是打在JAR中的。
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/**");
        // ico
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
        // knife4j
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    }

    // 重写父类提供的跨域请求处理的接口，跨域处理
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                // 开放哪些ip、端口、域名的访问权限，星号表示开放所有域
                .allowedOrigins("*")
                // 是否允许发送Cookie信息
                .allowCredentials(true)
                // 开放哪些Http方法，允许跨域访问
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                // 放行哪些原始域(头部信息) 允许HTTP请求中的携带哪些Header信息
                .allowedHeaders("*")
                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Header1", "Header2");
        //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        // UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        // configSource.registerCorsConfiguration("/**", config);
    }


}