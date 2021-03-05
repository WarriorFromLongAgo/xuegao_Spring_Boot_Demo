package com.xuegao.netty_chat_room_server.tomcat2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

//业务处理handler
public class WuzzTomcatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            // 转交给我们自己的request实现
            WuzzRequest request = new WuzzRequest(ctx, req);
            // 转交给我们自己的response实现
            WuzzResponse response = new WuzzResponse(ctx, req);
            // 实际业务处理
            String url = request.getUrl();

            if (WuzzTomcat.servletMapping.containsKey(url)) {
                WuzzTomcat.servletMapping.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        throw new Exception(cause.getMessage());
    }
}