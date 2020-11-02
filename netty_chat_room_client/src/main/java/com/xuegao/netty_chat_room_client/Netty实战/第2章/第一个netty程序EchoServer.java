package com.xuegao.netty_chat_room_client.Netty实战.第2章;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.util.ObjectUtils;

import java.net.InetSocketAddress;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.Netty实战.第2章
 * <br/> @ClassName：EchoServer
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/02 20:47
 */
public class 第一个netty程序EchoServer {
    private final int port;

    public 第一个netty程序EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (!ObjectUtils.isEmpty(args)) {
            int port = Integer.parseInt(args[0]);
            // 设置端口
            // 调用服务的start方法
            new 第一个netty程序EchoServer(port).start();
        }
    }

    public void start() throws InterruptedException {
        final 第一个netty程序EchoServerHandler echoServerHandler = new 第一个netty程序EchoServerHandler();
        // 创建 eventloopgroup
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            // 创建 server boot strap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    // 指定所使用的NIO传输channel
                    .channel(NioServerSocketChannel.class)
                    // 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 添加一个 echoServerHandler 到 子channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // echoServerHandler 被标注 @ChannelHandler.Sharable ，所以我们总是可以使用同样的实例
                            // 这里对于所有的客户端连接来说，都会使用同一个EchoServerHandler，因为其被标注为@Sharable，
                            // 这将在后面的章节中讲到。——译者注
                            ch.pipeline().addLast(echoServerHandler);
                        }
                    });
            // 异步的绑定服务器，调用sync() 方法阻塞等待直到绑定完成
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            // 获取channel的closefuture，并阻塞当前线程知道他完成为止
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭 eventLoopGroup 释放所有的资源
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}