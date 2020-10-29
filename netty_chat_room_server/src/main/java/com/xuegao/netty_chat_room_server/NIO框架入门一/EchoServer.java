package com.xuegao.netty_chat_room_server.NIO框架入门一;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server
 * <br/> @ClassName：NIO框架入门一
 * <br/> @Description：服务端主类 EchoServer.java
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 19:44
 */
public class EchoServer {
    // http://www.52im.net/thread-367-1-1.html
    // NIO框架入门(一)：服务端基于Netty4的UDP双向通信Demo演示 [附件下载]
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup)
                .channel(NioDatagramChannel.class)
                .handler(new EchoSeverHandler());

        // 服务端监听在9999端口
        bootstrap.bind(9999).sync().channel().closeFuture().await();
    }
}