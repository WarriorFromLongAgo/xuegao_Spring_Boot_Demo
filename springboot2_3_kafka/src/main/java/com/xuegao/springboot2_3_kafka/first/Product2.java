package com.xuegao.springboot2_3_kafka.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Product2 {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String name) {
        // UserInfo userInfo = new UserInfo();
        // userInfo.setUserName(name);
        // userInfo.setAge(11);
        // kafkaTemplate.send("user", JSON.toJSONString(userInfo));
        System.out.println("----------Product2--------------- + name" + name);
    }
}