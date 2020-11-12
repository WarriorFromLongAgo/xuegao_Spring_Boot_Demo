package com.xuegao.netty_chat_room_client.王仔编程B站;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.王仔编程B站
 * <br/> @ClassName：TimeClientHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/11 15:57
 */
@ChannelHandler.Sharable
// public class TimeClientHandler extends ChannelInboundHandlerAdapter {
public class TimeClientHandler extends SimpleChannelInboundHandler {
    private Logger log = LoggerFactory.getLogger(TimeClientHandler.class);

    byte[] request;

    public TimeClientHandler() {
        request = ("TIME ORDER" + System.lineSeparator()).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送给服务端，但是客户端接收不到
        // while (true) {
            // 发送消息给server
            // ByteBuf byteBuf;
            // byteBuf = Unpooled.buffer(request.length);
            // byteBuf.writeBytes(request);
            // ctx.writeAndFlush(byteBuf);

            // ctx.writeAndFlush(Unpooled.copiedBuffer("TIME ORDER" + System.lineSeparator(), StandardCharsets.UTF_8));
            // TimeUnit.SECONDS.sleep(1);
        // }

        // 发送给服务端，客户端可以接收到
        for (int i = 0; i < 10; i++) {
            // 发送消息给server
            ByteBuf byteBuf;
            byteBuf = Unpooled.buffer(request.length);
            byteBuf.writeBytes(request);
            ctx.writeAndFlush(byteBuf);
        }
    }

    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //     log.info("channelRead 11111");
    //     if (!ObjectUtils.isEmpty(msg)) {
    //         String message = msg.toString();
    //         log.info("现在时间 = {}", message);
    //     }
    //     log.info("channelRead 11111111");
    //     ctx.channel().close();
    // }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 记录已接收消息的转储
        log.info("channelRead0 22222222");
        // ByteBuf byteBuf = (ByteBuf) msg;
        log.info("channelRead0 = {} ", msg.toString());
    }

    // @Override
    // protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    //     log.info("channelRead 2222222222222");
    //     if (!ObjectUtils.isEmpty(msg)) {
    //         String message = msg.toString();
    //         log.info("现在时间 = {}", message);
    //     }
    //     log.info("channelRead 2222222222222222222222222");
    // }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("channelReadComplete");
        super.channelReadComplete(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("TimeClientHandler exceptionCaught = {} ", cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}