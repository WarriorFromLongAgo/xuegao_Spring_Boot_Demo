package com.xuegao.springboot_tool.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.springboot_tool.dao.SysUserinfoMapper;
import com.xuegao.springboot_tool.model.PageInfo;
import com.xuegao.springboot_tool.model.PageQuery;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import com.xuegao.springboot_tool.service.interfaces.ISysUserinfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：SysUserinfoServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/14 20:05
 */
@Service
public class SysUserinfoServiceImpl extends ServiceImpl<SysUserinfoMapper, SysUserinfo> implements ISysUserinfoService {

    @Override
    public PageInfo<SysUserinfo> page2(PageQuery<SysUserinfo> pageQuery) {
        PageInfo<SysUserinfo> page = baseMapper.page2(pageQuery);
        String s = JSON.toJSONString(page);
        LOG.info(s);
        return page;
    }

    @Override
    public PageInfo<SysUserinfo> page3(PageQuery<SysUserinfo> pageQuery) {
        // PageQuery<SysUserinfo> page = baseMapper.page3(pageQuery);
        // log.info(JSONObject.toJSONString(page));
        // log.info(page.toPageInfo().toString());
        // log.info(page.toPageInfo(SysUserinfo.class).toString());
        PageInfo<SysUserinfo> pageInfo = baseMapper.page3(pageQuery).toPageInfo(SysUserinfo.class);
        return pageInfo;
    }

    @Override
    public void countBoolean(Integer userCode) {
        // List<SysUserinfo> sysUserinfoList = list(new QueryChainWrapper<>(baseMapper).eq("userCode", userCode));
        // System.out.println(sysUserinfoList);
        // int userCode1 = count(new QueryChainWrapper<>(baseMapper).eq("userCode", userCode));
        // System.out.println(userCode1);
        List<SysUserinfo> list = baseMapper.list(userCode);
        System.out.println(list);
        Boolean aBoolean = baseMapper.countBoolean(userCode);
        System.out.println(aBoolean);
    }
}