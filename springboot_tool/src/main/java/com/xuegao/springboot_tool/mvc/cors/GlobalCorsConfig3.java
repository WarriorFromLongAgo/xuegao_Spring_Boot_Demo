// package com.xuegao.springboot_tool.mvc.cors;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import javax.servlet.http.HttpServletResponse;
// // 使用HttpServletResponse设置响应头(局部跨域配置)   https://juejin.im/post/6844903991558537223
// @Configuration
// public class GlobalCorsConfig3 {
//     @RequestMapping("/cors")
//     @ResponseBody
//     public String cors(HttpServletResponse response) {
//         //使用HttpServletResponse定义HTTP请求头，最原始的方法也是最通用的方法
//         response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
//         return "cors";
//     }
// }
