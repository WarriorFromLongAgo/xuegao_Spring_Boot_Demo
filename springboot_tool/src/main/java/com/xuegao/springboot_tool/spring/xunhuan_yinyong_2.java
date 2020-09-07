package com.xuegao.springboot_tool.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：xunhuan_yinyong_2
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/9/7 11:28
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class xunhuan_yinyong_2 {

    @Autowired
    private xunhuan_yinyong_1 xunhuan_yinyong_1;

    public void xunhuan_yinyong_2_test() {
        System.out.println(" xunhuan_yinyong_2_test ");
    }
}