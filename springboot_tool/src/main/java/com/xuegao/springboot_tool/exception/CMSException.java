package com.xuegao.springboot_tool.exception;

/***
 * <br/> @Title:
 * <br/> @MethodName:  自定义异常
 * <br/> @Return
 * <br/> @Description: 
 * <br/> @author: fjm
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


}