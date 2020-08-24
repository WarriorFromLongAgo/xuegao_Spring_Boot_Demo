package com.xuegao.springboot_tool.单元测试;

import com.xuegao.springboot_tool.model.doo.Product;
import com.xuegao.springboot_tool.service.interfaces.IProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    Product product;

    MultiValueMap<String, String> params;

    @Autowired
    private IProductService productService;

    @BeforeEach
    public void setUp() throws Exception {
        product = new Product();
        // product.setName("123456");
        params = new LinkedMultiValueMap<>();
        // params.add("name", "codehome");
    }

    //测试get接口
    @Test
    public void seckillProductTest() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/product/seckillTest")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse()
                .getContentAsString();
        Assert.assertEquals("调用成功", "创建订单成功", result);
    }

    @Test
    public void seckillProductTest1() {
        boolean aBoolean = productService.seckillProduct(1L, 1);
        Assert.assertTrue("调用成功", aBoolean);
    }

    @Test
    public void seckillProductTest2() {
        boolean aBoolean = productService.seckillProduct(1L, 1);
        Assert.assertTrue("调用成功", aBoolean);
    }
}