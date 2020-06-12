package com.xuegao.springboot2_3_kafka;

import com.xuegao.springboot2_3_kafka.first.Product1;
import com.xuegao.springboot2_3_kafka.first.Product2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Springboot23KafkaApplication implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(Springboot23KafkaApplication.class);

    public static void main(String[] args) {
        log.error("111111111111111111111111111111111111111111111111111111111111111111111");
        SpringApplication.run(Springboot23KafkaApplication.class, args).close();
    }

    @Autowired
    private Product1 product1;

    @Autowired
    private Product2 product2;

    // @Override
    // public void run(String... args) throws Exception {
    //     System.out.println("1322222222222222222222");
    //     for (int i = 100; i < 110; i++) {
    //         product1.send("afs" + i);
    //         product2.send("afs" + i);
    //     }
    // }

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
        this.template.send("myTopic", "foo1");
        this.template.send("myTopic", "foo2");
        this.template.send("myTopic", "foo3");
        latch.await(60, TimeUnit.SECONDS);
        log.error("All received");
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> consumerRecord) {
        log.error(consumerRecord.toString());
        latch.countDown();
    }
}
