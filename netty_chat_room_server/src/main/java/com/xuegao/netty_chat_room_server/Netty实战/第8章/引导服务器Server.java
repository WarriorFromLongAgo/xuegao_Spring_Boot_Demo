package com.xuegao.netty_chat_room_server.Netty实战.第8章;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty实战.第8章
 * <br/> @ClassName：引导服务器Server
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/04 17:34
 */
public class 引导服务器Server {
    public static void main(String[] args) {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        // 创建 ServerBootstrap
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 设置 EventLoopGroup，提供用于处理Channel事件的 EventLoopGroup
        serverBootstrap.group(nioEventLoopGroup)
                // 指定要使用的channel实现
                .channel(NioServerSocketChannel.class)
                // 设置用于 channel 事件和数据的 ChannelInboundHandler
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received data channelRead0");
                    }
                });
        // 通过配置好的 serverBootstrap 的实例绑定该 channel
        ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 9999));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Server bound");
                } else {
                    System.err.println("Bound attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });
    }
}