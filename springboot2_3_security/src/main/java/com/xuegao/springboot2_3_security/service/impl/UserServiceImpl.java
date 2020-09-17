package com.xuegao.springboot2_3_security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.springboot2_3_security.dao.SysUserinfoMapper;
import com.xuegao.springboot2_3_security.domain.SysUserinfo;
import com.xuegao.springboot2_3_security.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.service.impl
 * <br/> @ClassName：IUserServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/25 16:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserinfoMapper, SysUserinfo> implements IUserService {

    @Override
    public SysUserinfo selectUserByUsername(String username) {
        return getOne(new QueryWrapper<SysUserinfo>().eq("username", username));
    }

}