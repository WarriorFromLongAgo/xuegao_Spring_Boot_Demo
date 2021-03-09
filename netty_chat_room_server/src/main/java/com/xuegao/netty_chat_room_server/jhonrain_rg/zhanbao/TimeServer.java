package com.xuegao.netty_chat_room_server.jhonrain_rg.zhanbao;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.jhonrain_rg.zhanbao
 * <br/> @ClassName：TimeServer
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/09 18:10
 */
public class TimeServer {

    public void bind(int port) throws InterruptedException {
        /** 配置服务端端NIO线程组 **/
        /**
         * bossGroup : 监听客户端链接
         * workGroup : 处理SocketChannel的网络读写
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new TimeServerHandler());
                        }
                    });
            /** 绑定端口，同步等待成功 **/
            ChannelFuture f = b.bind(port).sync();

            /** 等待服务端监听端口关闭 **/
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 12000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        new TimeServer().bind(port);
    }
}