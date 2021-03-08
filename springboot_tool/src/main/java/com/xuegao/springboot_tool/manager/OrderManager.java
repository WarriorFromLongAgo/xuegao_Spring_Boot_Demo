package com.xuegao.springboot_tool.manager;

import com.xuegao.springboot_tool.dao.IOrderMapper;
import com.xuegao.springboot_tool.model.doo.Order;
import com.xuegao.springboot_tool.mvc.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.manager
 * <br/> @ClassName：OrderManager
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/05 22:55
 */
@Component
public class OrderManager {
    private static final Logger log = LoggerFactory.getLogger(OrderManager.class);

    private final IOrderMapper orderMapper;

    @Autowired
    public OrderManager(IOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public Long insert(Order order) {
        int insert = orderMapper.insert(order);
        if (insert > 0) {
            return order.getId();
        }
        log.error("OrderManager insert 异常 {}", order);
        throw new BusinessException("");
    }

    public Order getById(Long id) {
        return orderMapper.selectById(id);
    }

}