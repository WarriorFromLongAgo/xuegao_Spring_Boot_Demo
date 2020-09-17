package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.controller
 * <br/> @ClassName：ThreadController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/11 16:59
 */
@RestController
@RequestMapping(path = "/thread")
public class ThreadController<T> extends BaseController<T> {

    /**
     * <br/> @Title: 对于一个文章点赞 也可以取消点赞
     * <br/> @MethodName:  giveThumbsUpController
     * <br/>
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<T>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/17 10:58
     */
    @RequestMapping("/give_thumbs_up")
    public WrappedResponse<T> giveThumbsUpController() {

        return success();
    }

    /**
     * <br/> @Title: 显示 对一个文章点赞 的所有人信息
     * <br/> @MethodName:  thumbsUpListController
     * <br/>
     * <br/> @Return com.xuegao.springboot_tool.constant.common.WrappedResponse<T>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/17 10:59
     */
    @RequestMapping("/thumbs_up_list")
    public WrappedResponse<T> thumbsUpListController() {

        return success();
    }


}