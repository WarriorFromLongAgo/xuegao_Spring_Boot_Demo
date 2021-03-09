package com.xuegao.netty_chat_room_server.mininetty;

import com.xuegao.netty_chat_room_server.mininetty.pool.NioSelectorRunablePool;
import com.xuegao.netty_chat_room_server.mininetty.pool.Worker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

public class NioServerWorker extends AbstractNioSelector implements Worker {

    public NioServerWorker(Executor executor, String threadName, NioSelectorRunablePool selectorRunablePool) {
        super(executor, threadName, selectorRunablePool);
    }

    @Override
    protected void process(Selector selector) throws IOException {
        Set<SelectionKey> selectKeys = selector.selectedKeys();
        if (selectKeys.isEmpty()) {
            return;
        }
        for (Iterator<SelectionKey> ite = selectKeys.iterator(); ite.hasNext(); ) {
            SelectionKey key = ite.next();
            ite.remove();

            SocketChannel channel = (SocketChannel) key.channel();

            // �����ܳ���
            int ret = 0;
            boolean failure = true;
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            try {
                channel.read(buffer);
                failure = false;
            } catch (Exception e) {
                // ignore
            }
            // �ж������Ƿ��ѶϿ�
            if (ret < 0 || failure) {
                key.cancel();
                System.out.println(this.threadName + "�ͻ����ѶϿ�����");
            } else {
                System.out.println("�յ�����:" + new String(buffer.array()));

                // ��д����
                ByteBuffer outBuffer = ByteBuffer.wrap("\n received..".getBytes());
                channel.write(outBuffer);
            }
        }
    }

    @Override
    protected int select(Selector selector) throws IOException {
        return selector.select(500);
    }

    @Override
    public void registerNewChannelTask(final SocketChannel channel) {
        final Selector selector = this.selector;
        registerTask(new Runnable() {

            @Override
            public void run() {
                try {
                    channel.register(selector, SelectionKey.OP_READ);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
