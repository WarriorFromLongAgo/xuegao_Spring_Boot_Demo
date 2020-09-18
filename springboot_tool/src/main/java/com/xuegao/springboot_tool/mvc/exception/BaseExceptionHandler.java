package com.xuegao.springboot_tool.mvc.exception;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PackageName：com.fff.springbootweb1.handler
 * @ClassName：
 * @Description：
 * @author：
 * @date：2020/2/21 16:25
 */
@ControllerAdvice
public class BaseExceptionHandler<T> {

    /**
     * @Title: 异常处理
     * @MethodName: error
     * @Return java.lang.String
     * @author: fjm
     * @date: 2020/2/21 16:26
     */
    /**-------- 通用异常处理方法 --------**/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WrappedResponse<T> error(Exception e) {
        e.printStackTrace();
        // 通用异常结果
        return WrappedResponse.fail(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public WrappedResponse<T> error(NullPointerException e) {
        e.printStackTrace();
        return WrappedResponse.fail("NullPointerException");
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
