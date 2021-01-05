package com.xuegao.netty_chat_room_server.Netty进阶之路.第五章;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class LoadRunnerSleepClientHandler extends ChannelInboundHandlerAdapter {

    static final int SIZE = Integer.parseInt(System.getProperty("size", "10240"));
    private final ByteBuf firstMessage;
    Runnable loadRunner;
    AtomicLong sendSum = new AtomicLong(0);
    Runnable profileMonitor;

    /**
     * Creates a client-side handler.
     */
    public LoadRunnerSleepClientHandler() {
        firstMessage = Unpooled.buffer(SIZE);
        for (int i = 0; i < firstMessage.capacity(); i++) {
            firstMessage.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        loadRunner = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ByteBuf msg = null;
                while (true) {
                    byte[] body = new byte[SIZE];
                    msg = Unpooled.wrappedBuffer(body);
                    ctx.writeAndFlush(msg);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(loadRunner, "LoadRunner-Thread").start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
