// package com.xuegao.springboot_tool.orther.e.单元测试;
//
// import com.alibaba.fastjson.JSONObject;
// import com.baomidou.mybatisplus.extension.api.R;
// import com.xuegao.springboot_tool.model.doo.SysUserinfo;
// import org.junit.Assert;
// import org.junit.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;
// import org.springframework.web.bind.annotation.*;
//
// import javax.servlet.http.Cookie;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest
// @AutoConfigureMockMvc
// class UserControllerTest {
//
//     @Autowired
//     MockMvc mockMvc;
//
//     SysUserinfo SysUserinfo;
//
//     MultiValueMap<String, String> params;
//
//     @BeforeEach
//     public void setUp() throws Exception {
//         SysUserinfo = new SysUserinfo();
//         SysUserinfo.setUsername("123456");
//         params = new LinkedMultiValueMap<>();
//         params.add("name", "codehome");
//     }
//
//     //测试get接口
//     @Test
//     public void queryUser() throws Exception {
//         String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/query")
//                 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                 .params(params)
//         ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//                 .andDo(MockMvcResultHandlers.print())
//                 .andReturn().getResponse()
//                 .getContentAsString();
//         Assert.assertEquals("调用成功", "codehome", result);
//     }
//
//     //测试post接口
//     @Test
//     void addUser() throws Exception {
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(JSONObject.toJSONString(SysUserinfo))
//                 .accept(MediaType.APPLICATION_JSON)
//         ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//                 .andDo(MockMvcResultHandlers.print())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.data.passwd").value("123456"));
//     }
//
//     //测试cookie
//     @Test
//     void testCookie() throws Exception {
//         String token = mockMvc.perform(MockMvcRequestBuilders.get("/user/cookie")
//                 .cookie(new Cookie("token", "123456")))
//                 .andDo(MockMvcResultHandlers.print())
//                 .andReturn().getResponse()
//                 .getContentAsString();
//         Assert.assertEquals("token从cookie中获取成功", "123456", token);
//     }
// }
//
// //测试的接口类
// @RestController
// @RequestMapping("/user")
// public class UserController {
//
//     @GetMapping("/query")
//     public String queryUser(String name) {
//         return name;
//     }
//
//     @PostMapping("/add")
//     public R addUser(@RequestBody SysUserinfo SysUserinfo) {
//         return R.ok(SysUserinfo);
//     }
//
//     @GetMapping("/cookie")
//     public String testCookie(@CookieValue("token") String token) {
//         return token;
//     }
// }