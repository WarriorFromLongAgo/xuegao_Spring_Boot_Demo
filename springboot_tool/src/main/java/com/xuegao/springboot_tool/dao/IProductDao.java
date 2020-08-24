package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.doo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao
 * <br/> @ClassName：ProductDao
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/8/24 11:21
 */
@Mapper
public interface IProductDao extends BaseMapper<Product> {
}