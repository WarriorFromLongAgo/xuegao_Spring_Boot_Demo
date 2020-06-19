package com.xuegao.springboot2_3_security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuegao.springboot2_3_security.domain.RespBean;
import com.xuegao.springboot2_3_security.filter.JwtFilter;
import com.xuegao.springboot2_3_security.filter.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config.WebSecurityConfigJwt
 * <br/> @ClassName：WebSecurityConfigJwt
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/18 14:13
 */
@Configuration
public class WebSecurityConfigJwt extends WebSecurityConfigurerAdapter {

    // 简单起见，这里我并未对密码进行加密，因此配置了 NoOpPasswordEncoder 的实例。
    // 简单起见，这里并未连接数据库，我直接在内存中配置了两个用户，两个用户具备不同的角色。
    // 配置路径规则时，
    //     /hello 接口必须要具备 user 角色才能访问，
    //     /admin 接口必须要具备 admin 角色才能访问，POST 请求并且是
    //     /login 接口则可以直接通过，其他接口必须认证后才能访问。
    // 最后配置上两个自定义的过滤器并且关闭掉 csrf 保护。

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()

                .withUser("admin")
                .password("123")
                .roles("admin")

                .and()

                .withUser("sang")
                .password("456")
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        // 首先通过 obtainUsername 和 obtainPassword 方法提取出请求里边的用户名/密码出来，提取方式就是 request.getParameter ，
        // 这也是为什么 Spring Security 中默认的表单登录要通过 key/value 的形式传递参数，而不能传递 JSON 参数，如果像传递 JSON 参数，修改这里的逻辑即可。
        //
        // 获取到请求里传递来的用户名/密码之后，接下来就构造一个 UsernamePasswordAuthenticationToken 对象，
        // 传入 username 和 password，username 对应了 UsernamePasswordAuthenticationToken 中的 principal 属性，
        // 而 password 则对应了它的 credentials 属性。
        //
        // 接下来 setDetails 方法给 details 属性赋值，
        // org.springframework.security.authentication.UsernamePasswordAuthenticationToken 本身是没有 details 属性的，
        // 这个属性在它的父类 AbstractAuthenticationToken 中。details 是一个对象，这个对象里边放的是 WebAuthenticationDetails 实例，
        // 该实例主要描述了两个信息，请求的 remoteAddress 以及请求的 sessionId。


        // 最后一步，就是调用 authenticate 方法去做校验了。
        // 首先获取 authentication 的 Class，判断当前 provider 是否支持该 authentication。
        // 如果支持，则调用 provider 的 authenticate 方法开始做校验，校验完成后，会返回一个新的 Authentication。一会来和大家捋这个方法的具体逻辑。
        // 这里的 provider 可能有多个，如果 provider 的 authenticate 方法没能正常返回一个 Authentication，则调用 provider 的 parent 的 authenticate 方法继续校验。
        // copyDetails 方法则用来把旧的 Token 的 details 属性拷贝到新的 Token 中来。
        // 接下来会调用 eraseCredentials 方法擦除凭证信息，也就是你的密码，这个擦除方法比较简单，就是将 Token 中的 credentials 属性置空。
        // 最后通过 publishAuthenticationSuccess 方法将登录成功的事件广播出去。


        http.authorizeRequests()
                .antMatchers("/hello")
                .hasRole("user")
                .antMatchers("/admin")
                .hasRole("admin")
                .antMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                //其他配置
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request,
                                         HttpServletResponse response,
                                         AuthenticationException authException) throws IOException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        RespBean respBean = RespBean.error("访问失败!");
                        if (authException instanceof InsufficientAuthenticationException) {
                            respBean.setMsg("请求失败，请联系管理员!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .addFilterBefore(
                        new JwtLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf()
                .disable();
    }
}