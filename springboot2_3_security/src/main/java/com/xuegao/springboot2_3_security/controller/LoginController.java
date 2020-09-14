package com.xuegao.springboot2_3_security.controller;

import com.xuegao.springboot2_3_security.domain.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.controller
 * <br/> @ClassName：IndexController
 * <br/> @Description：http://www.itboyhub.com/2019/1224/springboot-security-login-json.html
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 14:13
 */
@RestController(value = "login")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public RespBean login() {
        return RespBean.error("尚未登录，请登录");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}