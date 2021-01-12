package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.aop.annotation.PrintlnLog;
import com.xuegao.springboot_tool.constant.aop.annotation.RedisLimit;
import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.model.dto.RequestDTO;
import com.xuegao.springboot_tool.service.interfaces.IThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

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

    /**
     * <br/> @Title: redisson 延迟队列
     * <br/> @MethodName:  delayedQueueByRedissonClientOffer
     * <br/>
     * <br/> @return com.xuegao.springboot_tool.constant.common.WrappedResponse<java.lang.Long>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/10/09 19:57
     */
    @RequestMapping(path = "/delayed_queue_redis_offer", method = RequestMethod.POST)
    public WrappedResponse<Long> delayedQueueByRedissonClientOffer() {
        threadService.delayedQueueByRedissonClientOffer();
        return WrappedResponse.success();
    }

    /**
     * <br/> @Title: redisson 延迟队列
     * <br/> @MethodName:  delayedQueueByRedissonClientTake
     * <br/>
     * <br/> @return com.xuegao.springboot_tool.constant.common.WrappedResponse<java.lang.Long>
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/10/09 19:58
     */
    @RequestMapping(path = "/delayed_queue_redis_take", method = RequestMethod.POST)
    public WrappedResponse<Long> delayedQueueByRedissonClientTake() {
        threadService.delayedQueueByRedissonClientTake();
        return WrappedResponse.success();
    }

    @PrintlnLog
    @GetMapping("async2")
    public WrappedResponse<String> async2() {
        System.out.println("外面 = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("TimeUnit.SECONDS.sleep(10);");
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            }
        });
        System.out.println("外面 = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        return WrappedResponse.success("dadasdsa");
    }

    @PrintlnLog
    @GetMapping("async3")
    public WrappedResponse<String> async3() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        // 创建CompletableFuture对象，他会包含计算结果
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("TimeUnit.SECONDS.sleep(10);");
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                return "1243124242422323";
            }
        });

        // System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        // TimeUnit.SECONDS.sleep(10);
        // System.out.println("TimeUnit.SECONDS.sleep(10);");
        // System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        // 无需等待还没结束的计算，直接返回结果
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        return WrappedResponse.success("successsuccesssuccesssuccesssuccess");
        // return WrappedResponse.success("successsuccesssuccesssuccesssuccess");
    }


    @PrintlnLog
    @GetMapping("async4")
    public WrappedResponse<String> async4() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("11111111111111111111111111111");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("11111111111111111111111111111");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).thenRunAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("22222222222222");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("22222222222222222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        return WrappedResponse.success("successsuccesssuccesssuccesssuccess");
    }
}