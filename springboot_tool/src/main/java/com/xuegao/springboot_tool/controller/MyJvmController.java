package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.model.doo.MyJvm;
import com.xuegao.springboot_tool.model.dto.RequestDTO;
import com.xuegao.springboot_tool.service.interfaces.IMyJvmService;
import com.xuegao.springboot_tool.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：MyJvmController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/9 11:07
 */
@RestController
@RequestMapping(path = "/my_jvm")
public class MyJvmController<T> extends BaseController<T> {

    @Autowired
    private IMyJvmService jvmService;

    @PostMapping("/cpu100")
    public WrappedResponse<T> cpu100(@RequestBody RequestDTO requestDTO) {
        Long id = Long.valueOf(requestDTO.getSource());

        jvmService.cpu100(id);
        return success();
    }

    @PostMapping("/StackOverflowError")
    public WrappedResponse<T> stackOverflowError() {

        jvmService.stackOverflowError();
        return success();
    }

    @PostMapping("/outOfMemoryError")
    public WrappedResponse<T> outOfMemoryError() {

        jvmService.outOfMemoryError();
        return success();
    }
}