package com.xuegao.netty_chat_room_server.Netty进阶之路.第三章;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty进阶之路.第三章
 * <br/> @ClassName：y3_1_1
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/29 16:51
 */
public class y3_1_1 extends ChannelInboundHandlerAdapter {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();
    PooledByteBufAllocator pooledByteBufAllocator = new PooledByteBufAllocator(false);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] byteArr = new byte[byteBuf.readableBytes()];
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 解析请求消息，做路由转发，代码省略
                // 转发成功，返回响应客户端
                ByteBuf respMessage = pooledByteBufAllocator.heapBuffer(byteArr.length);
                // 简化处理，将请求直接返回
                respMessage.writeBytes(byteArr);
                ctx.writeAndFlush(respMessage);
            }
        });
    }
}