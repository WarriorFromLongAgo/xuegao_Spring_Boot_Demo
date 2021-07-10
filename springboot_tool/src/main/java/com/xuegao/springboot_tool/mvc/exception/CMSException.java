package com.xuegao.springboot_tool.mvc.exception;

/***
 * <br/> @Title:
 * <br/> @MethodName:  自定义异常
 * <br/> @Return
 * <br/> @Description: 
 * <br/> @date:  2020/4/5 15:22
 */
public class CMSException extends RuntimeException {
    private Integer code;

    public CMSException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    // public CMSException(ResultCodeEnum resultCodeEnum) {
    //     super(resultCodeEnum.getMessage());
    //     this.code = resultCodeEnum.getCode();
    // }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }

    /* avoid the expensive and useless stack trace for api exceptions */
    /* 翻译：避免对api异常进行昂贵且无用的堆栈跟踪 */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}