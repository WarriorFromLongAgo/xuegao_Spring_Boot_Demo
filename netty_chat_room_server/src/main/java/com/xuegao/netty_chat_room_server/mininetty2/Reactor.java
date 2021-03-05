package com.xuegao.netty_chat_room_server.mininetty2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {

    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);
        serverSocketChannel.socket().bind(address);
        serverSocketChannel.configureBlocking(false);
        //向selector注册该channel
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("-->Start serverSocket.register!");

        //利用sk的attache功能绑定Acceptor 如果有事情，触发Acceptor
        sk.attach(new Acceptor());
        System.out.println("-->attach(new Acceptor()!");
    }

    @Override
    public void run() { // normally in a new Thread
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator it = selectionKeySet.iterator();
                //Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。
                while (it.hasNext()) {
                    //来一个事件 第一次触发一个accepter线程
                    //以后触发SocketReadHandler
                    dispatch((SelectionKey) (it.next()));
                }
                selectionKeySet.clear();
            }
        } catch (IOException ex) {
            System.out.println("reactor stop!" + ex);
        }
    }

    //运行Acceptor或SocketReadHandler
    void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable { // inner
        @Override
        public void run() {
            try {
                System.out.println("-->ready for accept!");
                SocketChannel c = serverSocketChannel.accept();
                if (c != null) {
                    //调用Handler来处理channel
                    new Handler(selector, c);
                }
            } catch (IOException ex) {
            }
        }
    }
}
