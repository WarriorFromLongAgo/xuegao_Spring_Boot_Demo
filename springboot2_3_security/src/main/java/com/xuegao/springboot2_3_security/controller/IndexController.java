package com.xuegao.springboot2_3_security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.controller
 * <br/> @ClassName：IndexController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/18 14:13
 */
@RestController(value = "index")
public class IndexController {

    @RequestMapping(value = {"/", "/index3"}, method = RequestMethod.GET)
    public String index3() {
        return "hello index";
    }

    @PermitAll
    @GetMapping("/echo")
    public String demo() {
        return "示例返回";
    }

    // 具备这个权限，才可以访问
    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public String hello2() {
        return "hello jwt !";
    }

    // 这个方法，可以不登录就可以访问
    @Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
    @RequestMapping(value = "/admin3", method = RequestMethod.GET)
    public String admin1() {
        return "hello admin !";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "我是管理员";
    }

    // 在前面拦截
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @RequestMapping(path = "/preAuthorize", method = RequestMethod.GET)
    public String preAuthorize() {
        return "askdadada";
    }

    // 在前面拦截
    // 两个权限拥有任意一个可以访问
    @PreAuthorize(value = "hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @RequestMapping(path = "/preAuthorize", method = RequestMethod.GET)
    public String preAuthorize2() {
        return "askdadada";
    }

    // 在前面拦截
    // 必须拥有这个权限才可以访问
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @RequestMapping(path = "/preAuthorize", method = RequestMethod.GET)
    public String preAuthorize3() {
        return "askdadada";
    }

    // 在后面拦截
    @PostAuthorize(value = "hasAuthority('ROLE_USER')")
    @RequestMapping(path = "/preAuthorize", method = RequestMethod.GET)
    public String postAuthorize() {
        return "askdadada";
    }
}