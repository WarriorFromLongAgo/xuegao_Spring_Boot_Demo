package com.xuegao.springboot2_3_security.service.impl;

import com.xuegao.springboot2_3_security.domain.SysUserinfo;
import com.xuegao.springboot2_3_security.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <br/> @PackageName：com.xuegao.springboot2_3_security.service.impl
 * <br/> @ClassName：UserDetailServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/27 0:22
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserinfo SysUserinfo = userService.selectUserByUsername(username);
        if (ObjectUtils.isEmpty(SysUserinfo)) {
            throw new UsernameNotFoundException("登录用户" + username + "不存在");
        }
        SysUserinfo.setAuthoritieList(AuthorityUtils.commaSeparatedStringToAuthorityList(SysUserinfo.getRoleArr()));
        return SysUserinfo;
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     // 1，查询用户名是否存在，如果不存在就抛异常
    //     if (!"admin".equals(username)) {
    //         throw new UsernameNotFoundException("adminadminadminadminadmin");
    //     }
    //     // 2，把密码解析一下，（注册时已经加密的）,或者把密码放入构造方法种
    //     String password = passwordEncoder.encode("123456");
    //     return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal"));
    //
    //     // 只有amdin, 123456可以登录
    // }
}