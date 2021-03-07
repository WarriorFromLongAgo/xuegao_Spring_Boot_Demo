package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.doo.Order;
import org.springframework.stereotype.Repository;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao
 * <br/> @ClassName：IOrderMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/30 14:24
 */
@Repository
public interface IOrderMapper extends BaseMapper<Order> {
}