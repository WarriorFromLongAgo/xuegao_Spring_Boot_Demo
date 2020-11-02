package com.xuegao.netty_chat_room_server.Netty实战.第1章;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty实战.第1章
 * <br/> @ClassName：ConnectHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/02 16:24
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当一个新的连接已经被建立时，channelActive(ChannelHandlerContext ctx) 将会被调用
        super.channelActive(ctx);
    }
}