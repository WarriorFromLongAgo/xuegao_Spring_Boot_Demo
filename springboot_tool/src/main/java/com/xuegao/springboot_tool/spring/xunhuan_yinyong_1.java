package com.xuegao.springboot_tool.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.spring
 * <br/> @ClassName：xunhuan_yinyong
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/9/7 11:28
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class xunhuan_yinyong_1 {

    @Autowired
    private xunhuan_yinyong_2 xunhuan_yinyong_2;

    public void xunhuan_yinyong_1_test() {
        System.out.println(" xunhuan_yinyong_1_test ");
    }

}