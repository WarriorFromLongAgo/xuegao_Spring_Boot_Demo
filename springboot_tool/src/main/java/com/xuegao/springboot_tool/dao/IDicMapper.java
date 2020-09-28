package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.doo.Dic;
import org.apache.ibatis.annotations.Mapper;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao
 * <br/> @ClassName：DicMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/28 16:24
 */
@Mapper
public interface IDicMapper extends BaseMapper<Dic> {
}