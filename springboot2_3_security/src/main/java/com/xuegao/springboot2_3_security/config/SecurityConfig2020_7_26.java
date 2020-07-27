// package com.xuegao.springboot2_3_security.config;
//
// import com.xuegao.springboot2_3_security.config.security.MyAuthenticationFailureHandler;
// import com.xuegao.springboot2_3_security.config.security.MyAuthenticationSuccessHandler;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot2_3_security.config
//  * <br/> @ClassName：SecurityConfig2020_7_26
//  * <br/> @Description：
//  * <br/> @author：feijm
//  * <br/> @date：2020/7/26 1:21
//  */
// @EnableWebSecurity
// @Configuration
// public class SecurityConfig2020_7_26 extends WebSecurityConfigurerAdapter {
//
//     @Autowired
//     private MyAuthenticationSuccessHandler successHandler;
//
//     @Autowired
//     private MyAuthenticationFailureHandler failureHandler;
//
//     @Autowired
//     private UserDetailsService userDetailsService;
//
//     @Override
//     protected void configure(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
//                 .formLogin()
//                 .loginPage("/login.html")
//                 .loginProcessingUrl("/login")
//                 .usernameParameter("username")
//                 .passwordParameter("password")
//                 // 不可以直接返回页面，只能通过handler处理
//                 .successHandler(successHandler)
//                 .failureHandler(failureHandler);
//
//         httpSecurity
//                 .authorizeRequests()
//                 .antMatchers("/login.html","/login")
//                 .permitAll()
//                 // 需要对外暴露的资源路径
//                 .antMatchers("/order")
//                 // user 和 admin角色可以访问
//                 .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//
//                 .antMatchers("/system/user", "/system/role", "/system/menu")
//                 // admin角色可以访问
//                 .hasAnyRole("ADMIN")
//                 // 所有的请求都必须被认证，必须登录之后访问
//                 .anyRequest().authenticated();
//
//     }
// }