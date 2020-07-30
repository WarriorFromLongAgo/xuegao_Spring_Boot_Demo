package com.xuegao.springboot2_3_security.service.impl;

import com.xuegao.springboot2_3_security.domain.Userinfo;
import com.xuegao.springboot2_3_security.service.interfaces.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.service.impl
 * <br/> @ClassName：JwtServiceImpl
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/7/30 0:20
 */
@Service
public class JwtServiceImpl implements IJwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        Authentication authentication = null;

        try {
            // 该方法会去调用 UserDetailServiceImpl loadUserByUsername
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("用户密码错误");
        }
        Userinfo principal = (Userinfo) authentication.getPrincipal();

        return null;
    }
}