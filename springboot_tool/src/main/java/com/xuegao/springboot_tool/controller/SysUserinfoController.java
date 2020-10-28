package com.xuegao.springboot_tool.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.springboot_tool.constant.aop.annotation.MyTest;
import com.xuegao.springboot_tool.constant.aop.annotation.PrintlnLog;
import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.model.PageInfo;
import com.xuegao.springboot_tool.model.PageQuery;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import com.xuegao.springboot_tool.service.interfaces.ISysUserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：SysUserinfoController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/28 10:21
 */
@RestController
public class SysUserinfoController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ISysUserinfoService sysUserinfoService;

    @Autowired
    public SysUserinfoController(ISysUserinfoService sysUserinfoService) {
        this.sysUserinfoService = sysUserinfoService;
    }

    @PrintlnLog(description = "主页详情-indexController")
    @RequestMapping(path = {"/", "/index.html", "/index"}, method = RequestMethod.GET)
    public String indexController() {
        // log.info("indexController = " + "hello");

        // 代码里面有异常，会进入AfterThrowing
        // int a = 2 / 0;

        System.out.println("indexController = " + "hello");
        return "hello";
    }

    // @PrintlnLog(description = "主页详情-test1Controller")
    @MyTest
    @RequestMapping(path = "/test1", method = RequestMethod.GET)
    public String test1Controller() {
        // log.info("indexController = " + "hello");

        // 代码里面有异常，会进入AfterThrowing
        // int a = 2 / 0;

        System.out.println("test1Controller = " + "test1");
        return "test1";
    }

    @RequestMapping(path = "/test1", method = RequestMethod.POST)
    public String test3Controller(@RequestBody @Valid SysUserinfo SysUserinfo) {
        // log.info("indexController = " + "hello");

        // 代码里面有异常，会进入AfterThrowing
        // int a = 2 / 0;

        System.out.println("test1Controller = " + "test1");
        return "test1";
    }

    @RequestMapping(path = "/page", method = RequestMethod.POST)
    public WrappedResponse<Object> page(@RequestBody PageQuery<SysUserinfo> pageQuery) {
        PageInfo<SysUserinfo> pageInfo = sysUserinfoService.page3(pageQuery);
        return WrappedResponse.success(pageInfo);
    }

}