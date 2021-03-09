package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.model.doo.Order;
import com.xuegao.springboot_tool.service.interfaces.IMysqlService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：MysqlController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/05 23:04
 */
@RestController
@RequestMapping("/mysql")
public class MysqlController {

    private final IMysqlService mysqlService;

    @Autowired
    public MysqlController(IMysqlService mysqlService) {
        this.mysqlService = mysqlService;
    }

    @GetMapping("/transactional")
    public WrappedResponse<T> transactional() {
        mysqlService.transactional();
        return WrappedResponse.success();
    }

    @GetMapping("/get")
    public WrappedResponse<Order> getById() {
        Order byId = mysqlService.getById();
        System.out.println(byId);
        return WrappedResponse.success(byId);
    }

}