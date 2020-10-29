package com.xuegao.netty_chat_room_server.NIO框架入门一;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.NIO框架入门一
 * <br/> @ClassName：EchoSeverHandler
 * <br/> @Description：服务端Handler类 EchoSeverHandler
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 19:48
 */
public class EchoSeverHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        // 读取收到的数据
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("【NOTE】>>>>>> 收到客户端的数据：" + body);

        // 回复一条信息给客户端
        channelHandlerContext.writeAndFlush(new DatagramPacket(
                Unpooled.copiedBuffer("Hello，我是Server，我的时间戳是" + System.currentTimeMillis()
                        , StandardCharsets.UTF_8)
                , datagramPacket.sender())).sync();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelRegistered(channelHandlerContext);
        System.out.println("i go it");
    }
}