package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.aop.annotation.Limit;
import com.xuegao.springboot_tool.constant.aop.annotation.PrintlnLog;
import com.xuegao.springboot_tool.constant.aop.annotation.RedisLimit;
import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.model.dto.RequestDTO;
import com.xuegao.springboot_tool.service.interfaces.IThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：ThreadController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/11 16:59
 */
// value = 给自己看的， tags = 给前端看的
@Api(value = "用户controller", tags = {"点赞操作接口"})
@RestController
@RequestMapping(path = "/thread")
public class ThreadController<T> extends BaseController<T> {

    private final IThreadService threadService;

    @Autowired
    public ThreadController(IThreadService threadService) {
        this.threadService = threadService;
    }

    /**
     * <br/> @Title: 对于一个文章点赞 也可以取消点赞
     * <br/> @MethodName:  giveThumbsUpController
     * <br/>
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<T>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/17 10:58
     */
    @ApiOperation(value = "对于一个文章点赞、取消点赞", tags = {"对于一个文章点赞、取消点赞"}, notes = "注意问题点")
    @RequestMapping(path = "/give_thumbs_up", method = RequestMethod.POST)
    @PrintlnLog
    // @RedisLimit(description = "点赞", key = "#requestDTO.source", limitCount = 1, expireTime = 500)
    @RedisLimit(description = "点赞", key = "#requestDTO.getSource()", limitCount = 1, expireTime = 50000000)
    public WrappedResponse<Boolean> giveThumbsUpController(@ApiParam(name = "requestDTO", value = "requestDTO", required = true)
                                                           @RequestBody RequestDTO requestDTO) {
        // 点赞的发起人
        Long giveUserId = Long.valueOf(requestDTO.getSource());
        // 点赞的文章id
        Long articleId = Long.valueOf(requestDTO.getTarget());
        // 0 取消点赞 1 点赞
        Integer thumbsUpFlag = Integer.valueOf(requestDTO.getValue().toString());

        boolean aBoolean = threadService.giveThumbsUpService(giveUserId, articleId, thumbsUpFlag);

        return WrappedResponse.success(aBoolean);
    }

    @RequestMapping(path = "/give_thumbs_up2")
    @PrintlnLog
    // @RedisLimit(description = "点赞", key = "#requestDTO.source", limitCount = 1, expireTime = 500)
    @RedisLimit(description = "点赞", key = "#id", limitCount = 1, expireTime = 500)
    public WrappedResponse<Boolean> giveThumbsUpController2(String id) {
        System.out.println(" giveThumbsUpController2 id = " + id);
        return WrappedResponse.success(id);
    }

    /**
     * <br/> @Title: 一个文章id的所有点赞人信息
     * <br/> @MethodName:  thumbsUpListByArticleIdController
     * <br/>
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<T>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/18 14:29
     */
    @RequestMapping(path = "/thumbs_up_list_article_id", method = RequestMethod.POST)
    public WrappedResponse<List<Long>> thumbsUpListByArticleIdController(@RequestBody RequestDTO requestDTO) {
        // 无用
        Long requestUserId = Long.valueOf(requestDTO.getSource());
        // 文章id
        Long articleId = Long.valueOf(requestDTO.getTarget());

        List<Long> longs = threadService.thumbsUpListByArticleIdService(requestUserId, articleId);

        return WrappedResponse.success(longs);
    }

    /**
     * <br/> @Title: 一个人的所有点赞文章
     * <br/> @MethodName:  thumbsUpListByUserIdController
     * <br/>
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<T>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/18 14:29
     */
    @RequestMapping(path = "/thumbs_up_list_user_id", method = RequestMethod.POST)
    public WrappedResponse<List<Long>> thumbsUpListByUserIdController(@RequestBody RequestDTO requestDTO) {
        // 请求人的id
        Long requestUserId = Long.valueOf(requestDTO.getSource());

        List<Long> longs = threadService.thumbsUpListByUserIdService(requestUserId);

        return WrappedResponse.success(longs);
    }

    /**
     * <br/> @Title: 一个文章的点赞数
     * <br/> @MethodName:  thumbsUpListCountController
     * <br/>
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<T>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/18 14:52
     */
    @RequestMapping(path = "/thumbs_up_list_count", method = RequestMethod.POST)
    public WrappedResponse<Long> thumbsUpListCountController(@RequestBody RequestDTO requestDTO) {
        // 请求人id
        Long requestUserId = Long.valueOf(requestDTO.getSource());
        // 文章id
        Long articleId = Long.valueOf(requestDTO.getTarget());

        Long aLong = threadService.thumbsUpListCountService(articleId);

        return WrappedResponse.success(aLong);
    }
}