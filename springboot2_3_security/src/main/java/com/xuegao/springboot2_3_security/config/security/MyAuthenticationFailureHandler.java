package com.xuegao.springboot2_3_security.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config.security
 * <br/> @ClassName：MyAuthenticationFailureHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/26 1:23
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write("{\"code\":\"400\", \"message\":\"登陆失败\"}");
    }
}