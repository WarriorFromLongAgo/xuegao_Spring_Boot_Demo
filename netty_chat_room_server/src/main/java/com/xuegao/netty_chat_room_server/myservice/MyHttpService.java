package com.xuegao.netty_chat_room_server.myservice;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.myservice
 * <br/> @ClassName：MyHttpService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/17 14:12
 */
public interface MyHttpService {
    void handleHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest httpRequest);
}