package com.xuegao.springboot2_3_security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuegao.springboot2_3_security.domain.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config
 * <br/> @ClassName：SecurityConfig6
 * <br/> @Description：从json中获取
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/18 15:59
 */
// public class SecurityConfig6 extends WebSecurityConfigurerAdapter {
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.inMemoryAuthentication()
//                 .withUser("zhangsan")
//                 .password("$2a$10$2O4EwLrrFPEboTfDOtC0F.RpUMk.3q3KvBHRx7XXKUMLBGjOOBs8q")
//                 .roles("user");
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests().anyRequest().authenticated()
//                 .and()
//                 .formLogin()
//                 .and().csrf().disable();
//         http.addFilterAt(customAuthenticationFilter2(), UsernamePasswordAuthenticationFilter.class);
//     }
//
//     @Bean
//     CustomAuthenticationFilter2 customAuthenticationFilter2() throws Exception {
//         CustomAuthenticationFilter2 filter = new CustomAuthenticationFilter2();
//         filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
//             @Override
//             public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                 resp.setContentType("application/json;charset=utf-8");
//                 PrintWriter out = resp.getWriter();
//                 RespBean respBean = RespBean.ok("登录成功!");
//                 out.write(new ObjectMapper().writeValueAsString(respBean));
//                 out.flush();
//                 out.close();
//             }
//         });
//         filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
//             @Override
//             public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                 resp.setContentType("application/json;charset=utf-8");
//                 PrintWriter out = resp.getWriter();
//                 RespBean respBean = RespBean.error("登录失败!");
//                 out.write(new ObjectMapper().writeValueAsString(respBean));
//                 out.flush();
//                 out.close();
//             }
//         });
//         filter.setAuthenticationManager(authenticationManagerBean());
//         return filter;
//     }
// }