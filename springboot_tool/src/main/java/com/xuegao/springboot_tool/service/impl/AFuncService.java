// package com.xuegao.springboot_tool.service.impl;
//
// import com.xuegao.springboot_tool.spring.EventContainer;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
//
// @Service
// public class AFuncService {
//     private static final Logger log = LoggerFactory.getLogger(AFuncService.class);
//     public void login() {
//         log.info("[{}]抛出登录事件 ... ", this.getClass());
//         EventContainer.submit(LoginEvent.class);
//     }
// }