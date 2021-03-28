package com.xuegao.springboot_tool.manager;

import com.xuegao.springboot_tool.dao.ITransactionLogMapper;
import com.xuegao.springboot_tool.model.doo.TransactionLog;
import com.xuegao.springboot_tool.mvc.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.manager
 * <br/> @ClassName：TransactionLogManager
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/29 0:05
 */
@Component
public class TransactionLogManager {

    private final ITransactionLogMapper transactionLogMapper;

    @Autowired
    public TransactionLogManager(ITransactionLogMapper transactionLogMapper) {
        this.transactionLogMapper = transactionLogMapper;
    }

    public Long insert(TransactionLog transactionLog) {
        int insert = transactionLogMapper.insert(transactionLog);
        if (insert < 1) {
            throw new BusinessException("TransactionLog insert 插入失败");
        }
        return transactionLog.getId();
    }
}