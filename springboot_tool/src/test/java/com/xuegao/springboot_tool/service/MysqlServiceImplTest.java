package com.xuegao.springboot_tool.service;

import com.xuegao.springboot_tool.manager.OrderManager;
import com.xuegao.springboot_tool.model.doo.Order;
import com.xuegao.springboot_tool.service.impl.MysqlServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service
 * <br/> @ClassName：MysqlServiceImplTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/08 17:54
 */
@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Rollback
public class MysqlServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(MysqlServiceImplTest.class);

    //mockito模拟一个service需要的对象出来
    @Mock
    private OrderManager orderManager;

    //这是依赖dao的service，injectmocks注解表示你要注入的目标
    @InjectMocks
    private MysqlServiceImpl mysqlService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() {
        Order order = new Order();
        order.setId(4L);
        order.setNumber(4);
        order.setProductId(4L);
        when(orderManager.getById(4L)).thenReturn(order);
        Order result = mysqlService.getById();
        System.out.println(result);
        Assert.assertEquals(order, result);

        when(orderManager.getById(Mockito.anyLong())).thenReturn(order);
        result = mysqlService.getById();
        System.out.println(result);
        Assert.assertEquals(order, result);
    }

}