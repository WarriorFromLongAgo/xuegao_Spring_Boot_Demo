// package com.xuegao.springboot_tool.service.impl;
//
// import com.alibaba.fastjson.JSONObject;
// import com.xuegao.springboot_tool.manager.TransactionLogManager;
// import com.xuegao.springboot_tool.manager.UserMoneyManager;
// import com.xuegao.springboot_tool.manager.YzyOrderManager;
// import com.xuegao.springboot_tool.manager.YzyRepoManager;
// import com.xuegao.springboot_tool.model.doo.TransactionLog;
// import com.xuegao.springboot_tool.service.interfaces.IMqService;
// import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
// import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
// import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
// import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
// import org.apache.rocketmq.common.message.MessageExt;
// import org.springframework.beans.factory.InitializingBean;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
//
// import java.util.List;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
//  * <br/> @ClassName：MqServiceImpl
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2021/3/28 23:50
//  */
// @Service
// public class MqServiceImpl implements IMqService, InitializingBean {
//
//     private final YzyOrderManager yzyOrderManager;
//
//     private final YzyRepoManager yzyRepoManager;
//
//     @Value("${rocketmq.name-server}")
//     private String consumerNameSrv;
//
//     @Value("${rocketmq.consumer.group}")
//     private String consumerGroupName;
//
//
//     @Autowired
//     public MqServiceImpl(YzyOrderManager yzyOrderManager,
//                          YzyRepoManager yzyRepoManager) {
//         this.yzyOrderManager = yzyOrderManager;
//         this.yzyRepoManager = yzyRepoManager;
//     }
//
//     @Override
//     public void afterPropertiesSet() throws Exception {
//         DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroupName);
//         consumer.setNamesrvAddr(consumerNameSrv);
//         consumer.subscribe("pay", "*");
//         consumer.registerMessageListener(new MessageListenerConcurrently() {
//             @Override
//             public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext context) {
//                 MessageExt messageExt = messageExtList.get(0);
//                 JSONObject jsonObject = JSONObject.parseObject(new String(messageExt.getBody()));
//                 // jsonObject.put("payFlag", "2");
//                 // jsonObject.put("userId", userId);
//                 // jsonObject.put("goodId", goodId);
//                 String payFlag = jsonObject.get("payFlag").toString();
//                 // String userId = jsonObject.get("userId").toString();
//                 // String goodId = jsonObject.get("goodId").toString();
//                 String orderId = jsonObject.get("orderId").toString();
//
//                 if ("1".equals(payFlag)) {
//                     yzyOrderManager.updateById(Integer.valueOf(orderId));
//                     System.out.println("ConsumeConcurrentlyStatus.CONSUME_SUCCESS payFlag = " + payFlag);
//                     return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                 } else {
//                     System.out.println("ConsumeConcurrentlyStatus.RECONSUME_LATER payFlag = " + payFlag);
//                     return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                 }
//             }
//         });
//         consumer.start();
//         System.out.println("consumer 启动");
//     }
//
// }