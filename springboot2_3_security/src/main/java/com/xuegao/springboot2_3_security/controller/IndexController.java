package com.xuegao.springboot2_3_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.controller
 * <br/> @ClassName：IndexController
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/18 14:13
 */
@RestController(value = "index")
public class IndexController {

    @RequestMapping(value = {"/", "/index3"}, method = RequestMethod.GET)
    public String index3() {
        return "hello index";
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public String hello2() {
        return "hello jwt !";
    }

    @RequestMapping(value = "/admin3", method = RequestMethod.GET)
    public String admin1() {
        return "hello admin !";
    }
}