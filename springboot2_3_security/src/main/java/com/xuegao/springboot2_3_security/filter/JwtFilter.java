package com.xuegao.springboot2_3_security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.filter
 * <br/> @ClassName：JwtFilter
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/18 14:13
 */
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        // 首先从请求头中提取出 authorization 字段，这个字段对应的 value 就是用户的 token。
        // 将提取出来的 token 字符串转换为一个 Claims 对象，再从 Claims 对象中提取出当前用户名和用户角色，
        // 创建一个 UsernamePasswordAuthenticationToken 放到当前的 Context 中，然后执行过滤链使请求继续执行下去。

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("Authorization");
        System.out.println("========================================");
        System.out.println("doFilter");
        System.out.println(((HttpServletRequest) servletRequest).getRequestURI());
        System.out.println(jwtToken);
        System.out.println("========================================");
        if (!StringUtils.isEmpty(jwtToken)) {
            Claims claims = Jwts.parser()
                    .setSigningKey("sang@123")
                    .parseClaimsJws(jwtToken.replace("Bearer", ""))
                    .getBody();
            //获取当前登录用户名
            String username = claims.getSubject();
            List<GrantedAuthority> authorities
                    = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authoritieList"));
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(req, servletResponse);
    }
}