package com.xuegao.springboot2_3_security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuegao.springboot2_3_security.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.filter
 * <br/> @ClassName：JwtLoginFilter
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/18 14:13
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // 自定义 JwtLoginFilter 继承自 AbstractAuthenticationProcessingFilter，并实现其中的三个默认方法。

    // attemptAuthentication 方法中，我们从登录参数中提取出用户名密码，
    // 然后调用 AuthenticationManager.authenticate() 方法去进行自动校验。
    // 第二步如果校验成功，就会来到 successfulAuthentication 回调中，在 successfulAuthentication 方法中，将用户角色遍历然后用一个 ,
        // 连接起来，然后再利用 Jwts 去生成 token，按照代码的顺序，生成过程一共配置了四个参数，
        // 分别是用户角色、主题、过期时间以及加密算法和密钥，然后将生成的 token 写出到客户端。
    // 第二步如果校验失败就会来到 unsuccessfulAuthentication 方法中，在这个方法中返回一个错误提示给客户端即可。

    public JwtLoginFilter(String defaultFilterProcessesUrl,
                          AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException {
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority())
                    .append(",");
        }
        System.out.println(as.toString());
        String jwt = Jwts.builder()
                //配置用户角色
                .claim("authoritieList", as)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512,"sang@123")
                .compact();
        log.error("======================================");
        log.error("successfulAuthentication");
        log.error(jwt);
        log.error("======================================");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(jwt));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("登录失败!");
        out.flush();
        out.close();
    }
}