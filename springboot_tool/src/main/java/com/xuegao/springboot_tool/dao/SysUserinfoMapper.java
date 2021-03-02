package com.xuegao.springboot_tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot_tool.model.PageInfo;
import com.xuegao.springboot_tool.model.PageQuery;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.dao
 * <br/> @ClassName：SysUserinfoMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/6/28 10:15
 */
@Repository
public interface SysUserinfoMapper extends BaseMapper<SysUserinfo> {

    PageInfo<SysUserinfo> page2(@Param("pageQuery") PageQuery<SysUserinfo> pageQuery);

    PageQuery<SysUserinfo> page3(@Param("pageQuery") PageQuery<SysUserinfo> pageQuery);

    Boolean countBoolean(@Param("userCode") Integer userCode);

    List<SysUserinfo> list(@Param("userCode") Integer userCode);

}