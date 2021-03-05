package com.xuegao.netty_chat_room_server.tomcat1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author luyanan
 * @since 2019/9/19
 * <p>请求封装</p>
 **/
public class Request {

    private ChannelHandlerContext chc;


    private HttpRequest httpRequest;

    public Request(ChannelHandlerContext chc, HttpRequest httpRequest) {
        this.chc = chc;
        this.httpRequest = httpRequest;
    }


    public String getUrl() {
        return this.httpRequest.uri();
    }

    public String getMethod() {
        return httpRequest.method().name();
    }


}