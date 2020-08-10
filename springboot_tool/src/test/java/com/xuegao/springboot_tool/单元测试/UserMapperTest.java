package com.xuegao.springboot_tool.单元测试;

import com.xuegao.springboot_tool.dao.UserInfoMapper;
import com.xuegao.springboot_tool.model.po.UserInfo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.单元测试
 * <br/> @ClassName：UserMapperTest
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/10 15:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    @Ignore
    public void testFindAll() {
        UserInfo userInfo = userInfoMapper.selectById(1);
        Assert.assertNotNull(userInfo);
    }

    @Test(expected = RuntimeException.class)
    public void testNullPointerException() {
        throw new RuntimeException();
    }

}