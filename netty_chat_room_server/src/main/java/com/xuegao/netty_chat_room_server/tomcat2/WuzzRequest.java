package com.xuegao.netty_chat_room_server.tomcat2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

public class WuzzRequest {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public WuzzRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl() {
        return req.uri();
    }

    public String getMethod() {
        return req.method().name();
    }
}