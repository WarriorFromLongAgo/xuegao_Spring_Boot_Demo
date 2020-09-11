package com.xuegao.websocket.model.bo;

/**
 * <br/> @PackageName：com.xuegao.websocket.model
 * <br/> @ClassName：WiselyResponse
 * <br/> @Description：后台发送消息实体
 * <br/> @author：xuegao
 * <br/> @date：2020/7/23 21:17
 */
public class WiselyResponse {
    private String responseMessage;

    public WiselyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}