package com.xuegao.netty_chat_room_server.Netty实战.第5章;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_server.Netty实战.第5章
 * <br/> @ClassName：ByteBufTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/03 20:11
 */
public class ByteBufTest {
    // slice = Netty in Action
    // slice = Jetty in Action
    // buffer = Jetty in Action rocks!
    // true
    // ==================================
    // ==================================
    // ==================================
    // copy = Netty in Action
    // slice = Netty in Action
    // buffer = Jetty in Action rocks!
    // false
    // ==================================
    // ==================================
    // ==================================
    // buffer = N
    // setByte = Jetty in Action rocks!
    // true
    // true
    // ==================================
    // ==================================
    // ==================================
    // buffer readByte = N
    // writeByte = etty in Action rocks!J
    // true
    // false
    public static void main(String[] args) {
        sliceTest();
        ge();
        copyTest();
        ge();
        getTest();
        ge();
        readTest();
    }

    public static void ge() {
        System.out.println("==================================");
        System.out.println("==================================");
        System.out.println("==================================");
    }

    public static void sliceTest() {
        ByteBuf buffer = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        // ByteBuf 的 索引从0开始，到索引15结束的一个新的切片
        ByteBuf slice = buffer.slice(0, 15);
        System.out.println("slice = " + slice.toString(StandardCharsets.UTF_8));
        // 更新索引0处的字节
        buffer.setByte(0, 'J');
        System.out.println("slice = " + slice.toString(StandardCharsets.UTF_8));
        System.out.println("buffer = " + buffer.toString(StandardCharsets.UTF_8));
        // 切割后的，和原来的是同一个内存空间，true
        System.out.println(buffer.getByte(0) == slice.getByte(0));
    }

    public static void copyTest() {
        ByteBuf buffer = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        // ByteBuf 的 索引从0开始，到索引15结束的一个新的切片
        ByteBuf copy = buffer.copy(0, 15);
        System.out.println("copy = " + copy.toString(StandardCharsets.UTF_8));
        buffer.setByte(0, 'J');
        System.out.println("slice = " + copy.toString(StandardCharsets.UTF_8));
        System.out.println("buffer = " + buffer.toString(StandardCharsets.UTF_8));
        // 复制后的，和原来的不是同一个内存空间，false
        System.out.println(buffer.getByte(0) == copy.getByte(0));
    }

    public static void getTest() {
        ByteBuf buffer = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        // 打印第一个字符
        System.out.println("buffer = " + ((char) buffer.getByte(0)));
        // 存储当前的读写索引
        int readerIndex = buffer.readerIndex();
        int writerIndex = buffer.writerIndex();
        // 修改索引 0 处的字节，并更新
        buffer.setByte(0, 'J');
        System.out.println("setByte = " + buffer.toString(StandardCharsets.UTF_8));
        // get set 操作不会更新读写索引，true true
        System.out.println(readerIndex == buffer.readerIndex());
        System.out.println(writerIndex == buffer.writerIndex());
    }

    public static void readTest() {
        ByteBuf buffer = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        // 打印第一个字符
        System.out.println("buffer readByte = " + ((char) buffer.readByte()));
        // 存储当前的读写索引
        int readerIndex = buffer.readerIndex();
        int writerIndex = buffer.writerIndex();
        // 将 字符 J 追加到缓存区
        buffer.writeByte('J');
        System.out.println("writeByte = " + buffer.toString(StandardCharsets.UTF_8));
        // read不会移动读索引，write操作会移动写索引，true，false
        System.out.println(readerIndex == buffer.readerIndex());
        System.out.println(writerIndex == buffer.writerIndex());
    }
}