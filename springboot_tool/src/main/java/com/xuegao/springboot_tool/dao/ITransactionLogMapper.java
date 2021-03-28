package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.doo.TransactionLog;
import org.springframework.stereotype.Repository;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao
 * <br/> @ClassName：ITransactionLogMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/3/29 0:04
 */
@Repository
public interface ITransactionLogMapper extends BaseMapper<TransactionLog> {
}