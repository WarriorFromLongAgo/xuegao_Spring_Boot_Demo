package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController<T> extends BaseController<T> {
    @Autowired
    private IProductService productService;

    /**
     * 秒杀商品测试
     * @return
     */
    @GetMapping("/seckillTest")
    public WrappedResponse<T> seckillProductTest() {
        Boolean flag = productService.seckillProduct(1L, 1);
        return flag ? success("创建订单成功") : fail("库存不足");
    }

    @GetMapping("/init")
    public String init() {
        productService.init(1L);
        return "1";
    }

    @GetMapping("/decr")
    public String decr() {
        productService.decr(1L);
        return "1";
    }

    @GetMapping("/sout")
    public String sout() {
        productService.sout();
        return "1";
    }
}
