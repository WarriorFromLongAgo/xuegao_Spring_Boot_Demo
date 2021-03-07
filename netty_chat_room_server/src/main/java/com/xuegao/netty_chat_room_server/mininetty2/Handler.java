package com.xuegao.netty_chat_room_server.mininetty2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;


public class Handler implements Runnable {

    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(Integer.MAX_VALUE);
    ByteBuffer output = ByteBuffer.allocate(Integer.MAX_VALUE);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        //设置为非阻塞模式
        socketChannel.configureBlocking(false);
        //此处的0，表示不关注任何时间
        selectionKey = this.socketChannel.register(selector, 0);
        //将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法
        selectionKey.attach(this);
        //将SelectionKey标记为可读，以便读取，不可关注可写事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete() {
        return false;
    }

    boolean outputIsComplete() {
        return false;
    }

    //这里可以通过线程池处理数据
    void process() {

    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        socketChannel.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            // Normally also do first write now
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        socketChannel.write(output);
        if (outputIsComplete()) {
            selectionKey.cancel();
        }
    }
    
}
