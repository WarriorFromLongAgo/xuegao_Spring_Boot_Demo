package com.xuegao.springboot2_3_security.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.config.security
 * <br/> @ClassName：MyAuthenticationSuccessHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/26 1:22
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write("{\"code\":\"200\", \"message\":\"登陆成功\"}");
    }
}