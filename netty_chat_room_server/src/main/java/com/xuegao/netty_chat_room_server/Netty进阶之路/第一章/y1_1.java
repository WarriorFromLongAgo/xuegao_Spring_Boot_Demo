package com.xuegao.netty_chat_room_server.Netty进阶之路.第一章;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty进阶之路.第一章
 * <br/> @ClassName：y1_1
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/28 19:52
 */
public class y1_1 {
    private static final Logger log = LoggerFactory.getLogger(y1_1.class);

    public static void main(String[] args) {
        NioEventLoopGroup bootGroup = null;
        NioEventLoopGroup workGroup = null;
        try {
            bootGroup = new NioEventLoopGroup();
            workGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bootGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(18008).sync();
            // 1
            // channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
            //     @Override
            //     public void operationComplete(ChannelFuture future) throws Exception {
            //         log.info(channelFuture.channel().toString() + " 链路关闭");
            //     }
            // });

            // 2
            // channelFuture.channel().closeFuture().sync();

            // 3
            NioEventLoopGroup finalBootGroup = bootGroup;
            NioEventLoopGroup finalWorkGroup = workGroup;
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    finalBootGroup.shutdownGracefully();
                    finalWorkGroup.shutdownGracefully();
                    log.info(channelFuture.channel().toString() + " 链路关闭");
                }
            });
        } catch (InterruptedException e) {
            // bootGroup.shutdownGracefully();
            // workGroup.shutdownGracefully();
        }
    }
}