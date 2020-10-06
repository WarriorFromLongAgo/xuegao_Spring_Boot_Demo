// package com.xuegao.springboot_tool.mvc.mvcconfigurer.cors;
//
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot_tool.mvc.mvcconfigurer.cors
//  * <br/> @ClassName：局部
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2020/8/30 16:05
//  */
// // 使用CrossOrigin注解（局部跨域配置）
// public class 局部 {
//     @RequestMapping("/cors")
//     @ResponseBody
//     @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
//     public String cors( ){
//         return "cors";
//     }
// 将CrossOrigin注解加在Controller层的方法上，该方法定义的RequestMapping端点将支持跨域访问
// 将CrossOrigin注解加在Controller层的类定义处，整个类所有的方法对应的RequestMapping端点都将支持跨域访问
//     https://juejin.im/post/6844903991558537223
// }