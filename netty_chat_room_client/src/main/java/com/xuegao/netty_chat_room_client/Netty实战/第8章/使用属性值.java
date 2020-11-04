package com.xuegao.netty_chat_room_client.Netty实战.第8章;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.Netty实战.第8章
 * <br/> @ClassName：使用属性值
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/04 18:29
 */
public class 使用属性值 {
    public static void main(String[] args) {
        AttributeKey<Integer> attributeKey = AttributeKey.newInstance("ID");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received data");
                    }

                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        Integer integer = ctx.channel().attr(attributeKey).get();
                        System.out.println(integer);
                    }
                });
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        bootstrap.attr(attributeKey, 123456);
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 9999));
        channelFuture.syncUninterruptibly();
    }
}