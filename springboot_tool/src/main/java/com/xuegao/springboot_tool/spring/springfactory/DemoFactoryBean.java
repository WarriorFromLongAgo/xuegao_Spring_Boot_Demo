package com.xuegao.springboot_tool.spring.springfactory;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by @author yihui in 17:19 18/9/30.
 */
public class DemoFactoryBean implements FactoryBean<FacDemoBean> {
    @Override
    public FacDemoBean getObject() throws Exception {
        return new FacDemoBean();
    }

    @Override
    public Class<?> getObjectType() {
        return FacDemoBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
