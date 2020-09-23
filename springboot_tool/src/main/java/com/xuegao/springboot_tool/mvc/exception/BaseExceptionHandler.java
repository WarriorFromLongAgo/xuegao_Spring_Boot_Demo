package com.xuegao.springboot_tool.mvc.exception;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.constant.enums.HttpCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@ControllerAdvice
public class BaseExceptionHandler {
    private Logger log = LoggerFactory.getLogger(BaseExceptionHandler.class);

    /**
     * @Title: 异常处理
     * @MethodName: error
     * @Return java.lang.String
     * @author: xuegao
     * @date: 2020/2/21 16:26
     */
    /**
     * -------- 通用异常处理方法 --------
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WrappedResponse<String> error(Exception e) {
        log.error("Exception = ", e);
        return WrappedResponse.fail(HttpCode.SERVER_ERROR.getMessage(), e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public WrappedResponse<String> error(NullPointerException e) {
        log.error("NullPointerException = ", e);
        return WrappedResponse.fail(HttpCode.SERVER_ERROR.getHttpCode(), HttpCode.SERVER_ERROR.getMessage(), "NullPointerException");
    }

    @ExceptionHandler(RedisLimitException.class)
    @ResponseBody
    public WrappedResponse<String> error(RedisLimitException e) {
        log.error("RedisLimitException = ", e);
        return WrappedResponse.fail(HttpCode.SERVER_ERROR.getHttpCode(), HttpCode.SERVER_ERROR.getMessage(), "RedisLimitException");
    }

    /**-------- 指定异常处理方法 --------**/
    // @ExceptionHandler(HttpClientErrorException.class)
    // @ResponseBody
    // public R error(IndexOutOfBoundsException e) {
    //     e.printStackTrace();
    //     return R.setResult(ResultCodeEnum.HTTP_CLIENT_ERROR);
    // }
    //
    // /**-------- 自定义定异常处理方法 --------**/
    // @ExceptionHandler(CMSException.class)
    // @ResponseBody
    // public R error(CMSException e) {
    //     e.printStackTrace();
    //     return R.error().message(e.getMessage()).code(e.getCode());
    // }
}
