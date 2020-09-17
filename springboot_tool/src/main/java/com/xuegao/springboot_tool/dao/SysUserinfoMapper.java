package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <br/> @PackageName：com.xuegao.dao
 * <br/> @ClassName：SysUserinfoMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/28 10:15
 */
@Mapper
public interface SysUserinfoMapper extends BaseMapper<SysUserinfo> {
}