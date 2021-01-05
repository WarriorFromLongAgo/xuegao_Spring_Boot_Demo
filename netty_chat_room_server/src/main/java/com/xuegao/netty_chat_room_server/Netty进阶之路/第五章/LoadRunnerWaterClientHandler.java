package com.xuegao.netty_chat_room_server.Netty进阶之路.第五章;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class LoadRunnerWaterClientHandler extends ChannelInboundHandlerAdapter {

    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    static Logger LOG = Logger.getLogger(LoadRunnerWaterClientHandler.class.getName());
    private final ByteBuf firstMessage;
    Runnable loadRunner;
    AtomicLong sendSum = new AtomicLong(0);
    Runnable profileMonitor;

    /**
     * Creates a client-side handler.
     */
    public LoadRunnerWaterClientHandler() {
        firstMessage = Unpooled.buffer(SIZE);
        for (int i = 0; i < firstMessage.capacity(); i++) {
            firstMessage.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        ctx.channel().config().setWriteBufferHighWaterMark(10 * 1024 * 1024);
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
                    if (ctx.channel().isWritable()) {
                        msg = Unpooled.wrappedBuffer("Netty OOM Example".getBytes());
                        ctx.writeAndFlush(msg);
                    } else {
                        LOG.warning("The write queue is busy : " + ctx.channel().unsafe().outboundBuffer().nioBufferSize());
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
