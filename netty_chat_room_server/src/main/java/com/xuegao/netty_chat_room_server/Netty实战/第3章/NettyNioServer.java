package com.xuegao.netty_chat_room_server.Netty实战.第3章;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty实战.第3章
 * <br/> @ClassName：NettyNioServer
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/03 17:51
 */
public class NettyNioServer {
    public void server(int port) throws InterruptedException {
        final ByteBuf buffer = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi oio", StandardCharsets.UTF_8));

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            // 创建 server 的 bootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventLoopGroup)
                    // 使用 OioServerSocketChannel 以允许阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 指定 ChannelInitializer 对于每个已接受的链接都调用他
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // 添加一个 channelInboundHandlerAdapter 以拦截和处理事件
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            // 将消息写到客户端，并添加 ChannelFutureListener，以便消息一被写完就关闭链接
                                            ctx.writeAndFlush(buffer.duplicate())
                                                    .addListener(ChannelFutureListener.CLOSE);
                                        }
                                    });

                        }
                    });
            // 绑定服务器，以接受链接
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 释放所有的资源
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}