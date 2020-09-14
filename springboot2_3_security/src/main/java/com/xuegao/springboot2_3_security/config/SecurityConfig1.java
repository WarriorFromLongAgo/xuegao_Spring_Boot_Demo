package com.xuegao.springboot2_3_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config
 * <br/> @ClassName：SecurityConfig
 * <br/> @Description：http://www.itboyhub.com/2019/1224/springboot-security-login-json.html
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 14:23
 */
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//         //下面这两行配置表示在内存中配置了两个用户
//         auth.inMemoryAuthentication()
//                 .withUser("javaboy")
//                 .roles("admin")
//                 // 明文是 123，加盐
//                 .password("$2a$10$OR3VSksVAmCzc.7WeaRPR.t0wyCsIj24k0Bne8iKWV1o.V9wsP8Xe")
//                 .and()
//                 .withUser("lisi")
//                 .roles("user")
//                 // 明文是 123，加盐
//                 .password("$2a$10$p1H8iWa8I4.CA.7Z8bwLjes91ZpY.rYREGHQEInNtAp4NzL6PLKxi");
//     }
//
//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }