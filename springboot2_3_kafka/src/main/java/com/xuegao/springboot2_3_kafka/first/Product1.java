package com.xuegao.springboot2_3_kafka.first;

import com.alibaba.fastjson.JSON;
import com.xuegao.springboot2_3_kafka.constant.KafkaConfig;
import com.xuegao.springboot2_3_kafka.domain.vo.SysUserinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class Product1 {

    private Logger log = LoggerFactory.getLogger(Product1.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String name) {
        SysUserinfo SysUserinfo = new SysUserinfo();
        SysUserinfo.setUserName(name);
        SysUserinfo.setAge(11);
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(KafkaConfig.first.TOPIC_1, JSON.toJSONString(SysUserinfo));

        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("onFailure = " + "processMessage, ex = {}, topic = {}, msg = {}", ex, KafkaConfig.first.TOPIC_1, JSON.toJSONString(SysUserinfo));
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                log.error("onSuccess" + "processMessage, topic = {}, msg = {}", KafkaConfig.first.TOPIC_1, JSON.toJSONString(SysUserinfo));
            }
        });
    }
}