package com.xuegao.springboot2_3_security.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.springboot2_3_security.domain.Userinfo;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.service.interfaces
 * <br/> @ClassName：IUserService
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/7/27 0:13
 */
public interface IUserService extends IService<Userinfo> {

    Userinfo selectUserByUsername(String username);
}