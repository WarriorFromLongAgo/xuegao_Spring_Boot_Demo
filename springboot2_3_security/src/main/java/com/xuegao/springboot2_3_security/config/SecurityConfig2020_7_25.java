// package com.xuegao.springboot2_3_security.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot2_3_security.config
//  * <br/> @ClassName：SecurityConfig2020_7_25
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2020/7/25 16:18
//  */
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig2020_7_25 extends WebSecurityConfigurerAdapter {
//
//     // 强散列，密码的加密，匹配
//     @Bean
//     public PasswordEncoder getPasswordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
//
//     @Override
//     protected void configure(HttpSecurity httpSecurity) throws Exception {
//         // 开启formlogin模式
//         httpSecurity.formLogin()
//                 // 自定义登陆页面，用户未登陆时，都会到这里
//                 .loginPage("/login.html")
//                 // 发现登录，就去执行
//                 // 必须和表单的提交地址一样，去执行自定义的登录逻辑，必须是post
//                 .loginProcessingUrl("/login")
//                 // 默认是username
//                 .usernameParameter("username")
//                 // 默认是password
//                 .passwordParameter("password")
//                 // 登陆成功跳转
//                 .defaultSuccessUrl("/index")
//                 // 登录失败跳转
//                 .failureForwardUrl("/login.html")
//         ;
//
//         // 配置权限
//         httpSecurity.authorizeRequests()
//                 // /login.html 不需要被拦截
//                 .antMatchers("/login.html", "/login")
//                 // 用户可以任意访问
//                 .permitAll()
//
//                 // 需要对外暴露的资源路径
//                 .antMatchers("/order")
//                 // user 和 admin角色可以访问
//                 .hasAnyAuthority("ROLE_user", "ROLE_admin")
//
//                 .antMatchers("/system/user", "/system/role", "/system/menu")
//                 // admin角色可以访问
//                 .hasAnyRole("admin")
//                 // 所有的请求都必须被认证，必须登录之后访问
//                 .anyRequest().authenticated();
//
//         // 关闭防火墙，禁用csrf攻击防御，否则无法登录成功
//         httpSecurity.csrf().disable();
//
//         // 登出功能
//         httpSecurity.logout().logoutUrl("logout");
//     }
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         // 使用内存的方式，不使用数据库
//         auth.inMemoryAuthentication()
//                 .withUser("user")
//                 .password(getPasswordEncoder().encode("123456"))
//                 .roles("user");
//         auth.inMemoryAuthentication()
//                 .withUser("admin")
//                 .password(getPasswordEncoder().encode("123456"))
//                 .roles("admin");
//         auth.inMemoryAuthentication().passwordEncoder(getPasswordEncoder());
//     }
// }