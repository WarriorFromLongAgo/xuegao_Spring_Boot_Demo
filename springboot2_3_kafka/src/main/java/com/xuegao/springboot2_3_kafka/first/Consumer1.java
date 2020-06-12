package com.xuegao.springboot2_3_kafka.first;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Consumer1 {

    @KafkaListener(topics = "user")
    public void consumer(ConsumerRecord consumerRecord) {
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());

        if (kafkaMassage.isPresent()) {
            Object o = kafkaMassage.get();
            System.out.println("consumer = " + o);
        }
    }
}