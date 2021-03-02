package com.xuegao.netty_chat_room_server.手写netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.手写netty
 * <br/> @ClassName：SocketMultiplexingSingleThreadv1
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/02/26 10:44
 */
public class SocketMultiplexingSingleThreadv1 {
    private ServerSocketChannel server = null;
    /*
     Selector相当于linux中的多路复用器（select poll    epoll kqueue） nginx  event{}
    * */

    private Selector selector = null;
    int port = 9090;
    /*初始化服务方法*/

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            /*
                如果在epoll模型下，open这一步相当于是执行了epoll_create方法,
                kernel在内存上开辟红黑树空间,产生一个文件描述符fd3,
                另外select  poll  epoll三种多路复用选择器优先选择：epoll,可以用-D修正
             */
            selector = Selector.open();

            /*
            server 约等于 listen状态的 fd4,后面fd4负责accept,要将fd4注册到fd3上,
            若有多个时间,可以将多个fd注册到fd3上,最后将fd3返回。
            server.register(),这一步相当于调用epoll的epoll_ctl(fd3,add,fd4),
            fd3是返回值,fd4是事件,add是操作
            如果：
            select，poll：jvm里开辟一个数组fd4放进去
            epoll：  epoll_ctl(fd3,ADD,fd4,EPOLLIN
             */
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*启动之后调用这个方法*/

    public void start() {
        //初始化服务端,包括绑定端口、设置非阻塞,注册到多路复用器上
        initServer();
        System.out.println("服务器启动了。。。。。");
        //然后开始轮询,一直循环
        try {
            while (true) {
                //返回选择器里面的key.打印着玩玩,没啥用
                Set<SelectionKey> keys = selector.keys();
                System.out.println(keys.size() + "   size");
                /*1,调用多路复用器(select,poll  or  epoll  (epoll_wait))
                下面selector.select()的意思：
                1，如果是select，poll复用器,那么内核执行的是select（fd4）或者poll(fd4)
                2，如果是epoll复用器,执行的是内核的 epoll_wait()方法,会返回一个链表,这儿是返回有效连接个数
                *, 参数可以带时间：没有时间，0  ：  阻塞，有时间设置一个超时
                selector.wakeup()  结果返回0
                懒加载：
                其实在触碰到selector.select()调用的时候触发了epoll_ctl的调用
                 */
                while (selector.select() > 0) {
                    //返回的selectionKeys是有状态的fd集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    //对fd几何进行遍历
                    Iterator<SelectionKey> iter = selectionKeys.iterator();
            /*
                不管使用的是什么多路复用器,都只是给服务器状态集,还是需要服务器去一个一个的处理他们的R/W。同步好辛苦！
                 区别是:
                 NIO自己对着每一个fd调用系统调用，这中间设计状态切换,非常浪费资源，
                 而Epoll只需要一次调用,内核kernel已经将状态统计好,返回给服务端,服务端根据状态精准的读取,避免无效的轮询
             */
                    while (iter.hasNext()) {
                        //拿到返回的状态集合进行遍历,key抓住当前事件,同时将事件从原集合中移除,避免重复处理
                        SelectionKey key = iter.next();
                        iter.remove(); //set  不移除会重复循环处理
                        if (key.isAcceptable()) {
                        /*
                            看代码的时候，这里是重点，如果要去接受一个新的连接
                            语义上，accept接受连接且返回新连接的FD对吧？
                            那新的FD怎么办？
                            select，poll，因为他们内核没有空间，那么在jvm中保存和前边的fd4那个listen的一起
                            epoll： 我们希望通过epoll_ctl把新的客户端fd注册到内核空间
                            */
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                            /*
                            连read 还有 write都处理了
                            在当前线程，这个方法可能会阻塞  ，如果阻塞了十年，其他的IO早就没电了。。。
                            所以，为什么提出了 IO THREADS
                            redis  是不是用了epoll，redis是不是有个io threads的概念 ，redis是不是单线程的
                            tomcat 8,9  异步的处理方式  IO  和   处理上  解耦
                            */
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            //来啦，目的是调用accept接受客户端,连接文件描述符fd7
            SocketChannel client = ssc.accept();
            //设置非阻塞
            client.configureBlocking(false);
            //buffer作用是缓冲,数据就不一个个读,而是一块块读
            ByteBuffer buffer = ByteBuffer.allocate(8192);

            /*
            调用了register,与之前类似,分select(poll)、Epoll两种情况
            select，poll：jvm里开辟一个数组 fd7 放进去
            epoll：  epoll_ctl(fd3,ADD,fd7,EPOLLIN
            fd3是内核开辟的空间哦~,将fd7放进去
             */
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("-------------------------------------------");
            System.out.println("新客户端：" + client.getRemoteAddress());
            System.out.println("-------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*读事件处理*/

    public void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        try {
            while (true) {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SocketMultiplexingSingleThreadv1 service = new SocketMultiplexingSingleThreadv1();
        service.start();
    }
}