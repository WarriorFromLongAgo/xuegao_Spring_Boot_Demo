package com.xuegao.springboot_tool.mvc.cors;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 重写WebMvcConfigurer的addCorsMappings方法（全局跨域配置）
//    https://juejin.im/post/6844903991558537223
/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：FileController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/9 0:33
 */
// @Configuration
// public class GlobalCorsConfig2 {
//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(@NotNull CorsRegistry registry) {
//                 registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
//                         .allowedOrigins("*")    //开放哪些ip、端口、域名的访问权限
//                         .allowCredentials(true)  //是否允许发送Cookie信息
//                         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")     //开放哪些Http方法，允许跨域访问
//                         .allowedHeaders("*")     //允许HTTP请求中的携带哪些Header信息
//                         .exposedHeaders("*")   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//                         .maxAge(3600);
//             }
//         };
//     }
// }
//
// 作者：zimug
//         链接：https://juejin.im/post/6844903991558537223
//         来源：掘金
//         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。