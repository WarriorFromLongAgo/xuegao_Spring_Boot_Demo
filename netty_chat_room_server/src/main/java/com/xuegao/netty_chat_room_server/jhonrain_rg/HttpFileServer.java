package com.xuegao.netty_chat_room_server.jhonrain_rg;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.jhonrain_rg
 * <br/> @ClassName：HttpFileServer
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/7 20:37
 */
public class HttpFileServer {
    /**
     * <pre>服务的初始化，绑定端口</pre>
     *
     * @param port 端口号
     * @throws InterruptedException
     */
    public void bind(int port) throws InterruptedException {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    /** 非阻塞模式启动 **/
                    .channel(NioServerSocketChannel.class)
                    /** 保持长链接 **/
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    /** 日志级别 **/
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    // 添加请求消息解码器
                                    .addLast("http-decoder", new HttpRequestDecoder())
                                    // HttpObjectAggregator
                                    .addLast("http-aggregator", new HttpObjectAggregator(65535))
                                    // 添加响应信息解码器
                                    .addLast("http-encoder", new HttpResponseEncoder())
                                    // 大文件流支持
                                    .addLast("http-chunked", new ChunkedWriteHandler());
                                    // 文件服务处理器
                                    // .addLast(new HttpFileServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        new HttpFileServer().bind(port);
    }
}