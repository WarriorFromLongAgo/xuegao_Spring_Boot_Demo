package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.service.interfaces.IMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：MqController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/28 22:46
 */
@RestController
public class MqController {

    private final IMqService iMqService;

    @Autowired
    public MqController(IMqService iMqService) {
        this.iMqService = iMqService;
    }

    @GetMapping("/transaction1")
    public WrappedResponse<String> transaction1() {
        iMqService.transaction1();
        return WrappedResponse.success();
    }
}