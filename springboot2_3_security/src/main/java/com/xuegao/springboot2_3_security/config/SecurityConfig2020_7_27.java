package com.xuegao.springboot2_3_security.config;

import com.xuegao.springboot2_3_security.config.security.MyAuthenticationFailureHandler;
import com.xuegao.springboot2_3_security.config.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config.security
 * <br/> @ClassName：SecurityConfig2020_7_27
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/7/27 0:28
 */
@EnableWebSecurity
@Configuration
// 开启方法授权
// securedEnabled = @Secured(value = "ROLE_ADMIN")
// prePostEnabled = @PreAuthorize() @PostAuthorize()
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig2020_7_27 extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    // 强散列，密码的加密，匹配
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                // 不可以直接返回页面，只能通过handler处理
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        httpSecurity
                .authorizeRequests()
                .antMatchers("/login.html", "/login")
                .permitAll()
                /** ====================================== */

                // 需要对外暴露的资源路径
                .antMatchers("/order")
                // user 和 admin角色可以访问
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                /** ====================================== */

                .antMatchers("/system/user", "/system/c3role", "/system/menu")
                // admin角色可以访问
                .hasAnyRole("ADMIN")

                /** ====================================== */
                // 所有的请求都必须被认证，必须登录之后访问
                .anyRequest().authenticated();

        // 关闭防火墙，禁用csrf攻击防御，否则无法登录成功
        httpSecurity.csrf().disable();

        // 登出功能
        httpSecurity.logout().logoutUrl("logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    /**
     * <br/> @Title:
     * <br/> @MethodName:  roleHierarchy
     * <br/>
     * <br/> @Return org.springframework.security.access.hierarchicalroles.RoleHierarchy
     * <br/> @Description: 在这里我们提供了一个 RoleHierarchy 接口的实例，使用字符串来描述了角色之间的继承关系，
     * <br/> @Description: ROLE_dba 具备 ROLE_admin 的所有权限，而 ROLE_admin 则具备 ROLE_user 的所有权限，继承与继承之间用一个空格隔开。
     * <br/> @Description: 提供了这个 Bean 之后，以后所有具备 ROLE_user 角色才能访问的资源，
     * <br/> @Description: ROLE_dba 和 ROLE_admin 也都能访问，具备 ROLE_amdin 角色才能访问的资源， ROLE_dba 也能访问。
     * <br/> @author: 花名：xuegao
     * <br/> @date:  2020/6/18 16:10
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}
