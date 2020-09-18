package com.xuegao.springboot_tool.service.impl;

import cn.hutool.system.UserInfo;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import com.xuegao.springboot_tool.model.vo.ThumbsUpArticleVO;
import com.xuegao.springboot_tool.model.vo.ThumbsUpUserinfoVO;
import com.xuegao.springboot_tool.service.interfaces.IThreadService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：ThreadServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/17 11:26
 */
@Service
public class ThreadServiceImpl implements IThreadService {

    private ValueOperations<String, Serializable> valueOperations;
    private ZSetOperations<String, Serializable> zSetOperations;

    @Autowired
    public ThreadServiceImpl(ValueOperations<String, Serializable> valueOperations,
                             ZSetOperations<String, Serializable> zSetOperations) {
        this.valueOperations = valueOperations;
        this.zSetOperations = zSetOperations;
    }

    @Override
    public Boolean giveThumbsUpService(Long giveUserId, Long articleId, Integer thumbsUpFlag) {
        SysUserinfo sysUserinfo1 = new SysUserinfo();
        sysUserinfo1.setId(1L);
        sysUserinfo1.setName("set");
        sysUserinfo1.setUsername("setusername");
        SysUserinfo sysUserinfo2 = new SysUserinfo();
        sysUserinfo2.setId(2L);
        sysUserinfo2.setName("set2");
        sysUserinfo2.setUsername("setusername2");

        valueOperations.set("set", sysUserinfo1);
        SysUserinfo sysUserinfo = (SysUserinfo) valueOperations.get("set");
        System.out.println(sysUserinfo);

        // Set<SysUserinfo> set = new HashSet<>();
        // set.add(sysUserinfo1);
        // set.add(sysUserinfo2);

        zSetOperations.add("zset", sysUserinfo1, 100);
        zSetOperations.add("zset", sysUserinfo2, 101);
        Long zset = zSetOperations.count("zset", 99, 102);
        System.out.println(zset);
        Set<Serializable> zset1 = zSetOperations.rangeByScore("zset", 99, 102);
        if (ObjectUtils.isNotEmpty(zset1)) {
            for (Serializable serializable : zset1) {
                SysUserinfo sysUserinfoTemp = (SysUserinfo) serializable;
                System.out.println(sysUserinfoTemp);
            }
        }
        return true;
    }

    @Override
    public List<ThumbsUpUserinfoVO> thumbsUpListByArticleIdService(Long requestUserId, Long articleId) {
        return null;
    }

    @Override
    public List<ThumbsUpArticleVO> thumbsUpListByUserIdService(Long requestUserId) {
        return null;
    }

    @Override
    public Integer thumbsUpListCountService(Long articleId) {
        return null;
    }
}