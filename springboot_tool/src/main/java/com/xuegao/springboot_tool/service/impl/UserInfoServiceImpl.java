package com.xuegao.springboot_tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.springboot_tool.dao.UserInfoMapper;
import com.xuegao.springboot_tool.model.po.UserInfo;
import com.xuegao.springboot_tool.service.interfaces.IUserInfoService;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：UserInfoServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/14 20:05
 */
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}