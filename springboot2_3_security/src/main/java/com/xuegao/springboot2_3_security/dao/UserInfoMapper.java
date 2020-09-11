package com.xuegao.springboot2_3_security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.springboot2_3_security.domain.Userinfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.dao
 * <br/> @ClassName：UserInfoMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/27 0:11
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<Userinfo> {
}