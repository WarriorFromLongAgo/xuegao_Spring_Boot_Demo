package com.xuegao.netty_chat_room_server.jhonrain_rg.zhanbao;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.jhonrain_rg.zhanbao
 * <br/> @ClassName：TimeServerHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/09 18:11
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("The Time Server Receive Order: " + body + "; the counter is: " + ++counter);
        String currentTime = "";
        if ("QUERY TIME ORDER".equalsIgnoreCase(body)) {
            currentTime = new Date(System.currentTimeMillis()).toString();
        } else {
            currentTime = "BAD ORDER";
        }
        currentTime += System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}