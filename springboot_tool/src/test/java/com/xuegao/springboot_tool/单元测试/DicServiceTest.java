package com.xuegao.springboot_tool.单元测试;

import com.xuegao.springboot_tool.model.doo.Dic;
import com.xuegao.springboot_tool.service.interfaces.IDicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.单元测试
 * <br/> @ClassName：DicServiceTest
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/28 16:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DicServiceTest {

    @Autowired
    private IDicService dicService;

    @Test
    public void getDicById() {
        Dic dicById = dicService.getDicById(1L);
        System.out.println(dicById);
    }

    @Test
    public void dicList() {
        List<Dic> dics = dicService.dicList(Arrays.asList(1L, 2L, 3L));
        System.out.println(dics);
    }

}