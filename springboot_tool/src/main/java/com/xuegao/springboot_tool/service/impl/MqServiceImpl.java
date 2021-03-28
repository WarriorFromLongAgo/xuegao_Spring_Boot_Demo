package com.xuegao.springboot_tool.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.xuegao.springboot_tool.manager.TransactionLogManager;
import com.xuegao.springboot_tool.manager.UserMoneyManager;
import com.xuegao.springboot_tool.model.doo.TransactionLog;
import com.xuegao.springboot_tool.service.interfaces.IMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：MqServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/28 23:50
 */
@Service
public class MqServiceImpl implements IMqService {

    private final UserMoneyManager userMoneyManager;

    private final TransactionLogManager transactionLogManager;

    @Autowired
    public MqServiceImpl(UserMoneyManager userMoneyManager,
                         TransactionLogManager transactionLogManager) {
        this.userMoneyManager = userMoneyManager;
        this.transactionLogManager = transactionLogManager;
    }

    @Override
    public void transaction1() {
        TransactionLog transactionLog = new TransactionLog();

        boolean aBoolean = userMoneyManager.decrementMoneyByUserId("1", 100L);
        if (Boolean.FALSE.equals(aBoolean)) {
            return;
        }



    }
}