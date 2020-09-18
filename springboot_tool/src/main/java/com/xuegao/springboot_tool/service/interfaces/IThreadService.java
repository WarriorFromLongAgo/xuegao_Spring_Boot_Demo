package com.xuegao.springboot_tool.service.interfaces;

import com.xuegao.springboot_tool.model.vo.ThumbsUpArticleVO;
import com.xuegao.springboot_tool.model.vo.ThumbsUpUserinfoVO;

import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.interfaces
 * <br/> @ClassName：IThreadService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/17 11:26
 */
public interface IThreadService {

    /**
     * <br/> @Title: 点赞 一个人对一篇文章点赞 或者取消点赞
     * <br/> @MethodName:  giveThumbsUpService
     * <br/> @param giveUserId:
     * <br/> @param articleId:
     * <br/> @param thumbsUpFlag:
     * <br/> @return java.lang.Boolean
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/18 14:58
     */
    Boolean giveThumbsUpService(Long giveUserId, Long articleId, Integer thumbsUpFlag);

    /**
     * <br/> @Title: 一个文章的点赞列表 人员的列表
     * <br/> @MethodName:  thumbsUpListByArticleIdService
     * <br/> @param requestUserId:
     * <br/> @param articleId:
     * <br/> @return java.util.List<com.xuegao.springboot_tool.model.vo.ThumbsUpUserinfoVO>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/18 15:50
     */
    List<ThumbsUpUserinfoVO> thumbsUpListByArticleIdService(Long requestUserId, Long articleId);

    /**
     * <br/> @Title: 一个人的 点赞列表 文章的信息
     * <br/> @MethodName:  thumbsUpListByUserIdService
     * <br/> @param requestUserId:
     * <br/> @return java.util.List<com.xuegao.springboot_tool.model.vo.ThumbsUpArticleVO>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/18 16:04
     */
    List<ThumbsUpArticleVO> thumbsUpListByUserIdService(Long requestUserId);

    /**
     * <br/> @Title: 一个文章 的点赞数量
     * <br/> @MethodName:  thumbsUpListCountService
     * <br/> @param articleId:
     * <br/> @return java.lang.Integer
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/18 16:06
     */
    Integer thumbsUpListCountService(Long articleId);
}