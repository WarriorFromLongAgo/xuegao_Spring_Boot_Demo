package com.xuegao.netty_chat_room_server.王仔编程B站.Time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.王仔编程B站
 * <br/> @ClassName：TimeServerHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/11 14:57
 */
// EchoServerHandler被标注为@shareable,所以我们可以总是使用同样的案例
@ChannelHandler.Sharable
// public class TimeServerHandler extends SimpleChannelInboundHandler {
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    private Logger log = LoggerFactory.getLogger(TimeServerHandler.class);
    // https://blog.csdn.net/qq_33227649/article/details/78319613

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channelRead");
        String currentTime = null;
        if (!ObjectUtils.isEmpty(msg)) {
            String message = msg.toString();
            if ("TIME ORDER".equalsIgnoreCase(message)) {
                currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            } else {
                currentTime = "ERROR TOKEN";
            }
        }
        log.info(" 回馈消息 = {}", currentTime);
        ByteBuf byteBuf = Unpooled.copiedBuffer((currentTime + "===" + System.lineSeparator()).getBytes());
        // 将收到的消息写给发送者，而不冲刷出站消息
        ctx.write(byteBuf);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端第一次，发消息的时候
        log.info(" channelActive ");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("channelReadComplete");
        // ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        // 将未决消息冲刷到远程节点，并且关闭该channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("TimeServerHandler exceptionCaught = {} ", cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("i go it");
        super.channelRegistered(channelHandlerContext);
    }
}