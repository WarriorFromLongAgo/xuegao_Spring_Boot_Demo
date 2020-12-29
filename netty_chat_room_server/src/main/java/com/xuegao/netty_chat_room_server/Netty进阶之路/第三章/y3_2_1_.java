package com.xuegao.netty_chat_room_server.Netty进阶之路.第三章;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty进阶之路.第三章
 * <br/> @ClassName：y3_2_1
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/29 20:41
 */
public class y3_2_1_ {
    public static void main(String[] args) {
        PoolTest();
    }

    static void PoolTest() {
        PooledByteBufAllocator pooledByteBufAllocator = new PooledByteBufAllocator(false);
        long beginTime = System.currentTimeMillis();
        ByteBuf buf = null;
        int maxTime = 1_0000_0000;
        for (int i = 0; i < maxTime; i++) {
            buf = pooledByteBufAllocator.heapBuffer(10 * 1024);
            buf.release();
        }
        System.out.println("execute " + maxTime + " time cost time: " + (System.currentTimeMillis() - beginTime));
    }
}