package com.xuegao.springboot2_3_kafka.domain.bo;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_kafka.domain.bo
 * <br/> @ClassName：
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/6/12 11:35
 */
@Configuration
@ConfigurationProperties(prefix = "kafka.topic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KafkaTopic implements Serializable {
    private String groupId;
    private String[] topicName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String[] getTopicName() {
        return topicName;
    }

    public void setTopicName(String[] topicName) {
        this.topicName = topicName;
    }
}