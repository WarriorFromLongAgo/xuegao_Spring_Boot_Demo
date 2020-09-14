package com.xuegao.springboot2_3_security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuegao.springboot2_3_security.domain.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config
 * <br/> @ClassName：SecurityConfig5
 * <br/> @Description：http://www.itboyhub.com/2019/1224/springboot-security-login-json.html
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 15:31
 */
// @Configuration
// public class SecurityConfig5 extends WebSecurityConfigurerAdapter {
//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
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
//     public void configure(WebSecurity web) throws Exception {
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//                 .anyRequest().authenticated()
//                 .and()
//                 .formLogin()
//                 .loginProcessingUrl("/doLogin")
//                 .successHandler(new AuthenticationSuccessHandler() {
//                     @Override
//                     public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                         RespBean ok = RespBean.ok("登录成功！", authentication.getPrincipal());
//                         resp.setContentType("application/json;charset=utf-8");
//                         PrintWriter out = resp.getWriter();
//                         out.write(new ObjectMapper().writeValueAsString(ok));
//                         out.flush();
//                         out.close();
//                     }
//                 })
//                 .failureHandler(new AuthenticationFailureHandler() {
//                     @Override
//                     public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                         RespBean error = RespBean.error("登录失败");
//                         resp.setContentType("application/json;charset=utf-8");
//                         PrintWriter out = resp.getWriter();
//                         out.write(new ObjectMapper().writeValueAsString(error));
//                         out.flush();
//                         out.close();
//                     }
//                 })
//                 .loginPage("/login")
//                 .permitAll()
//                 .and()
//                 .logout()
//                 .logoutUrl("/logout")
//                 .logoutSuccessHandler(new LogoutSuccessHandler() {
//                     @Override
//                     public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                         RespBean ok = RespBean.ok("注销成功！");
//                         resp.setContentType("application/json;charset=utf-8");
//                         PrintWriter out = resp.getWriter();
//                         out.write(new ObjectMapper().writeValueAsString(ok));
//                         out.flush();
//                         out.close();
//                     }
//                 })
//                 .permitAll()
//                 .and()
//                 .csrf()
//                 .disable()
//                 .exceptionHandling()
//                 .accessDeniedHandler(new AccessDeniedHandler() {
//                     @Override
//                     public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
//                         RespBean error = RespBean.error("权限不足，访问失败");
//                         resp.setStatus(403);
//                         resp.setContentType("application/json;charset=utf-8");
//                         PrintWriter out = resp.getWriter();
//                         out.write(new ObjectMapper().writeValueAsString(error));
//                         out.flush();
//                         out.close();
//                     }
//                 });
//
//     }
// }