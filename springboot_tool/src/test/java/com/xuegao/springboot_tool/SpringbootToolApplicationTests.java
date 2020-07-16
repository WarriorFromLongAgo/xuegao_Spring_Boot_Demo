package com.xuegao.springboot_tool;

import com.xuegao.springboot_tool.constant.aop.annotation.MyTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootToolApplicationTests {

    @Test
    void contextLoads() {
    }

    @MyTest
    @Test
    public void jdaskl() {
        System.out.println("fjhdsjkfhjsdkhfjksdhfphwruihw ");
    }
}
