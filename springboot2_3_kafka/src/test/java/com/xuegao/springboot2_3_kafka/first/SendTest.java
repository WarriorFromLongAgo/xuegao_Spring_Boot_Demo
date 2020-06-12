package com.xuegao.springboot2_3_kafka.first;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_kafka.first
 * <br/> @ClassName：
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/6/12 10:58
 */
@SpringBootTest
public class SendTest {

    @Autowired
    private Product1 product1;

    @Autowired
    private Product2 product2;

    @Test
    private void SendTest1() {
        for (int i = 0; i < 10; i++) {
            product1.send("afs" + i);
        }
    }

    @Test
    private void SendTest2() {
        for (int i = 0; i < 10; i++) {
            product2.send("afs" + i);
        }
    }

    @Test
    private void SendTest3() {
        for (int i = 0; i < 10; ) {
            product1.send("afs" + i++);
            product2.send("afs" + i++);
        }
    }

}