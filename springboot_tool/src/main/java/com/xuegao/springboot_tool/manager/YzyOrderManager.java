package com.xuegao.springboot_tool.manager;

import com.xuegao.springboot_tool.dao.IYzyOrderMapper;
import com.xuegao.springboot_tool.model.doo.YzyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.manager
 * <br/> @ClassName：YzyOrderManager
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/29 18:38
 */
@Component
public class YzyOrderManager {

    private final IYzyOrderMapper yzyOrderMapper;

    @Autowired
    public YzyOrderManager(IYzyOrderMapper yzyOrderMapper) {
        this.yzyOrderMapper = yzyOrderMapper;
    }

    public Integer updateById(Integer orderId) {
        YzyOrder yzyOrder = yzyOrderMapper.selectById(orderId);
        yzyOrder.setPayStatus(1);
        return yzyOrderMapper.updateById(yzyOrder);
    }

}