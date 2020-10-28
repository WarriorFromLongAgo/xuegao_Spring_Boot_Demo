package com.xuegao.springboot_tool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.springboot_tool.dao.SysUserinfoMapper;
import com.xuegao.springboot_tool.model.PageInfo;
import com.xuegao.springboot_tool.model.PageQuery;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import com.xuegao.springboot_tool.service.interfaces.ISysUserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：SysUserinfoServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/14 20:05
 */
@Service
public class SysUserinfoServiceImpl extends ServiceImpl<SysUserinfoMapper, SysUserinfo> implements ISysUserinfoService {
    private Logger log = LoggerFactory.getLogger(SysUserinfoServiceImpl.class);

    @Override
    public PageInfo<SysUserinfo> page2(PageQuery<SysUserinfo> pageQuery) {
        PageInfo<SysUserinfo> page = baseMapper.page2(pageQuery);
        log.info(JSONObject.toJSONString(page));
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
}