package com.xuegao.springboot_tool.mapper;

import com.xuegao.springboot_tool.manager.OrderManager;
import com.xuegao.springboot_tool.model.doo.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.mapper
 * <br/> @ClassName：OrderManagerTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/08 17:32
 */
@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Rollback
public class OrderManagerTest {
    @Autowired
    private OrderManager orderManager;

    @Before
    public void before() {
        Order order = new Order();
        order.setNumber(1);
        order.setProductId(1L);
        Long insert = orderManager.insert(order);
        System.out.println(insert);
    }

    @Test
    public void insert() {
        Order order = new Order();
        order.setNumber(1);
        order.setProductId(1L);
        Long insert = orderManager.insert(order);
        System.out.println(insert);
    }
}