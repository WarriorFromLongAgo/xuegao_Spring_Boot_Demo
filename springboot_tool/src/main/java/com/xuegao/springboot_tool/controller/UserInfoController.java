package com.xuegao.springboot_tool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：UserInfoController
 * <br/> @Description：
 * <br/> @author：花名：xuegao
 * <br/> @date：2020/6/28 10:21
 */
@RestController
public class UserInfoController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(path = {"/", "/index.html", "/index"}, method = RequestMethod.GET)
    public String indexController() {
        log.info("indexController = " + "hello");

        return "hello";
    }
}