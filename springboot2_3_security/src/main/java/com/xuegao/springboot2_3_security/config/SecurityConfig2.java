package com.xuegao.springboot2_3_security.config;

import com.xuegao.springboot2_3_security.filter.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config
 * <br/> @ClassName：SecurityConfig2
 * <br/> @Description：http://www.itboyhub.com/2019/1224/springboot-security-login-json.html
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 14:30
 */
// @Configuration
// public class SecurityConfig2 extends WebSecurityConfigurerAdapter {

    // @Autowired
    // VerifyCodeFilter verifyCodeFilter;
    //
    // /**
    //  * <br/> @Title: 忽略拦截某些路径
    //  * <br/> @MethodName:  configure
    //  * <br/> @param webSecurity:
    //  * <br/> @Return void
    //  * <br/> @Description:
    //  * <br/> @author: xuegao
    //  * <br/> @date:  2020/6/18 15:15
    //  */
    // @Override
    // public void configure(WebSecurity webSecurity) throws Exception {
    //     webSecurity
    //             .ignoring()
    //             .antMatchers("/vercode")
    //             .antMatchers();
    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
    //     http
    //             .authorizeRequests()//开启登录配置
    //             // 表示访问 /hello 这个接口，需要具备 admin 这个角色
    //             .antMatchers("/hello")
    //             .hasRole("admin")
    //             // 表示剩余的其他接口，登录之后就能访问
    //             .anyRequest()
    //             .authenticated()
    //             .and()
    //             .formLogin()
    //             // 定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
    //             .loginPage("/login_before")
    //             // 登录处理接口
    //             .loginProcessingUrl("/doLogin")
    //             // 定义登录时，用户名的 key，默认为 username
    //             .usernameParameter("uname")
    //             // 定义登录时，用户密码的 key，默认为 password
    //             .passwordParameter("passwd")
    //             // 登录成功的处理器
    //             // 配置登录成功的回调，如果是前后端分离开发的话，登录成功后返回 JSON 即可
    //             .successHandler(new AuthenticationSuccessHandler() {
    //                 @Override
    //                 public void onAuthenticationSuccess(HttpServletRequest request,
    //                                                     HttpServletResponse response,
    //                                                     Authentication authentication) throws IOException {
    //                     response.setContentType("application/json;charset=utf-8");
    //                     PrintWriter writer = response.getWriter();
    //                     writer.write("success");
    //                     writer.flush();
    //                 }
    //             })
    //             // 配置登录失败的回调，如果是前后端分离开发的话，登录成功后返回 JSON 即可
    //             .failureHandler(new AuthenticationFailureHandler() {
    //                 @Override
    //                 public void onAuthenticationFailure(HttpServletRequest request,
    //                                                     HttpServletResponse response,
    //                                                     AuthenticationException exception) throws IOException {
    //                     response.setContentType("application/json;charset=utf-8");
    //                     PrintWriter writer = response.getWriter();
    //                     writer.write("fail");
    //                     writer.flush();
    //                 }
    //             })
    //             // 和表单登录相关的接口统统都直接通过
    //             .permitAll()
    //             .and()
    //             .logout()
    //             .logoutUrl("/logout")
    //             // 配置退出登录的回调，如果是前后端分离开发的话，登录成功后返回 JSON 即可
    //             .logoutSuccessHandler(new LogoutSuccessHandler() {
    //                 @Override
    //                 public void onLogoutSuccess(HttpServletRequest request,
    //                                             HttpServletResponse response,
    //                                             Authentication authentication) throws IOException {
    //                     response.setContentType("application/json;charset=utf-8");
    //                     PrintWriter writer = response.getWriter();
    //                     writer.write("logout success");
    //                     writer.flush();
    //                 }
    //             })
    //             .permitAll()
    //             .and()
    //             .httpBasic()
    //             .and()
    //             .csrf()
    //             .disable();
    // }
// }