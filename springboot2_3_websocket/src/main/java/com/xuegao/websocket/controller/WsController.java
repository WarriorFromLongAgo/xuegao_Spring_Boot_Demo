package com.xuegao.websocket.controller;

import com.google.common.collect.Lists;
import com.xuegao.websocket.constant.consist.Constant;
import com.xuegao.websocket.model.bo.WiselyMessage;
import com.xuegao.websocket.model.bo.WiselyResponse;
import com.xuegao.websocket.service.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.websocket.controller
 * <br/> @ClassName：WsController
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/7/23 21:21
 */
@Controller
public class WsController {
    @Resource
    WebSocketService webSocketService;

    @MessageMapping(Constant.webSocket.FORETOSERVERPATH)
    //@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @SendTo(Constant.webSocket.PRODUCERPATH)//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public WiselyResponse say(WiselyMessage message) throws Exception {
        List<String> users = Lists.newArrayList();
        users.add("d892bf12bf7d11e793b69c5c8e6f60fb");//此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        webSocketService.send2Users(users, new WiselyResponse("admin hello"));

        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }
}