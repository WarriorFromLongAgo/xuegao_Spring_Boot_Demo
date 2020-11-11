package com.xuegao.netty_chat_room_client.王仔编程B站;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.王仔编程B站
 * <br/> @ClassName：TimeClient
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/11 15:56
 */
public class TimeClient {
    private Logger log = LoggerFactory.getLogger(TimeClient.class);

    public void connect(int port, String hostName) {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            // TCP_NODELAY就是用于启用或关于Nagle算法。
            // 如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；
            // 如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
            bootstrap.group(nioEventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(hostName, port))
                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            log.info("Client is ready");
            ChannelFuture channelFuture = bootstrap.connect().sync();
            log.info("Client is start");
            channelFuture.channel().closeFuture().sync();
            log.info("Client is closeFuture");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nioEventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TimeClient().connect(10000, "127.0.0.1");
    }
}