package com.xuegao.springboot2_3_kafka.domain.bo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KafkaTopic.class)
public class KafkaTopicConfiguration {

    private final KafkaTopic kafkaTopic;

    public KafkaTopicConfiguration(KafkaTopic kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    @Bean
    public String[] kafkaTopicName() {
        return kafkaTopic.getTopicName();
    }

    @Bean
    public String topicGroupId() {
        return kafkaTopic.getGroupId();
    }

}