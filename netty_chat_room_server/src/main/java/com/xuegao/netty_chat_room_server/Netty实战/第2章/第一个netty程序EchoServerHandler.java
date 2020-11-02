package com.xuegao.netty_chat_room_server.Netty实战.第2章;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty实战.第2章
 * <br/> @ClassName：第一个netty程序
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/02 18:27
 */
@ChannelHandler.Sharable
public class 第一个netty程序EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        // 将收到的消息打印到控制台
        System.out.println("Server received: " + byteBuf.toString(StandardCharsets.UTF_8));
        // 将收到的消息写给发送者，而不冲刷出站消息
        ctx.write(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        // 将未决消息冲刷到远程节点，并且关闭该channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        // 打印异常堆栈
        cause.printStackTrace();
        // 关闭该channel
        ctx.close();
    }
}