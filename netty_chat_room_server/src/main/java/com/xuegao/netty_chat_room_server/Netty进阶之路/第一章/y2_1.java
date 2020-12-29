package com.xuegao.netty_chat_room_server.Netty进阶之路.第一章;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty进阶之路.第一章
 * <br/> @ClassName：y2_1
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/28 20:56
 */
public class y2_1 {
    public static void main(String[] args) {

    }

    static void initClientPool(int poolSize) throws InterruptedException {
        for (int i = 0; i < poolSize; i++) {
            NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LoggingHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 19825)).sync();
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    eventLoopGroup.shutdownGracefully();
                }
            });
        }
    }

    static void initClientPool2(int poolSize) throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LoggingHandler());
                    }
                });
        for (int i = 0; i < poolSize; i++) {
            bootstrap.connect(new InetSocketAddress("127.0.0.1", 19825)).sync();
        }
    }
}