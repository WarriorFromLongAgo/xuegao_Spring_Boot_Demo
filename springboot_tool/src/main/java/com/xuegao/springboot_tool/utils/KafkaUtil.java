package com.xuegao.springboot_tool.utils;

import com.xuegao.springboot_tool.dao.IOrderMapper;
import com.xuegao.springboot_tool.model.doo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.utils
 * <br/> @ClassName：KafkaUtil
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/30 14:22
 */
@Component
public class KafkaUtil {
    @Autowired
    private IOrderMapper orderMapper;

    @Async
    public void sendMessage(Long productId, Integer number) {
        // 生成订单 和 数量
        Order order = new Order();
        order.setProductId(productId);
        order.setNumber(number);
        int insert = orderMapper.insert(order);
        // Order{id=1299959163994378242, productId=1, number=1}
        System.out.println(order);
        System.out.println(insert);
    }
}