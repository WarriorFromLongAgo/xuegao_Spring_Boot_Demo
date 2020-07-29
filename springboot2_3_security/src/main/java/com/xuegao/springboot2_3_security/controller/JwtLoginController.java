package com.xuegao.springboot2_3_security.controller;

import com.xuegao.springboot2_3_security.domain.RespBean;
import com.xuegao.springboot2_3_security.service.interfaces.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.controller
 * <br/> @ClassName：JwtLoginController
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/7/30 0:18
 */
@RestController
public class JwtLoginController {

    @Autowired
    private IJwtService jwtService;

    @RequestMapping("jwtlogin")
    public RespBean login(String username, String password) {
        String token = jwtService.login(username, password);
        return RespBean.ok(token);
    }
}