package com.xuegao.netty_chat_room_client.Netty实战.第1章;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.Netty实战.第1章
 * <br/> @ClassName：ChannelFutureListener
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/02 16:35
 */
public class ChannelFutureListener {
    public static void main(String[] args) {
        // // 异步的连接到远程节点
        // Channel channel = ;
        // ChannelFuture channelFuture = channel.connect(new InetSocketAddress("127.0.0.1", 25));
        // // 注册 ChannelFutureListener 以便在操作完成时获得通知
        // channelFuture.addListener(new io.netty.channel.ChannelFutureListener() {
        //     @Override
        //     public void operationComplete(ChannelFuture future) throws Exception {
        //         // 如果操作是成功的，啧创建一个ByteBuffer以持有数据
        //         if (future.isSuccess()) {
        //             ByteBuf buffer = Unpooled.copiedBuffer("hello", StandardCharsets.UTF_8);
        //             ChannelFuture writeAndFlush = future.channel().writeAndFlush(buffer);
        //             // 将数据异步的发送到远程节点，返回一个 ChannelFuture
        //         } else {
        //             // 访问错误，打印堆栈
        //             Throwable throwable = future.cause();
        //             throwable.printStackTrace();
        //         }
        //     }
        // });
    }
}