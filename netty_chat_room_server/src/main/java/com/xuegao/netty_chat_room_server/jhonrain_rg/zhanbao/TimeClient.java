package com.xuegao.netty_chat_room_server.jhonrain_rg.zhanbao;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.jhonrain_rg.zhanbao
 * <br/> @ClassName：TimeClient
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/09 18:11
 */
public class TimeClient {
    public void connect(int port, String host) throws InterruptedException {
        /** 配置客户端NIO线程组 **/
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            /** 发起异步链接操作 **/
            ChannelFuture f = b.connect(host, port).sync();
            /** 等待客户端链路关闭 **/
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
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
        new TimeClient().connect(port, "127.0.0.1");
    }
}