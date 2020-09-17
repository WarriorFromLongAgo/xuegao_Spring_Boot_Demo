// package com.xuegao.springboot_tool.单元测试;
//
// import com.xuegao.springboot_tool.dao.SysUserinfoMapper;
// import com.xuegao.springboot_tool.model.doo.SysUserinfo;
// import org.junit.Assert;
// import org.junit.Ignore;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot_tool.单元测试
//  * <br/> @ClassName：UserMapperTest
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2020/8/10 15:25
//  */
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class UserMapperTest {
//
//     @Autowired
//     private SysUserinfoMapper SysUserinfoMapper;
//
//     @Test
//     @Ignore
//     public void testFindAll() {
//         SysUserinfo SysUserinfo = SysUserinfoMapper.selectById(1);
//         Assert.assertNotNull(SysUserinfo);
//     }
//
//     @Test(expected = RuntimeException.class)
//     public void testNullPointerException() {
//         throw new RuntimeException();
//     }
//
// }