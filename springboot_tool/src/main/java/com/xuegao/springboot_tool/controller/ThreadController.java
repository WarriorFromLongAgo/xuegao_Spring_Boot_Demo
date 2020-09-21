package com.xuegao.springboot_tool.controller;

import com.google.common.collect.Lists;
import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.model.dto.RequestDTO;
import com.xuegao.springboot_tool.model.vo.ThumbsUpArticleVO;
import com.xuegao.springboot_tool.model.vo.ThumbsUpUserinfoVO;
import com.xuegao.springboot_tool.service.interfaces.IThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(value = "用户controller", tags = {"用户操作接口"})
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
    @ApiOperation(value = "获取用户信息", tags = {"获取用户信息copy"}, notes = "注意问题点")
    @RequestMapping("/give_thumbs_up")
    public WrappedResponse<Boolean> giveThumbsUpController(@ApiParam(name = "requestDTO", value = "requestDTO", required = true)
                                                           @RequestBody RequestDTO requestDTO) {
        Long giveUserId = Long.valueOf(requestDTO.getSource());
        Long articleId = Long.valueOf(requestDTO.getTarget());
        // 0 取消点赞 1 点赞
        Integer thumbsUpFlag = Integer.valueOf(requestDTO.getValue().toString());

        boolean aBoolean = threadService.giveThumbsUpService(giveUserId, articleId, thumbsUpFlag);

        return WrappedResponse.success(aBoolean);
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
    @RequestMapping("/thumbs_up_list_article_id")
    public WrappedResponse<List<ThumbsUpUserinfoVO>> thumbsUpListByArticleIdController(@RequestBody RequestDTO requestDTO) {
        Long requestUserId = Long.valueOf(requestDTO.getSource());
        Long articleId = Long.valueOf(requestDTO.getTarget());

        threadService.thumbsUpListByArticleIdService(requestUserId, articleId);

        return WrappedResponse.success(Lists.newArrayList());
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
    @RequestMapping("/thumbs_up_list_user_id")
    public WrappedResponse<List<ThumbsUpArticleVO>> thumbsUpListByUserIdController(@RequestBody RequestDTO requestDTO) {
        Long requestUserId = Long.valueOf(requestDTO.getSource());

        threadService.thumbsUpListByUserIdService(requestUserId);

        return WrappedResponse.success(Lists.newArrayList());
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
    @RequestMapping("/thumbs_up_list_count")
    public WrappedResponse<Integer> thumbsUpListCountController(@RequestBody RequestDTO requestDTO) {
        Long requestUserId = Long.valueOf(requestDTO.getSource());
        Long articleId = Long.valueOf(requestDTO.getTarget());

        threadService.thumbsUpListCountService(articleId);

        return WrappedResponse.success(1);
    }
}