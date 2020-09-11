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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config.security
 * <br/> @ClassName：SecurityConfig2020_7_27
 * <br/> @Description：
 * <br/> @author：xuegao
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
                // 设置 Form 表单登陆
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                // 不可以直接返回页面，只能通过handler处理
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                // 所有人都可以访问
                .permitAll();

        httpSecurity
                // 配置请求地址的权限
                .authorizeRequests()
                // 所有用户可访问
                .antMatchers("/login.html", "/login").permitAll()
                /** ====================================== */
                // 需要对外暴露的资源路径
                // user 和 admin角色可以访问
                .antMatchers("/order").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                /** ====================================== */
                // admin角色可以访问
                .antMatchers("/system/user", "/system/c3role", "/system/menu").hasAnyRole("ADMIN")
                /** ====================================== */
                // 所有的请求都必须被认证，必须登录之后访问
                .anyRequest().authenticated();

        // 关闭防火墙，禁用csrf攻击防御，否则无法登录成功
        httpSecurity.csrf().disable();

        // 登出功能，所有人都可以访问
        httpSecurity.logout().logoutUrl("logout").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 使用内存中的
                .inMemoryAuthentication()
                // 使用不加密的
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                // 配置 admin 用户
                .withUser("admin").password("admin").roles("ADMIN")
                // 配置 normal 用户
                .and().withUser("normal").password("normal").roles("NORMAL");
        auth
                // 使用 重写的 方法
                .userDetailsService(userDetailsService)

                .passwordEncoder(getPasswordEncoder());
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
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

}
