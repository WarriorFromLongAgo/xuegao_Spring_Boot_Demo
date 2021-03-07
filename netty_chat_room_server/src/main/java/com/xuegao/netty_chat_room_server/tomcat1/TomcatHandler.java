package com.xuegao.netty_chat_room_server.tomcat1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

import java.util.Map;

/**
 * @author luyanan
 * @since 2019/9/19
 * <p></p>
 **/
public class TomcatHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Servlet> servletMap = null;

    public TomcatHandler(Map<String, Servlet> servletMap) {
        this.servletMap = servletMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            Request request = new Request(ctx, httpRequest);

            Response response = new Response(ctx, httpRequest);

            String url = request.getUrl();
            if (servletMap.containsKey(url)) {
                servletMap.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }

        }
    }
}