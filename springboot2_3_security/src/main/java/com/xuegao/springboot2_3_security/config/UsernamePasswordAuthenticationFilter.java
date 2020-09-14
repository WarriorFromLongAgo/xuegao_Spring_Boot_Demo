package com.xuegao.springboot2_3_security.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config
 * <br/> @ClassName：UsernamePasswordAuthenticationFilter    从json登录
 * <br/> @Description：http://www.itboyhub.com/2019/1224/springboot-security-login-json.html
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 15:31
 */
// public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//     public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
//     public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
//
//     private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
//     private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
//     private boolean postOnly = true;
//
//     public UsernamePasswordAuthenticationFilter() {
//         super(new AntPathRequestMatcher("/login", "POST"));
//     }
//
//     @Override
//     public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//         if (postOnly && !"POST".equals(request.getMethod())) {
//             throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//         }
//
//         String username = obtainUsername(request);
//         String password = obtainPassword(request);
//
//         if (username == null) {
//             username = "";
//         }
//
//         if (password == null) {
//             password = "";
//         }
//
//         username = username.trim();
//
//         UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//                 username, password);
//
//         // Allow subclasses to set the "details" property
//         // setDetails(request, authRequest);
//
//         return this.getAuthenticationManager().authenticate(authRequest);
//     }
//
//     protected String obtainPassword(HttpServletRequest request) {
//         return request.getParameter(passwordParameter);
//     }
//
//     protected String obtainUsername(HttpServletRequest request) {
//         return request.getParameter(usernameParameter);
//     }
//     //...
//     //...
// }