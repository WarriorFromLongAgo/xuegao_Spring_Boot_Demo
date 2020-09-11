// package com.xuegao.springboot_tool.service.impl;
//
// import com.xuegao.springboot_tool.spring.ReceiveAnno;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
//  * <br/> @ClassName：BFuncService
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2020/8/19 18:02
//  */
// @Service
// public class BFuncService {
//
//     private static final Logger log = LoggerFactory.getLogger(BFuncService.class);
//
//     @ReceiveAnno(clz = LoginEvent.class)
//     private void doAfterLogin() {
//         log.info("[{}]监听到登录事件 ... ", this.getClass());
//     }
// }