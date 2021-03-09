package com.xuegao.springboot_tool.service.impl;

import com.xuegao.springboot_tool.manager.OrderManager;
import com.xuegao.springboot_tool.model.doo.Order;
import com.xuegao.springboot_tool.service.interfaces.IMysqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：MysqlServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/05 22:17
 */
@Service
public class MysqlServiceImpl implements IMysqlService {
    private static final Logger log = LoggerFactory.getLogger(MysqlServiceImpl.class);

    private final OrderManager orderManager;

    @Autowired
    public MysqlServiceImpl(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    @Override
    public void transactional() {
        Order order = new Order();
        order.setNumber(0);
        order.setProductId(System.currentTimeMillis());
        orderManager.insert(order);

        insert1();
        insert2();
        insert3();

        Order order4 = new Order();
        order4.setNumber(4);
        order4.setProductId(System.currentTimeMillis());
        orderManager.insert(order4);
    }


    @Override
    public Long insert1() {
        Order order = new Order();
        order.setNumber(1);
        order.setProductId(System.currentTimeMillis());
        return orderManager.insert(order);
    }

    @Override
    public Long insert2() {
        Order order = new Order();
        order.setNumber(2);
        order.setProductId(System.currentTimeMillis());
        orderManager.insert(order);

        Order order2 = new Order();
        order2.setNumber(22);
        int a = 8 / 0;
        order2.setProductId(System.currentTimeMillis());
        return orderManager.insert(order2);
    }

    @Override
    public Long insert3() {
        Order order = new Order();
        order.setNumber(3);
        order.setProductId(System.currentTimeMillis());
        return orderManager.insert(order);
    }

    @Override
    public Order getById() {
        Order order = orderManager.getById(4L);
        System.out.println(order);
        return order;
    }

}