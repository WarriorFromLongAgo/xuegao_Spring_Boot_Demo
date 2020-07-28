package com.xuegao.springboot_tool.config.serializer;

import com.xuegao.springboot_tool.model.bo.Book;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class TestSerizlizer {
    public static final int SUM = 10000;
    private static RedisSerializer<Object> jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
    private static RedisSerializer<Book> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Book.class);
    private static RedisSerializer<Book> fstSerializer = new FstSerializer<>(Book.class);
    private static RedisSerializer<Book> kryoSerializer = new KryoSerializer<>(Book.class);

    public static void main(String[] args) {
        Book book = new Book();
        book.setName("这本书呀真的好呀好呀");
        book.setNumber(9999);
        book.setVersion(19999919991991L);
        book.setCreateTime(new Date());
        book.setUpdateTime(LocalDateTime.now());
        ArrayList<String> list = new ArrayList<String>();
        list.add("一部分文字描述什么的");
        book.setList(list);
        System.out.println("JDK,FST,Kryo对比测试：");
        testJdk(book);
        testJdk(book);
        testFastJson(book);
        testFastJson(book);
        testFst(book);
        testFst(book);
        testKryo(book);
        testKryo(book);
    }

    private static void testJdk(Book book) {
        long size = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < SUM; i++) {
            byte[] jdkserialize = jdkSerializationRedisSerializer.serialize(book);
            size += jdkserialize.length;
            jdkSerializationRedisSerializer.deserialize(jdkserialize);
        }
        stopWatch.stop();
        System.out.println(String.format("原生JDK序列化方案[序列化%s次]耗时：%s ms, 大小 %s",
                SUM, stopWatch.getTotalTimeMillis(), size));
    }

    private static void testFastJson(Book book) {
        long size = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < SUM; i++) {
            byte[] jdkserialize = fastJsonRedisSerializer.serialize(book);
            size += jdkserialize.length;
            fastJsonRedisSerializer.deserialize(jdkserialize);
        }
        stopWatch.stop();
        System.out.println(String.format("FastJson序列化方案[序列化%s次]耗时：%s ms, 大小 %s",
                SUM, stopWatch.getTotalTimeMillis(), size));
    }

    private static void testFst(Book book) {
        long size = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < SUM; i++) {
            byte[] jdkserialize = fstSerializer.serialize(book);
            size += jdkserialize.length;
            fstSerializer.deserialize(jdkserialize);
        }
        stopWatch.stop();
        System.out.println(String.format("FST序列化方案[序列化%s次]耗时：%s ms, 大小 %s",
                SUM, stopWatch.getTotalTimeMillis(), size));
    }

    private static void testKryo(Book book) {
        long size = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < SUM; i++) {
            byte[] jdkserialize = kryoSerializer.serialize(book);
            size += jdkserialize.length;
            kryoSerializer.deserialize(jdkserialize);
        }
        stopWatch.stop();
        System.out.println(String.format("Kryo序列化方案[序列化%s次]耗时：%s ms, 大小 %s",
                SUM, stopWatch.getTotalTimeMillis(), size));
    }
}
