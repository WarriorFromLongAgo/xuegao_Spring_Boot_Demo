package com.xuegao.springboot_tool.service.impl;

import com.xuegao.springboot_tool.service.interfaces.IThreadService;
import com.xuegao.springboot_tool.utils.RedisConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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

    private static final Integer THUMBS_UP = 1;

    private ValueOperations<String, Serializable> valueOperations;
    private ZSetOperations<String, Serializable> zSetOperations;

    @Autowired
    public ThreadServiceImpl(ValueOperations<String, Serializable> valueOperations,
                             ZSetOperations<String, Serializable> zSetOperations) {
        this.valueOperations = valueOperations;
        this.zSetOperations = zSetOperations;
    }

    /**
     * <br/> @Title: 给一个微博点赞，或者取消点赞
     * <br/> @MethodName:  giveThumbsUpService
     * <br/> @param giveUserId:
     * <br/> @param articleId:
     * <br/> @param thumbsUpFlag:
     * <br/> @return java.lang.Boolean
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:30
     */
    @Override
    public Boolean giveThumbsUpService(Long giveUserId, Long articleId, Integer thumbsUpFlag) {
        // SysUserinfo sysUserinfo1 = new SysUserinfo();
        // sysUserinfo1.setId(1L);
        // sysUserinfo1.setName("set");
        // sysUserinfo1.setUsername("setusername");
        // valueOperations.set("set", sysUserinfo1);
        // SysUserinfo sysUserinfo = (SysUserinfo) valueOperations.get("set");
        // System.out.println(sysUserinfo);

        // Long zset = zSetOperations.count("zset", 99, 102);
        // System.out.println(zset);
        // Set<Serializable> zset1 = zSetOperations.rangeByScore("zset", 99, 102);
        // if (ObjectUtils.isNotEmpty(zset1)) {
        //     for (Serializable serializable : zset1) {
        //         SysUserinfo sysUserinfoTemp = (SysUserinfo) serializable;
        //         System.out.println(sysUserinfoTemp);
        //     }
        // }

        // thumbsUpFlag 等于 点赞
        if (THUMBS_UP.equals(thumbsUpFlag)) {
            // 用户的点赞列表
            zSetOperations.add(Long.toString(giveUserId), articleId, System.currentTimeMillis());
            // 文章的点赞列表
            zSetOperations.add(Long.toString(articleId), giveUserId, System.currentTimeMillis());
        } else {
            // 用户的点赞列表 删除
            zSetOperations.remove(Long.toString(giveUserId), articleId);
            // 文章的点赞列表删除
            zSetOperations.remove(Long.toString(articleId), giveUserId);
        }

        // 异步入库的操作
        // 添加到数据库
        // 重试三次，入库失败，打日志，发信息，发邮件
        return true;
    }

    /**
     * <br/> @Title: 一个微博的点赞人的列表，暂时不做分页了
     * <br/> @MethodName:  thumbsUpListByArticleIdService
     * <br/> @param requestUserId:
     * <br/> @param articleId:
     * <br/> @return java.util.List<java.lang.Long>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:29
     */
    @Override
    public List<Long> thumbsUpListByArticleIdService(Long requestUserId, Long articleId) {
        Set<Serializable> range = zSetOperations.reverseRange(Long.toString(articleId), 0, 1000);
        List<Long> longList = RedisConvertUtils.fastJsonDeserializeToSet(range, Long.class);
        // 里面存的是 文章的点赞人的id列表，去其他缓存里面获取这个人的信息就可以了。
        return longList;
    }

    /**
     * <br/> @Title: 一个人的点赞微博列表，暂时不做分页了。
     * <br/> @MethodName:  thumbsUpListByUserIdService
     * <br/> @param requestUserId:
     * <br/> @return java.util.List<java.lang.Long>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:28
     */
    @Override
    public List<Long> thumbsUpListByUserIdService(Long requestUserId) {
        Set<Serializable> range = zSetOperations.reverseRange(Long.toString(requestUserId), 0, 1000);
        // Set<Serializable> range = zSetOperations.rangeByLex(Long.toString(requestUserId), RedisZSetCommands.Range.range().)
        List<Long> longList = RedisConvertUtils.fastJsonDeserializeToSet(range, Long.class);
        // 里面存的是 人的点赞文章的id列表，去其他缓存里面获取这个人的信息就可以了。
        return longList;
    }

    /**
     * <br/> @Title: 一个文章有多少人点赞，返回的是 userid，按照时间顺序
     * <br/> @MethodName:  thumbsUpListCountService
     * <br/> @param articleId:
     * <br/> @return java.lang.Long
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/22 17:28
     */
    @Override
    public Long thumbsUpListCountService(Long articleId) {
        Long aLong = zSetOperations.zCard(Long.toString(articleId));
        return aLong;
    }
}