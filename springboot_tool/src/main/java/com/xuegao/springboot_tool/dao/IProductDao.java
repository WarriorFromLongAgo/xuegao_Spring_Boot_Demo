package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.doo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao
 * <br/> @ClassName：ProductDao
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/24 11:21
 */
@Repository
public interface IProductDao extends BaseMapper<Product> {

    @Delete(" truncate t_order ")
    Integer deleteAllOrder();

    @Update(" update t_product set stocks = stocks - 1 where id = #{productId} ")
    Integer decr(Long productId);
}