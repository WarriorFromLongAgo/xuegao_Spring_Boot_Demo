package com.xuegao.netty_chat_room_client.Netty实战.第8章;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;

import java.net.InetSocketAddress;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.Netty实战.第8章
 * <br/> @ClassName：引导客户端
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/04 17:07
 */
public class 引导客户端Client {
    public static void main(String[] args) {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        // 创建一个 Bootstrap 实例，以创建和链接新的客户端channel
        Bootstrap bootstrap = new Bootstrap();
        // 设置 EventLoopGroup，提供用于处理Channel事件的 EventLoopGroup
        bootstrap.group(nioEventLoopGroup)
                // 指定要使用的channel实现
                .channel(NioSctpChannel.class)
                // 设置用于 channel 事件和数据的 ChannelInboundHandler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received data channelRead0");
                    }
                });
        // 链接到主机
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 9999));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Connection established");
                } else {
                    System.err.println("Connection attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });
    }
}