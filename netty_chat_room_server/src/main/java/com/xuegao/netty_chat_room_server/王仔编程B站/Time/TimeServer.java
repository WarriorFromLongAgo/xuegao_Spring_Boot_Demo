package com.xuegao.netty_chat_room_server.王仔编程B站.Time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.王仔编程B站
 * <br/> @ClassName：TimeServer
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/11 14:51
 */
public class TimeServer {
    private Logger log = LoggerFactory.getLogger(TimeServer.class);
    public void bind(int port) {
        // 创建两个group

        // 接收客户端链接
        NioEventLoopGroup bootGroup = new NioEventLoopGroup();
        // 进行 socketChannel 的网络读写
        // 实际业务操作请求
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 服务器启动类,配置服务器
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            // 加入客户端线程和网络读写
            serverBootstrap.group(bootGroup, workerGroup)
                    // 我要指定使用NioServerSocketChannel这种类型的通道 ,当我们是Http的时候，需要更换这个Channel的类型
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 指定处理SocketChannel 的处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 利用LineBasedFrameDecoder解决TCP粘包问题
                            // 行解码器LineBasedFrameDecoder
                            // 你也可以把它当成以换行符分割字节流的解码器
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            // 配置一個解码器
                            // https://blog.csdn.net/taxi1993/article/details/103140523
                            socketChannel.pipeline().addLast(new StringDecoder());
                            // 将我们的服务器处理类传递进去
                            socketChannel.pipeline().addLast(new TimeServerHandler());
                        }
                    })
                    // https://blog.csdn.net/yelllowcong/article/details/78173255
                    // 最大连接数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 是否启用心跳保活机制
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
            ;
            log.info("...Server is Ready...");
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            log.info("...Server is Start...");
            channelFuture.channel().closeFuture().sync();
            log.info("...Server is closeFuture...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程组
            bootGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TimeServer().bind(10000);
    }
}