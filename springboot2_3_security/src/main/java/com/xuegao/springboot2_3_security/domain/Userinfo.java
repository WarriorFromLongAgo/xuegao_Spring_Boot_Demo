package com.xuegao.springboot2_3_security.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Auto-generated: 2020-07-27 0:0:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@TableName("userinfo")
public class Userinfo implements UserDetails {

    @TableId("id")
    private String id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("status")
    private String status;
    @TableField("roleArr")
    private String roleArr;

    private List<GrantedAuthority> authoritieList;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authoritieList;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getRoleArr() {
        return roleArr;
    }

    public void setRoleArr(String roleArr) {
        this.roleArr = roleArr;
    }

    public List<GrantedAuthority> getAuthoritieList() {
        return authoritieList;
    }

    public void setAuthoritieList(List<GrantedAuthority> authoritieList) {
        this.authoritieList = authoritieList;
    }
}