package com.xuegao.springboot_tool.service.interfaces;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.interfaces
 * <br/> @ClassName：IMqService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/28 23:50
 */
public interface IMqService {

    // ConsumeConcurrentlyStatus transaction1(List<MessageExt> messageExtList,
    //                                        ConsumeConcurrentlyContext context);
}