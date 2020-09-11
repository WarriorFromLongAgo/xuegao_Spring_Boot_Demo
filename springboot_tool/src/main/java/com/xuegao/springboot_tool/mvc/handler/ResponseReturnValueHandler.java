package com.xuegao.springboot_tool.mvc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.mvc.handler
 * <br/> @ClassName：ResponseReturnValueHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/8/16 15:42
 */
public class ResponseReturnValueHandler implements HandlerMethodReturnValueHandler {
    private static final Logger log = LoggerFactory.getLogger(ResponseReturnValueHandler.class);

    /**
     * <br/> @Title: 只处理@ResponseExcel 声明的方法
     * <br/> @MethodName:  supportsReturnType
     * <br/> @param returnType:
     * <br/> @Return boolean
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/8/16 15:45
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // returnType.getMethodAnnotation(ResponseExcel.class) != null;
        return false;
    }

    /**
     * <br/> @Title: 处理逻辑
     * <br/> @MethodName:  handleReturnValue
     * <br/> @param returnValue:
     * <br/> @param returnType:
     * <br/> @param mavContainer:
     * <br/> @param webRequest:
     * <br/> @Return void
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/8/16 15:45
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        // /* check */
        // HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        // Assert.state(response != null, "No HttpServletResponse");
        // ResponseExcel responseExcel = parameter.getMethodAnnotation(ResponseExcel.class);
        // Assert.state(responseExcel != null, "No @ResponseExcel");
        // mavContainer.setRequestHandled(true);
        //
        // sheetWriteHandlerList.forEach(handler -> {
        //     handler.export(o, response, responseExcel);
        // });
    }


    public ResponseReturnValueHandler() {
    }


}