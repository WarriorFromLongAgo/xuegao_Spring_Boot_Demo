package com.xuegao.netty_chat_room_client.Netty实战.第2章;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.Netty实战.第2章
 * <br/> @ClassName：第一个netty程序EchoClientHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/03 15:42
 */
// @ChannelHandler.Sharable 标记该类的实例可以被多个channel共享
@ChannelHandler.Sharable
public class 第一个netty程序EchoClientHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当被通知channel是活跃的时候，发送一条消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生异常时，记录错误并关闭channel
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 记录已接收消息的转储
        System.out.println("Client received: " + msg.toString());
    }
}