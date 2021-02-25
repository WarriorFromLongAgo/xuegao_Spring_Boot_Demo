package com.xuegao.netty_chat_room_client.http服务器.博客园逃离沙漠;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * netty server
 * 2018/11/1.
 */
public class HttpServer {
    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer());

        ChannelFuture future = null;
        try {
            future = bootstrap.bind(new InetSocketAddress(port)).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println(" server start up on port : " + port);


        assert future != null;
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                // 客户端断线重连逻辑
                if (future.isSuccess()) {
                    log.info("连接Netty服务端成功");
                } else {
                    log.info("连接失败，进行断线重连");
                    future.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            start();
                        }
                    }, 20, TimeUnit.SECONDS);
                }
            }
        });

        try {
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
